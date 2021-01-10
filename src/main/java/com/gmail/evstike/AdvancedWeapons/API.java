package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("deprecation")
public class API implements InventoryHolder {

    Fates plugin;

    public API(Fates instance) {
        plugin = instance;
    }

    public API() {
    }

    //VERSIONS
    public boolean serverIs116() {
        if (Bukkit.getVersion().contains("1.16")) {
            return true;
        }
        return false;
    }

    public boolean serverIs115() {
        if (Bukkit.getVersion().contains("1.15") || serverIs116()) {
            return true;
        }
        return false;
    }

    public boolean serverIs114() {
        if (Bukkit.getVersion().contains("1.14") || serverIs115()) {
            return true;
        }
        return false;
    }

    public boolean serverIs113() {
        if (Bukkit.getVersion().contains("1.13") || serverIs114()) {
            return true;
        }
        return false;
    }

    public boolean serverIs112() {
        if (Bukkit.getVersion().contains("1.12") || serverIs113()) {
            return true;
        }
        return false;
    }

    public boolean serverIs1111() {
        if (Bukkit.getVersion().contains("1.11.1") || Bukkit.getVersion().contains("1.11.2") || serverIs112()) {
            return true;
        }
        return false;
    }

    public boolean serverIs111() {
        if (Bukkit.getVersion().contains("1.11") || serverIs1111()) {
            return true;
        }
        return false;
    }

    public boolean serverIs110() {
        if (Bukkit.getVersion().contains("1.10") || serverIs111() || serverIs1111()) {
            return true;
        }
        return false;
    }

    public boolean serverIs19() {
        if (Bukkit.getVersion().contains("1.9") || serverIs110()) {
            return true;
        }
        return false;
    }

    public boolean serverIs18() {
        if (Bukkit.getVersion().contains("1.8") || serverIs19()) {
            return true;
        }
        return false;
    }

    //ITEMS
    public boolean armorHasLore(PlayerInventory inv, String lore) {
        if (inv.getHelmet() != null && inv.getHelmet().hasItemMeta() && inv.getHelmet().getItemMeta().hasLore()
                && inv.getHelmet().getItemMeta().getLore().contains(lore)
                || inv.getChestplate() != null && inv.getChestplate().hasItemMeta()
                && inv.getChestplate().getItemMeta().hasLore()
                && inv.getChestplate().getItemMeta().getLore().contains(lore)
                || inv.getLeggings() != null && inv.getLeggings().hasItemMeta()
                && inv.getLeggings().getItemMeta().hasLore()
                && inv.getLeggings().getItemMeta().getLore().contains(lore)
                || inv.getBoots() != null && inv.getBoots().hasItemMeta() && inv.getBoots().getItemMeta().hasLore()
                && inv.getBoots().getItemMeta().getLore().contains(lore)) {
            return true;
        }
        return false;
    }

    public boolean itemHasLore(PlayerInventory inv, String lore) {
        if (inv.getItemInHand().getType() != Material.AIR) {
            if (inv.getItemInHand().hasItemMeta()) {
                if (inv.getItemInHand().getItemMeta().hasLore()) {
                    if (inv.getItemInHand().getItemMeta().getLore().contains(lore)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isWeapon(Player player) {
        if (player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("sword") || player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("_axe")) {
            return true;
        }
        return false;
    }

    public boolean isArmor(Player player) {
        if (player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("helmet") || player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("chestplate") || player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("leggings") || player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("boots")) {
            return true;
        }
        return false;
    }

    public boolean isTool(Player player) {
        if (player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("pickaxe") || player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("_axe") || player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("shovel") || player.getInventory().getItemInHand().getType().toString().toLowerCase()
                .contains("shears")) {
            return true;
        }
        return false;
    }

    //ENCHANTMENT
    public int enchlevel(Player p, String s, ItemStack i) {
        for (String l : i.getItemMeta().getLore()) {

            if (ChatColor.stripColor(l).equals(ChatColor.stripColor(s))) {
                String test = ChatColor.stripColor(l);
                String lastWord = test.substring(test.lastIndexOf(" ") + 1);

                if (lastWord.equals(ChatColor.stripColor("I"))) {
                    return 1;
                }
                if (lastWord.equals(ChatColor.stripColor("II"))) {
                    return 2;
                }
                if (lastWord.equals(ChatColor.stripColor("III"))) {
                    return 3;
                }
                if (lastWord.equals(ChatColor.stripColor("IV"))) {
                    return 4;
                }
                if (lastWord.equals(ChatColor.stripColor("V"))) {
                    return 5;
                }
            }
        }
        return 0;
    }

    //COMMAND
    public boolean hasCommandPerm(CommandSender sender, Command cmd, String commandLabel, FileConfiguration f) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("advancedweapons." + cmd.getName())) {
                sender.sendMessage(f.getString("no-permission-msg").replace('&', '§').replace("{cmd}", commandLabel));
                return true;
            }
        }
        return false;
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static int getAmount(Player arg0, ItemStack arg1) {
        if (arg1 == null)
            return 0;
        int amount = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack slot = arg0.getInventory().getItem(i);
            if (slot == null || !slot.isSimilar(arg1))
                continue;
            amount += slot.getAmount();
        }
        return amount;
    }
    public ItemStack autominer() {
        ItemStack autominer = new ItemStack(XMaterial.HOPPER.parseMaterial());
        ItemMeta autominerMeta = autominer.getItemMeta();
        autominerMeta.setDisplayName("§bAutoMiner");
        autominer.setItemMeta(autominerMeta);
        return autominer;
    }
    public ItemStack dust(List<String> s) {
        List<String> Lore = new ArrayList();
        ItemStack glow = new ItemStack(XMaterial.GUNPOWDER.parseMaterial(), 1);
        ItemMeta glowMeta = glow.getItemMeta();
        glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
        for (String l : s) {
            Lore.add(l.replace('&','§'));
        }
        Lore.add("§8§oDust");
        glowMeta.setLore(Lore);
        glow.setItemMeta(glowMeta);
        return glow;
    }
    public ItemStack dustold() {
        List<String> Lore = new ArrayList();
        ItemStack glow = new ItemStack(XMaterial.GUNPOWDER.parseMaterial(), 1);
        ItemMeta glowMeta = glow.getItemMeta();
        glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
        Lore.add("§7This Dust has magical properties");
        Lore.add("§7which make it a valuable currency.");
        glowMeta.setLore(Lore);
        glow.setItemMeta(glowMeta);
        return glow;
    }
    public ItemStack back() {
        ItemStack back = new ItemStack(XMaterial.ARROW.parseItem());
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(ChatColor.GREEN + "Back");
        back.setItemMeta(backMeta);
        return back;
    }
    public void hasAvaliableSlot(Player playerP, ItemStack item, String s){
        HashMap<Integer, ItemStack> leftOver = new HashMap<Integer, ItemStack>();
        leftOver.putAll((playerP.getInventory().addItem(item)));
        if (!leftOver.isEmpty()) {
            Location loc = playerP.getLocation();
            ItemStack item2 = item.clone();
            item2.setAmount(leftOver.get(0).getAmount());
            playerP.getWorld().dropItem(loc, item2);
            playerP.sendMessage(s.replace('&', '§'));
        }
    }
    @Override
    public Inventory getInventory() {
        return null;
    }
}