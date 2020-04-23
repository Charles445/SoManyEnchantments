package com.Shultrea.Rin.Enum;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;

public enum EConstants {

	VanillaEnchantmentDamage(new Enchantment[]{Enchantments.SHARPNESS, Enchantments.SMITE, Enchantments.BANE_OF_ARTHROPODS}),
	VanillaArmorProtection(new Enchantment[]{Enchantments.PROTECTION, Enchantments.PROJECTILE_PROTECTION, Enchantments.BLAST_PROTECTION, Enchantments.FIRE_PROTECTION});
	
	Enchantment[] ench;
	
	EConstants(Enchantment[] e){
		e = ench;
	}
	
	public Enchantment[] getArrayEnchantment(){
		return ench;
	}
	
}
