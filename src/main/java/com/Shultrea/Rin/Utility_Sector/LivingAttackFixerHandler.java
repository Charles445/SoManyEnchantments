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


public class LivingAttackFixerHandler{

	public LivingAttackFixerHandler(){
		
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
	public void Fix(LivingUpdateEvent fEvent){
		
		if(!(fEvent.getEntityLiving() instanceof EntityPlayer))
			return;
		
		RemoveAttackDamage(fEvent.getEntityLiving());
			
		//NBTTagCompound nbt = new NBTTagCompound();
	    //fEvent.getEntityLiving().writeEntityToNBT(nbt);
      
		if(fEvent.getEntityLiving().isPotionActive(MobEffects.WEAKNESS)){
		if(ModConfig.miscellaneous.EnableFixEnchantment){
		Potion potion = MobEffects.WEAKNESS;
		
		potyon = fEvent.getEntityLiving().getActivePotionEffect(potion);
		
		level = potyon.getAmplifier() + 1;
		
		    //if(fEvent.getEntityLiving().getEntityWorld().getTotalWorldTime() % 20 == 0){
		   // nbt.setBoolean("shouldCleave", true);
		    //fEvent.getEntityLiving().readEntityFromNBT(nbt);
		   // }
		    
		AddAttackDamage(fEvent.getEntityLiving());
		
		
		
		
		/** ========================================
		IAttributeInstance attackAttr = attacker.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);	
		
		Collection<AttributeModifier> attr =  attacker.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getModifiers();

		int size = attr.size();
		String operators;
		
		for(int i = 0; i < size; i++){
			
			AttributeModifier operator = attr.iterator().next();
			
			double amount = operator.getAmount();
			double operatingAmount = operator.getOperation();
			
			if(operatingAmount == 0){
			operators = "0";
			}
			else if(operatingAmount == 1){
			operators = "1";
			}
			else if(operatingAmount == 2){
			operators = "2";
			}
			else continue;
			
			switch(operators){
			
			case "0":
			TotalDamage += (float) (amount);
			continue;
			
			case "1":
		    damageHolder += amount;
		    continue;
		    
			case "2":
			damageHolder2 *= (float) ((amount + 1));
			continue;
		}
			
		}
		double baseAttackDamage = attackAttr.getAttributeValue();
		
		TotalDamage += baseAttackDamage;
		damageHolder += 1;
		
		if(damageHolder2 != 1)
		damageHolder = damageHolder * (damageHolder2);
		
		if(damageHolder != 0)
		TotalDamage *= damageHolder;
		
		System.out.println(TotalDamage);
		if(TotalDamage > 0)
			return;
		
		float measure = 0;
		
		if(TotalDamage < 0)
			negativeValue = measure - TotalDamage;
		else if(TotalDamage == 0)
			addedDamage = 1.0f;
		====================================================*/
		
		
		
		/**if((attacker.getActivePotionEffect(weakness) == null)){
			if(attackAttr.getModifier(UUID.fromString("e6123481-134f-4c54-a535-29c3a241c7a21")) != null){
				{
					ItemStack weapon = attacker.getHeldItemMainhand();	
					int level = potyon.getAmplifier() + 1;
					IAttributeInstance attackAttri = attacker.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
							
					if(attackAttr.getModifier(UUID.fromString("e6123481-134f-4c54-a535-29c3a241c7a21")) == null)
						return;
				
					    AttributeModifier attackDamage = new AttributeModifier(UUID.fromString("e6109481-134f-4c54-a535-29c3ae5c7a21"),"attackDamage", level * 4f, 0);
					    attackAttr.removeModifier(attackDamage);	
				}
				}
			potyon = null;
			return;
		}
		potyon = attacker.getActivePotionEffect(weakness);
		
		*/
		//AddAttackDamage(attacker);
		
		//System.out.println("In!");
		}
		}
		else RemoveAttackDamage(fEvent.getEntityLiving());
		//nbt.setBoolean("shouldCleave", false);
		//fEvent.getEntityLiving().readEntityFromNBT(nbt);
	}
	

	
	  private void AddAttackDamage(EntityLivingBase fEntity)
			{
		    	ItemStack weapon = fEntity.getHeldItemMainhand();	
		    	//int level = potyon.getAmplifier() + 1;
				IAttributeInstance attackAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
				
				AttributeModifier attackDamage = new AttributeModifier(UUID.fromString("e6123481-134f-4c54-a535-29c3a241c7a21"),"attackDamage", level * 4, 0);
				attackAttr.removeModifier(attackDamage);
				attackAttr.applyModifier(attackDamage);
			
				  if(attackAttr.getModifier(UUID.fromString("e6123481-134f-4c54-a535-29c3a241c7a21")) != null)
					    return;
			
				
			}
			
			private void RemoveAttackDamage(EntityLivingBase fEntity)
			{
				ItemStack weapon = fEntity.getHeldItemMainhand();	
				//int level = potyon.getAmplifier() + 1;
				IAttributeInstance attackAttr = fEntity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE);
						
				if(attackAttr.getModifier(UUID.fromString("e6123481-134f-4c54-a535-29c3a241c7a21")) == null)
					return;
			
				
				
				    AttributeModifier attackDamage = new AttributeModifier(UUID.fromString("e6123481-134f-4c54-a535-29c3a241c7a21"),"attackDamage", level * 4, 0);
				    attackAttr.removeModifier(attackDamage);	
				
 
}
			public float getDamage(){
				return damageHolder;
			}
			
			}
