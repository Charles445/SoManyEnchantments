package com.Shultrea.Rin.Ench0_1_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Interfaces.IDamageMultiplier;
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

public class EnchantmentReviledBlade extends EnchantmentBase implements IDamageMultiplier
{
	public EnchantmentReviledBlade()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("ReviledBlade");
		this.setRegistryName("ReviledBlade");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.ReviledBladeEnable;
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
		return super.canApplyTogether(fTest) && !(fTest instanceof IDamageMultiplier);
	}
	    
	@Override
    public boolean canApply(ItemStack fTest)
    {
		return super.canApply(fTest);
    }
	    
	@SubscribeEvent(priority = EventPriority.LOWEST) 
	public void HandleEnchant(LivingDamageEvent fEvent)
	{
		if(!(EnchantmentsUtility.checkEventCondition(fEvent, this))) return;			
		EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
		ItemStack weapon = attacker.getHeldItemMainhand();
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_010.ReviledBlade, weapon) > 0)
		{
			float defenderHealthPercent = fEvent.getEntityLiving().getHealth() / fEvent.getEntityLiving().getMaxHealth();
			int levelfinish = EnchantmentHelper.getEnchantmentLevel(Smc_010.ReviledBlade, weapon);
			float dmgMod = (1.0f - defenderHealthPercent) * ((levelfinish * 0.1f) + 0.9f);
			dmgMod = 1.0F + dmgMod;
			float Damage = fEvent.getAmount();		
			
			//UtilityAccessor.damageEntity(fEvent.getEntityLiving(), somanyenchantments.PhysicalDamage, (dmgMod * Damage) - 0.001f);
			fEvent.setAmount(dmgMod * Damage);  
		}
	}
}
