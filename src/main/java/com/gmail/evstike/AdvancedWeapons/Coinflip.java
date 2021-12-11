package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.*;

public class Coinflip extends API implements CommandExecutor, Listener {
    
    Fates plugin;
    
    public Coinflip(Fates instance) {
        plugin = instance;
    }
    
    HashMap<UUID, UUID> match = new HashMap<UUID, UUID>();
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig()) == false) {
            if (cmd.getName().equalsIgnoreCase("coinflip")) {
                
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cError: §4Only Players can use this command!");
                    return true;
                }
                if (args.length != 1) {
                    sender.sendMessage("§cError: §4Please specify a player.");
                    return false;
                }
                if (args[0] == null) {
                    sender.sendMessage("§cError: §4Player " + args[0] + " not found.");
                    return false;
                }
                
                Player challenger = (Player) sender;
                Player target = Bukkit.getServer().getPlayerExact(args[0]);
                if (target == null) {
                    challenger.sendMessage("§cError: §4Player " + args[0] + " not found.");
                    return false;
                    // }
                    // if (target==sender) {
                    // sender.sendMessage("§cError: §4You cannot coinflip with
                    // yourself.");
                    // return false;
                }
                if (match.containsKey(target.getUniqueId()) || match.containsValue(target.getUniqueId())) {
                    challenger.sendMessage("§cError: §4Player " + args[0] + " is already in a match.");
                    return true;
                }
                challenger.sendMessage("§6You have sent a coinflip request to §a" + target.getName() + ".");
                challenger.sendMessage("§6You are Heads");
                target.sendMessage("§6You have received a coinflip request from §a" + challenger.getName() + ".");
                target.sendMessage("§6You are Tails");
                match.put(target.getUniqueId(), challenger.getUniqueId());
                openGUI(target);
                return true;
            }
        }
        return false;
    }
    
    private void openGUI(Player target) {
        Inventory inv = Bukkit.createInventory(null, 9, "Coinflip");
        UUID chalID = match.get(target.getUniqueId());
        
        File filename = plugin.createFile("playerdata.yml");
        FileConfiguration nameconfig = plugin.createYamlFile(filename);
        plugin.saveYamlFile(nameconfig, filename);
        
        Player challenger = Bukkit.getPlayer(chalID);
        ItemStack yes = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta yesMeta = yes.getItemMeta();
        List<String> Lore = new ArrayList<String>();
        
        ItemStack no = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta noMeta = no.getItemMeta();
        
        ItemStack coin = new ItemStack(Material.SUNFLOWER);
        ItemMeta coinMeta = coin.getItemMeta();
        
        List<String> Lore3 = new ArrayList<String>();
        ItemStack stats = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta statsMeta = (SkullMeta) stats.getItemMeta();
        statsMeta.setDisplayName(ChatColor.GOLD + "Your Stats");
        Lore3.add("§7" + nameconfig.getInt("playerdata." + target.getUniqueId() + ".coinflip.wins") + " wins");
        if (nameconfig.getConfigurationSection("playerdata." + target.getUniqueId()) != null) {
            double wl = nameconfig.getDouble("playerdata." + target.getUniqueId() + ".coinflip.wins")
                    / nameconfig.getDouble("playerdata." + target.getUniqueId() + ".coinflip.losses");
            Lore3.add("§7W/L " + Math.round(wl*100)/100.0);
        }
        
        statsMeta.setLore(Lore3);
        statsMeta.setOwningPlayer(target);
        stats.setItemMeta(statsMeta);
        inv.setItem(0, stats);
        
        List<String> Lore4 = new ArrayList<String>();
        ItemStack statsc = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta statscMeta = (SkullMeta) statsc.getItemMeta();
        statscMeta.setDisplayName(ChatColor.GOLD + "Challenger's Stats");
        Lore4.add("§7" + nameconfig.getInt("playerdata." + challenger.getUniqueId() + ".coinflip.wins") + " wins");
        if (nameconfig.getConfigurationSection("playerdata." + challenger.getUniqueId()) != null) {
            double wl = nameconfig.getDouble("playerdata." + challenger.getUniqueId() + ".coinflip.wins")
                    / nameconfig.getDouble("playerdata." + challenger.getUniqueId() + ".coinflip.losses");
            Lore4.add("§7W/L " + Math.round(wl*100)/100.0);
        }
        
        statscMeta.setLore(Lore4);
        statscMeta.setOwningPlayer(challenger);
        statsc.setItemMeta(statscMeta);
        inv.setItem(8, statsc);
        
        yesMeta.setDisplayName(ChatColor.GREEN + "Accept Coinflip");
        Lore.add("§7Click to start a coinflip");
        Lore.add("§7match with " + challenger.getName());
        yesMeta.setLore(Lore);
        yes.setItemMeta(yesMeta);
        
        List<String> Lore2 = new ArrayList<String>();
        noMeta.setDisplayName(ChatColor.RED + "Cancel Coinflip");
        Lore.add("§7Click to cancel your coinflip");
        Lore.add("§7duel with " + challenger.getName());
        noMeta.setLore(Lore2);
        no.setItemMeta(noMeta);
        
        coinMeta.setDisplayName(ChatColor.GOLD + "Coinflip");
        coin.setItemMeta(coinMeta);
        
        inv.setItem(2, yes);
        inv.setItem(4, coin);
        inv.setItem(6, no);
    
        target.openInventory(inv);
        
    }
    
    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        if (!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Coinflip"))
            return;
        
        Player target = (Player) event.getWhoClicked();
        
        if (event.getInventory().getHolder() == null) {
            event.setCancelled(true);
            
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR
                    || !event.getCurrentItem().hasItemMeta()) {
                
                return;
            }
            File filename = plugin.createFile("playerdata.yml");
            FileConfiguration nameconfig = plugin.createYamlFile(filename);
            plugin.saveYamlFile(nameconfig, filename);
    
            UUID chalID = match.get(target.getUniqueId());
            Player challenger = Bukkit.getPlayer(chalID);
            
            if (event.getRawSlot() == 2) {
                Random rand = new Random();
                int n = rand.nextInt(2) + 1;
                challenger.sendMessage(ChatColor.GREEN + target.getName() + " accepted the coinflip.");
                
                if (n == 1) {
                    challenger.sendMessage(ChatColor.GOLD + "Rolled §aHeads [You].");
                    target.sendMessage(ChatColor.GOLD + "Rolled §cHeads.");
                    challenger.sendMessage(ChatColor.GREEN + "You won the coinflip match.");
                    target.sendMessage(ChatColor.RED + "You lost the coinflip match.");
                    //WINCHAL
                    if (!nameconfig.contains("playerdata." + challenger.getUniqueId() + ".coinflip.wins")) {
                        nameconfig.createSection("playerdata." + challenger.getUniqueId() + ".coinflip.wins");
                    }
                    nameconfig.set("playerdata." + challenger.getUniqueId() + ".coinflip.wins",
                            nameconfig.getInt("playerdata." + challenger.getUniqueId() + ".coinflip.wins") + 1);
                    //LOSSPLAY
                    if (!nameconfig.contains("playerdata." + target.getUniqueId() + ".coinflip.losses")) {
                        nameconfig.createSection("playerdata." + target.getUniqueId() + ".coinflip.losses");
                    }
                    nameconfig.set("playerdata." + target.getUniqueId() + ".coinflip.losses",
                            nameconfig.getInt("playerdata." + target.getUniqueId() + ".coinflip.losses") + 1);
                    plugin.saveYamlFile(nameconfig, filename);
                    match.remove(target.getUniqueId());
                    target.closeInventory();
                }
                if (n == 2) {
                    challenger.sendMessage(ChatColor.GOLD + "Rolled §cTails.");
                    target.sendMessage(ChatColor.GOLD + "Rolled §aTails [You].");
                    challenger.sendMessage(ChatColor.RED + "You lost the coinflip match.");
                    target.sendMessage(ChatColor.GREEN + "You won the coinflip match.");
                    //WINPLAY
                    if (!nameconfig.contains("playerdata." + target.getUniqueId() + ".coinflip.wins")) {
                        nameconfig.createSection("playerdata." + target.getUniqueId() + ".coinflip.wins");
                    }
                    nameconfig.set("playerdata." + target.getUniqueId() + ".coinflip.wins",
                            nameconfig.getInt("playerdata." + target.getUniqueId() + ".coinflip.wins") + 1);
                    //LOSSCHAL
                    if (!nameconfig.contains("playerdata." + challenger.getUniqueId() + ".coinflip.losses")) {
                        nameconfig.createSection("playerdata." + challenger.getUniqueId() + ".coinflip.losses");
                    }
                    nameconfig.set("playerdata." + challenger.getUniqueId() + ".coinflip.losses",
                            nameconfig.getInt("playerdata." + challenger.getUniqueId() + ".coinflip.losses") + 1);
                    plugin.saveYamlFile(nameconfig, filename);
                    match.remove(target.getUniqueId());
                    target.closeInventory();
                }
            }
            if (event.getRawSlot() == 6) {
//                UUID chalID = match.get(target.getUniqueId());
//                Player challenger = Bukkit.getPlayer(chalID);
                target.sendMessage(ChatColor.RED + "You cancelled the coinflip.");
                challenger.sendMessage(ChatColor.RED + target.getName() + " has cancelled the coinflip.");
                match.remove(target.getUniqueId());
                target.closeInventory();
            }
        }
    }
    @EventHandler
    private void onInventoryClose(InventoryCloseEvent event) {
        Player target = (Player) event.getPlayer();
        if (match.containsKey(target.getUniqueId())) {
            UUID chalID = match.get(target.getUniqueId());
            Player challenger = Bukkit.getPlayer(chalID);
            target.sendMessage(ChatColor.RED + "You cancelled the coinflip.");
            challenger.sendMessage(ChatColor.RED + target.getName() + " has cancelled the coinflip.");
            match.remove(target.getUniqueId());
        }
        
    }
}
