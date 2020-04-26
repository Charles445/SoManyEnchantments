package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Interfaces.IWeatherEnchantment;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentRaining extends EnchantmentBase implements IWeatherEnchantment{
	public EnchantmentRaining()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Raining");
		this.setRegistryName("Raining");

	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.RainingEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 6;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 15 + (par1 - 1) * 15;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof IWeatherEnchantment);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }

@SubscribeEvent
public void HandleEnchant(LivingHurtEvent fEvent){
	float SkyDamage = 0.0f;
	
	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
		return;
	
	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
		return;
	
	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
	if(attacker == null)
		return;
	
	ItemStack dmgSource = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();
	if(dmgSource == null)
		return;
	
	if(EnchantmentHelper.getEnchantmentLevel(Smc_030.Raining, dmgSource) <= 0)
		return;
	
	int levelRain = EnchantmentHelper.getEnchantmentLevel(Smc_030.Raining, dmgSource);
	
	float Damage = fEvent.getAmount();
	
	if(attacker.world.isRaining() == true && EnchantmentsUtility.noBlockLight(attacker))
		
	{
		float FDamage = EnchantmentsUtility.CalculateDamageIgnoreSwipe(Damage, 0.2f, 0.80f, 1.0f, attacker, Smc_030.Raining);
	    fEvent.setAmount(FDamage);
	
	}
	else if(attacker.world.isRaining() == false && EnchantmentsUtility.noBlockLight(attacker))
		
	{
		float Fi = EnchantmentsUtility.CalculateDamageForNegativeSwipe(Damage, -0.2f, -0.3f, 1.0f, attacker, Smc_030.Raining);
		fEvent.setAmount(Fi);
		
		if(fEvent.getEntity().world.rand.nextInt(500) < 3 + levelRain){
		EnchantmentsUtility.Raining(fEvent.getEntityLiving().getEntityWorld());
		}
		else if(EnchantmentsUtility.noBlockLight(attacker) == false){
			float Fin = EnchantmentsUtility.CalculateDamageForNegativeSwipe(Damage, 0.0f, -0.5f, 1.0f, attacker, Smc_030.Raining);
			fEvent.setAmount(Fin);
		}
	
}
}
}
   
