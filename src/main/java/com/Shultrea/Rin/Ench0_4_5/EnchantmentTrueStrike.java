package com.Shultrea.Rin.Ench0_4_5;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentTrueStrike extends EnchantmentBase {
	public EnchantmentTrueStrike()
	{
		super(Rarity.RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("TrueStrike");
		this.setRegistryName("TrueStrike");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.TrueStrike;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 1;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 15 + (par1 - 1) * 15;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }
 
}