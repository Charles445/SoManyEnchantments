package com.Shultrea.Rin.Ench0_4_0;

import java.util.Map;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentUpgradedPotentials extends EnchantmentBase {
	public EnchantmentUpgradedPotentials()
	{
		super(Rarity.RARE, EnumList.NONE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("upgrade");
		this.setRegistryName("upgrade");
		
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.Upgrade;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 1;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 35;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 45;
    }
    
    @Override
    public boolean isTreasureEnchantment(){
    	return true;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest);
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return false;
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return ModConfig.enabled.Upgrade && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return ModConfig.enabled.Upgrade;
    }
	
	@SubscribeEvent
	public void onAnvilAttach(AnvilUpdateEvent event)
	{
		ItemStack left = event.getLeft();
		ItemStack right = event.getRight();
		if(left.isEmpty() || right.isEmpty())
		{
			//This isn't actually necessary? Doing it anyway
			return;
		}
		if(right.getItem()==Items.ENCHANTED_BOOK)
		{
			//Right is an enchanted book
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(right);
			if(enchantments.containsKey(this) && enchantments.get(this) >= 1)
			{
				//Right has Upgraded Potentials book
				
				if(left.isStackable())
				{
					//Left is stackable, right slot has upgraded potentials, set output to empty and return
					event.setOutput(ItemStack.EMPTY);
					return;
				}
				
				//Left is currently not stackable
				
				if(EnchantmentHelper.getEnchantments(left).size()==0)
				{
					//No enchantments on the left, exiting
					event.setOutput(ItemStack.EMPTY);
					return;
				}
				
				if(EnchantmentHelper.getEnchantmentLevel(this, left) >= 1)
				{
					//Left already has upgraded potentials, set output to empty and return
					event.setOutput(ItemStack.EMPTY);
					return;
				}
				
				//Left is currently not stackable and does not have upgraded potentials
				
				//Repair cost tweaking
				int cost = left.getRepairCost();
				cost = Math.max(0, (cost / 4) - 20);
				
				//Build a copy of the left with upgraded potentials and the modified repair cost
				ItemStack output = left.copy();
				output.setRepairCost(cost);
				output.addEnchantment(this, 1);
				
				//Set the result in the anvil
				event.setOutput(output);
				event.setCost(10);
			}
		}
	}
}
