package me.tolgaozgun.plants;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.tolgaozgun.plants.commands.MainCommand;
import me.tolgaozgun.plants.listeners.BlockBreakListener;
import me.tolgaozgun.plants.listeners.BlockGrowListener;
import me.tolgaozgun.plants.listeners.BlockPlaceListener;
import me.tolgaozgun.plants.listeners.BlockSpreadListener;
import me.tolgaozgun.plants.listeners.EntityDeathListener;
import me.tolgaozgun.plants.util.BlockConfig;
import me.tolgaozgun.plants.util.HopperClass;
import me.tolgaozgun.plants.util.Locale;

public class PluginMain extends JavaPlugin {
	private File blockFile, localeFile;
	private FileConfiguration blockConfig, localeConfig;
	
	public void onEnable() {
		createFiles(); // Loads the .yml files
		getServer().getPluginManager().registerEvents(new BlockGrowListener(), this);
		getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
		getServer().getPluginManager().registerEvents(new BlockSpreadListener(), this);
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
		getServer().getPluginCommand("plants").setExecutor(new MainCommand());
		BlockConfig.init();
		Locale.init();
		HopperClass.init();
	}

	public void reload() {
		getServer().getConsoleSender().sendMessage("Plants reloading..");
		try {
			blockConfig.load(blockFile);
			localeConfig.load(localeFile);
		} catch (IOException | InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BlockConfig.init();
		Locale.init();
		HopperClass.init();
		getServer().getConsoleSender().sendMessage("Plants reloaded successfully!");
	}
	
	
	public void onDisable() {
		
	}
	
	public File getBlockFile() {
		return blockFile;
	}
	
	public File getLocaleFile() {
		return localeFile;
	}

	public FileConfiguration getBlockConfig() {
		return blockConfig;
	}
	
	public FileConfiguration getLocaleConfig() {
		return localeConfig;
	}
	

	
	private void createFiles() {
		blockFile = new File(getDataFolder(), "blocks.yml");
		localeFile = new File(getDataFolder(), "messages.yml");
		
		
		if (!blockFile.exists()) {
			blockFile.getParentFile().mkdirs();
			saveResource("blocks.yml", true);
		}	
		
		if (!localeFile.exists()) {
			localeFile.getParentFile().mkdirs();
			saveResource("messages.yml", true);
		}	
		
		blockConfig = new YamlConfiguration();
		localeConfig = new YamlConfiguration();
		
		try {
			blockConfig.load(blockFile);
			localeConfig.load(localeFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
}
