package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class DustFunctions extends API implements Listener {
    
    Fates plugin;
    
    public DustFunctions(Fates instance) {
        plugin = instance;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material mat = block.getType();
        Player p = event.getPlayer();
        String s = mat.name();
        if (p.getInventory().getItemInHand().hasItemMeta()) {
            if (!p.getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
                for (String f : plugin.getConfig().getStringList("dust-obtainable")) {
                    String[] words = f.split(":");
                    String firstWord = words[0];
                    String lastWord = f.substring(f.lastIndexOf(":") + 1);
                    if (firstWord.equals(s)) {
                        Random rand = new Random();
                        int w = Integer.parseInt(lastWord);
                        int n = rand.nextInt(100) + 1;
                        if (n <= w) {
                            event.getBlock().getLocation().getWorld().dropItemNaturally(
                                    event.getBlock().getLocation(), dust(plugin.getConfig().getStringList("dust-item")));
                            event.getPlayer().spawnParticle(Particle.ENCHANTMENT_TABLE, event.getBlock().getLocation(), 4);
                        }
                    }
                }
            }
        }
    }
}