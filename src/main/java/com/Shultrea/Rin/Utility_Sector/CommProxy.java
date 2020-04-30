package com.Shultrea.Rin.Utility_Sector;

import java.util.List;

import javax.annotation.Nullable;

import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


/** The common proxy (shared server and client code) */
public class CommProxy {
	
	final List<SoundEvent> SOUND_EVENTS = Lists.newArrayList();
	
	public void preInit(FMLPreInitializationEvent event) {
		SMElogM.logger.info("PreInitializing " + somanyenchantments.NAME + "...");
		//SMEsounds.mainRegistry();
		SMEnetwork.mainRegistry();
		
		
		
		//MinecraftForge.EVENT_BUS.register(new LivingAttackFixerHandler());
		
		
	}
	
	public void onInit(FMLInitializationEvent event) {
		SMElogM.logger.info("Initializing " + somanyenchantments.NAME + "...");
		
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		SMElogM.logger.info("PostInitializing " + somanyenchantments.NAME + "...");
		
	}

	@Nullable
	public EntityPlayer getClientPlayer()
	{
		return null;
	}
}