package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IWeatherEnchantment;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentMoonlight extends EnchantmentBase implements IWeatherEnchantment{
	public EnchantmentMoonlight()
	{
		super(Rarity.RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Moonlight");
		this.setRegistryName("Moonlight");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.MoonlightEnable;
	}

	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Moonlight;
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
    public void onEntityDamagedAlt(EntityLivingBase user, Entity entiti, ItemStack stack, int level)
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
    	
    	if(this.isOffensivePetDisallowed(e.getSource().getImmediateSource(), e.getSource().getTrueSource()))
			return;
    	
    	EntityLivingBase attacker = (EntityLivingBase) e.getSource().getTrueSource();
    	
    	e.setAmount(EnchantmentsUtility.reduceDamage(attacker, false, attacker.getHeldItemMainhand(), this) + e.getAmount());
    }

}
   