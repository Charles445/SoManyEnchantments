package com.Shultrea.Rin.Ench0_3_0;



import java.util.Random;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;


public class EnchantmentBurningThorns extends EnchantmentBase{
	public EnchantmentBurningThorns()
	{
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST,EntityEquipmentSlot.LEGS,EntityEquipmentSlot.FEET,});
		this.setName("BurningThorns");
		this.setRegistryName("BurningThorns");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.BurningThorns;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 12 + (par1 - 1) * 9;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
    	return this.getMinEnchantability(par1) + 30;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	 return fTest == Smc_030.AdvancedThorns || fTest == Enchantments.THORNS? false : super.canApplyTogether(fTest);
    }
    
    @Override
    public void onUserHurt(EntityLivingBase user, Entity attacker, int level)
    {
    	if(user == null)
    		return;
    	
    	if(attacker == null || attacker.isDead)
    		return;
    	
        Random random = user.getRNG();
        ItemStack itemstack = EnchantmentHelper.getEnchantedItem(Enchantments.THORNS, user);

        if (shouldHit(level, random))
        {
            if (attacker != null)
            {
                attacker.attackEntityFrom(DamageSource.causeThornsDamage(user), (float)getDamage(level, random));
                attacker.setFire((1 * level) + 2);
            }

            if (!itemstack.isEmpty())
            {
                damageArmor(itemstack, 5, user);
            }
        }
        else if (!itemstack.isEmpty())
        {
            damageArmor(itemstack, 2, user);
        }
    }

    public static boolean shouldHit(int level, Random rnd)
    {
        return level <= 0 ? false : rnd.nextFloat() < 0.14F * (float)level;
    }

    public static int getDamage(int level, Random rnd)
    {
        return level > 10 ? level - 9 : 2 + rnd.nextInt(3);
    }

    private void damageArmor(ItemStack stack, int amount, EntityLivingBase entity)
    {
        int slot = -1;
        int x = 0;
        for (ItemStack i : entity.getArmorInventoryList())
        {
            if (i == stack){
                slot = x;
                break;
            }
            x++;
        }
        if (slot == -1 || !(stack.getItem() instanceof net.minecraftforge.common.ISpecialArmor))
        {
            stack.damageItem(1, entity);
            return;
        }
        net.minecraftforge.common.ISpecialArmor armor = (net.minecraftforge.common.ISpecialArmor)stack.getItem();
        armor.damageArmor(entity, stack, DamageSource.causeThornsDamage(entity), amount, slot);
    }
}