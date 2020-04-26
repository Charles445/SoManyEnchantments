package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentInnerBerserk extends EnchantmentBase
{
	public EnchantmentInnerBerserk()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("InnerBerserk");
		this.setRegistryName("InnerBerserk");
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.InnerBerserk;
	}
		
	@Override
	public int getMaxLevel()
	{
		return 4;
	}
		
	@Override
    public int getMinEnchantability(int par1)
    {
        return 15 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
    }
	    
	@Override
	public boolean canApplyTogether(Enchantment fTest)
	{
		return super.canApplyTogether(fTest);
	}
	    
	@Override
    public boolean canApply(ItemStack fTest)
    {
		return super.canApply(fTest);
    }
	
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return ModConfig.enabled.InnerBerserk && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return ModConfig.enabled.InnerBerserk;
    }
	    
	@SubscribeEvent(priority = EventPriority.LOW) 
	public void HandleEnchant(LivingDamageEvent fEvent)
	{
		if(!(EnchantmentsUtility.checkEventCondition(fEvent, this))) return;			
		EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
		
		if(EnchantmentHelper.getMaxEnchantmentLevel(this, attacker) > 0)
		{
			float defenderHealthPercent = attacker.getHealth() / attacker.getMaxHealth();
			int levelfinish = EnchantmentHelper.getMaxEnchantmentLevel(this, attacker);
			float dmgMod = (1.0f - defenderHealthPercent) * ((levelfinish * 0.05f) + 1.1f);
			dmgMod = 1.0F + dmgMod;
			float Damage = fEvent.getAmount();		
			fEvent.setAmount(dmgMod * Damage);  
		}
	}
}