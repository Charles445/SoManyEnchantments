package com.Shultrea.Rin.Ench0_4_5;

import java.util.List;
import java.util.Random;

import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IEnchantmentGreaterCurse;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.EnchantmentLister;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class EnchantmentPandora extends Enchantment implements IEnchantmentGreaterCurse {

	//An enchantment that is hidden until a certain time where it will reveal itself.
	//While hidden, it infects the inventory of players with a bunch of curse enchantments, not including itself.
	public EnchantmentPandora()
	{
		super(Rarity.VERY_RARE, EnumList.ALL, EntityEquipmentSlot.values());
		this.setName("Pandora");
		this.setRegistryName("Pandora");
		
	}
			
	@Override
	public int getMaxLevel()
	{
		return 1;
	}
			
	@Override
	public int getMinEnchantability(int par1)
	{
		return 100 + 50 * (par1 - 1);
    }
	
	@Override
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 100;
	}
		    
	@Override
	public boolean canApplyTogether(Enchantment fTest)
	{
		return super.canApplyTogether(fTest);				
	}
		    
	@Override
	public boolean canApply(ItemStack fTest)
	{
		return super.canApply(fTest);
	}
		    	
	@Override
	public boolean isTreasureEnchantment()
	{
		return true;
	}
	
	@Override
	public boolean isCurse()
	{
		return true;
	
	}
	
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return somanyenchantments.config.Pandora && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return somanyenchantments.config.Pandora;
    }
		 
	@SubscribeEvent
	public void HandleEnchant(PlayerTickEvent e)
    {
		if(e.phase == Phase.START)
			return;

		if(e.player == null || e.side == Side.CLIENT)
			return;
		
		EntityPlayer player = e.player;
		
		InventoryPlayer inv = player.inventory;	

		Random randy = new Random();
		
		if(randy.nextInt(1000) < 1)
		for(int x = 0; x < inv.mainInventory.size(); x++) {
			
			ItemStack stack = inv.mainInventory.get(x);
					
			if(EnchantmentHelper.getEnchantmentLevel(this, stack) <= 0)
				continue;
			
			for(int y = 0; y < inv.mainInventory.size(); y++) {
							
				if(randy.nextInt(1200) < 1)
					continue;
				
				ItemStack curse = inv.mainInventory.get(y);
				
				if(curse.isEmpty())
					continue;
				
				List<Enchantment> list = EnchantmentLister.CURSE;
				
				int random = player.getRNG().nextInt(list.size());
				Enchantment ench = list.get(random);
				int randLevel = player.getRNG().nextInt(ench.getMaxLevel());
				
				if(!ench.canApply(curse))
					continue;
		
				NBTTagList nbt = curse.getEnchantmentTagList();
				boolean isCompatible = true;
				for(int z = 0; z < nbt.tagCount(); z++) {
					NBTTagCompound tag = nbt.getCompoundTagAt(x);
					int enchId = tag.getShort("id");
					int currEnchLevel = tag.getShort("lvl");
					Enchantment enchantment = Enchantment.getEnchantmentByID(enchId);
					if(enchantment == null)
						continue;
					if(enchantment == this) {
						if(randy.nextInt(3) < 2)
						tag.setShort("lvl", (short) (currEnchLevel + 1));
						
						if(tag.getShort("lvl") > 5) {
							if(randy.nextInt(3) < 2)
							nbt.removeTag(x);
							return;
						}
						
						continue;
					}
					if(enchantment.isCompatibleWith(ench))
						continue;
					
					else isCompatible = false;
					break;
					
				}
				
				if((EnchantmentHelper.getEnchantmentLevel(ench, curse) <= 0) && isCompatible)
					curse.addEnchantment(list.get(random), randLevel + 1);
				
			}
	}
    
}
	@Override
	public String getTranslatedName(int level)
	{
		String s = I18n.translateToLocal(this.getName());
	    s = TextFormatting.DARK_RED + s;       
	    
	    if(Minecraft.getMinecraft().player != null)
	    if(Minecraft.getMinecraft().player.isCreative())
	    	return s;
	    
	    if(level >= 5)
	    return s;
	    
	    return "";
	   
	}
		    
		  
	}