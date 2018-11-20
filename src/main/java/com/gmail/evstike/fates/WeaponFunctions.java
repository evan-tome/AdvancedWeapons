package com.gmail.evstike.fates;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WeaponFunctions implements Listener {
	
	public WeaponFunctions(Fates plugin) {
	plugin.getServer().getPluginManager().registerEvents(this, plugin);	

	}
		@SuppressWarnings({ "deprecation" })
		@EventHandler
	public void onPlayerUse(PlayerInteractEvent event){
			Player p = (Player) event.getPlayer();
			
	ItemStack i = p.getInventory().getItemInMainHand();
    ItemMeta im = i.getItemMeta(); 
    if (i.getType().equals(Material.DIAMOND_SWORD)){
   
    if (im.getDisplayName().equals(ChatColor.RED + "The Destroyer")){
     p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
    }
    else
    return; {
	}
    }
}
}





		
  
		
	
    

        
		

		
		





    

