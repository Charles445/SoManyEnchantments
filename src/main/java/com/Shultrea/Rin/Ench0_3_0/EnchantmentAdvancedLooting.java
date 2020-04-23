package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentAdvancedLooting extends Enchantment 
{
	public EnchantmentAdvancedLooting()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("AdvancedLooting");
		this.setRegistryName("AdvancedLooting");
	}
		
	@Override
	public int getMaxLevel()
	{
		return 3;
	}
		
	@Override
	public int getMinEnchantability(int par1)
	{
		return 30 + 15 * (par1 - 1);
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 60;
	}
	    
	@Override
	public boolean canApplyTogether(Enchantment fTest)
	{
		return fTest == Enchantments.LOOTING ? false : super.canApplyTogether(fTest);
	}
	@Override
	public boolean canApply(ItemStack fTest)
	{
		return super.canApply(fTest);
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST) 
	public void HandleEnchant(LootingLevelEvent fEvent)
	{	
		if(!(fEvent.getDamageSource().getTrueSource() instanceof EntityPlayer))
			return;
		
		EntityPlayer player = (EntityPlayer) fEvent.getDamageSource().getTrueSource();
		ItemStack sword = player.getHeldItemMainhand();
					
		if(sword == null)
			return;
		
		int levelLooting = EnchantmentHelper.getEnchantmentLevel(Smc_030.AdvancedLooting, sword);
			
		if(levelLooting <= 0)
			return;
		
		fEvent.setLootingLevel(fEvent.getLootingLevel() + 2 + ((levelLooting - 1) * 2));
			
		if(Math.random() < 0.25f){
			
		fEvent.setLootingLevel(fEvent.getLootingLevel() + 2 + (levelLooting * 2));
		}	 
		}
	    
	
		
	}

