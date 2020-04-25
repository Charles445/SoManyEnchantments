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
import com.Shultrea.Rin.Main_Sector.ModConfig;
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
		if(ModConfig.enabled.ClearskyEnable)
			Clearsky = new EnchantmentClearsky();
		
		if(ModConfig.enabled.MoonlightEnable)
			Moonlight = new EnchantmentMoonlight();
		
		if(ModConfig.enabled.RainingEnable)
			Raining = new EnchantmentRaining();
		
		if(ModConfig.enabled.SunshineEnable)
			Sunshine = new EnchantmentSunshine();
		
		if(ModConfig.enabled.ThunderstormEnable)
			Thunderstorm = new EnchantmentThunderstorm();
		
		if(ModConfig.enabled.WinterEnable)
			Winter = new EnchantmentWinter();
		
		if(ModConfig.enabled.SmelterEnable)
			Smelter = new EnchantmentSmelter();
		
		if(ModConfig.enabled.EmpoweredDefenceEnable)
			EmpoweredDefence = new EnchantmentEmpoweredDefence();
		
		if(ModConfig.enabled.StrafeEnable)
			Strafe = new EnchantmentStrafe();
		
		if(ModConfig.enabled.CriticalStrikeEnable)
			CriticalStrike = new EnchantmentCriticalStrike();
		
		//if(ModConfig.enabled.ExtremeFortuneEnable)
		//	ExtremeFortune = new EnchantmentExtremeFortune();
	
		//if(ModConfig.enabled.Rune_RessurectionEnable)
			//Rune_Resurrection = new EnchantmentRune_Resurrection();
		
		if(ModConfig.enabled.AdvancedLootingEnable)
			AdvancedLooting = new EnchantmentAdvancedLooting();
		
		if(ModConfig.enabled.LevitatorEnable)
			Levitator = new EnchantmentLevitator();
		
		if(ModConfig.enabled.MagicProtectionEnable)
			MagicProtection = new EnchantmentMagicProtection();
		
		if(ModConfig.enabled.PhysicalProtectionEnable)
			PhysicalProtection = new EnchantmentPhysicalProtection();
		
		if(ModConfig.enabled.AshDestroyerEnable)
			AshDestroyer = new EnchantmentAshDestroyer();
		
		if(ModConfig.enabled.DesolatorEnable)
			Desolator = new EnchantmentDesolator();
		
		if(ModConfig.enabled.DisorientationEnable)
			Disorientation = new EnchantmentDisorientation();
		
		if(ModConfig.enabled.PurgingBladeEnable)
			PurgingBlade = new EnchantmentPurgingBlade();
		
		if(ModConfig.enabled.ViperEnable)
			Viper = new EnchantmentViper();
		
		if(ModConfig.enabled.AdvancedPowerEnable)
			AdvancedPower = new EnchantmentAdvancedPower();
		
		if(ModConfig.enabled.EnvenomedEnable)
			Envenomed = new EnchantmentEnvenomed();
		
		if(ModConfig.enabled.PowerlessEnable)
			Powerless = new EnchantmentPowerless();
		
		if(ModConfig.enabled.RevivalEnable)
		    Rune_Revival = new EnchantmentRune_Revival();
		
		if(ModConfig.enabled.AdvancedPunch)
			AdvancedPunch = new EnchantmentAdvancedPunch();
		
		if(ModConfig.enabled.AdvancedLure)
			AdvancedLure = new EnchantmentAdvancedLure();
		
		if(ModConfig.enabled.AdvancedLuckOfTheSea)
			AdvancedLuckOfTheSea = new EnchantmentAdvancedLuckOfTheSea();
		
		if(ModConfig.enabled.AdvancedFeatherFalling)
			AdvancedFeatherFalling = new EnchantmentAdvancedFeatherFalling();
		
		if(ModConfig.enabled.AdvancedThorns)
			AdvancedThorns = new EnchantmentAdvancedThorns();
		
		if(ModConfig.enabled.BurningThorns)
			BurningThorns = new EnchantmentBurningThorns();
		
	    if(ModConfig.enabled.AdvancedProtection)
	    	AdvancedProtection = new EnchantmentAdvancedProtection();
	    
	    if(ModConfig.enabled.AdvancedFireProtection)
	    	AdvancedFireProtection = new EnchantmentAdvancedFireProtection();
	    
	    if(ModConfig.enabled.AdvancedBlastProtection)
	    	AdvancedBlastProtection = new EnchantmentAdvancedBlastProtection();
	    
	    if(ModConfig.enabled.AdvancedProjectileProtection)
	    	AdvancedProjectileProtection = new EnchantmentAdvancedProjectileProtection();
	
		if(ModConfig.enabled.Quarrying)
			Quarrying = new EnchantmentQuarrying();
		    //Debug = new EnchantmentDebug();
	}

	  @Mod.EventBusSubscriber
	    public static class EventSubscriber {

	        @SubscribeEvent
	        public static void registerEnchantment(RegistryEvent.Register<net.minecraft.enchantment.Enchantment> event) {
	        	
	        	if(ModConfig.enabled.ClearskyEnable)
	    			event.getRegistry().register(Clearsky);
	    		
	    		if(ModConfig.enabled.MoonlightEnable)
	    			event.getRegistry().register(Moonlight);
	    		
	    		if(ModConfig.enabled.RainingEnable)
	    			event.getRegistry().register(Raining);
	    		
	    		if(ModConfig.enabled.SunshineEnable)
	    			event.getRegistry().register(Sunshine);
	    		
	    		if(ModConfig.enabled.ThunderstormEnable)
	    			event.getRegistry().register(Thunderstorm);
	    		
	    		if(ModConfig.enabled.WinterEnable)
	    			event.getRegistry().register(Winter);
	    		
	    		if(ModConfig.enabled.SmelterEnable)
	    			event.getRegistry().register(Smelter);
	    		
	    		if(ModConfig.enabled.EmpoweredDefenceEnable)
	    			event.getRegistry().register(EmpoweredDefence);
	    		
	    		if(ModConfig.enabled.StrafeEnable)
	    			event.getRegistry().register(Strafe);
	    		
	    		if(ModConfig.enabled.CriticalStrikeEnable)
	    			event.getRegistry().register(CriticalStrike);
	    		
	    		//if(ModConfig.enabled.ExtremeFortuneEnable)
	    		//	event.getRegistry().register(ExtremeFortune);
	    		
	    		//if(ModConfig.enabled.Rune_RessurectionEnable)
	    		//	event.getRegistry().register(Rune_Resurrection);
	    		
	    		if(ModConfig.enabled.AdvancedLootingEnable)
	    			event.getRegistry().register(AdvancedLooting);
	    		
	    		if(ModConfig.enabled.LevitatorEnable)
	    			event.getRegistry().register(Levitator);
	    		
	    		if(ModConfig.enabled.MagicProtectionEnable)
	    			event.getRegistry().register(MagicProtection);
	    		
	    		if(ModConfig.enabled.PhysicalProtectionEnable)
	    			event.getRegistry().register(PhysicalProtection);
	    		
	    		if(ModConfig.enabled.AshDestroyerEnable)
	    			event.getRegistry().register(AshDestroyer);
	    		
	    		if(ModConfig.enabled.DesolatorEnable)
	    			event.getRegistry().register(Desolator);
	    		
	    		if(ModConfig.enabled.DisorientationEnable)
	    			event.getRegistry().register(Disorientation);
	    		
	    		if(ModConfig.enabled.PurgingBladeEnable)
	    			event.getRegistry().register(PurgingBlade);
	    		
	    		if(ModConfig.enabled.ViperEnable)
	    			event.getRegistry().register(Viper);
	    		
	    		if(ModConfig.enabled.AdvancedPowerEnable)
	    			event.getRegistry().register(AdvancedPower);
	    		
	    		if(ModConfig.enabled.EnvenomedEnable)
	    			event.getRegistry().register(Envenomed);
	    		
	    		if(ModConfig.enabled.PowerlessEnable)
	    			event.getRegistry().register(Powerless);
	    		
	    		if(ModConfig.enabled.RevivalEnable)
	    			event.getRegistry().register(Rune_Revival);
	    		
	    	    if(ModConfig.enabled.AdvancedPunch)
	    	    	event.getRegistry().register(AdvancedPunch);
	    	    	
	    	    if(ModConfig.enabled.AdvancedLure)
	    	    	event.getRegistry().register(AdvancedLure);
	    	    
	    	    if(ModConfig.enabled.AdvancedLuckOfTheSea)
	    	    	event.getRegistry().register(AdvancedLuckOfTheSea);
	    	    
	    	    if(ModConfig.enabled.AdvancedFeatherFalling)
	    	    	event.getRegistry().register(AdvancedFeatherFalling);
	    	    
	    	    if(ModConfig.enabled.AdvancedThorns)
	    	    	event.getRegistry().register(AdvancedThorns);
	    	    
	    	    if(ModConfig.enabled.BurningThorns)
	    	    	event.getRegistry().register(BurningThorns);
	    	    
	    	    if(ModConfig.enabled.AdvancedProtection)
	    	    	event.getRegistry().register(AdvancedProtection);
	    	    
	    	    if(ModConfig.enabled.AdvancedFireProtection)
	    	    	event.getRegistry().register(AdvancedFireProtection);
	    	    
	    	    if(ModConfig.enabled.AdvancedBlastProtection)
	    	    	event.getRegistry().register(AdvancedBlastProtection);
	    	    
	    	    if(ModConfig.enabled.AdvancedProjectileProtection)
	    	    	event.getRegistry().register(AdvancedProjectileProtection);
	    	    
	    	    if(ModConfig.enabled.Quarrying)
	    	    	event.getRegistry().register(Quarrying);
	            		
	    	  //GameRegistry.register(Debug);
	            
	}
	  }
	  
	//Registers the enchantment itself and not its effect (event).
	public static void register(){
	/**	if(ModConfig.enabled.ClearskyEnable)
			GameRegistry.register(Clearsky);
		
		if(ModConfig.enabled.MoonlightEnable)
			GameRegistry.register(Moonlight);
		
		if(ModConfig.enabled.RainingEnable)
			GameRegistry.register(Raining);
		
		if(ModConfig.enabled.SunshineEnable)
			GameRegistry.register(Sunshine);
		
		if(ModConfig.enabled.ThunderstormEnable)
			GameRegistry.register(Thunderstorm);
		
		if(ModConfig.enabled.WinterEnable)
			GameRegistry.register(Winter);
		
		if(ModConfig.enabled.SmelterEnable)
			GameRegistry.register(Smelter);
		
		if(ModConfig.enabled.EmpoweredDefenceEnable)
			GameRegistry.register(EmpoweredDefence);
		
		if(ModConfig.enabled.StrafeEnable)
			GameRegistry.register(Strafe);
		
		if(ModConfig.enabled.CriticalStrikeEnable)
			GameRegistry.register(CriticalStrike);
		
		//if(ModConfig.enabled.ExtremeFortuneEnable)
		//	GameRegistry.register(ExtremeFortune);
		
		//if(ModConfig.enabled.Rune_RessurectionEnable)
		//	GameRegistry.register(Rune_Resurrection);
		
		if(ModConfig.enabled.ExtremeLootingEnable)
			GameRegistry.register(ExtremeLooting);
		
		if(ModConfig.enabled.LevitatorEnable)
			GameRegistry.register(Levitator);
		
		if(ModConfig.enabled.MagicProtectionEnable)
			GameRegistry.register(MagicProtection);
		
		if(ModConfig.enabled.PhysicalProtectionEnable)
			GameRegistry.register(PhysicalProtection);
		
		if(ModConfig.enabled.AshDestroyerEnable)
			GameRegistry.register(AshDestroyer);
		
		if(ModConfig.enabled.DesolatorEnable)
			GameRegistry.register(Desolator);
		
		if(ModConfig.enabled.DisorientationEnable)
			GameRegistry.register(Disorientation);
		
		if(ModConfig.enabled.PurgingBladeEnable)
			GameRegistry.register(PurgingBlade);
		
		if(ModConfig.enabled.ViperEnable)
			GameRegistry.register(Viper);
		
		if(ModConfig.enabled.ExtremePowerEnable)
			GameRegistry.register(ExtremePower);
		
		if(ModConfig.enabled.EnvenomedEnable)
			GameRegistry.register(Envenomed);
		
		if(ModConfig.enabled.PowerlessEnable)
			GameRegistry.register(Powerless);
		
		if(ModConfig.enabled.RevivalEnable)
			GameRegistry.register(Rune_Revival);
		
	    if(ModConfig.enabled.ExtremePunch)
	    	GameRegistry.register(ExtremePunch);
	    	
	    if(ModConfig.enabled.AdvancedLure)
	    	GameRegistry.register(AdvancedLure);
	    
	    if(ModConfig.enabled.AdvancedLuckOfTheSea)
	    	GameRegistry.register(AdvancedLuckOfTheSea);
	    
	    if(ModConfig.enabled.AdvancedFeatherFalling)
	    	GameRegistry.register(AdvancedFeatherFalling);
	    
	    if(ModConfig.enabled.AdvancedThorns)
	    	GameRegistry.register(AdvancedThorns);
	    
	    if(ModConfig.enabled.BurningThorns)
	    	GameRegistry.register(BurningThorns);
	    
	    if(ModConfig.enabled.AdvancedProtection)
	    	GameRegistry.register(AdvancedProtection);
	    
	    if(ModConfig.enabled.AdvancedFireProtection)
	    	GameRegistry.register(AdvancedFireProtection);
	    
	    if(ModConfig.enabled.AdvancedBlastProtection)
	    	GameRegistry.register(AdvancedBlastProtection);
	    
	    if(ModConfig.enabled.AdvancedProjectileProtection)
	    	GameRegistry.register(AdvancedProjectileProtection);
	    
	    
	    
		//GameRegistry.register(Debug);
		*/
	}
	//Registers the effect of an enchantment and not the enchantment.
	public static void enchHandler(){
		if(ModConfig.enabled.ClearskyEnable)
			MinecraftForge.EVENT_BUS.register(Clearsky);
		
		if(ModConfig.enabled.MoonlightEnable)
			MinecraftForge.EVENT_BUS.register(Moonlight);
		
		if(ModConfig.enabled.RainingEnable)
			MinecraftForge.EVENT_BUS.register(Raining);
		
		if(ModConfig.enabled.SunshineEnable)
			MinecraftForge.EVENT_BUS.register(Sunshine);
		
		if(ModConfig.enabled.ThunderstormEnable)
			MinecraftForge.EVENT_BUS.register(Thunderstorm);
		
		if(ModConfig.enabled.WinterEnable)
			MinecraftForge.EVENT_BUS.register(Winter);
		
		if(ModConfig.enabled.SmelterEnable)
			MinecraftForge.EVENT_BUS.register(Smelter);
		
		if(ModConfig.enabled.EmpoweredDefenceEnable)
			MinecraftForge.EVENT_BUS.register(EmpoweredDefence);
		
		if(ModConfig.enabled.StrafeEnable)
			MinecraftForge.EVENT_BUS.register(Strafe);
		
		//if(ModConfig.enabled.CriticalStrikeEnable)
		//	MinecraftForge.EVENT_BUS.register(CriticalStrike);
		
		//if(ModConfig.enabled.ExtremeFortuneEnable)
		//	MinecraftForge.EVENT_BUS.register(ExtremeFortune);
		
	//	if(ModConfig.enabled.Rune_RessurectionEnable)
		//	MinecraftForge.EVENT_BUS.register(Rune_Resurrection);
		
		if(ModConfig.enabled.AdvancedLootingEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedLooting);
		
		//if(ModConfig.enabled.AshDestroyerEnable)
		//	MinecraftForge.EVENT_BUS.register(AshDestroyer);
		
		if(ModConfig.enabled.DesolatorEnable)
			MinecraftForge.EVENT_BUS.register(Desolator);
		
		if(ModConfig.enabled.DisorientationEnable)
			MinecraftForge.EVENT_BUS.register(Disorientation);
		
		if(ModConfig.enabled.PurgingBladeEnable)
			MinecraftForge.EVENT_BUS.register(PurgingBlade);
		
		if(ModConfig.enabled.ViperEnable)
			MinecraftForge.EVENT_BUS.register(Viper);
		
		if(ModConfig.enabled.AdvancedPowerEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedPower);
		
		if(ModConfig.enabled.EnvenomedEnable)
			MinecraftForge.EVENT_BUS.register(Envenomed);
		
		if(ModConfig.enabled.PowerlessEnable)
			MinecraftForge.EVENT_BUS.register(Powerless);
		
		if(ModConfig.enabled.RevivalEnable)
			MinecraftForge.EVENT_BUS.register(Rune_Revival);
		
		if(ModConfig.enabled.AdvancedPunch)
			MinecraftForge.EVENT_BUS.register(AdvancedPunch);
		
		if(ModConfig.enabled.AdvancedLure)
			MinecraftForge.EVENT_BUS.register(AdvancedLure);
		
		if(ModConfig.enabled.AdvancedLuckOfTheSea)
			MinecraftForge.EVENT_BUS.register(AdvancedLuckOfTheSea);
		
		if(ModConfig.enabled.AdvancedFireProtection)
			MinecraftForge.EVENT_BUS.register(AdvancedFireProtection);
		
		if(ModConfig.enabled.AdvancedBlastProtection)
			MinecraftForge.EVENT_BUS.register(AdvancedBlastProtection);
			
		if(ModConfig.enabled.Quarrying)
			MinecraftForge.EVENT_BUS.register(Quarrying);
		//MinecraftForge.EVENT_BUS.register(Debug);
		
	
		
		//if(ModConfig.enabled.MagicProtectionEnable)
			//MinecraftForge.EVENT_BUS.register(MagicProtection);
	
		//if(ModConfig.enabled.PhysicalProtectionEnable)
			//MinecraftForge.EVENT_BUS.register(PhysicalProtection);
		
	}
	
}