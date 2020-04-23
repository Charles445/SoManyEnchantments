package com.Shultrea.Rin.Ench0_3_0;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraft.entity.Entity;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Interfaces.IWeatherEnchantment;

import java.util.List;

import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class EnchantmentClearsky extends Enchantment implements IWeatherEnchantment{
	public EnchantmentClearsky()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Clearsky");
		this.setRegistryName("Clearsky");
		
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
	
	if(EnchantmentHelper.getEnchantmentLevel(Smc_030.Clearsky, dmgSource) <= 0)
		return;
	
	
	int levelSunshine = EnchantmentHelper.getEnchantmentLevel(Smc_030.Clearsky, dmgSource);
	
	//System.out.println("ClearSky Initiating Damage");
	
	//Boolean PrintThis = EnchantmentsUtility.noBlockLight(attacker);
	
	//System.out.println(PrintThis);
	
	if(attacker.world.isRaining() == false && EnchantmentsUtility.noBlockLight(attacker))
		
	{
	//SkyDamage = (levelSunshine * 0.90f);
	//float Damage = fEvent.getAmount();
	//fEvent.setAmount(Damage + SkyDamage);
	
		float FDamage = EnchantmentsUtility.CalculateDamageIgnoreSwipe(fEvent.getAmount(), 0.5f, 0.75f, 1.00f, attacker, Smc_030.Clearsky);
	    fEvent.setAmount(FDamage);
	
	
	//System.out.println(SecondDamage);
		//System.out.println(Damage);
		
	}
	else if(attacker.world.isRaining() == true || attacker.world.isThundering() == true)
		
	{
		if(Math.random() * 2000 < 3 + (levelSunshine * 2)){
		EnchantmentsUtility.clearSky(fEvent.getEntityLiving().getEntityWorld());
		}
		
		float Fi = EnchantmentsUtility.CalculateDamageForNegativeSwipe(fEvent.getAmount(), 0.0f, -0.6f, 1.0f, attacker, Smc_030.Clearsky);
	    fEvent.setAmount(Fi);
		
	}
}
}
