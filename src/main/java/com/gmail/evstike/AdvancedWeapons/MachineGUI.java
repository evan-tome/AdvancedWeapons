package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MachineGUI extends API implements CommandExecutor, Listener {

    Fates plugin;

    public MachineGUI(Fates instance) {
        plugin = instance;
    }

        public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
            if (hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig()) == false) {
            if (cmd.getName().equalsIgnoreCase("machines")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    openGUI(player);
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


        private void openGUI(Player player) {
            Inventory inv = Bukkit.createInventory(null, 9, "Machines");

            //Items
            ItemStack comp = new ItemStack(XMaterial.RED_STAINED_GLASS_PANE.parseItem());
            ItemMeta compMeta = comp.getItemMeta();
            ItemStack un = new ItemStack(XMaterial.YELLOW_STAINED_GLASS_PANE.parseItem());
            ItemMeta unMeta = un.getItemMeta();
            ItemStack wall = new ItemStack(XMaterial.COBBLESTONE_WALL.parseMaterial(), 15);
            ItemMeta wallMeta = wall.getItemMeta();
            ItemStack drill = new ItemStack(XMaterial.HOPPER.parseMaterial(), 1);
            ItemMeta drillMeta = drill.getItemMeta();

            //Item meta
            List<String> Lore0 = new ArrayList<String>();
            compMeta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD + "INCOMPATIBLE");
            Lore0.add("");
            Lore0.add("§cThis item is not compatible");
            Lore0.add("§cwith your server version.");
            Lore0.add("§aIt is recommended to update to a newer");
            Lore0.add("§aversion for full compatibility.");
            Lore0.add("");
            compMeta.setLore(Lore0);
            comp.setItemMeta(compMeta);

            //Item meta
            List<String> Lores = new ArrayList<String>();
            unMeta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD + "COMING SOON");
            Lores.add("");
            Lores.add("§cThis item has not");
            Lores.add("§cbeen released yet.");
            Lores.add("§7It will be available");
            Lores.add("§7in a future update.");
            Lores.add("");
            unMeta.setLore(Lores);
            un.setItemMeta(unMeta);

            List<String> Lore = new ArrayList<String>();
            int num;

            //Port-a-Wall
            wallMeta.setDisplayName(ChatColor.AQUA + "Port-a-Wall");
            num = plugin.getConfig().getInt(ChatColor.stripColor("machine." +
                    wallMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
            Lore.add("");
            Lore.add("§7Instantly constructs a wall of");
            Lore.add("§7Cobblestone to block the way.");
            Lore.add("");
            Lore.add("§b" + num +"x "+"§7DUST");
            wallMeta.setLore(Lore);
            wall.setItemMeta(wallMeta);
            Lore.clear();

            //AutoMiner
            drillMeta.setDisplayName(ChatColor.AQUA + "AutoMiner");
            num = plugin.getConfig().getInt(ChatColor.stripColor("machine." +
                    drillMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
            Lore.add("");
            Lore.add("§7Automatically mines materials");
            Lore.add("§7over time and collects them.");
            Lore.add("");
            Lore.add("§b" + num +"x "+"§7DUST");
            drillMeta.setLore(Lore);
            drill.setItemMeta(drillMeta);

            //Inventory set
            inv.setItem(0, wall);
            inv.setItem(1, drill);

            player.openInventory(inv);

        }


        @SuppressWarnings({ "incomplete-switch" })
        @EventHandler
        public void onInventoryClick(InventoryClickEvent event) {
            if (!ChatColor.stripColor(event.getView().getTitle())
                    .equalsIgnoreCase("Machines"))
                return;
            Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);


            if(event.getCurrentItem()==null || event.getCurrentItem().getType()== Material.AIR||!event.getCurrentItem().hasItemMeta()){

                return;
            }
            if(event.getClickedInventory().getType()== InventoryType.CHEST) {
                switch (XMaterial.matchXMaterial(event.getCurrentItem())) {
                    case COBBLESTONE_WALL:
                        try {
                            List<String> Lore = new ArrayList<String>();
                            ItemStack wall = new ItemStack(XMaterial.COBBLESTONE_WALL.parseMaterial(), 15);
                            ItemMeta wallMeta = wall.getItemMeta();
                            wallMeta.setDisplayName(ChatColor.AQUA + "Port-a-Wall");
                            wallMeta.setLore(Lore);
                            wall.setItemMeta(wallMeta);
                            int num = plugin.getConfig().getInt(ChatColor.stripColor("machine." +
                                    wallMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
                            List<String> Loret = new ArrayList<String>();
                            ItemStack glow = new ItemStack(XMaterial.GUNPOWDER.parseMaterial(), num);
                            ItemMeta glowMeta = glow.getItemMeta();
                            glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
                            Loret.add("§7This Dust has magical properties");
                            Loret.add("§7which make it a valuable currency.");
                            glowMeta.setLore(Loret);
                            glow.setItemMeta(glowMeta);

                            if (player.getInventory().containsAtLeast(glow, num)) {
                                player.getInventory().removeItem(glow);
                                player.getInventory().addItem(wall);
                            } else {
                            }
                        } catch (Exception ignored) {
                            player.closeInventory();
                        }
                    case HOPPER:
                        try {
                            List<String> Lore = new ArrayList<String>();
                            ItemStack drill = new ItemStack(XMaterial.HOPPER.parseMaterial(), 1);
                            ItemMeta drillMeta = drill.getItemMeta();
                            drillMeta.setDisplayName(ChatColor.AQUA + "AutoMiner");
                            drillMeta.setLore(Lore);
                            drill.setItemMeta(drillMeta);
                            int num = plugin.getConfig().getInt(ChatColor.stripColor("machine." +
                                    drillMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
                            List<String> Loret = new ArrayList<String>();
                            ItemStack glow = new ItemStack(XMaterial.GUNPOWDER.parseMaterial(), num);
                            ItemMeta glowMeta = glow.getItemMeta();
                            glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
                            Loret.add("§7This Dust has magical properties");
                            Loret.add("§7which make it a valuable currency.");
                            glowMeta.setLore(Loret);
                            glow.setItemMeta(glowMeta);

                            if (player.getInventory().containsAtLeast(glow, num)) {
                                player.getInventory().removeItem(glow);
                                player.getInventory().addItem(drill);
                            } else {
                            }
                        } catch (Exception ignored) {
                            player.closeInventory();
                        }
                }
            }
        }
    }








