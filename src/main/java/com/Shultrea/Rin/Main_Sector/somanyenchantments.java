package com.Shultrea.Rin.Main_Sector;

import com.Shultrea.Rin.Enchantments_Sector.OrderedRegistry;
import com.Shultrea.Rin.Enchantments_Sector.Smc_010;
import com.Shultrea.Rin.Enchantments_Sector.Smc_020;
import com.Shultrea.Rin.Enchantments_Sector.Smc_030;
import com.Shultrea.Rin.Enchantments_Sector.Smc_040;
import com.Shultrea.Rin.Enum.EnumList;
import com.Shultrea.Rin.Prop_Sector.ArrowProperties;
import com.Shultrea.Rin.Prop_Sector.ArrowPropertiesStorage;
import com.Shultrea.Rin.Prop_Sector.IArrowProperties;
import com.Shultrea.Rin.Prop_Sector.IPlayerProperties;
import com.Shultrea.Rin.Prop_Sector.PlayerProperties;
import com.Shultrea.Rin.Prop_Sector.PlayerPropertiesStorage;
import com.Shultrea.Rin.Utility_Sector.CommProxy;
import com.Shultrea.Rin.Utility_Sector.EnchantmentLister;
import com.Shultrea.Rin.Utility_Sector.EnchantmentsUtility;
import com.Shultrea.Rin.Utility_Sector.HurtPatchHandler;
import com.Shultrea.Rin.Utility_Sector.OtherHandler;
import com.Shultrea.Rin.Utility_Sector.PotionLister;
import com.Shultrea.Rin.Utility_Sector.RefStrings;
import com.Shultrea.Rin.Utility_Sector.SMEsounds;

import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;



@Mod(modid = somanyenchantments.MODID, name = somanyenchantments.NAME, version = somanyenchantments.VERSION, acceptedMinecraftVersions = "[1.12.0, 1.12.2]")
public class somanyenchantments {
	// Mod Info
	public static final String MODID = "somanyenchantments";
	public static final String NAME = "Rin's So Many Enchantments?";
	public static final String VERSION = "0.5.4";
	// Mod Info End
	
	//Boolean
	public static boolean hotbarOnly = true;
	
	// Singleton
	@Instance("SoManyEnchantments")
	//For Swords
	public static somanyenchantments instance;
	
	@SidedProxy(clientSide = RefStrings.CLIENTSIDE, serverSide = RefStrings.SERVERSIDE)
	public static CommProxy proxy;

	/**Pure is a damage source added by this mod that can kill even creative players. It also bypasses several damage absorptions.*/
	public static DamageSource Pure = new DamageSource("Pure").setDamageAllowedInCreativeMode().setDamageBypassesArmor().setDamageIsAbsolute();
	/**Ethereal is a damage source added by this mod that bypasses armor*/
	public static DamageSource Ethereal = new DamageSource("Ethereal").setDamageBypassesArmor();
    /**Cleave is a damage source added by this mod that is used to damage enemies in a certain angle from the original damage source.*/
	public static DamageSource Cleave = new DamageSource("Cleave");
	/**Deconstruct is a damage source added by this mod that is used when an enemy is damaged from a molecular force. It bypasses armor and is absolute.*/
	public static DamageSource Deconstruct = new DamageSource("Deconstruct").setDamageIsAbsolute().setDamageBypassesArmor();
	/**Culled is a damage source added by this mod that is used to finish off low health opponents. Like Deconstruct, it bypasses armor and is absolute. */
	public static DamageSource Culled = new DamageSource("Culled").setDamageIsAbsolute().setDamageBypassesArmor();
	/**CounterAttack is a damage source added by this mod that is used by the counter attack enchantment. */
	public static DamageSource CounterAttack = new DamageSource("CounterAttack");
	/**Silence is a damage source added by this mod that is used instead of a physical damage to deal damage to silenced mob.*/
	public static DamageSource Silence = new DamageSource("Silence");
	/**Physical Damage is a damage source added by this mod that is used to deal ordinary damage to a mob. Some enchantments in this mod make use of it.*/
	public static DamageSource PhysicalDamage = new DamageSource("PhysicalDamage");
	

	
	// Config
	//public static ConfigOld config;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent fEvent)
    {   
    	CapabilityManager.INSTANCE.register(IArrowProperties.class, new ArrowPropertiesStorage(), ArrowProperties :: new);
    	CapabilityManager.INSTANCE.register(IPlayerProperties.class, new PlayerPropertiesStorage(), PlayerProperties :: new);
    	
    	//config = new ConfigOld(new Configuration(fEvent.getSuggestedConfigurationFile()));
    	
    	proxy.preInit(fEvent);
    	
    	SMEsounds.registerSounds();
    	
    	Smc_010.init();
    	
    	Smc_020.init();
    
    	Smc_030.init();
    	
    	Smc_040.init();

    }
    
    @EventHandler
    public void load(FMLInitializationEvent fEvent)
    {  
    	proxy.onInit(fEvent);
	}
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent fEvent)
    {
	    proxy.postInit(fEvent);
	    
	    //Event Source
	    OrderedRegistry.orderedRegister();
	    PotionLister.Cycle();
	    EnumList.initializeEnchantmentTab();
	    EnchantmentLister.initEnchantmentList();
	}  
}
