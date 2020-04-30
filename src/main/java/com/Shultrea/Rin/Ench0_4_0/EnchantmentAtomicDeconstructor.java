package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentAtomicDeconstructor extends EnchantmentBase
{
	//Originally extended EnchantmentRareWeaponBase
	
	public EnchantmentAtomicDeconstructor()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("AtomicDeconstructor");
		this.setRegistryName("AtomicDeconstructor");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.AtomicDeconstructor;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.AtomicDeconstructor;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 16 + 14 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
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