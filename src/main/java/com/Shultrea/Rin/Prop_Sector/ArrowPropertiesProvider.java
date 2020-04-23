package com.Shultrea.Rin.Prop_Sector;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class ArrowPropertiesProvider implements ICapabilitySerializable<NBTTagCompound>{
	
	 @CapabilityInject(IArrowProperties.class)
	 public static final Capability<IArrowProperties> ARROWPROPERTIES_CAP = null;
	 private IArrowProperties instance = ARROWPROPERTIES_CAP.getDefaultInstance();
	 
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

		return capability == ARROWPROPERTIES_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		
		return capability == ARROWPROPERTIES_CAP ? ARROWPROPERTIES_CAP.<T> cast(this.instance) : null;
	}

	@Override
	public NBTTagCompound serializeNBT() {	
		 return (NBTTagCompound) ARROWPROPERTIES_CAP.getStorage().writeNBT(ARROWPROPERTIES_CAP, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		ARROWPROPERTIES_CAP.getStorage().readNBT(ARROWPROPERTIES_CAP, instance, null, nbt);
		
	}
}
