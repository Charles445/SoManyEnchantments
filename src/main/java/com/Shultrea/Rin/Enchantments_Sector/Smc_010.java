package com.Shultrea.Rin.Enchantments_Sector;

import com.Shultrea.Rin.Ench0_1_0.EnchantmentAdvancedBaneOfArthropods;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentAdvancedEfficiency;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentAdvancedSharpness;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentAdvancedSmite;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentBlessedEdge;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentBluntness;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentButchering;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentCursedEdge;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentDefusion;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentFieryEdge;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentHeavyWeight;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentInefficient;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentPurification;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentReviledBlade;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentRune_PiercingCapabilities;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentRusted;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentSpellBreaker;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentSwifterSlashes;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentWaterAspect;
import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Smc_010 {
	
	public static EnchantmentBase Defusion; //
	public static EnchantmentBase Butchering; //
	public static EnchantmentBase Purification; //
	public static EnchantmentBase SpellBreaker; //
	public static EnchantmentBase WaterAspect; //
	public static EnchantmentBase CursedEdge; //
	public static EnchantmentBase BlessedEdge; //
	public static EnchantmentBase AdvancedSharpness; //
	public static EnchantmentBase AdvancedSmite; //
	public static EnchantmentBase AdvancedBaneOfArthropods; //
	public static EnchantmentBase FieryEdge; //
	public static EnchantmentBase Rune_PiercingCapabilities; //
	public static EnchantmentBase ReviledBlade; //
	public static EnchantmentBase SwifterSlashes; //
	
	public static EnchantmentBase BlazingBlade;
	public static EnchantmentBase Enchanted;
	public static EnchantmentBase SilencedEdge;
	public static EnchantmentBase Thorned;
	public static EnchantmentBase VoidEmpowered;
	public static EnchantmentBase Electrocution;
	public static EnchantmentBase ExtremeEfficency; //
	public static EnchantmentBase Bluntness; //
	public static EnchantmentBase Rusted; //
	public static EnchantmentBase HeavyWeight; //
	public static EnchantmentBase Inefficient; //
	
	public static void init()
	{	
		Inefficient = registerAs(new EnchantmentInefficient());
        HeavyWeight = registerAs(new EnchantmentHeavyWeight());
		Rusted = registerAs(new EnchantmentRusted());
		Bluntness = registerAs(new EnchantmentBluntness());
		ExtremeEfficency = registerAs(new EnchantmentAdvancedEfficiency());
		BlessedEdge = registerAs(new EnchantmentBlessedEdge());
     	Butchering = registerAs(new EnchantmentButchering());   	
     	CursedEdge = registerAs(new EnchantmentCursedEdge());
        Defusion = registerAs(new EnchantmentDefusion());
    	AdvancedBaneOfArthropods = registerAs(new EnchantmentAdvancedBaneOfArthropods());
     	AdvancedSharpness = registerAs(new EnchantmentAdvancedSharpness());
    	AdvancedSmite = registerAs(new EnchantmentAdvancedSmite());
     	FieryEdge = registerAs(new EnchantmentFieryEdge());
    	Purification = registerAs(new EnchantmentPurification());
     	ReviledBlade = registerAs(new EnchantmentReviledBlade());
    	Rune_PiercingCapabilities = registerAs(new EnchantmentRune_PiercingCapabilities());
     	SpellBreaker = registerAs(new EnchantmentSpellBreaker());
    	SwifterSlashes = registerAs(new EnchantmentSwifterSlashes());
     	WaterAspect = registerAs(new EnchantmentWaterAspect());
	}
	
	private static EnchantmentBase registerAs(Enchantment enchant)
	{
		return OrderedRegistry.registerAs(enchant);
	}

}
