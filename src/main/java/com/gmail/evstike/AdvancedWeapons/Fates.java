package com.gmail.evstike.AdvancedWeapons;


import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class Fates extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
    	getLogger().info("AdvancedWeapons has been enabled");
    	Bukkit.getServer().getPluginManager().registerEvents(this, this);
    	Bukkit.getServer().getPluginManager().registerEvents(new EnchantGUI(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new WeaponGUI(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new DustGUI(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new CustomEnchantGUI(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new WeaponEnchants(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MachineGUI(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new Coinflip(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new CakeListener(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new WeaponFunctions(this), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new UpdateCheck(this), this);
    	getCommand("enchgui").setExecutor(new EnchantGUI(this));
    	getCommand("enchgui").setTabCompleter(new EnchantGUI(this));
       	getCommand("ce").setExecutor(new CustomEnchantGUI(this));
    	getCommand("dust").setExecutor(new DustGUI(this));
    	getCommand("weapons").setExecutor(new WeaponGUI(this));
    	getCommand("advancedweapons").setExecutor(new Info(this));
    	getCommand("advancedweapons").setTabCompleter(new Info(this));
    	getCommand("ignite").setExecutor(new Commands(this));
		getCommand("hidden").setExecutor(new Commands(this));
    	getCommand("hideme").setExecutor(new Commands(this));
    	getCommand("showme").setExecutor(new Commands(this));
		getCommand("machines").setExecutor(new MachineGUI(this));
    	getCommand("coinflip").setExecutor(new Coinflip(this));
		API api = new API(this);
		Metrics metrics = new Metrics(this);
    	
    	//config
    	final FileConfiguration config = this.getConfig();  	
    	config.options().copyDefaults(true);  	
    	this.saveConfig();

    	//logger
		Logger log = getLogger();
		if(metrics.isEnabled()) {
			log.info("AdvancedWeapons is using bStats");
		}

		if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
			getLogger().info("ActionBarAPI detected");
		}
		if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null) {
			getLogger().info("ActionBarAPI has not been detected. Please install for full features");
		}
    	
    }
    @Override
	public void onDisable() {
		//getLogger().info("Disabling AdvancedWeapons.");
	}
}

