package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentAdept extends Enchantment{
//Popular demand of xp enchantment
	public EnchantmentAdept(){ 
		super(Rarity.RARE, EnumList.COMBAT_WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Adept");
		this.setRegistryName("Adept");
	}
	
	@Override
	public int getMaxLevel(){
		return 3;
	}

	@Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 26 + (par1 - 1) * 12;
    }
	
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Enchantments.LOOTING && fTest != Smc_030.AdvancedLooting;
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return ModConfig.enabled.Adept && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return ModConfig.enabled.Adept;
    }
    
    @Override
    public boolean isTreasureEnchantment() {
    	return true;
    }
    
    @Override
    public boolean canApply(ItemStack fTest)
    {
    	return fTest.getItem() instanceof ItemAxe ? true : super.canApply(fTest);
    }
    
    @SubscribeEvent
    public void onDeath(LivingExperienceDropEvent fEvent){

    	EntityPlayer player = fEvent.getAttackingPlayer();
    	
    	if(player == null)
    		return;
    	
    	ItemStack stack = player.getHeldItemMainhand();
    	
    	if(stack == null || stack.isEmpty()){
    		
    	stack = player.getHeldItemOffhand();
    	
    	if(stack == null || stack.isEmpty())
    		return;
    	
    	}
    	int lvl = EnchantmentHelper.getEnchantmentLevel(this, stack);
    	
    	if(lvl <= 0)
    		return;
    	

    	if(fEvent.getEntityLiving() != null && !fEvent.getEntityLiving().isNonBoss())
    		fEvent.setDroppedExperience(2 + lvl + (int)(fEvent.getOriginalExperience() * (0.75f + 0.5f * lvl)));
    	
    	else fEvent.setDroppedExperience(2 + lvl + (int)(fEvent.getOriginalExperience() * (1.05f + 0.15f * lvl)));
    	//System.out.println(fEvent.getDroppedExperience() + " - Altered EXP");
    	
    	//stack.damageItem(1, player);
    	//System.out.println(fEvent.getOriginalExperience() + " - Orig EXP");
    	//System.out.println(fEvent.getDroppedExperience() + " - Orig EXP");
    }
}
