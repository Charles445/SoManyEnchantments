package com.Shultrea.Rin.Main_Sector;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Config(modid=somanyenchantments.MODID)
public class ModConfig
{
	@Config.Comment("Enabled Enchantments")
	@Config.Name("Enabled Enchantments")
	public static EnabledConfig enabled = new EnabledConfig();
	
	@Config.Comment("Miscellaneous")
	@Config.Name("Miscellaneous")
	public static MiscellaneousConfig miscellaneous = new MiscellaneousConfig();
	
	public static class MiscellaneousConfig
	{
		@Config.Comment("Fixes enchantment that doesn't deal damage when damage is 0 (E.g having weakness while hitting with a wood sword doesn't let the enchantment deal the damage because negative damage cancels damage calculation.)")
		@Config.Name("Fix Zero Damage Enchantments")
	    @Config.RequiresMcRestart
	    public boolean EnableFixEnchantment = false;
		
		@Config.Comment("Evasion Enchantment performs a dodge-like action to the Player. Disable if you don't like falling into cliff that is caused by Evasion Enchantment.")
		@Config.Name("Evasion Dodge Roll")
	    public boolean EvasionKnockback = true;

		@Config.Comment("Restrict Quarrying to Ore blocks only.")
		@Config.Name("Quarrying Ore Only")
	    @Config.RequiresMcRestart
	    public boolean QuarryingOreOnly = true;
		
		@Config.Comment("Allow Quarrying to work with blocks that are tile entities. I don't know what blocks are these but it may be useful for other mods...")
		@Config.Name("Quarrying Tile Entities")
	    public boolean harvestTile = true;
	}
	
	public static class EnabledConfig
	{
		//Enabled enchantments
		@Config.Name("Adept")
	    @Config.RequiresMcRestart
	    public boolean Adept = true; 
		
	    @Config.Name("Advanced Bane of Arthropods")
	    @Config.RequiresMcRestart
	    public boolean AdvancedBaneOfArthropodsEnable = true;
		
		@Config.Name("Advanced Blast Protection")
	    @Config.RequiresMcRestart
	    public boolean AdvancedBlastProtection = true;
		
		@Config.Name("Advanced Efficiency")
	    @Config.RequiresMcRestart
	    public boolean AdvancedEfficencyEnable = true;

		@Config.Name("Advanced Feather Falling")
	    @Config.RequiresMcRestart
	    public boolean AdvancedFeatherFalling = true;
		
		@Config.Name("Advanced Fire Aspect")
	    @Config.RequiresMcRestart
	    public boolean AdvancedFireAspect = true; 

		@Config.Name("Advanced Fire Protection")
	    @Config.RequiresMcRestart
	    public boolean AdvancedFireProtection = true;

		@Config.Name("Advanced Flame")
	    @Config.RequiresMcRestart
	    public boolean AdvancedFlame = true;
		
		@Config.Name("Advanced Knockback")
	    @Config.RequiresMcRestart
	    public boolean AdvancedKnockbackEnable = true;

		@Config.Name("Advanced Looting")
	    @Config.RequiresMcRestart
	    public boolean AdvancedLootingEnable = true;

		@Config.Name("Advanced Luck of the Sea")
	    @Config.RequiresMcRestart
	    public boolean AdvancedLuckOfTheSea = true;

		@Config.Name("Advanced Lure")
	    @Config.RequiresMcRestart
	    public boolean AdvancedLure = true;
		
		@Config.Name("Advanced Mending")
	    @Config.RequiresMcRestart
	    public boolean AdvancedMending = true;
		
		@Config.Name("Advanced Power")
	    @Config.RequiresMcRestart
	    public boolean AdvancedPowerEnable = true;

		@Config.Name("Advanced Projectile Protection")
	    @Config.RequiresMcRestart
	    public boolean AdvancedProjectileProtection = true;
		
		@Config.Name("Advanced Protection")
	    @Config.RequiresMcRestart
	    public boolean AdvancedProtection = true;
		
		@Config.Name("Advanced Punch")
	    @Config.RequiresMcRestart
		public boolean AdvancedPunch = true;
		
		@Config.Name("Advanced Sharpness")
	    @Config.RequiresMcRestart
	    public boolean AdvancedSharpnessEnable = true;
	    
	    @Config.Name("Advanced Smite")
	    @Config.RequiresMcRestart
	    public boolean AdvancedSmiteEnable = true;

		@Config.Name("Advanced Thorns")
	    @Config.RequiresMcRestart
	    public boolean AdvancedThorns = true;
		
		@Config.Name("Ancient Sealed Curses")
	    @Config.RequiresMcRestart
	    public boolean AncientCurseInflicter = true;
		
		@Config.Name("Ancient Sword Mastery")
	    @Config.RequiresMcRestart
	    public boolean AncientSwordMastery = true;

		@Config.Name("Arc Slash")
	    @Config.RequiresMcRestart
	    public boolean Swiper = true;

		@Config.Name("Ash Destroyer")
	    @Config.RequiresMcRestart
	    public boolean AshDestroyerEnable = true;
		
		@Config.Name("Atomic Deconstructor")
	    @Config.RequiresMcRestart
	    public boolean AtomicDeconstructor = true; 
		
		@Config.Name("Blessed Edge")
	    @Config.RequiresMcRestart
		public boolean BlessedEdgeEnable = true;
		
		@Config.Name("Bluntness")
	    @Config.RequiresMcRestart
	    public boolean BluntnessEnable = true;

		@Config.Name("Brutality")
	    @Config.RequiresMcRestart
	    public boolean Brutality = true;

		@Config.Name("Burning Shield")
	    @Config.RequiresMcRestart
	    public boolean FieryShield = true;

		@Config.Name("Burning Thorns")
	    @Config.RequiresMcRestart
	    public boolean BurningThorns = true;

		@Config.Name("Butchering")
	    @Config.RequiresMcRestart
	    public boolean ButcheringEnable = true;
		
		@Config.Name("Clearskies' Favor")
	    @Config.RequiresMcRestart
	    public boolean ClearskyEnable = true;
		
		@Config.Name("Combat Veterancy")
	    @Config.RequiresMcRestart
	    public boolean CombatRegenerationEnable = true;
		
		@Config.Name("Counter Attack")
	    @Config.RequiresMcRestart
	    public boolean CounterAttackEnable = true;

		@Config.Name("Critical Strike")
	    @Config.RequiresMcRestart
	    public boolean CriticalStrikeEnable = true;
		
		@Config.Name("Culling")
	    @Config.RequiresMcRestart
	    public boolean CullingEnable = true;

		@Config.Name("Curse of Decay")
	    @Config.RequiresMcRestart
	    public boolean CurseofDecay = true;
		
		@Config.Name("Curse of Holding")
	    @Config.RequiresMcRestart
	    public boolean CurseofHolding = true;

		@Config.Name("Curse of Inaccuracy")
	    @Config.RequiresMcRestart
	    public boolean CurseOfInaccuracy = true; 

		@Config.Name("Curse of Possession")
	    @Config.RequiresMcRestart
	    public boolean CurseofPossession = true;

		@Config.Name("Curse of Vulnerability")
	    @Config.RequiresMcRestart
	    public boolean CurseofVulnerability = true;
		
		@Config.Name("Cursed Edge")
	    @Config.RequiresMcRestart
	    public boolean CursedEdgeEnable = true;

		@Config.Name("Dark Shadows")
	    @Config.RequiresMcRestart
	    public boolean DarkShadows = true;
		
		@Config.Name("Defusing Edge")
	    @Config.RequiresMcRestart
	    public boolean DefusionEnable = true;

		@Config.Name("Desolator")
	    @Config.RequiresMcRestart
	    public boolean DesolatorEnable = true;
		
		@Config.Name("Difficulty's Endowment")
	    @Config.RequiresMcRestart
	    public boolean DifficultyScaled = true;

		@Config.Name("Disarmament")
	    @Config.RequiresMcRestart
	    public boolean Disarmament = true;   

		@Config.Name("Disorientating Blade")
	    @Config.RequiresMcRestart
	    public boolean DisorientationEnable = true;

		@Config.Name("Dragging")
	    @Config.RequiresMcRestart
	    public boolean Pulling = true;

		@Config.Name("Empowered Defence")
	    @Config.RequiresMcRestart
	    public boolean EmpoweredDefenceEnable = true;

		@Config.Name("Envenomed")
	    @Config.RequiresMcRestart
	    public boolean EnvenomedEnable = true;

		@Config.Name("Evasion")
	    @Config.RequiresMcRestart
	    public boolean Evasion = true;
		
		@Config.Name("Fiery Edge")
	    @Config.RequiresMcRestart
	    public boolean FieryEdgeEnable = true;

		@Config.Name("Flinging")
	    @Config.RequiresMcRestart
	    public boolean Flinging = true;    

		@Config.Name("Freezing")
	    @Config.RequiresMcRestart
	    public boolean Freezing = true; 
		
		@Config.Name("Heavy Weight")
	    @Config.RequiresMcRestart
	    public boolean HeavyWeight = true;

		@Config.Name("Hors De Combat")
	    @Config.RequiresMcRestart
	    public boolean Hors_de_combat = true;
		
		@Config.Name("Inefficient")
	    @Config.RequiresMcRestart
	    public boolean InefficentEnable = true;

		@Config.Name("Inhumane")
	    @Config.RequiresMcRestart
	    public boolean Inhumane = true;

		@Config.Name("Inner Berserk")
	    @Config.RequiresMcRestart
	    public boolean InnerBerserk = true;

		@Config.Name("Instability")
	    @Config.RequiresMcRestart
	    public boolean Instability = true;

		@Config.Name("Jagged Rake")
	    @Config.RequiresMcRestart
	    public boolean ScytheDamage = true; 

		@Config.Name("Lesser Bane of Arthropods")
	    @Config.RequiresMcRestart
	    public boolean LesserBaneOfArthropods = true; 

		@Config.Name("Lesser Fire Aspect")
	    @Config.RequiresMcRestart
	    public boolean LesserFireAspect = true;  

		@Config.Name("Lesser Flame")
	    @Config.RequiresMcRestart
	    public boolean LesserFlame = true;

		//@Config.Name("Lesser Looting")
	    //@Config.RequiresMcRestart
	    //public boolean LesserLooting = true;

		@Config.Name("Lesser Sharpness")
	    @Config.RequiresMcRestart
	    public boolean LesserSharpness = true; 

		@Config.Name("Lesser Smite")
	    @Config.RequiresMcRestart
	    public boolean LesserSmite = true; 

		@Config.Name("Levitator")
	    @Config.RequiresMcRestart
	    public boolean LevitatorEnable = true;
		
		@Config.Name("Lifesteal")
	    @Config.RequiresMcRestart
	    public boolean LifestealEnable = true;
		
		@Config.Name("Light Weight")
	    @Config.RequiresMcRestart
	    public boolean LightWeight = true;

		@Config.Name("Luck Magnification")
	    @Config.RequiresMcRestart
	    public boolean LuckMagnification = true;
		
		@Config.Name("Lunar's Blessing")
	    @Config.RequiresMcRestart
	    public boolean MoonlightEnable = true;

		@Config.Name("Magic Protection")
	    @Config.RequiresMcRestart
	    public boolean MagicProtectionEnable = true;

		@Config.Name("Magma Walker")
	    @Config.RequiresMcRestart
	    public boolean MagmaWalker = true;

		@Config.Name("Meltdown")
	    @Config.RequiresMcRestart
	    public boolean Meltdown = true; 

		@Config.Name("Moisturized")
	    @Config.RequiresMcRestart
	    public boolean WellTilled = true; 
		
		@Config.Name("Mortalitas")
	    @Config.RequiresMcRestart
	    public boolean Mortalitas = true;
		
		@Config.Name("Natural Blocking")
	    @Config.RequiresMcRestart
	    public boolean NaturalBlocking = true;

		@Config.Name("Pandora's Curse")
	    @Config.RequiresMcRestart
	    public boolean Pandora = true;
		
		@Config.Name("Parry")
	    @Config.RequiresMcRestart
	    public boolean ParryEnable = true;
		
		@Config.Name("Penetrating Edge")
	    @Config.RequiresMcRestart
	    public boolean PenetratingEdgeEnable = true;
		
		@Config.Name("Physical Protection")
	    @Config.RequiresMcRestart
		public boolean PhysicalProtectionEnable = true;

		@Config.Name("Plowing")
	    @Config.RequiresMcRestart
	    public boolean TillingPower = true; 

		@Config.Name("Powerless")
	    @Config.RequiresMcRestart
	    public boolean PowerlessEnable = true;

		@Config.Name("Purging Blade")
	    @Config.RequiresMcRestart
	    public boolean PurgingBladeEnable = true;
		
		@Config.Name("Purification")
	    @Config.RequiresMcRestart
	    public boolean PurificationEnable = true;
		
		@Config.Name("Pushing")
	    @Config.RequiresMcRestart
	    public boolean Pushing = true;

		@Config.Name("Quarrying")
	    @Config.RequiresMcRestart
	    public boolean Quarrying = true;
	    
		@Config.Name("Rain's Bestowment")
	    @Config.RequiresMcRestart
	    public boolean RainingEnable = true;

		@Config.Name("Reinforced Sharpness")
	    @Config.RequiresMcRestart
	    public boolean SharperEdge = true;
	    
		@Config.Name("Reviled Blade")
	    @Config.RequiresMcRestart
	    public boolean ReviledBladeEnable = true;
		
		@Config.Name("Rune: Arrow Piercing")
	    @Config.RequiresMcRestart
	    public boolean Rune_PiercingArrows = true;
		
		@Config.Name("Rune: Magical Blessing")
	    @Config.RequiresMcRestart
	    public boolean Rune_MagicalBlessingEnable = true;
		
		@Config.Name("Rune: Piercing Capabilities")
	    @Config.RequiresMcRestart
	    public boolean Rune_PiercingCapabilitiesEnable = true;

		@Config.Name("Rune: Resurrection")
	    @Config.RequiresMcRestart
	    public boolean Rune_Resurrection = true;

		@Config.Name("Rune: Revival")
	    @Config.RequiresMcRestart
	    public boolean RevivalEnable = true;
		
		@Config.Name("Rusted")
	    @Config.RequiresMcRestart
	    public boolean RustedEnable = true;

		@Config.Name("Smelter")
	    @Config.RequiresMcRestart
	    public boolean SmelterEnable = true;

		@Config.Name("Sol's Blessing")
	    @Config.RequiresMcRestart
	    public boolean SunshineEnable = true;
		
		@Config.Name("Spell Breaker")
	    @Config.RequiresMcRestart
	    public boolean SpellBreakerEnable = true;

		@Config.Name("Splitshot")
	    @Config.RequiresMcRestart
	    public boolean SplitShot = true;

		@Config.Name("Strafe")
	    @Config.RequiresMcRestart
	    public boolean StrafeEnable = true;

		@Config.Name("Strengthened Vitality")
	    @Config.RequiresMcRestart
	    public boolean StrengthenedVitality = true;

		@Config.Name("Subject English")
	    @Config.RequiresMcRestart
	    public boolean English = true;     

		@Config.Name("Subject History")
	    @Config.RequiresMcRestart
	    public boolean History = true;   

		@Config.Name("Subject Mathematics")
	    @Config.RequiresMcRestart
	    public boolean Mathematics = true;   

		@Config.Name("Subject P.E.")
	    @Config.RequiresMcRestart
	    public boolean PE = true;          

		@Config.Name("Subject Science")
	    @Config.RequiresMcRestart
	    public boolean Science = true;   

		@Config.Name("Supreme Bane of Arthropods")
	    @Config.RequiresMcRestart
	    public boolean SupremeBaneOfArthropods = true; 

		@Config.Name("Supreme Fire Aspect")
	    @Config.RequiresMcRestart
	    public boolean SupremeFireAspect = true; 

		@Config.Name("Supreme Flame")
	    @Config.RequiresMcRestart
	    public boolean SupremeFlame = true;

		//@Config.Name("Supreme Looting")
	    //@Config.RequiresMcRestart
	    //public boolean SupremeLooting = true;

		@Config.Name("Supreme Sharpness")
	    @Config.RequiresMcRestart
	    public boolean SupremeSharpness = true; 
		
		@Config.Name("Supreme Smite")
	    @Config.RequiresMcRestart
	    public boolean SupremeSmite = true; 
		
		@Config.Name("Swifter Slashes")
	    @Config.RequiresMcRestart
	    public boolean SwifterSlashesEnable = true;

		@Config.Name("Thunderstorm's Bestowment")
	    @Config.RequiresMcRestart
	    public boolean ThunderstormEnable = true;
		
		@Config.Name("True Strike")
	    @Config.RequiresMcRestart
	    public boolean TrueStrike = true;

		@Config.Name("Underwater Strider")
	    @Config.RequiresMcRestart
	    public boolean UnderwaterStrider = true;
		
		@Config.Name("Unpredictable")
	    @Config.RequiresMcRestart
	    public boolean UnpredictableEnable = true;

		@Config.Name("Unreasonable")
	    @Config.RequiresMcRestart
	    public boolean Frenzy = true;

		@Config.Name("Unsheathing")
	    @Config.RequiresMcRestart
	    public boolean Unsheathing = true;

		@Config.Name("Upgraded Potentials")
	    @Config.RequiresMcRestart
	    public boolean Upgrade = true;

		@Config.Name("Viper")
	    @Config.RequiresMcRestart
	    public boolean ViperEnable = true;
		
		@Config.Name("Water Aspect")
	    @Config.RequiresMcRestart
	    public boolean WaterAspectEnable = true;

		@Config.Name("Winter's Grace")
	    @Config.RequiresMcRestart
	    public boolean WinterEnable = true;
	}
	
	@Mod.EventBusSubscriber(modid = somanyenchantments.MODID)
	private static class EventHandler
	{
		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if(event.getModID().equals(somanyenchantments.MODID))
			{
				ConfigManager.sync(somanyenchantments.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
