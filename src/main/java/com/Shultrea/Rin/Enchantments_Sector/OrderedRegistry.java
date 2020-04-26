package com.Shultrea.Rin.Enchantments_Sector;

import java.util.LinkedHashSet;
import java.util.Set;

import com.Shultrea.Rin.Enchantment_Base_Sector.EnchantmentBase;
import com.Shultrea.Rin.Main_Sector.somanyenchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class OrderedRegistry
{
	public static Set<EnchantmentBase> orderedEnchants = new LinkedHashSet<>();
	
	public static void orderedRegister()
	{
		//Smc 010
		subscribe(Smc_010.Inefficient);    	
		subscribe(Smc_010.HeavyWeight);    	
		subscribe(Smc_010.BlessedEdge);    	
		subscribe(Smc_010.Butchering); 
		subscribe(Smc_010.Defusion);    
		subscribe(Smc_010.AdvancedBaneOfArthropods);    
		subscribe(Smc_010.AdvancedSharpness);    
		subscribe(Smc_010.AdvancedSmite);    
		subscribe(Smc_010.FieryEdge);    
		subscribe(Smc_010.Purification);
		subscribe(Smc_010.SpellBreaker);    
		subscribe(Smc_010.SwifterSlashes);
		subscribe(Smc_010.WaterAspect);    
		subscribe(Smc_010.ExtremeEfficency);    
		subscribe(Smc_010.Bluntness);   
		subscribe(Smc_010.Rusted); 
		
		//Smc 020
		subscribe(Smc_020.Mortalitas);
		subscribe(Smc_020.Culling);
		subscribe(Smc_020.PenetratingEdge);
		subscribe(Smc_020.Unpredictable);
		subscribe(Smc_020.CounterAttack);
		subscribe(Smc_020.Parry);
		subscribe(Smc_020.CombatRegeneration);
		subscribe(Smc_020.AdvancedKnockback);
		subscribe(Smc_020.Lifesteal);
		
		//Smc 030
		subscribe(Smc_030.Clearsky);
		subscribe(Smc_030.Moonlight);
		subscribe(Smc_030.Raining);
		subscribe(Smc_030.Sunshine);
		subscribe(Smc_030.Thunderstorm);
		subscribe(Smc_030.Winter);
		subscribe(Smc_030.Smelter);
		subscribe(Smc_030.EmpoweredDefence);
		subscribe(Smc_030.Strafe);
		subscribe(Smc_030.AdvancedLooting);
		subscribe(Smc_030.Desolator);
		subscribe(Smc_030.Disorientation);
		subscribe(Smc_030.PurgingBlade);
		subscribe(Smc_030.Viper);
		subscribe(Smc_030.AdvancedPower);
		subscribe(Smc_030.Envenomed);
		subscribe(Smc_030.Powerless);
		subscribe(Smc_030.Rune_Revival);
		subscribe(Smc_030.AdvancedPunch);
		subscribe(Smc_030.AdvancedLure);
		subscribe(Smc_030.AdvancedLuckOfTheSea);
		subscribe(Smc_030.AdvancedFireProtection);
		subscribe(Smc_030.AdvancedBlastProtection);
		subscribe(Smc_030.Quarrying);
		
		//Smc 040
		subscribe(Smc_040.AtomicDeconstructor);
		subscribe(Smc_040.Disarmament);
		subscribe(Smc_040.Hors_de_combat);
		subscribe(Smc_040.CurseofPossession);
		subscribe(Smc_040.freezing);
		subscribe(Smc_040.advancedmending);
		subscribe(Smc_040.lesserflame);
		subscribe(Smc_040.advancedflame);
		subscribe(Smc_040.supremeflame);
		subscribe(Smc_040.splitshot);		
		subscribe(Smc_040.Rune_Resurrection);
		subscribe(Smc_040.strengthenedvitality);
		subscribe(Smc_040.welltilled);
		subscribe(Smc_040.upgrade);
		subscribe(Smc_040.Adept);
		subscribe(Smc_040.CurseofDecay);
		subscribe(Smc_040.MagmaWalker);
		subscribe(Smc_040.fieryshield);
		subscribe(Smc_040.DarkShadows);
		subscribe(Smc_040.CurseOfInaccuracy);
		subscribe(Smc_040.InnerBerserk);
		subscribe(Smc_040.AncientCurseInflicter);
		subscribe(Smc_040.TillingPower);
		subscribe(Smc_040.CurseofHolding);
		subscribe(Smc_040.CurseofVulnerability);
		subscribe(Smc_040.LightWeight);
		subscribe(Smc_040.UnderwaterStrider);
		subscribe(Smc_040.Frenzy);
		subscribe(Smc_040.Pushing);
		subscribe(Smc_040.Evasion);
		subscribe(Smc_040.Pulling);
		subscribe(Smc_040.Unsheathing);
		subscribe(Smc_040.Pandora);
		
		
		//ORDERED REGISTRY	
		subscribe(Smc_040.Instability);
		subscribe(Smc_040.LuckMagnification);
		subscribe(Smc_010.CursedEdge);
		subscribe(Smc_030.AshDestroyer);
		subscribe(Smc_030.CriticalStrike);
		subscribe(Smc_010.ReviledBlade);
		subscribe(Smc_040.difficultyscaled);
		subscribe(Smc_040.NaturalBlocking);
		subscribe(Smc_040.NaturalBlocking);
		subscribe(Smc_040.Rune_PiercingArrows);
		subscribe(Smc_040.Swiper);
		subscribe(Smc_020.Rune_MagicalBlessing);
		subscribe(Smc_010.Rune_PiercingCapabilities);
		subscribe(Smc_040.EnchantmentMastery);
	}
	
	public static EnchantmentBase registerEnchant(EnchantmentBase enchant)
	{
		if(enchant==null)
			throw new RuntimeException("Passed a null enchant during registerEnchant!");
		
		//if(enchant.isConfigEnabled())
		//{
			orderedEnchants.add(enchant);
		//}
		
		return enchant;
	}
	
	@Mod.EventBusSubscriber(modid = somanyenchantments.MODID)
    public static class EventSubscriber
    {
        @SubscribeEvent
        public static void registerEnchantment(RegistryEvent.Register<net.minecraft.enchantment.Enchantment> event)
        {
        	IForgeRegistry<Enchantment> registry = event.getRegistry();
        	for(EnchantmentBase enchant : orderedEnchants)
        	{
        		if(!enchant.isRegistered())
        		{
	        		registry.register(enchant);
	    			enchant.setRegistered();
        		}
        	}
        }
    }
	
	public static void subscribe(EnchantmentBase o)
	{
		if(o.isEnabled())
			MinecraftForge.EVENT_BUS.register(o);
		
	}
	
	public static EnchantmentBase registerAs(Enchantment enchant)
	{
		if(enchant instanceof EnchantmentBase)
		{
			return OrderedRegistry.registerEnchant((EnchantmentBase) enchant);
		}
		else
		{
			throw new RuntimeException("Tried to registerAs an enchantment that was not an EnchantmentBase! "+enchant);
		}
	}
}
