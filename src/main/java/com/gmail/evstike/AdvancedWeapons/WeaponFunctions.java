package com.gmail.evstike.AdvancedWeapons;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.Snow;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class WeaponFunctions extends API implements Listener {

    Fates plugin;

    public WeaponFunctions(Fates instance) {
        plugin = instance;
    }

    public static List<UUID> lfireball = new ArrayList<UUID>();
    public static List<UUID> lfire = new ArrayList<UUID>();

    public static List<UUID> lsnowball = new ArrayList<UUID>();

    @SuppressWarnings({"deprecation"})
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = (Player) event.getPlayer();
        ItemStack i = p.getInventory().getItemInHand();
        ItemMeta im = i.getItemMeta();
        FileConfiguration conf = plugin.getConfig();

        List<Action> actions = Arrays.asList(Action.LEFT_CLICK_AIR, Action.RIGHT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_BLOCK);
        if (actions.contains(event.getAction())) {

            if (serverIs19()) {
                if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
                    return;
                }
            }

            if (i.getType().equals(XMaterial.DIAMOND_SWORD.parseMaterial())) {
                if (im.hasDisplayName()) {
                    if (im.getDisplayName().equals(ChatColor.RED + "The Destroyer")) {
                        p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
                    }
                }
            }
            if (i.getType().equals(XMaterial.DIAMOND_AXE.parseMaterial())) {
                if (im.hasDisplayName()) {
                    if (im.getDisplayName().equals(ChatColor.RED + "The Slayer")) {
                        if (p.getHealth() <= 5) {
                            if (serverIs19()) {
                                p.spawnParticle(Particle.HEART, p.getLocation(), 1);
                            }
                            p.setHealth(p.getHealth() + 2);
                            if(i.getDurability()<=XMaterial.DIAMOND_AXE.parseMaterial().getMaxDurability()-25) {
                                short id = (short) ((short) i.getDurability() + 25);
                                i.setDurability(id);
                            }
                            if(i.getDurability()>XMaterial.DIAMOND_AXE.parseMaterial().getMaxDurability()-25) {
                                ItemStack a = new ItemStack(XMaterial.AIR.parseMaterial(), 1);
                                p.getInventory().setItem(p.getInventory().getHeldItemSlot(), a);
                            }
                            if (conf.getString("weapon.the-slayer.msg").equals("actionbar")) {
                                if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
                                    ActionBarAPI.sendActionBar(p, ChatColor.GOLD + "THE SLAYER HAS HEALED YOU", 20);
                                }
                            }
                            if (conf.getString("weapon.the-slayer.msg").equals("true")) {
                                p.sendMessage(ChatColor.GOLD + "THE SLAYER HAS HEALED YOU");
                            }
                            if (conf.getString("weapon.the-slayer.msg").equals("false")) {
                                return;
                            }
                            if (conf.getString("weapon.the-slayer.msg").equals("actionbar")) {
                                if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            if (p.isSneaking()) {
                if (i.getType().equals(XMaterial.STICK.parseMaterial())) {
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
                                        ItemStack b = new ItemStack(XMaterial.STICK.parseMaterial(), 1);
                                        ItemMeta bMeta = b.getItemMeta();
                                        bMeta.setDisplayName(ChatColor.RED + "The Dropper");
                                        lore.set(0, "§71");
                                        bMeta.setLore(lore);
                                        b.setItemMeta(bMeta);
                                        p.getInventory().removeItem(b);
                                        if (p.getItemInHand() != null) {
                                            if (p.getItemInHand().getType() == XMaterial.STICK.parseMaterial()) {
                                                if (p.getItemInHand().hasItemMeta()) {
                                                    int am2 = i.getAmount();
                                                    ItemStack b2 = new ItemStack(XMaterial.STICK.parseMaterial(), am2);
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
                            if (conf.getString("weapon.the-dropper.msg").equals("actionbar")) {
                                if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
                                    ActionBarAPI.sendActionBar(p, ChatColor.GOLD + "SHOT DROPPER AT " + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ(), 20);
                                }
                            }
                            if (conf.getString("weapon.the-dropper.msg").equals("true")) {
                                p.sendMessage(ChatColor.GOLD + "SHOT DROPPER");
                            }
                            if (conf.getString("weapon.the-dropper.msg").equals("false")) {
                                return;
                            }
                            if (conf.getString("weapon.the-dropper.msg").equals("actionbar")) {
                                if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            if (p.isSneaking()) {
                if (i.getType().equals(XMaterial.BLAZE_ROD.parseMaterial())) {
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
                                        ItemStack b = new ItemStack(XMaterial.BLAZE_ROD.parseMaterial(), 1);
                                        ItemMeta bMeta = b.getItemMeta();
                                        bMeta.setDisplayName(ChatColor.RED + "Fireball Launcher");
                                        lore.set(0, "§71");
                                        bMeta.setLore(lore);
                                        b.setItemMeta(bMeta);
                                        p.getInventory().removeItem(b);
                                        if (p.getItemInHand() != null) {
                                            if (p.getItemInHand().getType() == XMaterial.BLAZE_ROD.parseMaterial()) {
                                                if (p.getItemInHand().hasItemMeta()) {
                                                    int am2 = i.getAmount();
                                                    ItemStack b2 = new ItemStack(XMaterial.BLAZE_ROD.parseMaterial(), am2);
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
                            if (conf.getString("weapon.fireball-launcher.msg").equals("actionbar")) {
                                if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
                                    ActionBarAPI.sendActionBar(p, ChatColor.GOLD + "FIREBALL", 20);
                                }
                            }
                            if (conf.getString("weapon.fireball-launcher.msg").equals("true")) {
                                p.sendMessage(ChatColor.GOLD + "FIREBALL");
                            }
                            if (conf.getString("weapon.fireball-launcher.msg").equals("false")) {
                                return;
                            }
                            if (conf.getString("weapon.fireball-launcher.msg").equals("actionbar")) {
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
    public void onDeath(EntityDeathEvent e) {
        FileConfiguration conf = plugin.getConfig();
        if (e.getEntity() instanceof LivingEntity && e.getEntity().getKiller() instanceof Player) {
            Player p = (Player) e.getEntity().getKiller();
            LivingEntity mob = e.getEntity();
            ItemStack i = p.getInventory().getItemInHand();
            ItemMeta im = i.getItemMeta();

            if (i.getType().equals(XMaterial.BONE.parseMaterial())) {
                if (im.hasDisplayName()) {
                    if (im.getDisplayName().equals(ChatColor.RED + "The Skeletal Sword")) {
                        if (serverIs19()) {
                            p.getWorld().spawnParticle(Particle.SPELL, mob.getLocation(), 20);
                        }
                        ItemStack pump = new ItemStack(XMaterial.PUMPKIN.parseMaterial());
                        mob.getWorld().dropItem(mob.getLocation(), pump);
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
                                ItemStack b = new ItemStack(XMaterial.BONE.parseMaterial(), 1);
                                ItemMeta bMeta = b.getItemMeta();
                                bMeta.setDisplayName(ChatColor.RED + "The Skeletal Sword");
                                lore.set(0, "§71");
                                bMeta.setLore(lore);
                                b.setItemMeta(bMeta);
                                p.getInventory().removeItem(b);
                                if (p.getItemInHand() != null) {
                                    if (p.getItemInHand().getType() == XMaterial.BONE.parseMaterial()) {
                                        if (p.getItemInHand().hasItemMeta()) {
                                            int am2 = i.getAmount();
                                            ItemStack b2 = new ItemStack(XMaterial.BONE.parseMaterial(), am2);
                                            ItemMeta b2Meta = b2.getItemMeta();
                                            b2Meta.setDisplayName(ChatColor.RED + "The Skeletal Sword");
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
                    if (conf.getString("weapon.the-skeletal-sword.msg").equals("actionbar")) {
                        if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
                            ActionBarAPI.sendActionBar(p, ChatColor.GOLD + "THE SKELETAL SWORD BRINGS DEATH", 20);
                        }
                    }
                    if (conf.getString("weapon.the-skeletal-sword.msg").equals("true")) {
                        p.sendMessage(ChatColor.RED + "THE SKELETAL SWORD BRINGS DEATH");
                    }
                    if (conf.getString("weapon.the-skeletal-sword.msg").equals("false")) {
                        return;
                    }
                    if (conf.getString("weapon.the-skeletal-sword.msg").equals("actionbar")) {
                        if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null) {
                            return;
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onSnowballThrow(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Snowball) {
            Snowball e = (Snowball) event.getEntity();
            if (e.getShooter() instanceof Player) {
                Player p = (Player) e.getShooter();
                if (p.getInventory().getItemInHand().getType() == XMaterial.SNOWBALL.parseMaterial()) {
                    if (p.getInventory().getItemInHand().hasItemMeta()) {
                        if (p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "Ice Chunk")) {
                            if (!lsnowball.contains(e.getUniqueId())) {
                                lsnowball.add(e.getUniqueId());
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onSnowballHit(EntityDamageByEntityEvent event) {
        FileConfiguration conf = plugin.getConfig();
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity e = (LivingEntity) event.getEntity();
            if (event.getDamager() instanceof Snowball) {
                Snowball ent = (Snowball) event.getDamager();

                if(ent.getShooter() instanceof Player) {
                    Player p = (Player) ent.getShooter();
                    if (lsnowball.contains(ent.getUniqueId())) {
                        e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
                        if(serverIs113()) {
                            BlockData fallingDustData = Material.matchMaterial(XMaterial.SNOW_BLOCK.parseMaterial().toString()).createBlockData();
                            e.getWorld().spawnParticle(Particle.FALLING_DUST, e.getEyeLocation(), 10, 0.3, 0.3, 0.3, fallingDustData);
                        }
                        lsnowball.remove(ent.getUniqueId());

                        if (conf.getString("weapon.ice-chunk.msg").equals("actionbar")) {
                            if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
                                ActionBarAPI.sendActionBar(p, ChatColor.WHITE + "ICE CHUNK", 20);
                            }
                        }
                        if (conf.getString("weapon.ice-chunk.msg").equals("true")) {
                            p.sendMessage(ChatColor.WHITE + "ICE CHUNK");
                        }
                        if (conf.getString("weapon.ice-chunk.msg").equals("false")) {
                            return;
                        }
                        if (conf.getString("weapon.ice-chunk.msg").equals("actionbar")) {
                            if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null) {
                                return;
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
                    event.setFire(false);
                    event.setRadius(0);
                    lfire.remove(ent.getUniqueId());
                }
            }
    }
}