package com.Shultrea.Rin.Ench0_3_0;



import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Interfaces.IEnchantmentProtection;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;


public class EnchantmentMagicProtection extends EnchantmentBase implements IEnchantmentProtection{
	public EnchantmentMagicProtection()
	{
        super(Rarity.UNCOMMON, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
		this.setName("MagicProtection");
		this.setRegistryName("MagicProtection");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.MagicProtectionEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.MagicProtection;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 12 + (par1 - 1) * 14;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
    	return this.getMinEnchantability(par1) + 45;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	if(fTest instanceof EnchantmentProtection){
    		EnchantmentProtection p = (EnchantmentProtection) fTest;
    		if(p.protectionType != EnchantmentProtection.Type.FALL)
    			return false;
    		
    		else return super.canApplyTogether(fTest) && !(fTest instanceof IEnchantmentProtection);
    	}
    	
    	return super.canApplyTogether(fTest) && !(fTest instanceof IEnchantmentProtection);
    }
    
    @Override
    public int calcModifierDamage(int level, DamageSource source)
    {
        return source.canHarmInCreative() ? 0 : (source.isMagicDamage() ? level * 2 : 0);
    }
    
    

}

