package com.Shultrea.Rin.Ench0_4_5;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentFrenzy extends EnchantmentBase {
	public EnchantmentFrenzy()
	{
		super(Rarity.RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Frenzy");
		this.setRegistryName("Frenzy");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.Frenzy;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Frenzy;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 30 + (par1 - 1) * 15;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }
 
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onAttack(LivingAttackEvent event)
	{
		if(!event.isCanceled() && event.getAmount() > 0)
		{
			if(event.getSource().getTrueSource() != null && event.getSource().damageType.equals("player"))
			{
				World world = event.getEntityLiving().world;
				
				Random random = new Random();
				
				EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
				
				EntityLivingBase victim = event.getEntityLiving();
				
				if(attacker.getHeldItemMainhand().isEmpty())
					return;
	    		
				if(victim instanceof EntityPlayer)
					return;

				//Cap level to 10 to avoid incredibly large AABB checks in the event that someone acquires or cheats in a high level book
				int level = Math.min(10, EnchantmentHelper.getEnchantmentLevel(this, attacker.getHeldItemMainhand()));
				
				boolean roll = random.nextInt(10) < level * 200;
				
				
				if(roll)
				{
					List list = world.getEntitiesWithinAABBExcludingEntity(attacker, victim.getEntityBoundingBox().grow(5 + level * 5, 5 + level * 5, 5 + level * 5));
					Collections.shuffle(list);
					for(int x = 0; x < list.size(); x++)
					{
						if(list.get(x) instanceof EntityLivingBase)
						{
							EntityLivingBase randomTarget = (EntityLivingBase) list.get(x);
							if(victim.getDistanceSq(randomTarget) <= 36 + (level - 1) * 28)
							{
								if(victim.getMaxHealth() <= victim.getHealth())
								{
									victim.setRevengeTarget(randomTarget);
								
									//Check if the victim has been given a revenge target or already has one
									World victimWorld = victim.getEntityWorld();
									EntityLivingBase revenge = victim.getRevengeTarget();
									
									if(victimWorld instanceof WorldServer && revenge != null)
									{
										//Schedule the task, otherwise the attacker will become the target every time
										((WorldServer)world).addScheduledTask(() -> 
										{
											try
											{
												//Make absolutely sure that the three entities involved still exist
												if(victim!=null && attacker!=null && revenge != null)
												{
													//A dead victim can't get angry
													if(!victim.isDead)
													{
														//Double check that the victim class is ready for casting
														if(victim instanceof EntityLiving)
														{
															//Prevent monsters from targeting themselves
															if(!victim.getUniqueID().equals(revenge.getUniqueID()))
															{
																//Get mad!
																EntityLiving victimLiving = (EntityLiving)victim;
																victimLiving.setRevengeTarget(revenge);
																victimLiving.setAttackTarget(revenge);
															}
														}
													}
												}
											}
											catch(Exception ex)
											{
												//Task failed
											}
										});
									}
								}
    						
								//victim.attackEntityFrom(new EntityDamageSource("confusion", attacker), e.getAmount());
							}
						}
					}
				}
			}
		}
	}
}
