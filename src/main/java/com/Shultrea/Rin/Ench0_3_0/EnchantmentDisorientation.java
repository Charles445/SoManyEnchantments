package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Interfaces.IPotionDebuffer;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentDisorientation extends EnchantmentBase implements IPotionDebuffer{
	public EnchantmentDisorientation(){
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Disorientation");
		this.setRegistryName("Disorientation");
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.DisorientationEnable;
	}
		
	@Override
	public int getMaxLevel()
	{
		return 4;
	}
		
	@Override
	public int getMinEnchantability(int par1)
	{
		return 20 + 10 * (par1 - 1);
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 50;
	}
	    
	@Override
	public boolean canApplyTogether(Enchantment fTest)
	{
		return super.canApplyTogether(fTest) && !(fTest instanceof IPotionDebuffer);
	}
	
	@SubscribeEvent
	public void criticalWhenDisoriented(CriticalHitEvent e){
		
		if(!(e.getTarget() instanceof EntityLivingBase))
			return;
		
		EntityLivingBase eb = (EntityLivingBase) e.getTarget();
		
		if(eb.isPotionActive(MobEffects.SLOWNESS) && eb.isPotionActive(MobEffects.NAUSEA))
			if(EnchantmentsUtility.isLevelMax(e.getEntityPlayer().getHeldItemMainhand(), Smc_030.Disorientation))
				e.setDamageModifier(e.getDamageModifier() + 0.25f);
	}

	    	
	@Override
	public void onEntityDamaged(EntityLivingBase user, Entity entiti, int level)
	    {
	    	if(!(entiti instanceof EntityLivingBase))
	    		return;
	    	
	    	EntityLivingBase entity = (EntityLivingBase) entiti;
	    			
	    	if(entity.getRNG().nextInt(100) < 10)
	    	
	    	if(level == 1 || level == 2){
	    		entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 + (level * 10), level - 1));
	    		entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 80 + (level * 10), 0));
			}
			
				if(level >= 3){
					entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 + (level * 10), level - 1));
					entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 20 + (level * 10), level - 1));
					entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 25 + (level * 7), 0));
				
				
			}	
	    }
}