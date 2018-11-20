package com.gmail.evstike.fates;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Commands implements CommandExecutor {
	
	Fates plugin; 
	public Commands(Fates instance) {
    plugin = instance;
	}

	

	    @SuppressWarnings("deprecation")
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {  
	    if(commandLabel.equalsIgnoreCase("advancedweapons")) {
	       		    	
	    	CommandSender player = (Player) sender;
	     	player.sendMessage(ChatColor.GOLD + ("AdvancedWeapons version beta-0.4 by miner328"));
	     	return true;   	     
	     		 
	    }
	         if(commandLabel.equalsIgnoreCase("ignite")) {
	     	        
	     	        if (args.length != 1) {
	     	        	sender.sendMessage("§cError: §4Please specify a player.");
	     	           
	     	            return false;

	     	        }
	     	            
	     	           Player target = Bukkit.getServer().getPlayer(args[0]);
	     	          if (target == null) {
	     	             sender.sendMessage("§cError: §4Player: " + args[0] + " not found.");
	     	             return true;
	     	         }
	     	         
	     	        target.setFireTicks(1000);
	     	        sender.sendMessage(ChatColor.RED + args[0] + " §6has been ignited");
	     	        target.sendMessage("§6You have been §cignited.");
	     	        System.out.println("[AdvancedWeapons] " + args[0] + " has been ignited");
	     	        return true; 
	         }
	     	    
	         if(commandLabel.equalsIgnoreCase("hideme")) {
	     	        if (!(sender instanceof Player)) {
	     	            sender.sendMessage("§cError: §4Only Players can use this command!");
	     	            return true;
	     	        }
	     	       if (args.length != 1) {
	         	        	sender.sendMessage("§cError: §4Please specify a player.");
	         	        	return false;
	     	        }
	     	  
	     	        Player s = (Player) sender;

	     	        
	     	        Player target = Bukkit.getServer().getPlayer(args[0]);
	     	        if (target == null) {
	     	            sender.sendMessage("§cError: §4Player: " + args[0] + " not found.");
	     	            return true;
	     	        }
	     	        
	     	        target.hidePlayer(s);
	     	        sender.sendMessage("§6You have been hidden from: " + args[0]);
	     	        return true;
	     	    }
	     	   
	         if(commandLabel.equalsIgnoreCase("showme")) {
	   	        if (!(sender instanceof Player)) {
	   	            sender.sendMessage("§cError: §4Only Players can use this command!");
	   	            return true;
	   	        }
	   	     if (args.length != 1) {
	     	        	sender.sendMessage("§cError: §4Please specify a player.");
	     	        	return false;
	   	        }
	   	  
	   	        Player s = (Player) sender;

	   	        
	   	        Player target = Bukkit.getServer().getPlayer(args[0]);	   	       
	   	        if (target == null) {
	   	            sender.sendMessage("§cError: §4Player: " + args[0] + " not found.");
	   	            return true;
	   	        }
	   	        
	   	        target.showPlayer(s);
	   	        sender.sendMessage("§6You have been shown to: " + args[0]);
	   	        return true;
	         }
	         if(commandLabel.equalsIgnoreCase("settarget")) {
		   	        if (!(sender instanceof Player)) {
		   	            sender.sendMessage("§cError: §4Only Players can use this command!");
		   	            return true;
		   	        }
		   	     if (args.length != 1) {
		     	        	sender.sendMessage("§cError: §4/settarget {player}.");
		     	        	return false;
		   	        }
		   	        if (args[0]==null) {
		   	            sender.sendMessage("§cError: §4Player: " + args[0] + " not found.");
		   	            return false;
	   	        }
		   	  
		   	        Player s = (Player) sender;

		   	        
		   	        Player target = Bukkit.getServer().getPlayer(args[0]);	   	       
		   	        if (target == null) {
		   	            sender.sendMessage("§cError: §4Player: " + args[0] + " not found.");
		   	            return true;
		   	        }
		   	        target.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100000, 1));
		   	        s.sendMessage("§6You have set: " + args[0] + "as a target.");
		   	        return true;
	         }
	         if(commandLabel.equalsIgnoreCase("removetarget")) {
		   	        if (!(sender instanceof Player)) {
		   	            sender.sendMessage("§cError: §4Only Players can use this command!");
		   	            return true;
		   	        }
		   	     if (args.length != 1) {
		     	        	sender.sendMessage("§cError: §4/settarget {player}.");
		     	        	return false;
		   	        }
		   	        if (args[0]==null) {
		   	            sender.sendMessage("§cError: §4Player: " + args[0] + " not found.");
		   	            return false;
	   	        }
		   	  
		   	        Player s = (Player) sender;

		   	        
		   	        Player target = Bukkit.getServer().getPlayer(args[0]);	   	       
		   	        if (target == null) {
		   	            sender.sendMessage("§cError: §4Player: " + args[0] + " not found.");
		   	            return true;
		   	        }
		   	        target.removePotionEffect(PotionEffectType.GLOWING);
		   	        s.sendMessage("§6You have removed: " + args[0] + "as a target.");
		   	        return true;
    }
			return false;
          }
      }

	         		    

 
