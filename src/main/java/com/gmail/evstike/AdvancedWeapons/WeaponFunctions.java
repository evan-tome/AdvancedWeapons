package com.gmail.evstike.AdvancedWeapons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

public class WeaponFunctions implements Listener {

	Fates plugin;

	public WeaponFunctions(Fates instance) {
		plugin = instance;
	}
	public boolean serverIs18() {
		if (Bukkit.getVersion().contains("1.8")) {
			return true;
		}
		return false;
	}
	public static List<UUID> lfireball = new ArrayList<UUID>();
	public static List<UUID> lfire = new ArrayList<UUID>();

	@SuppressWarnings({"deprecation"})
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event) {
		Player p = (Player) event.getPlayer();
		FileConfiguration conf = plugin.getConfig();
		List<Action> actions = Arrays.asList(Action.LEFT_CLICK_AIR, Action.RIGHT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_BLOCK);
		if(actions.contains(event.getAction())){

		ItemStack i = p.getInventory().getItemInHand();
		ItemMeta im = i.getItemMeta();
		if (i.getType().equals(UMaterial.DIAMOND_SWORD.getMaterial())) {
			if (im.hasDisplayName()) {
				if (im.getDisplayName().equals(ChatColor.RED + "The Destroyer")) {
					p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
				}
			}
		}
		if (p.isSneaking()) {
			if (i.getType().equals(UMaterial.STICK.getMaterial())) {
				if (im.hasDisplayName()) {
					if (im.getDisplayName().equals(ChatColor.RED + "The Dropper")) {
						event.setCancelled(true);
						Location loc = p.getEyeLocation().toVector().add(p.getLocation().getDirection().multiply(4)).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch());
						ShulkerBullet bullet = p.getWorld().spawn(loc, ShulkerBullet.class);
						bullet.setShooter(p);
						if (im.hasLore()) {
							if (im.getLore().get(0) != null) {
								List<String> lore = im.getLore();
								int ammo = Integer.parseInt(ChatColor.stripColor(lore.get(0)));
								int am = ammo - 1;
								if (ammo > 1) {
									lore.set(0, "§7" + am);
									im.setLore(lore);
									i.setItemMeta(im);
								}
								if (ammo == 1) {
									ShulkerBullet bullet2 = p.getWorld().spawn(loc, ShulkerBullet.class);
									bullet2.setShooter(p);
									ItemStack b = new ItemStack(UMaterial.STICK.getMaterial(), 1);
									ItemMeta bMeta = b.getItemMeta();
									bMeta.setDisplayName(ChatColor.RED + "The Dropper");
									lore.set(0, "§71");
									bMeta.setLore(lore);
									b.setItemMeta(bMeta);
									p.getInventory().removeItem(b);
									if (p.getItemInHand() != null) {
										if (p.getItemInHand().getType() == UMaterial.STICK.getMaterial()) {
											if (p.getItemInHand().hasItemMeta()) {
												int am2 = i.getAmount();
												ItemStack b2 = new ItemStack(UMaterial.STICK.getMaterial(), am2);
												ItemMeta b2Meta = b2.getItemMeta();
												b2Meta.setDisplayName(ChatColor.RED + "The Dropper");
												lore.set(0, "§75");
												b2Meta.setLore(lore);
												b2.setItemMeta(b2Meta);
												p.getInventory().setItemInHand(b2);
											}
										}
									}
								}
							}
						}
						if (conf.getString("dropper-msg").equals("actionbar")) {
						if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
							ActionBarAPI.sendActionBar(p, ChatColor.GOLD + "SHOT DROPPER AT " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ(), 20);
						}
						}
						if (conf.getString("dropper-msg").equals("true")) {
							p.sendMessage(ChatColor.GOLD + "SHOT DROPPER");
						}
						if (conf.getString("dropper-msg").equals("false")) {
							return;
						}
						if (conf.getString("dropper-msg").equals("actionbar")) {
							if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null) {
								return;
							}
						}
					}
				}
			}
		}
		if (i.getType().equals(UMaterial.DIAMOND_AXE.getMaterial())) {
			if (im.hasDisplayName()) {
				if (im.getDisplayName().equals(ChatColor.RED + "The Slayer")) {
					if (p.getHealth() <= 5) {
						if(!serverIs18()) {
							p.spawnParticle(Particle.HEART, p.getLocation(), 1);
						}
						p.setHealth(p.getHealth() + 2);
						if (conf.getString("slayer-msg").equals("actionbar")) {
							if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
								ActionBarAPI.sendActionBar(p, ChatColor.GOLD + "THE SLAYER HAS HEALED YOU", 20);
							}
						}
						if (conf.getString("slayer-msg").equals("true")) {
							p.sendMessage(ChatColor.GOLD + "THE SLAYER HAS HEALED YOU");
						}
						if (conf.getString("slayer-msg").equals("false")) {
							return;
						}
						if (conf.getString("slayer-msg").equals("actionbar")) {
							if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null) {
								return;
							}
						}
					}
				}
			}
		}
		if (p.isSneaking()) {
			if (i.getType().equals(UMaterial.BLAZE_ROD.getMaterial())) {
				if (im.hasDisplayName()) {
					if (im.getDisplayName().equals(ChatColor.RED + "Fireball Launcher")) {
						event.setCancelled(true);
						Location loc = p.getEyeLocation().toVector().add(p.getLocation().getDirection().multiply(2)).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch());
						Fireball fireball = p.getWorld().spawn(loc, Fireball.class);
						fireball.setIsIncendiary(false);
						fireball.setFireTicks(0);
						fireball.setShooter(p);
						lfireball.add(fireball.getUniqueId());
						lfire.add(fireball.getUniqueId());
						if (!serverIs18()) {

							p.spawnParticle(Particle.FLAME, loc, 1);
						}

						if (im.hasLore()) {
							if (im.getLore().get(0) != null) {
								List<String> lore = im.getLore();
								int ammo = Integer.parseInt(ChatColor.stripColor(lore.get(0)));
								int am = ammo - 1;

								if (ammo > 1) {
									lore.set(0, "§7" + am);
									im.setLore(lore);
									i.setItemMeta(im);
								}

								if (ammo == 1) {
									ItemStack b = new ItemStack(UMaterial.BLAZE_ROD.getMaterial(), 1);
									ItemMeta bMeta = b.getItemMeta();
									bMeta.setDisplayName(ChatColor.RED + "Fireball Launcher");
									lore.set(0, "§71");
									bMeta.setLore(lore);
									b.setItemMeta(bMeta);
									p.getInventory().removeItem(b);
									if (p.getItemInHand() != null) {
										if (p.getItemInHand().getType() == UMaterial.BLAZE_ROD.getMaterial()) {
											if (p.getItemInHand().hasItemMeta()) {
												int am2 = i.getAmount();
												ItemStack b2 = new ItemStack(UMaterial.BLAZE_ROD.getMaterial(), am2);
												ItemMeta b2Meta = b2.getItemMeta();
												b2Meta.setDisplayName(ChatColor.RED + "Fireball Launcher");
												lore.set(0, "§75");
												b2Meta.setLore(lore);
												b2.setItemMeta(b2Meta);
												p.getInventory().setItemInHand(b2);
											}
										}
									}
								}
							}
						}
						if (conf.getString("fireball-msg").equals("actionbar")) {
							if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
								ActionBarAPI.sendActionBar(p, ChatColor.GOLD + "FIREBALL", 20);
							}
						}
						if (conf.getString("fireball-msg").equals("true")) {
							p.sendMessage(ChatColor.GOLD + "FIREBALL");
						}
						if (conf.getString("fireball-msg").equals("false")) {
							return;
						}
						if (conf.getString("fireball-msg").equals("actionbar")) {
							if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null) {
								return;
							}
						}
					}
				}
			}
			}
		}
	}
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		Entity ent = event.getEntity();

		if (ent instanceof Fireball) {
			if (((Fireball) ent).getShooter() instanceof Player) {
				if (lfireball.contains(ent.getUniqueId())) {
					event.setCancelled(true); //Removes block damage
					lfireball.remove(ent.getUniqueId());
				}
			}
		}
	}
	@EventHandler
	public void onExplosionPrime(ExplosionPrimeEvent event) {
		Entity ent = event.getEntity();
		if (ent instanceof Fireball)
			if (((Fireball) ent).getShooter() instanceof Player) {
				if (lfire.contains(ent.getUniqueId())) {
					event.setFire(false); //Only really needed for fireballs
					event.setRadius(0); //Increased from default(1), since the fireball now don't cause fire
					lfire.remove(ent.getUniqueId());
				}
			}
	}
}
		





		
  
		
	
    

        
		

		
		





    

