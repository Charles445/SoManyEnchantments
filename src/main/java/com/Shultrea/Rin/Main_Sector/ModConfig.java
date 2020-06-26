package com.Shultrea.Rin.Main_Sector;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Config(modid=somanyenchantments.MODID)
public class ModConfig
{
	@Config.Comment("Enabled Enchantments")
	@Config.Name("Enabled Enchantments")
	public static EnabledConfig enabled = new EnabledConfig();
	
	@Config.Comment("Maximum levels of each enchantment")
	@Config.Name("Enchantment Levels")
	public static LevelConfig level = new LevelConfig();
	
	@Config.Comment("Miscellaneous")
	@Config.Name("Miscellaneous")
	public static MiscellaneousConfig miscellaneous = new MiscellaneousConfig();
	
	public static class MiscellaneousConfig
	{
		@Config.Comment("Allow enchantments to change the weather")
		@Config.Name("Allow Weather Change")
		public boolean EnableWeatherChange = true;
		
		@Config.Comment("XP Orbs give double XP (this is a bug present in older versions)")
		@Config.Name("Double XP Orbs")
		public boolean EnableDoubleXPBug = false;
		
		@Config.Comment("Fixes enchantment that doesn't deal damage when damage is 0 (E.g having weakness while hitting with a wood sword doesn't let the enchantment deal the damage because negative damage cancels damage calculation.)")
		@Config.Name("Fix Zero Damage Enchantments")
	    public boolean EnableFixEnchantment = false;
		
		@Config.Comment("Evasion Enchantment performs a dodge-like action to the Player. Disable if you don't like falling into cliff that is caused by Evasion Enchantment.")
		@Config.Name("Evasion Dodge Roll")
	    public boolean EvasionKnockback = true;

		@Config.Comment("Restrict Quarrying to Ore blocks only.")
		@Config.Name("Quarrying Ore Only")
	    public boolean QuarryingOreOnly = true;
		
		@Config.Comment("Allow Quarrying to work with blocks that are tile entities. I don't know what blocks are these but it may be useful for other mods...")
		@Config.Name("Quarrying Tile Entities")
	    public boolean harvestTile = true;
		
		@Config.Comment("Unregister disabled enchantments")
		@Config.Name("Unregister Disabled Enchants")
		@Config.RequiresMcRestart
		public boolean unregisterDisabled = true;
	}
	
	
	
	@Mod.EventBusSubscriber(modid = somanyenchantments.MODID)
	private static class EventHandler
	{
		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if(event.getModID().equals(somanyenchantments.MODID))
			{
				ConfigManager.sync(somanyenchantments.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
