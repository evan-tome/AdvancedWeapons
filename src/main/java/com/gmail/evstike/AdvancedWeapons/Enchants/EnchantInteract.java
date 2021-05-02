package com.gmail.evstike.AdvancedWeapons.Enchants;

import com.gmail.evstike.AdvancedWeapons.API;
import com.gmail.evstike.AdvancedWeapons.Fates;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class EnchantInteract extends API implements Listener {
    
    Fates plugin;
    
    public EnchantInteract(Fates instance) {
        plugin = instance;
    }
    
    public List<String> affected = Lists.newArrayList();
    public String effects;
    
    public boolean chance(String path) {
        Random rand = new Random();
        int n = rand.nextInt(100) + 1;
        if (n <= (plugin.getConfig().getInt("enchant." + path + ".chance"))) {
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        FileConfiguration config = plugin.getConfig();
        ItemStack i = p.getInventory().getItemInHand();
        ItemMeta im = i.getItemMeta();
        int dur = i.getDurability();
        
        List<Action> actions = Arrays.asList(Action.LEFT_CLICK_AIR, Action.RIGHT_CLICK_AIR, Action.LEFT_CLICK_BLOCK, Action.RIGHT_CLICK_BLOCK);
        if (actions.contains(event.getAction())) {
            
            if (serverIs19()) {
                if (event.getHand().equals(EquipmentSlot.OFF_HAND)) {
                    return;
                }
            }
            if (im != null) {
                if (im.hasLore()) {
                    for (String s : im.getLore()) {
                        ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
                        for (String key : section.getKeys(false)) {
                            section.get(key);
                            ConfigurationSection item = section.getConfigurationSection(key);
                            
                            String n = item.getString("name").replace('&', '§');
                            String name = ChatColor.GRAY + ChatColor.stripColor(n);
                            int time = item.getInt("duration");
                            int ti = time * 20;
                            int l = item.getInt("amplifier");
                            
                            if (name.equals(s)) {
                                List events = item.getList("events");
                                if (events.contains("interact")) {
                                    if (chance(ChatColor.stripColor(name).toLowerCase().replace(" ", "-"))) {
                                        
                                        String function = item.getString("function");
                                        
                                        if (item.contains("dimension")) {
                                            if (!(item.getStringList("dimension").contains(p.getWorld().getEnvironment().toString()))) {
                                                return;
                                            }
                                        }
                                        
                                        if (item.contains("thresholdself")) {
                                            if (!(p.getHealth() <= p.getMaxHealth() * item.getDouble("thresholdself") / 100)) {
                                                return;
                                            }
                                        }
                                        if (item.contains("thresholdother")) {
                                            if (!(p.getHealth() <= p.getMaxHealth() * item.getDouble("thresholdother") / 100)) {
                                                return;
                                            }
                                        }
                                        
                                        //POTION
                                        if (function.equals("potion")) {
                                            for (String effect : item.getStringList("effects")) {
                                                if (PotionEffectType.getByName(effect.toUpperCase()) != null) {
                                                    if (item.contains("duration")) {
                                                        if (item.contains("amplifier")) {
                                                        } else {
                                                            Bukkit.getLogger().warning(n + " does not have an amplifier.");
                                                        }
                                                    } else {
                                                        Bukkit.getLogger().warning(n + " does not have a duration.");
                                                    }
                                                    p.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
                                                }
                                            }
                                        }
                                        //EXPLOSION
                                        if (function.equals("explosion")) {
                                            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                                                Location loc = event.getClickedBlock().getLocation();
                                                Boolean destroy = true;
                                                int power = 1;
                                                if (item.contains("destroy")) {
                                                    destroy = item.getBoolean("destroy");
                                                }
                                                if (item.contains("power")) {
                                                    power = item.getInt("power");
                                                }
                                                if (destroy == true) {
                                                    loc.getWorld().createExplosion(loc, power, false, true);
                                                }
                                                if (destroy == false) {
                                                    loc.getWorld().createExplosion(loc, power, false, false);
                                                    
                                                }
                                            }
                                        }
                                        if (item.contains("msg")) {
                                            if (item.getBoolean("msg") == true) {
                                                effects = StringUtils.capitaliseAllWords
                                                        (item.getStringList("effects").toString().replace("[", "").replace("]", ""));
                                                p.sendMessage(item.getString("chatmsg").replace("{user}", p.getName()).replace("{potion}", effects));
                                                effects = "";
                                            }
                                        }
                                        if (config.getBoolean("durability") == true) {
                                            i.setDurability((short) (dur + 1));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
