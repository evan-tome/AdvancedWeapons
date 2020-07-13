package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class Commands extends API implements CommandExecutor {

	Fates plugin;
	public Commands(Fates instance) {
    plugin = instance;
	}

	    @SuppressWarnings("deprecation")
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if (hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig()) == false) {

				File filename = plugin.createFile("playerdata.yml");
				FileConfiguration nameconfig = plugin.createYamlFile(filename);
				plugin.saveYamlFile(nameconfig, filename);

				if (cmd.getName().equalsIgnoreCase("ignite")) {
					if (args.length != 1) {
						sender.sendMessage("§cError: §4Please specify a player.");
						return true;
					}
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					if (target == null) {
						sender.sendMessage("§cError: §4Player " + args[0] + " not found.");
						return false;
					}
					String name = target.getName();
					target.setFireTicks(1000);
					sender.sendMessage(ChatColor.RED + name + " §6has been ignited");
					target.sendMessage("§6You have been §cignited.");
					return true;
				}
			}
		return false;
	    }
}

	         		    

 
