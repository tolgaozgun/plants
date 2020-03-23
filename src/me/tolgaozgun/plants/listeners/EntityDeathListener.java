package me.tolgaozgun.plants.listeners;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import me.tolgaozgun.plants.util.BlockConfig;
import me.tolgaozgun.plants.util.HopperClass;

public class EntityDeathListener implements Listener{
	private ArrayList<ItemStack> remainingDrops = new ArrayList<>();
	
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		Block hopperBlock = HopperClass.getNearest(e.getEntity().getLocation());
		if(hopperBlock != null) {
			Hopper hopper = (Hopper) hopperBlock.getState();
			for(ItemStack item: e.getDrops()) {
				if(BlockConfig.getHopperList().contains(item.getType())) {
					hopper.getInventory().addItem(item);
				}else {
					remainingDrops.add(item);
				}
			
			}
			e.getDrops().clear();
			for(ItemStack item: remainingDrops) {
				e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), item);
			}
			remainingDrops.clear();
		}
	}

}
