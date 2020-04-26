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
import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Main_Sector.ModConfig;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Smc_040 {
	
	//public static Enchantment PierceThrough;
	public static EnchantmentBase AncientCurseInflicter;
	/** The Rune Enchantments. */
	public static EnchantmentBase Rune_Resurrection;
	public static EnchantmentBase rune_starfall; //***
	public static EnchantmentBase Rune_PiercingArrows;

	
	public static EnchantmentBase Adept; //
	public static EnchantmentBase AtomicDeconstructor; //
	public static EnchantmentBase Brutality;
	public static EnchantmentBase Disarmament; //
	
	public static EnchantmentBase Mathematics; //
	public static EnchantmentBase Science;     //
	public static EnchantmentBase History;     //
	//public static Enchantment Physics;     //
    public static EnchantmentBase English;     //
	public static EnchantmentBase PE;          //
	
	public static EnchantmentBase flinging;    //
	public static EnchantmentBase sharperedge; //
	public static EnchantmentBase Adjuster;
	public static EnchantmentBase meltdown; //
	public static EnchantmentBase invis;
	public static EnchantmentBase WeaponBreaker; ///
	
	//Hoe
	public static EnchantmentBase welltilled; //
	public static EnchantmentBase TillingPower; //
	public static EnchantmentBase scythedamage; //
	
	public static EnchantmentBase Knockback;
	
	public static EnchantmentBase freezing; //
	public static EnchantmentBase Cryogenic;
	
	
	public static EnchantmentBase LesserFireAspect;  //
	public static EnchantmentBase AdvancedFireAspect; //
	public static EnchantmentBase SupremeFireAspect; //
	
	public static EnchantmentBase LesserSharpness; //
	public static EnchantmentBase SupremeSharpness; //
	
	public static EnchantmentBase LesserBaneOfArthropods; //
	public static EnchantmentBase SupremeBaneOfArthropods; //
	
	public static EnchantmentBase LesserSmite; //
	public static EnchantmentBase SupremeSmite; //
	
	public static EnchantmentBase LesserLooting;
	public static EnchantmentBase SupremeLooting;
	
	public static EnchantmentBase LesserKnockback;
	public static EnchantmentBase SupremeKnockback;
	
	public static EnchantmentBase LesserMending;
	public static EnchantmentBase advancedmending; //
	public static EnchantmentBase SupremeMending;
	
	public static EnchantmentBase LesserPower;
	public static EnchantmentBase SupremePower;
	
	public static EnchantmentBase LesserPunch;
	public static EnchantmentBase SupremePunch;
	
	public static EnchantmentBase LesserRespiration;
	public static EnchantmentBase AdvancedRespiration; //
	public static EnchantmentBase SupremeRespiration;
	
	public static EnchantmentBase CurseofPossession; //
	public static EnchantmentBase CurseofDecay; //
	public static EnchantmentBase CurseofVulnerability; //
	
	public static EnchantmentBase fieryshield;      //
	public static EnchantmentBase Fortress;
	public static EnchantmentBase Bash;
	public static EnchantmentBase Lighter;
	
	public static EnchantmentBase Multifisher; //
	public static EnchantmentBase Hors_de_combat; //
	
	public static EnchantmentBase Scavenge;
	public static EnchantmentBase splitshot; //
	
	public static EnchantmentBase lesserflame; //
    public static EnchantmentBase advancedflame; //
    public static EnchantmentBase supremeflame; //
    
	public static EnchantmentBase Swiper; //
	public static EnchantmentBase strengthenedvitality; //
	public static EnchantmentBase difficultyscaled; //
	public static EnchantmentBase upgrade; //
	
	public static EnchantmentBase lastWill;      //NYI
	public static EnchantmentBase Overload;		 //NYI
	public static EnchantmentBase BreakingPoint; //NYI
	
	public static EnchantmentBase MagmaWalker; //
	public static EnchantmentBase Inhumane;    //
	public static EnchantmentBase NaturalBlocking; //
	public static EnchantmentBase DarkShadows;
	public static EnchantmentBase InnerBerserk;
	
	public static EnchantmentBase CurseOfInaccuracy; 
	public static EnchantmentBase CurseofHolding;
	public static EnchantmentBase LuckMagnification;
	public static EnchantmentBase LightWeight;
	public static EnchantmentBase UnderwaterStrider;
	
	public static EnchantmentBase Frenzy;
	public static EnchantmentBase Pushing;
	public static EnchantmentBase Evasion;
	public static EnchantmentBase Pulling;
	public static EnchantmentBase Instability;
	public static EnchantmentBase Unsheathing;

	public static EnchantmentBase EnchantmentMastery;

	public static EnchantmentBase TrueStrike;
	
	public static EnchantmentBase Pandora;
	
	public static void init(){
		 
	
	Mathematics = registerAs(new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 0, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("Mathematics").setRegistryName("Mathematics"));
	
	Science =     registerAs(new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 1, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("Science").setRegistryName("Science"));
	
	History =     registerAs(new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 2, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("History").setRegistryName("History"));
	
	//Physics =     new EnchantmentSubjectEnchantments(Rarity.VERY_RARE, 3, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("Physics").setRegistryName("Physics");
	English =     registerAs(new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 4, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("English").setRegistryName("English"));
	
	PE =          registerAs(new EnchantmentSubjectEnchantments(Rarity.RARE, EnumList.SWORD, 5, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("PE").setRegistryName("PE"));
		
	AtomicDeconstructor        = registerAs(new EnchantmentAtomicDeconstructor());
	Disarmament                = registerAs(new EnchantmentDisarmament());
	Hors_de_combat             = registerAs(new EnchantmentHors_de_combat());
	SupremeSharpness           = registerAs(new EnchantmentTierDamage(Rarity.VERY_RARE, EnumList.COMBAT, 1, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("SupremeSharpness").setRegistryName("SupremeSharpness"));
	LesserSharpness            = registerAs(new EnchantmentTierDamage(Rarity.COMMON, EnumList.COMBAT, 0, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("LesserSharpness").setRegistryName("LesserSharpness"));
	LesserBaneOfArthropods     = registerAs(new EnchantmentTierDamage(Rarity.COMMON, EnumList.COMBAT, 4, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("LesserBaneOfArthropods").setRegistryName("LesserBaneOfArthropods"));
	SupremeBaneOfArthropods    = registerAs(new EnchantmentTierDamage(Rarity.VERY_RARE, EnumList.COMBAT, 5, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("SupremeBaneOfArthropods").setRegistryName("SupremeBaneOfArthropods"));
	LesserSmite                = registerAs(new EnchantmentTierDamage(Rarity.COMMON, EnumList.COMBAT, 2, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("LesserSmite").setRegistryName("LesserSmite"));
	SupremeSmite               = registerAs(new EnchantmentTierDamage(Rarity.VERY_RARE, EnumList.COMBAT, 3, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("SupremeSmite").setRegistryName("SupremeSmite"));
	
	//AdvancedRespiration        = new EnchantmentAdvancedRespiration();	
	CurseofPossession          = registerAs(new EnchantmentCurseofPossession());
	
	SupremeFireAspect           = registerAs(new EnchantmentFAtier(Rarity.VERY_RARE, EnumList.SWORD, 2, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("sfa").setRegistryName("sfa"));
	AdvancedFireAspect          = registerAs(new EnchantmentFAtier(Rarity.RARE, EnumList.SWORD, 1, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("afa").setRegistryName("afa"));
	LesserFireAspect            = registerAs(new EnchantmentFAtier(Rarity.COMMON, EnumList.SWORD, 0, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND}).setName("lfa").setRegistryName("lfa"));
	Swiper                      = registerAs(new EnchantmentSwiper());
	freezing                    = registerAs(new EnchantmentFreezing());
	advancedmending             = registerAs(new Enchantmentadvancedmending());
	
	lesserflame                 = registerAs(new Enchantmentflametier(Rarity.COMMON, EnumEnchantmentType.BOW,    0, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}).setName("lfl").setRegistryName("lfl"));
	advancedflame               = registerAs(new Enchantmentflametier(Rarity.VERY_RARE, EnumEnchantmentType.BOW, 1, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}).setName("afl").setRegistryName("afl"));
    supremeflame                = registerAs(new Enchantmentflametier(Rarity.VERY_RARE, EnumEnchantmentType.BOW, 2, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND}).setName("sfl").setRegistryName("sfl"));
    splitshot                   = registerAs(new EnchantmentSplitshot());
    sharperedge                 = registerAs(new EnchantmentReinforcedSharpness());
    Rune_Resurrection           = registerAs(new EnchantmentRune_Resurrection());
    flinging                    = registerAs(new EnchantmentFlinging());
    //rune_starfall               = registerAs(new EnchantmentRune_StarFall());
    strengthenedvitality        = registerAs(new EnchantmentStrengthenedVitality());
    difficultyscaled            = registerAs(new EnchantmentDifficultyScaled());
    meltdown                    = registerAs(new EnchantmentMeltdown());
    welltilled                  = registerAs(new EnchantmentWellTilled());
    upgrade                     = registerAs(new EnchantmentUpgradedPotentials());
    scythedamage                = registerAs(new EnchantmentScytheDamage());
    
    Adept  						= registerAs(new EnchantmentAdept());
    CurseofDecay 				= registerAs(new EnchantmentCurseofDecay());
    //PierceThrough 				= new EnchantmentPierceThrough();
    Brutality  					= registerAs(new EnchantmentBrutality());
    MagmaWalker  			 	= registerAs(new EnchantmentMagmaWalker());
    Inhumane					= registerAs(new EnchantmentInhumanity());
    fieryshield                 = registerAs(new EnchantmentFieryShield());
    NaturalBlocking				= registerAs(new EnchantmentNaturalBlocking());
    DarkShadows					= registerAs(new EnchantmentDarkShadows());
    
    CurseOfInaccuracy			= registerAs(new EnchantmentCurseofInaccuracy());
    Rune_PiercingArrows 		= registerAs(new EnchantmentRune_ArrowPiercing());
    InnerBerserk				= registerAs(new EnchantmentInnerBerserk());
    
    AncientCurseInflicter		= registerAs(new EnchantmentAncient_CurseInflicter());
    
    TillingPower				= registerAs(new EnchantmentTillingPower());
    CurseofHolding				= registerAs(new EnchantmentCurseofHolding());
    CurseofVulnerability		= registerAs(new EnchantmentCurseofVulnerability());
    LuckMagnification			= registerAs(new EnchantmentLuckMagnification());
    LightWeight					= registerAs(new EnchantmentLightWeight());
    //Multifisher					= new EnchantmentMultifisher();
    UnderwaterStrider 			= registerAs(new EnchantmentUnderwaterStrider());
    
    Frenzy 						= registerAs(new EnchantmentFrenzy());
    
    Pushing 					= registerAs(new EnchantmentPushing());
    Pulling 					= registerAs(new EnchantmentPulling());
    Evasion 					= registerAs(new EnchantmentEvasion());
    Instability					= registerAs(new EnchantmentInstability());
    Unsheathing 				= registerAs(new EnchantmentUnsheathing());
    EnchantmentMastery		 	= registerAs(new EnchantmentMastery());
    TrueStrike					= registerAs(new EnchantmentTrueStrike());
    Pandora 					= registerAs(new EnchantmentPandora());
    
	}
	
	private static EnchantmentBase registerAs(Enchantment enchant)
	{
		return OrderedRegistry.registerAs(enchant);
	}
}