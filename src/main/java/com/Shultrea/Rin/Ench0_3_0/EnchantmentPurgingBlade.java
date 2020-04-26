package com.Shultrea.Rin.Ench0_3_0;

import java.util.Collection;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentPurgingBlade extends EnchantmentBase {
	public EnchantmentPurgingBlade()
	{
		super(Rarity.UNCOMMON, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("PurgingBlade");
		this.setRegistryName("PurgingBlade");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.PurgingBladeEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 15 + (par1 - 1) * 8;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
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

    @SubscribeEvent(priority=EventPriority.HIGHEST, receiveCanceled=true)
    public void HandleEnchant(LivingHurtEvent fEvent)
    {
    	if(!fEvent.getSource().damageType.equals("player") && !fEvent.getSource().damageType.equals("mob"))
			return;
    	
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(attacker == null)
    		return;
    	
    	ItemStack dmgSource = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();
		if(dmgSource == null)
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_030.PurgingBlade, dmgSource) <= 0)
			return;
		
		int levelPurging = EnchantmentHelper.getEnchantmentLevel(Smc_030.PurgingBlade, dmgSource);
		
		if(Math.random() <= 0.1 + (0.06f * levelPurging)){
		
		UtilityAccessor.damageTarget(fEvent.getEntityLiving(), DamageSource.MAGIC, 1.25f + (0.75f * levelPurging));
		fEvent.getEntityLiving().hurtResistantTime = 0;
		fEvent.setAmount(fEvent.getAmount() * 1.2f);
		
		Collection<PotionEffect> collectPotion = fEvent.getEntityLiving().getActivePotionEffects();
		int size = collectPotion.size();
		
		
		for(int i = 0; i < size; i++){
			
			if(!collectPotion.iterator().hasNext())
				return;
			
			PotionEffect Potions = collectPotion.iterator().next();
			if(Math.random() > 0.5f){
				if(collectPotion.iterator().hasNext()){
				Potions = collectPotion.iterator().next();
				}
				
				if(Math.random() > 0.5f){
					if(collectPotion.iterator().hasNext()){
					Potions = collectPotion.iterator().next();
					}
				}
			}
			
			
			Potions.combine(new PotionEffect(Potions.getPotion(), 0 , 256));
			
			//int potionLevel = Potions.getAmplifier();
	
		}
    }
    }
}