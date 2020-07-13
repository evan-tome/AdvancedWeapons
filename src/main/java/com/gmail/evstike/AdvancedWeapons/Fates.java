package com.gmail.evstike.AdvancedWeapons;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Fates extends JavaPlugin implements Listener {
	public Fates() {
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		this.getLogger().info("AdvancedWeapons has been enabled");
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
		Bukkit.getServer().getPluginManager().registerEvents(new ConfigGUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MachineFunctions(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DustFunctions(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MachineMenu(this), this);
		this.getCommand("enchgui").setExecutor(new EnchantGUI(this));
		this.getCommand("enchgui").setTabCompleter(new EnchantGUI(this));
		this.getCommand("ce").setExecutor(new CustomEnchantGUI(this));
		this.getCommand("dust").setExecutor(new DustGUI(this));
		this.getCommand("weapons").setExecutor(new WeaponGUI(this));
		this.getCommand("advancedweapons").setExecutor(new Info(this));
		this.getCommand("advancedweapons").setTabCompleter(new Info(this));
		this.getCommand("ignite").setExecutor(new Commands(this));
		this.getCommand("hidden").setExecutor(new Hidden(this));
		this.getCommand("hidden").setTabCompleter(new Hidden(this));
		this.getCommand("machines").setExecutor(new MachineGUI(this));
		this.getCommand("coinflip").setExecutor(new Coinflip(this));
		new API(this);
		new ConfigGUI(this);
		Metrics metrics = new Metrics(this);
		Logger log = this.getLogger();
		FileConfiguration config = this.getConfig();
		File name = createFile("playerdata.yml");
		FileConfiguration nameconfig = createYamlFile(name);
		nameconfig.options().header("AdvancedWeapons Player Data is Stored Here");
		if(!nameconfig.contains("playerdata")) {
			nameconfig.createSection("playerdata");
		}
		//DUST
		if(config.contains("dust")) {
			ConfigurationSection section = config.getConfigurationSection("dust");
			for(String s : section.getKeys(false)) {
				OfflinePlayer player = Bukkit.getOfflinePlayer(s);
				int t = config.getInt("dust." + s);
				if (player.getName().length() > 16) {
					nameconfig.set("playerdata." + player.getName() + ".dust", t);
				}
				if (player.getName().length() <= 16) {
					nameconfig.set("playerdata." + player.getUniqueId() + ".dust", t);
				}
			}
			config.set("dust", null);
			this.saveConfig();
			log.info("Converted dust data");
		}
		//COINFLIP
		if(config.contains("coinflip")) {
			ConfigurationSection section = config.getConfigurationSection("coinflip");
			for(String s : section.getKeys(false)) {
				if(!s.equals("challenger")) {
				OfflinePlayer player = Bukkit.getOfflinePlayer(s);
				int wins = config.getInt("coinflip." + s + ".wins");
				int losses = config.getInt("coinflip." + s + ".losses");
					nameconfig.set("playerdata." + player.getUniqueId() + ".coinflip.wins", wins);
					nameconfig.set("playerdata." + player.getUniqueId() + ".coinflip.losses", losses);
				}
			}
			config.set("coinflip", null);
			this.saveConfig();
			log.info("Converted coinflip data");
		}
		File mname = createFile("machines.yml");
		FileConfiguration mconfig = createYamlFile(mname);
		//File minv = createFile("machineinv.yml");
		//FileConfiguration minvC = createYamlFile(minv);
		this.saveDefaultConfig();
		this.reloadConfig();
		saveYamlFile(nameconfig, name);
        saveYamlFile(mconfig, mname);
		//saveYamlFile(minvC, minv);
		if (metrics.isEnabled()) {
			log.info("AdvancedWeapons is using bStats");
		}

		if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null) {
			this.getLogger().info("ActionBarAPI detected");
		}

		if (Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null) {
			this.getLogger().info("ActionBarAPI has not been detected. Please install for full features");
		}
	}
	File createTempFile(String filename) {

		File c = new File(getDataFolder().getAbsolutePath());
		c.mkdir();
		File f= new File(getDataFolder().getAbsolutePath() + File.separator + filename);

		if(!f.exists())
		{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			f.delete();
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;
	}
	public File createFile(String filename)                                                                      //erstellt permanenten File
	{

		File c = new File(getDataFolder().getAbsolutePath());
		c.mkdir();
		File f= new File(getDataFolder().getAbsolutePath() + File.separator + filename);

		if(!f.exists())
		{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return f;
	}
	public FileConfiguration createYamlFile(File f)
	{
		FileConfiguration fc= YamlConfiguration.loadConfiguration(f);

		return fc;
	}

	public void saveYamlFile(FileConfiguration c,File f)
	{
		try {
			c.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onDisable() {
	}
}