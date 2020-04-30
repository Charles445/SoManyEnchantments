package com.Shultrea.Rin.Ench0_4_5;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IDamageMultiplier;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentInstability extends EnchantmentBase implements IDamageMultiplier{
	public EnchantmentInstability()
	{
		super(Rarity.VERY_RARE, EnumList.ALL_TOOL, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Instability");
		this.setRegistryName("Instability");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.Instability;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Instability;
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
    
    @Override
    public boolean canApplyTogether(Enchantment fTest)
    { 	
    	return super.canApplyTogether(fTest) && !(fTest instanceof EnchantmentDurability);
    }
    
    @Override
    public boolean isCurse() {
    	return true;
    }
 
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onAttack(LivingDamageEvent e) {
    	if(!e.isCanceled() && e.getAmount() > 0) {
    		if(e.getSource().getTrueSource() != null && e.getSource().damageType.equals("player")) {	
    		EntityLivingBase attacker = (EntityLivingBase) e.getSource().getTrueSource();
    		
    		if(attacker.getHeldItemMainhand().isEmpty())
    			return;
    		
    		ItemStack stack = attacker.getHeldItemMainhand();
    		
    		int level = EnchantmentHelper.getEnchantmentLevel(this, stack);
    		
    		if(level <= 0)
    			return;
    		
    		float percentage = ((float)stack.getItemDamage() / (float)stack.getMaxDamage());

    		//System.out.println(percentage + " percentage");

    		percentage = 1 + percentage * (0.75f * level);
    		
    		e.setAmount(e.getAmount() * percentage);
    		
    		stack.damageItem((int) (e.getAmount() * attacker.getRNG().nextFloat() / (8 - level)) + 1, attacker);
    	}
    }
  }
}