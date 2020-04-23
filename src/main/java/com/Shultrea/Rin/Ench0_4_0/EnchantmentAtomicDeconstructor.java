package com.Shultrea.Rin.Ench0_4_0;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentRareWeaponBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;



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
        return somanyenchantments.config.AtomicDeconstructor && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return somanyenchantments.config.AtomicDeconstructor;
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