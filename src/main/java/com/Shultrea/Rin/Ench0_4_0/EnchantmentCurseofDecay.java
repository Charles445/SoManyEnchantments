package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IEnchantmentCurse;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentCurseofDecay extends EnchantmentBase implements IEnchantmentCurse {
	//If the item is dropped, it will decay almost instantly without delay unlike curse of vanishing.
	public EnchantmentCurseofDecay() {
		super(Rarity.VERY_RARE, EnumList.ALL, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		// TODO Auto-generated constructor stub
		this.setRegistryName("CurseofDecay");
		this.setName("CurseofDecay");
	}
	
	@Override
	public boolean isEnabled()
	{
		return ModConfig.enabled.CurseofDecay;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 1;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 45;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 45;
    }
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && !(fTest instanceof EnchantmentCurseofPossession);	
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return super.canApply(fTest);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return stack.getItem().canApplyAtEnchantingTable(stack, this) && ModConfig.enabled.CurseofDecay;
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return true;
    }
    
    @Override
    public boolean isCurse(){
    	return true;
    }
    
    @Override
	public boolean isTreasureEnchantment(){
		return true;
	}
    
    @SubscribeEvent
    public void onThrow(ItemTossEvent fEvent){
    	
    	if(fEvent.getEntityItem() != null)
    	
    	if(EnchantmentHelper.getEnchantmentLevel(Smc_040.CurseofDecay, fEvent.getEntityItem().getItem()) > 0){
    	fEvent.getEntityItem().lifespan = 80;
    	fEvent.getEntityItem().setPickupDelay(10);
    	}
    }

}
