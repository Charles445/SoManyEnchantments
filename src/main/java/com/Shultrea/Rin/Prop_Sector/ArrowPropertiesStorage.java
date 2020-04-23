package com.Shultrea.Rin.Prop_Sector;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ArrowPropertiesStorage implements IStorage<IArrowProperties>{

	@Override
	public NBTBase writeNBT(Capability<IArrowProperties> capability, IArrowProperties instance, EnumFacing side) {
		NBTTagCompound compound = new NBTTagCompound();
		
		compound.setInteger("flameLevel", instance.getFlameLevel());
		
		compound.setFloat("ExplosionPower", instance.getExplosionPower());
		compound.setBoolean("CanDestroyBlocks", instance.getCanDestroyBlocks());
		compound.setBoolean("NoDrag", instance.getNoDrag());
		compound.setBoolean("CanRecover", instance.getCanRecover());
		
		compound.setInteger("enchlevel", instance.getLevel());
		compound.setBoolean("didStarFall", instance.didStarFall());
		compound.setBoolean("isStarFallMade", instance.getIsStarFallMade());
		compound.setBoolean("doesPierceInvulnerability", instance.isArrowRapidDamage());
		compound.setInteger("armorPierceLevel", instance.getArmorPiercingLevel());
		compound.setFloat("pullPower", instance.getPullPower());
		
		
		return compound;
	}

	@Override
	public void readNBT(Capability<IArrowProperties> capability, IArrowProperties instance, EnumFacing side,NBTBase nbt) {
		instance.setExplosion(((NBTTagCompound)nbt).getFloat("ExplosionPower"), ((NBTTagCompound)nbt).getBoolean("CanDestroyBlocks"));
		instance.setNoDrag(((NBTTagCompound)nbt).getBoolean("NoDrag"));
		instance.setCanRecover(((NBTTagCompound)nbt).getBoolean("CanRecover"));
		
		instance.setLevel(((NBTTagCompound)nbt).getInteger("enchlevel"));
		instance.setDidStarFall(((NBTTagCompound)nbt).getBoolean("didStarFall"));
		instance.setIsStarFallMade(((NBTTagCompound)nbt).getBoolean("isStarFallMade"));
		
		instance.setFlameLevel(((NBTTagCompound)nbt).getInteger("flameLevel"));
		instance.setArrowRapidDamage(((NBTTagCompound)nbt).getBoolean("doesPierceInvulnerability"));
		
		instance.setPiercingLevel(((NBTTagCompound)nbt).getInteger("armorPierceLevel"));
		instance.setPullPower(((NBTTagCompound)nbt).getFloat("pullPower"));
	}

}
