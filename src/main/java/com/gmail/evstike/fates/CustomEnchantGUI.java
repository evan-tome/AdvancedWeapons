package com.gmail.evstike.fates;

import java.util.ArrayList;
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

public class CustomEnchantGUI implements CommandExecutor, Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
	    if(commandLabel.equalsIgnoreCase("ce")) {
	    	

	}
	        openGUI(player);
	        player.sendMessage(ChatColor.GOLD + "Opening the Custom Enchantment Menu");
			return false;
	   
	}

	private void openGUI(Player player) {
	    Inventory inv = Bukkit.createInventory(null, 18, ChatColor.YELLOW + "" + ChatColor.BOLD
	    		+ "Custom Enchantments");

	    ItemStack speed1 = new ItemStack(Material.BOOK);
	    ItemMeta speed1Meta = speed1.getItemMeta();
	    ItemStack speed2 = new ItemStack(Material.BOOK);
	    ItemMeta speed2Meta = speed2.getItemMeta();
	    
	    ItemStack jump1 = new ItemStack(Material.BOOK);
	    ItemMeta jump1Meta = speed1.getItemMeta();
	    ItemStack jump2 = new ItemStack(Material.BOOK);
	    ItemMeta jump2Meta = speed2.getItemMeta();
	    
	    ItemStack haste1 = new ItemStack(Material.BOOK);
	    ItemMeta haste1Meta = haste1.getItemMeta();
	    ItemStack haste2 = new ItemStack(Material.BOOK);
	    ItemMeta haste2Meta = haste2.getItemMeta();
	    
        
	    speed1Meta.setDisplayName(ChatColor.YELLOW + "Speed I");
	    speed1.setItemMeta(speed1Meta);
	    
	    speed2Meta.setDisplayName(ChatColor.YELLOW + "Speed II");
	    speed2.setItemMeta(speed2Meta);

	    jump1Meta.setDisplayName(ChatColor.YELLOW + "Jump I");
	    jump1.setItemMeta(jump1Meta);
	    
	    jump2Meta.setDisplayName(ChatColor.YELLOW + "Jump II");
	    jump2.setItemMeta(jump2Meta);
	    
	    haste1Meta.setDisplayName(ChatColor.YELLOW + "Haste I");
	    haste1.setItemMeta(haste1Meta);
	    
	    haste2Meta.setDisplayName(ChatColor.YELLOW + "Haste II");
	    haste2.setItemMeta(haste2Meta);
	    
	        
	    inv.setItem(0, speed1);
	    inv.setItem(1, speed2);
	    inv.setItem(2, jump1);
	    inv.setItem(3, jump2);
	    inv.setItem(4, haste1);
	    inv.setItem(5, haste2);

	    player.openInventory(inv);

	}


	@SuppressWarnings({ "incomplete-switch" })
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
	    if (!ChatColor.stripColor(event.getInventory().getName())
	            .equalsIgnoreCase("Custom Enchantments"))
	        return;
	    Player player = (Player) event.getWhoClicked();
	    event.setCancelled(true);
	    
         
	    if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR||!event.getCurrentItem().hasItemMeta()){
	    	
	        return;
	    }
		switch (event.getCurrentItem().getType()) {
		case BOOK:
		}
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Speed I")){
			       try{
				 ArrayList<String> Lore = new ArrayList<String>();
				 ItemStack speed1 = player.getInventory().getItemInMainHand();
				ItemMeta speed1Meta = speed1.getItemMeta();
				    
				   if(speed1Meta.hasLore()){
				      
				      for(String s : speed1Meta.getLore()){
				        Lore.add(s);
				      }
					Lore.add("§7Speed I");
					speed1Meta.setLore(Lore);
					speed1.setItemMeta(speed1Meta);
					player.closeInventory();
					  }else{
					      Lore.add("§7Speed I");
					      speed1Meta.setLore(Lore);
					      speed1.setItemMeta(speed1Meta);
					      player.closeInventory();
					  }
				   
				       }catch(Exception ignored){
						      player.closeInventory();
				       }
			}
		if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Speed II")){
		       try{
			 ArrayList<String> Lore2 = new ArrayList<String>();
			 ItemStack speed2 = player.getInventory().getItemInMainHand();
			ItemMeta speed2Meta = speed2.getItemMeta();
			   if(speed2Meta.hasLore()){
			      
			      for(String s : speed2Meta.getLore()){
			        Lore2.add(s);
			      }
				Lore2.add("§7Speed II");
				speed2Meta.setLore(Lore2);
				speed2.setItemMeta(speed2Meta);
				player.closeInventory();
			  }else{
			      Lore2.add("§7Speed II");
			      speed2Meta.setLore(Lore2);
			      speed2.setItemMeta(speed2Meta);
			      player.closeInventory();
			  }
		       }catch(Exception ignored){
				      player.closeInventory();
			  }
		}
				if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Jump I")){
				       try{
					 ArrayList<String> Lore3 = new ArrayList<String>();
					 ItemStack jump1 = player.getInventory().getItemInMainHand();
					ItemMeta jump1Meta = jump1.getItemMeta();
					   if(jump1Meta.hasLore()){
					      
					      for(String s : jump1Meta.getLore()){
					        Lore3.add(s);
					      }
						Lore3.add("§7Jump I");
						jump1Meta.setLore(Lore3);
						jump1.setItemMeta(jump1Meta);
						player.closeInventory();
					  }else{
					      Lore3.add("§7Jump I");
					      jump1Meta.setLore(Lore3);
					      jump1.setItemMeta(jump1Meta);
					      player.closeInventory();
					  }
				       
	       }catch(Exception ignored){
			      player.closeInventory();
	       }
				}
					if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Jump II")){
					       try{
						 ArrayList<String> Lore4 = new ArrayList<String>();
						 ItemStack jump2 = player.getInventory().getItemInMainHand();
						ItemMeta jump2Meta = jump2.getItemMeta();
						   if(jump2Meta.hasLore()){
						      
						      for(String s : jump2Meta.getLore()){
						        Lore4.add(s);
						      }
							Lore4.add("§7Jump II");
							jump2Meta.setLore(Lore4);
							jump2.setItemMeta(jump2Meta);
							player.closeInventory();
						  }else{
						      Lore4.add("§7Jump II");
						      jump2Meta.setLore(Lore4);
						      jump2.setItemMeta(jump2Meta);
						      player.closeInventory();
						  }
		       }catch(Exception ignored){
				      player.closeInventory();
		       }
					}
						if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Haste I")){
						       try{
							 ArrayList<String> Lore5 = new ArrayList<String>();
							 ItemStack haste1 = player.getInventory().getItemInMainHand();
							ItemMeta haste1Meta = haste1.getItemMeta();
							   if(haste1Meta.hasLore()){
							      
							      for(String s : haste1Meta.getLore()){
							        Lore5.add(s);
							      }
								Lore5.add("§7Haste I");
								haste1Meta.setLore(Lore5);
								haste1.setItemMeta(haste1Meta);
								player.closeInventory();
							  }else{
							      Lore5.add("§7Haste I");
							      haste1Meta.setLore(Lore5);
							      haste1.setItemMeta(haste1Meta);
							      player.closeInventory();
							  }
			       }catch(Exception ignored){
					      player.closeInventory();
			       }
						}
							if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Haste II")){
							       try{
								 ArrayList<String> Lore6 = new ArrayList<String>();
								 ItemStack haste2 = player.getInventory().getItemInMainHand();
								ItemMeta haste2Meta = haste2.getItemMeta();
								   if(haste2Meta.hasLore()){
									   if(haste2.getType()==Material.DIAMOND_SWORD){
								      
								      for(String s : haste2Meta.getLore()){
								        Lore6.add(s);
								      }
									Lore6.add("§7Haste II");
									haste2Meta.setLore(Lore6);
									haste2.setItemMeta(haste2Meta);
									player.closeInventory();
								  }else{
								      Lore6.add("§7Haste II");
								      haste2Meta.setLore(Lore6);
								      haste2.setItemMeta(haste2Meta);
								      player.closeInventory();
								  }
								   }								   
				       }catch(Exception ignored){
						      player.closeInventory();
			


	}

							       }
}
}



	
