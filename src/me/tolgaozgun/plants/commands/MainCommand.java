package me.tolgaozgun.plants.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tolgaozgun.plants.PluginMain;
import me.tolgaozgun.plants.util.HopperClass;
import me.tolgaozgun.plants.util.Locale;

public class MainCommand implements CommandExecutor {
	
	private PluginMain plugin = PluginMain.getPlugin(PluginMain.class);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String str,String[] args) {
		if(args.length == 0) {
			
			sender.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "Plugin by Progr4mm3r");
			
		}else{
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("reload")) {
						if(p.isOp() || p.hasPermission("plants.admin.reload")){
							p.sendMessage(Locale.reload);
							plugin.reload();
							return true;
						}else {
							p.sendMessage(Locale.perm);
							return true;
						}
					}else if(args[0].equalsIgnoreCase("hopper")) {
						if(p.hasPermission("plants.user.hopper")) {
							HopperClass.setHopper(p);
						}else {
							p.sendMessage(Locale.perm);
						}
					}
				}
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
				sender.sendMessage(Locale.reload);
				plugin.reload();
				return true;
			}
			
		}
		return true;
	}

}
