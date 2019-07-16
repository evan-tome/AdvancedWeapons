package com.gmail.evstike.AdvancedWeapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Coinflip implements CommandExecutor, Listener {

	Fates plugin;

	public Coinflip(Fates instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("coinflip")) {
			if (!(sender instanceof Player)) {

				sender.sendMessage("§cError: §4Only Players can use this command!");
				return true;
			}
			if (args.length != 1) {
				sender.sendMessage("§cError: §4Please specify a player.");
				return false;
			}
			if (args[0] == null) {
				sender.sendMessage("§cError: §4Player " + args[0] + " not found.");
				return false;

			}
			// Random rand = new Random();
			// int n = rand.nextInt(2) + 1;

			@SuppressWarnings("deprecation")
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null) {
				sender.sendMessage("§cError: §4Player " + args[0] + " not found.");
				return true;
				// }
				// if (target==sender) {
				// sender.sendMessage("§cError: §4You cannot coinflip with
				// yourself.");
				// return false;
			}
			sender.sendMessage("§6You have sent a coinflip request to §a" + args[0] + ".");
			sender.sendMessage("§6You are Heads");
			target.sendMessage("§6You have received a coinflip request from §a" + sender.getName() + ".");
			target.sendMessage("§6You are Tails");
			plugin.getConfig().set("coinflip.challenger", sender.getName());
			plugin.saveConfig();
			plugin.reloadConfig();
			openGUI(target);
			return true;
		}
		return false;
	}

	private void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9, "Coinflip");

		String d = plugin.getConfig().getString("coinflip.challenger");
		@SuppressWarnings("deprecation")
		Player challenger = Bukkit.getPlayer(d);
		ItemStack yes = new ItemStack(UMaterial.LIME_DYE.getItemStack());
		ItemMeta yesMeta = yes.getItemMeta();
		List<String> Lore = new ArrayList<String>();

		ItemStack no = new ItemStack(UMaterial.RED_DYE.getItemStack());
		ItemMeta noMeta = no.getItemMeta();

		List<String> Lore3 = new ArrayList<String>();
			ItemStack stats = new ItemStack(UMaterial.PLAYER_HEAD_ITEM.getItemStack());
			SkullMeta statsMeta = (SkullMeta) stats.getItemMeta();
			statsMeta.setDisplayName(ChatColor.GOLD + "Your Stats");
			Lore3.add("§7" + plugin.getConfig().getInt("coinflip." + player.getName()) + " wins");
			statsMeta.setLore(Lore3);
			statsMeta.setOwningPlayer(player);
			stats.setItemMeta(statsMeta);
			inv.setItem(0, stats);

		yesMeta.setDisplayName(ChatColor.GREEN + "Accept Coinflip");
		Lore.add("§7Click to start your coinflip");
		Lore.add("§7duel with " + challenger.getName());
		yesMeta.setLore(Lore);
		yes.setItemMeta(yesMeta);

		List<String> Lore2 = new ArrayList<String>();
		noMeta.setDisplayName(ChatColor.RED + "Cancel Coinflip");
		Lore.add("§7Click to cancel your coinflip");
		Lore.add("§7duel with " + challenger.getName());
		noMeta.setLore(Lore2);
		no.setItemMeta(noMeta);

		inv.setItem(2, yes);
		inv.setItem(6, no);

		player.openInventory(inv);
	}

	@SuppressWarnings({ "deprecation" })
	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (!ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("Coinflip"))
			return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);

		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR
				|| !event.getCurrentItem().hasItemMeta()) {

			return;
		}
		if (event.getRawSlot() == 2) {
			String d = plugin.getConfig().getString("coinflip.challenger");
			Player d2 = Bukkit.getPlayer(d);
			Random rand = new Random();
			int n = rand.nextInt(2) + 1;
			player.closeInventory();
			d2.sendMessage(ChatColor.GREEN + player.getName() + " accepted the coinflip.");
			if (n == 1) {
				d2.sendMessage(ChatColor.GOLD + "Rolled §aHeads [You].");
				player.sendMessage(ChatColor.GOLD + "Rolled §cHeads.");
				d2.sendMessage(ChatColor.GREEN + "You won the coinflip match.");
				player.sendMessage(ChatColor.RED + "You lost the coinflip match.");
				if (!plugin.getConfig().contains("coinflip." + d2.getName())) {
					plugin.getConfig().set("coinflip." + d2.getName(), "1");
					plugin.saveConfig();
					plugin.reloadConfig();
				}
				if (plugin.getConfig().contains("coinflip." + d2.getName())) {
					int win = plugin.getConfig().getInt("coinflip." + d2.getName());
					plugin.getConfig().set("coinflip." + d2.getName(), win + 1);
					plugin.saveConfig();
					plugin.reloadConfig();
				}
			}
			if (n == 2) {
				d2.sendMessage(ChatColor.GOLD + "Rolled §cTails.");
				player.sendMessage(ChatColor.GOLD + "Rolled §aTails [You].");
				player.sendMessage(ChatColor.GREEN + "You won the coinflip match.");
				d2.sendMessage(ChatColor.RED + "You lost the coinflip match.");
				if (!plugin.getConfig().contains("coinflip." + player.getName())) {
					int win = plugin.getConfig().getInt("coinflip." + player.getName());
					plugin.getConfig().set("coinflip." + player.getName(), win + 1);
					plugin.saveConfig();
					plugin.reloadConfig();
				}
				if (plugin.getConfig().contains("coinflip." + player.getName())) {
					int win = plugin.getConfig().getInt("coinflip." + player.getName());
					plugin.getConfig().set("coinflip." + player.getName(), win + 1);
					plugin.saveConfig();
					plugin.reloadConfig();
				}
			}
		}
		if (event.getRawSlot() == 6) {
			String d = plugin.getConfig().getString("coinflip.challenger");
			Player d2 = Bukkit.getPlayer(d);
			player.closeInventory();
			player.sendMessage(ChatColor.RED + "You cancelled the coinflip.");
			d2.sendMessage(ChatColor.RED + player.getName() + " has cancelled the coinflip.");
			plugin.saveConfig();
			plugin.reloadConfig();
		}
	}
}
