package com.gmail.evstike.AdvancedWeapons;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WeaponGUI extends API implements CommandExecutor, Listener {

	Fates plugin;

	public WeaponGUI(Fates instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig()) == false) {
			if (cmd.getName().equalsIgnoreCase("weapons")) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					openGUI(player);
					return false;
				}
				if (!(sender instanceof Player)) {
					sender.sendMessage("§cError: §4Only Players can use this command!");
					return true;
				}
			}
		}
		return false;
	}

	private void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9, "Weapons");

		//Items
		ItemStack comp = new ItemStack(UMaterial.RED_STAINED_GLASS_PANE.getItemStack());
		ItemMeta compMeta = comp.getItemMeta();
		ItemStack dest = new ItemStack(UMaterial.DIAMOND_SWORD.getMaterial());
		ItemMeta destMeta = dest.getItemMeta();
		ItemStack slay = new ItemStack(UMaterial.DIAMOND_AXE.getMaterial());
		ItemMeta slayMeta = slay.getItemMeta();
		ItemStack drop = new ItemStack(UMaterial.STICK.getMaterial());
		ItemMeta dropMeta = drop.getItemMeta();
		ItemStack rod = new ItemStack(UMaterial.BLAZE_ROD.getMaterial());
		ItemMeta rodMeta = rod.getItemMeta();

		//Item meta
		List<String> Lore0 = new ArrayList<String>();
		compMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "INCOMPATIBLE");
		Lore0.add("");
		Lore0.add("§cThis item is not compatible");
		Lore0.add("§cwith your server version.");
		Lore0.add("§aIt is recommended to update to a newer");
		Lore0.add("§aversion for full compatibility.");
		Lore0.add("");
		compMeta.setLore(Lore0);
		comp.setItemMeta(compMeta);

		List<String> Lore = new ArrayList<String>();
		destMeta.setDisplayName(ChatColor.RED + "The Destroyer");
		int num = plugin.getConfig().getInt(ChatColor.stripColor("weapon." +
				destMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
		destMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		destMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		Lore.add("§7Rush I");
		Lore.add("");
		Lore.add("§7This sword from the infernal");
		Lore.add("§7depths emits fire when swung.");
		Lore.add("");
		Lore.add("§b" + num + "x " + "§7DUST");
		destMeta.setLore(Lore);
		dest.setItemMeta(destMeta);

		List<String> Lore2 = new ArrayList<String>();
		slayMeta.setDisplayName(ChatColor.RED + "The Slayer");
		int num2 = plugin.getConfig().getInt(ChatColor.stripColor("weapon." +
				slayMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
		slayMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 3, true);
		slayMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		Lore2.add("");
		Lore2.add("§7This deadly axe is rumoured");
		Lore2.add("§7to heal its user at low health.");
		Lore2.add("");
		Lore2.add("§b" + num2 + "x " + "§7DUST");
		slayMeta.setLore(Lore2);
		slay.setItemMeta(slayMeta);

		List<String> Lore3 = new ArrayList<String>();
		dropMeta.setDisplayName(ChatColor.RED + "The Dropper");
		int num3 = plugin.getConfig().getInt(ChatColor.stripColor("weapon." +
				dropMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
		Lore3.add("");
		Lore3.add("§7This silly stick drops levitating");
		Lore3.add("§7bullets while its user is crouching.");
		Lore3.add("");
		Lore3.add("§b" + num3 + "x " + "§7DUST");
		dropMeta.setLore(Lore3);
		drop.setItemMeta(dropMeta);

		List<String> Lore4 = new ArrayList<String>();
		rodMeta.setDisplayName(ChatColor.RED + "Fireball Launcher");
		int num4 = plugin.getConfig().getInt(ChatColor.stripColor("weapon." +
				rodMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
		Lore4.add("");
		Lore4.add("§7This rod of flames shoots fireballs");
		Lore4.add("§7while its user is crouching.");
		Lore4.add("");
		Lore4.add("§b" + num4 + "x " + "§7DUST");
		rodMeta.setLore(Lore4);
		rod.setItemMeta(rodMeta);


		//Inventory set
		inv.setItem(0, dest);
		inv.setItem(1, slay);
		if (serverIs19()) {
			inv.setItem(2, drop);
		}
		if (!serverIs19()) {
			inv.setItem(2, comp);
		}

		inv.setItem(3, rod);

		player.openInventory(inv);

	}


	@SuppressWarnings({"incomplete-switch"})
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (!ChatColor.stripColor(event.getView().getTitle())
				.equalsIgnoreCase("Weapons"))
			return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);


		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {

			return;
		}
		if (event.getClickedInventory().getType().equals(InventoryType.CHEST)) {
			switch (event.getCurrentItem().getType()) {
				case DIAMOND_SWORD:

					try {

						List<String> Lore = new ArrayList<String>();
						ItemStack dest = new ItemStack(UMaterial.DIAMOND_SWORD.getMaterial(), 1);
						ItemMeta destMeta = dest.getItemMeta();
						destMeta.setDisplayName(ChatColor.RED + "The Destroyer");
						Lore.add("§7Rush I");
						destMeta.setLore(Lore);
						dest.setItemMeta(destMeta);
						int num = plugin.getConfig().getInt(ChatColor.stripColor("weapon." +
								destMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
						List<String> Loret = new ArrayList<String>();
						ItemStack glow = new ItemStack(UMaterial.GUNPOWDER.getMaterial(), num);
						ItemMeta glowMeta = glow.getItemMeta();
						glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
						Loret.add("§7This dust has magical properties");
						Loret.add("§7which make it a valuable currency.");
						glowMeta.setLore(Loret);
						glow.setItemMeta(glowMeta);

						if (player.getInventory().containsAtLeast(glow, num)) {
							player.getInventory().removeItem(glow);
							dest.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
							dest.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
							player.getInventory().addItem(dest);

						} else {

						}

					} catch (Exception ignored) {
						player.closeInventory();
					}
			}

			switch (event.getCurrentItem().getType()) {
				case DIAMOND_AXE:

					try {


						ItemStack slay = new ItemStack(UMaterial.DIAMOND_AXE.getMaterial(), 1);
						ItemMeta slayMeta = slay.getItemMeta();
						slayMeta.setDisplayName(ChatColor.RED + "The Slayer");
						slay.setItemMeta(slayMeta);
						int num = plugin.getConfig().getInt(ChatColor.stripColor("weapon." +
								slayMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
						List<String> Loret = new ArrayList<String>();
						ItemStack glow = new ItemStack(UMaterial.GUNPOWDER.getMaterial(), num);
						ItemMeta glowMeta = glow.getItemMeta();
						glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
						Loret.add("§7This dust has magical properties");
						Loret.add("§7which make it a valuable currency.");
						glowMeta.setLore(Loret);
						glow.setItemMeta(glowMeta);

						if (player.getInventory().containsAtLeast(glow, num)) {
							player.getInventory().removeItem(glow);
							slay.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 3);
							slay.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
							player.getInventory().addItem(slay);

						} else {

						}

					} catch (Exception ignored) {
						player.closeInventory();
					}
			}

			switch (event.getCurrentItem().getType()) {
				case STICK:

					try {

						List<String> Lore3 = new ArrayList<String>();
						ItemStack sig = new ItemStack(UMaterial.STICK.getMaterial(), 1);
						ItemMeta sigMeta = sig.getItemMeta();
						sigMeta.setDisplayName(ChatColor.RED + "The Dropper");
						Lore3.add("§75");
						sigMeta.setLore(Lore3);
						sig.setItemMeta(sigMeta);
						int num = plugin.getConfig().getInt(ChatColor.stripColor("weapon." +
								sigMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
						List<String> Loret = new ArrayList<String>();
						ItemStack glow = new ItemStack(UMaterial.GUNPOWDER.getMaterial(), num);
						ItemMeta glowMeta = glow.getItemMeta();
						glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
						Loret.add("§7This dust has magical properties");
						Loret.add("§7which make it a valuable currency.");
						glowMeta.setLore(Loret);
						glow.setItemMeta(glowMeta);

						if (player.getInventory().containsAtLeast(glow, num)) {
							player.getInventory().removeItem(glow);
							player.getInventory().addItem(sig);

						} else {

						}

					} catch (Exception ignored) {
						player.closeInventory();
					}
			}
			switch (event.getCurrentItem().getType()) {
				case BLAZE_ROD:

					try {

						List<String> Lore4 = new ArrayList<String>();
						ItemStack rod = new ItemStack(UMaterial.BLAZE_ROD.getMaterial(), 1);
						ItemMeta rodMeta = rod.getItemMeta();
						rodMeta.setDisplayName(ChatColor.RED + "Fireball Launcher");
						Lore4.add("§75");
						rodMeta.setLore(Lore4);
						rod.setItemMeta(rodMeta);
						int num = plugin.getConfig().getInt(ChatColor.stripColor("weapon." +
								rodMeta.getDisplayName().toLowerCase().replace(" ", "-") + ".cost"));
						List<String> Loret = new ArrayList<String>();
						ItemStack glow = new ItemStack(UMaterial.GUNPOWDER.getMaterial(), num);
						ItemMeta glowMeta = glow.getItemMeta();
						glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
						Loret.add("§7This dust has magical properties");
						Loret.add("§7which make it a valuable currency.");
						glowMeta.setLore(Loret);
						glow.setItemMeta(glowMeta);

						if (player.getInventory().containsAtLeast(glow, num)) {
							player.getInventory().removeItem(glow);
							player.getInventory().addItem(rod);

						} else {

							//Fireball Shooter
							//if(i == Material.BLAZE_ROD){
							//Location loc = p.getEyeLocation().toVector().add(p.getLocation().getDirection().multiply(2)).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch());
							//Fireball fireball = p.getWorld().spawn(loc, Fireball.class);
							//fireball.setIsIncendiary(false);
							//fireball.setFireTicks(0);
							//p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD));
							//ActionBarAPI.sendActionBar(p,"**§6§lFIREBALL§f**", 40);

						}

					} catch (Exception ignored) {
						player.closeInventory();
					}
			}
		}
	}
}


			 								



	

