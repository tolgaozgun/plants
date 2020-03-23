package me.tolgaozgun.plants.listeners;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.tolgaozgun.plants.util.BlockConfig;
import me.tolgaozgun.plants.util.Locale;

public class BlockPlaceListener implements Listener{
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {

		ArrayList<Material> warningList = BlockConfig.getWarningList();
		Material mat = e.getBlockPlaced().getType();
		Player p = e.getPlayer();
		if(mat.equals(Material.HOPPER)) {
			p.sendMessage(Locale.hopperplaced);
		}
	
		if(warningList.contains(mat)) {
			p.sendMessage(Locale.warn);
		}
	}

}
