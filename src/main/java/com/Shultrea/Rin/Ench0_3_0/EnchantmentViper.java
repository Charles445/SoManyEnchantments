package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Interfaces.IConditionalDamage;
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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class EnchantmentViper extends EnchantmentBase implements IConditionalDamage {
	public EnchantmentViper()
	{
		super(Rarity.RARE,EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Viper");
		this.setRegistryName("Viper");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.ViperEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 18 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 42;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof IConditionalDamage);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @SubscribeEvent(priority=EventPriority.HIGH)
    public void HandleEnchant(LivingHurtEvent fEvent)
    {
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
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_030.Viper, dmgSource) <= 0)
			return;
		
		int levelViper = EnchantmentHelper.getEnchantmentLevel(Smc_030.Viper, dmgSource);
		
		float forgeDamage = fEvent.getAmount();
		
		float FDamage = 0;
		
		if(fEvent.getEntityLiving().isPotionActive(MobEffects.POISON)){
			
			 FDamage += 1.25 + 0.75f * levelViper;
			 
			
		}
		if(fEvent.getEntityLiving().isPotionActive(MobEffects.WITHER)){
			
			 FDamage += 0.5 + 0.5f * levelViper;
				 
		}
		
		//System.out.println(FDamage + " - Additional Damage Viper");
				
		forgeDamage = EnchantmentsUtility.CalculateDamageIgnoreSwipe(forgeDamage + FDamage, 0.0f, 1.00f, 1.0f, attacker, null);
		
		fEvent.setAmount(forgeDamage);
    }
		
    }

