package com.gmail.evstike.AdvancedWeapons;

import org.apache.logging.log4j.core.util.UuidUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Commands extends API implements CommandExecutor {

	Fates plugin;
	public Commands(Fates instance) {
    plugin = instance;
	}

	public static List<String> hidden = new ArrayList<String>();
	List<String> hide = new ArrayList<String>();

	    @SuppressWarnings("deprecation")
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			if(hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig())==false) {
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
				if (cmd.getName().equalsIgnoreCase("hidden")) {
					if (!(sender instanceof Player)) {
						sender.sendMessage("§cError: §4Only Players can use this command!");
						return true;
					}
					if (args.length != 1) {
						sender.sendMessage("§cError: §4Please specify a player.");
						return false;
					}
					Player s = (Player) sender;
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					UUID sn = s.getPlayer().getUniqueId();
					if (target == null) {
						sender.sendMessage("§cError: §4Player " + args[0] + " not found.");
						return true;
					}
					String name = target.getName();
					if (hidden.contains(sn + name)) {
						sender.sendMessage("§cError: §4You are already hidden from " + name + ".");
						return true;
					}
					if (!hidden.contains(sn + name)) {
						target.hidePlayer(s);
						sender.sendMessage("§6You have been hidden from: " + name);
						hidden.add(sn + name);
						for (String Line : hidden) {
							if (Line.contains(sn.toString())) {
								hide.add(Line);
							}
						}
						sender.sendMessage(ChatColor.GOLD + "Hidden from: " + ChatColor.GREEN + hide.toString()
								.replace("[", "").replace("]", "").replace(",", "§6,§a").replace(sn.toString(), ""));
						hide.clear();
					}
				}
				if (cmd.getName().equalsIgnoreCase("hideme")) {
					if (!(sender instanceof Player)) {
						sender.sendMessage("§cError: §4Only Players can use this command!");
						return true;
					}
					if (args.length != 1) {
						sender.sendMessage("§cError: §4Please specify a player.");
						return false;
					}
					Player s = (Player) sender;
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					UUID sn = s.getPlayer().getUniqueId();
					if (target == null) {
						sender.sendMessage("§cError: §4Player " + args[0] + " not found.");
						return true;
					}
					String name = target.getName();
					if (hidden.contains(sn + name)) {
						sender.sendMessage("§cError: §4You are already hidden from " + name + ".");
						return true;
					}
					if (!hidden.contains(sn + name)) {
						target.hidePlayer(s);
						sender.sendMessage("§6You have been hidden from: " + name);
						hidden.add(sn + name);
						for (String Line : hidden) {
							if (Line.contains(sn.toString())) {
								hide.add(Line);
							}
						}
						sender.sendMessage(ChatColor.GOLD + "Hidden from: " + ChatColor.GREEN + hide.toString()
								.replace("[", "").replace("]", "").replace(",", "§6,§a").replace(sn.toString(), ""));
						hide.clear();
					}
				}
				if (cmd.getName().equalsIgnoreCase("showme")) {
					if (!(sender instanceof Player)) {
						sender.sendMessage("§cError: §4Only Players can use this command!");
						return true;
					}
					if (args.length != 1) {
						sender.sendMessage("§cError: §4Please specify a player.");
						return false;
					}

					Player s = (Player) sender;
					UUID sn = s.getPlayer().getUniqueId();
					Player target = Bukkit.getServer().getPlayerExact(args[0]);
					if (target == null) {
						sender.sendMessage("§cError: §4Player " + args[0] + " not found.");
						return true;
					}
					String name = target.getName();
					if (!hidden.contains(sn + name)) {
						sender.sendMessage("§cError: §4You are not hidden from " + name + ".");
						return true;
					}
					if (hidden.contains(sn + name)) {
						target.showPlayer(s);
						sender.sendMessage("§6You have been shown to: " + name);
						hidden.remove(sn + name);
						for (String Line : hidden) {
							if (Line.contains(sn.toString())) {
								hide.add(Line);
							}
						}
						if (!hide.isEmpty()) {
							sender.sendMessage(ChatColor.GOLD + "Hidden from: " + ChatColor.GREEN + hide.toString()
									.replace("[", "").replace("]", "").replace(",", "§6,§a").replace(sn.toString(), ""));
						}
						if (hide.isEmpty()) {
							sender.sendMessage(ChatColor.GOLD + "Hidden from: " + ChatColor.RED + "Nobody");
						}
						hide.clear();
						return true;
					}
				}
			}
		return false;
	    }
}

	         		    

 
