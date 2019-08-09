package com.gmail.evstike.AdvancedWeapons;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.StringUtil;

@SuppressWarnings("deprecation")
public class CustomEnchantGUI extends API implements CommandExecutor, Listener {

	Fates plugin;

	public CustomEnchantGUI(Fates instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if(hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig())==false) {
            if (cmd.getName().equalsIgnoreCase("ce")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    openGUI(player);
                    return false;
                }
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cError: §4Only Players can use this command!");
                    return true;
                }
            }
        }
		return false;
	}

	private void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "Custom Enchantments");

		ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
			List<String> Lore0 = new ArrayList<String>();
			ItemStack ce = new ItemStack(UMaterial.BOOK.getItemStack());
			ItemMeta ceM = ce.getItemMeta();
			ceM.setDisplayName(item.getString("name".replace("&", "§")));
			ce.setItemMeta(ceM);

			int num = item.getInt("cost");
			String speed1Type = item.getString("type");

			Lore0.add("§7" + StringUtils.capitalize(speed1Type) + " Enchantment");
			for (String line : item.getStringList("lore")) {
				Lore0.add(line.replace("&", "§"));
			}
			Lore0.add("");
			Lore0.add("§b" + num + "x " + "§7DUST");
			ceM.setLore(Lore0);
			ce.setItemMeta(ceM);
			inv.addItem(ce);
		}

		player.openInventory(inv);

	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!ChatColor.stripColor(event.getView().getTitle())
				.equalsIgnoreCase("Custom Enchantments"))
			return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);


		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {

			return;
		}


		switch (event.getCurrentItem().getType()) {
			case BOOK:

				try {
					ItemMeta speed1M = event.getCurrentItem().getItemMeta();
					ItemStack speed1 = player.getInventory().getItemInHand();
					ItemMeta speed1Meta = speed1.getItemMeta();
					int num = plugin.getConfig().getInt(ChatColor.stripColor("enchant." +
							speed1M.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
					String i = StringUtils.capitaliseAllWords(speed1.getType().toString().toLowerCase().replace("_", " "));

					List<String> Lores = new ArrayList<String>();
					String test = ChatColor.GRAY + (ChatColor.stripColor(speed1M.getDisplayName()));
					String lastWord = test.substring(test.lastIndexOf(" ") + 1);
					ItemStack glow = new ItemStack(UMaterial.GUNPOWDER.getMaterial(), num);
					ItemMeta glowMeta = glow.getItemMeta();
					glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
					Lores.add("§7This dust has magical properties");
					Lores.add("§7which make it a valuable currency.");
					glowMeta.setLore(Lores);
					glow.setItemMeta(glowMeta);

					if (speed1.getType() != null || speed1.getType() != UMaterial.AIR.getMaterial()) {
						if (speed1Meta.hasLore()) {
							for (String Line : speed1Meta.getLore()) {
								List<String> L = speed1Meta.getLore();
								if (Line.equals(test)) {
									if (!lastWord.equals(ChatColor.stripColor("III"))) {
										if (player.getInventory().containsAtLeast(glow, num)) {
											player.getInventory().removeItem(glow);
											player.sendMessage("§7Upgraded your " + test + " §b" + i + " §7to §7" +
													test.replace(lastWord, lastWord + ChatColor.GRAY + "I"));
											L.set(L.indexOf(Line), test.replace(lastWord, lastWord + "I"));
											speed1Meta.setLore(L);
											speed1.setItemMeta(speed1Meta);
											player.closeInventory();
										}
									}
								}
								if (lastWord.equals(ChatColor.stripColor("III"))) {
									if (speed1Meta.getLore().contains(test)) {
										player.closeInventory();
										player.sendMessage("§7Your §b" + i + " §7has the max level of " +
												test.replace(lastWord, ""));
									}
								}
							}
							for (String Line : speed1Meta.getLore()) {
								List<String> L = speed1Meta.getLore();
								if ((Line + "I").equals(test) || (Line + "II").equals(test)) {
										if (player.getInventory().containsAtLeast(glow, num)) {
											player.getInventory().removeItem(glow);
											player.sendMessage("§7Increased your " + Line + " §b" + i + " §7to §7" +
													test);
											L.set(L.indexOf(Line), test);
											speed1Meta.setLore(L);
											speed1.setItemMeta(speed1Meta);
											player.closeInventory();
									}
								}
							}
						}
						ArrayList<String> Lore = new ArrayList<String>();
						if (speed1Meta.hasLore()) {
							for (String s : speed1Meta.getLore()) {
								Lore.add(s);
							}
						}
						if (plugin.getConfig().getString(ChatColor.stripColor("enchant." +
								speed1M.getDisplayName().toLowerCase().replace(" ", "-") + ".type")).equals("weapon")) {
							if (isWeapon(player)) {
								if (speed1Meta.hasLore()) {
									if (speed1Meta.getLore().contains(test) ||
											speed1Meta.getLore().contains(test + "I")) {
									}
								}
								if (speed1Meta.hasLore()) {
									if (!(speed1Meta.getLore().contains(test) ||
											speed1Meta.getLore().contains(test + "I")
											|| speed1Meta.getLore().contains(test + "II"))) {
									}
								}
								if (speed1Meta.hasLore()) {
									if ((speed1Meta.getLore().contains(test.replace(test.charAt
											(test.length() - 1), Character.MIN_VALUE))) &&
											!speed1Meta.getLore().contains(test.replace(lastWord, "III"))) {
										if (player.getInventory().containsAtLeast(glow, num)) {
											player.getInventory().removeItem(glow);
											Lore.add(test);
											speed1Meta.setLore(Lore);
											speed1.setItemMeta(speed1Meta);
											player.closeInventory();
											player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
										}
									}
								}
								if (speed1Meta.hasLore()) {
									if (!speed1Meta.getLore().contains(test + "I") &&
											!speed1Meta.getLore().contains(test.replace(lastWord, "III"))
											&& !speed1Meta.getLore().contains(test.replace(lastWord, "II")) &&
											!speed1Meta.getLore().contains(test.replace(lastWord, "I"))) {
										if (player.getInventory().containsAtLeast(glow, num)) {
											player.getInventory().removeItem(glow);
											Lore.add(test);
											speed1Meta.setLore(Lore);
											speed1.setItemMeta(speed1Meta);
											player.closeInventory();
											player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
										}
									}
								}
								if (!speed1Meta.hasLore()) {
									if (player.getInventory().containsAtLeast(glow, num)) {
										player.getInventory().removeItem(glow);
										Lore.add(test);
										speed1Meta.setLore(Lore);
										speed1.setItemMeta(speed1Meta);
										player.closeInventory();
										player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
									}
								}
							}
						}
						if (plugin.getConfig().getString(ChatColor.stripColor("enchant." +
								speed1M.getDisplayName().toLowerCase().replace(" ", "-") + ".type")).equals("armor")) {
							if (isArmor(player)) {
								if (speed1Meta.hasLore()) {
									if (speed1Meta.getLore().contains(test) ||
											speed1Meta.getLore().contains(test + "I")) {
									}
								}
								if (speed1Meta.hasLore()) {
									if (!(speed1Meta.getLore().contains(test) ||
											speed1Meta.getLore().contains(test + "I")
											|| speed1Meta.getLore().contains(test + "II"))) {
									}
								}
								if (speed1Meta.hasLore()) {
									if ((speed1Meta.getLore().contains(test.replace(test.charAt
											(test.length() - 1), Character.MIN_VALUE))) &&
											!speed1Meta.getLore().contains(test.replace(lastWord, "III"))) {
										if (player.getInventory().containsAtLeast(glow, num)) {
											player.getInventory().removeItem(glow);
											Lore.add(test);
											speed1Meta.setLore(Lore);
											speed1.setItemMeta(speed1Meta);
											player.closeInventory();
											player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
										}
									}
								}
								if (speed1Meta.hasLore()) {
									if (!speed1Meta.getLore().contains(test + "I") &&
											!speed1Meta.getLore().contains(test.replace(lastWord, "III"))
											&& !speed1Meta.getLore().contains(test.replace(lastWord, "II")) &&
											!speed1Meta.getLore().contains(test.replace(lastWord, "I"))) {
										if (player.getInventory().containsAtLeast(glow, num)) {
											player.getInventory().removeItem(glow);
											Lore.add(test);
											speed1Meta.setLore(Lore);
											speed1.setItemMeta(speed1Meta);
											player.closeInventory();
											player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
										}
									}
								}
								if (!speed1Meta.hasLore()) {
									if (player.getInventory().containsAtLeast(glow, num)) {
										player.getInventory().removeItem(glow);
										Lore.add(test);
										speed1Meta.setLore(Lore);
										speed1.setItemMeta(speed1Meta);
										player.closeInventory();
										player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
									}
								}
							}
						}
						if (plugin.getConfig().getString(ChatColor.stripColor("enchant." +
								speed1M.getDisplayName().toLowerCase().replace(" ", "-") + ".type")).equals("tool")) {
							if (isTool(player)) {
								if (speed1Meta.hasLore()) {
									if (speed1Meta.getLore().contains(test) ||
											speed1Meta.getLore().contains(test + "I")) {
									}
								}
								if (speed1Meta.hasLore()) {
									if (!(speed1Meta.getLore().contains(test) ||
											speed1Meta.getLore().contains(test + "I")
											|| speed1Meta.getLore().contains(test + "II"))) {
									}
								}
								if (speed1Meta.hasLore()) {
									if ((speed1Meta.getLore().contains(test.replace(test.charAt
											(test.length() - 1), Character.MIN_VALUE))) &&
											!speed1Meta.getLore().contains(test.replace(lastWord, "III"))) {
										if (player.getInventory().containsAtLeast(glow, num)) {
											player.getInventory().removeItem(glow);
											Lore.add(test);
											speed1Meta.setLore(Lore);
											speed1.setItemMeta(speed1Meta);
											player.closeInventory();
											player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
										}
									}
								}
								if (speed1Meta.hasLore()) {
									if (!speed1Meta.getLore().contains(test + "I") &&
											!speed1Meta.getLore().contains(test.replace(lastWord, "III"))
											&& !speed1Meta.getLore().contains(test.replace(lastWord, "II")) &&
											!speed1Meta.getLore().contains(test.replace(lastWord, "I"))) {
										if (player.getInventory().containsAtLeast(glow, num)) {
											player.getInventory().removeItem(glow);
											Lore.add(test);
											speed1Meta.setLore(Lore);
											speed1.setItemMeta(speed1Meta);
											player.closeInventory();
											player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
										}
									}
								}
								if (!speed1Meta.hasLore()) {
									if (player.getInventory().containsAtLeast(glow, num)) {
										player.getInventory().removeItem(glow);
										Lore.add(test);
										speed1Meta.setLore(Lore);
										speed1.setItemMeta(speed1Meta);
										player.closeInventory();
										player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
									}
								}
							}
						}
					}
				}
						catch (Exception ignored) {
						}
				}
		}
	}
	
		
		
					 								

