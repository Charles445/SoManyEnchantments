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
		
		if(somanyenchantments.config.InefficentEnable){
            Inefficient = new EnchantmentInefficient();
		    //enchArray.add(Inefficient);
		}
		if(somanyenchantments.config.HeavyWeight)
            HeavyWeight = new EnchantmentHeavyWeight();
		
		if(somanyenchantments.config.RustedEnable)
            Rusted = new EnchantmentRusted();
		
		if(somanyenchantments.config.RustedEnable)
            Rusted = new EnchantmentRusted();
		
		if(somanyenchantments.config.BluntnessEnable)
            Bluntness = new EnchantmentBluntness();
		
		if(somanyenchantments.config.AdvancedEfficencyEnable)
            ExtremeEfficency = new EnchantmentAdvancedEfficiency();
		
     	if(somanyenchantments.config.BlessedEdgeEnable)
            BlessedEdge = new EnchantmentBlessedEdge();
     	
     	if(somanyenchantments.config.ButcheringEnable)
            Butchering = new EnchantmentButchering();
    	
     	if(somanyenchantments.config.CursedEdgeEnable)
            CursedEdge = new EnchantmentCursedEdge();
     	
     	if(somanyenchantments.config.DefusionEnable)
            Defusion = new EnchantmentDefusion();
    	
     	if(somanyenchantments.config.AdvancedBaneOfArthropodsEnable)
            AdvancedBaneOfArthropods = new EnchantmentAdvancedBaneOfArthropods();
     	
     	if(somanyenchantments.config.AdvancedSharpnessEnable)
            AdvancedSharpness = new EnchantmentAdvancedSharpness();
    	
     	if(somanyenchantments.config.AdvancedSmiteEnable)
            AdvancedSmite = new EnchantmentAdvancedSmite();
     	
     	if(somanyenchantments.config.FieryEdgeEnable)
            FieryEdge = new EnchantmentFieryEdge();
    	
     	if(somanyenchantments.config.PurificationEnable)
            Purification = new EnchantmentPurification();
     	
     	if(somanyenchantments.config.ReviledBladeEnable)
            ReviledBlade = new EnchantmentReviledBlade();
    	
     	if(somanyenchantments.config.Rune_PiercingCapabilitiesEnable)
            Rune_PiercingCapabilities = new EnchantmentRune_PiercingCapabilities();
     	
     	if(somanyenchantments.config.SpellBreakerEnable)
            SpellBreaker = new EnchantmentSpellBreaker();
    	
     	if(somanyenchantments.config.SwifterSlashesEnable)
            SwifterSlashes = new EnchantmentSwifterSlashes();
     	
     	if(somanyenchantments.config.WaterAspectEnable)
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
        	if(somanyenchantments.config.InefficentEnable)
        	event.getRegistry().register(Inefficient);
        	
          	if(somanyenchantments.config.HeavyWeight)
        		event.getRegistry().register(HeavyWeight);
        		
        	if(somanyenchantments.config.RustedEnable)
        	    event.getRegistry().register(Rusted);
        		
        	if(somanyenchantments.config.BluntnessEnable)
        	    event.getRegistry().register(Bluntness);
        		
       		if(somanyenchantments.config.BlessedEdgeEnable)
        		event.getRegistry().register(BlessedEdge);
        		
        	if(somanyenchantments.config.ButcheringEnable)
        		event.getRegistry().register(Butchering);
        		
        	if(somanyenchantments.config.CursedEdgeEnable)
        		event.getRegistry().register(CursedEdge);
        		
        	if(somanyenchantments.config.DefusionEnable)
        		event.getRegistry().register(Defusion);
        		
        	if(somanyenchantments.config.AdvancedBaneOfArthropodsEnable)
        		event.getRegistry().register(AdvancedBaneOfArthropods);
        		
        	if(somanyenchantments.config.AdvancedEfficencyEnable)
        	    event.getRegistry().register(ExtremeEfficency);
        		
        	if(somanyenchantments.config.AdvancedSharpnessEnable)
        		event.getRegistry().register(AdvancedSharpness);
        		
        	if(somanyenchantments.config.AdvancedSmiteEnable)
        		event.getRegistry().register(AdvancedSmite);
        		
        	if(somanyenchantments.config.FieryEdgeEnable)
        		event.getRegistry().register(FieryEdge);
        		
        	if(somanyenchantments.config.PurificationEnable)
        		event.getRegistry().register(Purification);
        		
        	if(somanyenchantments.config.ReviledBladeEnable)
        		event.getRegistry().register(ReviledBlade);
        		
        	if(somanyenchantments.config.Rune_PiercingCapabilitiesEnable)
        		event.getRegistry().register(Rune_PiercingCapabilities);
        	
        	if(somanyenchantments.config.SpellBreakerEnable)
        		event.getRegistry().register(SpellBreaker);
        		
        	if(somanyenchantments.config.SwifterSlashesEnable)
        		event.getRegistry().register(SwifterSlashes);
        	
        	if(somanyenchantments.config.WaterAspectEnable)
        		event.getRegistry().register(WaterAspect);
        	
        }
    }
	
	
	
	public static void register()
	{
/**
		
		if(somanyenchantments.config.HeavyWeight)
		GameRegistry.register(HeavyWeight);
		
		if(somanyenchantments.config.RustedEnable)
	    GameRegistry.register(Rusted);
		
		if(somanyenchantments.config.BluntnessEnable)
	    GameRegistry.register(Bluntness);
		
		if(somanyenchantments.config.BlessedEdgeEnable)
		GameRegistry.register(BlessedEdge);
		
		if(somanyenchantments.config.ButcheringEnable)
		GameRegistry.register(Butchering);
		
		if(somanyenchantments.config.CursedEdgeEnable)
		GameRegistry.register(CursedEdge);
		
		if(somanyenchantments.config.DefusionEnable)
		GameRegistry.register(Defusion);
		
		if(somanyenchantments.config.ExtremeBaneOfArthropodsEnable)
		GameRegistry.register(ExtremeBaneOfArthropods);
		
		if(somanyenchantments.config.ExtremeEfficencyEnable)
	    GameRegistry.register(ExtremeEfficency);
		
		if(somanyenchantments.config.ExtremeSharpnessEnable)
		GameRegistry.register(ExtremeSharpness);
		
		if(somanyenchantments.config.ExtremeSmiteEnable)
		GameRegistry.register(ExtremeSmite);
		
		if(somanyenchantments.config.FieryEdgeEnable)
		GameRegistry.register(FieryEdge);
		
		if(somanyenchantments.config.PurificationEnable)
		GameRegistry.register(Purification);
		
		if(somanyenchantments.config.ReviledBladeEnable)
		GameRegistry.register(ReviledBlade);
		
		if(somanyenchantments.config.Rune_PiercingCapabilitiesEnable)
		GameRegistry.register(Rune_PiercingCapabilities);
		
		if(somanyenchantments.config.SpellBreakerEnable)
		GameRegistry.register(SpellBreaker);
		
		if(somanyenchantments.config.SwifterSlashesEnable)
		GameRegistry.register(SwifterSlashes);
	
		if(somanyenchantments.config.WaterAspectEnable)
		GameRegistry.register(WaterAspect);
		
    	

    	*/
	}
       
	public static void enchHandler(){
     	
		if(somanyenchantments.config.InefficentEnable)
			MinecraftForge.EVENT_BUS.register(Inefficient);    	
		
		if(somanyenchantments.config.HeavyWeight)
			MinecraftForge.EVENT_BUS.register(HeavyWeight);    	
		
		if(somanyenchantments.config.BlessedEdgeEnable)
			MinecraftForge.EVENT_BUS.register(BlessedEdge);    	
		
		if(somanyenchantments.config.ButcheringEnable)
			MinecraftForge.EVENT_BUS.register(Butchering);    
		
		//if(somanyenchantments.config.CursedEdgeEnable)
		//	MinecraftForge.EVENT_BUS.register(CursedEdge);    
		
		if(somanyenchantments.config.DefusionEnable)
			MinecraftForge.EVENT_BUS.register(Defusion);    
		
		if(somanyenchantments.config.AdvancedBaneOfArthropodsEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedBaneOfArthropods);    
		
		if(somanyenchantments.config.AdvancedSharpnessEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedSharpness);    
		
		if(somanyenchantments.config.AdvancedSmiteEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedSmite);    
		
		if(somanyenchantments.config.FieryEdgeEnable)
			MinecraftForge.EVENT_BUS.register(FieryEdge);    
		
		if(somanyenchantments.config.PurificationEnable)
			MinecraftForge.EVENT_BUS.register(Purification);    
		
		//if(somanyenchantments.config.ReviledBladeEnable)
		//	MinecraftForge.EVENT_BUS.register(ReviledBlade);    
		
		//if(somanyenchantments.config.Rune_PiercingCapabilitiesEnable)
		//	MinecraftForge.EVENT_BUS.register(Rune_PiercingCapabilities);    
		
		if(somanyenchantments.config.SpellBreakerEnable)
			MinecraftForge.EVENT_BUS.register(SpellBreaker);    
		
		if(somanyenchantments.config.SwifterSlashesEnable)
			MinecraftForge.EVENT_BUS.register(SwifterSlashes);    
		
		if(somanyenchantments.config.WaterAspectEnable)
			MinecraftForge.EVENT_BUS.register(WaterAspect);    
		
		if(somanyenchantments.config.AdvancedEfficencyEnable)
			MinecraftForge.EVENT_BUS.register(ExtremeEfficency);    
		
		if(somanyenchantments.config.BluntnessEnable)
			MinecraftForge.EVENT_BUS.register(Bluntness);   
		
		if(somanyenchantments.config.RustedEnable)
			MinecraftForge.EVENT_BUS.register(Rusted); 
		
		
		
		
     }
		
	

}
