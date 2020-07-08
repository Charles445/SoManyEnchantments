package com.Shultrea.Rin.Ench0_2_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;





public class EnchantmentAdvancedKnockback extends EnchantmentBase
{
	public EnchantmentAdvancedKnockback()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("AdvancedKnockback");
		this.setRegistryName("AdvancedKnockback");

	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AdvancedKnockbackEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.AdvancedKnockback;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 25 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	
    	return super.canApplyTogether(fTest) && fTest != Enchantments.KNOCKBACK;
    
    }
    
    
    public boolean isValidPlayer(Entity entity) {

        if (entity instanceof EntityPlayer) {

            if (((EntityPlayer) entity).getHeldItemMainhand() != null) {

                if (level(((EntityPlayer) entity).getHeldItemMainhand()) > 0) {

                    return true;
                }
            }
        }

        return false;
    }
  
  public boolean isValidMob(Entity entity) {

        if (entity instanceof EntityMob || entity instanceof EntityAnimal) {

            if (((EntityMob) entity).getHeldItemMainhand() != null) {

                if (level(((EntityMob) entity).getHeldItemMainhand()) > 0) {

                    return true;
                }
            }
            if (((EntityAnimal) entity).getHeldItemMainhand() != null) {

                if (level(((EntityAnimal) entity).getHeldItemMainhand()) > 0) {

                    return true;
                }
            }
        }

        return false;
    } 
  
  public int level(ItemStack stack) {

        return EnchantmentHelper.getEnchantmentLevel(Smc_020.AdvancedKnockback, stack);
    }

  
  
         
	
    
  
	@Override
	public void onEntityDamaged (EntityLivingBase user, Entity target, int level)
	{
		if(target instanceof EntityLivingBase)
		{
			ItemStack stack = user.getHeldItemMainhand();
			int levelknockBack = EnchantmentHelper.getEnchantmentLevel(Smc_020.AdvancedKnockback, stack);
			int modKnockback = 1;
			double Y = levelknockBack * 0.075D;
			target.motionY += Y;
			
			
			int Thelevel = EnchantmentHelper.getEnchantmentLevel(Smc_020.AdvancedKnockback, stack);
			
			int Knockback = 1;
			Knockback += Thelevel * 4;
			
			double XMot = target.motionX;
			double ZMot = target.motionZ;
			
			XMot += (double)(-MathHelper.sin(user.rotationYaw * (float)Math.PI / 180.0F) * (float)Knockback * 0.125F);
			ZMot += (double)(MathHelper.cos(user.rotationYaw * (float)Math.PI / 180.0F) * (float)Knockback * 0.125F);
			target.motionX = XMot /1.1D;
			target.motionZ = ZMot /1.1D;
			//System.out.println(target.motionX);
			//System.out.println(target.motionZ);
		}
            
	}
}
   
    

