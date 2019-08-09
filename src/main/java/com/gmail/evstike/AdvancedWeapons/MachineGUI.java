package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
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
            ItemStack comp = new ItemStack(UMaterial.RED_STAINED_GLASS_PANE.getItemStack());
            ItemMeta compMeta = comp.getItemMeta();
            ItemStack un = new ItemStack(UMaterial.YELLOW_STAINED_GLASS_PANE.getItemStack());
            ItemMeta unMeta = un.getItemMeta();
            ItemStack dest = new ItemStack(UMaterial.DIAMOND_SWORD.getMaterial());
            ItemMeta destMeta = dest.getItemMeta();

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
            Lores.add("§7It will be coming soon");
            Lores.add("§7in a future update.");
            Lores.add("");
            unMeta.setLore(Lores);
            un.setItemMeta(unMeta);

            List<String> Lore = new ArrayList<String>();
            destMeta.setDisplayName(ChatColor.RED + "The Destroyer");
            int num = plugin.getConfig().getInt(ChatColor.stripColor("weapon."+
                    destMeta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
            destMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
            destMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
            Lore.add("§7Rush I");
            Lore.add("");
            Lore.add("§7This sword from the infernal");
            Lore.add("§7depths emits fire when swung.");
            Lore.add("");
            Lore.add("§b" + num +"x "+"§7DUST");
            destMeta.setLore(Lore);
            dest.setItemMeta(destMeta);

            //Inventory set
            inv.setItem(0, un);
            //inv.setItem(1, dest);

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
            switch (event.getCurrentItem().getType()) {
                case DIAMOND_SWORD:

                    try{

                        List<String> Lore = new ArrayList<String>();
                        ItemStack dest = new ItemStack(UMaterial.DIAMOND_SWORD.getMaterial(), 1);
                        ItemMeta destMeta = dest.getItemMeta();
                        destMeta.setDisplayName(ChatColor.RED + "The Destroyer");
                        Lore.add("§7Rush I");
                        destMeta.setLore(Lore);
                        dest.setItemMeta(destMeta);
                        int num = plugin.getConfig().getInt(ChatColor.stripColor("weapon."+
                                destMeta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
                        List<String> Loret = new ArrayList<String>();
                        ItemStack glow = new ItemStack(UMaterial.GUNPOWDER.getMaterial(),num);
                        ItemMeta glowMeta = glow.getItemMeta();
                        glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
                        Loret.add("§7This dust has magical properties");
                        Loret.add("§7which make it a valuable currency.");
                        glowMeta.setLore(Loret);
                        glow.setItemMeta(glowMeta);

                        if(player.getInventory().containsAtLeast(glow,num)){
                            player.getInventory().removeItem(glow);
                            dest.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
                            dest.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
                            player.getInventory().addItem(dest);

                        }else{

                        }

                    }catch(Exception ignored){
                        player.closeInventory();
                    }
            }
        }
    }








