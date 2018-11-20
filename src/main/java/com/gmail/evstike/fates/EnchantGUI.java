package com.gmail.evstike.fates;

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

public class EnchantGUI implements CommandExecutor, Listener {
	 
	 
public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	Player player = (Player) sender;
    if (commandLabel.equalsIgnoreCase("enchgui")) {
    
}
        openGUI(player);
        player.sendMessage(ChatColor.GOLD + "Opened the Enchantment Menu");
		return false;
		
   
}

private void openGUI(Player player) {
    Inventory inv = Bukkit.createInventory(null, 36, ChatColor.YELLOW + "" + ChatColor.BOLD
    		+ "Enchantment Menu");

    ItemStack sharp = new ItemStack(Material.DIAMOND_SWORD);
    ItemMeta sharpMeta = sharp.getItemMeta();
    ItemStack smite = new ItemStack(Material.IRON_SWORD);
    ItemMeta smiteMeta = smite.getItemMeta();
    ItemStack bane = new ItemStack(Material.GOLDEN_SWORD);
    ItemMeta baneMeta = bane.getItemMeta();
    ItemStack knock = new ItemStack(Material.STONE_SWORD);
    ItemMeta knockMeta = knock.getItemMeta();
    ItemStack fire = new ItemStack(Material.WOODEN_SWORD);
    ItemMeta fireMeta = fire.getItemMeta();
    ItemStack loot = new ItemStack(Material.DIAMOND_AXE);
    ItemMeta lootMeta = loot.getItemMeta();
    ItemStack unb = new ItemStack(Material.IRON_AXE);
    ItemMeta unbMeta = unb.getItemMeta();
    ItemStack mend = new ItemStack(Material.GOLDEN_AXE);
    ItemMeta mendMeta = mend.getItemMeta();
    ItemStack thorns = new ItemStack(Material.STONE_AXE);
    ItemMeta thornsMeta = thorns.getItemMeta();
    ItemStack silk = new ItemStack(Material.WOODEN_AXE);
    ItemMeta silkMeta = silk.getItemMeta();
    ItemStack eff = new ItemStack(Material.DIAMOND_PICKAXE);
    ItemMeta effMeta = eff.getItemMeta();
    ItemStack fort = new ItemStack(Material.IRON_PICKAXE);
    ItemMeta fortMeta = fort.getItemMeta();
    ItemStack pow = new ItemStack(Material.BOW);
    ItemMeta powMeta = pow.getItemMeta();
    ItemStack flame = new ItemStack(Material.DIAMOND_SHOVEL);
    ItemMeta flameMeta = flame.getItemMeta();
    ItemStack punch = new ItemStack(Material.IRON_SHOVEL);
    ItemMeta punchMeta = punch.getItemMeta();
    ItemStack inf = new ItemStack(Material.GOLDEN_SHOVEL);
    ItemMeta infMeta = inf.getItemMeta();
    ItemStack aff = new ItemStack(Material.DIAMOND_HELMET);
    ItemMeta affMeta = aff.getItemMeta();
    ItemStack oxy = new ItemStack(Material.IRON_HELMET);
    ItemMeta oxyMeta = oxy.getItemMeta();
    ItemStack fall = new ItemStack(Material.DIAMOND_BOOTS);
    ItemMeta fallMeta = fall.getItemMeta();
    ItemStack fro = new ItemStack(Material.IRON_BOOTS);
    ItemMeta froMeta = fro.getItemMeta();
    ItemStack str = new ItemStack(Material.GOLDEN_BOOTS);
    ItemMeta strMeta = str.getItemMeta();
    ItemStack prot = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta protMeta = prot.getItemMeta();
    ItemStack bprot = new ItemStack(Material.IRON_CHESTPLATE);
    ItemMeta bprotMeta = bprot.getItemMeta();
    ItemStack fprot = new ItemStack(Material.GOLDEN_CHESTPLATE);
    ItemMeta fprotMeta = fprot.getItemMeta();
    ItemStack proj = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
    ItemMeta projMeta = proj.getItemMeta();
    ItemStack lure = new ItemStack(Material.FISHING_ROD);
    ItemMeta lureMeta = lure.getItemMeta();
    ItemStack luck = new ItemStack(Material.LEATHER_LEGGINGS);
    ItemMeta luckMeta = luck.getItemMeta();
    ItemStack close = new ItemStack(Material.BARRIER);
    ItemMeta closeMeta = close.getItemMeta();
    ItemStack bind = new ItemStack(Material.SLIME_BALL);
    ItemMeta bindMeta = bind.getItemMeta();

    sharpMeta.setDisplayName(ChatColor.YELLOW + "Sharpness V");
    sharp.setItemMeta(sharpMeta);

    smiteMeta.setDisplayName(ChatColor.YELLOW + "Smite V");
    smite.setItemMeta(smiteMeta);

    baneMeta.setDisplayName(ChatColor.YELLOW + "Bane of Arthropods V");
    bane.setItemMeta(baneMeta);

    knockMeta.setDisplayName(ChatColor.YELLOW + "Knockback II");
    knock.setItemMeta(knockMeta);
    
    fireMeta.setDisplayName(ChatColor.YELLOW + "Fire Aspect II");
    fire.setItemMeta(fireMeta);
    
    lootMeta.setDisplayName(ChatColor.YELLOW + "Looting III");
    loot.setItemMeta(lootMeta);
    
    unbMeta.setDisplayName(ChatColor.YELLOW + "Unbreaking III");
    unb.setItemMeta(unbMeta);
    
    mendMeta.setDisplayName(ChatColor.YELLOW + "Mending");
    mend.setItemMeta(mendMeta);
    
    thornsMeta.setDisplayName(ChatColor.YELLOW + "Thorns III");
    thorns.setItemMeta(thornsMeta);
    
    silkMeta.setDisplayName(ChatColor.YELLOW + "Silk Touch");
    silk.setItemMeta(silkMeta);
    
    effMeta.setDisplayName(ChatColor.YELLOW + "Efficiency V");
    eff.setItemMeta(effMeta);
    
    fortMeta.setDisplayName(ChatColor.YELLOW + "Fortune III");
    fort.setItemMeta(fortMeta);
    
    powMeta.setDisplayName(ChatColor.YELLOW + "Power V");
    pow.setItemMeta(powMeta);
    
    flameMeta.setDisplayName(ChatColor.YELLOW + "Flame");
    flame.setItemMeta(flameMeta);
    
    punchMeta.setDisplayName(ChatColor.YELLOW + "Punch II");
    punch.setItemMeta(punchMeta);
    
    infMeta.setDisplayName(ChatColor.YELLOW + "Infinity");
    inf.setItemMeta(infMeta);
    
    affMeta.setDisplayName(ChatColor.YELLOW + "Aqua Affinity");
    aff.setItemMeta(affMeta);
    
    oxyMeta.setDisplayName(ChatColor.YELLOW + "Respiration III");
    oxy.setItemMeta(oxyMeta);

    fallMeta.setDisplayName(ChatColor.YELLOW + "Feather Falling IV");
    fall.setItemMeta(fallMeta);
    
    froMeta.setDisplayName(ChatColor.YELLOW + "Frost Walker II");
    fro.setItemMeta(froMeta);
    
    strMeta.setDisplayName(ChatColor.YELLOW + "Depth Strider III");
    str.setItemMeta(strMeta);
    
    protMeta.setDisplayName(ChatColor.YELLOW + "Protection IV");
    prot.setItemMeta(protMeta);
    
    bprotMeta.setDisplayName(ChatColor.YELLOW + "Blast Protection IV");
    bprot.setItemMeta(bprotMeta);
    
    fprotMeta.setDisplayName(ChatColor.YELLOW + "Fire Protection IV");
    fprot.setItemMeta(fprotMeta);
    
    projMeta.setDisplayName(ChatColor.YELLOW + "Projectile Protection IV");
    proj.setItemMeta(projMeta);
    
    lureMeta.setDisplayName(ChatColor.YELLOW + "Luck of the Sea III");
    lure.setItemMeta(lureMeta);
    
    luckMeta.setDisplayName(ChatColor.YELLOW + "Lure III");
    luck.setItemMeta(luckMeta);
    
    closeMeta.setDisplayName(ChatColor.DARK_RED + "Close the Menu");
    close.setItemMeta(closeMeta);
    
    bindMeta.setDisplayName(ChatColor.RED + "Curse of Binding");
    bind.setItemMeta(bindMeta);
    
    inv.setItem(0, sharp);
    inv.setItem(1, smite);
    inv.setItem(2, bane);
    inv.setItem(3, knock);
    inv.setItem(4, fire);
    inv.setItem(5, loot);
    inv.setItem(6, unb);
    inv.setItem(7, mend);
    inv.setItem(8, thorns);
    inv.setItem(9, silk);
    inv.setItem(10, eff);
    inv.setItem(11, fort);
    inv.setItem(12, pow);
    inv.setItem(13, flame);
    inv.setItem(14, punch);
    inv.setItem(15, inf);
    inv.setItem(16, aff);
    inv.setItem(17, oxy);
    inv.setItem(18, fall);
    inv.setItem(19, fro);
    inv.setItem(20, str);
    inv.setItem(21, prot);
    inv.setItem(22, bprot);
    inv.setItem(23, fprot);
    inv.setItem(24, proj);
    inv.setItem(25, lure);
    inv.setItem(26, luck);
    inv.setItem(31, close);

    player.openInventory(inv);

}

@SuppressWarnings({ "incomplete-switch" })
@EventHandler
public void onInventoryClick(InventoryClickEvent event) {
    if (!ChatColor.stripColor(event.getInventory().getName())
            .equalsIgnoreCase("Enchantment Menu"))
        return;
    Player player = (Player) event.getWhoClicked();
    event.setCancelled(true);

    if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR||!event.getCurrentItem().hasItemMeta()){
        return;
    }
switch (event.getCurrentItem().getType()) {
case DIAMOND_SWORD:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);

}
switch (event.getCurrentItem().getType()) {
case IRON_SWORD:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);

}
switch (event.getCurrentItem().getType()) {
case GOLDEN_SWORD:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
    }
switch (event.getCurrentItem().getType()) {
case STONE_SWORD:
	player.getInventory().getItemInMainHand().addEnchantment(Enchantment.KNOCKBACK, 2);
}
switch (event.getCurrentItem().getType()) {
case WOODEN_SWORD:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
}
switch (event.getCurrentItem().getType()) {
case DIAMOND_AXE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
}
switch (event.getCurrentItem().getType()) {
case IRON_AXE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.DURABILITY, 3);
}
switch (event.getCurrentItem().getType()) {
case GOLDEN_AXE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.MENDING, 1);
}
switch (event.getCurrentItem().getType()) {
case STONE_AXE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.THORNS, 3);
}
switch (event.getCurrentItem().getType()) {
case WOODEN_AXE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
}
switch (event.getCurrentItem().getType()) {
case DIAMOND_PICKAXE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);
}
switch (event.getCurrentItem().getType()) {
case IRON_PICKAXE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
}
switch (event.getCurrentItem().getType()) {
case BOW:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
}
switch (event.getCurrentItem().getType()) {
case DIAMOND_SHOVEL:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
}
switch (event.getCurrentItem().getType()) {
case IRON_SHOVEL:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
}
switch (event.getCurrentItem().getType()) {
case GOLDEN_SHOVEL:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
}
switch (event.getCurrentItem().getType()) {
case DIAMOND_HELMET:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
}
switch (event.getCurrentItem().getType()) {
case IRON_HELMET:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.OXYGEN, 3);
}
switch (event.getCurrentItem().getType()) {
case DIAMOND_BOOTS:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
}
switch (event.getCurrentItem().getType()) {
case IRON_BOOTS:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.FROST_WALKER, 2);
}
switch (event.getCurrentItem().getType()) {
case GOLDEN_BOOTS:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
}
switch (event.getCurrentItem().getType()) {
case DIAMOND_CHESTPLATE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
}
switch (event.getCurrentItem().getType()) {
case IRON_CHESTPLATE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
}
switch (event.getCurrentItem().getType()) {
case GOLDEN_CHESTPLATE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 4);
}
switch (event.getCurrentItem().getType()) {
case CHAINMAIL_CHESTPLATE:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
}
switch (event.getCurrentItem().getType()) {
case LEATHER_LEGGINGS:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.LURE, 3);
}
switch (event.getCurrentItem().getType()) {

case FISHING_ROD:
	player.getInventory().getItemInMainHand().addUnsafeEnchantment(Enchantment.LUCK, 3);
}
switch (event.getCurrentItem().getType()) {

case BARRIER:
	player.closeInventory();
    }
    }
}

