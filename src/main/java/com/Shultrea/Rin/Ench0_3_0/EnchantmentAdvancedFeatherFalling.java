package com.Shultrea.Rin.Ench0_3_0;



import com.Shultrea.Rin.Interfaces.IEnchantmentProtection;
import com.Shultrea.Rin.Interfaces.IEnhancedEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;


public class EnchantmentAdvancedFeatherFalling extends Enchantment implements IEnhancedEnchantment{
	public EnchantmentAdvancedFeatherFalling()
	{
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
		this.setName("AdvancedFeatherFalling");
		this.setRegistryName("AdvancedFeatherFalling");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 4;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 27 + (par1 - 1) * 12;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
    	return this.getMinEnchantability(par1) + 40;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	 return fTest == Enchantments.FEATHER_FALLING ? false : super.canApplyTogether(fTest);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @Override
    public int calcModifierDamage(int level, DamageSource source)
    {
    	if(Math.random() < 0.35f) 
    		 return source.canHarmInCreative() ? 0 : (source == DamageSource.FALL ? level * 5 : 0);
    	
    	else return source.canHarmInCreative() ? 0 : (source == DamageSource.FALL ? level * 4 : 0);
    }
    
    

}

