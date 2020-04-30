package com.Shultrea.Rin.Ench0_4_0;

import java.util.UUID;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Interfaces.IEnchantmentProtection;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class EnchantmentStrengthenedVitality extends EnchantmentBase{
	public EnchantmentStrengthenedVitality()
	{
	super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[]{EntityEquipmentSlot.CHEST});
    this.setName("strengthenedvitality");
	this.setRegistryName("strengthenedvitality");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.StrengthenedVitality;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.StrengthenedVitality;
    }
	@Override
    public int getMinEnchantability(int par1)
    {
        return 25 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 75;
    }
	
    @Override
    public boolean canApplyTogether(Enchantment e)
    {
    	return super.canApplyTogether(e) && !(e instanceof IEnchantmentProtection) && !(e instanceof EnchantmentProtection);
    }
		  
	@SubscribeEvent(priority=EventPriority.HIGHEST)
	public void updateHealth(PlayerTickEvent fEvent){
		  
		if(fEvent.phase != Phase.END)
			return;
		  
		int i = EnchantmentHelper.getMaxEnchantmentLevel(Smc_040.strengthenedvitality, fEvent.player);
		if(i <= 0){
			RemoveHealth(fEvent.player);
			return;
		}
		AddHealth(fEvent.player);
		
		
	}
    private void AddHealth(EntityPlayer fEntity)
    {    	
    	int level = EnchantmentHelper.getMaxEnchantmentLevel(Smc_040.strengthenedvitality, fEntity);
    	IAttributeInstance speedAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);

    	AttributeModifier modSpeed = new AttributeModifier(UUID.fromString("e681-134f-4c54-a535-29c3ae5c7a21"),"StrengthenedHealthBoost", 0.1D * level, 2);
    	speedAttr.removeModifier(modSpeed);
    	speedAttr.applyModifier(modSpeed);
    	
    	if(speedAttr.getModifier(UUID.fromString("e681-134f-4c54-a535-29c3ae5c7a21")) != null)
    		return;
    	
	}
			
    private void RemoveHealth(EntityLivingBase fEntity)
	{				
    	int level = EnchantmentHelper.getMaxEnchantmentLevel(Smc_040.strengthenedvitality, fEntity);
		IAttributeInstance speedAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH);
						
		if(speedAttr.getModifier(UUID.fromString("e681-134f-4c54-a535-29c3ae5c7a21")) == null)
			return;
					
		AttributeModifier modSpeed = new AttributeModifier(UUID.fromString("e681-134f-4c54-a535-29c3ae5c7a21"),"StrengthenedHealthBoost", 0.2D * level, 2);
		speedAttr.removeModifier(modSpeed);
		
	}
	  
}
