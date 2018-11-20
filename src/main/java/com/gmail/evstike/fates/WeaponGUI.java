package com.gmail.evstike.fates;

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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WeaponGUI implements CommandExecutor, Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
	    if(commandLabel.equalsIgnoreCase("weapons")) {
	    	

	}
	        openGUI(player);
	    player.sendMessage(ChatColor.GOLD + "Opening Weapons Menu");
			return false;
	   
	}

	private void openGUI(Player player) {
	    Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "" + ChatColor.BOLD
	    		+ "Weapons Menu");
	    
      //Items
	    ItemStack dest = new ItemStack(Material.DIAMOND_SWORD);
	    ItemMeta destMeta = dest.getItemMeta();
	    ItemStack slay = new ItemStack(Material.DIAMOND_AXE);
	    ItemMeta slayMeta = slay.getItemMeta();

      //Item meta
	    destMeta.setDisplayName(ChatColor.RED + "The Destroyer");
	    destMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
	    destMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
	    dest.setItemMeta(destMeta);	    
	    slayMeta.setDisplayName(ChatColor.RED + "The Slayer");
		slayMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 3, true);
		slayMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
	    slay.setItemMeta(slayMeta);

	    
	  //Inventory set  
	    inv.setItem(0, dest);
	    inv.setItem(1, slay);

	    player.openInventory(inv);

	}


	@SuppressWarnings({ "incomplete-switch" })
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
	    if (!ChatColor.stripColor(event.getInventory().getName())
	            .equalsIgnoreCase("Weapons Menu"))
	        return;
	    Player player = (Player) event.getWhoClicked();
	    event.setCancelled(true);
	    
         
	    if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR||!event.getCurrentItem().hasItemMeta()){
	    	
	        return;
	    }
	switch (event.getCurrentItem().getType()) {
	case DIAMOND_SWORD:
    List<String> Lore = new ArrayList<String>();
	    ItemStack dest = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta destMeta = dest.getItemMeta();
		destMeta.setDisplayName(ChatColor.RED + "The Destroyer");
		Lore.add("§7Speed");
		destMeta.setLore(Lore);
		dest.setItemMeta(destMeta);
		dest.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		dest.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
		player.getInventory().addItem(dest);
		player.closeInventory();
			
		}
	
	switch (event.getCurrentItem().getType()) {
	case DIAMOND_AXE:
		 ItemStack slay = new ItemStack(Material.DIAMOND_AXE, 1);
			ItemMeta slayMeta = slay.getItemMeta();
			slayMeta.setDisplayName(ChatColor.RED + "The Slayer");
			slay.setItemMeta(slayMeta);
			slay.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 3);
			slay.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
			player.getInventory().addItem(slay);
			player.closeInventory();
		
	}
	}
}

	

