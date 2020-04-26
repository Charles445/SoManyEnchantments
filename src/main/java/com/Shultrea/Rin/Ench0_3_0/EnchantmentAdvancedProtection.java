package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Interfaces.IEnchantmentProtection;
import com.Shultrea.Rin.Interfaces.IEnhancedEnchantment;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class EnchantmentAdvancedProtection extends EnchantmentBase implements IEnchantmentProtection, IEnhancedEnchantment{
	public EnchantmentAdvancedProtection()
	{
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
		this.setName("AdvancedProtection");
		this.setRegistryName("AdvancedProtection");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AdvancedProtection;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 4;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 32 + (par1 - 1) * 11;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
    	return this.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	if(fTest instanceof EnchantmentProtection){
    		EnchantmentProtection p = (EnchantmentProtection) fTest;
    		if(p.protectionType != EnchantmentProtection.Type.FALL)
    			return false;
    		
    		else return true;
    	}
    	
    	 return super.canApplyTogether(fTest) && !(fTest instanceof IEnchantmentProtection);
    }
    
    @Override
    public int calcModifierDamage(int level, DamageSource source)
    {
        return source.canHarmInCreative() ? 0 : level * 2;
    }
    
    

}

