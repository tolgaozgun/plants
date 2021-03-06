package me.tolgaozgun.plants.listeners;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.inventory.ItemStack;

import me.tolgaozgun.plants.PluginMain;
import me.tolgaozgun.plants.util.BlockConfig;
import me.tolgaozgun.plants.util.HopperClass;

public class BlockSpreadListener implements Listener{
	
	private PluginMain plugin = PluginMain.getPlugin(PluginMain.class);
	 private ArrayList<ItemStack> remainingDrops = new ArrayList<>();
	
	
	@EventHandler
	public void onBlockSpread(BlockSpreadEvent e) {
		

		Location loc = e.getNewState().getBlock().getLocation(); 
		e.getBlock().getState().update();
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {

			@Override
			public void run() {

				checkLocation(loc);
			}
			
		}, 20l);
	}
	
	public void checkLocation(Location loc) {
		ArrayList<Material> blockList = BlockConfig.getBlockList();
		Material mat = loc.getWorld().getBlockAt(loc).getType();
		if(blockList.contains(mat)) {
			Block hopperBlock = HopperClass.getNearest(loc);
			Block plant = loc.getWorld().getBlockAt(loc);
			Collection<ItemStack> items = plant.getDrops();
			if(hopperBlock!=null) {
				Hopper hopper = (Hopper) hopperBlock.getState();
				for(ItemStack item: items) {
					if(BlockConfig.getHopperList().contains(item.getType())) {
						hopper.getInventory().addItem(item);
					}else {
						remainingDrops.add(item);
					}
				}
				for(ItemStack item: remainingDrops) {
					plant.getWorld().dropItemNaturally(plant.getLocation(), item);
				}
				remainingDrops.clear();
			}else {
				for(ItemStack item: items) {
					plant.getWorld().dropItemNaturally(plant.getLocation(), item);
				}
			}
			plant.setType(Material.AIR);	
		}

	}

}
