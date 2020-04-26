package com.Shultrea.Rin.Ench0_3_0;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Interfaces.IPotionDebuffer;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentDesolator extends EnchantmentBase implements IPotionDebuffer{
	public EnchantmentDesolator()
	{
		super(Rarity.RARE, EnumList.COMBAT_AXE, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName("Desolator");
		this.setRegistryName("Desolator");
		
	}

	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.DesolatorEnable;
	}
	
	@Override
	public int getMaxLevel()
	{
		return 4;
	}
		
	@Override
	public int getMinEnchantability(int par1)
	{
		return 17 + 8 * (par1 - 1);
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 30;
	}
	    
	@Override
	public boolean canApplyTogether(Enchantment fTest)
	{
		return super.canApplyTogether(fTest) && !(fTest instanceof IPotionDebuffer);
	}
	   
	@SubscribeEvent(priority = EventPriority.LOW)
	public void HandleEnchant(LivingHurtEvent fEvent)
	{
		if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
			return;
						
		if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
			return;
						
		EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
		ItemStack weapon = attacker.getHeldItemMainhand();
						
		if(weapon == null)
			return;
		
		int level = EnchantmentHelper.getEnchantmentLevel(Smc_030.Desolator, weapon);
		
		if(EnchantmentHelper.getEnchantmentLevel(Smc_030.Desolator, weapon) <= 0)
			return;
		
			if(fEvent.getEntity().world.rand.nextInt(100) < 8 * level)
		{
			if(level >= 3){
			((EntityLivingBase)fEvent.getEntityLiving()).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 140, level - 3));
			}
			((EntityLivingBase)fEvent.getEntityLiving()).addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 140, 0 - level));
			if(!(fEvent.getEntityLiving().getActivePotionEffect(MobEffects.RESISTANCE) != null)){
			    fEvent.setAmount(fEvent.getAmount() * 1.2f * level);
			}
		
		}
	}
}