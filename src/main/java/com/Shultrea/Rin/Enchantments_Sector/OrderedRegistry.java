package com.Shultrea.Rin.Enchantments_Sector;

import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OrderedRegistry {

	public static void orderedRegister() {
		
		if(ModConfig.enabled.Instability)
			MinecraftForge.EVENT_BUS.register(Smc_040.Instability);
		
		if(ModConfig.enabled.LuckMagnification)
			MinecraftForge.EVENT_BUS.register(Smc_040.LuckMagnification);
		
		if(ModConfig.enabled.CursedEdgeEnable)
			MinecraftForge.EVENT_BUS.register(Smc_010.CursedEdge);
		
		if(ModConfig.enabled.AshDestroyerEnable)
			MinecraftForge.EVENT_BUS.register(Smc_030.AshDestroyer);
		
		if(ModConfig.enabled.CriticalStrikeEnable)
			MinecraftForge.EVENT_BUS.register(Smc_030.CriticalStrike);
		
		if(ModConfig.enabled.ReviledBladeEnable)
			MinecraftForge.EVENT_BUS.register(Smc_010.ReviledBlade);
		
		if(ModConfig.enabled.CursedEdgeEnable)
			MinecraftForge.EVENT_BUS.register(Smc_040.difficultyscaled);
		
		
		if(ModConfig.enabled.NaturalBlocking)
			MinecraftForge.EVENT_BUS.register(Smc_040.NaturalBlocking);
		
		if(ModConfig.enabled.NaturalBlocking)
			MinecraftForge.EVENT_BUS.register(Smc_040.NaturalBlocking);
		
		if(ModConfig.enabled.Rune_PiercingArrows)
			MinecraftForge.EVENT_BUS.register(Smc_040.Rune_PiercingArrows);
		
		//if(ModConfig.enabled.)
		//	MinecraftForge.EVENT_BUS.register(Smc_040.Swiper);
		
		if(ModConfig.enabled.Swiper)
			MinecraftForge.EVENT_BUS.register(Smc_040.Swiper);
		
		if(ModConfig.enabled.Rune_MagicalBlessingEnable)
			MinecraftForge.EVENT_BUS.register(Smc_020.Rune_MagicalBlessing);
		
		if(ModConfig.enabled.Rune_PiercingCapabilitiesEnable)
			MinecraftForge.EVENT_BUS.register(Smc_010.Rune_PiercingCapabilities);   
		
		if(ModConfig.enabled.AncientSwordMastery)
		MinecraftForge.EVENT_BUS.register(Smc_040.EnchantmentMastery); 
		

	}
	
}
