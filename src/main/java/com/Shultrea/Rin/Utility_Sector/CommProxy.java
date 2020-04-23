package com.Shultrea.Rin.Utility_Sector;

import java.util.List;

import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.google.common.collect.Lists;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;


/** The common proxy (shared server and client code) */
public class CommProxy {
	
	final List<SoundEvent> SOUND_EVENTS = Lists.newArrayList();
	
	public void preInit(FMLPreInitializationEvent event) {
		SMElogM.logger.info("PreInitializing " + RefStrings.NAME + "...");
		//SMEsounds.mainRegistry();
		SMEnetwork.mainRegistry();
		
		
		
		MinecraftForge.EVENT_BUS.register(new LivingAttackFixerHandler());
		
		
	}
	
	public void onInit(FMLInitializationEvent event) {
		SMElogM.logger.info("Initializing " + RefStrings.NAME + "...");
		
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		SMElogM.logger.info("PostInitializing " + RefStrings.NAME + "...");
		
	}

}