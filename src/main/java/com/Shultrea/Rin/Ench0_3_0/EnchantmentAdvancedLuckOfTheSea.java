package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentAdvancedLuckOfTheSea extends EnchantmentBase
{
	public EnchantmentAdvancedLuckOfTheSea()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.FISHING_ROD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("AdvancedLuckOfTheSea");
		this.setRegistryName("AdvancedLuckOfTheSea");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AdvancedLuckOfTheSea;
	}
		
	@Override
	public int getMaxLevel()
	{
		return ModConfig.level.AdvancedLuckOfTheSea;
	}
		
	@Override
	public int getMinEnchantability(int par1)
	{
		return 15 + 15 * (par1);
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 30;
	}
	    
	@Override
	public boolean canApplyTogether(Enchantment fTest)
	{
		
		 return fTest == Enchantments.LUCK_OF_THE_SEA  ? false : super.canApplyTogether(fTest);
	}
	
	public static int getValue(ItemStack stack)
	{
		if(!Smc_030.AdvancedLuckOfTheSea.isEnabled())
			return 0;
		
		int level = EnchantmentHelper.getEnchantmentLevel(Smc_030.AdvancedLuckOfTheSea, stack);
		if(level <= 0)
			return 0;
		
		int toReturn = level * 2 + 2;
		if(Math.random() < 0.15f)
			toReturn = level * 3 + 3;
		
		return toReturn;
	}
	
	/*
	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void onEvent(EntityJoinWorldEvent fEvent)
	{
		if (fEvent.getEntity() instanceof EntityFishHook)
		{
			EntityFishHook hook = (EntityFishHook) fEvent.getEntity();
			EntityLivingBase fisher = hook.getAngler();
			ItemStack fishingRod = fisher.getHeldItemMainhand();
			
			if(fishingRod == null || fishingRod == ItemStack.EMPTY){
				
				fishingRod = fisher.getHeldItemOffhand();
				if(fishingRod == null || fishingRod == ItemStack.EMPTY){
					
					return;
				}
			}
			int level = EnchantmentHelper.getEnchantmentLevel(Smc_030.AdvancedLuckOfTheSea, fishingRod);
			
			if(level <= 0)
			return;
			
			hook.setLuck(level * 2 + 2);
			if(Math.random() < 0.15f){
			hook.setLuck(level * 3 + 3);
			}
		}
	}
	*/
}
	