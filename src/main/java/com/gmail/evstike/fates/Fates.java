package com.gmail.evstike.fates;


import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Fates extends JavaPlugin {
	
    public Permission playerPermission = new Permission("playerAbilities.allowed");
	
    @Override
	public void onEnable() {
    	getLogger().info("AdvancedWeapons has been enabled!");
    	//new ItemListener(this);
    	Bukkit.getServer().getPluginManager().registerEvents(new EnchantGUI(), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new WeaponGUI(), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new DustGUI(), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new CustomEnchantGUI(), this);
    	//Bukkit.getServer().getPluginManager().registerEvents(new ItemListener(null), this);
    	new WeaponFunctions(this);
    	new WeaponEnchants(this);
    	new Runes(this);
    	PluginManager pm = getServer().getPluginManager();
		if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null)
		getLogger().info("ActionBarAPI detected");
		if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null)
		getLogger().info("ActionBarAPI has not been detected. Please install for full features");
    	pm.addPermission(playerPermission);	
    	getCommand("enchgui").setExecutor(new EnchantGUI());
       	getCommand("ce").setExecutor(new CustomEnchantGUI());
    	getCommand("dust").setExecutor(new DustGUI());
    	getCommand("weapons").setExecutor(new WeaponGUI());
    	getCommand("advancedweapons").setExecutor(new Commands(null));
    	getCommand("ignite").setExecutor(new Commands(null));
    	getCommand("hideme").setExecutor(new Commands(null));
    	getCommand("showme").setExecutor(new Commands(null));
    	final FileConfiguration config = this.getConfig();
    	this.getConfig();
    	this.getConfig().options().copyDefaults(true);
    	config.addDefault("weaponeffects", "true");
    	this.saveDefaultConfig();
    	
    	
    	
    	Logger log = getLogger();
    	log.info("WeaponEffects: " + this.getConfig().getBoolean("weaponseffects"));
    }

    
    @Override
	public void onDisable() {
     
    
}
}

