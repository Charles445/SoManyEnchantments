package com.Shultrea.Rin.Enchantments_Sector;

import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OrderedRegistry {

	public static void orderedRegister() {
		
		if(somanyenchantments.config.Instability)
			MinecraftForge.EVENT_BUS.register(Smc_040.Instability);
		
		if(somanyenchantments.config.LuckMagnification)
			MinecraftForge.EVENT_BUS.register(Smc_040.LuckMagnification);
		
		if(somanyenchantments.config.CursedEdgeEnable)
			MinecraftForge.EVENT_BUS.register(Smc_010.CursedEdge);
		
		if(somanyenchantments.config.AshDestroyerEnable)
			MinecraftForge.EVENT_BUS.register(Smc_030.AshDestroyer);
		
		if(somanyenchantments.config.CriticalStrikeEnable)
			MinecraftForge.EVENT_BUS.register(Smc_030.CriticalStrike);
		
		if(somanyenchantments.config.ReviledBladeEnable)
			MinecraftForge.EVENT_BUS.register(Smc_010.ReviledBlade);
		
		if(somanyenchantments.config.CursedEdgeEnable)
			MinecraftForge.EVENT_BUS.register(Smc_040.difficultyscaled);
		
		
		if(somanyenchantments.config.NaturalBlocking)
			MinecraftForge.EVENT_BUS.register(Smc_040.NaturalBlocking);
		
		if(somanyenchantments.config.NaturalBlocking)
			MinecraftForge.EVENT_BUS.register(Smc_040.NaturalBlocking);
		
		if(somanyenchantments.config.Rune_PiercingArrows)
			MinecraftForge.EVENT_BUS.register(Smc_040.Rune_PiercingArrows);
		
		//if(somanyenchantments.config.)
		//	MinecraftForge.EVENT_BUS.register(Smc_040.Swiper);
		
		if(somanyenchantments.config.Swiper)
			MinecraftForge.EVENT_BUS.register(Smc_040.Swiper);
		
		if(somanyenchantments.config.Rune_MagicalBlessingEnable)
			MinecraftForge.EVENT_BUS.register(Smc_020.Rune_MagicalBlessing);
		
		if(somanyenchantments.config.Rune_PiercingCapabilitiesEnable)
			MinecraftForge.EVENT_BUS.register(Smc_010.Rune_PiercingCapabilities);   
		
		if(somanyenchantments.config.AncientSwordMastery)
		MinecraftForge.EVENT_BUS.register(Smc_040.EnchantmentMastery); 
		

	}
	
}
