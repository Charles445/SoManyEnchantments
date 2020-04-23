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
		if(somanyenchantments.config.Mortalitas)
			Mortalitas = new EnchantmentMortalitas();
		
		if(somanyenchantments.config.PenetratingEdgeEnable)
			PenetratingEdge = new EnchantmentPenetratingEdge();
		
		if(somanyenchantments.config.CombatRegenerationEnable)
			CombatRegeneration = new EnchantmentCombatVeterancy();
		
		if(somanyenchantments.config.AdvancedKnockbackEnable)
			AdvancedKnockback = new EnchantmentAdvancedKnockback();
		
		if(somanyenchantments.config.Rune_MagicalBlessingEnable)
			Rune_MagicalBlessing = new EnchantmentRune_MagicalBlessing();
		
		if(somanyenchantments.config.CounterAttackEnable)
			CounterAttack = new EnchantmentCounterAttack();
		
		if(somanyenchantments.config.ParryEnable)
			Parry = new EnchantmentParry();
		
		if(somanyenchantments.config.UnpredictableEnable)
			Unpredictable = new EnchantmentUnpredictable();
		
		if(somanyenchantments.config.LifestealEnable)
			Lifesteal = new EnchantmentLifesteal();
		
		if(somanyenchantments.config.CullingEnable)
			Culling = new EnchantmentCulling();
		
		
		
	}

	  @Mod.EventBusSubscriber
	    public static class EventSubscriber {

	        @SubscribeEvent
	        public static void registerEnchantment(RegistryEvent.Register<net.minecraft.enchantment.Enchantment> event) {
	        	if(somanyenchantments.config.PenetratingEdgeEnable)
	    			event.getRegistry().register(PenetratingEdge);
	    		
	    		if(somanyenchantments.config.CombatRegenerationEnable)
	    			event.getRegistry().register(CombatRegeneration);
	    		
	    		if(somanyenchantments.config.AdvancedKnockbackEnable)
	    			event.getRegistry().register(AdvancedKnockback);
	    		
	    		if(somanyenchantments.config.Rune_MagicalBlessingEnable)
	    			event.getRegistry().register(Rune_MagicalBlessing);
	    		
	    		if(somanyenchantments.config.CounterAttackEnable)
	    			event.getRegistry().register(CounterAttack);
	    		
	    		if(somanyenchantments.config.ParryEnable)
	    			event.getRegistry().register(Parry);
	    		
	    		if(somanyenchantments.config.UnpredictableEnable)
	    			event.getRegistry().register(Unpredictable);
	    		
	    		if(somanyenchantments.config.LifestealEnable)
	    			event.getRegistry().register(Lifesteal);
	    		
	    		if(somanyenchantments.config.CullingEnable)
	    			event.getRegistry().register(Culling);
	        	
	    		if(somanyenchantments.config.Mortalitas)
	    			event.getRegistry().register(Mortalitas);
	          	
	        	
	        }
	    }

	public static void enchHandler(){
		
		if(somanyenchantments.config.Mortalitas)
			MinecraftForge.EVENT_BUS.register(Mortalitas);
		
		if(somanyenchantments.config.CullingEnable)
			MinecraftForge.EVENT_BUS.register(Culling);

		if(somanyenchantments.config.PenetratingEdgeEnable)
			MinecraftForge.EVENT_BUS.register(PenetratingEdge);
		
		if(somanyenchantments.config.UnpredictableEnable)
			MinecraftForge.EVENT_BUS.register(Unpredictable);
		
		if(somanyenchantments.config.CounterAttackEnable)
			MinecraftForge.EVENT_BUS.register(CounterAttack);
		
		if(somanyenchantments.config.ParryEnable)
			MinecraftForge.EVENT_BUS.register(Parry);
		
		//if(somanyenchantments.config.Rune_MagicalBlessingEnable)
		//	MinecraftForge.EVENT_BUS.register(Rune_MagicalBlessing);
		
		if(somanyenchantments.config.CombatRegenerationEnable)
			MinecraftForge.EVENT_BUS.register(CombatRegeneration);
		
		if(somanyenchantments.config.AdvancedKnockbackEnable)
			MinecraftForge.EVENT_BUS.register(AdvancedKnockback);
		
		if(somanyenchantments.config.LifestealEnable)
			MinecraftForge.EVENT_BUS.register(Lifesteal);
	
		
		
		
	}
}
