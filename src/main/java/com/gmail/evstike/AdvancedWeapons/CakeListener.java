package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Cake;

import java.util.ArrayList;
import java.util.List;

public class CakeListener implements Listener {

    Fates plugin;

    public CakeListener(Fates instance) {
        plugin = instance;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onCakePlace(BlockPlaceEvent e) {
        FileConfiguration config = plugin.getConfig();
        if (config.getBoolean("cake") == true) {
            Player p = e.getPlayer();
            if (p.getInventory().getItemInHand().getType().name().contains("CAKE")) {
                Location loc = e.getBlock().getLocation();
                if (!p.getItemInHand().hasItemMeta()) return;
                for (String im : p.getInventory().getItemInHand().getItemMeta().getLore()) {
                    try {
                        int slice = Integer.parseInt(ChatColor.stripColor(im));
                        Block block = loc.getWorld().getBlockAt(loc);
                        BlockState state = block.getState();
                        Cake cake = (Cake) state.getData();
                        cake.setSlicesEaten(slice);
                        state.setData(cake);
                        state.update(true);
                    } catch (NumberFormatException exception) {
                    }
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onCakeBreak(BlockBreakEvent event) {
        FileConfiguration config = plugin.getConfig();
        if (config.getBoolean("cake") == true) {
            Player p = event.getPlayer();
            List<String> Lore = new ArrayList<String>();
            if (p.getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                if (event.getBlock().getType().name().contains("CAKE")) {
                    ItemStack cake = new ItemStack(Material.CAKE);
                    ItemMeta cakeMeta = cake.getItemMeta();
                    int c = event.getBlock().getData();
                    if (c > 0) {
                        Lore.add("§7" + c);
                        cakeMeta.setLore(Lore);
                        cake.setItemMeta(cakeMeta);
                        p.getWorld().dropItem(event.getBlock().getLocation(), cake);
                    }
                    if (c == 0) {
                        p.getWorld().dropItem(event.getBlock().getLocation(), cake);
                    }
                }
            }
        }
    }
}
