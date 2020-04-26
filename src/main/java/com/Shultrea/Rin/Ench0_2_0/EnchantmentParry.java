package com.Shultrea.Rin.Ench0_2_0;
import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;
import com.Shultrea.Rin.Utility_Sector.SMEsounds;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentParry extends EnchantmentBase {
	public EnchantmentParry()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Parry");
		this.setRegistryName("Parry");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.ParryEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return  20 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
    	return this.getMinEnchantability(par1) + 40;
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = false) 
    public void HandleEnchant(LivingAttackEvent e)
    {	
    	if(e.getSource().damageType != "player" && e.getSource().damageType != "mob" && e.getSource().damageType == "arrow")
			return;
    	
    	if(!(e.getEntity() instanceof EntityLivingBase))
    		return;
    	
    	if(!(e.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase victim = e.getEntityLiving();
    	
    	if(victim == null)
    		return;
    	
		ItemStack weaponSword = victim.getHeldItemMainhand();
				
		EntityLivingBase attacker = (EntityLivingBase) e.getSource().getTrueSource();
		
		if(attacker == null)
			return;
		
		if(weaponSword == null)
			return;
				
		if(EnchantmentHelper.getEnchantmentLevel(Smc_020.Parry, weaponSword) <= 0)
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_040.TrueStrike, attacker.getHeldItemMainhand()) > 0 && victim.getRNG().nextInt(100) < 75)
			return;
		
		int levelParry = EnchantmentHelper.getEnchantmentLevel(Smc_020.Parry, weaponSword);
		
		
		if(victim.world.rand.nextInt(100) < 16 + (levelParry * 8) && EnchantmentsUtility.canBlockDamageSourceIgnoreUnblockable(e.getSource(), victim)){
			if(!attacker.world.isRemote)
				EnchantmentsUtility.ImprovedKnockBack(attacker, 0.3F + (0.15f * levelParry), victim.posX - attacker.posX, victim.posZ - attacker.posZ);
		attacker.getEntityWorld().playSound(null, attacker.posX, attacker.posY, attacker.posZ, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.PLAYERS, 0.3f, 3f);
		e.setCanceled(true);
		if(victim instanceof EntityPlayer){
			
			EntityPlayer player = (EntityPlayer) victim;
			player.hurtResistantTime = 15;
			
		}
		
			
		else e.getEntityLiving().hurtResistantTime = 15;
    }
    }
}

