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
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class EnchantArrowSelf extends API implements Listener {
    
    Fates plugin;
    
    public EnchantArrowSelf(Fates instance) {
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
    public void onArrowSelf(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof LivingEntity) {
            LivingEntity defender = (LivingEntity) e.getEntity();
            if (e.getDamager() instanceof Arrow) {
                Arrow ar = (Arrow) e.getDamager();
                if (ar.getShooter() instanceof Player) {
                    Player attacker = (Player) ar.getShooter();
                    FileConfiguration config = plugin.getConfig();
                    ItemStack i = attacker.getInventory().getItemInHand();
                    ItemMeta im = i.getItemMeta();
                    int dur = i.getDurability();
    
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
                                        if (events.contains("attackself")) {
                                            if (chance(ChatColor.stripColor(name).toLowerCase().replace(" ", "-"))) {
                                
                                                String function = item.getString("function");
                                                LivingEntity t = defender;
                                
                                                if (item.contains("dimension")) {
                                                    if (!(item.getStringList("dimension").contains(attacker.getWorld().getEnvironment().toString()))) {
                                                        return;
                                                    }
                                                }
                                
                                                if (item.contains("thresholdself")) {
                                                    if (!(attacker.getHealth() <= attacker.getMaxHealth() * item.getDouble("thresholdself") / 100)) {
                                                        return;
                                                    }
                                                }
                                                if (item.contains("thresholdother")) {
                                                    if (!(defender.getHealth() <= defender.getMaxHealth() * item.getDouble("thresholdother") / 100)) {
                                                        return;
                                                    }
                                                }
                                
                                                //TEST AGAINST MOBS
                                                if (item.contains("affected")) {
                                                    for (String a : item.getStringList("affected")) {
                                                        if (a.equals(t.getType().toString())) {
                                                            affected.add(t.getType().toString());
                                                        }
                                                        if (a.equals("poison")) {
                                                            for (String p : poison) {
                                                                if (!affected.contains(p)) {
                                                                    affected.add(p);
                                                                }
                                                            }
                                                        }
                                                        if (a.equals("undead")) {
                                                            for (String u : undead) {
                                                                if (!affected.contains(u)) {
                                                                    affected.add(u);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (!affected.contains(t.getType().toString())) {
                                                        return;
                                                    }
                                                    affected.clear();
                                                }
                                                if (item.contains("immune")) {
                                                    for (String a : item.getStringList("immune")) {
                                                        if (!(a.equals("poison") || a.equals("undead"))) {
                                                            if (a.equalsIgnoreCase(t.getType().toString())) {
                                                                return;
                                                            }
                                                        }
                                                        if (a.equals("poison")) {
                                                            if (poison.contains(t.getType().toString())) {
                                                                return;
                                                            }
                                                        }
                                                        if (a.equals("undead")) {
                                                            if (undead.contains(t.getType().toString())) {
                                                                return;
                                                            }
                                                        }
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
                                                            attacker.addPotionEffect(new PotionEffect(PotionEffectType.getByName(effect.toUpperCase()), ti, l));
                                                        }
                                                    }
                                                }
                                                //DAMAGE
                                                if (function.equals("damage")) {
                                                    for (Double effect : item.getDoubleList("effects")) {
                                                        attacker.damage(effect);
                                                    }
                                                }
                                                //EXPLOSION
                                                if (function.equals("explosion")) {
                                                    Location loc = defender.getLocation();
                                                    loc = attacker.getLocation();
                                                    
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
                                                if (item.contains("msg")) {
                                                    if (item.getBoolean("msg") == true) {
                                                        if (item.contains("chatmsg")) {
                                            
                                                            String attackt = StringUtils.capitaliseAllWords(attacker.getType().getName().toLowerCase().replace("_", " "));
                                                            String defendt = StringUtils.capitaliseAllWords(defender.getType().getName().toLowerCase().replace("_", " "));
                                            
                                                            String user = attacker.getDisplayName();
                                            
                                                            effects = StringUtils.capitaliseAllWords
                                                                    (item.getStringList("effects").toString().replace("[", "").replace("]", ""));
                                            
                                                            attacker.sendMessage(item.getString("chatmsg").replace("{attacker}", attacker.getName())
                                                                    .replace("{defender}", defender.getName()).replace("{attackertype}", attackt)
                                                                    .replace("{defendertype}", defendt).replace("{effects}", effects).replace("{user}", user)
                                                                    .replace("{user}", defender.getName()).replace("{potion}", effects).replace("{damage}", effects)
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
        }
    }
}