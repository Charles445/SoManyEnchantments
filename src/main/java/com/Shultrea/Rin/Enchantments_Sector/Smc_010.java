package com.Shultrea.Rin.Enchantments_Sector;




import java.util.ArrayList;

import com.Shultrea.Rin.Ench0_1_0.EnchantmentBlessedEdge;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentBluntness;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentButchering;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentCursedEdge;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentDefusion;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentAdvancedBaneOfArthropods;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentAdvancedEfficiency;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentAdvancedSharpness;
import com.Shultrea.Rin.Ench0_1_0.EnchantmentAdvancedSmite;
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
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;
import com.Shultrea.Rin.Utility_Sector.LivingAttackFixerHandler;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smc_010 {
	
	
	public static Enchantment Defusion; //
	public static Enchantment Butchering; //
	public static Enchantment Purification; //
	public static Enchantment SpellBreaker; //
	public static Enchantment WaterAspect; //
	public static Enchantment CursedEdge; //
	public static Enchantment BlessedEdge; //
	public static Enchantment AdvancedSharpness; //
	public static Enchantment AdvancedSmite; //
	public static Enchantment AdvancedBaneOfArthropods; //
	public static Enchantment FieryEdge; //
	public static Enchantment Rune_PiercingCapabilities; //
	public static Enchantment ReviledBlade; //
	public static Enchantment SwifterSlashes; //
	
	public static Enchantment BlazingBlade;
	public static Enchantment Enchanted;
	public static Enchantment SilencedEdge;
	public static Enchantment Thorned;
	public static Enchantment VoidEmpowered;
	public static Enchantment Electrocution;
	public static Enchantment ExtremeEfficency; //
	public static Enchantment Bluntness; //
	public static Enchantment Rusted; //
	public static Enchantment HeavyWeight; //
	public static Enchantment Inefficient; //
	


	
	public static void init()
	{	
	    //ArrayList<Enchantment> enchArray = new ArrayList<Enchantment>();
		
		if(ModConfig.enabled.InefficentEnable){
            Inefficient = new EnchantmentInefficient();
		    //enchArray.add(Inefficient);
		}
		if(ModConfig.enabled.HeavyWeight)
            HeavyWeight = new EnchantmentHeavyWeight();
		
		if(ModConfig.enabled.RustedEnable)
            Rusted = new EnchantmentRusted();
		
		if(ModConfig.enabled.RustedEnable)
            Rusted = new EnchantmentRusted();
		
		if(ModConfig.enabled.BluntnessEnable)
            Bluntness = new EnchantmentBluntness();
		
		if(ModConfig.enabled.AdvancedEfficencyEnable)
            ExtremeEfficency = new EnchantmentAdvancedEfficiency();
		
     	if(ModConfig.enabled.BlessedEdgeEnable)
            BlessedEdge = new EnchantmentBlessedEdge();
     	
     	if(ModConfig.enabled.ButcheringEnable)
            Butchering = new EnchantmentButchering();
    	
     	if(ModConfig.enabled.CursedEdgeEnable)
            CursedEdge = new EnchantmentCursedEdge();
     	
     	if(ModConfig.enabled.DefusionEnable)
            Defusion = new EnchantmentDefusion();
    	
     	if(ModConfig.enabled.AdvancedBaneOfArthropodsEnable)
            AdvancedBaneOfArthropods = new EnchantmentAdvancedBaneOfArthropods();
     	
     	if(ModConfig.enabled.AdvancedSharpnessEnable)
            AdvancedSharpness = new EnchantmentAdvancedSharpness();
    	
     	if(ModConfig.enabled.AdvancedSmiteEnable)
            AdvancedSmite = new EnchantmentAdvancedSmite();
     	
     	if(ModConfig.enabled.FieryEdgeEnable)
            FieryEdge = new EnchantmentFieryEdge();
    	
     	if(ModConfig.enabled.PurificationEnable)
            Purification = new EnchantmentPurification();
     	
     	if(ModConfig.enabled.ReviledBladeEnable)
            ReviledBlade = new EnchantmentReviledBlade();
    	
     	if(ModConfig.enabled.Rune_PiercingCapabilitiesEnable)
            Rune_PiercingCapabilities = new EnchantmentRune_PiercingCapabilities();
     	
     	if(ModConfig.enabled.SpellBreakerEnable)
            SpellBreaker = new EnchantmentSpellBreaker();
    	
     	if(ModConfig.enabled.SwifterSlashesEnable)
            SwifterSlashes = new EnchantmentSwifterSlashes();
     	
     	if(ModConfig.enabled.WaterAspectEnable)
            WaterAspect = new EnchantmentWaterAspect();
     	
     	
     	/**int size = enchArray.size();
     	
     	if(size <= 0){
     	for(int k = 0; k < size; k++){
 
        Enchantment theReturn = enchArray.get(k);
     
		return theReturn;
     	
     	}
     	}
     	else return null;
		
    	*/

	}

    @Mod.EventBusSubscriber
    public static class EventSubscriber {

        /**
         * Registers the felling enchantment when the Enchantment register event fires.
         * @param event The enchantment register event.
         */
        @SubscribeEvent
        public static void registerEnchantment(RegistryEvent.Register<net.minecraft.enchantment.Enchantment> event) {
        	if(ModConfig.enabled.InefficentEnable)
        	event.getRegistry().register(Inefficient);
        	
          	if(ModConfig.enabled.HeavyWeight)
        		event.getRegistry().register(HeavyWeight);
        		
        	if(ModConfig.enabled.RustedEnable)
        	    event.getRegistry().register(Rusted);
        		
        	if(ModConfig.enabled.BluntnessEnable)
        	    event.getRegistry().register(Bluntness);
        		
       		if(ModConfig.enabled.BlessedEdgeEnable)
        		event.getRegistry().register(BlessedEdge);
        		
        	if(ModConfig.enabled.ButcheringEnable)
        		event.getRegistry().register(Butchering);
        		
        	if(ModConfig.enabled.CursedEdgeEnable)
        		event.getRegistry().register(CursedEdge);
        		
        	if(ModConfig.enabled.DefusionEnable)
        		event.getRegistry().register(Defusion);
        		
        	if(ModConfig.enabled.AdvancedBaneOfArthropodsEnable)
        		event.getRegistry().register(AdvancedBaneOfArthropods);
        		
        	if(ModConfig.enabled.AdvancedEfficencyEnable)
        	    event.getRegistry().register(ExtremeEfficency);
        		
        	if(ModConfig.enabled.AdvancedSharpnessEnable)
        		event.getRegistry().register(AdvancedSharpness);
        		
        	if(ModConfig.enabled.AdvancedSmiteEnable)
        		event.getRegistry().register(AdvancedSmite);
        		
        	if(ModConfig.enabled.FieryEdgeEnable)
        		event.getRegistry().register(FieryEdge);
        		
        	if(ModConfig.enabled.PurificationEnable)
        		event.getRegistry().register(Purification);
        		
        	if(ModConfig.enabled.ReviledBladeEnable)
        		event.getRegistry().register(ReviledBlade);
        		
        	if(ModConfig.enabled.Rune_PiercingCapabilitiesEnable)
        		event.getRegistry().register(Rune_PiercingCapabilities);
        	
        	if(ModConfig.enabled.SpellBreakerEnable)
        		event.getRegistry().register(SpellBreaker);
        		
        	if(ModConfig.enabled.SwifterSlashesEnable)
        		event.getRegistry().register(SwifterSlashes);
        	
        	if(ModConfig.enabled.WaterAspectEnable)
        		event.getRegistry().register(WaterAspect);
        	
        }
    }
	
	
	
	public static void register()
	{
/**
		
		if(ModConfig.enabled.HeavyWeight)
		GameRegistry.register(HeavyWeight);
		
		if(ModConfig.enabled.RustedEnable)
	    GameRegistry.register(Rusted);
		
		if(ModConfig.enabled.BluntnessEnable)
	    GameRegistry.register(Bluntness);
		
		if(ModConfig.enabled.BlessedEdgeEnable)
		GameRegistry.register(BlessedEdge);
		
		if(ModConfig.enabled.ButcheringEnable)
		GameRegistry.register(Butchering);
		
		if(ModConfig.enabled.CursedEdgeEnable)
		GameRegistry.register(CursedEdge);
		
		if(ModConfig.enabled.DefusionEnable)
		GameRegistry.register(Defusion);
		
		if(ModConfig.enabled.ExtremeBaneOfArthropodsEnable)
		GameRegistry.register(ExtremeBaneOfArthropods);
		
		if(ModConfig.enabled.ExtremeEfficencyEnable)
	    GameRegistry.register(ExtremeEfficency);
		
		if(ModConfig.enabled.ExtremeSharpnessEnable)
		GameRegistry.register(ExtremeSharpness);
		
		if(ModConfig.enabled.ExtremeSmiteEnable)
		GameRegistry.register(ExtremeSmite);
		
		if(ModConfig.enabled.FieryEdgeEnable)
		GameRegistry.register(FieryEdge);
		
		if(ModConfig.enabled.PurificationEnable)
		GameRegistry.register(Purification);
		
		if(ModConfig.enabled.ReviledBladeEnable)
		GameRegistry.register(ReviledBlade);
		
		if(ModConfig.enabled.Rune_PiercingCapabilitiesEnable)
		GameRegistry.register(Rune_PiercingCapabilities);
		
		if(ModConfig.enabled.SpellBreakerEnable)
		GameRegistry.register(SpellBreaker);
		
		if(ModConfig.enabled.SwifterSlashesEnable)
		GameRegistry.register(SwifterSlashes);
	
		if(ModConfig.enabled.WaterAspectEnable)
		GameRegistry.register(WaterAspect);
		
    	

    	*/
	}
       
	public static void enchHandler(){
     	
		if(ModConfig.enabled.InefficentEnable)
			MinecraftForge.EVENT_BUS.register(Inefficient);    	
		
		if(ModConfig.enabled.HeavyWeight)
			MinecraftForge.EVENT_BUS.register(HeavyWeight);    	
		
		if(ModConfig.enabled.BlessedEdgeEnable)
			MinecraftForge.EVENT_BUS.register(BlessedEdge);    	
		
		if(ModConfig.enabled.ButcheringEnable)
			MinecraftForge.EVENT_BUS.register(Butchering);    
		
		//if(ModConfig.enabled.CursedEdgeEnable)
		//	MinecraftForge.EVENT_BUS.register(CursedEdge);    
		
		if(ModConfig.enabled.DefusionEnable)
			MinecraftForge.EVENT_BUS.register(Defusion);    
		
		if(ModConfig.enabled.AdvancedBaneOfArthropodsEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedBaneOfArthropods);    
		
		if(ModConfig.enabled.AdvancedSharpnessEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedSharpness);    
		
		if(ModConfig.enabled.AdvancedSmiteEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedSmite);    
		
		if(ModConfig.enabled.FieryEdgeEnable)
			MinecraftForge.EVENT_BUS.register(FieryEdge);    
		
		if(ModConfig.enabled.PurificationEnable)
			MinecraftForge.EVENT_BUS.register(Purification);    
		
		//if(ModConfig.enabled.ReviledBladeEnable)
		//	MinecraftForge.EVENT_BUS.register(ReviledBlade);    
		
		//if(ModConfig.enabled.Rune_PiercingCapabilitiesEnable)
		//	MinecraftForge.EVENT_BUS.register(Rune_PiercingCapabilities);    
		
		if(ModConfig.enabled.SpellBreakerEnable)
			MinecraftForge.EVENT_BUS.register(SpellBreaker);    
		
		if(ModConfig.enabled.SwifterSlashesEnable)
			MinecraftForge.EVENT_BUS.register(SwifterSlashes);    
		
		if(ModConfig.enabled.WaterAspectEnable)
			MinecraftForge.EVENT_BUS.register(WaterAspect);    
		
		if(ModConfig.enabled.AdvancedEfficencyEnable)
			MinecraftForge.EVENT_BUS.register(ExtremeEfficency);    
		
		if(ModConfig.enabled.BluntnessEnable)
			MinecraftForge.EVENT_BUS.register(Bluntness);   
		
		if(ModConfig.enabled.RustedEnable)
			MinecraftForge.EVENT_BUS.register(Rusted); 
		
		
		
		
     }
		
	

}
