package com.Shultrea.Rin.Enchantments_Sector;


import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedBlastProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedFeatherFalling;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedFireProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentQuarrying;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedLuckOfTheSea;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedLure;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedProjectileProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedThorns;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAshDestroyer;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentBurningThorns;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentClearsky;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentCriticalStrike;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentDebug;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentDesolator;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentDisorientation;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentEmpoweredDefence;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentEnvenomed;
//import com.Shultrea.Rin.Ench0_3_0.EnchantmentExtremeFortune;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedLooting;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedPower;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedPunch;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentLevitator;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentMagicProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentThunderstorm;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentViper;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentSunshine;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentWinter;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentMoonlight;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentPhysicalProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentPowerless;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentPurgingBlade;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentRaining;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentRune_Revival;
//import com.Shultrea.Rin.Ench0_3_0.EnchantmentRune_Resurrection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentSmelter;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentStrafe;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.ExtraEvent;
import com.Shultrea.Rin.Utility_Sector.LivingAttackFixerHandler;
import com.Shultrea.Rin.Utility_Sector.OtherHandler;
import com.Shultrea.Rin.Utility_Sector.RefStrings;
import com.Shultrea.Rin.Utility_Sector.SMElogM;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smc_030 {

	public static Enchantment EmpoweredDefence;
	//public static Enchantment Rune_Resurrection;
	public static Enchantment Clearsky;
	public static Enchantment Moonlight;
	public static Enchantment Raining;
	public static Enchantment Sunshine;
	public static Enchantment Thunderstorm;
	public static Enchantment Winter;
	public static Enchantment ExtremeFortune;
	public static Enchantment Smelter;
	
	public static Enchantment Strafe;
	public static Enchantment AdvancedPower;
	public static Enchantment CriticalStrike;
	public static Enchantment AshDestroyer;
	public static Enchantment Desolator;
	public static Enchantment Disorientation;
	public static Enchantment Envenomed;
	public static Enchantment PurgingBlade;
	public static Enchantment Viper;
	public static Enchantment AdvancedLooting;
	public static Enchantment Levitator;
	public static Enchantment MagicProtection;
	public static Enchantment PhysicalProtection;
	public static Enchantment Debug;
	public static Enchantment Powerless;
	public static Enchantment AdvancedPunch;
	public static Enchantment Rune_Revival;
    public static Enchantment AdvancedLure;
    public static Enchantment AdvancedLuckOfTheSea;
    public static Enchantment AdvancedFeatherFalling;
    public static Enchantment AdvancedThorns;
    public static Enchantment BurningThorns;
    public static Enchantment AdvancedProtection;
    public static Enchantment AdvancedFireProtection;
    public static Enchantment AdvancedBlastProtection;
    public static Enchantment AdvancedProjectileProtection;
    public static Enchantment Quarrying;
	
	
	
	public static void init(){
		if(somanyenchantments.config.ClearskyEnable)
			Clearsky = new EnchantmentClearsky();
		
		if(somanyenchantments.config.MoonlightEnable)
			Moonlight = new EnchantmentMoonlight();
		
		if(somanyenchantments.config.RainingEnable)
			Raining = new EnchantmentRaining();
		
		if(somanyenchantments.config.SunshineEnable)
			Sunshine = new EnchantmentSunshine();
		
		if(somanyenchantments.config.ThunderstormEnable)
			Thunderstorm = new EnchantmentThunderstorm();
		
		if(somanyenchantments.config.WinterEnable)
			Winter = new EnchantmentWinter();
		
		if(somanyenchantments.config.SmelterEnable)
			Smelter = new EnchantmentSmelter();
		
		if(somanyenchantments.config.EmpoweredDefenceEnable)
			EmpoweredDefence = new EnchantmentEmpoweredDefence();
		
		if(somanyenchantments.config.StrafeEnable)
			Strafe = new EnchantmentStrafe();
		
		if(somanyenchantments.config.CriticalStrikeEnable)
			CriticalStrike = new EnchantmentCriticalStrike();
		
		//if(somanyenchantments.config.ExtremeFortuneEnable)
		//	ExtremeFortune = new EnchantmentExtremeFortune();
	
		//if(somanyenchantments.config.Rune_RessurectionEnable)
			//Rune_Resurrection = new EnchantmentRune_Resurrection();
		
		if(somanyenchantments.config.AdvancedLootingEnable)
			AdvancedLooting = new EnchantmentAdvancedLooting();
		
		if(somanyenchantments.config.LevitatorEnable)
			Levitator = new EnchantmentLevitator();
		
		if(somanyenchantments.config.MagicProtectionEnable)
			MagicProtection = new EnchantmentMagicProtection();
		
		if(somanyenchantments.config.PhysicalProtectionEnable)
			PhysicalProtection = new EnchantmentPhysicalProtection();
		
		if(somanyenchantments.config.AshDestroyerEnable)
			AshDestroyer = new EnchantmentAshDestroyer();
		
		if(somanyenchantments.config.DesolatorEnable)
			Desolator = new EnchantmentDesolator();
		
		if(somanyenchantments.config.DisorientationEnable)
			Disorientation = new EnchantmentDisorientation();
		
		if(somanyenchantments.config.PurgingBladeEnable)
			PurgingBlade = new EnchantmentPurgingBlade();
		
		if(somanyenchantments.config.ViperEnable)
			Viper = new EnchantmentViper();
		
		if(somanyenchantments.config.AdvancedPowerEnable)
			AdvancedPower = new EnchantmentAdvancedPower();
		
		if(somanyenchantments.config.EnvenomedEnable)
			Envenomed = new EnchantmentEnvenomed();
		
		if(somanyenchantments.config.PowerlessEnable)
			Powerless = new EnchantmentPowerless();
		
		if(somanyenchantments.config.RevivalEnable)
		    Rune_Revival = new EnchantmentRune_Revival();
		
		if(somanyenchantments.config.AdvancedPunch)
			AdvancedPunch = new EnchantmentAdvancedPunch();
		
		if(somanyenchantments.config.AdvancedLure)
			AdvancedLure = new EnchantmentAdvancedLure();
		
		if(somanyenchantments.config.AdvancedLuckOfTheSea)
			AdvancedLuckOfTheSea = new EnchantmentAdvancedLuckOfTheSea();
		
		if(somanyenchantments.config.AdvancedFeatherFalling)
			AdvancedFeatherFalling = new EnchantmentAdvancedFeatherFalling();
		
		if(somanyenchantments.config.AdvancedThorns)
			AdvancedThorns = new EnchantmentAdvancedThorns();
		
		if(somanyenchantments.config.BurningThorns)
			BurningThorns = new EnchantmentBurningThorns();
		
	    if(somanyenchantments.config.AdvancedProtection)
	    	AdvancedProtection = new EnchantmentAdvancedProtection();
	    
	    if(somanyenchantments.config.AdvancedFireProtection)
	    	AdvancedFireProtection = new EnchantmentAdvancedFireProtection();
	    
	    if(somanyenchantments.config.AdvancedBlastProtection)
	    	AdvancedBlastProtection = new EnchantmentAdvancedBlastProtection();
	    
	    if(somanyenchantments.config.AdvancedProjectileProtection)
	    	AdvancedProjectileProtection = new EnchantmentAdvancedProjectileProtection();
	
		if(somanyenchantments.config.Quarrying)
			Quarrying = new EnchantmentQuarrying();
		    //Debug = new EnchantmentDebug();
	}

	  @Mod.EventBusSubscriber
	    public static class EventSubscriber {

	        @SubscribeEvent
	        public static void registerEnchantment(RegistryEvent.Register<net.minecraft.enchantment.Enchantment> event) {
	        	
	        	if(somanyenchantments.config.ClearskyEnable)
	    			event.getRegistry().register(Clearsky);
	    		
	    		if(somanyenchantments.config.MoonlightEnable)
	    			event.getRegistry().register(Moonlight);
	    		
	    		if(somanyenchantments.config.RainingEnable)
	    			event.getRegistry().register(Raining);
	    		
	    		if(somanyenchantments.config.SunshineEnable)
	    			event.getRegistry().register(Sunshine);
	    		
	    		if(somanyenchantments.config.ThunderstormEnable)
	    			event.getRegistry().register(Thunderstorm);
	    		
	    		if(somanyenchantments.config.WinterEnable)
	    			event.getRegistry().register(Winter);
	    		
	    		if(somanyenchantments.config.SmelterEnable)
	    			event.getRegistry().register(Smelter);
	    		
	    		if(somanyenchantments.config.EmpoweredDefenceEnable)
	    			event.getRegistry().register(EmpoweredDefence);
	    		
	    		if(somanyenchantments.config.StrafeEnable)
	    			event.getRegistry().register(Strafe);
	    		
	    		if(somanyenchantments.config.CriticalStrikeEnable)
	    			event.getRegistry().register(CriticalStrike);
	    		
	    		//if(somanyenchantments.config.ExtremeFortuneEnable)
	    		//	event.getRegistry().register(ExtremeFortune);
	    		
	    		//if(somanyenchantments.config.Rune_RessurectionEnable)
	    		//	event.getRegistry().register(Rune_Resurrection);
	    		
	    		if(somanyenchantments.config.AdvancedLootingEnable)
	    			event.getRegistry().register(AdvancedLooting);
	    		
	    		if(somanyenchantments.config.LevitatorEnable)
	    			event.getRegistry().register(Levitator);
	    		
	    		if(somanyenchantments.config.MagicProtectionEnable)
	    			event.getRegistry().register(MagicProtection);
	    		
	    		if(somanyenchantments.config.PhysicalProtectionEnable)
	    			event.getRegistry().register(PhysicalProtection);
	    		
	    		if(somanyenchantments.config.AshDestroyerEnable)
	    			event.getRegistry().register(AshDestroyer);
	    		
	    		if(somanyenchantments.config.DesolatorEnable)
	    			event.getRegistry().register(Desolator);
	    		
	    		if(somanyenchantments.config.DisorientationEnable)
	    			event.getRegistry().register(Disorientation);
	    		
	    		if(somanyenchantments.config.PurgingBladeEnable)
	    			event.getRegistry().register(PurgingBlade);
	    		
	    		if(somanyenchantments.config.ViperEnable)
	    			event.getRegistry().register(Viper);
	    		
	    		if(somanyenchantments.config.AdvancedPowerEnable)
	    			event.getRegistry().register(AdvancedPower);
	    		
	    		if(somanyenchantments.config.EnvenomedEnable)
	    			event.getRegistry().register(Envenomed);
	    		
	    		if(somanyenchantments.config.PowerlessEnable)
	    			event.getRegistry().register(Powerless);
	    		
	    		if(somanyenchantments.config.RevivalEnable)
	    			event.getRegistry().register(Rune_Revival);
	    		
	    	    if(somanyenchantments.config.AdvancedPunch)
	    	    	event.getRegistry().register(AdvancedPunch);
	    	    	
	    	    if(somanyenchantments.config.AdvancedLure)
	    	    	event.getRegistry().register(AdvancedLure);
	    	    
	    	    if(somanyenchantments.config.AdvancedLuckOfTheSea)
	    	    	event.getRegistry().register(AdvancedLuckOfTheSea);
	    	    
	    	    if(somanyenchantments.config.AdvancedFeatherFalling)
	    	    	event.getRegistry().register(AdvancedFeatherFalling);
	    	    
	    	    if(somanyenchantments.config.AdvancedThorns)
	    	    	event.getRegistry().register(AdvancedThorns);
	    	    
	    	    if(somanyenchantments.config.BurningThorns)
	    	    	event.getRegistry().register(BurningThorns);
	    	    
	    	    if(somanyenchantments.config.AdvancedProtection)
	    	    	event.getRegistry().register(AdvancedProtection);
	    	    
	    	    if(somanyenchantments.config.AdvancedFireProtection)
	    	    	event.getRegistry().register(AdvancedFireProtection);
	    	    
	    	    if(somanyenchantments.config.AdvancedBlastProtection)
	    	    	event.getRegistry().register(AdvancedBlastProtection);
	    	    
	    	    if(somanyenchantments.config.AdvancedProjectileProtection)
	    	    	event.getRegistry().register(AdvancedProjectileProtection);
	    	    
	    	    if(somanyenchantments.config.Quarrying)
	    	    	event.getRegistry().register(Quarrying);
	            		
	    	  //GameRegistry.register(Debug);
	            
	}
	  }
	  
	//Registers the enchantment itself and not its effect (event).
	public static void register(){
	/**	if(somanyenchantments.config.ClearskyEnable)
			GameRegistry.register(Clearsky);
		
		if(somanyenchantments.config.MoonlightEnable)
			GameRegistry.register(Moonlight);
		
		if(somanyenchantments.config.RainingEnable)
			GameRegistry.register(Raining);
		
		if(somanyenchantments.config.SunshineEnable)
			GameRegistry.register(Sunshine);
		
		if(somanyenchantments.config.ThunderstormEnable)
			GameRegistry.register(Thunderstorm);
		
		if(somanyenchantments.config.WinterEnable)
			GameRegistry.register(Winter);
		
		if(somanyenchantments.config.SmelterEnable)
			GameRegistry.register(Smelter);
		
		if(somanyenchantments.config.EmpoweredDefenceEnable)
			GameRegistry.register(EmpoweredDefence);
		
		if(somanyenchantments.config.StrafeEnable)
			GameRegistry.register(Strafe);
		
		if(somanyenchantments.config.CriticalStrikeEnable)
			GameRegistry.register(CriticalStrike);
		
		//if(somanyenchantments.config.ExtremeFortuneEnable)
		//	GameRegistry.register(ExtremeFortune);
		
		//if(somanyenchantments.config.Rune_RessurectionEnable)
		//	GameRegistry.register(Rune_Resurrection);
		
		if(somanyenchantments.config.ExtremeLootingEnable)
			GameRegistry.register(ExtremeLooting);
		
		if(somanyenchantments.config.LevitatorEnable)
			GameRegistry.register(Levitator);
		
		if(somanyenchantments.config.MagicProtectionEnable)
			GameRegistry.register(MagicProtection);
		
		if(somanyenchantments.config.PhysicalProtectionEnable)
			GameRegistry.register(PhysicalProtection);
		
		if(somanyenchantments.config.AshDestroyerEnable)
			GameRegistry.register(AshDestroyer);
		
		if(somanyenchantments.config.DesolatorEnable)
			GameRegistry.register(Desolator);
		
		if(somanyenchantments.config.DisorientationEnable)
			GameRegistry.register(Disorientation);
		
		if(somanyenchantments.config.PurgingBladeEnable)
			GameRegistry.register(PurgingBlade);
		
		if(somanyenchantments.config.ViperEnable)
			GameRegistry.register(Viper);
		
		if(somanyenchantments.config.ExtremePowerEnable)
			GameRegistry.register(ExtremePower);
		
		if(somanyenchantments.config.EnvenomedEnable)
			GameRegistry.register(Envenomed);
		
		if(somanyenchantments.config.PowerlessEnable)
			GameRegistry.register(Powerless);
		
		if(somanyenchantments.config.RevivalEnable)
			GameRegistry.register(Rune_Revival);
		
	    if(somanyenchantments.config.ExtremePunch)
	    	GameRegistry.register(ExtremePunch);
	    	
	    if(somanyenchantments.config.AdvancedLure)
	    	GameRegistry.register(AdvancedLure);
	    
	    if(somanyenchantments.config.AdvancedLuckOfTheSea)
	    	GameRegistry.register(AdvancedLuckOfTheSea);
	    
	    if(somanyenchantments.config.AdvancedFeatherFalling)
	    	GameRegistry.register(AdvancedFeatherFalling);
	    
	    if(somanyenchantments.config.AdvancedThorns)
	    	GameRegistry.register(AdvancedThorns);
	    
	    if(somanyenchantments.config.BurningThorns)
	    	GameRegistry.register(BurningThorns);
	    
	    if(somanyenchantments.config.AdvancedProtection)
	    	GameRegistry.register(AdvancedProtection);
	    
	    if(somanyenchantments.config.AdvancedFireProtection)
	    	GameRegistry.register(AdvancedFireProtection);
	    
	    if(somanyenchantments.config.AdvancedBlastProtection)
	    	GameRegistry.register(AdvancedBlastProtection);
	    
	    if(somanyenchantments.config.AdvancedProjectileProtection)
	    	GameRegistry.register(AdvancedProjectileProtection);
	    
	    
	    
		//GameRegistry.register(Debug);
		*/
	}
	//Registers the effect of an enchantment and not the enchantment.
	public static void enchHandler(){
		if(somanyenchantments.config.ClearskyEnable)
			MinecraftForge.EVENT_BUS.register(Clearsky);
		
		if(somanyenchantments.config.MoonlightEnable)
			MinecraftForge.EVENT_BUS.register(Moonlight);
		
		if(somanyenchantments.config.RainingEnable)
			MinecraftForge.EVENT_BUS.register(Raining);
		
		if(somanyenchantments.config.SunshineEnable)
			MinecraftForge.EVENT_BUS.register(Sunshine);
		
		if(somanyenchantments.config.ThunderstormEnable)
			MinecraftForge.EVENT_BUS.register(Thunderstorm);
		
		if(somanyenchantments.config.WinterEnable)
			MinecraftForge.EVENT_BUS.register(Winter);
		
		if(somanyenchantments.config.SmelterEnable)
			MinecraftForge.EVENT_BUS.register(Smelter);
		
		if(somanyenchantments.config.EmpoweredDefenceEnable)
			MinecraftForge.EVENT_BUS.register(EmpoweredDefence);
		
		if(somanyenchantments.config.StrafeEnable)
			MinecraftForge.EVENT_BUS.register(Strafe);
		
		//if(somanyenchantments.config.CriticalStrikeEnable)
		//	MinecraftForge.EVENT_BUS.register(CriticalStrike);
		
		//if(somanyenchantments.config.ExtremeFortuneEnable)
		//	MinecraftForge.EVENT_BUS.register(ExtremeFortune);
		
	//	if(somanyenchantments.config.Rune_RessurectionEnable)
		//	MinecraftForge.EVENT_BUS.register(Rune_Resurrection);
		
		if(somanyenchantments.config.AdvancedLootingEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedLooting);
		
		//if(somanyenchantments.config.AshDestroyerEnable)
		//	MinecraftForge.EVENT_BUS.register(AshDestroyer);
		
		if(somanyenchantments.config.DesolatorEnable)
			MinecraftForge.EVENT_BUS.register(Desolator);
		
		if(somanyenchantments.config.DisorientationEnable)
			MinecraftForge.EVENT_BUS.register(Disorientation);
		
		if(somanyenchantments.config.PurgingBladeEnable)
			MinecraftForge.EVENT_BUS.register(PurgingBlade);
		
		if(somanyenchantments.config.ViperEnable)
			MinecraftForge.EVENT_BUS.register(Viper);
		
		if(somanyenchantments.config.AdvancedPowerEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedPower);
		
		if(somanyenchantments.config.EnvenomedEnable)
			MinecraftForge.EVENT_BUS.register(Envenomed);
		
		if(somanyenchantments.config.PowerlessEnable)
			MinecraftForge.EVENT_BUS.register(Powerless);
		
		if(somanyenchantments.config.RevivalEnable)
			MinecraftForge.EVENT_BUS.register(Rune_Revival);
		
		if(somanyenchantments.config.AdvancedPunch)
			MinecraftForge.EVENT_BUS.register(AdvancedPunch);
		
		if(somanyenchantments.config.AdvancedLure)
			MinecraftForge.EVENT_BUS.register(AdvancedLure);
		
		if(somanyenchantments.config.AdvancedLuckOfTheSea)
			MinecraftForge.EVENT_BUS.register(AdvancedLuckOfTheSea);
		
		if(somanyenchantments.config.AdvancedFireProtection)
			MinecraftForge.EVENT_BUS.register(AdvancedFireProtection);
		
		if(somanyenchantments.config.AdvancedBlastProtection)
			MinecraftForge.EVENT_BUS.register(AdvancedBlastProtection);
			
		if(somanyenchantments.config.Quarrying)
			MinecraftForge.EVENT_BUS.register(Quarrying);
		//MinecraftForge.EVENT_BUS.register(Debug);
		
	
		
		//if(somanyenchantments.config.MagicProtectionEnable)
			//MinecraftForge.EVENT_BUS.register(MagicProtection);
	
		//if(somanyenchantments.config.PhysicalProtectionEnable)
			//MinecraftForge.EVENT_BUS.register(PhysicalProtection);
		
	}
	
}