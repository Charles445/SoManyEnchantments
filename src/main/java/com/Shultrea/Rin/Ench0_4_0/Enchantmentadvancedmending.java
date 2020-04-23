package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Enchantmentadvancedmending extends Enchantment{
	public Enchantmentadvancedmending()
	{
	super(Rarity.VERY_RARE, EnumEnchantmentType.BREAKABLE, EntityEquipmentSlot.values());
    this.setName("advancedmending");
	this.setRegistryName("advancedmending");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 1;
    }
	
	@Override
	public boolean isTreasureEnchantment(){
		return true;
	}
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 40 * par1;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 65;
    }
	
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return fTest == Enchantments.MENDING ? false : super.canApplyTogether(fTest);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return somanyenchantments.config.AdvancedMending && stack.isItemStackDamageable() && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return somanyenchantments.config.AdvancedMending;
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return fTest.getItem() instanceof Item ? super.canApply(fTest) : false;
    }
    @SubscribeEvent
    public void onXP(PlayerPickupXpEvent fEvent){
    	
    	 if(EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING,fEvent.getEntityPlayer().getHeldItemMainhand()) > 0)
    		 return;
    	 
    	 if(EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING,fEvent.getEntityPlayer().getHeldItemOffhand()) > 0)
    		 return;
    	 	 
    	 ItemStack itemstack = EnchantmentHelper.getEnchantedItem(this, fEvent.getEntityPlayer());
    	 
    	 System.out.println(fEvent.getOrb().xpValue + " - Picked Up XP");
    	 
    	 //if(EnchantmentHelper.getEnchantmentLevel(this, itemstack) <= 0)
    		// return;
    	 
    	  if (!itemstack.isEmpty() && itemstack.isItemDamaged())
          {
              int i = Math.min(this.xpToDurability(fEvent.getOrb().xpValue), itemstack.getItemDamage());
              fEvent.getOrb().xpValue -= i / 2;
              itemstack.setItemDamage(itemstack.getItemDamage() - i);
          }

          if (fEvent.getOrb().xpValue > 0)
          {
             fEvent.getEntityPlayer().addExperience(fEvent.getOrb().xpValue);
    }
}
 
   

    private int xpToDurability(int xp)
    {
        return xp * 3;
    }
    
}