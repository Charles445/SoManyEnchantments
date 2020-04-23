package com.Shultrea.Rin.Enum;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class EnumList {

	static final Predicate<Item> a = item -> item instanceof Item;
	static final Predicate<Item> ax = item -> item instanceof ItemAxe;
	static final Predicate<Item> pax = item -> item instanceof ItemPickaxe;
	static final Predicate<Item> ho = item -> item instanceof ItemHoe;
	static final Predicate<Item> blade = item -> item instanceof ItemSword;
	static final Predicate<Item> shov = item -> item instanceof ItemSpade;
	static final Predicate<Item> comtool = item -> item instanceof ItemTool; 
	static final Predicate<Item> cons = item -> item instanceof ItemAppleGold;
	static final Predicate<Item> shie = item -> item instanceof ItemShield;
	static final Predicate<Item> cb = item -> item.isDamageable();
	static final Predicate<Item> caas = item -> item instanceof ItemSword || item instanceof ItemAxe;
	static final Predicate<Item> rom  = item -> item instanceof ItemSword || item instanceof ItemAxe || item instanceof ItemBow;
	static final Predicate<Item> allTool = item -> item instanceof ItemTool || item instanceof ItemSword;
	static final Predicate<Item> n = item -> false;
	
	public static final EnumEnchantmentType COMBAT_WEAPON		= EnumHelper.addEnchantmentType("Combat Weapon", rom);
	public static final EnumEnchantmentType DAMAGEABLE			= EnumHelper.addEnchantmentType("Damageable", cb);
	public static final EnumEnchantmentType COMBAT_GOLDEN_APPLE = EnumHelper.addEnchantmentType("Golden Apple", cons);
	public static final EnumEnchantmentType COMBAT_TOOL 		= EnumHelper.addEnchantmentType("Combat Tool", comtool);
	public static final EnumEnchantmentType COMBAT_AXE 			= EnumHelper.addEnchantmentType("Combat Axe", ax);
	public static final EnumEnchantmentType AXE 				= EnumHelper.addEnchantmentType("Tool Axe", ax);
	public static final EnumEnchantmentType PICKAXE  			= EnumHelper.addEnchantmentType("Tool Pickaxe", pax);
	public static final EnumEnchantmentType HOE 	 			= EnumHelper.addEnchantmentType("Tool Hoe", ho);
	public static final EnumEnchantmentType SWORD 		 		= EnumHelper.addEnchantmentType("Combat Sword", blade);
	public static final EnumEnchantmentType SPADE 	 			= EnumHelper.addEnchantmentType("Tool Shovel", shov);
	public static final EnumEnchantmentType SHIELD	 			= EnumHelper.addEnchantmentType("Combat Shield", shie);
	public static final EnumEnchantmentType COMBAT				= EnumHelper.addEnchantmentType("Combat", caas);
	public static final EnumEnchantmentType ALL_TOOL			= EnumHelper.addEnchantmentType("All Tools", allTool);
	public static final EnumEnchantmentType ALL					= EnumHelper.addEnchantmentType("All", a);
	public static final EnumEnchantmentType NONE				= EnumHelper.addEnchantmentType("None", n);

	
	public EnumList(){
		
	}
	
	public static void initializeEnchantmentTab(){
		CreativeTabs c = CreativeTabs.COMBAT;
		CreativeTabs a = CreativeTabs.TOOLS;
		List<EnumEnchantmentType> combatType = new ArrayList(); 
		List<EnumEnchantmentType> toolTypes = new ArrayList(); 
		
		for(int x = 0; x < c.getRelevantEnchantmentTypes().length; x++){
			EnumEnchantmentType[] e = c.getRelevantEnchantmentTypes();
			combatType.add(e[x]);	
		}
		for(int x = 0; x < a.getRelevantEnchantmentTypes().length; x++){
			EnumEnchantmentType[] e = a.getRelevantEnchantmentTypes();
			toolTypes.add(e[x]);	
		}
		
		combatType.add(SWORD);
		combatType.add(COMBAT_AXE);
		combatType.add(COMBAT_TOOL);
		combatType.add(COMBAT_GOLDEN_APPLE);
		combatType.add(SHIELD);
		combatType.add(COMBAT);
		combatType.add(COMBAT_WEAPON);
		
		toolTypes.add(ALL_TOOL);	
		toolTypes.add(HOE);
		toolTypes.add(AXE);
		toolTypes.add(SPADE);
		toolTypes.add(PICKAXE);
		toolTypes.add(DAMAGEABLE);
		toolTypes.add(ALL);
		toolTypes.add(NONE);
		
		EnumEnchantmentType[] finalCombatEnchantmentType = combatType.toArray(new EnumEnchantmentType[combatType.size()]);
		EnumEnchantmentType[] finalToolEnchantmentType = toolTypes.toArray(new EnumEnchantmentType[toolTypes.size()]);

		CreativeTabs.COMBAT.setRelevantEnchantmentTypes(finalCombatEnchantmentType);
		CreativeTabs.TOOLS.setRelevantEnchantmentTypes(finalToolEnchantmentType);
	}
	
	
	
	
	
}
