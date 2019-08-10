package com.gmail.evstike.AdvancedWeapons;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class DustGUI extends API implements CommandExecutor, Listener {

	Fates plugin;

	public DustGUI(Fates instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig()) == false) {
			if (cmd.getName().equalsIgnoreCase("dust")) {
				if (sender instanceof Player) {
					Player player = (Player) sender;
					openGUI(player);
					if (!plugin.getConfig().contains("dust." + player.getUniqueId())) {
						plugin.getConfig().createSection("dust." + player.getUniqueId());
						plugin.saveConfig();
						plugin.reloadConfig();
						return false;
					}
				}
				if (!(sender instanceof Player)) {
					sender.sendMessage("§cError: §4Only Players can use this command!");
					return true;
				}
			}
		}
		return false;
	}


	private ItemStack dust() {
		List<String> Lore = new ArrayList<String>();
		ItemStack glow = new ItemStack(UMaterial.GUNPOWDER.getMaterial(), 1);
		ItemMeta glowMeta = glow.getItemMeta();
		glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
		Lore.add("§7This dust has magical properties");
		Lore.add("§7which make it a valuable currency.");
		glowMeta.setLore(Lore);
		glow.setItemMeta(glowMeta);
		return glow;
	}

	private void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9, "Dust Bank");

		ItemStack gun = new ItemStack(UMaterial.GUNPOWDER.getMaterial());
		ItemMeta gunMeta = gun.getItemMeta();
		ItemStack glow = new ItemStack(UMaterial.SUNFLOWER.getMaterial());
		ItemMeta glowMeta = glow.getItemMeta();
		ItemStack chest = new ItemStack(UMaterial.CHEST.getMaterial());
		ItemMeta chestMeta = chest.getItemMeta();
		ItemStack blaze = new ItemStack(UMaterial.GLASS_BOTTLE.getMaterial());
		ItemMeta blazeMeta = blaze.getItemMeta();
		ItemStack con1 = new ItemStack(UMaterial.match(plugin.getConfig().getString("dust-material")).getMaterial());
		ItemMeta con1Meta = con1.getItemMeta();

		List<String> Lore = new ArrayList<String>();
		gunMeta.setDisplayName(ChatColor.GREEN + "Purchase Dust");
		int num = plugin.getConfig().getInt("dust-cost");
		Lore.add("§7This common dust has magical properties");
		Lore.add("§7which make it a valuable currency.");
		Lore.add("");
		Lore.add("§a" + num + " xp");
		gunMeta.setLore(Lore);
		gun.setItemMeta(gunMeta);

		List<String> Lore2 = new ArrayList<String>();
		glowMeta.setDisplayName(ChatColor.GREEN + "Withdraw Dust");
		Lore2.add("§7Withdraw dust from your vault in");
		Lore2.add("§7the bank of the realm down below.");
		Lore2.add("");
		Lore2.add("§bClick to withdraw 1 dust");
		glowMeta.setLore(Lore2);
		glow.setItemMeta(glowMeta);

		List<String> Lore3 = new ArrayList<String>();
		chestMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dust Balance");
		chest.setItemMeta(chestMeta);
		int num3 = plugin.getConfig().getInt("dust." + player.getUniqueId());
		Lore3.add("§7Check how much dust you stored in");
		Lore3.add("§7the bank of the realm down below.");
		Lore3.add("");
		Lore3.add("§7You have " + "§a" + num3 + " §7dust");
		chestMeta.setLore(Lore3);
		chest.setItemMeta(chestMeta);

		List<String> Lore4 = new ArrayList<String>();
		blazeMeta.setDisplayName(ChatColor.GREEN + "Deposit Dust");
		Lore4.add("§7Deposit dust to a safely locked vault");
		Lore4.add("§7in the bank of the realm down below.");
		Lore4.add("");
		Lore4.add("§bClick to deposit 1 dust");
		blazeMeta.setLore(Lore4);
		blaze.setItemMeta(blazeMeta);

		List<String> Lore5 = new ArrayList<String>();
		con1Meta.setDisplayName(ChatColor.GREEN + "Trade for Dust");
		int quant = plugin.getConfig().getInt("dust-trade");
		String dia = StringUtils.capitaliseAllWords(plugin.getConfig()
				.getString("dust-material").toLowerCase().replace("_", " "));
		Lore5.add(ChatColor.GRAY + "Convert §b" + dia + "s" + " §7to dust.");
		Lore5.add("");
		Lore5.add(ChatColor.AQUA + "" + quant + "x " + dia);
		con1Meta.setLore(Lore5);
		con1.setItemMeta(con1Meta);

		inv.setItem(0, gun);
		inv.setItem(2, con1);
		inv.setItem(4, glow);
		inv.setItem(6, blaze);
		inv.setItem(8, chest);

		player.openInventory(inv);
	}

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Dust Bank"))
			return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR
				|| !event.getCurrentItem().hasItemMeta()) {

			return;
		}
		if (event.getClickedInventory().getType().equals(InventoryType.CHEST)) {
			if (event.getRawSlot() == 0) {
				int num = plugin.getConfig().getInt("dust-cost");
				int xp = player.getTotalExperience();
				if (xp >= num) {
					player.getInventory().addItem(dust());
					player.giveExp(-num);
				}
				if (event.getRawSlot() == 0) {
					if (xp < num) {

						player.sendMessage(ChatColor.AQUA + "You have " + xp + "/" + num + " xp");
						player.closeInventory();
					}
				}
			}

			if (event.getRawSlot() == 6) {

				ItemStack chest = new ItemStack(Material.CHEST);
				ItemMeta chestMeta = chest.getItemMeta();
				List<String> Lore3 = new ArrayList<String>();
				chestMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dust Balance");
				chest.setItemMeta(chestMeta);
				int num3 = plugin.getConfig().getInt("dust." + player.getUniqueId());
				Lore3.add("§7Check how much dust you stored in");
				Lore3.add("§7the bank of the realm down below.");
				Lore3.add("");
				int nu = num3 + 1;
				Lore3.add("§7You have " + "§a" + nu + " §7dust");
				chestMeta.setLore(Lore3);
				chest.setItemMeta(chestMeta);
				event.getInventory().setItem(8, chest);

				if (!player.getInventory().containsAtLeast(dust(), 1)) {
					player.closeInventory();
					player.sendMessage(ChatColor.RED + "You have §bno §cdust.");
				}

				if (player.getInventory().containsAtLeast(dust(), 1)) {
					player.getInventory().removeItem(dust());
					player.sendMessage(ChatColor.GREEN + "You deposited §b1 §adust.");
					int num = plugin.getConfig().getInt("dust." + player.getUniqueId());
					plugin.getConfig().set("dust." + player.getUniqueId(), num + 1);
					plugin.saveConfig();
					plugin.reloadConfig();
					int nort = num + 1;
					player.sendMessage(ChatColor.GOLD + "Your current dust balance is §b" + nort + " §6dust.");

				}
			}

			if (event.getRawSlot() == 4) {

				ItemStack chest = new ItemStack(Material.CHEST);
				ItemMeta chestMeta = chest.getItemMeta();
				List<String> Lore3 = new ArrayList<String>();
				chestMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Dust Balance");
				chest.setItemMeta(chestMeta);
				int num3 = plugin.getConfig().getInt("dust." + player.getUniqueId());
				Lore3.add("§7Check how much dust you stored in");
				Lore3.add("§7the bank of the realm down below.");
				Lore3.add("");
				int nu = num3 - 1;
				Lore3.add("§7You have " + "§a" + nu + " §7dust");
				chestMeta.setLore(Lore3);
				chest.setItemMeta(chestMeta);
				event.getInventory().setItem(8, chest);

				int num = plugin.getConfig().getInt("dust." + player.getUniqueId());
				if (num > 0) {
					plugin.getConfig().set("dust." + player.getUniqueId(), num - 1);
					plugin.saveConfig();
					plugin.reloadConfig();
					player.getInventory().addItem(dust());
					player.sendMessage(ChatColor.GREEN + "You withdrew §b1 §adust.");
					int nert = num - 1;
					player.sendMessage(ChatColor.GOLD + "Your current dust balance is §b" + nert + " §6dust.");
				}

				if (num == 0) {
					player.sendMessage(ChatColor.RED + "You have §bno §cdust.");
					player.closeInventory();
				}
			}

			int slot = event.getRawSlot();
			if (slot == 2) {
				PlayerInventory held = player.getInventory();
				int quant = plugin.getConfig().getInt("dust-trade");
				ItemStack dia = new ItemStack(UMaterial.match(plugin.getConfig().getString("dust-material"))
						.getMaterial(), quant);
				if (held.containsAtLeast(dia, quant)) {
					held.removeItem(dia);
					player.getInventory().addItem(dust());
				}
			}
		}
	}
}
