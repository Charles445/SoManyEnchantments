package com.Shultrea.Rin.Utility_Sector;

import java.util.Collection;
import java.util.UUID;

import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class LivingAttackFixerHandler
{
	private static final UUID CACHED_UUID = UUID.fromString("e6123481-134f-4c54-a535-29c3a241c7a21");
	
	//Currently unused
	
	public LivingAttackFixerHandler()
	{
		
	}

	private PotionEffect potyon = null;
	int level = 0;
	 
	float addedDamage = 0;
	float negativeValue = 0;
    float TotalDamage = 0;
    float damageHolder = 0;
    float damageHolder2 = 1;
	boolean isTrue = false;
	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled=true)
	public void Fix(LivingUpdateEvent event)
	{
		//if(!ModConfig.miscellaneous.weaknessPreventsDamage)
		//	return;
		
		if(!(event.getEntityLiving() instanceof EntityPlayer))
			return;
		
		RemoveAttackDamage(event.getEntityLiving());
		
		if(event.getEntityLiving().isPotionActive(MobEffects.WEAKNESS))
		{
			if(ModConfig.miscellaneous.EnableFixEnchantment)
			{
				Potion potion = MobEffects.WEAKNESS;
			
				potyon = event.getEntityLiving().getActivePotionEffect(potion);
				
				level = potyon.getAmplifier() + 1;
				
				AddAttackDamage(event.getEntityLiving());
			}
		}
		else 
		{
			RemoveAttackDamage(event.getEntityLiving());
		}
	}
	
	private void AddAttackDamage(EntityLivingBase fEntity)
	{
    	//ItemStack weapon = fEntity.getHeldItemMainhand();	
    	//int level = potyon.getAmplifier() + 1;
		IAttributeInstance attackAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
		
		AttributeModifier attackDamage = new AttributeModifier(CACHED_UUID,"attackDamage", level * 4, 0);
		attackAttr.removeModifier(attackDamage);
		attackAttr.applyModifier(attackDamage);
	}
			
	private void RemoveAttackDamage(EntityLivingBase fEntity)
	{
		//ItemStack weapon = fEntity.getHeldItemMainhand();	
		//int level = potyon.getAmplifier() + 1;
		IAttributeInstance attackAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
				
		if(attackAttr.getModifier(CACHED_UUID) == null)
			return;
		
	    AttributeModifier attackDamage = new AttributeModifier(CACHED_UUID,"attackDamage", level * 4, 0);
	    attackAttr.removeModifier(attackDamage);	
	
	}
	
	public float getDamage()
	{
		return damageHolder;
	}
}
