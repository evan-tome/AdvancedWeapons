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
import java.util.Collections;
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
					player.openInventory(i());
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
	//MAIN
	public Inventory i() {
		Inventory i = Bukkit.createInventory(null, 9, "Custom Enchantments");
		ItemStack all = new ItemStack(XMaterial.NETHER_STAR.parseMaterial());
		ItemMeta allMeta = all.getItemMeta();
		allMeta.setDisplayName("§bAll Enchantments");
		all.setItemMeta(allMeta);
		
		ItemStack name = new ItemStack(XMaterial.NAME_TAG.parseMaterial());
		ItemMeta nameMeta = all.getItemMeta();
		nameMeta.setDisplayName("§bFilter by Name");
		name.setItemMeta(nameMeta);
		
		ItemStack type = new ItemStack(XMaterial.SHEARS.parseMaterial());
		ItemMeta typeMeta = all.getItemMeta();
		typeMeta.setDisplayName("§bFilter by Type");
		type.setItemMeta(typeMeta);
		
		i.setItem(2, name);
		i.setItem(4, all);
		i.setItem(6, type);
		return i;
	}
	//ALL
	private void openGUI(Player player) {
		int sl=0;
		int sn=0;
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
			int sl1 = sl + 1;
			sl=sl1;
		}
		sl = sl + 1;
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
		if (sl >= 29 && sl <= 45) {
			sn = 45;
		}
		Inventory inv = Bukkit.createInventory(null, sn, "Custom Enchantments - All");
		
		inv.setItem(inv.getSize()-1, back());

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
	//NAME
	private void openNameGUI(Player player) {
		int sl=0;
		int sn=0;
		List<String> l = new ArrayList<>(Collections.emptyList());
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
			String i = item.getString("name".replace('&', '§'));
			String name = ChatColor.stripColor(i);
			String lastWord = name.substring(name.lastIndexOf(" ") + 1);
			String firstWord = name.substring(0, name.lastIndexOf(" "));
			if (!l.contains(firstWord)) {
				l.add(firstWord);
				int sl1 = sl + 1;
				sl = sl1;
			}
		}
		sl = sl + 1;
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
		if (sl>=37&&sl <= 45) {
			sn = 45;
		}
		if (sl>=46&&sl <= 54) {
			sn = 54;
		}
		Inventory n = Bukkit.createInventory(null, sn, "Custom Enchantments - Name");
		
		n.setItem(n.getSize()-1, back());
		
		for (String s : l) {
			List<String> Lore = new ArrayList<String>();
			ItemStack ce = new ItemStack(XMaterial.BOOKSHELF.parseMaterial());
			ItemMeta ceM = ce.getItemMeta();
			ceM.setDisplayName(s.replace('&', '§'));
			ceM.setLore(Lore);
			ce.setItemMeta(ceM);
			n.addItem(ce);
		}
		
		player.openInventory(n);
		
	}
	//TYPE
	private void openTypeGUI(Player player) {
		Inventory t = Bukkit.createInventory(null, 9, "Custom Enchantments - Type");
		
		t.setItem(t.getSize() - 1, back());
		
		List<String> Lore = new ArrayList<String>();
		ItemStack ce = new ItemStack(XMaterial.BOOKSHELF.parseMaterial());
		ItemMeta ceM = ce.getItemMeta();
		
		ceM.setDisplayName("§eWeapon");
		ceM.setLore(Lore);
		ce.setItemMeta(ceM);
		t.addItem(ce);
		ceM.setDisplayName("§eTool");
		ceM.setLore(Lore);
		ce.setItemMeta(ceM);
		t.addItem(ce);
		ceM.setDisplayName("§eArmor");
		ceM.setLore(Lore);
		ce.setItemMeta(ceM);
		t.addItem(ce);
		
		player.openInventory(t);
	}
	//NAME2
	private void openName2GUI(Player player, String s) {
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
		}
		String st = "Custom Enchantments - " + s;
		st = StringUtils.abbreviate(st, 32);
		Inventory n = Bukkit.createInventory(null, 9, st);
		
		n.setItem(n.getSize()-1, back());
		
		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
			List<String> Lore = new ArrayList<String>();
			
			int num = item.getInt("cost");
			String speed1Type = item.getString("type");
			String i = item.getString("name".replace('&', '§'));
			String name = ChatColor.stripColor(i);
			String lastWord = name.substring(name.lastIndexOf(" ") + 1);
			String firstWord = name.substring(0, name.lastIndexOf(" "));
			
			if (name.contains(s)) {
				
				ItemStack ce = new ItemStack(XMaterial.BOOK.parseMaterial());
				ItemMeta ceM = ce.getItemMeta();
				ceM.setDisplayName(item.getString("name").replace('&', '§'));
				Lore.add("§7" + StringUtils.capitalize(speed1Type) + " Enchantment");
				for (String line : item.getStringList("lore")) {
					Lore.add(line.replace('&','§'));
				}
				Lore.add("");
				Lore.add("§b" + num + "x " + "§7DUST");
				ceM.setLore(Lore);
				ce.setItemMeta(ceM);
				n.addItem(ce);
			}
		}
		
		player.openInventory(n);
		
	}
	//TYPE2
	private void openType2GUI(Player player, String s) {
		int sl = 0;
		int sn = 0;
		ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
			if(item.contains("type")) {
				String speed1Type = item.getString("type");
				if (speed1Type != null) {
					if (speed1Type.equalsIgnoreCase(s)) {
						int sl1 = sl + 1;
						sl = sl1;
					}
				}
			}
		}
		sl = sl + 1;
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
		if (sl>=37&&sl <= 45) {
			sn = 45;
		}
		if (sl>=46&&sl <= 54) {
			sn = 54;
		}
		
		Inventory t = Bukkit.createInventory(null, sn, "Custom Enchantments - " + s);
		
		t.setItem(t.getSize() - 1, back());
		
		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
			List<String> Lore = new ArrayList<String>();
			
			int num = item.getInt("cost");
			if (item.contains("type")) {
				String speed1Type = item.getString("type");
				if (speed1Type != null) {
					String i = item.getString("name".replace('&', '§'));
					String name = ChatColor.stripColor(i);
					String lastWord = name.substring(name.lastIndexOf(" ") + 1);
					String firstWord = name.substring(0, name.lastIndexOf(" "));
					
					if (speed1Type.equalsIgnoreCase(s)) {
						
						ItemStack ce = new ItemStack(XMaterial.BOOK.parseMaterial());
						ItemMeta ceM = ce.getItemMeta();
						ceM.setDisplayName(item.getString("name").replace('&', '§'));
						Lore.add("§7" + StringUtils.capitalize(speed1Type) + " Enchantment");
						for (String line : item.getStringList("lore")) {
							Lore.add(line.replace('&', '§'));
						}
						Lore.add("");
						Lore.add("§b" + num + "x " + "§7DUST");
						ceM.setLore(Lore);
						ce.setItemMeta(ceM);
						t.addItem(ce);
					}
				}
			}
		}
		
		player.openInventory(t);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!ChatColor.stripColor(event.getView().getTitle())
				.contains("Custom Enchantments"))
			return;
		Player player = (Player) event.getWhoClicked();
		if (event.getInventory().getHolder() == null) {
			event.setCancelled(true);
			
			
			if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
				
				return;
			}
			
			
			switch (event.getCurrentItem().getType()) {
				case NAME_TAG:
					openNameGUI(player);
					break;
				case NETHER_STAR:
					openGUI(player);
					break;
				case SHEARS:
					openTypeGUI(player);
					break;
				case ARROW:
					player.openInventory(i());
					break;
				case BOOKSHELF:
					String string = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					if (player.getOpenInventory().getTitle().contains("Name")) {
						openName2GUI(player, string);
					}
					if (player.getOpenInventory().getTitle().contains("Type")) {
						openType2GUI(player, string);
					}
					break;
				case BOOK:
					try {
						ItemMeta speed1M = event.getCurrentItem().getItemMeta();
						ItemStack speed1 = player.getInventory().getItemInHand();
						ItemMeta speed1Meta = speed1.getItemMeta();
						int num = plugin.getConfig().getInt(ChatColor.stripColor("enchant." +
								speed1M.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
						String i = StringUtils.capitaliseAllWords(speed1.getType().toString().toLowerCase().replace("_", " "));
						
						ItemStack glow = dust(plugin.getConfig().getStringList("dust-item")).clone();
						glow.setAmount(num);
						
						List<String> Lores = new ArrayList<String>();
						String test = ChatColor.GRAY + (ChatColor.stripColor(speed1M.getDisplayName()));
						String firstWord = test.substring(0, test.lastIndexOf(" "));
						String lastWord = test.substring(test.lastIndexOf(" ") + 1);
						
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
										String il = item.getString("name").replace('&', '§');
										String name = ChatColor.stripColor(il);
										max.add(name);
									}
									
									if (max.contains(ChatColor.stripColor(firstWord) + " " + upEnchLevel(test, player))) {
										if (!player.getInventory().containsAtLeast(glow, num)) {
											player.sendMessage(plugin.getConfig().getString("insufficient-dust-msg").replace('&', '§'));
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
										player.sendMessage(plugin.getConfig().getString("insufficient-dust-msg").replace('&', '§'));
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
									player.sendMessage(plugin.getConfig().getString("insufficient-dust-msg").replace('&', '§'));
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
						if (speed1.getType() == XMaterial.AIR.parseMaterial()) {
							player.sendMessage("§cYou must be holding an item to enchant.");
						}
					} catch (Exception ignored) {
					}
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
	public boolean itemNameContains(Inventory inv, String s, Player p) {
		if(!inv.isEmpty()) {
			for (ItemStack is : inv.getContents()) {
				if (is != null) {
					if (is.hasItemMeta() && is.getItemMeta().hasDisplayName()) {
						if (is.getItemMeta().getDisplayName().contains(s.replace('&', '§'))) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}