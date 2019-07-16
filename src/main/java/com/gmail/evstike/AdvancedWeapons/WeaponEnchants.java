package com.gmail.evstike.AdvancedWeapons;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("deprecation")
public class WeaponEnchants extends API implements Listener {

	Fates plugin;

	public WeaponEnchants(Fates instance) {
		plugin = instance;
	}

	public boolean chance(String path) {
		Random rand = new Random();
		int n = rand.nextInt(100) + 1;
		if (n <= (plugin.getConfig().getInt("enchant." + path + ".chance"))) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		Player p = (Player) event.getPlayer();
		FileConfiguration config = plugin.getConfig();
		ItemStack i = p.getInventory().getItemInHand();
		int dur = i.getDurability();

		if (itemHasLore(p.getInventory(), "§7Rush I")) {
			if (chance("rush-i")) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0));
				if (config.getBoolean("durability") == true) {
					i.setDurability((short) (dur + 1));
				}
			}
		}
		if (itemHasLore(p.getInventory(), "§7Rush II")) {
			if (chance("rush-ii")) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 0));
				if (config.getBoolean("durability") == true) {
					i.setDurability((short) (dur + 1));
				}
			}
		}
		if (itemHasLore(p.getInventory(), "§7Rush III")) {
			if (chance("rush-iii")) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 160, 1));
				if (config.getBoolean("durability") == true) {
					i.setDurability((short) (dur + 1));
				}
			}
		}
		if (itemHasLore(p.getInventory(), "§7Bounce I")) {
			if (chance("bounce-i")) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 80, 0));
				if (config.getBoolean("durability") == true) {
					i.setDurability((short) (dur + 1));
				}
			}
		}

		if (itemHasLore(p.getInventory(), "§7Bounce II")) {
			if (chance("bounce-ii")) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, 1));
				if (config.getBoolean("durability") == true) {
					i.setDurability((short) (dur + 1));
				}
			}
		}

		if (itemHasLore(p.getInventory(), "§7Bounce III")) {
			if (chance("bounce-iii")) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 160, 1));
				if (config.getBoolean("durability") == true) {
					i.setDurability((short) (dur + 1));
				}
			}
		}
		if (itemHasLore(p.getInventory(), "§7Hyper I")) {
			if (chance("hyper-i")) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 80, 0));
				if (config.getBoolean("durability") == true) {
					i.setDurability((short) (dur + 1));
				}
			}
		}

		if (itemHasLore(p.getInventory(), "§7Hyper II")) {
			if (chance("hyper-ii")) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 120, 0));
				if (config.getBoolean("durability") == true) {
					i.setDurability((short) (dur + 1));
				}
			}
		}

		if (itemHasLore(p.getInventory(), "§7Hyper III")) {
			if (chance("hyper-iii")) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 160, 1));
				if (config.getBoolean("durability") == true) {
					i.setDurability((short) (dur + 1));
				}
			}
		} else {
			return;
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity defender = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Player) {
				Player attacker = (Player) e.getDamager();
				List<String> poison = Arrays.asList("ZOMBIE", "HUSK", "SKELETON", "STRAY", "WITHER", "WITHER_SKELETON",
						"SPIDER", "CAVE_SPIDER", "DROWNED", "LIGHTNING", "PRIMED_TNT", "ZOMBIE_VILLAGER", "PIG_ZOMBIE",
						"SKELETON_HORSE", "ZOMBIE_HORSE", "PHANTOM", "ARMOR_STAND");

				FileConfiguration config = plugin.getConfig();
				ItemStack i = attacker.getInventory().getItemInHand();
				ItemMeta im = i.getItemMeta();
				int dur = i.getDurability();

				if (i.getType() != Material.AIR) {
					if (im.hasLore()) {
						if (im.getLore().contains("§7Plague I")) {
							if (!(poison.contains(defender.getType().toString().toUpperCase()))) {
								if (chance("plague-i")) {
									if (!(defender.hasPotionEffect(PotionEffectType.POISON))) {
										defender.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
										if (plugin.getConfig().getBoolean("enchant.plague-i.msg") == true) {
											attacker.sendMessage(ChatColor.GOLD + "You infected a " + ChatColor.GREEN
													+ e.getEntityType().getName().replace("_", " "));
											if (config.getBoolean("durability") == true) {
												i.setDurability((short) (dur + 1));
											}
										}
									}
								}
							}
						}
					}
				}
				if (itemHasLore(attacker.getInventory(), "§7Plague II")) {
					if (!(poison.contains(defender.getType().toString().toUpperCase()))) {
						if (chance("plague-ii")) {
							if (!(defender.hasPotionEffect(PotionEffectType.POISON))) {
								defender.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 0));
								if (plugin.getConfig().getBoolean("enchant.plague-ii.msg") == true) {
									attacker.sendMessage(ChatColor.GOLD + "You infected a " + ChatColor.GREEN
											+ e.getEntityType().getName().replace("_", " "));
									if (config.getBoolean("durability") == true) {
										i.setDurability((short) (dur + 1));
									}
								}
							}
						}
					}
				}
				if (itemHasLore(attacker.getInventory(), "§7Plague III")) {
					if (!(poison.contains(defender.getType().toString().toUpperCase()))) {
						if (chance("plague-iii")) {
							if (!(defender.hasPotionEffect(PotionEffectType.POISON))) {
								defender.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 160, 1));
								if (plugin.getConfig().getBoolean("enchant.plague-iii.msg") == true) {
									attacker.sendMessage(ChatColor.GOLD + "You infected a " + ChatColor.GREEN
											+ e.getEntityType().getName().replace("_", " "));
									if (config.getBoolean("durability") == true) {
										i.setDurability((short) (dur + 1));
									}
								}
							}
						}
					}
				}

				if (itemHasLore(attacker.getInventory(), "§7End Ender I")) {
					if (attacker instanceof Player) {
						if (defender.getType().equals(EntityType.ENDERMAN)
								|| defender.getType().equals(EntityType.ENDERMITE)) {
							if (chance("end-ender-i")) {
								defender.damage(1);
								if (plugin.getConfig().getBoolean("enchant.end-ender-i.msg") == true) {
									attacker.sendMessage(ChatColor.GOLD + "You hit an " + ChatColor.GREEN
											+ e.getEntityType().getName().replace("_", " "));
									if (config.getBoolean("durability") == true) {
										i.setDurability((short) (dur + 1));
									}
								}
							}
						}
					}
				}
				if (itemHasLore(attacker.getInventory(), "§7End Ender II")) {
					if (attacker instanceof Player) {
						if (defender.getType().equals(EntityType.ENDERMAN)
								|| defender.getType().equals(EntityType.ENDERMITE)) {
							if (chance("end-ender-ii")) {
								defender.damage(1.5);
								if (plugin.getConfig().getBoolean("enchant.end-ender-ii.msg") == true) {
									attacker.sendMessage(ChatColor.GOLD + "You hit an " + ChatColor.GREEN
											+ e.getEntityType().getName().replace("_", " "));
									if (config.getBoolean("durability") == true) {
										i.setDurability((short) (dur + 1));
									}
								}
							}
						}
					}
				}
				if (itemHasLore(attacker.getInventory(), "§7End Ender III")) {
					if (attacker instanceof Player) {
						if (defender.getType().equals(EntityType.ENDERMAN)
								|| defender.getType().equals(EntityType.ENDERMITE)) {
							if (chance("end-ender-iii")) {
								defender.damage(2);
								if (plugin.getConfig().getBoolean("enchant.end-ender-iii.msg") == true) {
									attacker.sendMessage(ChatColor.GOLD + "You hit an " + ChatColor.GREEN
											+ e.getEntityType().getName().replace("_", " "));
									if (config.getBoolean("durability") == true) {
										i.setDurability((short) (dur + 1));
									}
								}
							}
						}
					}
				}
			}

		}
	}

	@EventHandler
	public void onDefend(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player defender = (Player) e.getEntity();

			PlayerInventory inv = defender.getInventory();
			double dam = e.getDamage();

			boolean testfor1 = false;
			if (dam >= 8) {
				if (chance("tolerance-i")) {
					if (!(defender.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE))) {
						if (plugin.getConfig().getBoolean("enchant.tolerance-i.msg") == true) {
							testfor1 = true;
						}
					}
				}
			}
			if (plugin.getConfig().getString("enchant.tolerance-i.type").equalsIgnoreCase("armor")) {
				if (armorHasLore(inv, "§7Tolerance I")) {

					if (testfor1 == true) {
						defender.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80, 0));
						if (plugin.getConfig().getBoolean("enchant.tolerance-i.msg") == true) {
							defender.sendMessage("§6You tolerated §a" + dam + " §6damage.");

						}
					}
				}
			}
			if (!plugin.getConfig().getString("enchant.tolerance-i.type").equalsIgnoreCase("armor")) {
				if (itemHasLore(inv, "§7Tolerance I")) {
					defender.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80, 0));
					if (plugin.getConfig().getBoolean("enchant.tolerance-i.msg") == true) {
						defender.sendMessage("§6You tolerated §a" + dam + " §6damage.");

					}
				}
			}
			boolean testfor2 = false;
			if (dam >= 8) {
				if (chance("tolerance-ii")) {
					if (!(defender.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE))) {
						if (plugin.getConfig().getBoolean("enchant.tolerance-ii.msg") == true) {
							testfor2 = true;
						}
					}
				}
			}
			if (plugin.getConfig().getString("enchant.tolerance-ii.type").equalsIgnoreCase("armor")) {
				if (armorHasLore(inv, "§7Tolerance II")) {

					if (testfor2 == true) {
						defender.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 0));
						if (plugin.getConfig().getBoolean("enchant.tolerance-ii.msg") == true) {
							defender.sendMessage("§6You tolerated §a" + dam + " §6damage.");

						}
					}
				}
			}
			if (!plugin.getConfig().getString("enchant.tolerance-ii.type").equalsIgnoreCase("armor")) {
				if (itemHasLore(inv, "§7Tolerance II")) {
					if (testfor2 == true) {
						defender.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 120, 0));
						if (plugin.getConfig().getBoolean("enchant.tolerance-ii.msg") == true) {
							defender.sendMessage("§6You tolerated §a" + dam + " §6damage.");

						}
					}
				}
			}
			boolean testfor3 = false;
			if (dam >= 8) {
				if (chance("tolerance-iii")) {
					if (!(defender.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE))) {
						if (plugin.getConfig().getBoolean("enchant.tolerance-iii.msg") == true) {
							testfor3 = true;
						}
					}
				}
			}
			if (plugin.getConfig().getString("enchant.tolerance-iii.type").equalsIgnoreCase("armor")) {
				if (armorHasLore(inv, "§7Tolerance III")) {

					if (testfor3 == true) {
						defender.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 160, 1));
						if (plugin.getConfig().getBoolean("enchant.tolerance-iii.msg") == true) {
							defender.sendMessage("§6You tolerated §a" + dam + " §6damage.");

						}
					}
				}
			}
			if (!plugin.getConfig().getString("enchant.tolerance-iii.type").equalsIgnoreCase("armor")) {
				if (itemHasLore(inv, "§7Tolerance III")) {
					if (testfor3 == true) {
						defender.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 160, 1));
						if (plugin.getConfig().getBoolean("enchant.tolerance-iii.msg") == true) {
							defender.sendMessage("§6You tolerated §a" + dam + " §6damage.");

						}
					}
				}
			}
		} else {
			return;
		}
	}
}
