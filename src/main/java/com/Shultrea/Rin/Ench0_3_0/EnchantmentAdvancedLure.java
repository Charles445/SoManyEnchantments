package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentAdvancedLure extends EnchantmentBase
{
	public EnchantmentAdvancedLure()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.FISHING_ROD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("AdvancedLure");
		this.setRegistryName("AdvancedLure");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AdvancedLure;
	}
		
	@Override
	public int getMaxLevel()
	{
		return ModConfig.level.AdvancedLure;
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
		return fTest == Enchantments.LURE  ? false : super.canApplyTogether(fTest);
	}
	
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
		
			
			//ItemStack item = EnchantmentHelper.getEnchantedItem(Smc_030.AdvancedLure, fisher);
			
			int level = EnchantmentHelper.getEnchantmentLevel(Smc_030.AdvancedLure, fishingRod);
			
			if(level <= 0)
			return;
			
			hook.setLureSpeed(level + 1);
			if(Math.random() < 0.15f){
			hook.setLureSpeed(level + 2);
			}
		}
}
	}
	