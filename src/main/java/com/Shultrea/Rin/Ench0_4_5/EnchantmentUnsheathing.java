package com.Shultrea.Rin.Ench0_4_5;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentUnsheathing extends EnchantmentBase {
	public EnchantmentUnsheathing()
	{
		super(Rarity.VERY_RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Unsheathing");
		this.setRegistryName("Unsheathing");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.Unsheathing;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Unsheathing;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 30 + (par1 - 1) * 15;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }
 
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onAttack(LivingAttackEvent e) {
    	if(!e.isCanceled() && e.getAmount() > 0) {
    		if(e.getSource().getTrueSource() != null && e.getEntityLiving() != null && e.getEntityLiving() instanceof EntityPlayer) {
    			EntityPlayer victim = (EntityPlayer) e.getEntityLiving();
    			InventoryPlayer inv = victim.inventory;	
    			for(int x = 0; x < inv.getHotbarSize(); x++) {
    				
    				ItemStack stack = inv.getStackInSlot(x);
    				
    				if(EnchantmentHelper.getEnchantmentLevel(this, stack) <= 0)
    					continue;
    				
    				if(EnchantmentsUtility.RANDOM.nextInt(2) <= -1 + EnchantmentHelper.getEnchantmentLevel(this, stack))
    					continue;
    				
    				if(inv.getStackInSlot(inv.currentItem) == stack)
    					continue;
    				
    				ItemStack s = inv.getStackInSlot(inv.currentItem);
    				
    				inv.setInventorySlotContents(inv.currentItem, stack);
    				inv.setInventorySlotContents(x, s);			
    				stack.setAnimationsToGo(0);
    			}
    				
    		}
    	}
    }
}