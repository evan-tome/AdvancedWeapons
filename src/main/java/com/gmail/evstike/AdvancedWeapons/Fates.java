package com.gmail.evstike.AdvancedWeapons;

import com.gmail.evstike.AdvancedWeapons.Enchants.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;


public class Fates extends JavaPlugin implements Listener {
	public Fates() {
	}
	private static Economy econ = null;

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
        this.getLogger().info("AdvancedWeapons has been enabled");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantGUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WeaponGUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DustGUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CustomEnchantGUI(this), this);
		
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantInteract(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantAttackOther(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantAttackSelf(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantArmorOther(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantArmorSelf(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantItemOther(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantItemSelf(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantBlockBreak(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantArrowSelf(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantArrowOther(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new EnchantArrowLand(this), this);
		
		Bukkit.getServer().getPluginManager().registerEvents(new MachineGUI(this), this);
		
		Coinflip coinflip = new Coinflip(this);
		Bukkit.getServer().getPluginManager().registerEvents(coinflip, this);
		
		
		Bukkit.getServer().getPluginManager().registerEvents(new CakeListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new WeaponFunctions(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new UpdateCheck(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new ConfigGUI(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Hidden(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MachineFunctions(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new DustFunctions(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MachineMenu(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CEditor(this), this);
		this.getCommand("enchgui").setExecutor(new EnchantGUI(this));
		this.getCommand("enchgui").setTabCompleter(new EnchantGUI(this));
		this.getCommand("ce").setExecutor(new CustomEnchantGUI(this));
		this.getCommand("dust").setExecutor(new DustGUI(this));
		this.getCommand("weapons").setExecutor(new WeaponGUI(this));
		this.getCommand("advancedweapons").setExecutor(new Info(this));
		this.getCommand("advancedweapons").setTabCompleter(new Info(this));
		this.getCommand("hidden").setExecutor(new Hidden(this));
		this.getCommand("hidden").setTabCompleter(new Hidden(this));
		this.getCommand("machines").setExecutor(new MachineGUI(this));
		this.getCommand("coinflip").setExecutor(coinflip);
		this.getCommand("ceditor").setExecutor(new CEditor(this));
		new API(this);
		new ConfigGUI(this);
		Metrics metrics = new Metrics(this);
		Logger log = this.getLogger();
		if(!Bukkit.getVersion().contains("1.18")) {
			this.getLogger().info("AdvancedWeapons is not compatible with your server version");
		}
		
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
		File iname = createFile("machineinv.yml");
		FileConfiguration iconfig = createYamlFile(iname);
		File wname = createFile("customweapons.yml");
		FileConfiguration wconfig = createYamlFile(wname);
		
		saveYamlFile(nameconfig, name);
		saveYamlFile(mconfig, mname);
		saveYamlFile(iconfig, iname);
		saveYamlFile(wconfig, wname);
		
		this.saveDefaultConfig();
		
		//UPDATER CONFIG

		File update = createFile("updater.yml");
		FileConfiguration updateconfig = createYamlFile(update);
		
		ArrayList l = new ArrayList();
		ArrayList l2 = new ArrayList();
		for (String key2 : updateconfig.getKeys(true)) {
			l.add(key2);
		}
		for (String key3 : config.getKeys(true)) {
			l2.add(key3);
		}
		for (String key : config.getDefaults().getKeys(true)) {
			if (!l.contains(key)) {
				if (!l2.contains(key)) {
					Object s = config.getDefaults().get(key);
					config.set(key, s);
					saveYamlFile(config, createFile("config.yml"));
				}
			}
		}
		l.clear();
		l2.clear();
		
		for (String key : config.getDefaults().getKeys(false)) {
			updateconfig.options().header("DO NOT EDIT OR DELETE");
			Object s = config.getDefaults().get(key);
			updateconfig.set(key, s);
		}
		saveYamlFile(updateconfig, update);
		
		if (metrics.isEnabled()) {
			log.info("AdvancedWeapons is using bStats");
		}
		if (!setupEconomy() ) {
			log.severe("Vault could not find an economy.");
			return;
		}
		if (this.getConfig().getBoolean("update-check")) {
			if (!this.getDescription().getVersion().contains("-dev")) {
				Logger logger = this.getLogger();

				new UpdateCheck(this, 67760).getVersion(version -> {
                    if (this.getDescription().getVersion().contains("-beta")) {
                        logger.info("You are using a beta version of AdvancedWeapons. Some features may be unstable");
                    }
					if (this.getDescription().getVersion().replace("-beta", "").equalsIgnoreCase(version)) {
						logger.info("You are using the latest version of AdvancedWeapons: " + version);
					} else {
						logger.info("New version of AdvancedWeapons was found: " + version);
					}
				});
			}
		}
	}
	public boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	public static Economy getEconomy() {
		return econ;
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