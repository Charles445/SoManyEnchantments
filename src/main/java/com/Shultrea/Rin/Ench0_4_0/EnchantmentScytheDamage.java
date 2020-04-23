package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class EnchantmentScytheDamage extends Enchantment {
	public EnchantmentScytheDamage()
	{
		super(Rarity.RARE, EnumList.HOE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("ScytheDamage");
		this.setRegistryName("ScytheDamage");
		
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
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest);
    }
    
    @Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return stack.getItem() instanceof ItemHoe && somanyenchantments.config.ScytheDamage && stack.getItem().canApplyAtEnchantingTable(stack, this);
	}
    
    @Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType)
    {
    	if(somanyenchantments.config.ScytheDamage)
    		return (1.0f + 0.55f * level);
    	
    	return 0;
    }
     
    @Override
    public boolean isAllowedOnBooks()
    {
        return somanyenchantments.config.ScytheDamage;
    }
    
}

