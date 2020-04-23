package com.Shultrea.Rin.Ench0_4_5;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentFrenzy extends Enchantment {
	public EnchantmentFrenzy()
	{
		super(Rarity.RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Frenzy");
		this.setRegistryName("Frenzy");
		
	}
	@Override
	public int getMaxLevel()
    {
        return 2;
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
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return somanyenchantments.config.Frenzy && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return somanyenchantments.config.Frenzy;
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
 
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onAttack(LivingAttackEvent e) {
    	if(!e.isCanceled() && e.getAmount() > 0) {
    		if(e.getSource().getTrueSource() != null && e.getSource().damageType.equals("player")) {
    		World world = e.getEntityLiving().world;
    		
    		Random random = new Random();
    		
    		EntityLivingBase attacker = (EntityLivingBase) e.getSource().getTrueSource();
    		
    		EntityLivingBase victim = e.getEntityLiving();
    			
    		if(attacker.getHeldItemMainhand().isEmpty())
    			return;
    		
    		if(victim instanceof EntityPlayer)
    			return;
    		
    		int level = EnchantmentHelper.getEnchantmentLevel(this, attacker.getHeldItemMainhand());
    		
    		boolean roll = random.nextInt(10) < level * 200;
    				
    		if(roll) {
    			List list = world.getEntitiesWithinAABBExcludingEntity(attacker, victim.getEntityBoundingBox().grow(5 + level * 5, 5 + level * 5, 5 + level * 5));
    			Collections.shuffle(list);
    			for(int x = 0; x < list.size(); x++) {
    				if(list.get(x) instanceof EntityLivingBase) {
    				EntityLivingBase randomTarget = (EntityLivingBase) list.get(x);
    					if(victim.getDistanceSq(randomTarget) <= 36 + (level - 1) * 28) {
    						if(victim.getMaxHealth() <= victim.getHealth())
    							victim.setRevengeTarget(randomTarget);
    						e.setCanceled(true);
    			
    						//victim.attackEntityFrom(new EntityDamageSource("confusion", attacker), e.getAmount());
    						
    						
    					}
    				}
    			}
    		}
    			
    	}
    }
  }
}