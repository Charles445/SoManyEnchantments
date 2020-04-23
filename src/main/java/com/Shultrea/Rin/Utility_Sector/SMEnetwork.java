package com.Shultrea.Rin.Utility_Sector;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


public class SMEnetwork {
	
	public static SimpleNetworkWrapper net;
	public static int id;
	
	public static void mainRegistry() {
		id = 0;
		registerNetwork();
	}
	
	private static void registerNetwork() {
		net = NetworkRegistry.INSTANCE.newSimpleChannel(RefStrings.SHORTNAME + ".net");
		net.registerMessage(MsgSP_Particle.Handler.class, MsgSP_Particle.class, id++, Side.CLIENT);
	
	}

}