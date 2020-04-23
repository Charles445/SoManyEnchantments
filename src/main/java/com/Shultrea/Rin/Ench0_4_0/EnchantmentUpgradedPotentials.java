package com.Shultrea.Rin.Ench0_4_0;

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
    public void onAnvilAttach(AnvilUpdateEvent fEvent){
    	int level = EnchantmentsUtility.getInput2EnchLevel(fEvent.getRight(), Smc_040.upgrade);
    	//System.out.println("level: " + level);
    	if(level <= 0)
    		return;
    	
    	//if(fEvent.getOutput() == ItemStack.EMPTY)
    		//return;
    	
    	//if(fEvent.getResult() == Event.Result.DENY || fEvent.isCanceled())
    		//return
    	
    	ItemStack weapon = fEvent.getLeft();
    	
    	int decision = EnchantmentHelper.getEnchantmentLevel(Smc_040.upgrade, weapon);
    	
    	if(decision > 0){
    		fEvent.setCanceled(true);
    		return;
    	}
    	
    	int estimate = 0;
    	
    	int cost = weapon.getRepairCost();
    	
    	estimate = cost / 4 - 20;
    	
    	if(estimate < 0)
    		estimate = 0;

    	//weapon.setRepairCost(estimate);
    
    	ItemStack finalOutput = weapon.copy();
    	finalOutput.setRepairCost(estimate);

    	fEvent.setCost(10);
    	
    	finalOutput.addEnchantment(Smc_040.upgrade, 1);
    
    	fEvent.setOutput(finalOutput);
    	//System.out.println("Cost: " + fEvent.getCost());	
    }
}