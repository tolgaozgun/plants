package me.tolgaozgun.plants.listeners;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import me.tolgaozgun.plants.PluginMain;
import me.tolgaozgun.plants.util.BlockConfig;
import me.tolgaozgun.plants.util.HopperClass;

public class BlockBreakListener implements Listener{
	private PluginMain plugin = PluginMain.getPlugin(PluginMain.class);
	private ArrayList<ItemStack> remainingDrops = new ArrayList<ItemStack>();
	

	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Block b2 = e.getBlock();
		if(b2.getType().equals(Material.HOPPER)) {
			if (b2.hasMetadata("plantsHopper") && !b2.getMetadata("plantsHopper").isEmpty()) {
			    if (b2.getMetadata("plantsHopper").get(0).asBoolean()) {
					b2.setMetadata("plantsHopper", new FixedMetadataValue(plugin,false));
			    }
			}
		}
		
		Block hopperBlock = HopperClass.getNearest(e.getBlock().getLocation());
		if(hopperBlock != null) {
			Hopper hopper = (Hopper) hopperBlock.getState();
			for(ItemStack item: b2.getDrops()) {
				if(BlockConfig.getHopperList().contains(item.getType())) {
					hopper.getInventory().addItem(item);
				}else {
					remainingDrops.add(item);
				}
			}
			b2.getDrops().clear();
			b2.setType(Material.AIR);
			e.setCancelled(true);
			for(ItemStack item: remainingDrops) {
				b2.getWorld().dropItemNaturally(b2.getLocation(), item);
			}
			remainingDrops.clear();
		}
	
	}

}
