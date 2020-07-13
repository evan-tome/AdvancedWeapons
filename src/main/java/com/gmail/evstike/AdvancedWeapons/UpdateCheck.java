package com.gmail.evstike.AdvancedWeapons;

import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import com.google.common.net.HttpHeaders;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.function.BiConsumer;

public class UpdateCheck implements Listener {

    public enum VersionResponse {

        LATEST,
        FOUND_NEW,

        UNAVAILABLE
    }

    private static final String SPIGOT_URL = "https://api.spigotmc.org/legacy/update.php?resource=67760";

    private final JavaPlugin javaPlugin;

    private String currentVersion;
    private int resourceId = -1;
    private BiConsumer<VersionResponse, String> versionResponse;

    UpdateCheck(@Nonnull JavaPlugin javaPlugin) {
        this.javaPlugin = Objects.requireNonNull(javaPlugin, "javaPlugin");
        this.currentVersion = javaPlugin.getDescription().getVersion();
    }

    public static UpdateCheck of(@Nonnull JavaPlugin javaPlugin) {
        return new UpdateCheck(javaPlugin);
    }

    public UpdateCheck currentVersion(@Nonnull String currentVersion) {
        this.currentVersion = currentVersion;
        return this;
    }

    public UpdateCheck resourceId(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public UpdateCheck handleResponse(@Nonnull BiConsumer<VersionResponse, String> versionResponse) {
        this.versionResponse = versionResponse;
        return this;
    }

    public void check() {
        Objects.requireNonNull(this.javaPlugin, "javaPlugin");
        Objects.requireNonNull(this.currentVersion, "currentVersion");
        Preconditions.checkState(this.resourceId != -1, "resource id not set");
        Objects.requireNonNull(this.versionResponse, "versionResponse");

        Bukkit.getScheduler().runTaskAsynchronously(this.javaPlugin, () -> {
            try {
                HttpURLConnection httpURLConnection = (HttpsURLConnection) new URL(String.format(SPIGOT_URL, this.resourceId)).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty(HttpHeaders.USER_AGENT, "Mozilla/5.0");
                String fetchedVersion = Resources.toString(httpURLConnection.getURL(), Charset.defaultCharset());

                boolean latestVersion = fetchedVersion.equalsIgnoreCase(this.currentVersion);

                Bukkit.getScheduler().runTask(this.javaPlugin, () -> this.versionResponse.accept(latestVersion ? VersionResponse.LATEST : VersionResponse.FOUND_NEW, latestVersion ? this.currentVersion : fetchedVersion));
            } catch (IOException exception) {
                exception.printStackTrace();
                Bukkit.getScheduler().runTask(this.javaPlugin, () -> this.versionResponse.accept(VersionResponse.UNAVAILABLE, null));
            }
        });
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        if (javaPlugin.getConfig().getBoolean("update-check") == true) {
            if (e.getPlayer().isOp()||e.getPlayer().hasPermission("advancedweapons.admin")) {
                UpdateCheck
                        .of(javaPlugin)
                        .resourceId(67760)
                        .handleResponse((versionResponse, version) -> {
                            switch (versionResponse) {
                                case FOUND_NEW:
                                    if (!javaPlugin.getDescription().getVersion().contains("dev")) {
                                        e.getPlayer().sendMessage(ChatColor.GREEN + "New version of AdvancedWeapons was found: " + version);
                                        Bukkit.getLogger().info("New version of AdvancedWeapons was found: " + version);
                                    }
                                    break;
                                case LATEST:
                                    Bukkit.getLogger().info("You are using the latest version of AdvancedWeapons: " + version);
                                    break;
                                case UNAVAILABLE:
                                    Bukkit.getLogger().info("Unable to perform an update check.");
                            }
                        }).check();
            }
        }
    }

    @EventHandler
    public void pluginEnable(PluginEnableEvent e) {
        if (javaPlugin.getConfig().getBoolean("update-check") == true) {
            if (e.getPlugin() == javaPlugin) {
                UpdateCheck
                        .of(javaPlugin)
                        .resourceId(67760)
                        .handleResponse((versionResponse, version) -> {
                            switch (versionResponse) {
                                case FOUND_NEW:
                                    if (!javaPlugin.getDescription().getVersion().contains("dev")) {
                                        Bukkit.getLogger().warning("[AdvancedWeapons] New version of AdvancedWeapons was found: " + version);
                                    }
                                    break;
                                case LATEST:
                                    Bukkit.getLogger().info("You are using the latest version of AdvancedWeapons: " + version);
                                    break;
                                case UNAVAILABLE:
                                    Bukkit.getLogger().info("Unable to perform an update check.");
                            }
                        }).check();
            }
        }
    }
}