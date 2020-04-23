package com.Shultrea.Rin.Utility_Sector;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


/** Send a message from server to client to spawn particles */
public class MsgSP_Particle implements IMessage {
	
	private String name;
	private double x, y, z;
	private double vx, vy, vz;
	
	public MsgSP_Particle() { }
	
	public MsgSP_Particle(String name, double x, double y, double z, double vx, double vy, double vz) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		this.name = tag.getString("name");
		this.x = tag.getDouble("x");
		this.y = tag.getDouble("y");
		this.z = tag.getDouble("z");
		this.vx = tag.getDouble("vx");
		this.vy = tag.getDouble("vy");
		this.vz = tag.getDouble("vz");
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("name", this.name);
		tag.setDouble("x", this.x);
		tag.setDouble("y", this.y);
		tag.setDouble("z", this.z);
		tag.setDouble("vx", this.vx);
		tag.setDouble("vy", this.vy);
		tag.setDouble("vz", this.vz);
		ByteBufUtils.writeTag(buf, tag);
	}
	
	/** spawn the particle */
	public static class Handler implements IMessageHandler<MsgSP_Particle, IMessage> {
        @Override
        public IMessage onMessage(MsgSP_Particle m, MessageContext ctx) {
        	if(Misc.DEBUG) System.out.println("Spawning particle");
        	Minecraft.getMinecraft().addScheduledTask(() -> SMEparticles.spawnParticle(m.name, m.x, m.y, m.z, m.vx, m.vy, m.vz));
            return null; // no response in this case
        }
	}
}
