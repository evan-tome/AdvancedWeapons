package com.gmail.evstike.AdvancedWeapons;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class DustGUI extends API implements CommandExecutor, Listener {

	Fates plugin;

	public DustGUI(Fates instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig()) == false) {
			if (cmd.getName().equalsIgnoreCase("dust")) {

				File name = plugin.createFile("playerdata.yml");
				FileConfiguration nameconfig = plugin.createYamlFile(name);
				plugin.saveYamlFile(nameconfig, name);

				if (args.length != 1 && args.length != 2 && args.length != 3 || args.length == 1 && !args[0].equals("give") ||
						args.length == 2 && !args[0].equals("give") || args.length == 3 && !args[0].equals("give")) {
					if (sender instanceof Player) {
						Player player = (Player) sender;
						openGUI(player);
						if (!nameconfig.contains("playerdata." + player.getUniqueId())) {
							nameconfig.createSection("playerdata." + player.getUniqueId());
							nameconfig.set("playerdata." + player.getUniqueId() + ".name", player.getName());
							plugin.saveYamlFile(nameconfig, name);
							return false;
						}
					}
					if (!(sender instanceof Player)) {
						sender.sendMessage("§cError: §4Only Players can use this command!");
					}
				}
				if (args.length == 1 || args.length == 2 || args.length == 3) {
					String p = null;
					int amount = 1;
					ItemStack g = dust().clone();
					g.setAmount(amount);
					if (args[0].equals("give")) {
						if (sender.hasPermission("advancedweapons.dust.give")) {
							if (args.length == 1 || args.length == 2) {
								p = sender.getName();
							}
							if (args.length == 2) {
								Player target = Bukkit.getServer().getPlayerExact(args[1]);
								if (target != null) {
									p = target.getName();
									sender.sendMessage("§6You gave §b" + target.getName() + " §a" + amount + " §6Dust.");
								}
								if (target == null) {
									if (!isInt(args[1])) {
										sender.sendMessage("§cError: §4Player " + args[1] + " not found.");
										return false;
									}
									if (isInt(args[1])) {
										if (sender instanceof Player) {
											amount = Integer.parseInt(args[1]);
											p = sender.getName();
										}
									}
								}
							}
							if (args.length == 3) {
								if (isInt(args[1])) {
									Player target = Bukkit.getServer().getPlayerExact(args[2]);
									p = target.getName();
									amount = Integer.parseInt(args[1]);
									if (target != null) {
										sender.sendMessage("§6You gave §b" + target.getName() + " §a" + amount + " §6Dust.");
									}
									if (target == null) {
										sender.sendMessage("§cError: §4Player " + args[2] + " not found.");
										return false;
									}
								}
								if (!isInt(args[1])) {
									sender.sendMessage("§cError: §4" + args[1] + " is not a number.");
									return false;
								}
							}
							if (p == sender.getName()) {
								if (!(sender instanceof Player)) {
									sender.sendMessage("§cError: §4Only Players can use this command!");
								}
								if (sender instanceof Player) {
									Player pl = Bukkit.getPlayer(p);
									g.setAmount(amount);
									pl.getInventory().addItem(g);
									pl.sendMessage("§6You have received §a" + amount + " §6Dust.");
								}
							}
							if (!(p == sender.getName())) {
								if (!(p == null)) {
									Player pl = Bukkit.getPlayer(p);
									g.setAmount(amount);
									pl.getInventory().addItem(g);
									pl.sendMessage("§6You have received §a" + amount + " §6Dust.");
								}
							}
						}
						if (!sender.hasPermission("advancedweapons.dust.give")) {
							Player player = (Player) sender;
							openGUI((Player) sender);
							if (!nameconfig.contains("playerdata." + player.getUniqueId())) {
								nameconfig.createSection("playerdata." + player.getUniqueId());
								plugin.saveYamlFile(nameconfig, name);
							}
						}
					}
				}
			}
		}
		return false;
	}

	private ItemStack dust() {
		List<String> Lore = new ArrayList<String>();
		ItemStack glow = new ItemStack(XMaterial.GUNPOWDER.parseMaterial(), 1);
		ItemMeta glowMeta = glow.getItemMeta();
		glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
		Lore.add("§7This Dust has magical properties");
		Lore.add("§7which make it a valuable currency.");
		glowMeta.setLore(Lore);
		glow.setItemMeta(glowMeta);
		return glow;
	}

	private void openGUI(Player player) {

		File name = plugin.createFile("playerdata.yml");
		FileConfiguration nameconfig = plugin.createYamlFile(name);
		plugin.saveYamlFile(nameconfig, name);

		Inventory inv = Bukkit.createInventory(null, 9, "Dust Bank");
		int quant = plugin.getConfig().getInt("dust-trade");
		ItemStack gun = new ItemStack(XMaterial.GUNPOWDER.parseMaterial());
		ItemMeta gunMeta = gun.getItemMeta();
		ItemStack glow = new ItemStack(XMaterial.SUNFLOWER.parseMaterial());
		ItemMeta glowMeta = glow.getItemMeta();
		ItemStack chest = new ItemStack(XMaterial.CHEST.parseMaterial());
		ItemMeta chestMeta = chest.getItemMeta();
		ItemStack blaze = new ItemStack(XMaterial.GLASS_BOTTLE.parseMaterial());
		ItemMeta blazeMeta = blaze.getItemMeta();
		ItemStack mat = new ItemStack(XMaterial.matchXMaterial(plugin.getConfig().getString("dust-material")).get().parseMaterial());
		ItemMeta matMeta = mat.getItemMeta();
		mat.setAmount(quant);

		List<String> Lore = new ArrayList<String>();
		gunMeta.setDisplayName(ChatColor.GREEN + "Purchase Dust");
		int num = plugin.getConfig().getInt("dust-cost");
		Lore.add("§7This Dust has magical properties");
		Lore.add("§7which make it a valuable currency.");
		Lore.add("");
		Lore.add("§a" + num + " exp");
		gunMeta.setLore(Lore);
		gun.setItemMeta(gunMeta);

		List<String> Lore2 = new ArrayList<String>();
		glowMeta.setDisplayName(ChatColor.GREEN + "Withdraw Dust");
		Lore2.add("§7Withdraw Dust from your vault in");
		Lore2.add("§7the bank of the realm down below.");
		Lore2.add("");
		Lore2.add("§bLEFT-CLICK to withdraw 1 Dust");
		Lore2.add("§bRIGHT-CLICK to withdraw ALL Dust");
		glowMeta.setLore(Lore2);
		glow.setItemMeta(glowMeta);

		List<String> Lore3 = new ArrayList<String>();
		chestMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dust Balance");
		chest.setItemMeta(chestMeta);
		int num3 = nameconfig.getInt("playerdata." + player.getUniqueId() + ".dust");
		Lore3.add("§7Check how much Dust you stored in");
		Lore3.add("§7the bank of the realm down below.");
		Lore3.add("");
		Lore3.add("§7You have " + "§a" + num3 + " §7dust");
		chestMeta.setLore(Lore3);
		chest.setItemMeta(chestMeta);

		List<String> Lore4 = new ArrayList<String>();
		blazeMeta.setDisplayName(ChatColor.GREEN + "Deposit Dust");
		Lore4.add("§7Deposit Dust to a safely locked vault");
		Lore4.add("§7in the bank of the realm down below.");
		Lore4.add("");
		Lore4.add("§bLEFT-CLICK to deposit 1 Dust");
		Lore4.add("§bRIGHT-CLICK to deposit ALL Dust");
		blazeMeta.setLore(Lore4);
		blaze.setItemMeta(blazeMeta);

		List<String> Lore5 = new ArrayList<String>();
		matMeta.setDisplayName(ChatColor.GREEN + "Trade for Dust");
		String dia = StringUtils.capitaliseAllWords(plugin.getConfig()
				.getString("dust-material").toLowerCase().replace("_", " "));
		Lore5.add(ChatColor.GRAY + "Convert §b" + dia + "s" + " §7to Dust.");
		Lore5.add("");
		Lore5.add(ChatColor.AQUA + "" + quant + "x " + dia);
		matMeta.setLore(Lore5);
		mat.setItemMeta(matMeta);

		if(plugin.getConfig().getBoolean("enable-dust-cost")) {
			inv.setItem(0, gun);
		}
		if(plugin.getConfig().getBoolean("enable-dust-material")) {
			inv.setItem(2, mat);
		}
		inv.setItem(4, glow);
		inv.setItem(6, blaze);
		inv.setItem(8, chest);

		player.openInventory(inv);
	}

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {

		File name = plugin.createFile("playerdata.yml");
		FileConfiguration nameconfig = plugin.createYamlFile(name);
		plugin.saveYamlFile(nameconfig, name);

		if (!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Dust Bank"))
			return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR
				|| !event.getCurrentItem().hasItemMeta()) {

			return;
		}
		if (event.getClickedInventory().getType().equals(InventoryType.CHEST)) {
			if (event.getRawSlot() == 0) {
				int num = plugin.getConfig().getInt("dust-cost");
				int xp = player.getTotalExperience();
				if (xp >= num) {
					player.getInventory().addItem(dust());
					player.giveExp(-num);
				}
				if (event.getRawSlot() == 0) {
					if (xp < num) {

						player.sendMessage(ChatColor.AQUA + "You have " + xp + "/" + num + " exp");
						player.closeInventory();
					}
				}
			}

			if (event.getRawSlot() == 6) {
				if (!player.getInventory().containsAtLeast(dust(), 1)) {
					player.closeInventory();
					player.sendMessage(ChatColor.RED + "You have §bno §cDust.");
				}
				ItemStack g = dust().clone();
				int a = 1;
				if (event.getClick() == ClickType.LEFT) {
					a = 1;
				}
				if (event.getClick() == ClickType.RIGHT) {
					a = getAmount(player, g);
				}
				g.setAmount(a);
				if (player.getInventory().containsAtLeast(g, 1)) {
					player.getInventory().removeItem(g);
					int num = nameconfig.getInt("playerdata." + player.getUniqueId() + ".dust");
					nameconfig.set("playerdata." + player.getUniqueId() + ".dust", num + a);
					plugin.saveYamlFile(nameconfig, name);
					int nort = num + a;
					player.sendMessage(ChatColor.GOLD + "Your current Dust balance is §b" + nort + " §6Dust. §6[§a+§b" + a + "§6]");

					ItemStack chest = new ItemStack(Material.CHEST);
					ItemMeta chestMeta = chest.getItemMeta();
					List<String> Lore3 = new ArrayList<String>();
					chestMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dust Balance");
					chest.setItemMeta(chestMeta);
					int num3 = nameconfig.getInt("playerdata." + player.getUniqueId() + ".dust");
					Lore3.add("§7Check how much Dust you stored in");
					Lore3.add("§7the bank of the realm down below.");
					Lore3.add("");
					int nu = num3;
					Lore3.add("§7You have " + "§a" + nu + " §7Dust");
					chestMeta.setLore(Lore3);
					chest.setItemMeta(chestMeta);
					event.getInventory().setItem(8, chest);
				}
			}

			if (event.getRawSlot() == 4) {
				int num = nameconfig.getInt("playerdata." + player.getUniqueId() + ".dust");
				if (num > 0) {
					ItemStack g = dust().clone();
					int a = 1;
					if (event.getClick() == ClickType.LEFT) {
						a = 1;
					}
					if (event.getClick() == ClickType.RIGHT) {
						a = num;
					}
					g.setAmount(a);
					nameconfig.set("playerdata." + player.getUniqueId() + ".dust", num - a);
					plugin.saveYamlFile(nameconfig, name);
					player.getInventory().addItem(g);
					int nert = num - a;
					player.sendMessage(ChatColor.GOLD + "Your current Dust balance is §b" + nert + " §6Dust. §6[§c-§b" + a + "§6]");

					ItemStack chest = new ItemStack(Material.CHEST);
					ItemMeta chestMeta = chest.getItemMeta();
					List<String> Lore3 = new ArrayList<String>();
					chestMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dust Balance");
					chest.setItemMeta(chestMeta);
					int num3 = nameconfig.getInt("playerdata." + player.getUniqueId() + ".dust");
					Lore3.add("§7Check how much dust you stored in");
					Lore3.add("§7the bank of the realm down below.");
					Lore3.add("");
					int nu = num3;
					Lore3.add("§7You have " + "§a" + nu + " §7Dust");
					chestMeta.setLore(Lore3);
					chest.setItemMeta(chestMeta);
					event.getInventory().setItem(8, chest);
				}
				if (num == 0) {
					player.sendMessage(ChatColor.RED + "You have §bno §cDust.");
					player.closeInventory();
				}
			}

			int slot = event.getRawSlot();
			if (slot == 2) {
				PlayerInventory held = player.getInventory();
				int quant = plugin.getConfig().getInt("dust-trade");
				ItemStack dia = new ItemStack(XMaterial.matchXMaterial(plugin.getConfig().getString("dust-material")).get()
						.parseMaterial(), quant);
				if (held.containsAtLeast(dia, quant)) {
					held.removeItem(dia);
					player.getInventory().addItem(dust());
				}
			}
		}
	}
}
