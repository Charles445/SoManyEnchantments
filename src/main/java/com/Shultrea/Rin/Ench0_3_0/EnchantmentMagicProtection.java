package com.Shultrea.Rin.Ench0_3_0;



import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Interfaces.IEnchantmentProtection;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.UtilityAccessor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentMagicProtection extends Enchantment implements IEnchantmentProtection{
	public EnchantmentMagicProtection()
	{
        super(Rarity.UNCOMMON, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
		this.setName("MagicProtection");
		this.setRegistryName("MagicProtection");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 4;
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
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @Override
    public int calcModifierDamage(int level, DamageSource source)
    {
        return source.canHarmInCreative() ? 0 : (source.isMagicDamage() ? level * 2 : 0);
    }
    
    

}

