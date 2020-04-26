package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentAdvancedPower extends EnchantmentBase {
	public EnchantmentAdvancedPower()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("AdvancedPower");
		this.setRegistryName("AdvancedPower");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AdvancedPowerEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 10 + (par1 - 1) * 8;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	if(fTest == Enchantments.POWER)
    		return false;
    	
    	return super.canApplyTogether(fTest);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }

@SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
public void onEvent(EntityJoinWorldEvent event)
{
	if (event.getEntity() instanceof EntityArrow)
	{
		
		EntityArrow arrow = (EntityArrow) event.getEntity();
		EntityLivingBase shooter = (EntityLivingBase) arrow.shootingEntity;
		if(shooter == null)
		return;
		
	    ItemStack bow = shooter.getActiveItemStack();
		
		if(bow == null || bow == ItemStack.EMPTY){
			
			bow = shooter.getHeldItemOffhand();
			if(bow == null || bow == ItemStack.EMPTY){
				
				return;
			}
		}
		
		 
		
		 int p = EnchantmentHelper.getEnchantmentLevel(Smc_030.AdvancedPower, bow);
		 if(p > 0){
			arrow.setDamage(arrow.getDamage() + 1.25D + (double)p * 0.75D);
			
			 if(p >= 4){
				 arrow.setIsCritical(true);
			 }
			 
			 else if(shooter.getRNG().nextFloat() < (p * 0.25f)){
				 arrow.setIsCritical(true);
			 }
			 
			 
			 
		 }
	
	
		  
}
}
}

