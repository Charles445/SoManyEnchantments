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
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentWinter extends EnchantmentBase implements IWeatherEnchantment{
	public EnchantmentWinter()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Winter");
		this.setRegistryName("Winter");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.WinterEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 6;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 10 + (par1 - 1) * 10;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
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

  
  public int level(ItemStack stack) {

        return EnchantmentHelper.getEnchantmentLevel(Smc_030.Winter, stack);
    }




@SubscribeEvent(priority=EventPriority.HIGHEST)
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
	
	if(EnchantmentHelper.getEnchantmentLevel(Smc_030.Winter, dmgSource) <= 0)
		return;
	
	BlockPos blockpos = attacker.getPosition();
	float Damage = fEvent.getAmount();
	
	int levelWinter = EnchantmentHelper.getEnchantmentLevel(Smc_030.Winter, dmgSource);
	
	if(attacker.world.isRaining() == true && EnchantmentsUtility.noBlockLight(attacker) && EnchantmentsUtility.isInColdTemperature(attacker, attacker.getEntityWorld().getBiome(blockpos), true))
		
	{
	
	//SkyDamage = (2.0f + levelWinter * 1.30f);
	//fEvent.setAmount(Damage + SkyDamage);
	
	{	
		float FDamage = EnchantmentsUtility.CalculateDamageIgnoreSwipe(Damage, 0.10f, 0.90f, 1.15f, attacker, Smc_030.Winter);
	    fEvent.setAmount(FDamage);
	}
	
	if(levelWinter >= 2)
	fEvent.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 60, levelWinter - 2));
	if(levelWinter >= 4)
	fEvent.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 60, levelWinter - 4));
	fEvent.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 60, levelWinter - 3));
	
	}
	else if((attacker.world.isRaining() == false && EnchantmentsUtility.noBlockLight(attacker)) || EnchantmentsUtility.isInColdTemperature(attacker, attacker.getEntityWorld().getBiome(blockpos), true) == false)
		
	{
		float Fin = EnchantmentsUtility.CalculateDamageForNegativeSwipe(Damage, 0.00f, -0.6f, 1.0f, attacker, Smc_030.Winter);
		fEvent.setAmount(Fin);
		
		
		
		
		if(Math.random() * 5 < 0.02f + levelWinter)
    	EnchantmentsUtility.Raining(attacker.getEntityWorld());
	
	
}
	else if(EnchantmentsUtility.noBlockLight(attacker) == false){
		float Fid = EnchantmentsUtility.CalculateDamageForNegativeSwipe(Damage, 0.00f, -0.8f, 1.0f, attacker, Smc_030.Winter);
		fEvent.setAmount(Fid);
	}
}
}
   