package me.tolgaozgun.plants.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import me.tolgaozgun.plants.PluginMain;

public class BlockConfig {
	
	private static PluginMain plugin = PluginMain.getPlugin(PluginMain.class);
	private static ArrayList<Material> blockList = new ArrayList<>();
	private static ArrayList<Material> warningList = new ArrayList<>();
	private static ArrayList<Material> hopperList = new ArrayList<>();
	private static FileConfiguration config = plugin.getBlockConfig();
	private static int range;
	
	
	public static void init() {
		blockList.clear();
		warningList.clear();
		hopperList.clear();
		for(String s: config.getStringList("blacklisted-blocks")) {
			try {
				Material m = Material.getMaterial(s);
				blockList.add(m);
			}catch(Exception e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[PLANTS] UNABLE TO ADD " + s);
				continue;
			}
		}
		for(String s: config.getStringList("warning-blocks")) {
			try {
				Material m = Material.getMaterial(s);
				warningList.add(m);
			}catch(Exception e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[PLANTS] UNABLE TO ADD " + s);
				continue;
			}
		}
		for(String s: config.getStringList("hopper-blocks")) {
			try {
				Material m = Material.getMaterial(s);
				hopperList.add(m);
			}catch(Exception e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "[PLANTS] UNABLE TO ADD " + s);
				continue;
			}
		}
		if(config.getBoolean("bamboo")) {
			blockList.add(Material.BAMBOO);
		}
		if(config.getBoolean("kelp")) {
			blockList.add(Material.KELP);
		}
		if(config.getBoolean("chorus")) {
			blockList.add(Material.CHORUS_FLOWER);
		}
		if(config.getBoolean("warn-bamboo")) {
			warningList.add(Material.BAMBOO_SAPLING);
		}
		if(config.getBoolean("warn-kelp")) {
			warningList.add(Material.KELP);
		}
		if(config.getBoolean("warn-chorus")) {
			warningList.add(Material.CHORUS_PLANT);
			warningList.add(Material.CHORUS_FLOWER);
		}
		range = config.getInt("range",5);
	}

	public static int getRange() {
		return range;
	}
	
	public static ArrayList<Material> getBlockList(){
		return blockList;
	}
	
	public static ArrayList<Material> getWarningList(){
		return warningList;
	}
	
	public static ArrayList<Material> getHopperList(){
		return hopperList;
	}
}
