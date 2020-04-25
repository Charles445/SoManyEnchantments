package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentRareWeaponBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentAtomicDeconstructor extends EnchantmentRareWeaponBase {
	public EnchantmentAtomicDeconstructor()
	{
    this.setName("AtomicDeconstructor");
	this.setRegistryName("AtomicDeconstructor");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 2;
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
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return ModConfig.enabled.AtomicDeconstructor && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return ModConfig.enabled.AtomicDeconstructor;
    }

    @SubscribeEvent
    public void HandleEnchant(LivingAttackEvent fEvent)
    {
    	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
			return;
    		
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(attacker == null)
    		return;
    	
    	ItemStack dmgSource = ((EntityLivingBase) fEvent.getSource().getTrueSource()).getHeldItemMainhand();
		if(dmgSource == null)
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_040.AtomicDeconstructor, dmgSource) <= 0)
			return;
		
		int levelAtomicDeconstructor = EnchantmentHelper.getEnchantmentLevel(Smc_040.AtomicDeconstructor, dmgSource);
		
		if(fEvent.getEntityLiving().getEntityWorld().rand.nextInt(3000) < (levelAtomicDeconstructor * 3))
		{
		float DeadHP = fEvent.getEntityLiving().getMaxHealth();
		fEvent.getEntityLiving().hurtResistantTime = 0;
		
		if(!(fEvent.getEntityLiving() instanceof EntityPlayer)){
		fEvent.getEntityLiving().attackEntityFrom(somanyenchantments.Deconstruct, DeadHP * 100f);
		}
		
		}
    }
}