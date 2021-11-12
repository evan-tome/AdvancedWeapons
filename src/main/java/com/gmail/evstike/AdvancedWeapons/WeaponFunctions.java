package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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
    
            if (i.getType().equals(Material.DIAMOND_SWORD)) {
                if (im.hasDisplayName()) {
                    if (im.getDisplayName().equals(ChatColor.RED + "The Destroyer")) {
                        p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
                    }
                }
            }
            if (i.getType().equals(Material.DIAMOND_AXE)) {
                if (im.hasDisplayName()) {
                    if (im.getDisplayName().equals(ChatColor.RED + "The Slayer")) {
                        if (p.getHealth() <= 5) {
                            p.spawnParticle(Particle.HEART, p.getLocation(), 1);
                            p.setHealth(p.getHealth() + 2);
                            if (i.getDurability() <= Material.DIAMOND_AXE.getMaxDurability() - 25) {
                                short id = (short) ((short) i.getDurability() + 25);
                                i.setDurability(id);
                            }
                            if (i.getDurability() > Material.DIAMOND_AXE.getMaxDurability() - 25) {
                                ItemStack a = new ItemStack(Material.AIR, 1);
                                p.getInventory().setItem(p.getInventory().getHeldItemSlot(), a);
                            }
                            if (conf.getString("weapon.the-slayer.msg").equals("actionbar")) {
                                sendActionBar(p, ChatColor.GOLD + "THE SLAYER HAS HEALED YOU");
                            }
                            if (conf.getString("weapon.the-slayer.msg").equals("true")) {
                                p.sendMessage(ChatColor.GOLD + "THE SLAYER HAS HEALED YOU");
                            }
                            if (conf.getString("weapon.the-slayer.msg").equals("false")) {
                                return;
                            }
                        }
                    }
                }
            }
            if (p.isSneaking()) {
                if (i.getType().equals(Material.STICK)) {
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
                                        ItemStack b = new ItemStack(Material.STICK, 1);
                                        ItemMeta bMeta = b.getItemMeta();
                                        bMeta.setDisplayName(ChatColor.RED + "The Dropper");
                                        lore.set(0, "§71");
                                        bMeta.setLore(lore);
                                        b.setItemMeta(bMeta);
                                        p.getInventory().removeItem(b);
                                        if (p.getItemInHand() != null) {
                                            if (p.getItemInHand().getType() == Material.STICK) {
                                                if (p.getItemInHand().hasItemMeta()) {
                                                    int am2 = i.getAmount();
                                                    ItemStack b2 = new ItemStack(Material.STICK, am2);
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
                                sendActionBar(p, ChatColor.GOLD + "SHOT DROPPER");
                            }
                            if (conf.getString("weapon.the-dropper.msg").equals("true")) {
                                p.sendMessage(ChatColor.GOLD + "SHOT DROPPER");
                            }
                            if (conf.getString("weapon.the-dropper.msg").equals("false")) {
                                return;
                            }
                        }
                    }
                }
            }
            if (p.isSneaking()) {
                if (i.getType().equals(Material.BLAZE_ROD)) {
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
                            if (serverIs19()) {
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
                                        ItemStack b = new ItemStack(Material.BLAZE_ROD, 1);
                                        ItemMeta bMeta = b.getItemMeta();
                                        bMeta.setDisplayName(ChatColor.RED + "Fireball Launcher");
                                        lore.set(0, "§71");
                                        bMeta.setLore(lore);
                                        b.setItemMeta(bMeta);
                                        p.getInventory().removeItem(b);
                                        if (p.getItemInHand() != null) {
                                            if (p.getItemInHand().getType() == Material.BLAZE_ROD) {
                                                if (p.getItemInHand().hasItemMeta()) {
                                                    int am2 = i.getAmount();
                                                    ItemStack b2 = new ItemStack(Material.BLAZE_ROD, am2);
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
                                sendActionBar(p, ChatColor.GOLD + "FIREBALL");
                            }
                            if (conf.getString("weapon.fireball-launcher.msg").equals("true")) {
                                p.sendMessage(ChatColor.GOLD + "FIREBALL");
                            }
                            if (conf.getString("weapon.fireball-launcher.msg").equals("false")) {
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (i.getType().equals(Material.LEAD)) {
            if (im.hasDisplayName()) {
                if (im.getDisplayName().equals(ChatColor.RED + "Spirit Leash")) {
                    event.setCancelled(true);
                    Vector playerDirection = p.getLocation().getDirection();
                    Arrow fireball = p.launchProjectile(Arrow.class, playerDirection.multiply(3));
                    fireball.setShooter(p);
                    fireball.setColor(Color.PURPLE);
                    lfire.add(fireball.getUniqueId());
                    
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
                                ItemStack b = new ItemStack(Material.LEAD, 1);
                                ItemMeta bMeta = b.getItemMeta();
                                bMeta.setDisplayName(ChatColor.RED + "Spirit Leash");
                                lore.set(0, "§71");
                                bMeta.setLore(lore);
                                b.setItemMeta(bMeta);
                                p.getInventory().removeItem(b);
                                if (p.getItemInHand() != null) {
                                    if (p.getItemInHand().getType() == Material.LEAD) {
                                        if (p.getItemInHand().hasItemMeta()) {
                                            int am2 = i.getAmount();
                                            ItemStack b2 = new ItemStack(Material.LEAD, am2);
                                            ItemMeta b2Meta = b2.getItemMeta();
                                            b2Meta.setDisplayName(ChatColor.RED + "Spirit Leash");
                                            lore.set(0, "§73");
                                            b2Meta.setLore(lore);
                                            b2.setItemMeta(b2Meta);
                                            p.getInventory().setItemInHand(b2);
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
    public void onDeath(EntityDeathEvent e) {
        FileConfiguration conf = plugin.getConfig();
        if (e.getEntity() instanceof LivingEntity && e.getEntity().getKiller() instanceof Player) {
            Player p = (Player) e.getEntity().getKiller();
            LivingEntity mob = e.getEntity();
            ItemStack i = p.getInventory().getItemInHand();
            ItemMeta im = i.getItemMeta();
    
            if (i.getType().equals(Material.BONE)) {
                if (im.hasDisplayName()) {
                    if (im.getDisplayName().equals(ChatColor.RED + "The Skeletal Sword")) {
                        p.getWorld().spawnParticle(Particle.SPELL, mob.getLocation(), 20);
                        ItemStack pump = new ItemStack(Material.PUMPKIN);
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
                                ItemStack b = new ItemStack(Material.BONE, 1);
                                ItemMeta bMeta = b.getItemMeta();
                                bMeta.setDisplayName(ChatColor.RED + "The Skeletal Sword");
                                lore.set(0, "§71");
                                bMeta.setLore(lore);
                                b.setItemMeta(bMeta);
                                p.getInventory().removeItem(b);
                                if (p.getItemInHand() != null) {
                                    if (p.getItemInHand().getType() == Material.BONE) {
                                        if (p.getItemInHand().hasItemMeta()) {
                                            int am2 = i.getAmount();
                                            ItemStack b2 = new ItemStack(Material.BONE, am2);
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
                        sendActionBar(p, ChatColor.GOLD + "THE SKELETAL SWORD BRINGS DEATH");
                    }
                    if (conf.getString("weapon.the-skeletal-sword.msg").equals("true")) {
                        p.sendMessage(ChatColor.RED + "THE SKELETAL SWORD BRINGS DEATH");
                    }
                    if (conf.getString("weapon.the-skeletal-sword.msg").equals("false")) {
                        return;
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
                if (p.getInventory().getItemInHand().getType() == Material.SNOWBALL) {
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
    
                if (ent.getShooter() instanceof Player) {
                    Player p = (Player) ent.getShooter();
                    if (lsnowball.contains(ent.getUniqueId())) {
                        e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
                        BlockData fallingDustData = Material.matchMaterial(Material.SNOW_BLOCK.toString()).createBlockData();
                        e.getWorld().spawnParticle(Particle.FALLING_DUST, e.getEyeLocation(), 10, 0.3, 0.3, 0.3, fallingDustData);
                        lsnowball.remove(ent.getUniqueId());
    
                        if (conf.getString("weapon.ice-chunk.msg").equals("actionbar")) {
                            sendActionBar(p, ChatColor.WHITE + "BRRRR... YOU HIT A " + e.getType().name().replace("_", " ") + " WITH AN ICE CHUNK");
                        }
                        if (conf.getString("weapon.ice-chunk.msg").equals("true")) {
                            p.sendMessage(ChatColor.WHITE + "BRRRR... YOU HIT A " + e.getType().name().replace("_", " ") + " WITH AN ICE CHUNK");
                        }
                        if (conf.getString("weapon.ice-chunk.msg").equals("false")) {
                            return;
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
        if (ent instanceof Fireball) {
            if (((Fireball) ent).getShooter() instanceof Player) {
                if (lfire.contains(ent.getUniqueId())) {
                    event.setFire(false);
                    event.setRadius(0);
                    lfire.remove(ent.getUniqueId());
                }
            }
        }
    }
    
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Entity ent = event.getEntity();
        if (ent instanceof Arrow) {
            if (((Arrow) ent).getShooter() instanceof Player) {
                if (lfire.contains(ent.getUniqueId())) {
                    ent.remove();
                    if (event.getHitEntity() instanceof LivingEntity) {
                        LivingEntity lent = (LivingEntity) event.getHitEntity();
                        ((Player) ((Arrow) ent).getShooter()).spawnParticle(Particle.DRAGON_BREATH, lent.getLocation(), 4);
                        lent.teleport(((Player) ((Arrow) ent).getShooter()).getLocation());
                        Player p = ((Player) ((Arrow) ent).getShooter()).getPlayer();
                        
                        FileConfiguration conf = plugin.getConfig();
                        if (conf.getString("weapon.spirit-leash.msg").equals("actionbar")) {
                            sendActionBar(p, ChatColor.LIGHT_PURPLE + "YOU SUMMONED A " + lent.getType().name().replace("_", " "));
                        }
                        if (conf.getString("weapon.spirit-leash.msg").equals("true")) {
                            p.sendMessage(ChatColor.LIGHT_PURPLE + "YOU SUMMONED A " + lent.getType().name().replace("_", " "));
                        }
                        if (conf.getString("weapon.spirit-leash.msg").equals("false")) {
                            return;
                        }
                    }
                    lfire.remove(ent.getUniqueId());
                }
            }
        }
    }
    
    @EventHandler
    public void onGrapple(PlayerFishEvent e) {
        if (e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY) {
            if (e.getCaught() instanceof LeashHitch) {
                LeashHitch l = (LeashHitch) e.getCaught();
                Player p = e.getPlayer();
                if (p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("§cGrappling Hook")) {
                    l.setCustomNameVisible(true);
                    l.setCustomName("§6§l⭐");
                    //Location d = e.getCaught().getLocation();
                    Location d = e.getHook().getLocation();
                    Vector v = d.toVector().subtract(p.getLocation().toVector()).normalize();
                    p.setVelocity(v.multiply(2.0));
                    p.getItemInHand().setDurability((short) (p.getItemInHand().getDurability() + 16));
    
                    FileConfiguration conf = plugin.getConfig();
                    if (conf.getString("weapon.grappling-hook.msg").equals("actionbar")) {
                        sendActionBar(p, ChatColor.GOLD + "GRAPPLING");
                    }
                    if (conf.getString("weapon.grappling-hook.msg").equals("true")) {
                        p.sendMessage(ChatColor.GOLD + "GRAPPLING");
                    }
                    if (conf.getString("weapon.grappling-hook.msg").equals("false")) {
                        return;
                    }
    
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            l.setCustomNameVisible(false);
                        }
                    }, 20L);
                }
            }
        }
    }
    
    @EventHandler
    public void onHook(ProjectileHitEvent e) {
        if (e.getEntity() instanceof FishHook) {
            if (e.getHitEntity() instanceof LeashHitch) {
                if (e.getEntity().getShooter() instanceof Player) {
                    Player p = (Player) e.getEntity().getShooter();
                    if (p.getInventory().getItemInHand().getItemMeta().getDisplayName().equals("§cGrappling Hook")) {
    
                        FileConfiguration conf = plugin.getConfig();
                        if (conf.getString("weapon.grappling-hook.msg").equals("actionbar")) {
                            sendActionBar(p, ChatColor.GREEN + "HOOKED");
                        }
                        if (conf.getString("weapon.grappling-hook.msg").equals("true")) {
                            p.sendMessage(ChatColor.GREEN + "HOOKED");
                        }
                        if (conf.getString("weapon.grappling-hook.msg").equals("false")) {
                            return;
                        }
                        LeashHitch l = (LeashHitch) e.getHitEntity();
                        l.setCustomName("§a§l+");
                        l.setCustomNameVisible(true);
                        
                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                l.setCustomNameVisible(false);
                            }
                        }, 100L);
                    }
                }
            }
        }
    }
}