package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Interfaces.IPotionDebuffer;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class EnchantmentEnvenomed extends EnchantmentBase implements IPotionDebuffer{
	public EnchantmentEnvenomed(){
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Envenomed");
		this.setRegistryName("Envenomed");
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.EnvenomedEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 16 + 12 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {			
 	return super.canApplyTogether(fTest) && !(fTest instanceof IPotionDebuffer);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
 
    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity entiti, int level)
    {
    	if(!(entiti instanceof EntityLivingBase))
    		return;
    	EntityLivingBase entity = (EntityLivingBase) entiti;		
    	
 		entity.addPotionEffect(new PotionEffect(MobEffects.POISON, 30 + (level * 10), level - 1));	
 		entity.addPotionEffect(new PotionEffect(MobEffects.WITHER, 30 + (level * 10), level));		
    }
    
}

   
