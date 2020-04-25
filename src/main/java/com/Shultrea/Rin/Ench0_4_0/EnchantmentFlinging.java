package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;





public class EnchantmentFlinging extends Enchantment {
	public EnchantmentFlinging()
	{
		super(Rarity.UNCOMMON, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("flinging");
		this.setRegistryName("flinging");

	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 5 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 20;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return fTest == Enchantments.KNOCKBACK ||  fTest == Smc_020.AdvancedKnockback ? false : super.canApplyTogether(fTest);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem().canApplyAtEnchantingTable(stack, this) && ModConfig.enabled.Flinging;
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return ModConfig.enabled.Flinging;
    }
    
    @Override
    public void onEntityDamaged (EntityLivingBase user, Entity target, int level) {
    	        
    	ItemStack stack = user.getHeldItemMainhand();
    	int levelknockBack = EnchantmentHelper.getEnchantmentLevel(Smc_040.flinging, stack);
   
    	double Y = levelknockBack * 0.1875D + 0.075f;
    	target.motionY += Y;
    	target.velocityChanged = true;
            
       	
            
            
        }
    }
   
    