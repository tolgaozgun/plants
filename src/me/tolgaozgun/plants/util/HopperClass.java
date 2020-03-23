package me.tolgaozgun.plants.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import me.tolgaozgun.plants.PluginMain;

public class HopperClass {
	
	private static PluginMain plugin = PluginMain.getPlugin(PluginMain.class);
	
	private static int range = 5;
	
	public static void init() {
		range = BlockConfig.getRange();
	}
	
	public static Block getNearest(Location loc) {
		World world = loc.getWorld();
		int realRange = range / 2;
		for(int x = loc.getBlockX() - realRange; x < loc.getBlockX() + realRange; x++) {
			for(int z = loc.getBlockZ() - realRange; z < loc.getBlockZ() + realRange; z++) {
				for(int y = loc.getBlockY() - realRange; y < loc.getBlockY() + realRange; y++) {
					Block b = world.getBlockAt(new Location(world,x,y,z));
					
					if(b.getType().equals(Material.HOPPER)){

						if (b.hasMetadata("plantsHopper") && !b.getMetadata("plantsHopper").isEmpty()) {
						    if (b.getMetadata("plantsHopper").get(0).asBoolean()) {
								return b;
						    }
						}
					}
				}
			}
		}
		return null;
		
	}
	
	public static void setHopper(Player p) {
		Block b2 = p.getTargetBlock(null, 200);
		if(b2.getType().equals(Material.HOPPER)) {
			if (b2.hasMetadata("plantsHopper") && !b2.getMetadata("plantsHopper").isEmpty()) {
			    if (b2.getMetadata("plantsHopper").get(0).asBoolean()) {
					p.sendMessage(Locale.hopperalready);
					return;
			    }else {
					b2.setMetadata("plantsHopper", new FixedMetadataValue(plugin,true));
					p.sendMessage(Locale.hopperset);
					return;
			    }
			}else{
				b2.setMetadata("plantsHopper", new FixedMetadataValue(plugin,true));
				p.sendMessage(Locale.hopperset);
				return;
			}
		}else {
			p.sendMessage(Locale.hoppernot);
			return;
		}
	}

}
