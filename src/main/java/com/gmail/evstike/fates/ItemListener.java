package com.gmail.evstike.fates;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.ShulkerBullet;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

public class ItemListener implements Listener {
    Fates plugin;
    
	public ItemListener(Fates instance){
		plugin = instance;

	}
	
	//Weapon Effects
	@EventHandler
	public void onPlayerUse(PlayerInteractEvent event){
		Player p = (Player) event.getPlayer();
		Material i = p.getInventory().getItemInMainHand().getType();
		//if(plugin.getConfig().getString("weaponeffects").equals("true"))
		
	    if(i == Material.DIAMOND_SWORD || i == Material.IRON_SWORD || i == Material.STONE_SWORD){		
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 1));
        if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null);
        ActionBarAPI.sendActionBar(p,"**§b§lSPEED§f**", 80); 
        if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null);
        p.sendMessage("**§b§lSPEED§f**"); 	 
        
        }
		    if(i == Material.DIAMOND_AXE || i == Material.IRON_AXE || i == Material.STONE_AXE){
	        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1));
	        if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null)
	        ActionBarAPI.sendActionBar(p,"**§7§lRESISTANCE§f**", 100);
	        if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null)
	        p.sendMessage("**§7§lRESISTANCE§f**"); 	  
		        
		    }  
		    if(i == Material.DIAMOND_SHOVEL || i == Material.IRON_SHOVEL || i == Material.STONE_SHOVEL){
		        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 80, 1)); 
		        //if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") != null)
		        //ActionBarAPI.sendActionBar(p,"***§d§lHASTE§f***", 100);
		        //if(Bukkit.getServer().getPluginManager().getPlugin("ActionBarAPI") == null)
		        //p.sendMessage("**§d§lHASTE§f**"); 

		    }
		    if(i == Material.DIAMOND_HOE || i == Material.IRON_HOE || i == Material.STONE_HOE){
		    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 1));
		    ActionBarAPI.sendActionBar(p,"**§a§lJUMP BOOST§f**", 100);
		    
		    }
		    if(i == Material.DIAMOND_PICKAXE || i == Material.IRON_PICKAXE || i == Material.STONE_PICKAXE){
			    p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0));
			    ActionBarAPI.sendActionBar(p,"**§c§lSTRENGTH§f**", 100);  
			    
			    }
			    //Fireball Shooter
			    if(i == Material.BLAZE_ROD){
			    	Location loc = p.getEyeLocation().toVector().add(p.getLocation().getDirection().multiply(2)).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch());
			    	Fireball fireball = p.getWorld().spawn(loc, Fireball.class);
			    	fireball.setIsIncendiary(false);
			    	fireball.setFireTicks(0);
			    	p.getInventory().removeItem(new ItemStack(Material.BLAZE_ROD));
				    ActionBarAPI.sendActionBar(p,"**§6§lFIREBALL§f**", 40); 

			    }  	
			    //Shulker bullet shooter
			    if(i == Material.STICK){
			    	Location loc = p.getEyeLocation().toVector().add(p.getLocation().getDirection().multiply(4)).toLocation(p.getWorld(), p.getLocation().getYaw(), p.getLocation().getPitch());
			    	ShulkerBullet bullet = p.getWorld().spawn(loc, ShulkerBullet.class);
			    	bullet.setShooter(p);
			    	p.getInventory().removeItem(new ItemStack(Material.STICK));
				    ActionBarAPI.sendActionBar(p,"**§d§lBULLET§f**", 40); 
			    }
				    else if(i == null);
			    if(plugin.getConfig().getString("weaponeffects").equals("false"));
				    	return;
		}
			    
			    
			    

	}



	   

		   

		




	


	