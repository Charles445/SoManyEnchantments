package com.Shultrea.Rin.Enchantments_Sector;


import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedBlastProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedFeatherFalling;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedFireProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentQuarrying;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedLuckOfTheSea;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedLure;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedProjectileProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedThorns;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAshDestroyer;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentBurningThorns;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentClearsky;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentCriticalStrike;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentDebug;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentDesolator;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentDisorientation;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentEmpoweredDefence;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentEnvenomed;
//import com.Shultrea.Rin.Ench0_3_0.EnchantmentExtremeFortune;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedLooting;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedPower;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentAdvancedPunch;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentLevitator;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentMagicProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentThunderstorm;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentViper;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentSunshine;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentWinter;
import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentMoonlight;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentPhysicalProtection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentPowerless;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentPurgingBlade;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentRaining;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentRune_Revival;
//import com.Shultrea.Rin.Ench0_3_0.EnchantmentRune_Resurrection;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentSmelter;
import com.Shultrea.Rin.Ench0_3_0.EnchantmentStrafe;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.ExtraEvent;
import com.Shultrea.Rin.Utility_Sector.LivingAttackFixerHandler;
import com.Shultrea.Rin.Utility_Sector.OtherHandler;
import com.Shultrea.Rin.Utility_Sector.RefStrings;
import com.Shultrea.Rin.Utility_Sector.SMElogM;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smc_030 {

	public static EnchantmentBase EmpoweredDefence;
	public static EnchantmentBase Clearsky;
	public static EnchantmentBase Moonlight;
	public static EnchantmentBase Raining;
	public static EnchantmentBase Sunshine;
	public static EnchantmentBase Thunderstorm;
	public static EnchantmentBase Winter;
	public static EnchantmentBase ExtremeFortune;
	public static EnchantmentBase Smelter;
	
	public static EnchantmentBase Strafe;
	public static EnchantmentBase AdvancedPower;
	public static EnchantmentBase CriticalStrike;
	public static EnchantmentBase AshDestroyer;
	public static EnchantmentBase Desolator;
	public static EnchantmentBase Disorientation;
	public static EnchantmentBase Envenomed;
	public static EnchantmentBase PurgingBlade;
	public static EnchantmentBase Viper;
	public static EnchantmentBase AdvancedLooting;
	public static EnchantmentBase Levitator;
	public static EnchantmentBase MagicProtection;
	public static EnchantmentBase PhysicalProtection;
	public static EnchantmentBase Debug;
	public static EnchantmentBase Powerless;
	public static EnchantmentBase AdvancedPunch;
	public static EnchantmentBase Rune_Revival;
    public static EnchantmentBase AdvancedLure;
    public static EnchantmentBase AdvancedLuckOfTheSea;
    public static EnchantmentBase AdvancedFeatherFalling;
    public static EnchantmentBase AdvancedThorns;
    public static EnchantmentBase BurningThorns;
    public static EnchantmentBase AdvancedProtection;
    public static EnchantmentBase AdvancedFireProtection;
    public static EnchantmentBase AdvancedBlastProtection;
    public static EnchantmentBase AdvancedProjectileProtection;
    public static EnchantmentBase Quarrying;
	
	public static void init()
	{
		Clearsky = registerAs(new EnchantmentClearsky());
		Moonlight = registerAs(new EnchantmentMoonlight());
		Raining = registerAs(new EnchantmentRaining());
		Sunshine = registerAs(new EnchantmentSunshine());
		Thunderstorm = registerAs(new EnchantmentThunderstorm());
		Winter = registerAs(new EnchantmentWinter());
		Smelter = registerAs(new EnchantmentSmelter());
		EmpoweredDefence = registerAs(new EnchantmentEmpoweredDefence());
		Strafe = registerAs(new EnchantmentStrafe());
		CriticalStrike = registerAs(new EnchantmentCriticalStrike());
		AdvancedLooting = registerAs(new EnchantmentAdvancedLooting());
		Levitator = registerAs(new EnchantmentLevitator());
		MagicProtection = registerAs(new EnchantmentMagicProtection());
		PhysicalProtection = registerAs(new EnchantmentPhysicalProtection());
		AshDestroyer = registerAs(new EnchantmentAshDestroyer());
		Desolator = registerAs(new EnchantmentDesolator());
		Disorientation = registerAs(new EnchantmentDisorientation());
		PurgingBlade = registerAs(new EnchantmentPurgingBlade());
		Viper = registerAs(new EnchantmentViper());
		AdvancedPower = registerAs(new EnchantmentAdvancedPower());
		Envenomed = registerAs(new EnchantmentEnvenomed());
		Powerless = registerAs(new EnchantmentPowerless());
		Rune_Revival = registerAs(new EnchantmentRune_Revival());
		AdvancedPunch = registerAs(new EnchantmentAdvancedPunch());
		AdvancedLure = registerAs(new EnchantmentAdvancedLure());
		AdvancedLuckOfTheSea = registerAs(new EnchantmentAdvancedLuckOfTheSea());
		AdvancedFeatherFalling = registerAs(new EnchantmentAdvancedFeatherFalling());
		AdvancedThorns = registerAs(new EnchantmentAdvancedThorns());
		BurningThorns = registerAs(new EnchantmentBurningThorns());
		AdvancedProtection = registerAs(new EnchantmentAdvancedProtection());
	    AdvancedFireProtection = registerAs(new EnchantmentAdvancedFireProtection());
	    AdvancedBlastProtection = registerAs(new EnchantmentAdvancedBlastProtection());
	    AdvancedProjectileProtection = registerAs(new EnchantmentAdvancedProjectileProtection());
	    Quarrying = registerAs(new EnchantmentQuarrying());
	}
	
	private static EnchantmentBase registerAs(Enchantment enchant)
	{
		return OrderedRegistry.registerAs(enchant);
	}
	
}