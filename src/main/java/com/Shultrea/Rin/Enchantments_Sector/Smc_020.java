package com.Shultrea.Rin.Enchantments_Sector;

import com.Shultrea.Rin.Ench0_2_0.EnchantmentCombatVeterancy;
import com.Shultrea.Rin.Ench0_2_0.EnchantmentCounterAttack;
import com.Shultrea.Rin.Ench0_2_0.EnchantmentCulling;
import com.Shultrea.Rin.Ench0_2_0.EnchantmentAdvancedKnockback;
import com.Shultrea.Rin.Ench0_2_0.EnchantmentLifesteal;
import com.Shultrea.Rin.Ench0_2_0.EnchantmentParry;
import com.Shultrea.Rin.Ench0_2_0.EnchantmentPenetratingEdge;
import com.Shultrea.Rin.Ench0_2_0.EnchantmentRune_MagicalBlessing;
import com.Shultrea.Rin.Ench0_2_0.EnchantmentUnpredictable;
import com.Shultrea.Rin.Ench0_2_0.EnchantmentMortalitas;
import com.Shultrea.Rin.Main_Sector.ModConfig;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Smc_020 {

	public static Enchantment PenetratingEdge;
	public static Enchantment CombatRegeneration;
	public static Enchantment AdvancedKnockback;
	public static Enchantment Rune_MagicalBlessing;
	public static Enchantment CounterAttack;
	public static Enchantment Parry;
	public static Enchantment Unpredictable;
	public static Enchantment Lifesteal;
	public static Enchantment Culling;
	public static Enchantment Mortalitas;
	
	public static void init(){
		if(ModConfig.enabled.Mortalitas)
			Mortalitas = new EnchantmentMortalitas();
		
		if(ModConfig.enabled.PenetratingEdgeEnable)
			PenetratingEdge = new EnchantmentPenetratingEdge();
		
		if(ModConfig.enabled.CombatRegenerationEnable)
			CombatRegeneration = new EnchantmentCombatVeterancy();
		
		if(ModConfig.enabled.AdvancedKnockbackEnable)
			AdvancedKnockback = new EnchantmentAdvancedKnockback();
		
		if(ModConfig.enabled.Rune_MagicalBlessingEnable)
			Rune_MagicalBlessing = new EnchantmentRune_MagicalBlessing();
		
		if(ModConfig.enabled.CounterAttackEnable)
			CounterAttack = new EnchantmentCounterAttack();
		
		if(ModConfig.enabled.ParryEnable)
			Parry = new EnchantmentParry();
		
		if(ModConfig.enabled.UnpredictableEnable)
			Unpredictable = new EnchantmentUnpredictable();
		
		if(ModConfig.enabled.LifestealEnable)
			Lifesteal = new EnchantmentLifesteal();
		
		if(ModConfig.enabled.CullingEnable)
			Culling = new EnchantmentCulling();
		
		
		
	}

	  @Mod.EventBusSubscriber
	    public static class EventSubscriber {

	        @SubscribeEvent
	        public static void registerEnchantment(RegistryEvent.Register<net.minecraft.enchantment.Enchantment> event) {
	        	if(ModConfig.enabled.PenetratingEdgeEnable)
	    			event.getRegistry().register(PenetratingEdge);
	    		
	    		if(ModConfig.enabled.CombatRegenerationEnable)
	    			event.getRegistry().register(CombatRegeneration);
	    		
	    		if(ModConfig.enabled.AdvancedKnockbackEnable)
	    			event.getRegistry().register(AdvancedKnockback);
	    		
	    		if(ModConfig.enabled.Rune_MagicalBlessingEnable)
	    			event.getRegistry().register(Rune_MagicalBlessing);
	    		
	    		if(ModConfig.enabled.CounterAttackEnable)
	    			event.getRegistry().register(CounterAttack);
	    		
	    		if(ModConfig.enabled.ParryEnable)
	    			event.getRegistry().register(Parry);
	    		
	    		if(ModConfig.enabled.UnpredictableEnable)
	    			event.getRegistry().register(Unpredictable);
	    		
	    		if(ModConfig.enabled.LifestealEnable)
	    			event.getRegistry().register(Lifesteal);
	    		
	    		if(ModConfig.enabled.CullingEnable)
	    			event.getRegistry().register(Culling);
	        	
	    		if(ModConfig.enabled.Mortalitas)
	    			event.getRegistry().register(Mortalitas);
	          	
	        	
	        }
	    }

	public static void enchHandler(){
		
		if(ModConfig.enabled.Mortalitas)
			MinecraftForge.EVENT_BUS.register(Mortalitas);
		
		if(ModConfig.enabled.CullingEnable)
			MinecraftForge.EVENT_BUS.register(Culling);

		if(ModConfig.enabled.PenetratingEdgeEnable)
			MinecraftForge.EVENT_BUS.register(PenetratingEdge);
		
		if(ModConfig.enabled.UnpredictableEnable)
			MinecraftForge.EVENT_BUS.register(Unpredictable);
		
		if(ModConfig.enabled.CounterAttackEnable)
			MinecraftForge.EVENT_BUS.register(CounterAttack);
		
		if(ModConfig.enabled.ParryEnable)
			MinecraftForge.EVENT_BUS.register(Parry);
		
		//if(ModConfig.enabled.Rune_MagicalBlessingEnable)
		//	MinecraftForge.EVENT_BUS.register(Rune_MagicalBlessing);
		
		if(ModConfig.enabled.CombatRegenerationEnable)
			MinecraftForge.EVENT_BUS.register(CombatRegeneration);
		
		if(ModConfig.enabled.AdvancedKnockbackEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedKnockback);
		
		if(ModConfig.enabled.LifestealEnable)
			MinecraftForge.EVENT_BUS.register(Lifesteal);
	
		
		
		
	}
}
