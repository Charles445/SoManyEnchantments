package com.Shultrea.Rin.Enchantments_Sector;



import com.Shultrea.Rin.Ench0_4_0.EnchantmentAdept;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentAncient_CurseInflicter;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentAtomicDeconstructor;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentBrutality;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentCurseofDecay;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentCurseofHolding;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentCurseofInaccuracy;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentCurseofPossession;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentCurseofVulnerability;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentDarkShadows;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentDifficultyScaled;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentDisarmament;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentFAtier;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentFieryShield;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentFlinging;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentFreezing;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentHors_de_combat;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentInhumanity;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentInnerBerserk;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentLightWeight;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentLuckMagnification;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentMagmaWalker;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentMeltdown;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentNaturalBlocking;
//import com.Shultrea.Rin.Ench0_4_0.EnchantmentPierceThrough;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentReinforcedSharpness;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentRune_ArrowPiercing;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentRune_Resurrection;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentRune_StarFall;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentScytheDamage;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentSplitshot;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentStrengthenedVitality;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentSubjectEnchantments;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentSwiper;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentTierDamage;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentTillingPower;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentUnderwaterStrider;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentUpgradedPotentials;
import com.Shultrea.Rin.Ench0_4_0.EnchantmentWellTilled;
import com.Shultrea.Rin.Ench0_4_0.Enchantmentadvancedmending;
import com.Shultrea.Rin.Ench0_4_0.Enchantmentflametier;
import com.Shultrea.Rin.Ench0_4_5.EnchantmentEvasion;
import com.Shultrea.Rin.Ench0_4_5.EnchantmentFrenzy;
import com.Shultrea.Rin.Ench0_4_5.EnchantmentInstability;
import com.Shultrea.Rin.Ench0_4_5.EnchantmentMastery;
import com.Shultrea.Rin.Ench0_4_5.EnchantmentPandora;
import com.Shultrea.Rin.Ench0_4_5.EnchantmentPulling;
import com.Shultrea.Rin.Ench0_4_5.EnchantmentPushing;
import com.Shultrea.Rin.Ench0_4_5.EnchantmentTrueStrike;
import com.Shultrea.Rin.Ench0_4_5.EnchantmentUnsheathing;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.Config;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Smc_040 {
    
	public static Config c = somanyenchantments.config;
	
	//public static Enchantment PierceThrough;
	public static Enchantment AncientCurseInflicter;
	/** The Rune Enchantments. */
	public static Enchantment Rune_Resurrection;
	public static Enchantment rune_starfall; //***
	public static Enchantment Rune_PiercingArrows;

	
	public static Enchantment Adept; //
	public static Enchantment AtomicDeconstructor; //
	public static Enchantment Brutality;
	public static Enchantment Disarmament; //
	
	public static Enchantment Mathematics; //
	public static Enchantment Science;     //
	public static Enchantment History;     //
	//public static Enchantment Physics;     //
    public static Enchantment English;     //
	public static Enchantment PE;          //
	
	public static Enchantment flinging;    //
	public static Enchantment sharperedge; //
	public static Enchantment Adjuster;
	public static Enchantment meltdown; //
	public static Enchantment invis;
	public static Enchantment WeaponBreaker; ///
	
	//Hoe
	public static Enchantment welltilled; //
	public static Enchantment TillingPower; //
	public static Enchantment scythedamage; //
	
	public static Enchantment Knockback;
	
	public static Enchantment freezing; //
	public static Enchantment Cryogenic;
	
	
	public static Enchantment LesserFireAspect;  //
	public static Enchantment AdvancedFireAspect; //
	public static Enchantment SupremeFireAspect; //
	
	public static Enchantment LesserSharpness; //
	public static Enchantment SupremeSharpness; //
	
	public static Enchantment LesserBaneOfArthropods; //
	public static Enchantment SupremeBaneOfArthropods; //
	
	public static Enchantment LesserSmite; //
	public static Enchantment SupremeSmite; //
	
	public static Enchantment LesserLooting;
	public static Enchantment SupremeLooting;
	
	public static Enchantment LesserKnockback;
	public static Enchantment SupremeKnockback;
	
	public static Enchantment LesserMending;
	public static Enchantment advancedmending; //
	public static Enchantment SupremeMending;
	
	public static Enchantment LesserPower;
	public static Enchantment SupremePower;
	
	public static Enchantment LesserPunch;
	public static Enchantment SupremePunch;
	
	public static Enchantment LesserRespiration;
	public static Enchantment AdvancedRespiration; //
	public static Enchantment SupremeRespiration;
	
	public static Enchantment CurseofPossession; //
	public static Enchantment CurseofDecay; //
	public static Enchantment CurseofVulnerability; //
	
	public static Enchantment fieryshield;      //
	public static Enchantment Fortress;
	public static Enchantment Bash;
	public static Enchantment Lighter;
	
	public static Enchantment Multifisher; //
	public static Enchantment Hors_de_combat; //
	
	public static Enchantment Scavenge;
	public static Enchantment splitshot; //
	
	public static Enchantment lesserflame; //
    public static Enchantment advancedflame; //
    public static Enchantment supremeflame; //
    
	public static Enchantment Swiper; //
	public static Enchantment strengthenedvitality; //
	public static Enchantment difficultyscaled; //
	public static Enchantment upgrade; //
	
	public static Enchantment lastWill;      //NYI
	public static Enchantment Overload;		 //NYI
	public static Enchantment BreakingPoint; //NYI
	
	public static Enchantment MagmaWalker; //
	public static Enchantment Inhumane;    //
	public static Enchantment NaturalBlocking; //
	public static Enchantment DarkShadows;
	public static Enchantment InnerBerserk;
	
	public static Enchantment CurseOfInaccuracy; 
	public static Enchantment CurseofHolding;
	public static Enchantment LuckMagnification;
	public static Enchantment LightWeight;
	public static Enchantment UnderwaterStrider;
	
	public static Enchantment Frenzy;
	public static Enchantment Pushing;
	public static Enchantment Evasion;
	public static Enchantment Pulling;
	public static Enchantment Instability;
	public static Enchantment Unsheathing;

	public static Enchantment EnchantmentMastery;

	public static Enchantment TrueStrike;
	
	public static Enchantment Pandora;
	
	public static void init(){
		 
	
	Mathematics = new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 0, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("Mathematics").setRegistryName("Mathematics");
	
	Science =     new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 1, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("Science").setRegistryName("Science");
	
	History =     new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 2, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("History").setRegistryName("History");
	
	//Physics =     new EnchantmentSubjectEnchantments(Rarity.VERY_RARE, 3, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("Physics").setRegistryName("Physics");
	English =     new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 4, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("English").setRegistryName("English");
	
	PE =          new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 5, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("PE").setRegistryName("PE");
		
	AtomicDeconstructor        = new EnchantmentAtomicDeconstructor();
	Disarmament                = new EnchantmentDisarmament();
	Hors_de_combat             = new EnchantmentHors_de_combat();
	SupremeSharpness           = new EnchantmentTierDamage(Rarity.VERY_RARE, EnumList.COMBAT, 1, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("SupremeSharpness").setRegistryName("SupremeSharpness");
	LesserSharpness            = new EnchantmentTierDamage(Rarity.COMMON, EnumList.COMBAT, 0, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("LesserSharpness").setRegistryName("LesserSharpness");
	LesserBaneOfArthropods     = new EnchantmentTierDamage(Rarity.COMMON, EnumList.COMBAT, 4, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("LesserBaneOfArthropods").setRegistryName("LesserBaneOfArthropods");
	SupremeBaneOfArthropods    = new EnchantmentTierDamage(Rarity.VERY_RARE, EnumList.COMBAT, 5, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("SupremeBaneOfArthropods").setRegistryName("SupremeBaneOfArthropods");
	LesserSmite                = new EnchantmentTierDamage(Rarity.COMMON, EnumList.COMBAT, 2, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("LesserSmite").setRegistryName("LesserSmite");
	SupremeSmite               = new EnchantmentTierDamage(Rarity.VERY_RARE, EnumList.COMBAT, 3, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("SupremeSmite").setRegistryName("SupremeSmite");
	
	//AdvancedRespiration        = new EnchantmentAdvancedRespiration();	
	CurseofPossession          = new EnchantmentCurseofPossession();
	
	SupremeFireAspect           = new EnchantmentFAtier(Rarity.VERY_RARE, EnumList.SWORD, 2, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("sfa").setRegistryName("sfa");
	AdvancedFireAspect          = new EnchantmentFAtier(Rarity.RARE, EnumList.SWORD, 1, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("afa").setRegistryName("afa");
	LesserFireAspect            = new EnchantmentFAtier(Rarity.COMMON, EnumList.SWORD, 0, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("lfa").setRegistryName("lfa");
	Swiper                      = new EnchantmentSwiper();
	freezing                    = new EnchantmentFreezing();
	advancedmending             = new Enchantmentadvancedmending();
	
	lesserflame                 = new Enchantmentflametier(Rarity.COMMON, EnumEnchantmentType.BOW,    0, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}).setName("lfl").setRegistryName("lfl");
	advancedflame               = new Enchantmentflametier(Rarity.VERY_RARE, EnumEnchantmentType.BOW, 1, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}).setName("afl").setRegistryName("afl");
    supremeflame                = new Enchantmentflametier(Rarity.VERY_RARE, EnumEnchantmentType.BOW, 2, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}).setName("sfl").setRegistryName("sfl");
    splitshot                   = new EnchantmentSplitshot();
    sharperedge                 = new EnchantmentReinforcedSharpness();
    Rune_Resurrection           = new EnchantmentRune_Resurrection();
    flinging                    = new EnchantmentFlinging();
    rune_starfall               = new EnchantmentRune_StarFall();
    strengthenedvitality        = new EnchantmentStrengthenedVitality();
    difficultyscaled            = new EnchantmentDifficultyScaled();
    meltdown                    = new EnchantmentMeltdown();
    welltilled                  = new EnchantmentWellTilled();
    upgrade                     = new EnchantmentUpgradedPotentials();
    scythedamage                = new EnchantmentScytheDamage();
    
    Adept  						= new EnchantmentAdept();
    CurseofDecay 				= new EnchantmentCurseofDecay();
    //PierceThrough 				= new EnchantmentPierceThrough();
    Brutality  					= new EnchantmentBrutality();
    MagmaWalker  			 	= new EnchantmentMagmaWalker();
    Inhumane					= new EnchantmentInhumanity();
    fieryshield                 = new EnchantmentFieryShield();
    NaturalBlocking				= new EnchantmentNaturalBlocking();
    DarkShadows					= new EnchantmentDarkShadows();
    
    CurseOfInaccuracy			= new EnchantmentCurseofInaccuracy();
    Rune_PiercingArrows 		= new EnchantmentRune_ArrowPiercing();
    InnerBerserk				= new EnchantmentInnerBerserk();
    
    AncientCurseInflicter		= new EnchantmentAncient_CurseInflicter();
    
    TillingPower				= new EnchantmentTillingPower();
    CurseofHolding				= new EnchantmentCurseofHolding();
    CurseofVulnerability		= new EnchantmentCurseofVulnerability();
    LuckMagnification			= new EnchantmentLuckMagnification();
    LightWeight					= new EnchantmentLightWeight();
    //Multifisher					= new EnchantmentMultifisher();
    UnderwaterStrider 			= new EnchantmentUnderwaterStrider();
    
    Frenzy 						= new EnchantmentFrenzy();
    
    Pushing 					= new EnchantmentPushing();
    Pulling 					= new EnchantmentPulling();
    Evasion 					= new EnchantmentEvasion();
    Instability					= new EnchantmentInstability();
    Unsheathing 				= new EnchantmentUnsheathing();
    EnchantmentMastery		 	= new EnchantmentMastery();
    TrueStrike					= new EnchantmentTrueStrike();
    Pandora 					= new EnchantmentPandora();
    
	}
	
	  @Mod.EventBusSubscriber
	    public static class EventSubscriber {

	        @SubscribeEvent
	        public static void registerEnchantment(RegistryEvent.Register<net.minecraft.enchantment.Enchantment> event) {
            
	        	event.getRegistry().register(Mathematics);
	        	event.getRegistry().register(Science);
	        	event.getRegistry().register(History);
	        	//event.getRegistry().register(Physics);
	        	event.getRegistry().register(English);
	        	event.getRegistry().register(PE);
	        	
	        	event.getRegistry().register(AtomicDeconstructor);
	        	event.getRegistry().register(Disarmament);
	        	event.getRegistry().register(Hors_de_combat);
	        	
	        	event.getRegistry().register(LesserSharpness);
	        	event.getRegistry().register(SupremeSharpness);
	        	event.getRegistry().register(LesserBaneOfArthropods);
	        	event.getRegistry().register(SupremeBaneOfArthropods);
	        	event.getRegistry().register(LesserSmite);
	        	event.getRegistry().register(SupremeSmite);
	        	
	        	//event.getRegistry().register(AdvancedRespiration);
	        	event.getRegistry().register(CurseofPossession);
	        	
	        	event.getRegistry().register(Swiper);
	        	
	        	event.getRegistry().register(LesserFireAspect);
	        	event.getRegistry().register(AdvancedFireAspect);
	        	event.getRegistry().register(SupremeFireAspect);
	        	event.getRegistry().register(freezing);
	        	event.getRegistry().register(advancedmending);
	        	
	        	event.getRegistry().register(lesserflame);
	        	event.getRegistry().register(advancedflame);
	        	event.getRegistry().register(supremeflame);
	        	event.getRegistry().register(splitshot);
	        	event.getRegistry().register(flinging);
	        	event.getRegistry().register(sharperedge);
	        	event.getRegistry().register(Rune_Resurrection);
	        	//event.getRegistry().register(rune_starfall);
	        	event.getRegistry().register(strengthenedvitality);
	        	event.getRegistry().register(difficultyscaled);
	        	event.getRegistry().register(meltdown);
	        	event.getRegistry().register(welltilled);
	        	event.getRegistry().register(upgrade);
	        	event.getRegistry().register(scythedamage);
	        	event.getRegistry().register(Adept);
	        	event.getRegistry().register(CurseofDecay);
	        	
	        	
	        	//event.getRegistry().register(PierceThrough);        	
	        	event.getRegistry().register(Brutality);
	        	event.getRegistry().register(MagmaWalker);
	        	event.getRegistry().register(Inhumane);
	        	event.getRegistry().register(fieryshield);
	        	event.getRegistry().register(NaturalBlocking);
	        	event.getRegistry().register(DarkShadows);
	        	event.getRegistry().register(CurseOfInaccuracy);
	        	event.getRegistry().register(Rune_PiercingArrows);
	        	event.getRegistry().register(InnerBerserk);
	        	event.getRegistry().register(TillingPower);
	        	event.getRegistry().register(CurseofHolding);
	        	event.getRegistry().register(CurseofVulnerability);
	        	event.getRegistry().register(LightWeight);
	        	event.getRegistry().register(LuckMagnification);
	        	
	        	event.getRegistry().register(AncientCurseInflicter);
	        	event.getRegistry().register(UnderwaterStrider);
	        	
	        	event.getRegistry().register(Frenzy);
	        	event.getRegistry().register(Evasion);
	        	event.getRegistry().register(Pushing);
	        	event.getRegistry().register(Pulling);
	        	event.getRegistry().register(Instability);
	        	event.getRegistry().register(Unsheathing);
	        	event.getRegistry().register(EnchantmentMastery);
	        	event.getRegistry().register(TrueStrike);
	        	event.getRegistry().register(Pandora);
	        }
	  }
    
	//Registers the forge event effects of an enchantment and not the enchantment.
	public static void enchHandler(){
		
		if(c.AtomicDeconstructor)
		MinecraftForge.EVENT_BUS.register(AtomicDeconstructor);
		
		if(c.Disarmament)
		MinecraftForge.EVENT_BUS.register(Disarmament);
		
		if(c.Hors_de_combat)
		MinecraftForge.EVENT_BUS.register(Hors_de_combat);
	    
		//MinecraftForge.EVENT_BUS.register(new EnchantmentSubjectEnchantments());
		
		//MinecraftForge.EVENT_BUS.register(AdvancedRespiration);
		
		if(c.CurseofPossession)
		MinecraftForge.EVENT_BUS.register(CurseofPossession);
		
		//if(c.Swiper)
		//MinecraftForge.EVENT_BUS.register(Swiper);
		
		if(c.Freezing)
		MinecraftForge.EVENT_BUS.register(freezing);
		
		if(c.AdvancedMending)
		MinecraftForge.EVENT_BUS.register(advancedmending);
		
		if(c.LesserFlame)
		MinecraftForge.EVENT_BUS.register(lesserflame);
		
		if(c.AdvancedFlame)
		MinecraftForge.EVENT_BUS.register(advancedflame);
		
		if(c.SupremeFlame)
		MinecraftForge.EVENT_BUS.register(supremeflame);
		
		if(c.SplitShot)
		MinecraftForge.EVENT_BUS.register(splitshot);
		
		if(c.Rune_Resurrection)
		MinecraftForge.EVENT_BUS.register(Rune_Resurrection);
		
		//MinecraftForge.EVENT_BUS.register(rune_starfall);
		
		if(c.StrengthenedVitality)
		MinecraftForge.EVENT_BUS.register(strengthenedvitality);
		
		//if(c.DifficultyScaled)
		//MinecraftForge.EVENT_BUS.register(difficultyscaled);
		
		if(c.WellTilled)
		MinecraftForge.EVENT_BUS.register(welltilled);
		
		if(c.Upgrade)
		MinecraftForge.EVENT_BUS.register(upgrade);
		
		if(c.Adept)
			MinecraftForge.EVENT_BUS.register(Adept);
		
		if(c.CurseofDecay)
			MinecraftForge.EVENT_BUS.register(CurseofDecay);
		
		//MinecraftForge.EVENT_BUS.register(PierceThrough);
		
		if(c.MagmaWalker)
			MinecraftForge.EVENT_BUS.register(MagmaWalker);
		
		if(c.FieryShield)
			MinecraftForge.EVENT_BUS.register(fieryshield);
		
	//	if(c.NaturalBlocking)
	//		MinecraftForge.EVENT_BUS.register(NaturalBlocking);
		
		if(c.DarkShadows)
			MinecraftForge.EVENT_BUS.register(DarkShadows);
		
		if(c.CurseOfInaccuracy)
			MinecraftForge.EVENT_BUS.register(CurseOfInaccuracy);
		
		//if(c.Rune_PiercingArrows)
		//	MinecraftForge.EVENT_BUS.register(Rune_PiercingArrows);
		
		if(c.InnerBerserk)
			MinecraftForge.EVENT_BUS.register(InnerBerserk);
	
		if(c.AncientCurseInflicter)
			MinecraftForge.EVENT_BUS.register(AncientCurseInflicter);
		
		if(c.TillingPower)
			MinecraftForge.EVENT_BUS.register(TillingPower);
		
		if(c.CurseofHolding)
			MinecraftForge.EVENT_BUS.register(CurseofHolding);
		
		if(c.CurseofVulnerability)
			MinecraftForge.EVENT_BUS.register(CurseofVulnerability);
			
		//if(c.LuckMagnification)
		//	MinecraftForge.EVENT_BUS.register(LuckMagnification);
		
		if(c.LightWeight)
			MinecraftForge.EVENT_BUS.register(LightWeight);
		
		if(c.UnderwaterStrider)
			MinecraftForge.EVENT_BUS.register(UnderwaterStrider);
		
		if(c.Frenzy)
			MinecraftForge.EVENT_BUS.register(Frenzy);
		
		if(c.Pushing)
			MinecraftForge.EVENT_BUS.register(Pushing);
		
		if(c.Evasion)
			MinecraftForge.EVENT_BUS.register(Evasion);
		
		if(c.Pulling)
			MinecraftForge.EVENT_BUS.register(Pulling);
		
		//if(c.Instability)
		//	MinecraftForge.EVENT_BUS.register(Instability);
		
		if(c.Unsheathing)
			MinecraftForge.EVENT_BUS.register(Unsheathing);
		
		if(c.Pandora)
			MinecraftForge.EVENT_BUS.register(Pandora);
		
		
		//MinecraftForge.EVENT_BUS.register(Multifisher);
	
	}
	
}