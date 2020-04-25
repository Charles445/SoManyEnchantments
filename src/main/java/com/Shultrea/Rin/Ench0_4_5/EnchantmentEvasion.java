package com.Shultrea.Rin.Ench0_4_5;

import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentEvasion extends Enchantment
{
	public EnchantmentEvasion()
	{
		super(Rarity.RARE, EnumEnchantmentType.ARMOR_LEGS, new EntityEquipmentSlot[]{EntityEquipmentSlot.LEGS});
		this.setName("Evasion");
		this.setRegistryName("Evasion");
	}
		
	@Override
	public int getMaxLevel()
	{
		return 3;
	}
		
	@Override
    public int getMinEnchantability(int par1)
    {
        return 25 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 40;
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
        return ModConfig.enabled.Evasion && stack.getItem().canApplyAtEnchantingTable(stack, this);
    }
    
    @Override
    public boolean isAllowedOnBooks()
    {
        return ModConfig.enabled.Evasion;
    }
	    
    @SubscribeEvent(priority = EventPriority.LOW, receiveCanceled = false) 
    public void HandleEnchant(LivingAttackEvent fEvent)
    {	
    	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob" && fEvent.getSource().damageType == "arrow")
			return;
    	
    	if(!(fEvent.getEntity() instanceof EntityLivingBase))
    		return;
    	
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase victim = fEvent.getEntityLiving();
    	
    	if(victim == null)
    		return;
    		
		EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();
		
		if(attacker == null)
			return;

		if(EnchantmentHelper.getEnchantmentLevel(Smc_040.TrueStrike, attacker.getHeldItemMainhand()) > 0) {
			if(EnchantmentsUtility.RANDOM.nextInt(100) < 75)
				return;
		
		}
		int level = EnchantmentHelper.getMaxEnchantmentLevel(this, victim);
				
		if(level <= 0)
			return;
			
		double randX = 0.65 + victim.getRNG().nextDouble() * 0.25f;
		randX = victim.getRNG().nextBoolean() ? randX * -1 : randX;
		
		double randZ = 0.65 + victim.getRNG().nextDouble() * 0.25f;
		randZ = victim.getRNG().nextBoolean() ? randZ * -1 : randZ;
		
		if(fEvent.getEntityLiving().world.rand.nextInt(100) < 5 + (level * 15)){
			if(!attacker.world.isRemote && ModConfig.miscellaneous.EvasionKnockback)
				EnchantmentsUtility.ImprovedKnockBack(victim, 0.7f, (attacker.posX - victim.posX) * randX, (attacker.posZ - victim.posZ) * randZ);
			victim.getEntityWorld().playSound(null, victim.posX, victim.posY, victim.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.3f, victim.getRNG().nextFloat() * 2.25f + 0.75f);
			fEvent.setCanceled(true);
		
		victim.hurtResistantTime = 15 + 5 * level;
    }
    }
}