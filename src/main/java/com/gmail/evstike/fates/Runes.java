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

public class Runes implements Listener {
	
	public Runes(Fates plugin) {
	plugin.getServer().getPluginManager().registerEvents(this, plugin);	

	}
		@EventHandler
	public void onPlayerUse(PlayerInteractEvent event){
			Player p = (Player) event.getPlayer();
			
	ItemStack i = p.getInventory().getItemInMainHand();
    ItemMeta im = i.getItemMeta(); 
    
    if (i.getType() != Material.AIR){
    if (im.hasLore()){
    if (im.getLore().contains("§7Speed 1")){
      p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 0));
 	}
    if (i.getType() != Material.AIR){
    if (im.hasLore()){
        if (im.getLore().contains("§7Speed 2")){
          p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1));
        }
        if (i.getType() != Material.AIR){
            if (im.hasLore()){
            if (im.getLore().contains("§7Jump 1")){
              p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 0));
            }
            if (i.getType() != Material.AIR){
                if (im.hasLore()){
                if (im.getLore().contains("§7Jump 2")){
                  p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 1));
                }
            if (i.getType() != Material.AIR){
             if (im.hasLore()){
              if (im.getLore().contains("§7Haste 1")){
              p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 60, 0));
                    }
              if (i.getType() != Material.AIR){
               if (im.hasLore()){
               if (im.getLore().contains("§7Haste 2")){
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