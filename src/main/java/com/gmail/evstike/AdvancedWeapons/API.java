package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class API {

    Fates plugin;

    public API(Fates instance) {
        plugin = instance;
    }

    public API() {
    }
    //VERSIONS
    public boolean serverIs114() {
        if (Bukkit.getVersion().contains("1.14")) {
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
    public void activateEnchantment(Player p, PotionEffect pot) {
        p.addPotionEffect(pot);
    }

    public enum EnchantmentNumbers {
        I("1"),
        II("2"),
        III("3");

        EnchantmentNumbers(String s) {
        }
    }

    //COMMAND
    public boolean hasCommandPerm(CommandSender sender, Command cmd, String commandLabel, FileConfiguration f) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("advancedweapons." + cmd.getName())) {
                sender.sendMessage(f.getString("no-permission-msg").replace("{cmd}", commandLabel));
                return true;
            }
        }
        return false;
    }
}






