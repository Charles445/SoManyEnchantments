package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentBrutality extends EnchantmentBase{

	public EnchantmentBrutality() {
		super(Rarity.RARE, EnumList.COMBAT_AXE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Brutality");
		this.setRegistryName("Brutality");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.Brutality;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Brutality;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 15 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }
    
    @Override
    public void onEntityDamagedAlt(EntityLivingBase user, Entity target, ItemStack stack, int level)
    {
    	if(!ModConfig.enabled.Brutality)
    		return;
    	
    	if(!(target instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase victim = (EntityLivingBase) target;
    	
    	Iterable<ItemStack> iter = victim.getArmorInventoryList();
  
    	int x = 5;
    	
    	for(ItemStack item : iter){
    		x--;
    	}
    	
    	for(ItemStack item : iter){
    		ItemStack armor = item;
    		armor.damageItem((int) (armor.getMaxDamage() * (0.0025f * x) + EnchantmentsUtility.RANDOM.nextInt(x + 2)) + 1, victim);
    	}
    	
    }
    
	

}
