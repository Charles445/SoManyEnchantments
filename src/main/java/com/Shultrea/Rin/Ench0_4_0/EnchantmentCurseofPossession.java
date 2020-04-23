package com.Shultrea.Rin.Ench0_4_0;

import java.util.List;

import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Interfaces.IEnchantmentCurse;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.IInteractionObject;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class EnchantmentCurseofPossession extends Enchantment implements IEnchantmentCurse {
	
	public static int interval;
	
	public EnchantmentCurseofPossession()
	{
	super(Rarity.VERY_RARE, EnumEnchantmentType.ALL, EntityEquipmentSlot.values());
    this.setName("CurseofPossession");
	this.setRegistryName("CurseofPossession");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 1;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 25 * par1;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 25;
    }
	
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof EnchantmentCurseofDecay);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem().canApplyAtEnchantingTable(stack, this) && somanyenchantments.config.CurseofPossession;
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return false;
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return fTest.getItem() instanceof Item ? super.canApply(fTest) : false;
    }
    
	@Override
	public boolean isCurse(){
		return true;
	}
	
	@Override
	public boolean isTreasureEnchantment(){
		return true;
	}
	  
	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void toss(ItemTossEvent fEvent){
		EntityItem item = fEvent.getEntityItem();
		 
		ItemStack theThrown = item.getItem().copy();
		  
		int l = EnchantmentHelper.getEnchantmentLevel(this, theThrown);
		if(l <= 0){
	    return;
		}
		 
		EntityPlayer player = fEvent.getPlayer();
		 
		if(!player.isCreative()){
			boolean flag = player.inventory.addItemStackToInventory(theThrown);
		
			if(!flag){
				EntityItem entityItem = player.entityDropItem(theThrown, 1.3f);	    		
				entityItem.setOwner(player.getName());
				entityItem.setNoPickupDelay();
				entityItem.setEntityInvulnerable(true);  			
		}
		
		fEvent.setCanceled(true);
		}
}
	
	@SubscribeEvent
	public void onExist(WorldTickEvent e){
		
		if(e.phase == Phase.START)
			return;
		
		
		if(interval > 20) {
			
		List<Entity> list = e.world.loadedEntityList;
		
		//0.4.1
		if(list.isEmpty() || list == null)
			return;
		
		for(int x = 0; x < list.size(); x++){
			
			if(list.get(x) instanceof EntityItem){
				EntityItem item = (EntityItem) list.get(x);
				ItemStack stack = item.getItem();
				EntityPlayer thrower;
				
				if(item.ticksExisted <= 3)
					continue;
				
				//TODO Replace checker with Config
				if(Smc_040.CurseofDecay != null && EnchantmentHelper.getEnchantmentLevel(Smc_040.CurseofDecay, stack) > 0){
					item.lifespan = 40;
					item.setPickupDelay(10);
				}
						
				if(EnchantmentHelper.getEnchantmentLevel(this, stack) > 0){
					//0.4.1
					if(EnchantmentHelper.getEnchantmentLevel(Smc_040.CurseofDecay, stack) <= 0)
					item.setNoDespawn();
					
					if(e.world.getClosestPlayerToEntity(item, 32) == null)
						continue;
					thrower = e.world.getClosestPlayerToEntity(item, 32);
					if(thrower != null)
					if(!thrower.isCreative()){
					boolean flag = thrower.addItemStackToInventory(stack);
					
					if(flag)
						list.remove(x);
				}
				}
			}
	}
	}
		else interval++;
	}
	/*
	@SubscribeEvent
	public void onChest(PlayerTickEvent e){
		if(e.phase == Phase.START || e.side == Side.CLIENT)
			return;
		
		if(e.player == null)
			return;
		
		InventoryPlayer inv = e.player.inventory;
		Container c = e.player.openContainer;
		
		boolean flag = c instanceof IInteractionObject;
		boolean flag2 = 
		
		if(!(c instanceof IInteractionObject))
			return;
		
		//System.out.println(c);
	
		
		if(c != null && !c.getInventory().isEmpty()) {
			NonNullList<ItemStack> list = c.getInventory();
			
			for(int x = 0; x < list.size(); x++) {
			
				ItemStack stack = list.get(x);	
				int level = EnchantmentHelper.getEnchantmentLevel(this, stack);
				if(level <= 0)
					continue;
				boolean flag = inv.addItemStackToInventory(stack);
				
				if(!flag) {
					
				}
				else stack.setCount(0);
				
			
		}
		}
		//Inventory inv2;
		//if(!inv.getItemStack().isEmpty()) {
		//	inv.getItemStack().is
		//}
	
	}
	*/
	/*
	@SubscribeEvent
	public void onInteract(PlayerContainerEvent.Close e) {
		if(e.getEntityPlayer().world.isRemote)
			return;
		
		if(e.getEntityPlayer() == null)
			return;
		
		if(e.getContainer() == e.getEntityPlayer().inventoryContainer)
			return;
		
		if(e.getContainer().getInventory().isEmpty())
			return;
		
		if(e.getContainer() != null && e.getContainer() instanceof Container) {
			InventoryPlayer inv = e.getEntityPlayer().inventory;
			NonNullList<ItemStack> list = e.getContainer().inventoryItemStacks;
			
			for(int x = 0; x < list.size(); x++) {
				
				ItemStack stack = list.get(x);	
				System.out.println(e.getContainer() + " " + stack);
				
				int level = EnchantmentHelper.getEnchantmentLevel(this, stack);
				if(level <= 0)
					continue;
				
				boolean flag = inv.addItemStackToInventory(stack);
				
				if(!flag) {
					if(inv.offHandInventory.isEmpty()) {						
						inv.offHandInventory.add(stack);
						stack.setCount(0);
					}
					/*
					else
					for(int d = 0; d < inv.mainInventory.size(); d++) {
						ItemStack r = inv.mainInventory.get(d);
						if(!r.isEmpty()) {
							Slot slot = e.getContainer().getSlot(x);
							boolean test = e.getContainer().canAddItemToSlot(slot, stack, false);
							if(test) {
								
							}
						}
						
					}
					*/
				//}
				//else stack.setCount(0);
				
			
		//}
			
		//}
		
	//}
	
	}

