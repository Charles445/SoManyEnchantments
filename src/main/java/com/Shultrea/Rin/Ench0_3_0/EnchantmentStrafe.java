package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Prop_Sector.ArrowPropertiesProvider;
import com.Shultrea.Rin.Prop_Sector.IArrowProperties;
import com.Shultrea.Rin.Utility_Sector.RefStrings;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentStrafe extends Enchantment{
	public EnchantmentStrafe()
	{
		super(Rarity.UNCOMMON, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("Strafe");
		this.setRegistryName("Strafe");

	}
	
	@Override
	public int getMaxLevel()
    {
        return 6;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 14 + 12 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return fTest == Enchantments.INFINITY || fTest == Enchantments.PUNCH || fTest == Smc_030.AdvancedPunch ? false : super.canApplyTogether(fTest);  	
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
	public void onEvent(LivingEntityUseItemEvent.Tick event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		ItemStack bow = event.getItem();
		if(bow.isEmpty()){
			return;
		}
		
		if (EnchantmentHelper.getEnchantmentLevel(Smc_030.Strafe, bow) > 0)
			
		if (bow.getItem() instanceof ItemBow)
		{
			
			
				int d =event.getDuration();
				if (EnchantmentHelper.getMaxEnchantmentLevel(Smc_030.Strafe, entity ) <= 4)
				{
					if (d%(5-EnchantmentHelper.getMaxEnchantmentLevel(Smc_030.Strafe, entity)) == 0)
					{
						event.setDuration(d-1);
						if(event.getDuration() < 5000)
							event.setDuration(20000);
					}
				}
				else if(EnchantmentHelper.getMaxEnchantmentLevel(Smc_030.Strafe, entity ) == 5)
				{
					event.setDuration(d-(EnchantmentHelper.getMaxEnchantmentLevel(Smc_030.Strafe, entity))-8);
					if(event.getDuration() < 5000)
						event.setDuration(20000);
				}
				else if(EnchantmentHelper.getMaxEnchantmentLevel(Smc_030.Strafe, entity ) > 5)
				{
					event.setDuration(d-(EnchantmentHelper.getMaxEnchantmentLevel(Smc_030.Strafe, entity))-1200);
					if(event.getDuration() < 5000)
						event.setDuration(20000);
				}
			
		}
	}

	@SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
    public void onEvent(LivingHurtEvent fEvent)
    {
		
    	if (!(fEvent.getSource().getDamageType().equals("arrow")))
    		return;
    	
    	if(!(fEvent.getSource().getImmediateSource() instanceof EntityArrow))
    		return;
    	
    	EntityArrow arrow = (EntityArrow) fEvent.getSource().getImmediateSource();	
    	
		if(!(arrow.hasCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null)))
			return;
			
		IArrowProperties ar = arrow.getCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null);
    		
    	if(ar.isArrowRapidDamage()){	
    		fEvent.getEntityLiving().hurtResistantTime = 0;
    	}
    	
		
    	}
    	    
    	    
    }
    	    
    	    
    	
    	
    
    
    

	