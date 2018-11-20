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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DustGUI implements CommandExecutor, Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
	    if(commandLabel.equalsIgnoreCase("dust")) {
	    	

	}
	        openGUI(player);
	    player.sendMessage(ChatColor.GOLD + "Opening Dust Menu");
			return false;
	   
	}

	private void openGUI(Player player) {
	    Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "" + ChatColor.BOLD
	    		+ "Dust Menu");

	    ItemStack glow = new ItemStack(Material.GLOWSTONE_DUST);
	    ItemMeta glowMeta = glow.getItemMeta();
	    ItemStack slay = new ItemStack(Material.DIAMOND_AXE);
	    ItemMeta slayMeta = slay.getItemMeta();


	    glowMeta.setDisplayName(ChatColor.RED + "The Destroyer");
	    glow.setItemMeta(glowMeta);	    
	    slayMeta.setDisplayName(ChatColor.RED + "The Slayer");
	    slay.setItemMeta(slayMeta);

	    
	        
	    inv.setItem(0, glow);
	    inv.setItem(1, slay);

	    player.openInventory(inv);

	}


	@SuppressWarnings({ "incomplete-switch" })
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
	    if (!ChatColor.stripColor(event.getInventory().getName())
	            .equalsIgnoreCase("Dust Menu"))
	        return;
	    Player player = (Player) event.getWhoClicked();
	    event.setCancelled(true);
	    
         
	    if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR||!event.getCurrentItem().hasItemMeta()){
	    	
	        return;
	    }
	switch (event.getCurrentItem().getType()) {
	case GLOWSTONE_DUST:
	    @SuppressWarnings("unused") List<String> Lore = new ArrayList<String>();
	    ItemStack glow = new ItemStack(Material.GLOWSTONE_DUST, 1);
		ItemMeta glowMeta = glow.getItemMeta();
		glowMeta.setDisplayName(ChatColor.YELLOW + "Glowstone");
		glowMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		glowMeta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);
		glowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		glow.setItemMeta(glowMeta);
		player.getInventory().addItem(glow);
		player.closeInventory();
			
		}
	
	switch (event.getCurrentItem().getType()) {
	case DIAMOND_AXE:
	    @SuppressWarnings("unused") List<String> Lore = new ArrayList<String>();
		 ItemStack slay = new ItemStack(Material.DIAMOND_AXE, 1);
			ItemMeta slayMeta = slay.getItemMeta();
			slayMeta.setDisplayName(ChatColor.RED + "The Slayer");
			slay.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 3);
			slay.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
			slay.setItemMeta(slayMeta);
			player.getInventory().addItem(slay);
			player.closeInventory();
		
	}	    
	}
	}
	
