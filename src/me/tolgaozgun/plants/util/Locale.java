package me.tolgaozgun.plants.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import me.tolgaozgun.plants.PluginMain;

public class Locale {

	private static PluginMain plugin = PluginMain.getPlugin(PluginMain.class);
	private static FileConfiguration config = plugin.getLocaleConfig();
	public static String warn,reload,perm,hoppernot,hopperalready,hopperset,hopperplaced;
	
	public static void init() {
		warn = getString("warning","&6[Plants] &bThis plant will be broken once grown!");
		perm = getString("no-permission","&6[Plants] &4&lNot permitted to use this command!");
		reload = getString("reload","&6[Plants] &4Plants &breloaded successfully!");
		hoppernot = getString("hopper-not-hopper","&6[Plants] &4This is not a hopper!");
		hopperalready = getString("hopper-already-collector","&6[Plants] &4This is already a &6Collector Hopper&b!");
		hopperset = getString("hopper-set","&6[Plants] &4Collector Hopper &bis successfully set!");
		hopperplaced = getString("hopper-placed","&6[Plants] &bYou placed a hopper! To make this a &6Collector Hopper&b, use &4/plants hopper");
	}
	
	private static String getString(String str, String str2) {
		return ChatColor.translateAlternateColorCodes('&', config.getString(str,str2));
	}
}
