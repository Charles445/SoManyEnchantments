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
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentPhysicalProtection extends Enchantment implements IEnchantmentProtection{
	public EnchantmentPhysicalProtection()
	{
        super(Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
		this.setName("PhysicalProtection");
		this.setRegistryName("PhysicalProtection");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 4;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 14 + (par1 - 1) * 12;
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
    		
    		else return true;
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
        return source.canHarmInCreative() ? 0 : (!(source.isMagicDamage() || source.isFireDamage() || source.isExplosion() || source.isProjectile() || source.damageType.equals("outOfWorld") || source.damageType.equals("drown") || source.damageType.equals("generic") || source.damageType.equals("wither") || source.damageType.equals("lightningBolt") || source.damageType.equals("inFire") || source.damageType.equals("onFire") || source.damageType.equals("hotFloor") || source.damageType.equals("Ethereal") ||  source.damageType.equals("Culled")) ? level * 3 : 0);
    }

}

