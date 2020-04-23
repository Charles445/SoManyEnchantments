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
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraft.entity.Entity;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IWeatherEnchantment;

import java.util.List;

import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentMoonlight extends Enchantment implements IWeatherEnchantment{
	public EnchantmentMoonlight()
	{
		super(Rarity.RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Moonlight");
		this.setRegistryName("Moonlight");
		
	}

	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 16 + (par1 - 1) * 12;
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
    public void onEntityDamaged(EntityLivingBase user, Entity entiti, int level)
    {
    	if(!(entiti instanceof EntityLivingBase))
    		return;
    	  	
    	EntityLivingBase entity = (EntityLivingBase) entiti;
    	 
    	if(!user.world.isDaytime() && EnchantmentsUtility.noBlockLight(user)){
    		if(!entity.isPotionActive(MobEffects.BLINDNESS))
    		entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200));
    		
    		else {
    			PotionEffect potion = entity.getActivePotionEffect(MobEffects.BLINDNESS);
    			entity.addPotionEffect(new PotionEffect(potion.getPotion(), potion.getDuration() + 80, potion.getAmplifier(), false, false));
    		}
    	}
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onHurtEvent(LivingHurtEvent e){
    	if(!(EnchantmentsUtility.checkEventCondition(e, this))) return;
    	EntityLivingBase attacker = (EntityLivingBase) e.getSource().getTrueSource();
    	
    	e.setAmount(EnchantmentsUtility.reduceDamage(attacker, false, attacker.getHeldItemMainhand(), this) + e.getAmount());
    }

}
   