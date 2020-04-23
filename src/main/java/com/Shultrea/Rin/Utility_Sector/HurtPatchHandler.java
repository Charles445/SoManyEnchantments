package com.Shultrea.Rin.Utility_Sector;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HurtPatchHandler {
	//float b = 0;
	int leveLs = 0;
    float OldDamage = 0.0f;
	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled=true)
	public void Patch(LivingHurtEvent fEvent){
		
		fEvent.setCanceled(false);
		
		if(fEvent.getSource().damageType != "player")
			return;
		
		float Counter = fEvent.getAmount();
		
		//Potion weakness = MobEffects.WEAKNESS;
		EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();
		
		if(!(attacker != null))
		return;
		
		if(!(attacker.isPotionActive(MobEffects.WEAKNESS)))
			return;
		
		    float SecondDamage = fEvent.getAmount();
		    
		    NBTTagCompound nbt = new NBTTagCompound();
		    attacker.writeEntityToNBT(nbt);
		    
		    
		    {
		    Potion potion = MobEffects.WEAKNESS;
				
			PotionEffect potyon = null;
				
			potyon = attacker.getActivePotionEffect(potion);
				
			leveLs = potyon.getAmplifier() + 1;
		    	
			ItemStack weapon = attacker.getHeldItemMainhand();
			if(weapon==ItemStack.EMPTY||weapon==null){
			fEvent.setAmount(fEvent.getAmount() - (leveLs * 4.0f));
			return;
			}
		//if(potyon == null)
		//return;
			
			/**  if(SecondDamage != 1.0f && SecondDamage <= 4.0f){	
			    	System.out.println("Beginsssssssssssssss");
				   	
				    fEvent.setAmount(0);
				    
				    nbt.setBoolean("shouldCleave", false);
				    fEvent.getEntityLiving().readEntityFromNBT(nbt);
				   // b = b + 1.0f;
				    return;
			    	  
			}
			    	 else if(nbt.getBoolean("shouldCleave") == true){
			    		 System.out.println("Activating *****");
			    		 if(SecondDamage == 1.0f)
			    		 fEvent.setAmount(1.0f);
			    		 return;
			    	}
				
			     */
			  
			    
			
			 
			//else
			{
			float FinalDamage = EnchantmentsUtility.ExclusiveCalculateDamageForNegativeSwipe(fEvent.getAmount(), -6.0f, attacker);
			
			
			//if(FinalDamage > 1.0f){
			fEvent.setAmount(FinalDamage + 2.0f);
			/**return;
			}
			else if(FinalDamage <= 1.0f){
			if(fEvent.getAmount() != 1.0f){
			OldDamage = fEvent.getAmount();
			fEvent.setAmount(OldDamage);
			}
			else OldDamage = 1.0f;
			
			if(FinalDamage == 1.0f){
				FinalDamage = 0;
				FinalDamage = OldDamage * 0.25f;
				fEvent.setAmount(FinalDamage);
			}
			if(OldDamage == 1.0f){
				fEvent.setAmount(OldDamage);
			}
			*/
			}
		//int lvl = potyon.getAmplifier() + 1;
		}
		
		
		//System.out.println(fEvent.getAmount());
		//System.out.println("Out!");
	//}
}
}
