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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
				FileConfiguration cfg = YamlConfiguration.loadConfiguration(new File("customenchantments.yml"));
				player.sendMessage(ChatColor.GREEN+""+plugin.getCustomConfig().getString("test"));
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
	    Inventory inv = Bukkit.createInventory(null, 18, "Custom Enchantments");

	    ItemStack speed1 = new ItemStack(Material.BOOK);
	    ItemMeta speed1Meta = speed1.getItemMeta();        
	    ItemStack speed2 = new ItemStack(Material.BOOK);
	    ItemMeta speed2Meta = speed2.getItemMeta();
	    ItemStack speed3 = new ItemStack(Material.BOOK);
	    ItemMeta speed3Meta = speed3.getItemMeta();
	    
	    ItemStack jump1 = new ItemStack(Material.BOOK);
	    ItemMeta jump1Meta = jump1.getItemMeta();
	    ItemStack jump2 = new ItemStack(Material.BOOK);
	    ItemMeta jump2Meta = jump2.getItemMeta();
	    ItemStack jump3 = new ItemStack(Material.BOOK);
	    ItemMeta jump3Meta = jump3.getItemMeta();
	    
	    ItemStack haste1 = new ItemStack(Material.BOOK);
	    ItemMeta haste1Meta = haste1.getItemMeta();
	    ItemStack haste2 = new ItemStack(Material.BOOK);
	    ItemMeta haste2Meta = haste2.getItemMeta();
	    ItemStack haste3 = new ItemStack(Material.BOOK);
	    ItemMeta haste3Meta = haste3.getItemMeta();
	    
	    ItemStack poison1 = new ItemStack(Material.BOOK);
	    ItemMeta poison1Meta = poison1.getItemMeta();
	    ItemStack poison2 = new ItemStack(Material.BOOK);
	    ItemMeta poison2Meta = poison2.getItemMeta();
	    ItemStack poison3 = new ItemStack(Material.BOOK);
	    ItemMeta poison3Meta = poison3.getItemMeta();
	    
	    ItemStack end1 = new ItemStack(Material.BOOK);
	    ItemMeta end1Meta = end1.getItemMeta();
	    ItemStack end2 = new ItemStack(Material.BOOK);
	    ItemMeta end2Meta = end2.getItemMeta();
	    ItemStack end3 = new ItemStack(Material.BOOK);
	    ItemMeta end3Meta = end3.getItemMeta();
	    
	    ItemStack tol1 = new ItemStack(Material.BOOK);
	    ItemMeta tol1Meta = end1.getItemMeta();
	    ItemStack tol2 = new ItemStack(Material.BOOK);
	    ItemMeta tol2Meta = end2.getItemMeta();
	    ItemStack tol3 = new ItemStack(Material.BOOK);
	    ItemMeta tol3Meta = end3.getItemMeta();
	    
	    
	    List<String> Lore = new ArrayList<String>();
	    speed1Meta.setDisplayName(ChatColor.YELLOW + "Rush I");
	    int num = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    speed1Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
	    String speed1Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
	    speed1Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
	    Lore.add("§7"+StringUtils.capitalize(speed1Type)+" Enchantment");
	    Lore.add("§7Gives you a quick rush of speed.");
	    Lore.add("§7Speed I - 4s");
	    Lore.add("");
	    Lore.add("§b" + num +"x "+"§7DUST");
        speed1Meta.setLore(Lore);
	    speed1.setItemMeta(speed1Meta);	    
	    
	    List<String> Lore2 = new ArrayList<String>();
	    speed2Meta.setDisplayName(ChatColor.YELLOW + "Rush II");
	    int num2 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    speed2Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
	    String speed2Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
	    speed2Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
	    Lore2.add("§7"+StringUtils.capitalize(speed2Type)+" Enchantment");
	    Lore2.add("§7Gives you a quick rush of speed.");
	    Lore2.add("§7Speed I - 6s");
	    Lore2.add("");
	    Lore2.add("§b" + num2 +"x "+"§7DUST");
        speed2Meta.setLore(Lore2);
	    speed2.setItemMeta(speed2Meta);	
	    
	    List<String> Lore3 = new ArrayList<String>();
	    speed3Meta.setDisplayName(ChatColor.YELLOW + "Rush III");
	    int num3 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    speed3Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
	    String speed3Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
	    speed3Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
	    Lore3.add("§7"+StringUtils.capitalize(speed3Type)+" Enchantment");
	    Lore3.add("§7Gives you a quick rush of speed.");
	    Lore3.add("§7Speed II - 8s");
	    Lore3.add("");
	    Lore3.add("§b" + num3 +"x "+"§7DUST");
        speed3Meta.setLore(Lore3);
	    speed3.setItemMeta(speed3Meta);	    
	    
	    List<String> Lore4 = new ArrayList<String>();
	    jump1Meta.setDisplayName(ChatColor.YELLOW + "Bounce I");
	    int num4 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    jump1Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    	    
	    String jump1Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
	    jump1Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    	    
	    Lore4.add("§7"+StringUtils.capitalize(jump1Type)+" Enchantment");
	    Lore4.add("§7Lets you jump to new heights.");
	    Lore4.add("§7Jump I - 4s");
	    Lore4.add("");
	    Lore4.add("§b" + num4 +"x "+"§7DUST");
        jump1Meta.setLore(Lore4);
	    jump1.setItemMeta(jump1Meta);	 
	    
	    List<String> Lore5 = new ArrayList<String>();
	    jump2Meta.setDisplayName(ChatColor.YELLOW + "Bounce II");
	    int num5 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    jump2Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String jump2Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        jump2Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore5.add("§7"+StringUtils.capitalize(jump2Type)+" Enchantment");
	    Lore5.add("§7Lets you jump to new heights.");
	    Lore5.add("§7Jump I - 6s");
	    Lore5.add("");
	    Lore5.add("§b" + num5 +"x "+"§7DUST");
        jump2Meta.setLore(Lore5);
	    jump2.setItemMeta(jump2Meta);
	    
	    List<String> Lore6 = new ArrayList<String>();
	    jump3Meta.setDisplayName(ChatColor.YELLOW + "Bounce III");
	    int num6 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    jump3Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String jump3Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        jump3Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore6.add("§7"+StringUtils.capitalize(jump3Type)+" Enchantment");
	    Lore6.add("§7Lets you jump to new heights.");
	    Lore6.add("§7Jump II - 8s");
	    Lore6.add("");
	    Lore6.add("§b" + num6 +"x "+"§7DUST");
        jump3Meta.setLore(Lore6);
	    jump3.setItemMeta(jump3Meta);	 	
	    
	    List<String> Lore7 = new ArrayList<String>();
	    haste1Meta.setDisplayName(ChatColor.YELLOW + "Hyper I");
	    int num7 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    haste1Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String haste1Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        haste1Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore7.add("§7"+StringUtils.capitalize(haste1Type)+" Enchantment");
	    Lore7.add("§7Hit faster when hyper.");
	    Lore7.add("§7Haste I - 4s");
	    Lore7.add("");
	    Lore7.add("§b" + num7 +"x "+"§7DUST");
        haste1Meta.setLore(Lore7);
	    haste1.setItemMeta(haste1Meta);	  
	    
	    List<String> Lore8 = new ArrayList<String>();
	    haste2Meta.setDisplayName(ChatColor.YELLOW + "Hyper II");
	    int num8 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    haste2Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String haste2Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        haste2Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore8.add("§7"+StringUtils.capitalize(haste2Type)+" Enchantment");
	    Lore8.add("§7Hit faster when hyper.");
	    Lore8.add("§7Haste I - 6s");
	    Lore8.add("");
	    Lore8.add("§b" + num8 +"x "+ "§7DUST");
        haste2Meta.setLore(Lore8);
	    haste2.setItemMeta(haste2Meta);
	    
	    List<String> Lore9 = new ArrayList<String>();
	    haste3Meta.setDisplayName(ChatColor.YELLOW + "Hyper III");
	    int num9 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    haste3Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String haste3Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        haste3Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore9.add("§7"+StringUtils.capitalize(haste3Type)+" Enchantment");
	    Lore9.add("§7Hit faster when hyper.");
	    Lore9.add("§7Haste II - 8s");
	    Lore9.add("");
	    Lore9.add("§b" + num9 +"x "+"§7DUST");
        haste3Meta.setLore(Lore9);
	    haste3.setItemMeta(haste3Meta);
	    
	    List<String> Lore10 = new ArrayList<String>();
	    poison1Meta.setDisplayName(ChatColor.YELLOW + "Plague I");
	    int num10 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    poison1Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String poison1Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        poison1Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore10.add("§7"+StringUtils.capitalize(poison1Type)+" Enchantment");
	    Lore10.add("§7Inflicts poison on your enemies.");
	    Lore10.add("§7Poison I - 6s");
	    Lore10.add("");
	    Lore10.add("§b" + num10 +"x "+"§7DUST");
        poison1Meta.setLore(Lore10);
	    poison1.setItemMeta(poison1Meta);
	    
	    List<String> Lore11 = new ArrayList<String>();
	    poison2Meta.setDisplayName(ChatColor.YELLOW + "Plague II");
	    int num11 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    poison2Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String poison2Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        poison2Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore11.add("§7"+StringUtils.capitalize(poison2Type)+" Enchantment");
	    Lore11.add("§7Inflicts poison on your enemies.");
	    Lore11.add("§7Poison I - 8s");
	    Lore11.add("");
	    Lore11.add("§b" + num11 +"x "+"§7DUST");
        poison2Meta.setLore(Lore11);
	    poison2.setItemMeta(poison2Meta);
	    
	    List<String> Lore12 = new ArrayList<String>();
	    poison3Meta.setDisplayName(ChatColor.YELLOW + "Plague III");
	    int num12 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    poison3Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String poison3Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        poison3Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore12.add("§7"+StringUtils.capitalize(poison3Type)+" Enchantment");
	    Lore12.add("§7Inflicts poison on your enemies.");
	    Lore12.add("§7Poison II - 8s");
	    Lore12.add("");
	    Lore12.add("§b" + num12 +"x "+"§7DUST");
        poison3Meta.setLore(Lore12);
	    poison3.setItemMeta(poison3Meta);
	    
	    List<String> Lore13 = new ArrayList<String>();
	    end1Meta.setDisplayName(ChatColor.YELLOW + "End Ender I");
	    int num13 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    end1Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String end1Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        end1Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore13.add("§7"+StringUtils.capitalize(end1Type)+" Enchantment");
	    Lore13.add("§7Deals more damage to endermen");
	    Lore13.add("§7and endermites.");
	    Lore13.add("§7+1 damage");
	    Lore13.add("");
	    Lore13.add("§b" + num13 +"x "+"§7DUST");
	    end1Meta.setLore(Lore13);
        end1.setItemMeta(end1Meta);
	    
	    List<String> Lore14 = new ArrayList<String>();
	    end2Meta.setDisplayName(ChatColor.YELLOW + "End Ender II");
	    int num14 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    end2Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String end2Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        end2Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore14.add("§7"+StringUtils.capitalize(end2Type)+" Enchantment");
	    Lore14.add("§7Deals more damage to endermen");
	    Lore14.add("§7and endermites.");
	    Lore14.add("§7+1.5 damage");
	    Lore14.add("");
	    Lore14.add("§b" + num14 +"x "+"§7DUST");
	    end2Meta.setLore(Lore14);
        end2.setItemMeta(end2Meta);
        
	    List<String> Lore15 = new ArrayList<String>();
	    end3Meta.setDisplayName(ChatColor.YELLOW + "End Ender III");
	    int num15 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    end3Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String end3Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        end3Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore15.add("§7"+StringUtils.capitalize(end3Type)+" Enchantment");
	    Lore15.add("§7Deals more damage to endermen");
	    Lore15.add("§7and endermites.");
	    Lore15.add("§7+2 damage");
	    Lore15.add("");
	    Lore15.add("§b" + num15 +"x "+"§7DUST");
	    end3Meta.setLore(Lore15);
        end3.setItemMeta(end3Meta);
        
	    List<String> Lore16 = new ArrayList<String>();
	    tol1Meta.setDisplayName(ChatColor.YELLOW + "Tolerance I");
	    int num16 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    tol1Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String tol1Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        tol1Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore16.add("§7"+StringUtils.capitalize(tol1Type)+" Enchantment");
	    Lore16.add("§7Gives resistance after taking more");
	    Lore16.add("§7than a certain amount of damage.");
	    Lore16.add("§78+ damage required");
	    Lore16.add("");
	    Lore16.add("§b" + num16 +"x "+"§7DUST");
	    tol1Meta.setLore(Lore16);
        tol1.setItemMeta(tol1Meta);
        
	    List<String> Lore17 = new ArrayList<String>();
	    tol2Meta.setDisplayName(ChatColor.YELLOW + "Tolerance II");
	    int num17 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    tol2Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String tol2Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        tol2Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore17.add("§7"+StringUtils.capitalize(tol2Type)+" Enchantment");
	    Lore17.add("§7Gives resistance after taking more");
	    Lore17.add("§7than a certain amount of damage.");
	    Lore17.add("§78+ damage required");
	    Lore17.add("");
	    Lore17.add("§b" + num17 +"x "+"§7DUST");
	    tol2Meta.setLore(Lore17);
        tol2.setItemMeta(tol2Meta);
        
	    List<String> Lore18 = new ArrayList<String>();
	    tol3Meta.setDisplayName(ChatColor.YELLOW + "Tolerance III");
	    int num18 = plugin.getConfig().getInt(ChatColor.stripColor("enchant."+
	    tol3Meta.getDisplayName().toLowerCase().replace(" ","-")+".cost"));
	    
        String tol3Type = plugin.getConfig().getString(ChatColor.stripColor("enchant."+
        tol3Meta.getDisplayName().toLowerCase().replace(" ","-")+".type"));
	    
        Lore18.add("§7"+StringUtils.capitalize(tol3Type)+" Enchantment");
	    Lore18.add("§7Gives resistance after taking more");
	    Lore18.add("§7than a certain amount of damage.");
	    Lore18.add("§78+ damage required");
	    Lore18.add("");
	    Lore18.add("§b" + num18 +"x "+"§7DUST");
	    tol3Meta.setLore(Lore18);
        tol3.setItemMeta(tol3Meta);
	        
	    inv.setItem(0, speed1);
	    inv.setItem(1, speed2);
	    inv.setItem(2, speed3);
	    inv.setItem(3, jump1);
	    inv.setItem(4, jump2);
	    inv.setItem(5, jump3);
	    inv.setItem(6, haste1);
	    inv.setItem(7, haste2);
	    inv.setItem(8, haste3);
	    inv.setItem(9, poison1);
	    inv.setItem(10, poison2);
	    inv.setItem(11, poison3);
	    inv.setItem(12, end1);
	    inv.setItem(13, end2);
	    inv.setItem(14, end3);
	    inv.setItem(15, tol1);
	    inv.setItem(16, tol2);
	    inv.setItem(17, tol3);

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
		default:
			break;
	}
	}
}
	
		
		
					 								

