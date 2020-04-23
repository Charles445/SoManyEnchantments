package com.Shultrea.Rin.Prop_Sector;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PlayerPropertiesStorage implements IStorage<IPlayerProperties>{

	@Override
	public NBTBase writeNBT(Capability<IPlayerProperties> capability, IPlayerProperties instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
	
		compound.setBoolean("isResurrecting", instance.isResurrecting());
		compound.setDouble("moralitasDamage", instance.getMoralitasDamage());
		
		return compound;
	}

	@Override
	public void readNBT(Capability<IPlayerProperties> capability, IPlayerProperties instance, EnumFacing side,NBTBase nbt) {
		NBTTagCompound nbttag = (NBTTagCompound) nbt;
		instance.setResurrecting(nbttag.getBoolean("isResurrecting"));
		instance.setMoralitasDamage(nbttag.getDouble("motalitasDamage"));
		
	}

}
