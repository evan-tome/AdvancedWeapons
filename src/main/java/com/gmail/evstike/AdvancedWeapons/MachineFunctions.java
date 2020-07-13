package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Orientable;
import org.bukkit.block.data.Rotatable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

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

    //public ItemStack it;
    public ItemStack it = new ItemStack(XMaterial.COBBLESTONE_WALL.parseMaterial(), 1);

    {
        ItemMeta im = it.getItemMeta();
        im.setDisplayName(ChatColor.AQUA + "Port-a-Wall");
        it.setItemMeta(im);
    }

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
        String s;

        m = XMaterial.COBBLESTONE_WALL.parseMaterial();
        s = ChatColor.AQUA + "Port-a-Wall";

        if (event.getBlockPlaced().getType().equals(XMaterial.COBBLESTONE_WALL.parseMaterial())) {
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

                    newMachine(id, "Port-a-Wall", p, true, XMaterial.COBBLESTONE_WALL.parseMaterial());

                    bl.setType(XMaterial.AIR.parseMaterial());
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
        m = XMaterial.COBBLESTONE_WALL.parseMaterial();
        m1 = XMaterial.RED_STAINED_GLASS.parseItem().getType();
        m2 = XMaterial.FURNACE.parseMaterial();
        if (serverIs114()) {
            m2 = XMaterial.BLAST_FURNACE.parseMaterial();
        }
        s = ChatColor.AQUA + "Drill";

        if (event.getBlockPlaced().getType().equals(XMaterial.HOPPER.parseMaterial())) {
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

                    newMachine(id, "Drill", p, true, XMaterial.HOPPER.parseMaterial());

                    bl.setType(XMaterial.AIR.parseMaterial());
                    blockPlace(bl, XMaterial.HOPPER.parseMaterial(), id, p, it);
                    blockPlace(bl.getWorld().getBlockAt(l.getBlockX(), l.getBlockY() + 1, l.getBlockZ()), XMaterial.OAK_FENCE.parseMaterial(), id, p, it);
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
                        XBlock.setDirection(blocks, BlockFace.WEST);
                        XBlock.setColor(block, DyeColor.RED);
                        if (serverIs113()) {
                            setBlock(blocks, m2, BlockFace.WEST);
                        }
                        p.getWorld().playEffect(l, Effect.MOBSPAWNER_FLAMES, 0);
                    }
                    if (!verify) {
                        event.setCancelled(true);
                        p.sendMessage("§cCould not place.");
                        blockBreak(l);
                    }
                    verify = true;
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
                        str2loc(loc, key).setType(XMaterial.AIR.parseMaterial());

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
    public void onLoad(PluginEnableEvent event) {
        Mortis();
    }

    public void Mortis() {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        boolean y = false;
                        File minv = plugin.createFile("machineinv.yml");
                        FileConfiguration minvC = plugin.createYamlFile(minv);
                        File mname = plugin.createFile("machines.yml");
                        FileConfiguration mconfig = plugin.createYamlFile(mname);
                        for (String key : minvC.getKeys(false)) {
                            ConfigurationSection section = minvC.getConfigurationSection(key);
                            if (section.getString("type").equals("Drill")) {
                                if (section.getInt("fuel") >= 4) {
                                    List<String> l = section.getStringList("list");
                                    for (String s : l) {
                                        String[] words = s.split(":");
                                        String firstWord = words[0];
                                        String lastWord = s.substring(s.lastIndexOf(":") + 1);
                                        int i = Integer.parseInt(lastWord) + 1;
                                        if (i <= 64) {
                                            Random rand = new Random();
                                            int n = rand.nextInt(100) + 1;
                                            if (n <= 90) {
                                                l.set(l.indexOf(s), firstWord + ":" + i);
                                            }
                                            if (getLastWord(l.get(0)) == 64 || getLastWord(l.get(1)) == 64 ||
                                                    getLastWord(l.get(2)) == 64 || getLastWord(l.get(3)) == 64) {
                                                y = true;
                                            }
                                        }
                                    }
                                    section.set("list", l);
                                    plugin.saveYamlFile(minvC, minv);
                                    if (y) {
                                        Block block = str2loc(mconfig.getStringList(key + ".list").get(3), key);
                                        XBlock.setColor(block, DyeColor.LIME);
                                        y = false;
                                    }
                                }
                            }
                        }
                    }
                }, 0L, 180L);
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
                    str2loc(loc, key).setType(XMaterial.AIR.parseMaterial());
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
}