package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Orientable;
import org.bukkit.block.data.Rotatable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LeashHitch;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
public class MachineFunctions extends API implements Listener {

    Fates plugin;

    public MachineFunctions(Fates instance) {
        plugin = instance;
    }

    String id = "0";
    int a = 0;
    boolean verify = true;

    @SuppressWarnings({"deprecation"})
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(BlockPlaceEvent event) {
    
        File mname = plugin.createFile("machines.yml");
        FileConfiguration mconfig = plugin.createYamlFile(mname);
        plugin.saveYamlFile(mconfig, mname);
    
        Player p = event.getPlayer();
        Block bl = event.getBlockPlaced();
        Location l = bl.getLocation();
        ItemStack i = p.getInventory().getItemInHand();
        ItemMeta im = i.getItemMeta();
        Material m;
        Material m1;
        Material m2;
        Material m3;
        Material m4;
        String s;
    
        m = Material.COBBLESTONE_WALL;
        s = ChatColor.AQUA + "Port-a-Wall";
    
        if (event.getBlockPlaced().getType().equals(Material.COBBLESTONE_WALL)) {
            if (im.hasDisplayName()) {
                if (im.getDisplayName().equals(s)) {
    
                    if (mconfig.getKeys(false).isEmpty()) {
                        id = "0";
                    } else {
                        for (String key : mconfig.getKeys(false)) {
                            a = Integer.parseInt(mconfig.getConfigurationSection(key).getName());
                        }
                        id = a + 1 + "";
                    }
                    if (event.isCancelled()) {
                        return;
                    }
    
                    newMachine(id, "Port-a-Wall", p, true, Material.COBBLESTONE_WALL);
    
                    ItemStack it = new ItemStack(Material.COBBLESTONE_WALL, 1);
                    ItemMeta itm = it.getItemMeta();
                    itm.setDisplayName(ChatColor.AQUA + "Port-a-Wall");
                    it.setItemMeta(itm);
                    
                    bl.setType(Material.AIR);
                    blockCheck(bl, m, id, p, it);
                    blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ()), m, id, p, it);
                    blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ()), m, id, p, it);
    
                    if (getCardinalDirection(p).equals("N") || getCardinalDirection(p).equals("NW") ||
                            getCardinalDirection(p).equals("S") || getCardinalDirection(p).equals("SE")) {
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ() + 1), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ() + 2), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ() - 1), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ() - 2), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ() + 1), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ() + 2), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ() - 1), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ() - 2), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() + 1), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() + 2), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 1), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 2), m, id, p, it);
                    }
                    if (getCardinalDirection(p).equals("E") || getCardinalDirection(p).equals("NE") ||
                            getCardinalDirection(p).equals("W") || getCardinalDirection(p).equals("SW")) {
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() + 1, l.getBlockY() + 2, l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() + 2, l.getBlockY() + 2, l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() - 1, l.getBlockY() + 2, l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() - 2, l.getBlockY() + 2, l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() + 1, l.getBlockY() + 1, l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() + 2, l.getBlockY() + 1, l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() - 1, l.getBlockY() + 1, l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() - 2, l.getBlockY() + 1, l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() + 1, l.getBlockY(), l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() + 2, l.getBlockY(), l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() - 1, l.getBlockY(), l.getBlockZ()), m, id, p, it);
                        blockCheck(bl.getWorld().getBlockAt(l.getBlockX() - 2, l.getBlockY(), l.getBlockZ()), m, id, p, it);
                    }
                    p.getWorld().playEffect(l, Effect.MOBSPAWNER_FLAMES, 0);
                }
            }
        }
        m = Material.COBBLESTONE_WALL;
        m1 = Material.RED_STAINED_GLASS;
        m2 = Material.BLAST_FURNACE;
        
        s = ChatColor.AQUA + "AutoMiner";
    
        if (event.getBlockPlaced().getType().equals(Material.HOPPER)) {
            if (im.hasDisplayName()) {
                if (im.getDisplayName().equals(s)) {
                    if (p.getWorld().getEnvironment() == World.Environment.NORMAL) {
    
                        if (mconfig.getKeys(false).isEmpty()) {
                            id = "0";
                        } else {
                            for (String key : mconfig.getKeys(false)) {
                                a = Integer.parseInt(mconfig.getConfigurationSection(key).getName());
                            }
                            if (!mconfig.getKeys(false).isEmpty()) {
                                id = a + 1 + "";
                            }
                        }
                        if (event.isCancelled()) {
                            return;
                        }
    
                        newMachine(id, "AutoMiner", p, true, Material.HOPPER);
    
                        ItemStack it = new ItemStack(Material.COBBLESTONE_WALL, 1);
                        ItemMeta itm = it.getItemMeta();
                        itm.setDisplayName(ChatColor.AQUA + "Port-a-Wall");
                        it.setItemMeta(itm);
                        
                        bl.setType(Material.AIR);
                        blockPlace(bl, Material.HOPPER, id, p, it);
                        blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ()), Material.OAK_FENCE, id, p, it);
                        blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ()), m, id, p, it);
    
                        blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 1), m1, id, p, it);
                        blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 2), m2, id, p, it);
                        blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 3), m, id, p, it);
                        blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ() - 3), m, id, p, it);
                        blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ() - 2), m, id, p, it);
                        blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ() - 1), m, id, p, it);
                        blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ() - 2), m, id, p, it);
    
                        if (verify) {
                            Block block = bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 1);
                            Block blocks = bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 2);
                            setBlock(blocks, blocks.getType(), BlockFace.WEST);
                            block.setType(Material.RED_STAINED_GLASS);
                            setBlock(blocks, m2, BlockFace.WEST);
                            p.getWorld().playEffect(l, Effect.MOBSPAWNER_FLAMES, 0);
                        }
                        if (!verify) {
                            event.setCancelled(true);
                            p.sendMessage("§cNot enough space to place this.");
                            blockBreak(l);
                        }
                        verify = true;
                    } else {
                        p.sendMessage("§cThis Machine can only be placed in the Overworld.");
                        event.getBlockPlaced().setType(Material.AIR);
                    }
                }
            }
        }
        //GRAPPLING POST
        s = ChatColor.AQUA + "Grappling Post";
    
        if (event.getBlockPlaced().getType().equals(Material.OAK_FENCE)) {
            if (im.hasDisplayName()) {
                if (im.getDisplayName().equals(s)) {
    
                    if (mconfig.getKeys(false).isEmpty()) {
                        id = "0";
                    } else {
                        for (String key : mconfig.getKeys(false)) {
                            a = Integer.parseInt(mconfig.getConfigurationSection(key).getName());
                        }
                        if (!mconfig.getKeys(false).isEmpty()) {
                            id = a + 1 + "";
                        }
                    }
                    if (event.isCancelled()) {
                        return;
                    }
    
                    newMachine(id, "Grappling Post", p, true, Material.OAK_FENCE);
    
                    ItemStack it = new ItemStack(Material.COBBLESTONE_WALL, 1);
                    ItemMeta itm = it.getItemMeta();
                    itm.setDisplayName(ChatColor.AQUA + "Grappling Post");
                    it.setItemMeta(itm);
    
                    bl.setType(Material.AIR);
                    blockPlace(bl, Material.OAK_FENCE, id, p, it);
                    Location loc = event.getBlockPlaced().getLocation();
                    Location loc2 = event.getBlockPlaced().getLocation();
                    loc.setY(loc.getY() - 1);
                    loc2.setY(loc2.getY() + 1);
                    m = loc.getBlock().getType();
                    m2 = loc2.getBlock().getType();
                    loc.getBlock().setType(Material.AIR);
                    loc2.getBlock().setType(Material.AIR);
    
                    LeashHitch hitch = (LeashHitch) p.getWorld().spawnEntity(event.getBlockPlaced().getLocation(), EntityType.LEASH_HITCH);
                    Bukkit.getEntity(hitch.getUniqueId()).setPersistent(true);
                    Bukkit.getEntity(hitch.getUniqueId()).setInvulnerable(true);
                    loc.getBlock().setType(m);
                    loc2.getBlock().setType(m2);
    
                }
            }
        }
        //OBSIDIAN CARVER
        m = Material.GRINDSTONE;
        m1 = Material.MAGMA_BLOCK;
        m2 = Material.COBBLESTONE_SLAB;
    
        s = ChatColor.AQUA + "Obsidian Carver";
    
        if (event.getBlockPlaced().getType().equals(Material.DRAGON_HEAD)) {
            if (im.hasDisplayName()) {
                if (im.getDisplayName().equals(s)) {
    
                    if (mconfig.getKeys(false).isEmpty()) {
                        id = "0";
                    } else {
                        for (String key : mconfig.getKeys(false)) {
                            a = Integer.parseInt(mconfig.getConfigurationSection(key).getName());
                        }
                        if (!mconfig.getKeys(false).isEmpty()) {
                            id = a + 1 + "";
                        }
                    }
                    if (event.isCancelled()) {
                        return;
                    }
    
                    newMachine(id, "AutoMiner", p, true, Material.HOPPER);
    
                    ItemStack it = new ItemStack(Material.COBBLESTONE_WALL, 1);
                    ItemMeta itm = it.getItemMeta();
                    itm.setDisplayName(ChatColor.AQUA + "Port-a-Wall");
                    it.setItemMeta(itm);
    
                    bl.setType(Material.AIR);
                    blockPlace(bl, Material.DRAGON_HEAD, id, p, it);
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ()), Material.OAK_FENCE, id, p, it);
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ()), m, id, p, it);
    
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 1), m1, id, p, it);
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 2), m2, id, p, it);
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 3), m, id, p, it);
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ() - 3), m, id, p, it);
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ() - 2), m, id, p, it);
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ() - 1), m, id, p, it);
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 2, l.getBlockZ() - 2), m, id, p, it);
    
                    if (verify) {
                        Block block = bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 1);
                        Block blocks = bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY(), l.getBlockZ() - 2);
                        setBlock(blocks, blocks.getType(), BlockFace.WEST);
                        block.setType(Material.RED_STAINED_GLASS);
                        setBlock(blocks, m2, BlockFace.WEST);
                        p.getWorld().playEffect(l, Effect.MOBSPAWNER_FLAMES, 0);
                    }
                    if (!verify) {
                        event.setCancelled(true);
                        p.sendMessage("§cCould not place.");
                        blockBreak(l);
                    }
                    verify = true;
                } else {
                    p.sendMessage("§cThis Machine can only be placed in the Overworld.");
                    event.getBlockPlaced().setType(Material.AIR);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {

        File mname = plugin.createFile("machines.yml");
        FileConfiguration mconfig = plugin.createYamlFile(mname);
        File name = plugin.createFile("machineinv.yml");
        FileConfiguration config = plugin.createYamlFile(name);

        Block bl = event.getBlock();
        Location l = bl.getLocation();
        for (String key : mconfig.getKeys(false)) {
            ConfigurationSection item = mconfig.getConfigurationSection(key);
            if (item.getBoolean("breakable")) {
                List<String> list = item.getStringList("list");
                if (list.contains(c(l))) {
                    for (String loc : list) {
                        str2loc(loc, key).setType(Material.AIR);

                    }
                    mconfig.set(key, null);
                    plugin.saveYamlFile(mconfig, mname);
                    config.set(key, null);
                    plugin.saveYamlFile(config, name);
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeashInteract(PlayerInteractAtEntityEvent event) {

        Entity e = event.getRightClicked();
        if (e instanceof LeashHitch) {
            LeashHitch el = (LeashHitch) e;
            Block bl = el.getLocation().getBlock();
            Location l = bl.getLocation();

            File mname = plugin.createFile("machines.yml");
            FileConfiguration mconfig = plugin.createYamlFile(mname);
            File name = plugin.createFile("machineinv.yml");
            FileConfiguration config = plugin.createYamlFile(name);

            for (String key : mconfig.getKeys(false)) {
                ConfigurationSection item = mconfig.getConfigurationSection(key);
                if (item.getString("type").equals("Grappling Post")) {
                    List<String> list = item.getStringList("list");
                    if (list.contains(c(l))) {
                        blockBreak(l);
                    }
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeashDamage(HangingBreakEvent event) {
        
        Entity e = event.getEntity();
        if (e instanceof LeashHitch) {
            LeashHitch el = (LeashHitch) e;
            Block bl = el.getLocation().getBlock();
            Location l = bl.getLocation();
            
            File mname = plugin.createFile("machines.yml");
            FileConfiguration mconfig = plugin.createYamlFile(mname);
            File name = plugin.createFile("machineinv.yml");
            FileConfiguration config = plugin.createYamlFile(name);
            
            for (String key : mconfig.getKeys(false)) {
                ConfigurationSection item = mconfig.getConfigurationSection(key);
                if (item.getString("type").equals("Grappling Post")) {
                    List<String> list = item.getStringList("list");
                    if (list.contains(c(l))) {
                        //blockBreak(l);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLoad(PluginEnableEvent event) {
        Mortis();
    }

    public void Mortis() {
        new BukkitRunnable() {
            @Override
            public void run() {
                File minv = plugin.createFile("machineinv.yml");
                FileConfiguration minvC = plugin.createYamlFile(minv);
                File mname = plugin.createFile("machines.yml");
                FileConfiguration mconfig = plugin.createYamlFile(mname);
    
                boolean full = false; //machine is not full
                if (minvC.getKeys(false).size() > 0) {
                    for (String key : minvC.getKeys(false)) {
                        ConfigurationSection section = minvC.getConfigurationSection(key);
                        if (section.getString("type").equals("AutoMiner")) {
                
                            List<String> l = section.getStringList("list");
                            for (String s : l) {
                                int in = l.indexOf(s); //index of material
                                String[] words = s.split(":");
                                String firstWord = words[0];
                                String lastWord = s.substring(s.lastIndexOf(":") + 1);
                                int i = Integer.parseInt(lastWord) + 1;
                                if (i <= maxSize(in)) {
                                    Random rand = new Random();
                                    int n = rand.nextInt(100) + 1;
                                    if (n <= matChance(in)) {
                                        l.set(l.indexOf(s), firstWord + ":" + i);
                                    }
                                    if (getLastWord(l.get(0)) == maxSize(0) || getLastWord(l.get(1)) == maxSize(1) || //check if a stack is full
                                            getLastWord(l.get(2)) == maxSize(2) || getLastWord(l.get(3)) == maxSize(3)) {
                                        full = true; //if a slot is full
                                    }
                                }
                                section.set("list", l);
                                plugin.saveYamlFile(minvC, minv);
                                if (full) {
                                    Block block = str2loc(mconfig.getStringList(key + ".list").get(3), key);
                                    block.setType(Material.LIME_STAINED_GLASS);
                                    full = false;
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 20L, 500L);
    }
    
    public int getLastWord(String s) {
        String[] words = s.split(":");
        String lastWord = s.substring(s.lastIndexOf(":") + 1);
        int i = Integer.parseInt(lastWord);
        return i;
    }
    public void blockBreak(Location l) {

        File mname = plugin.createFile("machines.yml");
        FileConfiguration mconfig = plugin.createYamlFile(mname);

        for (String key : mconfig.getKeys(false)) {
            ConfigurationSection item = mconfig.getConfigurationSection(key);
            List<String> list = item.getStringList("list");
            if (list.contains(c(l))) {
                for (String loc : list) {
                    str2loc(loc, key).setType(Material.AIR);
                }
                mconfig.set(key, null);
                plugin.saveYamlFile(mconfig, mname);
            }
        }
    }
        public Block str2loc (String str, String id){
            File mname = plugin.createFile("machines.yml");
            FileConfiguration mconfig = plugin.createYamlFile(mname);
            String str2loc[]=str.split(",");
            String w = mconfig.getString(id + ".world");
            Block loc = Bukkit.getWorld(w).getBlockAt(
                    Integer.parseInt(str2loc[0]),Integer.parseInt(str2loc[1]),Integer.parseInt(str2loc[2]));
            return loc;
        }

    public boolean blockCheck(Block l, Material m, String id, Player p, ItemStack i) {

        if (p.getInventory().containsAtLeast(i, 1)) {
            if (!l.getType().isSolid()) {
                File mname = plugin.createFile("machines.yml");
                FileConfiguration mconfig = plugin.createYamlFile(mname);
                List<String> list = mconfig.getStringList(id + ".list");
                list.add(c(l.getLocation()));
                mconfig.set(id + ".list", list);

                plugin.saveYamlFile(mconfig, mname);
                l.setType(m);
                p.getInventory().removeItem(i);
            }
        }
        return false;
    }
    public boolean blockPlace(Block l, Material m, String id, Player p, ItemStack i) {

        if (!l.getType().isSolid()) {
            File mname = plugin.createFile("machines.yml");
            FileConfiguration mconfig = plugin.createYamlFile(mname);
            List<String> list = mconfig.getStringList(id + ".list");
            list.add(c(l.getLocation()));
            mconfig.set(id + ".list", list);

            plugin.saveYamlFile(mconfig, mname);
            l.setType(m);
        }else {
            verify = false;
        }
        return false;
    }
    public String c(Location x) {
        String c1 = (int)x.getX()+","+(int)x.getY()+","+(int)x.getZ();
        return c1;
    }
    public boolean newMachine(String id, String type, Player p, boolean breakable, Material m) {
        File mname = plugin.createFile("machines.yml");
        FileConfiguration mconfig = plugin.createYamlFile(mname);

            mconfig.createSection(id);
            mconfig.set(id + ".type", type);
            mconfig.set(id + ".icon", m.name());
            mconfig.set(id + ".owner", p.getUniqueId().toString());
            mconfig.set(id + ".breakable", breakable);
            mconfig.set(id + ".world", p.getWorld().getName());
            mconfig.createSection(id + ".list");
            plugin.saveYamlFile(mconfig, mname);

        return false;
    }
    public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
        if (0 <= rotation && rotation < 22.5) {
            return "N";
        } else if (22.5 <= rotation && rotation < 67.5) {
            return "NE";
        } else if (67.5 <= rotation && rotation < 112.5) {
            return "E";
        } else if (112.5 <= rotation && rotation < 157.5) {
            return "SE";
        } else if (157.5 <= rotation && rotation < 202.5) {
            return "S";
        } else if (202.5 <= rotation && rotation < 247.5) {
            return "SW";
        } else if (247.5 <= rotation && rotation < 292.5) {
            return "W";
        } else if (292.5 <= rotation && rotation < 337.5) {
            return "NW";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "N";
        } else {
            return null;
        }
    }
    public static void setBlock(Block block, Material material, BlockFace blockFace) {
        block.setType(material);
        BlockData blockData = block.getBlockData();
        if (blockData instanceof Directional) {
            ((Directional) blockData).setFacing(blockFace);
            block.setBlockData(blockData);
        }
        if (blockData instanceof Orientable) {
            ((Orientable) blockData).setAxis(Axis.valueOf(convertBlockFaceToAxis(blockFace)));
            block.setBlockData(blockData);
        }
        if (blockData instanceof Rotatable) {
            ((Rotatable) blockData).setRotation(blockFace);
            block.setBlockData(blockData);
        }
    }
    private static String convertBlockFaceToAxis(BlockFace face) {
        switch (face) {
            case NORTH:
            case SOUTH:
                return "Z";
            case EAST:
            case WEST:
                return "X";
            case UP:
            case DOWN:
                return "Y";
            default:
                return "X";
        }
    }
    private int matChance(int i) {

        int chance = 0;
        if (i == 0) {
            chance = 5;
        }
        if (i == 1) {
            chance = 15;
        }
        if (i == 2) {
            chance = 70;
        }
        if (i == 3) {
            chance = 85;
        }

        return chance;
    }
    private Block easyBlockLoc(Block bl, Location l, int x, int y, int z) {
        Block block = bl.getWorld().getBlockAt(l.getBlockX()+x, l.getBlockY()+y, l.getBlockZ()+z);
        return block;
    }
}