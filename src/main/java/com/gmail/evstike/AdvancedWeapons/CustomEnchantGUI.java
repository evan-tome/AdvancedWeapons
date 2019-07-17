package com.gmail.evstike.AdvancedWeapons;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@SuppressWarnings("deprecation")
public class CustomEnchantGUI implements CommandExecutor, Listener {
	
	Fates plugin;
	public CustomEnchantGUI(Fates instance) {
	plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ce")) {
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
		return false;
	}

	private void openGUI(Player player) {
	    Inventory inv = Bukkit.createInventory(null, 27, "Custom Enchantments");

		ConfigurationSection section = plugin.getConfig().getConfigurationSection("enchant");
		for (String key : section.getKeys(false)) {
			section.get(key);
			ConfigurationSection item = section.getConfigurationSection(key);
			List<String> Lore0 = new ArrayList<String>();
			ItemStack ce = new ItemStack(UMaterial.BOOK.getItemStack());
			ItemMeta ceM = ce.getItemMeta();
			ceM.setDisplayName(item.getString("name".replace("&", "§")));
            ce.setItemMeta(ceM);

			int num = item.getInt("cost");
			String speed1Type = item.getString("type");

			Lore0.add("§7" + StringUtils.capitalize(speed1Type) + " Enchantment");
			for (String line : item.getStringList("lore")) {
				Lore0.add(line.replace("&", "§"));
			}
			Lore0.add("");
			Lore0.add("§b" + num +"x "+"§7DUST");
			ceM.setLore(Lore0);
			ce.setItemMeta(ceM);
			inv.addItem(ce);
		}

	    player.openInventory(inv);

	}
	
	@EventHandler	
	public void onInventoryClick(InventoryClickEvent event) {
	    if (!ChatColor.stripColor(event.getView().getTitle())
	            .equalsIgnoreCase("Custom Enchantments"))
	        return;
	    Player player = (Player) event.getWhoClicked();
	    event.setCancelled(true);
	    
         
	    if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR||!event.getCurrentItem().hasItemMeta()){
	    	
	        return;
	    }
	
	   	    
		switch (event.getCurrentItem().getType()) {
		case BOOK:	
			
			       try{
			     ItemMeta speed1M = event.getCurrentItem().getItemMeta();
				 ItemStack speed1 = player.getInventory().getItemInHand();
				ItemMeta speed1Meta = speed1.getItemMeta();
				int num = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
				speed1M.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
				
			    List<String> Lores = new ArrayList<String>();
			    ItemStack glow = new ItemStack(UMaterial.GUNPOWDER.getMaterial(),num);
			    ItemMeta glowMeta = glow.getItemMeta();
				glowMeta.setDisplayName(ChatColor.GREEN + "Dust");
			    Lores.add("§7This dust has magical properties");
			    Lores.add("§7which make it a valuable currency.");
			    glowMeta.setLore(Lores);
				glow.setItemMeta(glowMeta);
				
				 ArrayList<String> Lore = new ArrayList<String>();			       
						   if(speed1Meta.hasLore()){							   
							   for(String s : speed1Meta.getLore()){	
								   Lore.add(s);
									player.closeInventory();
							   }	
						   }
							if(plugin.getConfig().getString(ChatColor.stripColor("enchant."+
						   speed1M.getDisplayName().toLowerCase().replace(" ","-")+".type")).equals("weapon")){
							if(player.getInventory().getItemInHand().getType().toString().toLowerCase()
							.contains("sword")||player.getInventory().getItemInHand().getType().toString().toLowerCase()
							.contains("_axe")){				   	   
							if(player.getInventory().containsAtLeast(glow,num)){	
			                player.getInventory().removeItem(glow);		
						    Lore.add(ChatColor.GRAY+ChatColor.stripColor(speed1M.getDisplayName()));
							speed1Meta.setLore(Lore);
							speed1.setItemMeta(speed1Meta);
							player.closeInventory();
							player.sendMessage("§7Enchanted your §b"+speed1.getType()+" §7with §7"
							+ChatColor.stripColor(speed1M.getDisplayName()));		
							}
							}
							}
							if(plugin.getConfig().getString(ChatColor.stripColor("enchant."+
									   speed1M.getDisplayName().toLowerCase().replace(" ","-")+".type")).equals("armor")){
										if(player.getInventory().getItemInHand().getType().toString().toLowerCase()
										.contains("helmet")||player.getInventory().getItemInHand().getType().toString().toLowerCase()
										.contains("chestplate")||player.getInventory().getItemInHand().getType().toString().toLowerCase()
										.contains("leggings")||player.getInventory().getItemInHand().getType().toString().toLowerCase()
										.contains("boots")){				   	   
										if(player.getInventory().containsAtLeast(glow,num)){	
						                player.getInventory().removeItem(glow);		
									    Lore.add(ChatColor.GRAY+ChatColor.stripColor(speed1M.getDisplayName()));
										speed1Meta.setLore(Lore);
										speed1.setItemMeta(speed1Meta);
										player.closeInventory();
										player.sendMessage("§7Enchanted your §b"+speed1.getType()+" §7with §7"
										+ChatColor.stripColor(speed1M.getDisplayName()));	
										}
										}
							            }
									if(plugin.getConfig().getString(ChatColor.stripColor("enchant."+
									speed1M.getDisplayName().toLowerCase().replace(" ","-")+".type")).equals("tool")){
									if(player.getInventory().getItemInHand().getType().toString().toLowerCase()
									.contains("pickaxe")||player.getInventory().getItemInHand().getType().toString().toLowerCase()
									.contains("_axe")||player.getInventory().getItemInHand().getType().toString().toLowerCase()
									.contains("shovel")||player.getInventory().getItemInHand().getType().toString().toLowerCase()
									.contains("shears")){				   	   
									if(player.getInventory().containsAtLeast(glow,num)){	
									player.getInventory().removeItem(glow);		
									Lore.add(ChatColor.GRAY+ChatColor.stripColor(speed1M.getDisplayName()));
									speed1Meta.setLore(Lore);
									speed1.setItemMeta(speed1Meta);
									player.closeInventory();
									player.sendMessage("§7Enchanted your §b"+speed1.getType()+" §7with §7"
									+ChatColor.stripColor(speed1M.getDisplayName()));	
									}
									}	
							
												
					  }else{			      					      	
					      					      
							}													
						   				   
				       }catch(Exception ignored){
						      				       			    							 
				       	       
		
	}
	}
	}
}
	
		
		
					 								

