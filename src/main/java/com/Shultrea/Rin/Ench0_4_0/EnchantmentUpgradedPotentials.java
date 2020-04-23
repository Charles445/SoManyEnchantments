package com.Shultrea.Rin.Ench0_4_0;

import java.util.Map;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentUpgradedPotentials extends Enchantment {
	public EnchantmentUpgradedPotentials()
	{
		super(Rarity.RARE, EnumList.NONE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("upgrade");
		this.setRegistryName("upgrade");
		
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
        return somanyenchantments.config.Upgrade && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return somanyenchantments.config.Upgrade;
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
