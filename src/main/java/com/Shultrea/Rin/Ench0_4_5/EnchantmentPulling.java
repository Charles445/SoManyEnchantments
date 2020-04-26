package com.Shultrea.Rin.Ench0_4_5;

import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedPunch;
import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Prop_Sector.ArrowPropertiesProvider;
import com.Shultrea.Rin.Prop_Sector.IArrowProperties;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentPulling extends EnchantmentBase {
	public EnchantmentPulling()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.BOW, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND });
		this.setName("Pulling");
		this.setRegistryName("Pulling");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.Pulling;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
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
    	return fTest == Enchantments.PUNCH || fTest instanceof EnchantmentAdvancedPunch ? false : super.canApplyTogether(fTest);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return ModConfig.enabled.Pulling && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return ModConfig.enabled.Pulling;
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
		/**EntityArrow arrow = (EntityArrow) event.getEntity();
		EntityLivingBase shooter = (EntityLivingBase) arrow.shootingEntity;
		if(shooter == null)
		return;
		ItemStack bow = shooter.getHeldItemMainhand();
		int PunchLevel = EnchantmentHelper.getEnchantmentLevel(Smc_030.ExtremePunch, bow);
		if(PunchLevel == 0)
			return;
		arrow.setKnockbackStrength((PunchLevel * 2) + 1);
	   */
		
		EntityArrow arrow = (EntityArrow) event.getEntity();
		EntityLivingBase shooter = (EntityLivingBase) arrow.shootingEntity;
		if(shooter == null)
		return;
		
		
	    ItemStack bow = shooter instanceof EntityPlayer ? shooter.getActiveItemStack() : !shooter.getHeldItemMainhand().isEmpty() ? shooter.getHeldItemMainhand() : shooter.getHeldItemOffhand();
		
		if(bow == null || bow == ItemStack.EMPTY){
			
			bow = shooter.getHeldItemOffhand();
			if(bow == null || bow == ItemStack.EMPTY){
				
				return;
			}
		}
		
		
		 int p = EnchantmentHelper.getEnchantmentLevel(this, bow);
		 if(p > 0){
			 if(!arrow.hasCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null))
				 return;
				
			 	IArrowProperties properties = arrow.getCapability(ArrowPropertiesProvider.ARROWPROPERTIES_CAP, null);
				properties.setPullPower(1.25f + p * 1.75f);
			 }
		 }
	    
	}


}