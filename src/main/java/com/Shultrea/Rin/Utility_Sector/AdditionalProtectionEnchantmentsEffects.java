package com.Shultrea.Rin.Utility_Sector;

import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AdditionalProtectionEnchantmentsEffects {

	  @SubscribeEvent(priority=EventPriority.LOW, receiveCanceled=true)
	    public void protectionOnly(LivingHurtEvent fEvent){
		  	if(!ModConfig.miscellaneous.extraProtectionEffects)
		  		return;
	    	//System.out.println("EVENT EEEEEEEEEEEEEEE");
	    	if(!(ModConfig.enabled.AdvancedProtection))
	    		return;
		  
	    	if(fEvent.getSource().damageType.equals("null"))
				return;
	    	
	    	int modifier = EnchantmentsUtility.CalcModgetTotalLevel(4.75f, Smc_030.AdvancedProtection, fEvent.getEntityLiving());
	    	
	    	float damage = EnchantmentsUtility.getDamageAfterMagicAbsorb(fEvent.getAmount(), modifier);
	    	
	    	fEvent.setAmount(damage);
	    }
	    
	    @SubscribeEvent(priority=EventPriority.LOW, receiveCanceled=true)
	    public void projprotection(LivingHurtEvent fEvent){
		  	if(!ModConfig.miscellaneous.extraProtectionEffects)
		  		return;
	    	//System.out.println("EVENT EEEEEEEEEEEEEEE");
	    	if(!(ModConfig.enabled.AdvancedProjectileProtection))
	    		return;	
	    	
	    	if(!(fEvent.getSource().isProjectile()))
				return;
	    	
	    	int modifier = EnchantmentsUtility.CalcModgetTotalLevel(7.5f, Smc_030.AdvancedProjectileProtection, fEvent.getEntityLiving());
	    	
	    	float damage = EnchantmentsUtility.getDamageAfterMagicAbsorb(fEvent.getAmount(), modifier);
	    	
	    	fEvent.setAmount(damage);
	    }
	    
	    @SubscribeEvent(priority=EventPriority.LOW, receiveCanceled=true)
	    public void fireprotection(LivingDamageEvent fEvent){
		  	if(!ModConfig.miscellaneous.extraProtectionEffects)
		  		return;
	    	//System.out.println("EVENT EEEEEEEEEEEEEEE");
	    	if(!(ModConfig.enabled.AdvancedFireProtection))
	    		return;	
	    	
	    	if(!(fEvent.getSource().isFireDamage()))
				return;
	    	
	    	int modifier = EnchantmentsUtility.CalcModgetTotalLevel(7.5f, Smc_030.AdvancedFireProtection, fEvent.getEntityLiving());
	    	
	    	float damage = EnchantmentsUtility.getDamageAfterMagicAbsorb(fEvent.getAmount(), modifier);
	    	
	    	fEvent.setAmount(damage);
	    }
	    
	    @SubscribeEvent(priority=EventPriority.LOW, receiveCanceled=true)
	    public void blastprotection(LivingHurtEvent fEvent){
		  	if(!ModConfig.miscellaneous.extraProtectionEffects)
		  		return;
	    	//System.out.println("EVENT EEEEEEEEEEEEEEE");
	    	if(!(ModConfig.enabled.AdvancedBlastProtection))
	    		return;
	    		
	    	if(!(fEvent.getSource().isExplosion()))
				return;
	    	
	    	int modifier = EnchantmentsUtility.CalcModgetTotalLevel(7.5f, Smc_030.AdvancedBlastProtection, fEvent.getEntityLiving());
	    	
	    	float damage = EnchantmentsUtility.getDamageAfterMagicAbsorb(fEvent.getAmount(), modifier);
	    	
	    	fEvent.setAmount(damage);
	    }
	    
	    @SubscribeEvent(priority=EventPriority.LOW, receiveCanceled=true)
	    public void featherfall(LivingHurtEvent fEvent){
		  	if(!ModConfig.miscellaneous.extraProtectionEffects)
		  		return;
	    	//System.out.println("EVENT EEEEEEEEEEEEEEE");
	    	if(!(ModConfig.enabled.AdvancedFeatherFalling))
	    		return;
	    		
	    	if((fEvent.getSource() != DamageSource.FALL))
				return;
	    	
	    	int modifier = EnchantmentsUtility.CalcModgetTotalLevel(9, Smc_030.AdvancedFeatherFalling, fEvent.getEntityLiving());
	    	
	    	float damage = EnchantmentsUtility.getDamageAfterMagicAbsorb(fEvent.getAmount(), modifier);
	    	
	    	fEvent.setAmount(damage);
	    }
	
}
