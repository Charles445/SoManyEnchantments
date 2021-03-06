package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
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

public class Enchantmentadvancedmending extends EnchantmentBase{
	public Enchantmentadvancedmending()
	{
	super(Rarity.VERY_RARE, EnumEnchantmentType.BREAKABLE, EntityEquipmentSlot.values());
    this.setName("advancedmending");
	this.setRegistryName("advancedmending");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AdvancedMending;
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
        return stack.isItemStackDamageable() && super.canApplyAtEnchantingTable(stack);
    }
    
    @SubscribeEvent
    public void onXP(PlayerPickupXpEvent event)
    {
    	EntityPlayer player = event.getEntityPlayer();
    	EntityXPOrb orb = event.getOrb();
    	
    	if(orb.xpValue > 0 && ModConfig.miscellaneous.EnableDoubleXPBug)
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