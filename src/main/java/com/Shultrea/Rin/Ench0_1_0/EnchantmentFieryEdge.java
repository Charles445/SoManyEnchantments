package com.Shultrea.Rin.Ench0_1_0;

import java.util.Random;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentFieryEdge extends EnchantmentBase {
	public EnchantmentFieryEdge()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("FieryEdge");
		this.setRegistryName("FieryEdge");
	}

	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.FieryEdgeEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 25 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
    }

    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Enchantments.FIRE_ASPECT && !(fTest instanceof EnchantmentWaterAspect);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @Override
    public void onEntityDamaged(EntityLivingBase user, Entity target, int level)
    {
    	if(!(target instanceof EntityLivingBase))
        return;
    	
    	EntityLivingBase victim = (EntityLivingBase) target;
    	
    	target.setFire(level * 6);
    	
    	Random randy = new Random();
    	
    	if(victim.isBurning() && randy.nextInt(10 - level * 2) < 3){
    	victim.hurtResistantTime = 0;
    	}
    	
    }
}



