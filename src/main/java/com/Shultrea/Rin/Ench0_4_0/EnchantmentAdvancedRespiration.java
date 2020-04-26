package com.Shultrea.Rin.Ench0_4_0;

import com.Shultrea.Rin.Enchantments_Sector.Smc_040;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class EnchantmentAdvancedRespiration extends Enchantment
{
	//UNUSED
	
	public EnchantmentAdvancedRespiration()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_HEAD, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD});
	    this.setName("AdvancedRespiration");
		this.setRegistryName("AdvancedRespiration");
	}
	
	@Override
	public int getMaxLevel()
    {
        return 3;
    }
	@Override
    public int getMinEnchantability(int par1)
    {
        return 12 + 8 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
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
	
	public static int decreaseAirSupply(EntityPlayer entity, int air)
	    {
	        int i = EnchantmentHelper.getMaxEnchantmentLevel(Smc_040.AdvancedRespiration, entity);
	        return i > 0 && entity.getRNG().nextInt(i * 4 + 4) > 0 ? air + 1 : air;
	    }
	  
	  @SubscribeEvent(priority=EventPriority.HIGHEST)
	    public void decreaseAir(PlayerTickEvent fEvent){
		  
		  if(fEvent.phase != Phase.END)
			  return;
		  
		  int i = EnchantmentHelper.getMaxEnchantmentLevel(Smc_040.AdvancedRespiration, fEvent.player);
		  if(i <= 0)
			  return;
		  
		  if(fEvent.player.canBreatheUnderwater())
			  return;
		  
		  if(!(fEvent.player.isInWater()))
			  return;
		  
		  fEvent.player.setAir(decreaseAirSupply(fEvent.player,fEvent.player.getAir()));
		  
	  }
	  }

