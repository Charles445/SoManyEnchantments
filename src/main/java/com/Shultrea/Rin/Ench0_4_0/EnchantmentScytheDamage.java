package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class EnchantmentScytheDamage extends EnchantmentBase {
	public EnchantmentScytheDamage()
	{
		super(Rarity.RARE, EnumList.HOE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("ScytheDamage");
		this.setRegistryName("ScytheDamage");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.ScytheDamage;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 5;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 14 + 12 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }
    
    @Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
    	return super.canApplyAtEnchantingTable(stack) && stack.getItem() instanceof ItemHoe;
	}
    
    @Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType)
    {
    	if(ModConfig.enabled.ScytheDamage)
    		return (1.0f + 0.55f * level);
    	
    	return 0;
    }
    
}

