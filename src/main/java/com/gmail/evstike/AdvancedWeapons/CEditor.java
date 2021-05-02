package com.gmail.evstike.AdvancedWeapons;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

@SuppressWarnings("deprecation")
public class CEditor extends API implements CommandExecutor, Listener {
    
    Fates plugin;
    
    public CEditor(Fates instance) {
        plugin = instance;
    }
    
    List<String> max = new ArrayList<String>();
    HashMap<UUID, String> hash = new HashMap<UUID, String>();
    HashMap<UUID, String> ench = new HashMap<UUID, String>();
    
    List<String> potion = Arrays.asList("interact", "attackself", "attackother", "armorself", "armorother", "itemself", "itemother", "blockbreak");
    List<String> damage = Arrays.asList("attackself", "attackother", "armorself", "armorother", "itemself", "itemother");
    List<String> explosion = Arrays.asList("interact", "attackself", "attackother", "armorself", "armorother", "itemself", "itemother", "blockbreak");
    List<String> fortune = Collections.singletonList("blockbreak");
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig())) {
            if (cmd.getName().equalsIgnoreCase("ceditor")) {
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
        Inventory i = Bukkit.createInventory(null, 9, "CE Editor");
        ItemStack c = new ItemStack(Material.PAPER);
        ItemMeta cMeta = c.getItemMeta();
        cMeta.setDisplayName("§bCreate New Enchantment");
        c.setItemMeta(cMeta);
    
        ItemStack e = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta eMeta = e.getItemMeta();
        eMeta.setDisplayName("§bEdit Enchantment");
        e.setItemMeta(eMeta);
    
        ItemStack d = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta dMeta = d.getItemMeta();
        dMeta.setDisplayName("§bDelete Enchantment");
        d.setItemMeta(dMeta);
    
        i.setItem(2, c);
        i.setItem(4, e);
        i.setItem(6, d);
        int s = 0;
        return i;
    }
    //ALL
    private void openEditGUI(Player player) {
        int sl=0;
        int sn=0;
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
        for (String key : section.getKeys(false)) {
            section.get(key);
            ConfigurationSection item = section.getConfigurationSection(key);
            sl += 1;
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
        Inventory inv = Bukkit.createInventory(null, sn, "CE Editor - Edit");
        
        inv.setItem(inv.getSize()-1, back());
        
        for (String key : section.getKeys(false)) {
            section.get(key);
            ConfigurationSection item = section.getConfigurationSection(key);
            List<String> Lore = new ArrayList<String>();
            ItemStack ce = new ItemStack(Material.BOOK);
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
    private void openDeleteGUI(Player player) {
        int sl=0;
        int sn=0;
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
        for (String key : section.getKeys(false)) {
            section.get(key);
            ConfigurationSection item = section.getConfigurationSection(key);
            sl += 1;
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
        Inventory inv = Bukkit.createInventory(null, sn, "CE Editor - Delete");
        
        inv.setItem(inv.getSize()-1, back());
        
        for (String key : section.getKeys(false)) {
            section.get(key);
            ConfigurationSection item = section.getConfigurationSection(key);
            List<String> Lore = new ArrayList<String>();
            ItemStack ce = new ItemStack(Material.BOOK);
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
    //CREATE
    private void openCreateGUI(Player player, String s) {
        Inventory i = Bukkit.createInventory(null, 18, "CE Editor - Create - "+s);
        i.setItem(i.getSize()-1, back());
    
        List<String> Lore = new ArrayList<>();
        ItemStack c = new ItemStack(Material.BOOKSHELF);
        ItemMeta cMeta = c.getItemMeta();
    
        cMeta.setDisplayName("§aDescription");
        Lore.add("§7Description of the enchantment book");
        Lore.add("§aCurrent value:");
        if(!plugin.getConfig().getStringList("enchant."+enchPath(ench.get(player.getUniqueId()))+".lore").isEmpty()) {
            List<String> e = plugin.getConfig().getStringList("enchant." + enchPath(s) + ".lore");
            for (String es : e) {
                Lore.add(ChatColor.translateAlternateColorCodes('&', es));
            }
        } else {
            Lore.add("§7null");
        }
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
    
        c.setType(Material.GUNPOWDER);
        cMeta.setDisplayName("§aCost");
        Lore.add("§7Cost of the enchantment in Dust");
        Lore.add("§aCurrent value: §7"+plugin.getConfig().getInt("enchant."+enchPath(s)+".cost"));
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
    
        c.setType(Material.RABBIT_FOOT);
        cMeta.setDisplayName("§aChance");
        Lore.add("§7Chance of the enchantment activating");
        Lore.add("§aCurrent value: §7"+plugin.getConfig().getInt("enchant."+enchPath(s)+".chance")+"%");
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
        
        c.setType(Material.DIAMOND_SWORD);
        cMeta.setDisplayName("§aType");
        Lore.add("§7Type of item that can be enchanted");
        Lore.add("§aCurrent value: §7"+plugin.getConfig().getString("enchant."+enchPath(s)+".type"));
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
    
        c.setType(Material.LEVER);
        cMeta.setDisplayName("§aEvents");
        Lore.add("§7Events that activate the enchantment");
        Lore.add("§aCurrent value:");
        if(!plugin.getConfig().getStringList("enchant."+enchPath(ench.get(player.getUniqueId()))+".events").isEmpty()) {
            List<String> e = plugin.getConfig().getStringList("enchant." + enchPath(s) + ".events");
            for (String es : e) {
                Lore.add("§7- " + es);
            }
        } else {
            Lore.add("§7- null");
        }
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
    
        c.setType(Material.REDSTONE);
        cMeta.setDisplayName("§aFunction");
        Lore.add("§7What the enchantment does when activated");
        Lore.add("§aCurrent value: §7"+plugin.getConfig().getString("enchant."+enchPath(s)+".function"));
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
    
        c.setType(Material.ENDER_EYE);
        cMeta.setDisplayName("§aDimensions");
        Lore.add("§7Dimensions that the enchantment can activate in");
        Lore.add("§aCurrent value:");
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("dimension")) {
            List<String> d = plugin.getConfig().getStringList("enchant." + enchPath(s) + ".dimension");
            for (String ds : d) {
                Lore.add("§7- " + ds);
            }
        } else {
            Lore.add("§7- null (ALL)");
        }
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
    
        c.setType(Material.ZOMBIE_HEAD);
        cMeta.setDisplayName("§aEntities");
        Lore.add("§7Entities that are affected by the enchantment");
        if (!containsEvent((ench.get(player.getUniqueId())), damage)) {
            c.setType(Material.RED_STAINED_GLASS_PANE);
            Lore.add("§cNot compatible with the selected events");
        }
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
    
        c.setType(Material.GRASS_BLOCK);
        cMeta.setDisplayName("§aBlocks");
        Lore.add("§7Blocks that activate the enchantment");
        if (!containsEvent((ench.get(player.getUniqueId())), fortune)) {
            c.setType(Material.RED_STAINED_GLASS_PANE);
            Lore.add("§cNot compatible with the selected events");
        }
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("blocks")) {
            List<String> d = plugin.getConfig().getStringList("enchant." + enchPath(s) + ".blocks");
            for (String ds : d) {
                Lore.add("§7- " + ds);
            }
        } else {
            Lore.add("§7- null (ALL)");
        }
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
    
        c.setType(Material.DIAMOND_CHESTPLATE);
        Lore.add("§7Health that the enchantment activates at");
        cMeta.setDisplayName("§aThreshold");
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
    
        c.setType(Material.OAK_SIGN);
        Lore.add("§7Message displayed when the enchantment activates");
        cMeta.setDisplayName("§aMessage");
        cMeta.setLore(Lore);
        c.setItemMeta(cMeta);
        Lore.clear();
        cMeta.setLore(null);
        i.addItem(c);
        
        player.openInventory(i);
        
    }
    //TYPE
    private void openTypeGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Type");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<>();
        ItemStack ce = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§eWeapon");
        Lore.add("§8Sword, Axe");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("type")) {
            if (plugin.getConfig().getString("enchant." + enchPath(ench.get(player.getUniqueId())) + ".type").equals("weapon")) {
                ce.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            }
        }
        t.addItem(ce);
        ce.setType(Material.DIAMOND_PICKAXE);
        ceM.setDisplayName("§eTool");
        Lore.add("§8Pickaxe, Axe, Shovel, Hoe, Shears");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("type")) {
            if (plugin.getConfig().getString("enchant." + enchPath(ench.get(player.getUniqueId())) + ".type").equals("tool")) {
                ce.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            }
        }
        t.addItem(ce);
        ce.setType(Material.DIAMOND_CHESTPLATE);
        ceM.setDisplayName("§eArmor");
        Lore.add("§8Helmet, Chestplate, Leggings, Boots");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
            if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("type")) {
                if (plugin.getConfig().getString("enchant." + enchPath(ench.get(player.getUniqueId())) + ".type").equals("armor")) {
                    ce.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                }
            }
        t.addItem(ce);
        
        player.openInventory(t);
    }
    //DIMENSIONS
    private void openDimensionGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Dimensions");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<String>();
        ItemStack ce = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§eNormal");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("dimension")) {
            if (plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".dimension").contains("NORMAL")) {
                ce.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            }
        }
        t.addItem(ce);
        ce.setType(Material.NETHERRACK);
        ceM.setDisplayName("§eNether");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("dimension")) {
            if (plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".dimension").contains("NETHER")) {
                ce.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            }
        }
        t.addItem(ce);
        ce.setType(Material.END_STONE);
        ceM.setDisplayName("§eThe End");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("dimension")) {
            if (plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".dimension").contains("THE_END")) {
                ce.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            }
        }
        t.addItem(ce);
        
        player.openInventory(t);
    }
    //THRESHOLD
    private void openThresholdGUI(Player player, String s) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Threshold");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<String>();
        ItemStack ce = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§eThresholdSelf");
        Lore.add("§7Health that the user must be at");
        Lore.add("§aCurrent value: §8<= §7"+plugin.getConfig().getInt("enchant."+enchPath(s)+".thresholdself")+"%");
        Lore.add("§aTarget: §7User");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        ce.setType(Material.CREEPER_HEAD);
        ceM.setDisplayName("§eThresholdOther");
        Lore.add("§7Health that the other entity must be at");
        Lore.add("§aCurrent value: §8<= §7"+plugin.getConfig().getInt("enchant."+enchPath(s)+".thresholdsother")+"%");
        Lore.add("§aTarget: §7Other Entity");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        player.openInventory(t);
    }
    //MOBS
    private void openMobsGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Mobs");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<String>();
        ItemStack ce = new ItemStack(Material.BONE);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§eAffected");
        Lore.add("§7Entities that are affected");
        Lore.add("§aCurrent value:");
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("affected")) {
            List<String> e = plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".affected");
            for (String es : e) {
                Lore.add("§7- " + es);
            }
        } else {
            Lore.add("§7- null");
        }
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        ce.setType(Material.DIAMOND_HORSE_ARMOR);
        ceM.setDisplayName("§eImmune");
        Lore.add("§7Entities that are immune");
        Lore.add("§aCurrent value:");
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("immune")) {
            List<String> e = plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".immune");
            for (String es : e) {
                Lore.add("§7- " + es);
            }
        } else {
            Lore.add("§7- null");
        }
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        player.openInventory(t);
    }
    private void openMessageGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Message");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<String>();
        ItemStack ce = new ItemStack(Material.NAME_TAG);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§eChatMsg");
        Lore.add("§7Message that displays");
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("chatmsg")) {
            Lore.add("§aCurrent value: §f" + plugin.getConfig().getString("enchant." + enchPath(ench.get(player.getUniqueId())) + ".chatmsg").replace('&', '§'));
        } else {
            Lore.add("§aCurrent value: §7null");
        }
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
    
        ceM.setDisplayName("§eMsg");
        if (plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + ".msg")) {
            ce.setType(Material.LIME_STAINED_GLASS_PANE);
            Lore.add("§aCurrent value: §a" + plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + ".msg"));
        } else {
            ce.setType(Material.RED_STAINED_GLASS_PANE);
            Lore.add("§aCurrent value: §c" + plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + ".msg"));
        }
        Lore.add("§7Does the message display?");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        player.openInventory(t);
    }
    //POTION
    private void openPotionGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Potion");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<String>();
        ItemStack ce = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§eEffects");
        Lore.add("§7Potions effects that are given");
        Lore.add("§aCurrent value:");
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("effects")) {
            List<String> e = plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".effects");
            for (String es : e) {
                Lore.add("§7- " + es);
            }
        } else {
            Lore.add("§7- null");
        }
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        ce.setType(Material.YELLOW_STAINED_GLASS_PANE);
        ceM.setDisplayName("§eDuration");
        Lore.add("§7Duration of the potion effect");
        Lore.add("§aCurrent value: §7" + plugin.getConfig().getInt("enchant." + enchPath(ench.get(player.getUniqueId())) + ".duration")+"s");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
    
        ceM.setDisplayName("§eAmplifier");
        Lore.add("§7Level of the potion effect");
        Lore.add("§aCurrent value: §7" + plugin.getConfig().getInt("enchant." + enchPath(ench.get(player.getUniqueId())) + ".amplifier"));
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        player.openInventory(t);
    }
    //EXPLOSION
    private void openExplosionGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Explosion");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<>();
        ItemStack ce = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§ePower");
        Lore.add("§7Power of the explosion");
        Lore.add("§aCurrent value: §7" + plugin.getConfig().getInt("enchant." + enchPath(ench.get(player.getUniqueId())) + ".power"));
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        ceM.setDisplayName("§eDestroy");
        if (plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + ".destroy")) {
            ce.setType(Material.LIME_STAINED_GLASS_PANE);
            Lore.add("§aCurrent value: §a" + plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + ".destroy"));
        } else {
            ce.setType(Material.RED_STAINED_GLASS_PANE);
            Lore.add("§aCurrent value: §c" + plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + ".destroy"));
        }
        Lore.add("§7Does the explosion destroy blocks?");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        player.openInventory(t);
    }
    //DAMAGE
    private void openDamageGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Damage");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<String>();
        ItemStack ce = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§eEffects");
        Lore.add("§7Amount of damage dealt");
        Lore.add("§aCurrent value:");
        if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("effects")) {
            List<String> e = plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".effects");
            for (String es : e) {
                Lore.add("§7- " + es);
            }
        } else {
            Lore.add("§7- null");
        }
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        player.openInventory(t);
    }
    //FORTUNE
    private void openFortuneGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Fortune");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<String>();
        ItemStack ce = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§eAmount");
        Lore.add("§7Amount of additional blocks dropped");
        Lore.add("§aCurrent value: §7" + plugin.getConfig().getInt("enchant." + enchPath(ench.get(player.getUniqueId())) + ".amount"));
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        ceM.setDisplayName("§eDropAtPlayer");
        if (plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + ".dropatplayer")) {
            ce.setType(Material.LIME_STAINED_GLASS_PANE);
            Lore.add("§aCurrent value: §a" + plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + ".dropatplayer"));
        } else {
            ce.setType(Material.RED_STAINED_GLASS_PANE);
            Lore.add("§aCurrent value: §c" + plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + ".dropatplayer"));
        }
        Lore.add("§7Do the blocks drop at the player or at the block?");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        player.openInventory(t);
    }
    //EVENTS
    private void openEventGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Events");
        
        t.setItem(t.getSize() - 1, back());
        
        List<String> Lore = new ArrayList<String>();
        ItemStack ce = new ItemStack(Material.OAK_BUTTON);
        ItemMeta ceM = ce.getItemMeta();
        
        ceM.setDisplayName("§eInteract");
        Lore.add("§7Activates when the user clicks");
        Lore.add("§8Held item must contain enchantment");
        Lore.add("§aTarget: §7User");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        ce.setType(Material.DIAMOND_SWORD);
        ceM.setDisplayName("§eAttackOther");
        Lore.add("§7Activates when another entity is hit");
        Lore.add("§8Held item must contain enchantment");
        Lore.add("§aTarget: §7Hit Entity");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
    
        ce.setType(Material.WOODEN_SWORD);
        ceM.setDisplayName("§eAttackSelf");
        Lore.add("§7Activates when another entity is hit");
        Lore.add("§8Held item must contain enchantment");
        Lore.add("§aTarget: §7User");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
    
        ce.setType(Material.DIAMOND_CHESTPLATE);
        ceM.setDisplayName("§eArmorOther");
        Lore.add("§7Activates when the user is hit");
        Lore.add("§8Armor must contain enchantment");
        Lore.add("§aTarget: §7Attacker");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
    
        ce.setType(Material.LEATHER_CHESTPLATE);
        ceM.setDisplayName("§eArmorSelf");
        Lore.add("§7Activates when the user is hit");
        Lore.add("§8Armor must contain enchantment");
        Lore.add("§aTarget: §7User");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
    
        ce.setType(Material.DIAMOND);
        ceM.setDisplayName("§eItemOther");
        Lore.add("§7Activates when the user is hit");
        Lore.add("§8Held item must contain enchantment");
        Lore.add("§aTarget: §7Attacker");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
    
        ce.setType(Material.COAL);
        ceM.setDisplayName("§eItemSelf");
        Lore.add("§7Activates when the user is hit");
        Lore.add("§8Held item must contain enchantment");
        Lore.add("§aTarget: §7User");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
    
        ce.setType(Material.DIAMOND_PICKAXE);
        ceM.setDisplayName("§eBlockBreak");
        Lore.add("§7Activates when the user breaks a block");
        Lore.add("§8Held item must contain enchantment");
        Lore.add("§aTarget: §7User");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        for(ItemStack i : t.getContents()) {
            if(i!=null) {
                if(plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("events")) {
                    String es = ChatColor.stripColor(i.getItemMeta().getDisplayName().toLowerCase());
                    if (plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".events").contains(es)) {
                        i.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                }
            }
        }
        
        player.openInventory(t);
    }
    //FUNCTION
    private void openFunctionGUI(Player player) {
        Inventory t = Bukkit.createInventory(null, 9, "CE Editor - Function");
        
        t.setItem(t.getSize() - 1, back());
    
        List<String> Lore = new ArrayList<String>();
        ItemStack ce = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta ceM = ce.getItemMeta();
        ItemStack pot = new ItemStack(Material.NETHER_WART);
        ItemMeta potM = pot.getItemMeta();
        
        potM.setDisplayName("§ePotion");
        Lore.add("§7Gives a potion effect");
        if(!containsEvent((ench.get(player.getUniqueId())), potion)) {
            pot.setType(Material.RED_STAINED_GLASS_PANE);
            Lore.add("§cNot compatible with the selected events");
        } else {
            pot.setType(Material.POTION);
        }
        potM.setLore(Lore);
        pot.setItemMeta(potM);
        Lore.clear();
        t.addItem(pot);
        
        ceM.setDisplayName("§eDamage");
        Lore.add("§7Deals damage");
        if(!containsEvent((ench.get(player.getUniqueId())), damage)) {
            ce.setType(Material.RED_STAINED_GLASS_PANE);
            Lore.add("§cNot compatible with the selected events");
        }
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        ce.setType(Material.TNT);
        ceM.setDisplayName("§eExplosion");
        Lore.add("§7Creates an explosion");
        if(!containsEvent((ench.get(player.getUniqueId())), explosion)) {
            ce.setType(Material.RED_STAINED_GLASS_PANE);
            Lore.add("§cNot compatible with the selected events");
        }
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        ce.setType(Material.DIAMOND_PICKAXE);
        ceM.setDisplayName("§eFortune");
        Lore.add("§7Drops more blocks");
        if(!containsEvent((ench.get(player.getUniqueId())), fortune)) {
            ce.setType(Material.RED_STAINED_GLASS_PANE);
            Lore.add("§cNot compatible with the selected events");
        }
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        Lore.clear();
        t.addItem(ce);
        
        for(ItemStack i : t.getContents()) {
            if(i!=null) {
                if (plugin.getConfig().getConfigurationSection("enchant."+enchPath(ench.get(player.getUniqueId()))).contains("function")) {
                    String es = ChatColor.stripColor(i.getItemMeta().getDisplayName().toLowerCase());
                    if (plugin.getConfig().getString("enchant." + enchPath(ench.get(player.getUniqueId())) + ".function").equals(es)) {
                        i.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    }
                }
            }
        }
        player.openInventory(t);
    }
    //LORE
    private void openLoreGUI(Player player) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
        for (String key : section.getKeys(false)) {
            section.get(key);
            ConfigurationSection item = section.getConfigurationSection(key);
        }
        Inventory n = Bukkit.createInventory(null, 9, "CE Editor - Lore");
    
        n.setItem(n.getSize() - 1, back());
    
        int amount = 0;
        if (plugin.getConfig().getConfigurationSection("enchant." + enchPath(ench.get(player.getUniqueId()))).contains("lore")) {
            List<String> l = plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".lore");
            for (String s : l) {
                ItemStack ce = new ItemStack(Material.PAPER);
                ItemMeta ceM = ce.getItemMeta();
                List<String> Lore = new ArrayList<>();
                ceM.setDisplayName("§b" + amount);
                Lore.add(s.replace('&', '§'));
                Lore.add("");
                Lore.add("§6RIGHT-CLICK to delete line");
                ceM.setLore(Lore);
                ce.setItemMeta(ceM);
                n.addItem(ce);
                amount += 1;
            }
        }
        
        List<String> Lore = new ArrayList<>();
        ItemStack ce = new ItemStack(Material.MAP);
        ItemMeta ceM = ce.getItemMeta();
        ceM.setDisplayName("§aAdd Line");
        Lore.add("§7Add a line of lore");
        ceM.setLore(Lore);
        ce.setItemMeta(ceM);
        n.addItem(ce);
    
        player.openInventory(n);
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getView().getTitle())
                .contains("CE Editor"))
            return;
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory().getHolder() == null) {
                event.setCancelled(true);
                if (event.getRawSlot() < event.getView().getTopInventory().getSize()) {
    
    
                    if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
        
                        return;
                    }
    
                    switch (event.getCurrentItem().getType()) {
                        case WRITABLE_BOOK:
                            player.sendMessage("hi");
                            openEditGUI(player);
                            break;
                        case BLAZE_POWDER:
                            openDeleteGUI(player);
                            break;
                        case ARROW:
                            List<String> home = Arrays.asList("- Delete", "- Edit", "- Create");
                            List<String> function = Arrays.asList("- Potion", "- Damage", "- Explosion", "- Fortune");
                            if (listHasInvName(home, event.getView().getTitle())) {
                                player.openInventory(i());
                            } else if (listHasInvName(function, event.getView().getTitle())) {
                                openFunctionGUI(player);
                            } else {
                                openCreateGUI(player, ench.get(player.getUniqueId()));
                            }
                            break;
                        case BOOK:
                            if (ChatColor.stripColor(event.getView().getTitle()).contains("- Edit")) {
                                String s = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                                ench.put(player.getUniqueId(), s);
                                openCreateGUI(player, s);
                            }
                            if (ChatColor.stripColor(event.getView().getTitle()).contains("- Delete")) {
                                String s = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                                hash.put(player.getUniqueId(), "delete");
                                ench.put(player.getUniqueId(), s);
                                player.sendMessage("§aAre you sure you want to delete §e" + s + "§a?");
                                player.sendMessage("§aPlease enter the name of the enchantment");
                                player.closeInventory();
                            }
                            break;
                        case PAPER:
                            if (!event.getView().getTitle().contains("Lore")) {
                                hash.put(player.getUniqueId(), "name");
                                player.sendMessage("§aEnter the name with the tier (I, II, III, etc.)");
                                player.closeInventory();
                            }
                            if (event.getView().getTitle().contains("Lore")) {
                                if(event.getClick() == ClickType.RIGHT) {
                                    List<String> lore = plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".lore");
                                    hash.remove(player.getUniqueId());
                                    player.sendMessage("§aYou removed line: §5§o" +
                                            lore.get(Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()))).replace('&', '§'));
                                    lore.remove(Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())));
                                    plugin.getConfig().set("enchant." + enchPath(ench.get(player.getUniqueId())) + ".lore", lore);
                                    updateConfig();
                                    openLoreInv(player);
                                }
                            }
                            break;
                        case BOOKSHELF:
                            openLoreGUI(player);
                            break;
                        case GUNPOWDER:
                            hash.put(player.getUniqueId(), "cost");
                            player.sendMessage("§aEnter an integer for the cost");
                            player.closeInventory();
                            break;
                        case RABBIT_FOOT:
                            hash.put(player.getUniqueId(), "chance");
                            player.sendMessage("§aEnter an integer for the % chance");
                            player.closeInventory();
                            break;
                        case DIAMOND_SWORD:
                            if (event.getView().getTitle().contains("Function")) {
                                openDamageGUI(player);
                            } else if (!event.getView().getTitle().contains("Type") && !event.getView().getTitle().contains("Events")) {
                                openTypeGUI(player);
                            }
                            break;
                        case LEVER:
                            openEventGUI(player);
                            break;
                        case DIAMOND_PICKAXE:
                            if (event.getView().getTitle().contains("Function")) {
                                openFortuneGUI(player);
                            }
                            break;
                        case ENDER_EYE:
                            openDimensionGUI(player);
                            break;
                        case DIAMOND_CHESTPLATE:
                            if (event.getView().getTitle().contains("- Create")) {
                                openThresholdGUI(player, ench.get(player.getUniqueId()));
                            }
                            break;
                        case PLAYER_HEAD:
                            hash.put(player.getUniqueId(), "thresholdself");
                            player.sendMessage("§aEnter an integer for the % threshold");
                            player.closeInventory();
                            break;
                        case CREEPER_HEAD:
                            hash.put(player.getUniqueId(), "thresholdother");
                            player.sendMessage("§aEnter an integer for the % threshold");
                            player.closeInventory();
                            break;
                        case ZOMBIE_HEAD:
                            openMobsGUI(player);
                            break;
                        case BONE:
                            hash.put(player.getUniqueId(), "affected");
                            player.sendMessage("§aEnter a list of entities, separated by ','");
                            //player.sendMessage("§bCheck out the item description in the menu for placeholders");
                            player.sendMessage("§bUse §d'poison' §bfor all entities immune to poison");
                            player.sendMessage("§bUse §d'undead' §bfor all undead entities");
                            player.closeInventory();
                            break;
                        case DIAMOND_HORSE_ARMOR:
                            hash.put(player.getUniqueId(), "immune");
                            player.sendMessage("§aEnter a list of entities, separated by ','");
                            //player.sendMessage("§bCheck out the item description in the menu for placeholders");
                            player.sendMessage("§bUse §d'poison' §bfor all entities immune to poison");
                            player.sendMessage("§bUse §d'undead' §bfor all undead entities");
                            player.closeInventory();
                            break;
                        case REDSTONE:
                            openFunctionGUI(player);
                            break;
                        case POTION:
                            openPotionGUI(player);
                            break;
                        case TNT:
                            openExplosionGUI(player);
                            break;
                        case GRASS_BLOCK:
                            if (!event.getView().getTitle().contains("- Dimension")) {
                                hash.put(player.getUniqueId(), "blocks");
                                player.sendMessage("§aEnter a list of blocks, separated by ','");
                                player.closeInventory();
                            }
                            break;
                        case NAME_TAG:
                            hash.put(player.getUniqueId(), "message");
                            player.sendMessage("§aEnter the message (use '&' for color codes)");
                            player.closeInventory();
                            break;
                        case OAK_SIGN:
                            openMessageGUI(player);
                            break;
                        case MAP:
                            hash.put(player.getUniqueId(), "lore");
                            player.sendMessage("§aEnter the line (use '&' for color codes)");
                            player.closeInventory();
                            break;
                    }
                    ItemStack ci = event.getCurrentItem();
                    ItemMeta ciM = ci.getItemMeta();
    
                    if (event.getView().getTitle().contains("Type")) {
                        for (ItemStack i : event.getView().getTopInventory().getContents()) {
                            if (i != null) {
                                if (i.getItemMeta().hasEnchants()) {
                                    for (Enchantment e : i.getEnchantments().keySet()) {
                                        i.removeEnchantment(e);
                                    }
                                }
                            }
                        }
                        if (event.getCurrentItem().getType() != Material.ARROW) {
                            event.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                            plugin.getConfig().set("enchant." + enchPath(ench.get(player.getUniqueId())) + ".type",
                                    ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase()));
                        }
                    }
                    if (event.getView().getTitle().contains("Function")) {
                        if (event.getCurrentItem().getType() != Material.ARROW && event.getCurrentItem().getType() != Material.RED_STAINED_GLASS_PANE) {
                            for (ItemStack i : event.getView().getTopInventory().getContents()) {
                                if (i != null) {
                                    if (i.getItemMeta().hasEnchants()) {
                                        for (Enchantment e : i.getEnchantments().keySet()) {
                                            i.removeEnchantment(e);
                                        }
                                    }
                                }
                            }
                            event.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                            plugin.getConfig().set("enchant." + enchPath(ench.get(player.getUniqueId())) + ".function",
                                    ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase()));
                        }
                    }
                    if (event.getView().getTitle().contains("Dimensions")) {
                        List<String> d = plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".dimension");
                        if (event.getCurrentItem().containsEnchantment(Enchantment.DURABILITY)) {
                            event.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                            d.remove(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toUpperCase().replace(' ', '_'));
                        } else {
                            if (event.getCurrentItem().getType() != Material.ARROW) {
                                event.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                                d.add(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toUpperCase().replace(' ', '_'));
                            }
                        }
                        plugin.getConfig().set("enchant." + enchPath(ench.get(player.getUniqueId())) + ".dimension", d);
                        if (d.isEmpty()) {
                            plugin.getConfig().set("enchant." + enchPath(ench.get(player.getUniqueId())) + ".dimension", null);
                        }
                        updateConfig();
                        d.clear();
                    }
                    if (event.getView().getTitle().contains("Events")) {
                        List<String> e = plugin.getConfig().getStringList("enchant." + enchPath(ench.get(player.getUniqueId())) + ".events");
                        if (event.getCurrentItem().getType() != Material.ARROW) {
                            if (event.getCurrentItem().containsEnchantment(Enchantment.DURABILITY)) {
                                event.getCurrentItem().removeEnchantment(Enchantment.DURABILITY);
                                e.remove(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase());
                            } else {
                                event.getCurrentItem().addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                                if (!e.contains(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase())) {
                                    e.add(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).toLowerCase());
                                }
                            }
                            plugin.getConfig().set("enchant." + enchPath(ench.get(player.getUniqueId())) + ".events", e);
                            updateConfig();
                            e.clear();
                        }
                    }
                    if (event.getView().getTitle().contains("Potion")) {
                        if (ChatColor.stripColor(ci.getItemMeta().getDisplayName()).contains("Effects")) {
                            hash.put(player.getUniqueId(), "potion");
                            player.sendMessage("§aEnter a list of effects, separated by ','");
                            player.closeInventory();
                        }
                        if (ChatColor.stripColor(ci.getItemMeta().getDisplayName()).contains("Duration")) {
                            hash.put(player.getUniqueId(), "duration");
                            player.sendMessage("§aEnter an integer for the duration");
                            player.closeInventory();
                        }
                        if (ChatColor.stripColor(ci.getItemMeta().getDisplayName()).contains("Amplifier")) {
                            hash.put(player.getUniqueId(), "amplifier");
                            player.sendMessage("§aEnter an integer for the amplifier (starts at 0)");
                            player.closeInventory();
                        }
                    }
                    if (event.getView().getTitle().contains("Damage")) {
                        if (ChatColor.stripColor(ci.getItemMeta().getDisplayName()).contains("Effects")) {
                            hash.put(player.getUniqueId(), "damage");
                            player.sendMessage("§aEnter a list of doubles for the damage, separated by ','");
                            player.closeInventory();
                        }
                    }
                    if (event.getView().getTitle().contains("Explosion")) {
                        if (ChatColor.stripColor(ci.getItemMeta().getDisplayName()).contains("Power")) {
                            hash.put(player.getUniqueId(), "power");
                            player.sendMessage("§aEnter an integer for the explosion power (starts at 0)");
                            player.closeInventory();
                        }
                    }
                    if (event.getView().getTitle().contains("Fortune")) {
                        if (ChatColor.stripColor(ci.getItemMeta().getDisplayName()).contains("Amount")) {
                            hash.put(player.getUniqueId(), "amount");
                            player.sendMessage("§aEnter an integer for the amount");
                            player.closeInventory();
                        }
                    }
                    if (!event.getView().getTitle().contains("Function") && !event.getView().getTitle().contains("Create")) {
                        if (ci.getType() == Material.RED_STAINED_GLASS_PANE || ci.getType() == Material.LIME_STAINED_GLASS_PANE) {
                            String name = ChatColor.stripColor(ciM.getDisplayName()).toLowerCase();
                            List<String> l = ciM.getLore();
        
                            if (plugin.getConfig().getBoolean("enchant." + enchPath(ench.get(player.getUniqueId())) + "." + name)) {
                                ci.setType(Material.RED_STAINED_GLASS_PANE);
                                plugin.getConfig().set("enchant." + enchPath(ench.get(player.getUniqueId())) + "." + name, false);
                                updateConfig();
                                l.set(0, "§aCurrent value: §cfalse");
                                ciM.setLore(l);
                                ci.setItemMeta(ciM);
                            } else {
                                ci.setType(Material.LIME_STAINED_GLASS_PANE);
                                plugin.getConfig().set("enchant." + enchPath(ench.get(player.getUniqueId())) + "." + name, true);
                                updateConfig();
                                l.set(0, "§aCurrent value: §atrue");
                                ciM.setLore(l);
                                ci.setItemMeta(ciM);
                            }
                        }
                    }
                }
        }
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String s = event.getMessage();
        if (hash.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
            switch (hash.get(player.getUniqueId())) {
                case "name":
                    if (enchlevelString(s) < 1 || s.trim().length() < 4) {
                        player.sendMessage("§cYou did not enter a tier (I, II, III, etc.). Try again");
                    } else {
                        hash.remove(player.getUniqueId());
                        ench.put(player.getUniqueId(), s);
                        plugin.getConfig().set("enchant."+enchPath(s)+".name", "&e"+s);
                        updateConfig();
                        openInv(player);
                    }
                    break;
                case "message":
                    hash.remove(player.getUniqueId());
                    plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".chatmsg", s);
                    updateConfig();
                    openInv(player);
                    break;
                case "lore":
                    List<String> lore = plugin.getConfig().getStringList("enchant."+enchPath(ench.get(player.getUniqueId()))+".lore");
                    hash.remove(player.getUniqueId());
                    lore.add(s);
                    plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".lore", lore);
                    updateConfig();
                    openLoreInv(player);
                    break;
                case "cost":
                    if(isInt(s)) {
                        if (Integer.parseInt(s) >= 0) {
                            hash.remove(player.getUniqueId());
                            plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".cost", Integer.parseInt(s));
                            updateConfig();
                            openInv(player);
                        } else {
                            player.sendMessage("§cInteger must be positive. Try again");
                        }
                    } else {
                        player.sendMessage("§cThis is not an integer. Try again");
                    }
                    break;
                case "chance":
                    if(isInt(s)) {
                        if (Integer.parseInt(s) >= 0 && Integer.parseInt(s) <= 100) {
                            hash.remove(player.getUniqueId());
                            plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".chance", Integer.parseInt(s));
                            updateConfig();
                            openInv(player);
                        } else {
                            player.sendMessage("§cInteger must range from 0 to 100. Try again");
                        }
                    } else {
                        player.sendMessage("§cThis is not an integer. Try again");
                    }
                    break;
                case "thresholdself":
                    if(isInt(s)) {
                        if (Integer.parseInt(s) >= 0 && Integer.parseInt(s) <= 100) {
                            hash.remove(player.getUniqueId());
                            plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".thresholdself", Integer.parseInt(s));
                            updateConfig();
                            openInv(player);
                        } else {
                            player.sendMessage("§cInteger must range from 0 to 100. Try again");
                        }
                    } else {
                        player.sendMessage("§cThis is not an integer. Try again");
                    }
                    break;
                case "thresholdother":
                    if(isInt(s)) {
                        if (Integer.parseInt(s) >= 0 && Integer.parseInt(s) <= 100) {
                            hash.remove(player.getUniqueId());
                            plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".thresholdother", Integer.parseInt(s));
                            updateConfig();
                            openInv(player);
                        } else {
                            player.sendMessage("§cInteger must range from 0 to 100. Try again");
                        }
                    } else {
                        player.sendMessage("§cThis is not an integer. Try again");
                    }
                    break;
                case "damage":
                    List<Double> dl = new ArrayList<>(Collections.emptyList());
                    ArrayList<String> f = new ArrayList<>();
                    s = s.trim();
                    String[] sp = s.split(",");
                    for (String ps : sp) {
                        if (isDouble(ps)) {
                            double pd = Double.parseDouble(ps);
                            if(pd >= 0) {
                                dl.add(pd);
                            } else {
                                f.add(String.valueOf(pd));
                            }
                        } else {
                            f.add(ps);
                        }
                    }
                    if(!f.isEmpty()) {
                        player.sendMessage("§cCould not add: "+listToString(f).trim());
                    }
                    plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".effects", dl);
                    updateConfig();
                    hash.remove(player.getUniqueId());
                    dl.clear();
                    f.clear();
                    openFunctionInv(player);
                    break;
                case "potion":
                    List<String> pl = new ArrayList<>(Collections.emptyList());
                    ArrayList<String> f2 = new ArrayList<>();
                    s = s.trim();
                    String[] sp2 = s.split(",");
                    for (String ps : sp2) {
                        if (PotionEffectType.getByName(ps.trim())!=null) {
                            pl.add(ps.trim().toUpperCase());
                        } else {
                            f2.add(ps.trim());
                        }
                    }
                    if(!f2.isEmpty()) {
                        player.sendMessage("§cCould not add: "+listToString(f2).trim());
                    }
                    plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".effects", pl);
                    updateConfig();
                    hash.remove(player.getUniqueId());
                    pl.clear();
                    f2.clear();
                    openFunctionInv(player);
                    break;
                case "blocks":
                    List<String> bl = new ArrayList<>(Collections.emptyList());
                    ArrayList<String> f3 = new ArrayList<>();
                    s = s.trim();
                    String[] sp3 = s.split(",");
                    for (String ps : sp3) {
                        if (Material.matchMaterial(ps) != null) {
                            if (Material.getMaterial(Material.matchMaterial(ps).name()).isBlock()) {
                                bl.add(ps.trim().toUpperCase());
                            } else {
                                f3.add(ps.trim());
                            }
                        } else {
                            f3.add(ps.trim());
                        }
                    }
                    if(!f3.isEmpty()) {
                        player.sendMessage("§cCould not add: "+listToString(f3).trim());
                    }
                    plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".blocks", bl);
                    updateConfig();
                    hash.remove(player.getUniqueId());
                    bl.clear();
                    f3.clear();
                    openInv(player);
                    break;
                case "affected":
                    List<String> al = new ArrayList<>(Collections.emptyList());
                    ArrayList<String> f4 = new ArrayList<>();
                    s = s.trim();
                    String[] sp4 = s.split(",");
                    for (String ps : sp4) {
                        if (EntityType.fromName(ps)!=null) {
                            al.add(ps.trim().toUpperCase());
                        } else if (ps.equals("poison")) {
                            al.add(ps.trim());
                        } else if (ps.equals("undead")) {
                            al.add(ps.trim());
                        } else {
                            f4.add(ps.trim());
                        }
                    }
                    if(!f4.isEmpty()) {
                        player.sendMessage("§cCould not add: "+listToString(f4).trim());
                    }
                    plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".affected", al);
                    updateConfig();
                    hash.remove(player.getUniqueId());
                    al.clear();
                    f4.clear();
                    openInv(player);
                    break;
                case "immune":
                    List<String> il = new ArrayList<>(Collections.emptyList());
                    ArrayList<String> f5 = new ArrayList<>();
                    s = s.trim();
                    String[] sp5 = s.split(",");
                    for (String ps : sp5) {
                        if (EntityType.fromName(ps)!=null) {
                            il.add(ps.trim().toUpperCase());
                        } else if (ps.equals("poison")) {
                            il.add(ps.trim());
                        } else if (ps.equals("undead")) {
                            il.add(ps.trim());
                        } else {
                            f5.add(ps.trim());
                        }
                    }
                    if(!f5.isEmpty()) {
                        player.sendMessage("§cCould not add: "+listToString(f5).trim());
                    }
                    plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".immune", il);
                    updateConfig();
                    hash.remove(player.getUniqueId());
                    il.clear();
                    f5.clear();
                    openInv(player);
                    break;
                case "amount":
                    if(isInt(s)) {
                        if (Integer.parseInt(s) >= 0) {
                            hash.remove(player.getUniqueId());
                            plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".amount", Integer.parseInt(s));
                            updateConfig();
                            openFunctionInv(player);
                        } else {
                            player.sendMessage("§cInteger must be at least 0. Try again");
                        }
                    } else {
                        player.sendMessage("§cThis is not an integer. Try again");
                    }
                    break;
                case "power":
                    if(isInt(s)) {
                        if (Integer.parseInt(s) >= 0) {
                            hash.remove(player.getUniqueId());
                            plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".power", Integer.parseInt(s));
                            updateConfig();
                            openFunctionInv(player);
                        } else {
                            player.sendMessage("§cInteger must be at least 0. Try again");
                        }
                    } else {
                        player.sendMessage("§cThis is not an integer. Try again");
                    }
                    break;
                case "duration":
                    if(isInt(s)) {
                        if (Integer.parseInt(s) >= 0) {
                            hash.remove(player.getUniqueId());
                            plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".duration", Integer.parseInt(s));
                            updateConfig();
                            openFunctionInv(player);
                        } else {
                            player.sendMessage("§cInteger must be at least 0. Try again");
                        }
                    } else {
                        player.sendMessage("§cThis is not an integer. Try again");
                    }
                    break;
                case "amplifier":
                    if(isInt(s)) {
                        if (Integer.parseInt(s) >= 0) {
                            hash.remove(player.getUniqueId());
                            plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId()))+".amplifier", Integer.parseInt(s));
                            updateConfig();
                            openFunctionInv(player);
                        } else {
                            player.sendMessage("§cInteger must be at least 0. Try again");
                        }
                    } else {
                        player.sendMessage("§cThis is not an integer. Try again");
                    }
                    break;
                case "delete":
                    if(s.equals(ench.get(player.getUniqueId()))) {
                        plugin.getConfig().set("enchant."+enchPath(ench.get(player.getUniqueId())), null);
                        player.sendMessage("§aDeleted §e"+s);
                        updateConfig();
                    } else {
                        player.sendMessage("§cCancelled.");
                        openDeleteInv(player);
                    }
                    hash.remove(player.getUniqueId());
                    ench.remove(player.getUniqueId());
                    break;
            }
        }
    }
    public void updateConfig() {
        plugin.saveConfig();
        plugin.reloadConfig();
    }
    public String enchPath(String s) {
        String str = s.toLowerCase().replace(' ', '-');
        return str;
    }
    public String listToString(List<String> l) {
        String str = "";
        for(String s : l) {
            str += s+", ";
        }
        return str;
    }
    public boolean listHasInvName(List<String> l, String s) {
        for (String i : l) {
            if(s.contains(i)) {
                return true;
            }
        }
        return false;
    }
    public void openInv(Player p) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            openCreateGUI(p, ench.get(p.getUniqueId()));
        });
    }
    public void openDeleteInv(Player p) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            openDeleteGUI(p);
        });
    }
    public void openFunctionInv(Player p) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            openFunctionGUI(p);
        });
    }
    public void openLoreInv(Player p) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            openLoreGUI(p);
        });
    }
    public boolean containsEvent(String s, List<String> l) {
        for (String e : plugin.getConfig().getStringList("enchant." + enchPath(s) + ".events")) {
            if(l.contains(e)) {
                return true;
            }
        }
        return false;
    }
}