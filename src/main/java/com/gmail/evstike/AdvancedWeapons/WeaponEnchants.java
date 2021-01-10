package com.gmail.evstike.AdvancedWeapons;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
public class WeaponEnchants extends API implements Listener {

	Fates plugin;

	public WeaponEnchants(Fates instance) {
		plugin = instance;
	}

	public List<String> poison = Arrays.asList("ZOMBIE", "HUSK", "SKELETON", "STRAY", "WITHER", "WITHER_SKELETON",
			"SPIDER", "CAVE_SPIDER", "DROWNED", "LIGHTNING", "PRIMED_TNT", "ZOMBIE_VILLAGER", "PIG_ZOMBIE",
			"SKELETON_HORSE", "ZOMBIE_HORSE", "PHANTOM", "ARMOR_STAND");
	public List<String> undead = Arrays.asList("ZOMBIE", "HUSK", "SKELETON", "STRAY", "WITHER", "WITHER_SKELETON",
			"DROWNED", "ZOMBIE_VILLAGER", "PIG_ZOMBIE", "SKELETON_HORSE", "ZOMBIE_HORSE", "PHANTOM");
	public List<String> affected = Lists.newArrayList();
	public String effects;

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
		Player p = event.getPlayer();
		FileConfiguration config = plugin.getConfig();
		ItemStack i = p.getInventory().getItemInHand();
		ItemMeta im = i.getItemMeta();
		int dur = i.getDurability();

		List<Action> actions = Arrays.asList(Action.LEFT_CLICK_AIR, Action.RIGHT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_BLOCK);
		if (actions.contains(event.getAction())) {

			if (serverIs19()) {
				if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
					return;
				}
			}
			if (im != null) {
				if (im.hasLore()) {
					for (String s : im.getLore()) {
						ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
						for (String key : section.getKeys(false)) {
							section.get(key);
							ConfigurationSection item = section.getConfigurationSection(key);

							String n = item.getString("name").replace('&', '§');
							String name = ChatColor.GRAY + ChatColor.stripColor(n);
							int time = item.getInt("duration");
							int ti = time * 20;
							int l = item.getInt("amplifier");

							if (name.equals(s)) {
								List events = item.getList("events");
								if (events.contains("interact")) {
									if (chance(ChatColor.stripColor(name).toLowerCase().replace(" ", "-"))) {

										String function = item.getString("function");

										if (item.contains("dimension")) {
											if (!(item.getStringList("dimension").contains(p.getWorld().getEnvironment().toString()))) {
												return;
											}
										}

										if (item.contains("thresholdself")) {
											if (!(p.getHealth() <= p.getMaxHealth() * item.getDouble("thresholdself") / 100)) {
												return;
											}
										}
										if (item.contains("thresholdother")) {
											if (!(p.getHealth() <= p.getMaxHealth() * item.getDouble("thresholdother") / 100)) {
												return;
											}
										}

										//POTION
										if (function.equals("potion")) {
											for (String effect : item.getStringList("effects")) {
												if (PotionEffectType.getByName(effect.toUpperCase()) != null) {
													if (item.contains("duration")) {
														if (item.contains("amplifier")) {
														} else {
															Bukkit.getLogger().warning(n + " does not have an amplifier.");
														}
													} else {
														Bukkit.getLogger().warning(n + " does not have a duration.");
													}
													p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
												}
											}
										}
										//EXPLOSION
										if (function.equals("explosion")) {
											if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
												Location loc = event.getClickedBlock().getLocation();
												Boolean destroy = true;
												int power = 1;
												if (item.contains("destroy")) {
													destroy = item.getBoolean("destroy");
												}
												if (item.contains("power")) {
													power = item.getInt("power");
												}
												if (destroy == true) {
													loc.getWorld().createExplosion(loc, power, false, true);
												}
												if (destroy == false) {
													loc.getWorld().createExplosion(loc, power, false, false);

												}
											}
										}
										if (item.contains("msg")) {
											if (item.getBoolean("msg") == true) {
												effects = StringUtils.capitaliseAllWords
														(item.getStringList("effects").toString().replace("[", "").replace("]", ""));
												p.sendMessage(item.getString("chatmsg").replace("{user}", p.getName()).replace("{potion}", effects));
												effects = "";
											}
										}
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
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity) {
			LivingEntity defender = (LivingEntity) e.getEntity();
			if (e.getDamager() instanceof Player) {
				Player attacker = (Player) e.getDamager();
				FileConfiguration config = plugin.getConfig();
				ItemStack i = attacker.getInventory().getItemInHand();
				ItemMeta im = i.getItemMeta();
				int dur = i.getDurability();

				if (im != null) {
					if (im.hasLore()) {
						for (String s : im.getLore()) {
							ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
							for (String key : section.getKeys(false)) {
								section.get(key);
								ConfigurationSection item = section.getConfigurationSection(key);

								String n = item.getString("name").replace('&', '§');
								String name = ChatColor.GRAY + ChatColor.stripColor(n);
								int time = item.getInt("duration");
								int ti = time * 20;
								int l = item.getInt("amplifier");

								if (name.equals(s)) {
									List events = item.getList("events");
									if (events.contains("attackother") || events.contains("attackself")) {
										if (chance(ChatColor.stripColor(name).toLowerCase().replace(" ", "-"))) {

											String function = item.getString("function");
											LivingEntity t = defender;

											if (item.contains("dimension")) {
												if (!(item.getStringList("dimension").contains(attacker.getWorld().getEnvironment().toString()))) {
													return;
												}
											}

											if (item.contains("thresholdself")) {
												if (!(attacker.getHealth() <= attacker.getMaxHealth() * item.getDouble("thresholdself") / 100)) {
													return;
												}
											}
											if (item.contains("thresholdother")) {
												if (!(defender.getHealth() <= defender.getMaxHealth() * item.getDouble("thresholdother") / 100)) {
													return;
												}
											}

											//TEST AGAINST MOBS
											if (item.contains("affected")) {
												for (String a : item.getStringList("affected")) {
													if (a.equals(t.getType().toString())) {
														affected.add(t.getType().toString());
													}
													if (a.equals("poison")) {
														for (String p : poison) {
															if (!affected.contains(p)) {
																affected.add(p);
															}
														}
													}
													if (a.equals("undead")) {
														for (String u : undead) {
															if (!affected.contains(u)) {
																affected.add(u);
															}
														}
													}
												}
												if (!affected.contains(t.getType().toString())) {
													return;
												}
												affected.clear();
											}
											if (item.contains("immune")) {
												for (String a : item.getStringList("immune")) {
													if (!(a.equals("poison") || a.equals("undead"))) {
														if (a.equalsIgnoreCase(t.getType().toString())) {
															return;
														}
													}
													if (a.equals("poison")) {
														if (poison.contains(t.getType().toString())) {
															return;
														}
													}
													if (a.equals("undead")) {
														if (undead.contains(t.getType().toString())) {
															return;
														}
													}
												}
											}

											//POTION
											if (function.equals("potion")) {
												for (String effect : item.getStringList("effects")) {
													if (PotionEffectType.getByName(effect.toUpperCase()) != null) {
														if (item.contains("duration")) {
															if (item.contains("amplifier")) {
															} else {
																Bukkit.getLogger().warning(n + " does not have an amplifier.");
															}
														} else {
															Bukkit.getLogger().warning(n + " does not have a duration.");
														}
														if (events.contains("attackother")) {
															defender.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
														}
													}
													if (events.contains("attackself")) {
														attacker.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
													}
												}
											}
											//DAMAGE
											if (function.equals("damage")) {
												for (Double effect : item.getDoubleList("effects")) {
													if (events.contains("attackother")) {
														defender.damage(effect);
													}
													if (events.contains("attackself")) {
														attacker.damage(effect);
													}
												}
											}
											//EXPLOSION
											if (function.equals("explosion")) {
												Location loc = defender.getLocation();
												if (events.contains("attackother")) {
													loc = defender.getLocation();
												}
												if (events.contains("attackself")) {
													loc = attacker.getLocation();
												}
												Boolean destroy = true;
												int power = 1;
												if (item.contains("destroy")) {
													destroy = item.getBoolean("destroy");
												}
												if (item.contains("power")) {
													power = item.getInt("power");
												}
												if (destroy == true) {
													loc.getWorld().createExplosion(loc, power, false, true);
												}
												if (destroy == false) {
													loc.getWorld().createExplosion(loc, power, false, false);

												}
											}
											if (item.contains("msg")) {
												if (item.getBoolean("msg") == true) {
													if (item.contains("chatmsg")) {
														String ent = "";
														if (defender != null) {
															ent = StringUtils.capitaliseAllWords(defender.getType().getName().toLowerCase().replace("_", " "));
														}
														effects = StringUtils.capitaliseAllWords
																(item.getStringList("effects").toString().replace("[", "").replace("]", ""));
														attacker.sendMessage(item.getString("chatmsg").replace("{defender}", ent)
																.replace("{user}", attacker.getName()).replace("{potion}", effects).replace("{damage}", effects));
														effects = "";
													}
												}
											}
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
		}
	}

	@EventHandler
	public void onItemDefend(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player defender = (Player) e.getEntity();
			if (e.getDamager() instanceof LivingEntity) {
				LivingEntity attacker = (LivingEntity) e.getDamager();
				FileConfiguration config = plugin.getConfig();
				ItemStack i = defender.getInventory().getItemInHand();
				ItemMeta im = i.getItemMeta();
				int dur = i.getDurability();

				if (im != null) {
					if (im.hasLore()) {
						for (String s : im.getLore()) {
							ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
							for (String key : section.getKeys(false)) {
								section.get(key);
								ConfigurationSection item = section.getConfigurationSection(key);

								String n = item.getString("name").replace('&', '§');
								String name = ChatColor.GRAY + ChatColor.stripColor(n);
								int time = item.getInt("duration");
								int ti = time * 20;
								int l = item.getInt("amplifier");

								if (name.equals(s)) {
									List events = item.getList("events");
									if (events.contains("itemother") || events.contains("itemself")) {
										if (chance(ChatColor.stripColor(name).toLowerCase().replace(" ", "-"))) {

											String function = item.getString("function");
											LivingEntity t = attacker;

											if (item.contains("dimension")) {
												if (!(item.getStringList("dimension").contains(defender.getWorld().getEnvironment().toString()))) {
													return;
												}
											}

											if (item.contains("thresholdself")) {
												if (!(defender.getHealth() <= defender.getMaxHealth() * item.getDouble("thresholdself") / 100)) {
													return;
												}
											}
											if (item.contains("thresholdother")) {
												if (!(attacker.getHealth() <= attacker.getMaxHealth() * item.getDouble("thresholdother") / 100)) {
													return;
												}
											}

											//POTION
											if (function.equals("potion")) {
												for (String effect : item.getStringList("effects")) {
													if (PotionEffectType.getByName(effect.toUpperCase()) != null) {
														if (item.contains("duration")) {
															if (item.contains("amplifier")) {
															} else {
																Bukkit.getLogger().warning(n + " does not have an amplifier.");
															}
														} else {
															Bukkit.getLogger().warning(n + " does not have a duration.");
														}
														if (events.contains("itemself")) {
															defender.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
														}
														if (events.contains("itemother")) {
															attacker.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
														}
													}
												}
											}
											//DAMAGE
											if (function.equals("damage")) {
												for (Double effect : item.getDoubleList("effects")) {
													if (events.contains("itemself")) {
														defender.damage(effect);
													}
													if (events.contains("itemother")) {
														attacker.damage(effect);
													}
												}
											}
											//EXPLOSION
											if (function.equals("explosion")) {
												Location loc = defender.getLocation();
												if (events.contains("itemself")) {
													loc = defender.getLocation();
												}
												if (events.contains("itemother")) {
													loc = attacker.getLocation();
												}
												Boolean destroy = true;
												int power = 1;
												if (item.contains("destroy")) {
													destroy = item.getBoolean("destroy");
												}
												if (item.contains("power")) {
													power = item.getInt("power");
												}
												if (destroy == true) {
													loc.getWorld().createExplosion(loc, power, false, true);
												}
												if (destroy == false) {
													loc.getWorld().createExplosion(loc, power, false, false);

												}
											}
											if (item.contains("msg")) {
												if (item.getBoolean("msg") == true) {
													if (item.contains("chatmsg")) {
														String ent = "";
														if (defender != null) {
															ent = StringUtils.capitaliseAllWords(attacker.getType().getName().toLowerCase().replace("_", " "));
														}
														effects = StringUtils.capitaliseAllWords
																(item.getStringList("effects").toString().replace("[", "").replace("]", ""));
														defender.sendMessage(item.getString("chatmsg").replace("{attacker}", ent)
																.replace("{user}", defender.getName()).replace("{potion}", effects).replace("{damage}", effects));
														effects = "";
													}
												}
											}
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
		}
	}

	@EventHandler
	public void onArmorDefend(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player defender = (Player) e.getEntity();
			if (e.getDamager() instanceof LivingEntity) {
				LivingEntity attacker = (LivingEntity) e.getDamager();
				FileConfiguration config = plugin.getConfig();

				for (ItemStack i : defender.getInventory().getArmorContents()) {
					if (i != null) {
						if (i.hasItemMeta()) {
							ItemMeta im = i.getItemMeta();
							if (im.hasLore()) {
								int dur = i.getDurability();

								for (String s : im.getLore()) {
									ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
									for (String key : section.getKeys(false)) {
										section.get(key);
										ConfigurationSection item = section.getConfigurationSection(key);

										String n = item.getString("name").replace('&', '§');
										String name = ChatColor.GRAY + ChatColor.stripColor(n);
										int time = item.getInt("duration");
										int ti = time * 20;
										int l = item.getInt("amplifier");

										if (name.equals(s)) {
											List events = item.getList("events");
											if (events.contains("armorother") || events.contains("armorself")) {
												if (chance(ChatColor.stripColor(name).toLowerCase().replace(" ", "-"))) {

													String function = item.getString("function");
													LivingEntity t = attacker;

													if (item.contains("dimension")) {
														if (!(item.getStringList("dimension").contains(defender.getWorld().getEnvironment().toString()))) {
															return;
														}
													}
													if (item.contains("thresholdself")) {
														if (!(defender.getHealth() <= defender.getMaxHealth() * item.getDouble("thresholdself") / 100)) {
															return;
														}
													}
													if (item.contains("thresholdother")) {
														if (!(attacker.getHealth() <= attacker.getMaxHealth() * item.getDouble("thresholdother") / 100)) {
															return;
														}
													}

													//TEST AGAINST MOBS
													if (item.contains("affected")) {
														for (String a : item.getStringList("affected")) {
															if (a.equals(t.getType().toString())) {
																affected.add(t.getType().toString());
															}
															if (a.equals("poison")) {
																for (String p : poison) {
																	if (!affected.contains(p)) {
																		affected.add(p);
																	}
																}
															}
															if (a.equals("undead")) {
																for (String u : undead) {
																	if (!affected.contains(u)) {
																		affected.add(u);
																	}
																}
															}
														}
														if (!affected.contains(t.getType().toString())) {
															return;
														}
														affected.clear();
													}
													if (item.contains("immune")) {
														for (String a : item.getStringList("immune")) {
															if (!(a.equals("poison") || a.equals("undead"))) {
																if (a.equalsIgnoreCase(t.getType().toString())) {
																	return;
																}
															}
															if (a.equals("poison")) {
																if (poison.contains(t.getType().toString())) {
																	return;
																}
															}
															if (a.equals("undead")) {
																if (undead.contains(t.getType().toString())) {
																	return;
																}
															}
														}
													}

													//POTION
													if (function.equals("potion")) {
														for (String effect : item.getStringList("effects")) {
															if (PotionEffectType.getByName(effect.toUpperCase()) != null) {
																if (item.contains("duration")) {
																	if (item.contains("amplifier")) {
																	} else {
																		Bukkit.getLogger().warning(n + " does not have an amplifier.");
																	}
																} else {
																	Bukkit.getLogger().warning(n + " does not have a duration.");
																}
																if (events.contains("armorself")) {
																	defender.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
																}
																if (events.contains("armorother")) {
																	attacker.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
																}
															}
														}
													}

													//DAMAGE
													if (function.equals("damage")) {
														for (Double effect : item.getDoubleList("effects")) {
															if (events.contains("armorself")) {
																defender.damage(effect);
															}
															if (events.contains("armorother")) {
																attacker.damage(effect);
															}
														}
													}
													//EXPLOSION
													if (function.equals("explosion")) {
														Location loc = defender.getLocation();
														if (events.contains("itemself")) {
															loc = defender.getLocation();
														}
														if (events.contains("itemother")) {
															loc = attacker.getLocation();
														}
														Boolean destroy = true;
														int power = 1;
														if (item.contains("destroy")) {
															destroy = item.getBoolean("destroy");
														}
														if (item.contains("power")) {
															power = item.getInt("power");
														}
														if (destroy == true) {
															loc.getWorld().createExplosion(loc, power, false, true);
														}
														if (destroy == false) {
															loc.getWorld().createExplosion(loc, power, false, false);

														}
													}
													if (item.contains("msg")) {
														if (item.getBoolean("msg") == true) {
															String ent = "";
															if (defender != null) {
																ent = StringUtils.capitaliseAllWords(attacker.getType().getName().toLowerCase().replace("_", " "));
															}
															effects = StringUtils.capitaliseAllWords
																	(item.getStringList("effects").toString().replace("[", "").replace("]", ""));
															defender.sendMessage(item.getString("chatmsg").replace("{attacker}", ent)
																	.replace("{user}", defender.getName()).replace("{potion}", effects).replace("{damage}", effects));
															effects = "";
														}
													}
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
				}
			}
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		FileConfiguration config = plugin.getConfig();
		ItemStack i = p.getInventory().getItemInHand();
		ItemMeta im = i.getItemMeta();
		int dur = i.getDurability();
		Block b = e.getBlock();
		XMaterial bt = XMaterial.matchXMaterial(e.getBlock().getType());

		if (im != null) {
			if (im.hasLore()) {
				for (String s : im.getLore()) {
					ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
					for (String key : section.getKeys(false)) {
						section.get(key);
						ConfigurationSection item = section.getConfigurationSection(key);

						String n = item.getString("name").replace('&', '§');
						String name = ChatColor.GRAY + ChatColor.stripColor(n);
						int time = item.getInt("duration");
						int ti = time * 20;
						int l = item.getInt("amplifier");

						if (name.equals(s)) {
							List events = item.getList("events");
							if (events.contains("blockbreak")) {
								if (chance(ChatColor.stripColor(name).toLowerCase().replace(" ", "-"))) {

									String function = item.getString("function");

									if (item.contains("dimension")) {
										if (!(item.getStringList("dimension").contains(p.getWorld().getEnvironment().toString()))) {
											return;
										}
									}

									if (item.contains("thresholdself")) {
										if (!(p.getHealth() <= p.getMaxHealth() * item.getDouble("thresholdself") / 100)) {
											return;
										}
									}
									if (item.contains("thresholdother")) {
										if (!(p.getHealth() <= p.getMaxHealth() * item.getDouble("thresholdother") / 100)) {
											return;
										}
									}

									//POTION
									if (function.equals("potion")) {
										for (String effect : item.getStringList("effects")) {
											if (PotionEffectType.getByName(effect.toUpperCase()) != null) {
												if (item.contains("duration")) {
													if (item.contains("amplifier")) {
													} else {
														Bukkit.getLogger().warning(n + " does not have an amplifier.");
													}
												} else {
													Bukkit.getLogger().warning(n + " does not have a duration.");
												}
												p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
											}
										}
									}
									//DAMAGE
									if (function.equals("damage")) {
										for (Double effect : item.getDoubleList("effects")) {
											p.damage(effect);
										}
									}
									//EXPLOSION
									if (function.equals("explosion")) {
										Location loc = p.getLocation();

										Boolean destroy = true;
										int power = 1;
										if (item.contains("destroy")) {
											destroy = item.getBoolean("destroy");
										}
										if (item.contains("power")) {
											power = item.getInt("power");
										}
										if (destroy == true) {
											loc.getWorld().createExplosion(loc, power, false, true);
										}
										if (destroy == false) {
											loc.getWorld().createExplosion(loc, power, false, false);

										}
									}
									//FORTUNE
									if (function.equals("fortune")) {
										for (String effect : item.getStringList("blocks")) {
											if (effect.equals(bt.parseMaterial().name())) {
												if (item.contains("amount")) {
													if (item.contains("dropatblock")) {
													} else {
														Bukkit.getLogger().warning(n + " does not specify a drop location.");
													}
												} else {
													Bukkit.getLogger().warning(n + " does not have an amount.");
												}
												ItemStack bd = new ItemStack(bt.parseMaterial(), item.getInt("amount"));
												if (item.getBoolean("dropatblock") == true) {
													p.getWorld().dropItemNaturally(b.getLocation(), bd);
												}
												if (item.getBoolean("dropatblock") == false) {
													p.getWorld().dropItemNaturally(p.getLocation(), bd);
												}
											}
										}
									}
									if (item.contains("msg")) {
										if (item.getBoolean("msg") == true) {
											if (item.contains("chatmsg")) {
												String ent = "";
												if (p != null) {
													ent = StringUtils.capitaliseAllWords(p.getType().getName().toLowerCase().replace("_", " "));
												}
												effects = StringUtils.capitaliseAllWords
														(item.getStringList("effects").toString().replace("[", "").replace("]", ""));
												p.sendMessage(item.getString("chatmsg").replace("{defender}", ent)
														.replace("{user}", p.getName()).replace("{potion}", effects).replace("{damage}", effects));
												effects = "";
											}
										}
									}
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
}