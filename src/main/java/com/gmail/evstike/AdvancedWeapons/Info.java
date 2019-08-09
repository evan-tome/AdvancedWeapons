package com.gmail.evstike.AdvancedWeapons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

public class Info extends API implements CommandExecutor, TabCompleter {

	Fates plugin;

	public Info(Fates instance) {
		plugin = instance;
	}


	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig()) == false) {
			if (cmd.getName().equalsIgnoreCase("advancedweapons")) {
				if (args.length != 1 || args.length == 1 && args[0].equalsIgnoreCase("help")) {
					String ver = Bukkit.getServer().getPluginManager().getPlugin("AdvancedWeapons")
							.getDescription().getVersion();
					String author = Bukkit.getServer().getPluginManager().getPlugin("AdvancedWeapons")
							.getDescription().getAuthors().toString().replace("[", "");
					sender.sendMessage(ChatColor.GOLD + ("AdvancedWeapons version " + ver + " by " + author + ".")
							.replace("]", ""));
					sender.sendMessage(ChatColor.GOLD + ("Do §a/" + commandLabel + " pages §6for more information."));

					return true;
				}

				List<String> a = Arrays.asList("help", "pages", "enchants", "commands",
						"dust", "discord", "author", "guide", "version",
						"download", "permissions", "admin", "reload");
				if (args.length == 1) {
					if (!a.contains(args[0].toLowerCase())) {
						sender.sendMessage(ChatColor.GOLD + ("Unknown page §c" + '"' + args[0] + '"' + "§6. Please specify a page."));
						sender.sendMessage(ChatColor.GOLD + ("Do §a/" + commandLabel + " pages §6for more information."));
						return true;
					}
				}

				if (args[0].equalsIgnoreCase("pages")) {

					sender.sendMessage("§6-=AdvancedWeapons=-");
					sender.sendMessage("§6Please specify a page");
					sender.sendMessage("§6/" + commandLabel + " §a(page) ");
					sender.sendMessage("§a- §6enchants");
					sender.sendMessage("§a- §6commands");
					sender.sendMessage("§a- §6dust");
					sender.sendMessage("§a- §6discord");
					sender.sendMessage("§a- §6guide");
					sender.sendMessage("§a- §6author");
					sender.sendMessage("§a- §6version");
					sender.sendMessage("§a- §6download");
					if (sender.hasPermission("advancedweapons.admin")) {
						sender.sendMessage("§a- §badmin");
						sender.sendMessage("§a- §bpermissions");
						sender.sendMessage("§a- §breload");
						sender.sendMessage("§6-==========-");
						return true;
					} else {
						sender.sendMessage("§6-==========-");
					}
				}

				if (args[0].equalsIgnoreCase("enchants")) {
					sender.sendMessage("§6-=Enchants=-");
					sender.sendMessage("§a- §7Rush I-III");
					sender.sendMessage("§a- §7Bounce I-III");
					sender.sendMessage("§a- §7Hyper I-III");
					sender.sendMessage("§a- §7Poison I-III");
					sender.sendMessage("§a- §7End Ender I-III");
					sender.sendMessage("§a- §7Tolerance I-III");
					sender.sendMessage("§6-===========-");
					return true;
				}
				if (args[0].equalsIgnoreCase("commands")) {
					sender.sendMessage("§6-=Commands=-");
					sender.sendMessage("§a- §6/advancedweapons {page #} §7Plugin information.");
					sender.sendMessage("§a- §6/ce §7Enchant your items with custom enchantments.");
					sender.sendMessage("§a- §6/dust §7Access your dust vault.");
					sender.sendMessage("§a- §6/weapons §7Receive magical weapons.");
					sender.sendMessage("§a- §6/coinflip §7Challenge someone to a coinflip match.");
					if (sender.hasPermission("advancedweapons.admin")) {
						sender.sendMessage("§a- §b/enchgui §7Select enchantments from a menu.");
						sender.sendMessage("§a- §b/ignite {player} §7Ignite a player on fire.");
						sender.sendMessage("§a- §b/hidme {player} §7Completely hide yourself from a certain player.");
						sender.sendMessage("§a- §b/showme {player} §7Show yourself to a player that you're hidden to.");
					}
					sender.sendMessage("§6-==========-");
				}

				if (args[0].equalsIgnoreCase("dust")) {
					sender.sendMessage("§6-=Dust=-");
					sender.sendMessage("§6Purchase dust using §a/dust");
					sender.sendMessage("§aDust is an AdvancedWeapons currency");
					sender.sendMessage("§aused to enchant your items and buy");
					sender.sendMessage("§amagical weapons. Dust can be crystalized");
					sender.sendMessage("§ainto crystal fuel for ancient machines.");
					sender.sendMessage("§6-============================-");
					return true;
				}
				if (args[0].equalsIgnoreCase("permissions")) {
					if (sender.hasPermission("advancedweapons.admin")) {
						sender.sendMessage("§6-=Permissions=-");
						sender.sendMessage("§a- §badvancedweapons.info");
						sender.sendMessage("§a- §badvancedweapons.ce");
						sender.sendMessage("§a- §badvancedweapons.weapons");
						sender.sendMessage("§a- §badvancedweapons.dust");
						sender.sendMessage("§a- §badvancedweapons.coinflip");
						sender.sendMessage("§a- §badvancedweapons.enchantgui");
						sender.sendMessage("§a- §badvancedweapons.ignite");
						sender.sendMessage("§a- §badvancedweapons.hideme");
						sender.sendMessage("§a- §badvancedweapons.showme");
						sender.sendMessage("§a- §badvancedweapons.admin");
						sender.sendMessage("§a- §badvancedweapons.*");
						sender.sendMessage("§6-===============-");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("admin")) {
					if (sender.hasPermission("advancedweapons.admin")) {
						sender.sendMessage("§6-=Admin Pages=-");
						sender.sendMessage("§a- §6admin");
						sender.sendMessage("§a- §6permissions");
						sender.sendMessage("§a- §6reload");
						sender.sendMessage("§6-===============-");
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("discord")) {
					sender.sendMessage("§6-=§b§lNitro§d§lClub §6Discord§6=-");
					sender.sendMessage("§6Join with invite link");
					sender.sendMessage("§ahttps://discord.gg/5BAM3HV");
					sender.sendMessage("§6-=============================-");
					return true;
				}
				if (args[0].equalsIgnoreCase("author")) {
					sender.sendMessage("§6-=AdvancedWeapons by §aminer328§6=-");
					sender.sendMessage("§6Support him on SpigotMC at");
					sender.sendMessage("§ahttps://www.spigotmc.org/members/miner328.253132/");
					sender.sendMessage("§6-=============================-");
					return true;
				}
				if (args[0].equalsIgnoreCase("download")) {
					sender.sendMessage("§6-=Download AdvancedWeapons=-");
					sender.sendMessage("§6Download on SpigotMC at");
					sender.sendMessage("§ahttps://www.spigotmc.org/resources/advancedweapons-custom-enchantments-items-dust.67760/");
					sender.sendMessage("§6-=============================-");
					return true;
				}
				if (args[0].equalsIgnoreCase("guide")) {
					sender.sendMessage("§6-=Guide=-");
					sender.sendMessage("§6Want to find out how to use AdvancedWeapons? Head to");
					sender.sendMessage("§ahttps://www.advancedweapons.weebly.com/");
					sender.sendMessage("§6-=============================-");
					return true;
				}
				if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("ver")) {
					String ver = Bukkit.getServer().getPluginManager().getPlugin("AdvancedWeapons")
							.getDescription().getVersion();
					sender.sendMessage("§6-=Version=-");
					sender.sendMessage(ChatColor.GREEN + ("AdvancedWeapons version " + ver + ".").replace("]", ""));
					sender.sendMessage("§6-=============================-");
					return true;
				}
				if (args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("advancedweapons.reload")) {
						Logger log = plugin.getLogger();
						plugin.reloadConfig();
						sender.sendMessage(plugin.getConfig().getString("reload-msg").replace("&", "§"));
						System.out.println("[AdvancedWeapons] Config reloaded");
						return true;

					}
				}
				if (args[0].equalsIgnoreCase("reload")) {
					if (!(sender.hasPermission("advancedweapons.reload"))) {
						sender.sendMessage("§cYou don't have permission to perform this command.");
						return false;
					}
				}

				if (args[0].equalsIgnoreCase("reload")) {
					if (!(sender instanceof Player)) {
						Logger log = plugin.getLogger();
						plugin.reloadConfig();
						System.out.println("[AdvancedWeapons] Config reloaded");
						return true;

					}
				}
				if (args[0].equalsIgnoreCase("admin")) {
					if (!(sender.hasPermission("advancedweapons.admin"))) {
						sender.sendMessage("§cYou don't have permission to perform this command.");
						return false;
					}
				}

				if (args[0].equalsIgnoreCase("permissions")) {
					if (!(sender.hasPermission("advancedweapons.permissions"))) {
						sender.sendMessage("§cYou don't have permission to perform this command.");
						return false;
					}
				}
			}
		}
			return false;
		}
		public List<String> onTabComplete (CommandSender sender, Command cmd, String commandLabel, String[]args){
			if (cmd.getName().equalsIgnoreCase("advancedweapons")) {
				List<String> a = Arrays.asList("help", "pages", "enchants", "commands",
						"dust", "discord", "author", "guide", "version",
						"download");

				List<String> b = Arrays.asList("admin", "permissions", "reload");

				List<String> f = Lists.newArrayList();
				if (args.length == 1) {
					for (String s : a) {
						if (s.startsWith(args[0])) f.add(s);
					}
					if (sender.hasPermission("advancedweapons.admin")) {
						for (String m : b) {
							if (m.startsWith(args[0])) f.add(m);
						}
					}
				}
				return f;
			}

			if (args.length > 1) {
				ArrayList<String> noInput = new ArrayList<String>();

				noInput.add("");

				return noInput;
			}
			return null;
		}
	}

	         		    

 
