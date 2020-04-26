package com.Shultrea.Rin.Ench0_3_0;


import java.util.List;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IEnchantmentRune;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentRune_Revival extends EnchantmentBase implements IEnchantmentRune {
	
	
	public EnchantmentRune_Revival()
	{
		super(Rarity.VERY_RARE, EnumList.DAMAGEABLE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND});
		this.setName("Rune_Revival");
		this.setRegistryName("Rune_Revival");	
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.RevivalEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 30 + 30 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 60;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof IEnchantmentRune);  	
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
    	return !(stack.getItem() instanceof ItemArmor) ? super.canApplyAtEnchantingTable(stack) : false;
    }
       
    @SubscribeEvent
    public void TriggeredEvent(PlayerDestroyItemEvent fEvent){
    	
    	int amount = 0;
    	EntityPlayer entity = (EntityPlayer) fEvent.getEntityLiving();
    	
    	ItemStack tool = fEvent.getOriginal();
    	  	
    	int levelR = EnchantmentHelper.getEnchantmentLevel(Smc_030.Rune_Revival, tool);
    	if(levelR <= 0)
    		return;
    	
    	if(levelR >= 3)
    	  levelR = 2;
    	
    	int durability = tool.getMaxDamage();
    	
    	float extraChance = durability > 1250 ? 0 : durability > 750 ? 4 : durability > 200 ? 6 : durability > 80 ? 8 : durability <= 80 ? 10 : 0;
    	extraChance = extraChance / 100f;
 	
    	boolean test = EnchantmentsUtility.RANDOM.nextDouble() < (0.15f + (levelR * 0.15f)) + extraChance;
    	
    	//System.out.println(extraChance + " - Extra Chance");
    	
    	if(test){
    		ItemStack newTool = tool.copy();
    	   	newTool.setItemDamage((int) (newTool.getItemDamage() - (newTool.getItemDamage() * (0.5f * levelR))));
    		List<ItemStack> list = entity.inventory.mainInventory;
    		//boolean flag = entity.inventory.addItemStackToInventory(newTool);
    		int slot = entity.inventory.currentItem;
    		//if(!flag){
    		/**
    		for (int i = 0; i < list.size(); ++i)
            {
                if (((ItemStack)list.get(i)).isEmpty() && i != slot)
                {
                    entity.inventory.mainInventory.set(i, newTool);
                }
            }
    		*/
    		
    		boolean flag = EnchantmentsUtility.addItemStackToInventoryWithoutHolding(newTool, entity.inventory);
    		
    		if(!flag){
    			EntityItem entityItem = entity.entityDropItem(newTool, 1.3f);	    		
    		    entityItem.setOwner(entity.getName());
    		    entityItem.setNoPickupDelay();
    		    entityItem.setEntityInvulnerable(true);  			
    		}
    	}     
    }
    
    @Override
    public String getTranslatedName(int level)
    {
        String s = I18n.translateToLocal(this.getName());
        s = TextFormatting.GREEN + s;       
        return level == 1 && this.getMaxLevel() == 1 ? s : s + " " + I18n.translateToLocal("enchantment.level." + level);
    }
    
}

    
//}

  