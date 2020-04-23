
package com.Shultrea.Rin.Prop_Sector;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PlayerPropertiesProvider implements ICapabilitySerializable<NBTTagCompound>{
	

	 @CapabilityInject(IPlayerProperties.class)
	 public static final Capability<IPlayerProperties> PLAYERPROPERTIES_CAP = null;
	 
	 private IPlayerProperties propertyInstance = PLAYERPROPERTIES_CAP.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == PLAYERPROPERTIES_CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
			
		return capability == PLAYERPROPERTIES_CAP ? PLAYERPROPERTIES_CAP.<T> cast(this.propertyInstance) : null;
		
	}

	@Override
	public NBTTagCompound serializeNBT() {	
		 return (NBTTagCompound) PLAYERPROPERTIES_CAP.getStorage().writeNBT(PLAYERPROPERTIES_CAP, this.propertyInstance, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		PLAYERPROPERTIES_CAP.getStorage().readNBT(PLAYERPROPERTIES_CAP, propertyInstance, null, nbt);
		
	}
}
