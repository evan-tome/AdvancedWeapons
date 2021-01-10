package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class UpdateCheck implements Listener {

    private Fates plug;
    private JavaPlugin plugin;
    private int resourceId;

    public UpdateCheck(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public UpdateCheck(Fates instance) {
        plug = instance;
    }

    {
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plug.getLogger().info("Cannot look for updates: " + exception.getMessage());
            }
        });
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        if (plug.getConfig().getBoolean("update-check")) {
            if (e.getPlayer().isOp() || e.getPlayer().hasPermission("advancedweapons.admin")) {
                if (!plug.getDescription().getVersion().contains("-dev")) {
                    Logger logger = plug.getLogger();

                    new UpdateCheck(plug, 67760).getVersion(version -> {
                        if (plug.getDescription().getVersion().contains("-beta")) {
                            e.getPlayer().sendMessage("§aYou are using a beta version of AdvancedWeapons: " + plug.getDescription().getVersion().replace("beta", "§bbeta"));
                            logger.info("You are using a beta version of AdvancedWeapons");
                        }
                        if (plug.getDescription().getVersion().replace("-beta", "").equalsIgnoreCase(version)) {
                            //logger.info("You are using the latest version of AdvancedWeapons: " + version);
                        } else {
                            e.getPlayer().sendMessage(ChatColor.GREEN + "New version of AdvancedWeapons was found: " + version);
                            logger.info("New version of AdvancedWeapons was found: " + version);
                        }
                    });
                }
            }
        }
    }
}