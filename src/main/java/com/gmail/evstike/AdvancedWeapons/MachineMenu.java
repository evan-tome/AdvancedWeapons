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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
public class MachineMenu extends API implements Listener {
    Fates plugin;

    public MachineMenu(Fates instance) {
        this.plugin = instance;
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onMachineOpen(PlayerInteractEvent event) {
        File mname = this.plugin.createFile("machines.yml");
        FileConfiguration mconfig = this.plugin.createYamlFile(mname);
        this.plugin.saveYamlFile(mconfig, mname);
        File name = this.plugin.createFile("machineinv.yml");
        FileConfiguration config = this.plugin.createYamlFile(name);
        this.plugin.saveYamlFile(config, name);
        Player p = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !p.isSneaking()) {
            Block bl = event.getClickedBlock();
            Location l = bl.getLocation();
            ItemStack i = p.getInventory().getItemInHand();
            for (String key : mconfig.getKeys(false)) {
                ConfigurationSection item = mconfig.getConfigurationSection(key);
                List<String> list = item.getStringList("list");
                if (list.contains(this.c(l))) {

                    if (bl.getType().equals(XMaterial.FURNACE.parseMaterial()) || bl.getType().equals(XMaterial.BLAST_FURNACE.parseMaterial())) {
                        event.setCancelled(true);
                        if (!config.isConfigurationSection(key)) {
                            config.createSection(key);
                            config.set(key + ".type", mconfig.getString(key + ".type"));
                            config.set(key + ".location", mconfig.getStringList(key + ".list").get(4));
                            config.set(key + ".fuel", 8);
                            config.createSection(key + ".list");
                            List<String> s = config.getStringList(key + ".list");
                            s.add(this.m(0).name() + ":0");
                            s.add(this.m(1).name() + ":0");
                            s.add(this.m(2).name() + ":0");
                            s.add(this.m(3).name() + ":0");
                            config.set(key + ".list", s);
                            this.plugin.saveYamlFile(config, name);
                            Block block = this.str2loc(mconfig.getStringList(key + ".list").get(3), key);
                            XBlock.setColor(block, DyeColor.YELLOW);
                        }
                        boolean open = false;
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            if (pl.getOpenInventory().getTitle().equals(key + " - Machine")) {
                                open = true;
                            }
                        }
                        if (!open) {
                            this.openGUI(p, key);
                            open = false;
                        }
                        if (open) {
                            p.sendMessage("§cThis Machine is currently in use.");
                        }
                    }
                    if (bl.getType().equals(XMaterial.HOPPER.parseMaterial())) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    public void openGUI(Player player, String s) {
        File mname = this.plugin.createFile("machines.yml");
        FileConfiguration mconfig = this.plugin.createYamlFile(mname);
        this.plugin.saveYamlFile(mconfig, mname);
        File name = this.plugin.createFile("machineinv.yml");
        FileConfiguration config = this.plugin.createYamlFile(name);
        this.plugin.saveYamlFile(config, name);
        
        Inventory inv = Bukkit.createInventory(null, 27, s + " - Machine");
        ItemStack b = new ItemStack(XMaterial.IRON_BARS.parseMaterial());
        ItemMeta bMeta = b.getItemMeta();
        ItemStack c2 = new ItemStack(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE.parseItem());
        ItemMeta c2Meta = c2.getItemMeta();
        ItemStack f = new ItemStack(XMaterial.YELLOW_STAINED_GLASS_PANE.parseItem());
        ItemMeta fMeta = f.getItemMeta();
        ItemStack nf = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseItem());
        ItemMeta nfMeta = nf.getItemMeta();

        List<String> Lore = new ArrayList();
        bMeta.setDisplayName("§7");
        bMeta.setLore(Lore);
        b.setItemMeta(bMeta);
        Lore.clear();

        c2Meta.setDisplayName("§aMined materials will appear in this slot.");
        c2Meta.setLore(Lore);
        c2.setItemMeta(c2Meta);
        Lore.clear();

        int i4 = config.getConfigurationSection(s).getInt("fuel");
        fMeta.setDisplayName("§aMachine Fuel");
        Lore.add("§7Powers up the Machine.");
        Lore.add("§b" + i4 + "/8 §7fuel remaining.");
        Lore.add("");
        fMeta.setLore(Lore);
        f.setItemMeta(fMeta);
        Lore.clear();

        nfMeta.setDisplayName("§cMachine Fuel");
        Lore.add("§7Powers up the Machine.");
        Lore.add("§c" + i4 + "/8 §7fuel remaining.");
        Lore.add("");
        Lore.add("§cFUEL CANNOT BE REFILLED");
        nfMeta.setLore(Lore);
        nf.setItemMeta(nfMeta);
        Lore.clear();


        int i2;
        for(i2 = 0; i2 < inv.getSize(); ++i2) {
            if (i2 != 0 && i2 != 1 && i2 != 2 && i2 != 9 && i2 != 10 && i2 != 11 && i2 != 13 && i2 != 14 && i2 != 15 && i2 != 16) {
                inv.setItem(i2, b);
            }
        }

        inv.setItem(10, autominer().clone());

        inv.setItem(13, c2);
        inv.setItem(14, c2);
        inv.setItem(15, c2);
        inv.setItem(16, c2);
        i2 = 13;
        ConfigurationSection section = config.getConfigurationSection(s);
        for(String l : section.getStringList("list")) {
            String[] words = l.split(":");
            String firstWord = words[0];
            String lastWord = l.substring(l.lastIndexOf(":") + 1);
            int i = Integer.parseInt(lastWord);
            if (i > 0) {
                List<String> lore = new ArrayList();
                ItemStack is = new ItemStack((XMaterial.matchXMaterial(firstWord).get()).parseMaterial(), i);
                ItemMeta isM = is.getItemMeta();
                lore.add("§aClick to collect this material.");
                lore.add("§cCollecting will deplete fuel.");
                lore.add("§7" + is.getAmount() + "/" + is.getMaxStackSize());
                isM.setLore(lore);
                is.setItemMeta(isM);
                inv.setItem(i2, is);
                if (is.getType() == XMaterial.INK_SAC.parseMaterial()) {
                    Dye dd = new Dye(is.getType());
                    dd.setColor(DyeColor.BLUE);
                    dd.getData();
                    ItemStack ll = dd.toItemStack(i);
                    ll.setItemMeta(isM);
                    inv.setItem(i2, ll);
                }

                ++i2;
            }
        }

        inv.setItem(18, nf);
        inv.setItem(19, nf);
        inv.setItem(20, nf);
        inv.setItem(11, nf);
        inv.setItem(2, nf);
        inv.setItem(1, nf);
        inv.setItem(0, nf);
        inv.setItem(9, nf);
        int fuel = config.getInt(s + ".fuel");
        if (fuel > 0) {
            inv.setItem(18, f);
        }

        if (fuel > 1) {
            inv.setItem(19, f);
        }

        if (fuel > 2) {
            inv.setItem(20, f);
        }

        if (fuel > 3) {
            inv.setItem(11, f);
        }
        if (fuel > 4) {
            inv.setItem(2, f);
        }
        if (fuel > 5) {
            inv.setItem(1, f);
        }
        if (fuel > 6) {
            inv.setItem(0, f);
        }
        if (fuel > 7) {
            inv.setItem(9, f);
        }

        player.openInventory(inv);
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        String[] t = event.getView().getTitle().split(" ");
        String s = t[0];
        if (!ChatColor.stripColor(event.getView().getTitle()).equals(s + " - AdvancedWeapons") && !ChatColor.stripColor(event.getView().getTitle()).equals(s + " - Machine"))
            return;
    
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getHolder() == null) {
            event.setCancelled(true);
    
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
        
                return;
            }
    
            if (event.getClickedInventory().getType().equals(InventoryType.CHEST)) {
                if (event.getCurrentItem() != null && event.getCurrentItem().getType() != XMaterial.AIR.parseMaterial() && event.getCurrentItem().hasItemMeta()) {
                    File name = this.plugin.createFile("machineinv.yml");
                    FileConfiguration config = this.plugin.createYamlFile(name);
                    this.plugin.saveYamlFile(config, name);
                    int i = config.getConfigurationSection(s).getInt("fuel") - 1;
            
                    List<String> Lore = new ArrayList();
                    ItemStack nf = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseItem());
                    ItemMeta nfMeta = nf.getItemMeta();
                    nfMeta.setDisplayName("§cMachine Fuel");
                    Lore.add("§7Powers up the Machine.");
                    Lore.add("§c" + i + "/8 §7fuel.");
                    Lore.add("");
                    Lore.add("§cFUEL CANNOT BE REFILLED");
                    nfMeta.setLore(Lore);
                    nf.setItemMeta(nfMeta);
                    Lore.clear();
            
                    File mname = this.plugin.createFile("machines.yml");
                    FileConfiguration mconfig = this.plugin.createYamlFile(mname);
                    this.plugin.saveYamlFile(mconfig, mname);
                    if (ChatColor.stripColor(event.getView().getTitle()).equals(s + " - Machine")) {
                        Inventory inv = event.getClickedInventory();
                
                        ItemStack c2 = new ItemStack(XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE.parseItem());
                        ItemMeta c2Meta = c2.getItemMeta();
                        c2Meta.setDisplayName("§aMined materials will appear in this slot.");
                        c2Meta.setLore(Lore);
                        c2.setItemMeta(c2Meta);
                        Lore.clear();
                
                        if ((event.getRawSlot() == 13 || event.getRawSlot() == 14 || event.getRawSlot() == 15 || event.getRawSlot() == 16) && event.getCurrentItem().getType() != XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE.parseMaterial()) {
                            ConfigurationSection section = config.getConfigurationSection(s);
                            List<String> l = section.getStringList("list");
                    
                            for (String ss : l) {
                                String[] words = ss.split(":");
                                String firstWord = words[0];
                        
                                if (firstWord.equals(event.getCurrentItem().getType().name()) ||
                                        (event.getCurrentItem().getType() == XMaterial.LAPIS_LAZULI.parseMaterial())) {
                            
                                    player.getInventory().addItem(invItem(event.getCurrentItem()));
                                    event.getClickedInventory().setItem(event.getRawSlot(), c2);
                                    Block block = this.str2loc(mconfig.getStringList(s + ".list").get(3), s);
                            
                                    int i4 = section.getInt("fuel") - 1;
                            
                                    if (i4 == 0) {
                                        inv.setItem(18, nf);
                                        machineBreak(s);
                                        config.set(s, null);
                                        plugin.saveYamlFile(config, name);
                                        player.closeInventory();
                                    }
                            
                                    if (i4 == 1) {
                                        inv.setItem(19, nf);
                                    }
                            
                                    if (i4 == 2) {
                                        inv.setItem(20, nf);
                                    }
                            
                                    if (i4 == 3) {
                                        inv.setItem(11, nf);
                                    }
                                    if (i4 == 4) {
                                        inv.setItem(2, nf);
                                    }
                                    if (i4 == 5) {
                                        inv.setItem(1, nf);
                                    }
                                    if (i4 == 6) {
                                        inv.setItem(0, nf);
                                    }
                                    if (i4 == 7) {
                                        inv.setItem(9, nf);
                                    }
                                    for (ItemStack it : event.getClickedInventory().getContents()) {
                                        if (it.hasItemMeta()) {
                                            ItemMeta itMeta = it.getItemMeta();
                                            if (itMeta.hasLore()) {
                                                List<String> lo = itMeta.getLore();
                                                if (it.getType().equals(XMaterial.YELLOW_STAINED_GLASS_PANE.parseMaterial())) {
                                                    lo.set(1, "§b" + i + "/8 §7fuel.");
                                                }
                                                if (it.getType().equals(XMaterial.RED_STAINED_GLASS_PANE.parseMaterial())) {
                                                    lo.set(1, "§b" + i + "/8 §7fuel.");
                                                }
                                                itMeta.setLore(lo);
                                                it.setItemMeta(itMeta);
                                            }
                                        }
                                    }
                                    if (inv.getItem(13).getAmount() < inv.getItem(13).getMaxStackSize()) {
                                        if (inv.getItem(14).getAmount() < inv.getItem(14).getMaxStackSize()) {
                                            if (inv.getItem(15).getAmount() < inv.getItem(15).getMaxStackSize()) {
                                                if (inv.getItem(16).getAmount() < inv.getItem(16).getMaxStackSize()) {
                                                    XBlock.setColor(block, DyeColor.YELLOW);
                                                }
                                            }
                                        }
                                    }
                            
                                    int i2 = l.indexOf(ss);
                                    int i3 = section.getInt("fuel") - 1;
                                    l.set(i2, firstWord + ":0");
                                    section.set("list", l);
                                    if (section.getInt("fuel") > 0) {
                                        section.set("fuel", i3);
                                    }
                                    this.plugin.saveYamlFile(config, name);
                                }
                            }
                        }
                    }
                }
        
            }
        }
    }

    private ItemStack invItem(ItemStack i) {
        ItemStack item = i.clone();
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList();
        im.setLore(lore);
        item.setItemMeta(im);
        item.setAmount(i.getAmount());
        return item;
    }

    private XMaterial m(int i) {
        Random rand = new Random();
        int n = rand.nextInt(100) + 1;
        XMaterial mat = XMaterial.AIR;
        if (i == 0) {
            if (n >= 0 && n < 50) {
                mat = XMaterial.DIAMOND;
            } else {
                mat = XMaterial.LAPIS_LAZULI;
            }
        }

        if (i == 1) {
            if (n >= 0 && n < 50) {
                mat = XMaterial.IRON_INGOT;
            } else {
                mat = XMaterial.GOLD_INGOT;
            }
        }

        if (i == 2) {
            if (n >= 0 && n < 50) {
                mat = XMaterial.COAL;
            } else {
                mat = XMaterial.STONE;
            }
        }

        if (i == 3) {
            if (n >= 0 && n < 50) {
                mat = XMaterial.COBBLESTONE;
            } else {
                mat = XMaterial.GRAVEL;
            }
        }

        return mat;
    }

    public String c(Location x) {
        String c1 = (int)x.getX() + "," + (int)x.getY() + "," + (int)x.getZ();
        return c1;
    }

    private Block str2loc(String str, String id) {
        File mname = this.plugin.createFile("machines.yml");
        FileConfiguration mconfig = this.plugin.createYamlFile(mname);
        String[] str2loc = str.split(",");
        String w = mconfig.getString(id + ".world");
        Block loc = Bukkit.getWorld(w).getBlockAt(Integer.parseInt(str2loc[0]), Integer.parseInt(str2loc[1]), Integer.parseInt(str2loc[2]));
        return loc;
    }

    public static void setBlock(Block block, Material material, BlockFace blockFace) {
        block.setType(material);
        BlockData blockData = block.getBlockData();
        if (blockData instanceof Directional) {
            ((Directional)blockData).setFacing(blockFace);
            block.setBlockData(blockData);
        }

        if (blockData instanceof Orientable) {
            ((Orientable)blockData).setAxis(Axis.valueOf(convertBlockFaceToAxis(blockFace)));
            block.setBlockData(blockData);
        }

        if (blockData instanceof Rotatable) {
            ((Rotatable)blockData).setRotation(blockFace);
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
    public void machineBreak(String key) {

        File mname = plugin.createFile("machines.yml");
        FileConfiguration mconfig = plugin.createYamlFile(mname);
        File name = plugin.createFile("machineinv.yml");
        FileConfiguration config = plugin.createYamlFile(name);

        ConfigurationSection item = mconfig.getConfigurationSection(key);
        List<String> list = item.getStringList("list");
        for (String loc : list) {
            str2loc(loc, key).setType(XMaterial.AIR.parseMaterial());
        }
        mconfig.set(key, null);
        plugin.saveYamlFile(mconfig, mname);
        config.set(key, null);
        plugin.saveYamlFile(config, name);
    }
}
