package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
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
    public void onXP(PlayerPickupXpEvent event)
    {
    	EntityPlayer player = event.getEntityPlayer();
    	EntityXPOrb orb = event.getOrb();
    	
    	//TODO config for double XP glitch
    	if(orb.xpValue > 0)
    	{
    		player.addExperience(orb.xpValue);
    	}
    	
		//Get a random item on the player that has advanced mending
		ItemStack itemstack = EnchantmentHelper.getEnchantedItem(this, player);

		//Attempt to repair it using some of the XP from the orb itself
		if(!itemstack.isEmpty() && itemstack.isItemDamaged())
		{
			int value = Math.min(orb.xpValue * 3, itemstack.getItemDamage());
			itemstack.setItemDamage(itemstack.getItemDamage() - value);

			orb.xpValue -= value/2;
			//There is a chance that the orb.xpValue has become negative (for example, an orb.xpValue of 2 can become -1)
			if(orb.xpValue < 0)
				orb.xpValue = 0;
			
		}
    }
 
   

    private int xpToDurability(int xp)
    {
        return xp * 3;
    }
    
}