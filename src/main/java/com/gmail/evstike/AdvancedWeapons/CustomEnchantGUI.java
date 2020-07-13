package com.gmail.evstike.AdvancedWeapons;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class CustomEnchantGUI extends API implements CommandExecutor, Listener {

	Fates plugin;

	public CustomEnchantGUI(Fates instance) {
		plugin = instance;
	}

    List<String> max = new ArrayList<String>();

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig())) {
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
		int sl=0;
		int sn=0;
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
			int sl1 = sl+1;
			sl=sl1;
		}
		if(sl>=0&&sl<=9) {
			sn=9;
		}
		if(sl>=10&&sl<=18) {
			sn=18;
		}
		if(sl>=19&&sl<=27) {
			sn=27;
		}
		if(sl>=28&&sl<=36) {
			sn=36;
		}
		Inventory inv = Bukkit.createInventory(null, sn, "Custom Enchantments");

		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
			List<String> Lore = new ArrayList<String>();
			ItemStack ce = new ItemStack(XMaterial.BOOK.parseMaterial());
			ItemMeta ceM = ce.getItemMeta();
			ceM.setDisplayName(item.getString("name").replace('&', '§'));

			int num = item.getInt("cost");
			String speed1Type = item.getString("type");

			Lore.add("§7" + StringUtils.capitalize(speed1Type) + " Enchantment");
			for (String line : item.getStringList("lore")) {
				Lore.add(line.replace('&','§'));
			}
			Lore.add("");
			Lore.add("§b" + num + "x " + "§7DUST");
			ceM.setLore(Lore);
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
                    String firstWord = test.substring(0, test.lastIndexOf(" "));
                    String lastWord = test.substring(test.lastIndexOf(" ") + 1);
                    ItemStack glow = new ItemStack(XMaterial.GUNPOWDER.parseMaterial(), num);
                    ItemMeta glowMeta = glow.getItemMeta();
                    glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
                    Lores.add("§7This Dust has magical properties");
                    Lores.add("§7which make it a valuable currency.");
                    glowMeta.setLore(Lores);
                    glow.setItemMeta(glowMeta);

                    if (speed1.getType() != XMaterial.AIR.parseMaterial()) {
                        ArrayList<String> Lore = new ArrayList<String>();
                        if (speed1Meta.hasLore()) {
                            for (String s : speed1Meta.getLore()) {
                                    Lore.add(s);
                            }
                        }
                        if (speed1Meta.hasLore()) {
                            if (speed1Meta.getLore().contains(test)) {

                                ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
                                for (String key : section.getKeys(false)) {
                                    ConfigurationSection item = section.getConfigurationSection(key);
                                    String il = item.getString("name").replace('&','§');
                                    String name = ChatColor.stripColor(il);
                                    max.add(name);
                                }
                                if (max.contains(ChatColor.stripColor(firstWord) + " " + upEnchLevel(test, player))) {
									if (!player.getInventory().containsAtLeast(glow, num)) {
										player.sendMessage("§cYou don't have enough Dust to purchase this.");
									}
                                    if (player.getInventory().containsAtLeast(glow, num)) {
                                        player.getInventory().removeItem(glow);
                                        Lore.set(speed1Meta.getLore().indexOf(test), firstWord + " " + upEnchLevel(test, player));
                                        player.sendMessage("§7Upgraded your " + test + " §b" + i + " §7to §7" +
                                                firstWord + " " + upEnchLevel(test, player));
                                        speed1Meta.setLore(Lore);
                                        speed1.setItemMeta(speed1Meta);
                                    }
                                }
                                if (!max.contains(ChatColor.stripColor(firstWord) + " " + upEnchLevel(test, player))) {
                                    player.sendMessage("§7Your §b" + i + " §7has the max level of " + firstWord);
                                }
                                max.clear();
                            }
                        }
                        if (plugin.getConfig().getString(ChatColor.stripColor("enchant." +
                                speed1M.getDisplayName().toLowerCase().replace(" ", "-") + ".type")).equals("weapon")) {
                            if (!isWeapon(player)) {
                                player.sendMessage("§cYou must be holding a weapon to enchant your item.");
                                return;
                            }
                        }
                        if (plugin.getConfig().getString(ChatColor.stripColor("enchant." +
                                speed1M.getDisplayName().toLowerCase().replace(" ", "-") + ".type")).equals("armor")) {
                            if (!isArmor(player)) {
                                player.sendMessage("§cYou must be holding a piece of armor to enchant your item.");
                                return;
                            }
                        }
                        if (plugin.getConfig().getString(ChatColor.stripColor("enchant." +
                                speed1M.getDisplayName().toLowerCase().replace(" ", "-") + ".type")).equals("tool")) {
                            if (!isTool(player)) {
                                player.sendMessage("§cYou must be holding a tool to enchant your item.");
                                return;
                            }
                        }
                        if (speed1Meta.hasLore()) {
                            if (!speed1Meta.getLore().contains(firstWord + " I") && !speed1Meta.getLore().contains(firstWord + " II") &&
                                    !speed1Meta.getLore().contains(firstWord + " III") && !speed1Meta.getLore().contains(firstWord + " IV") &&
                                    !speed1Meta.getLore().contains(firstWord + " V")) {
								if (!player.getInventory().containsAtLeast(glow, num)) {
									player.sendMessage("§cYou don't have enough Dust to purchase this.");
								}
                                if (player.getInventory().containsAtLeast(glow, num)) {
                                    player.getInventory().removeItem(glow);
                                    Lore.add(test);
                                    speed1Meta.setLore(Lore);
                                    speed1.setItemMeta(speed1Meta);
                                    player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
                                }
                            }
                        }
                        if (!speed1Meta.hasLore()) {
							if (!player.getInventory().containsAtLeast(glow, num)) {
								player.sendMessage("§cYou don't have enough Dust to purchase this.");
							}
                            if (player.getInventory().containsAtLeast(glow, num)) {
                                player.getInventory().removeItem(glow);
                                Lore.add(test);
                                speed1Meta.setLore(Lore);
                                speed1.setItemMeta(speed1Meta);
                                player.sendMessage("§7Enchanted your §b" + i + " §7with §7" + test);
                            }
                        }
                    }
                    if(speed1.getType()==XMaterial.AIR.parseMaterial()) {
                        player.sendMessage("§cYou must be holding an item to enchant.");
                    }
				} catch (Exception ignored) {
				}
		}
	}
	public String maxEnchLevel(String s, Player p) {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
		for (String key : section.getKeys(false)) {
			ConfigurationSection item = section.getConfigurationSection(key);
			String i = item.getString("name".replace("&", "§"));
			String name = ChatColor.stripColor(i);
			String lastWord = name.substring(name.lastIndexOf(" ") + 1);
			String firstWord = name.substring(0, name.lastIndexOf(" "));

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
	public String upEnchLevel(String s, Player p) {
		ItemMeta im = p.getInventory().getItemInHand().getItemMeta();
			String name = ChatColor.stripColor(s);
			String lastWord = name.substring(name.lastIndexOf(" ") + 1);
			String firstWord = name.substring(0, name.lastIndexOf(" "));
			if(lastWord.equals("I")) {
			    return "II";
				}
			if(lastWord.equals("II")) {
				return "III";
			}
			if(lastWord.equals("III")) {
				return "IV";
			}
			if(lastWord.equals("IV")) {
				return "V";
			}
		return "I";
	}
	public String downEnchLevel(String s, Player p) {
		ItemMeta im = p.getInventory().getItemInHand().getItemMeta();
		for(String l : im.getLore()) {
			String name = ChatColor.stripColor(l);
			String lastWord = l.substring(l.lastIndexOf(" ") + 1);
			String firstWord = l.substring(0, l.lastIndexOf(" "));
			if(lastWord.equals("V")) {
				return "IV";
			}
			if(lastWord.equals("IV")) {
				return "III";
			}
			if(lastWord.equals("III")) {
				return "II";
			}
			if(lastWord.equals("II")) {
				return "I";
			}
		}
		return "I";
	}
}