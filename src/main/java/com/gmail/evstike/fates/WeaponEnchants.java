package com.gmail.evstike.fates;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WeaponEnchants implements Listener {
	
	public WeaponEnchants(Fates plugin) {
	plugin.getServer().getPluginManager().registerEvents(this, plugin);	

	}
		@EventHandler
	public void onPlayerUse(PlayerInteractEvent event){
			Player p = (Player) event.getPlayer();
			
	ItemStack i = p.getInventory().getItemInMainHand();
    ItemMeta im = i.getItemMeta(); 
    
    if (i.getType() != Material.AIR){
    if (im.hasLore()){
    if (im.getLore().contains("§7Speed I")){
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 0));
 	}
    if (i.getType() != Material.AIR){
    if (im.hasLore()){
        if (im.getLore().contains("§7Speed II")){
          p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1));
        }
        if (i.getType() != Material.AIR){
            if (im.hasLore()){
            if (im.getLore().contains("§7Jump I")){
              p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 0));
            }
            if (i.getType() != Material.AIR){
                if (im.hasLore()){
                if (im.getLore().contains("§7Jump II")){
                  p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 1));
                }
            if (i.getType() != Material.AIR){
             if (im.hasLore()){
              if (im.getLore().contains("§7Haste I")){
              p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 60, 0));
                    }
              if (i.getType() != Material.AIR){
               if (im.hasLore()){
               if (im.getLore().contains("§7Haste II")){
               p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 60, 1));
        }
        else
        	return; {
        }
        
        }
    
}
    }
		}
		}
}
    }
    }
		}
}
}
}
}
}




		
  
		
	
    

        
		
