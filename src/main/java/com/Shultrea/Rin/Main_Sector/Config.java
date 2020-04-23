package com.Shultrea.Rin.Main_Sector;

import net.minecraftforge.common.config.Configuration;

public class Config
{
   
   
	public final boolean AdvancedPunch;
	public final boolean PhysicalProtectionEnable;
	public final boolean BlessedEdgeEnable;
    public final boolean CursedEdgeEnable;
    public final boolean FieryEdgeEnable;
    public final boolean ButcheringEnable;
    public final boolean DefusionEnable;
    public final boolean PurificationEnable;
    public final boolean Rune_PiercingCapabilitiesEnable;
    public final boolean WaterAspectEnable;
    public final boolean SwifterSlashesEnable;
    public final boolean SpellBreakerEnable;
    public final boolean AdvancedSharpnessEnable;
    public final boolean AdvancedBaneOfArthropodsEnable;
    public final boolean AdvancedSmiteEnable;
    public final boolean ReviledBladeEnable;
    public final boolean AdvancedEfficencyEnable;
    public final boolean BluntnessEnable;
    public final boolean RustedEnable;
    public final boolean HeavyWeight;
    public final boolean InefficentEnable;
    
    public final boolean CombatRegenerationEnable;
    public final boolean CounterAttackEnable;
    public final boolean CullingEnable;
    public final boolean AdvancedKnockbackEnable;
    public final boolean LifestealEnable;
    public final boolean ParryEnable;
    public final boolean PenetratingEdgeEnable;
    public final boolean Rune_MagicalBlessingEnable;
    public final boolean UnpredictableEnable;
    public final boolean Mortalitas;
    
    //~Reminder~ To move this...
    
    public final boolean ClearskyEnable;
    public final boolean MoonlightEnable;
    public final boolean RainingEnable;
    public final boolean SunshineEnable;
    public final boolean ThunderstormEnable;
    public final boolean WinterEnable;
   // public final boolean Rune_RessurectionEnable;
    
    public final boolean SmelterEnable;
   // public final boolean ExtremeFortuneEnable;
    public final boolean EmpoweredDefenceEnable;
    public final boolean StrafeEnable;
    public final boolean AdvancedPowerEnable;
    public final boolean CriticalStrikeEnable;
    public final boolean AdvancedLootingEnable;
    public final boolean LevitatorEnable;
    public final boolean MagicProtectionEnable;
    public final boolean AshDestroyerEnable;
    public final boolean DesolatorEnable;
    public final boolean DisorientationEnable;
    public final boolean PurgingBladeEnable;
    public final boolean ViperEnable;
    public final boolean PowerlessEnable;
    public final boolean EnvenomedEnable;
	public final boolean RevivalEnable;
    public final boolean EnableFixEnchantment;
	public final boolean AdvancedLure;
	public final boolean AdvancedLuckOfTheSea;
	public final boolean AdvancedThorns;
	public final boolean BurningThorns;
	public final boolean AdvancedFeatherFalling;
	
	public final boolean AdvancedProtection;
	public final boolean AdvancedFireProtection;
	public final boolean AdvancedBlastProtection;
	public final boolean AdvancedProjectileProtection;
	public final boolean Quarrying;
	public final boolean QuarryingOreOnly;
	public final boolean harvestTile;
	
	public final boolean EvasionKnockback;
	
	

	public final boolean AncientCurseInflicter;
	public final boolean Rune_Resurrection;
	//public final boolean rune_starfall; 
	public final boolean Rune_PiercingArrows;
	public final boolean Adept; 
	public final boolean AtomicDeconstructor; 
	public final boolean Brutality;
	public final boolean Disarmament; 
	public final boolean Mathematics; 
	public final boolean Science;     
	public final boolean History;     
    public final boolean English;     
	public final boolean PE;          
	public final boolean Flinging;    
	public final boolean SharperEdge; 
	//public final boolean Adjuster;
	public final boolean Meltdown; 
	//public final boolean Invis;
	public final boolean WellTilled; 
	public final boolean TillingPower; 
	public final boolean ScytheDamage; 
	public final boolean Freezing; 
	public final boolean LesserFireAspect;  
	public final boolean AdvancedFireAspect; 
	public final boolean SupremeFireAspect; 
	public final boolean LesserSharpness; 
	public final boolean SupremeSharpness; 
	public final boolean LesserBaneOfArthropods; 
	public final boolean SupremeBaneOfArthropods; 
	public final boolean LesserSmite; 
	public final boolean SupremeSmite; 
	public final boolean LesserLooting;
	public final boolean SupremeLooting;
	public final boolean AdvancedMending; //
	public final boolean CurseofPossession; //
	public final boolean CurseofDecay; //
	public final boolean CurseofVulnerability; //
	public final boolean FieryShield;      //
	public final boolean Hors_de_combat; //
	public final boolean SplitShot; //
	public final boolean LesserFlame; //
    public final boolean AdvancedFlame; //
    public final boolean SupremeFlame; //
	public final boolean Swiper; //
	public final boolean StrengthenedVitality; //
	public final boolean DifficultyScaled; //
	public final boolean Upgrade; //
	public final boolean MagmaWalker; //
	public final boolean Inhumane;    //
	public final boolean NaturalBlocking; //
	public final boolean DarkShadows;
	public final boolean InnerBerserk;
	public final boolean CurseOfInaccuracy; 
	public final boolean CurseofHolding;
	public final boolean LuckMagnification;
	public final boolean LightWeight;
	public final boolean UnderwaterStrider;
	public final boolean Frenzy;
	public final boolean Pushing;
	public final boolean Evasion;
	public final boolean Pulling;
	public final boolean Instability;
	public final boolean Unsheathing;
	public final boolean AncientSwordMastery;
	public final boolean TrueStrike;
	public final boolean Pandora;
	
	public final String Category_Damage = "Damage Dealing Enchantments";
	public final String Category_Rune = "Rune Enchantments";
	public final String Category_Weather = "Weather Enchantments";
	public final String Category_Multiplier = "Damage Multiplying Enchantments";
	public final String Category_Potion = "Potion Inflicting Enchantments";
	public final String Category_Curse = "Curse Enchantments";
	public final String Category_Enhanced = "Vanilla Enhanced Enchantments";
	public final String Category_Variant = "Vanilla Variant Enchantments";
	public final String Category_Conditional = "Conditional Enchantments";
	public final String Category_Ancient = "Ancient Enchantments";
	public final String Category_Misc    = "Miscellaneous Enchantments";
	public final String Category_Extra    = "Extra Enchantments";
	public final String Category_Axe = "Axe-Restricted Enchantments";
	public final String Category_Pickaxe = "Axe-Restricted Enchantments";
	public final String Category_Hoe = "Axe-Restricted Enchantments";
	public final String Category_Shovel = "Axe-Restricted Enchantments";
	public final String Category_Shield = "Shield Enchantments";
	public final String Category_Helmet = "Helmet-Restricted Enchantments";
	public final String Category_Chest  = "Chestplate-Restricted Enchantments";
	public final String Category_Leg = "Leggings-Restricted Enchantments";
	public final String Category_Boots = "Boots-Restricted Enchantments";
	public final String Category_Bow = "Bow Enchantments";
	public final String Category_GreaterCurse = "Greater Curse Enchantments";
	

	
    public Config(Configuration config)
    {
        config.load();
        
        Mortalitas = config.get("Extra Damage Enchantment", "Is Mortalitas Enabled?", true).getBoolean(true);
        InefficentEnable = config.get(Category_Curse, "Is Inefficent Enabled?", true).getBoolean(true);
        HeavyWeight = config.get(Category_Curse, "Is Heavy Weight Enabled?", true).getBoolean(true);
        RustedEnable = config.get(Category_Curse, "Is Rusted Enabled?", true).getBoolean(true);
        BluntnessEnable = config.get(Category_Curse, "Is Bluntness Enabled?", true).getBoolean(true);
        CursedEdgeEnable = config.get(Category_Curse, "Is Cursed Edge Enabled?", true).getBoolean(true);
        PowerlessEnable = config.get(Category_Curse, "Is Powerless Enabled?", true).getBoolean(true);
        
        AdvancedEfficencyEnable = config.get("Main Tools Enchantment", "Is Advanced Efficency Enabled?", true).getBoolean(true);
        BlessedEdgeEnable = config.get("Extra Weapon Enchantment", "Is Blessed Edge Enabled?", true).getBoolean(true);
        FieryEdgeEnable = config.get("Main Weapon Enchantment", "Is Fiery Edge Enabled?", true).getBoolean(true);
        AdvancedSharpnessEnable = config.get("Main Weapon Enchantment", "Is Advanced Sharpness Enabled?", true).getBoolean(true);
        AdvancedBaneOfArthropodsEnable = config.get("Main Weapon Enchantment", "Is Advanced Bane of Arthropods Enabled?", true).getBoolean(true);
        AdvancedSmiteEnable = config.get("Main Weapon Enchantment", "Is Advanced Smite Enabled?", true).getBoolean(true);
        WaterAspectEnable = config.get("Main Weapon Enchantment", "Is Water Aspect Enabled?", true).getBoolean(true);
        ReviledBladeEnable = config.get("Extra Weapon Enchantment", "Is Reviled Blade Enabled?", true).getBoolean(true);
        SpellBreakerEnable = config.get("Main Weapon Enchantment", "Is Spell Breaker Enabled?", true).getBoolean(true);
        DefusionEnable = config.get("Main Weapon Enchantment", "Is Defusion Enabled?", true).getBoolean(true);
        Rune_PiercingCapabilitiesEnable = config.get("Enchantment Runes", "Is Rune: Piercing Capabilities Enabled?", true).getBoolean(true);
        ButcheringEnable = config.get("Main Weapon Enchantment", "Is Butchering Enabled?", true).getBoolean(true);
        PurificationEnable = config.get("Main Weapon Enchantment", "Is Purification Edge Enabled?", true).getBoolean(true);
        SwifterSlashesEnable = config.get("Extra Weapon Enchantment", "Is Swifter Slashes Enabled?", true).getBoolean(true);
        
        CombatRegenerationEnable = config.get("Extra Weapon Enchantment", "Is Combat Regeneration Enabled?", true).getBoolean(true);
        CounterAttackEnable = config.get("Extra Weapon Enchantment", "Is Counter Attack Enabled?", true).getBoolean(true);
        CullingEnable = config.get("Axe Restricted Enchantment", "Is Culling Enabled?", true).getBoolean(true);
        AdvancedKnockbackEnable = config.get("Extra Weapon Enchantment", "Is Extreme Knockback Enabled?", true).getBoolean(true);
        LifestealEnable = config.get("Extra Weapon Enchantment", "Is Lifesteal Enabled?", true).getBoolean(true);
        ParryEnable = config.get("Extra Weapon Enchantment", "Is Parry Enabled?", true).getBoolean(true);
        PenetratingEdgeEnable = config.get("Axe Restricted Enchantment", "Is Penetrating Edge Enabled?", true).getBoolean(true);
        Rune_MagicalBlessingEnable = config.get("Enchantment Runes", "Is Rune: Magical Blessing Enabled?", true).getBoolean(true);
        UnpredictableEnable = config.get("Extra Weapon Enchantment", "Is Unpredictable Enabled?", true).getBoolean(true);
        //Reminder
        
        ClearskyEnable = config.get("Weather Enchantments", "Is Clearsky Enabled?", true).getBoolean(true);
        MoonlightEnable = config.get("Day and Night Enchantments", "Is Moonlight Enabled?", true).getBoolean(true);
        RainingEnable = config.get("Weather Enchantments", "Is Raining Enabled?", true).getBoolean(true);
        SunshineEnable = config.get("Day and Night Enchantments", "Is Sunshine Enabled?", true).getBoolean(true);
        ThunderstormEnable = config.get("Weather Enchantments", "Is Thunderstorm Enabled?", true).getBoolean(true);
        WinterEnable = config.get("Weather Enchantments", "Is Winter Enabled?", true).getBoolean(true);
        
        EmpoweredDefenceEnable = config.get("Main Shield Enchantment", "Is Empowered Defence Enabled?", true).getBoolean(true);
        SmelterEnable = config.get("Extra Utility Enchantment", "Is Smelter Enabled?", true).getBoolean(true);
        //ExtremeFortuneEnable = config.get("Main Utility Enchantments", "Is Extreme Fortune Enabled?", true).getBoolean(true);
        StrafeEnable = config.get("Extra Bow Enchantment", "Is Strafe Enabled?", true).getBoolean(true);
        AdvancedPowerEnable = config.get("Main Bow Enchantment", "Is Extreme Power Enabled?", true).getBoolean(true);
        CriticalStrikeEnable = config.get("Extra Weapon Enchantment", "Is Critical Strike Enabled?", true).getBoolean(true);
        //Rune_RessurectionEnable = config.get("Enchantment Runes", "Is Rune: Ressurection Enabled?", true).getBoolean(true);
        AdvancedLootingEnable = config.get("Main Utility Enchantment", "Is Extreme Looting Enabled?", true).getBoolean(true);
        LevitatorEnable = config.get("Extra Weapon Enchantment", "Is Levitator Enabled?", true).getBoolean(true);
        MagicProtectionEnable = config.get("Main Armor Enchantment", "Is Magic Protection Enabled?", true).getBoolean(true);
        PhysicalProtectionEnable = config.get("Extra Armor Enchantment", "Is Physical Protection Enabled?", true).getBoolean(true);
        
        AshDestroyerEnable = config.get("Extra Weapon Enchantment", "Is Ash Destroyer Enabled?", true).getBoolean(true);
        DesolatorEnable = config.get("Axe Restricted Enchantment", "Is Desolator Enabled?", true).getBoolean(true);
        DisorientationEnable = config.get("Extra Weapon Enchantment", "Is Disorienting Blade Enabled?", true).getBoolean(true);
        PurgingBladeEnable = config.get("Extra Weapon Enchantment", "Is Purging Blade Enabled?", true).getBoolean(true);
        ViperEnable = config.get("Extra Weapon Enchantment", "Is Viper Enabled?", true).getBoolean(true);
        EnvenomedEnable = config.get("Extra Weapon Enchantment", "Is Envenomed Enabled?", true).getBoolean(true);

        RevivalEnable = config.get("Enchantment Runes", "Is Rune: Revival Enabled?", true).getBoolean(true);
        AdvancedPunch = config.get("Main Bow Enchantment", "Is Extreme Punch Enabled?", true).getBoolean(true);
        EnableFixEnchantment = config.getBoolean("Fix Most of the Enchantments not functioning when damage is negative? ", "Additional Configurations", false, "Fixes enchantment that doesn't deal damage when damage is 0 (E.g having weakness while hitting with a wood sword doesn't let the enchantment deal the damage because negative damage cancels damage calculation.)");
        AdvancedLure = config.get("Main Fishing Rod Enchantment", "Is Advanced Lure Enabled?", true).getBoolean(true);
        AdvancedLuckOfTheSea = config.get("Main Fishing Rod Enchantment", "Is Advanced Luck Of The Sea Enabled?", true).getBoolean(true);
        AdvancedThorns = config.get("Main Armor Enchantment", "Is Advanced Thorns Enabled", true).getBoolean(true);
        BurningThorns = config.get("Main Armor Enchantment", "Is Burning Thorns Enabled", true).getBoolean(true);
        AdvancedFeatherFalling = config.get("Main Armor Enchantment", "Is Advanced Feather Falling Enabled", true).getBoolean(true);
        
        AdvancedProtection = config.get("Main Armor Enchantment", "Is Advanced Protection Enabled", true).getBoolean(true);
        AdvancedProjectileProtection = config.get("Main Armor Enchantment", "Is Advanced Projectile Enabled", true).getBoolean(true);
        AdvancedBlastProtection = config.get("Main Armor Enchantment", "Is Advanced Blast Protection Enabled", true).getBoolean(true);
        AdvancedFireProtection = config.get("Main Armor Enchantment", "Is Advanced Fire Protection Enabled", true).getBoolean(true);
        
        
        Quarrying = config.get(Category_Variant, "Is Quarrying Enabled", true).getBoolean(true);
        QuarryingOreOnly = config.get("Misc", "Restrict Quarrying to Ore blocks only.", true).getBoolean(true);
        harvestTile = config.get("Misc", "Disables Quarrying from working with blocks that are tile entities. I don't know what blocks are these but it may be useful for other mods...", true).getBoolean(true);
        EvasionKnockback = config.get("Misc", "Disables the Evasion Enchantment from performing a dodge-like action to the Player. Disable if you don't like falling into cliff that is caused by Evasion Enchantment.", false).getBoolean(false);
            
   	 	AncientCurseInflicter 	 = config.get(Category_Ancient, "Is Ancient Curse Inflicter Enabled", true).getBoolean(true);
   	 	Rune_Resurrection	 	 = config.get(Category_Rune, "Is Ancient Curse Inflicter Enabled", true).getBoolean(true);
   		Rune_PiercingArrows  	 = config.get(Category_Ancient, "Is Rune: Piercing Arrows Enabled", true).getBoolean(true);
   		Adept				 	 = config.get(Category_Variant, "Is Adept Enabled", true).getBoolean(true);
   		AtomicDeconstructor  	 = config.get(Category_Misc, "Is Atomic Deconstructor Enabled", true).getBoolean(true);
   		Brutality            	 = config.get(Category_Extra, "Is Ancient Curse Inflicter Enabled", true).getBoolean(true);
   		Disarmament          	 = config.get(Category_Extra, "Is Ancient Curse Inflicter Enabled", true).getBoolean(true);
   		Mathematics          	 = config.get("Subject Enchantments", "Is Mathematics Enabled", true).getBoolean(true);
   		Science					 = config.get("Subject Enchantments", "Is Science Enabled", true).getBoolean(true);     
   		History					 = config.get("Subject Enchantments", "Is History Enabled", true).getBoolean(true);   
   		English				  	 = config.get("Subject Enchantments", "Is English Enabled", true).getBoolean(true);          
   		PE				    	 = config.get("Subject Enchantments", "Is Physical Education (P.E.) Enabled", true).getBoolean(true);   
   		Flinging				 = config.get(Category_Variant, "Is Flinging Enabled", true).getBoolean(true);   
   		SharperEdge         	 = config.get(Category_Variant, "Is Sharper Edge Enabled", true).getBoolean(true);        
   		Meltdown             	 = config.get(Category_Variant, "Is Meltdown Enabled", true).getBoolean(true);
   		WellTilled				 = config.get(Category_Hoe, "Is Moisturized Enabled", true).getBoolean(true);
   		TillingPower			 = config.get(Category_Hoe, "Is Plowing Enabled", true).getBoolean(true); 
   		ScytheDamage			 = config.get(Category_Hoe, "Is Plowing Enabled", true).getBoolean(true); 
   		Freezing				 = config.get(Category_Potion, "Is Plowing Enabled", true).getBoolean(true); 
   		LesserFireAspect		 = config.get(Category_Enhanced, "Is Lesser Fire Aspect Enabled", true).getBoolean(true); 
   		AdvancedFireAspect	 	 = config.get(Category_Enhanced, "Is Advanced Fire Aspect Enabled", true).getBoolean(true); 
   		SupremeFireAspect	  	 = config.get(Category_Enhanced, "Is Supreme Fire Aspect Enabled", true).getBoolean(true); 
   		LesserSharpness		  	 = config.get(Category_Enhanced, "Is Lesser Sharpness Enabled", true).getBoolean(true); 
   		SupremeSharpness	     = config.get(Category_Enhanced, "Is Supreme Sharpness Enabled", true).getBoolean(true); 
   		LesserBaneOfArthropods	 = config.get(Category_Enhanced, "Is Lesser Bane of Arthropods Enabled", true).getBoolean(true); 
   		SupremeBaneOfArthropods	 = config.get(Category_Enhanced, "Is Supreme Bane of Arthropods Enabled", true).getBoolean(true); 
   		LesserSmite				 = config.get(Category_Enhanced, "Is Lesser Smite Enabled", true).getBoolean(true); 
   		SupremeSmite			 = config.get(Category_Enhanced, "Is Supreme Smite Enabled", true).getBoolean(true); 
   		LesserLooting			 = config.get(Category_Enhanced, "Is Lesser Looting Enabled", true).getBoolean(true); 
   		SupremeLooting			 = config.get(Category_Enhanced, "Is Supreme Looting Enabled", true).getBoolean(true); 
   		AdvancedMending	 		 = config.get(Category_Enhanced, "Is Advanced Mending Enabled", true).getBoolean(true); 
   		CurseofPossession		 = config.get(Category_Curse, "Is Curse of Possession Enabled", true).getBoolean(true); 
   		CurseofDecay			 = config.get(Category_Curse, "Is Curse of Decay Enabled", true).getBoolean(true); 
   		CurseofVulnerability	 = config.get(Category_Curse, "Is Curse of Vulnerability Enabled", true).getBoolean(true); 
   		FieryShield				 = config.get(Category_Shield, "Is Fiery Shield Enabled", true).getBoolean(true);     
   		Hors_de_combat			 = config.get(Category_Shield, "Is Hors de Combat Enabled", true).getBoolean(true);
   		SplitShot				 = config.get(Category_Bow, "Is Split Shot Enabled", true).getBoolean(true);
   		LesserFlame				 = config.get(Category_Enhanced, "Is Lesser Flame Enabled", true).getBoolean(true);
   		AdvancedFlame			 = config.get(Category_Enhanced, "Is Advanced Flame Enabled", true).getBoolean(true);
   		SupremeFlame			 = config.get(Category_Enhanced, "Is Supreme Flame Enabled", true).getBoolean(true);
   		Swiper					 = config.get(Category_Enhanced, "Is Arc Slash Enabled", true).getBoolean(true);
   		StrengthenedVitality	 = config.get(Category_Chest, "Is Strengthened Vitality Enabled", true).getBoolean(true);
   		DifficultyScaled		 = config.get(Category_Multiplier, "Is Difficulty Scaled Enabled", true).getBoolean(true);
   		Upgrade					 = config.get(Category_Extra, "Is Upgraded Potentials Enabled", true).getBoolean(true);
   		MagmaWalker				 = config.get(Category_Variant, "Is Magma Walker Enabled", true).getBoolean(true);
   		Inhumane				 = config.get(Category_Damage, "Is Inhumane Enabled", true).getBoolean(true);   
   		NaturalBlocking			 = config.get(Category_Shield, "Is Natural Blocking Enabled", true).getBoolean(true);
   		DarkShadows				 = config.get(Category_Extra, "Is Dark Shadows Enabled", true).getBoolean(true);
   		InnerBerserk			 = config.get(Category_Chest, "Is Inner Berserk Enabled", true).getBoolean(true);
   		CurseOfInaccuracy		 = config.get(Category_Curse, "Is Curse of Inaccuracy Enabled", true).getBoolean(true);
   		CurseofHolding			 = config.get(Category_Curse, "Is Curse of Holding Enabled", true).getBoolean(true);
   		LuckMagnification		 = config.get(Category_Extra, "Is Luck Magnification Enabled", true).getBoolean(true);
   		LightWeight 			 = config.get(Category_Boots, "Is Light Weight Enabled", true).getBoolean(true);
   		
   		UnderwaterStrider        = config.get(Category_Variant,    "Is Underwater Strider Enabled", true).getBoolean(true);
   		Frenzy					 = config.get(Category_Extra, 	   "Is Frenzy Enabled", true).getBoolean(true);
   		Pushing					 = config.get(Category_Bow,    	   "Is Pushing Enabled", true).getBoolean(true);
   		Evasion					 = config.get(Category_Leg, 	   "Is Evasion Enabled", true).getBoolean(true);
   		Pulling 				 = config.get(Category_Bow, 	   "Is Pulling Enabled", true).getBoolean(true);
   		Instability				 = config.get(Category_Multiplier, "Is Instability Enabled", true).getBoolean(true);
   		Unsheathing				 = config.get(Category_Extra, "Is Unsheathing Enabled", true).getBoolean(true);
   		AncientSwordMastery      = config.get(Category_Ancient, "Is Ancient Sword Mastery Enabled", true).getBoolean(true);
   		TrueStrike      		 = config.get(Category_Extra, "Is True Strike Enabled", true).getBoolean(true);
   		Pandora      		     = config.get(Category_GreaterCurse, "Is Pandora's Curse Enabled", true).getBoolean(true);
   		
        if(config.hasChanged())
        {
            config.save();
        }
    }
}