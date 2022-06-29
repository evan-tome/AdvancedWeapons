package com.gmail.evstike.AdvancedWeapons.Enchants;

import com.gmail.evstike.AdvancedWeapons.API;
import com.gmail.evstike.AdvancedWeapons.Fates;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class EnchantBlockBreak extends API implements Listener {
    
    Fates plugin;
    
    public EnchantBlockBreak(Fates instance) {
        plugin = instance;
    }
    
    public List<String> affected = Lists.newArrayList();
    public String effects;
    
    public boolean chance(String path) {
        Random rand = new Random();
        int n;
        if (!moduleIsDisabled("enchants", plugin.getConfig())) {
            n = rand.nextInt(100) + 1;
            if (n <= (plugin.getConfig().getInt("enchant." + path + ".chance"))) {
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings("deprecation")
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        FileConfiguration config = plugin.getConfig();
        ItemStack i = p.getInventory().getItemInHand();
        ItemMeta im = i.getItemMeta();
        int dur = i.getDurability();
        Block b = e.getBlock();
        Material bt = e.getBlock().getType();
    
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
                            if (events.contains("blockbreak")) {
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
                                    if (item.contains("blocks")) {
                                        if (!(item.getStringList("blocks").contains(e.getBlock().getType().name()))) {
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
                                        Location loc = p.getLocation();
                                    
                                        Boolean destroy = false;
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
                                    //FORTUNE
                                    if (function.equals("fortune")) {
                                        if (item.contains("amount")) {
                                            if (item.contains("dropatplayer")) {
                                            } else {
                                                Bukkit.getLogger().warning(n + " does not specify a drop location.");
                                            }
                                        } else {
                                            Bukkit.getLogger().warning(n + " does not have an amount.");
                                        }
                                        ItemStack bd = new ItemStack(bt, item.getInt("amount"));
                                        if (item.getBoolean("dropatplayer") == true) {
                                            p.getWorld().dropItemNaturally(p.getLocation(), bd);
                                        }
                                        if (item.getBoolean("dropatplayer") == false) {
                                            p.getWorld().dropItemNaturally(b.getLocation(), bd);
                                        }
                                    }
                                    if (item.contains("msg")) {
                                        if (item.getBoolean("msg") == true) {
                                            if (item.contains("chatmsg")) {
    
                                                String attackt = StringUtils.capitaliseAllWords(p.getType().getName().toLowerCase().replace("_", " "));
                                                String defendt = StringUtils.capitaliseAllWords(p.getType().getName().toLowerCase().replace("_", " "));
                                                String block = StringUtils.capitaliseAllWords(e.getBlock().getType().name().toLowerCase().replace("_", " "));
    
                                                String user = p.getDisplayName();
    
                                                effects = StringUtils.capitaliseAllWords
                                                        (item.getStringList("effects").toString().replace("[", "").replace("]", ""));
    
                                                p.sendMessage(item.getString("chatmsg").replace("{attacker}", p.getName())
                                                        .replace("{defender}", p.getName()).replace("{attackertype}", attackt)
                                                        .replace("{defendertype}", defendt).replace("{effects}", effects).replace("{user}", user)
                                                        .replace("{block}", block)
                                                        .replace("{user}", p.getName()).replace("{potion}", effects).replace("{damage}", effects)
                                                        .replace("&", "§"));
                                            }
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
