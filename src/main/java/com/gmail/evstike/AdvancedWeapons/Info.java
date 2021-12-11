package com.gmail.evstike.AdvancedWeapons;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
public class Info extends ConfigGUI implements CommandExecutor, TabCompleter {
	Fates plugin;

	public Info(Fates instance) {
		plugin = instance;
	}

	List<String> max = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!this.hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig()) && cmd.getName().equalsIgnoreCase("advancedweapons")) {

			File filename = plugin.createFile("playerdata.yml");
			FileConfiguration nameconfig = plugin.createYamlFile(filename);
			plugin.saveYamlFile(nameconfig, filename);

			File mname = plugin.createFile("machines.yml");
			FileConfiguration mconfig = plugin.createYamlFile(mname);
			plugin.saveYamlFile(mconfig, mname);

			List<String> a = Arrays.asList("help", "pages", "stats", "enchants", "commands", "dust", "discord", "author", "guide", "ver", "version", "download",
					"permissions", "admin", "config", "reload", "rl");

			if (args.length == 0 || args.length == 1 && args[0].equals("help")) {
				String ver = Bukkit.getServer().getPluginManager().getPlugin("AdvancedWeapons").getDescription().getVersion();
				String author = Bukkit.getServer().getPluginManager().getPlugin("AdvancedWeapons").getDescription().getAuthors().toString().replace("[", "");
				sender.sendMessage(ChatColor.GOLD + ("AdvancedWeapons version §b" + ver + "§6 by §b" + author + "§a.").replace("]", ""));
				sender.sendMessage(ChatColor.GOLD + "Use §a/" + commandLabel + " pages §6for more information.");
			}
			if (args.length > 0 && !a.contains(args[0].toLowerCase())) {
				sender.sendMessage(ChatColor.GOLD + "Unknown page §c\"" + args[0] + '"' + "§6. Please specify a page.");
				sender.sendMessage(ChatColor.GOLD + "Use §a/" + commandLabel + " pages §6for more information.");
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("pages")) {
					sender.sendMessage("§6-=AdvancedWeapons=-");
					sender.sendMessage("§6Please specify a page");
					sender.sendMessage("§6/" + commandLabel + " §a(page) ");
					sender.sendMessage("§a- §6enchants");
					sender.sendMessage("§a- §6commands");
					sender.sendMessage("§a- §6dust");
					sender.sendMessage("§a- §6discord");
					//sender.sendMessage("§a- §6guide");
					sender.sendMessage("§a- §6author");
					sender.sendMessage("§a- §6version");
					sender.sendMessage("§a- §6download");
					if (sender.hasPermission("advancedweapons.admin")) {
						sender.sendMessage("§a- §badmin");
						sender.sendMessage("§a- §bpermissions");
						sender.sendMessage("§a- §bconfig");
						sender.sendMessage("§a- §breload");
					}

					sender.sendMessage("§6-================-");
				}
				if (args[0].equalsIgnoreCase("stats")) {
					sender.sendMessage("§6-=" + sender.getName() +"'s Stats=-");
					
					File pfile = plugin.createFile("playerdata.yml");
					FileConfiguration pconf = plugin.createYamlFile(pfile);
					Player p = (Player) sender;
					
					ConfigurationSection item = pconf.getConfigurationSection("playerdata." + p.getUniqueId());
					int dust = item.getInt("dust");
					int wins = item.getInt("coinflip.wins");
					int losses = item.getInt("coinflip.losses");
					double wl = wins*1.0/losses;
					sender.sendMessage("§aGeneral:");
					sender.sendMessage("§a- §6Dust: §7" + dust);
					sender.sendMessage("§aCoinflip:");
					sender.sendMessage("§a- §6Wins: §7" + wins);
					sender.sendMessage("§a- §6Losses: §7" + losses);
					sender.sendMessage("§a- §6W/L: §7" + Math.round(wl*100)/100.0);
					
					sender.sendMessage("§6-================-");
					max.clear();
				}
				if (args[0].equalsIgnoreCase("enchants")) {
					sender.sendMessage("§6-=Enchants=-");
					ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
					String fname = "";

					for (String key : section.getKeys(false)) {
						section.get(key);
						ConfigurationSection item = section.getConfigurationSection(key);
						String i = item.getString("name").replace('&', '§');
						String name = ChatColor.stripColor(i);
						String firstWord = name.substring(0, name.lastIndexOf(" "));

						if (maxEnchLevel(name).equals("I")) {
							fname = firstWord + " I";
						}
						if (!maxEnchLevel(name).equals("I")) {
							fname = firstWord + " I-" + maxEnchLevel(name);
						}
						if (!max.contains(fname)) {
							max.add(fname);
						}
					}
					for (String uname : max) {
						sender.sendMessage("§a- §7" + uname);
					}
					sender.sendMessage("§bCreate and edit custom enchantments with §6/ceditor");
					sender.sendMessage("§6-================-");
					max.clear();
				}
				if (args[0].equalsIgnoreCase("commands")) {
					sender.sendMessage("§6-=Commands=-");
					sender.sendMessage("§a- §6/advancedweapons {page} §7Plugin information.");
					sender.sendMessage("§a- §6/ce §7Enchant your items with custom enchantments.");
					sender.sendMessage("§a- §6/weapons §7Receive magical weapons.");
					sender.sendMessage("§a- §6/machines §7Power up ancient machinery.");
					sender.sendMessage("§a- §6/dust §7Access your Dust vault.");
					sender.sendMessage("§a- §6/coinflip §7Challenge someone to a coinflip match.");
					if (sender.hasPermission("advancedweapons.admin")) {
						sender.sendMessage("§a- §b/ceditor §7Create and edit custom enchantments.");
						sender.sendMessage("§a- §b/enchgui §7Select enchantments from a menu.");
						sender.sendMessage("§a- §b/ignite {player} §7Ignite a player on fire.");
						sender.sendMessage("§a- §b/hidden {option} {player} §7Completely hide yourself from players.");
						sender.sendMessage("§a- §b/dust give {amount} {player} §7Give a player Dust.");
					}
					sender.sendMessage("§6-================-");
				}
				if (args[0].equalsIgnoreCase("dust")) {
					sender.sendMessage("§6-=Dust=-");
					sender.sendMessage("§6Purchase Dust using §a/dust");
					sender.sendMessage("§7Dust is an AdvancedWeapons currency used to");
					sender.sendMessage("§7enchant your items and buy magical weapons");
					sender.sendMessage("§7Legends say that Dust can be used to power");
					sender.sendMessage("§7ancient machinery.");
					sender.sendMessage("§6-================-");
				}
				if (args[0].equalsIgnoreCase("permissions") && sender.hasPermission("advancedweapons.admin")) {
					sender.sendMessage("§6-=Permissions=-");
					sender.sendMessage("§a- §badvancedweapons.player");
					sender.sendMessage("§a- §badvancedweapons.advancedweapons");
					sender.sendMessage("§a- §badvancedweapons.ce");
					sender.sendMessage("§a- §badvancedweapons.weapons");
					sender.sendMessage("§a- §badvancedweapons.machines");
					sender.sendMessage("§a- §badvancedweapons.dust");
					sender.sendMessage("§b- §badvancedweapons.dust.give");
					sender.sendMessage("§a- §badvancedweapons.coinflip");
					sender.sendMessage("§b- §badvancedweapons.ceditor");
					sender.sendMessage("§b- §badvancedweapons.enchantgui");
					sender.sendMessage("§b- §badvancedweapons.ignite");
					sender.sendMessage("§b- §badvancedweapons.hidden");
					sender.sendMessage("§b- §badvancedweapons.reload");
					sender.sendMessage("§b- §badvancedweapons.config");
					sender.sendMessage("§b- §badvancedweapons.admin");
					sender.sendMessage("§b- §badvancedweapons.*");
					sender.sendMessage("§6-================-");
				}
				if (args[0].equalsIgnoreCase("admin") && sender.hasPermission("advancedweapons.admin")) {
					sender.sendMessage("§6-=Admin Pages=-");
					sender.sendMessage("§a- §badmin");
					sender.sendMessage("§a- §bpermissions");
					sender.sendMessage("§a- §breload");
					sender.sendMessage("§a- §bconfig");
					sender.sendMessage("§6-================-");
				}
				if (args[0].equalsIgnoreCase("discord")) {
					sender.sendMessage("§6-=AdvancedWeapons Discord=-");
					sender.sendMessage("§aNeed AdvancedWeapons support?");
					sender.sendMessage("§aJoin with invite link:");
					sender.sendMessage("§bhttps://discord.gg/gUKbxJm");
					sender.sendMessage("§6-================-");
				}
				if (args[0].equalsIgnoreCase("author")) {
					sender.sendMessage("§6-=AdvancedWeapons by §aminer328§6=-");
					sender.sendMessage("§aSupport him on SpigotMC at");
					sender.sendMessage("§bhttps://www.spigotmc.org/members/miner328.253132/");
					sender.sendMessage("§6-================-");
				}
				if (args[0].equalsIgnoreCase("download")) {
					sender.sendMessage("§6-=Download AdvancedWeapons=-");
					sender.sendMessage("§aDownload on SpigotMC at");
					sender.sendMessage("§bhttps://www.spigotmc.org/resources/advancedweapons-custom-enchantments-items-dust.67760/");
					sender.sendMessage("§6-================-");
				}
				if (args[0].equalsIgnoreCase("guide")) {
					sender.sendMessage("§6-=Guide=-");
					sender.sendMessage("§aWant to find out how to use AdvancedWeapons? Head to");
					sender.sendMessage("§bhttps://www.advancedweapons.weebly.com/");
					sender.sendMessage("§6-================-");
				}
				if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("ver")) {
					String ver = Bukkit.getServer().getPluginManager().getPlugin("AdvancedWeapons").getDescription().getVersion();
					sender.sendMessage("§6-=Version=-");
					sender.sendMessage("§aAdvancedWeapons version §b" + ver.replace("]", ""));
					sender.sendMessage("§6-================-");
				}
				//if (args[0].equalsIgnoreCase("github") || args[0].equalsIgnoreCase("git")) {
				//sender.sendMessage("§6-=Github=-");
				//sender.sendMessage("§aWant to help improve AdvancedWeapons? Head to");
				//sender.sendMessage("§bhttps://github.com/Evstike/AdvancedWeapons/");
				//sender.sendMessage("§6-================-");
				//}
				if ((args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) && sender.hasPermission("advancedweapons.reload")) {
					plugin.saveDefaultConfig();
					plugin.reloadConfig();
					plugin.saveYamlFile(nameconfig, filename);
					plugin.saveYamlFile(mconfig, mname);
					sender.sendMessage(plugin.getConfig().getString("reload-msg").replace('&', '§'));
					System.out.println("[AdvancedWeapons] Config reloaded");
				}
				if ((args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) && !sender.hasPermission("advancedweapons.reload")) {
					sender.sendMessage("§cYou don't have permission to perform this command.");
				}
				if ((args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) && !(sender instanceof Player)) {
					plugin.saveDefaultConfig();
					plugin.reloadConfig();
					plugin.saveYamlFile(nameconfig, filename);
					plugin.saveYamlFile(mconfig, mname);
					System.out.println("[AdvancedWeapons] Config reloaded");
				}
				if (args[0].equalsIgnoreCase("config") && sender.hasPermission("advancedweapons.config")) {
					if (sender instanceof Player) {
						openGUI((Player) sender);
					} else {
						sender.sendMessage("§cOnly players can use this command.");
					}
				}
				if (args[0].equalsIgnoreCase("config") && !sender.hasPermission("advancedweapons.admin")) {
					sender.sendMessage("§cYou don't have permission to perform this command.");
				}
				if (args[0].equalsIgnoreCase("admin") && !sender.hasPermission("advancedweapons.admin")) {
					sender.sendMessage("§cYou don't have permission to perform this command.");
				}
				if (args[0].equalsIgnoreCase("permissions") && !sender.hasPermission("advancedweapons.permissions")) {
					sender.sendMessage("§cYou don't have permission to perform this command.");
				}
			}
			if (plugin.getConfig().getBoolean("discord-message")) {
				Random rand = new Random();
				int n = rand.nextInt(100) + 1;
				if (n <= 5) {
					sender.sendMessage("§aNeed AdvancedWeapons support? Join our Discord:");
					sender.sendMessage("§bhttps://discord.gg/gUKbxJm");
				}
			}
		}
		return false;
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("advancedweapons")) {
			if (args.length > 1) {
				ArrayList<String> noInput = new ArrayList();
				noInput.add("");
				return noInput;
			} else {
				return null;
			}
		} else {
			List<String> a = Arrays.asList("help", "pages", "stats", "enchants", "commands", "dust", "discord", "author", "version", "download");
			List<String> b = Arrays.asList("admin", "permissions", "reload", "config");
			List<String> f = Lists.newArrayList();
			if (args.length == 1) {
				for (String m : a) {
					if (m.startsWith(args[0])) {
						f.add(m);
					}
				}

				if (sender.hasPermission("advancedweapons.admin")) {
					for (String m : b) {
						if (m.startsWith(args[0])) {
							f.add(m);
						}
					}
				}
			}

			return f;
		}
	}
	public String maxEnchLevel(String s) {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
		for (String key : section.getKeys(false)) {
			ConfigurationSection item = section.getConfigurationSection(key);
			String i = item.getString("name".replace("&", "§"));
			String name = ChatColor.stripColor(i);
			String lastWord = name.substring(name.lastIndexOf(" ") + 1);
			String firstWord = s.substring(0, s.lastIndexOf(" "));

			if(section.contains(((firstWord + " I").toLowerCase()).replace(" ","-"))) {
				if(!section.contains(((firstWord + " II").toLowerCase()).replace(" ","-"))) {
					return "I";
				}
			}
			if(section.contains(((firstWord + " II").toLowerCase()).replace(" ","-"))) {
				if(!section.contains(((firstWord + " III").toLowerCase()).replace(" ","-"))) {
					return "II";
				}
			}
			if(section.contains(((firstWord + " III").toLowerCase()).replace(" ","-"))) {
				if(!section.contains(((firstWord + " IV").toLowerCase()).replace(" ","-"))) {
					return "III";
				}
			}
			if(section.contains(((firstWord + " IV").toLowerCase()).replace(" ","-"))) {
				if(!section.contains(((firstWord + " V").toLowerCase()).replace(" ","-"))) {
					return "IV";
				}
			}
			if(section.contains(((firstWord + " V").toLowerCase()).replace(" ","-"))) {
				return "V";
			}
		}
		return "I";
	}
}