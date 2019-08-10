package com.gmail.evstike.AdvancedWeapons;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class EnchantGUI extends API implements CommandExecutor, Listener, TabCompleter {

    Fates plugin;

    public EnchantGUI(Fates instance) {
        plugin = instance;
    }

    public static int num;

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (hasCommandPerm(sender, cmd, commandLabel, plugin.getConfig()) == false) {
            if (cmd.getName().equalsIgnoreCase("enchgui")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (args.length != 2) {
                        player.sendMessage("§cError: §4/" + commandLabel + " [level] [safe/unsafe].");
                        return false;
                    }
                    if (args.length == 2) {
                        if (!isInt(args[0])) {
                            player.sendMessage("§cError: §4Please specify an enchantment level.");
                            return false;
                        }
                    }
                    if (args.length == 2) {
                        if (!(args[1].equalsIgnoreCase("safe") || args[1].equalsIgnoreCase("unsafe"))) {
                            player.sendMessage("§cError: §4/" + commandLabel + " [level] [safe/unsafe].");
                            return false;
                        }
                    }
                    if (args.length == 2) {
                        if (isInt(args[0]) && args[1].equalsIgnoreCase("safe")) {

                            num = Integer.parseInt(args[0]);
                            openGUI(player);
                            return false;
                        }
                    }
                    if (args.length == 2) {
                        if (isInt(args[0]) && args[1].equalsIgnoreCase("unsafe")) {

                            num = Integer.parseInt(args[0]);
                            openUnsafeGUI(player);
                            return false;
                        }
                    }
                }
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cError: §4Only Players can use this command!");
                return true;
            }
        }
	return false;
    }
//SAFE
private void openGUI(Player player) {
    Inventory inv = Bukkit.createInventory(null, 45, "Enchantment Menu");
  	
  	ItemStack i = player.getInventory().getItemInHand();
  	
  	//SHARPNESS
    ItemStack sharp = new ItemStack(UMaterial.DIAMOND_SWORD.getMaterial());
    ItemMeta sharpMeta = sharp.getItemMeta();
    sharpMeta.setDisplayName(ChatColor.YELLOW + "Sharpness §7("+num+")");
    sharp.setItemMeta(sharpMeta);
    
  	if(num>=Enchantment.DAMAGE_ALL.getStartLevel()){
  	if(num<=Enchantment.DAMAGE_ALL.getMaxLevel()){
  	if(Enchantment.DAMAGE_ALL.canEnchantItem(i)){
    inv.addItem(sharp);
  	}
  	}
  	}
  	//SMITE
    ItemStack smite = new ItemStack(UMaterial.IRON_SWORD.getMaterial());
    ItemMeta smiteMeta = smite.getItemMeta();
    smiteMeta.setDisplayName(ChatColor.YELLOW + "Smite §7("+num+")");
    smite.setItemMeta(smiteMeta);
    
  	if(num>=Enchantment.DAMAGE_UNDEAD.getStartLevel()){
  	if(num<=Enchantment.DAMAGE_UNDEAD.getMaxLevel()){
  	if(Enchantment.DAMAGE_UNDEAD.canEnchantItem(i)){
    inv.addItem(smite);
  	}
  	}
  	}
  	//BANE OF ARTHROPODS
    ItemStack bane = new ItemStack(UMaterial.GOLDEN_SWORD.getMaterial());
    ItemMeta baneMeta = bane.getItemMeta();
    baneMeta.setDisplayName(ChatColor.YELLOW + "Bane of Arthropods §7("+num+")");
    bane.setItemMeta(baneMeta);
    
  	if(num>=Enchantment.DAMAGE_ARTHROPODS.getStartLevel()){
  	if(num<=Enchantment.DAMAGE_ARTHROPODS.getMaxLevel()){
  	if(Enchantment.DAMAGE_ARTHROPODS.canEnchantItem(i)){
    inv.addItem(bane);
  	}
  	}
  	}
  	//KNOCKBACK
    ItemStack knock = new ItemStack(UMaterial.STONE_SWORD.getMaterial());
    ItemMeta knockMeta = knock.getItemMeta();
    knockMeta.setDisplayName(ChatColor.YELLOW + "Knockback §7("+num+")");
    knock.setItemMeta(knockMeta);
    
  	if(num>=Enchantment.KNOCKBACK.getStartLevel()){
  	if(num<=Enchantment.KNOCKBACK.getMaxLevel()){
  	if(Enchantment.KNOCKBACK.canEnchantItem(i)){
    inv.addItem(knock);
  	}
  	}
  	}
  	//FIRE ASPECT
    ItemStack fire = new ItemStack(UMaterial.WOODEN_SWORD.getMaterial());
    ItemMeta fireMeta = fire.getItemMeta();
    fireMeta.setDisplayName(ChatColor.YELLOW + "Fire Aspect §7("+num+")");
    fire.setItemMeta(fireMeta);
    
  	if(num>=Enchantment.FIRE_ASPECT.getStartLevel()){
  	if(num<=Enchantment.FIRE_ASPECT.getMaxLevel()){
  	if(Enchantment.FIRE_ASPECT.canEnchantItem(i)){
    inv.addItem(fire);
  	}
  	}
  	}
    //SWEEPING EDGE
    if(serverIs1111()){
        ItemStack sweep = new ItemStack(UMaterial.BLAZE_ROD.getMaterial());
        ItemMeta sweepMeta = sweep.getItemMeta();
        sweepMeta.setDisplayName(ChatColor.YELLOW + "Sweeping Edge §7("+num+")");
        sweep.setItemMeta(sweepMeta);

        if(num>=Enchantment.SWEEPING_EDGE.getStartLevel()){
            if(num<=Enchantment.SWEEPING_EDGE.getMaxLevel()) {
                if (Enchantment.SWEEPING_EDGE.canEnchantItem(i)) {
                    inv.addItem(sweep);
                }
            }
        }
    }
  	//LOOTING
    ItemStack loot = new ItemStack(UMaterial.DIAMOND_AXE.getMaterial());
    ItemMeta lootMeta = loot.getItemMeta();
    lootMeta.setDisplayName(ChatColor.YELLOW + "Looting §7("+num+")");
    loot.setItemMeta(lootMeta);
    
  	if(num>=Enchantment.LOOT_BONUS_MOBS.getStartLevel()){
  	if(num<=Enchantment.LOOT_BONUS_MOBS.getMaxLevel()){
  	if(Enchantment.LOOT_BONUS_MOBS.canEnchantItem(i)){
    inv.addItem(loot);
  	}
  	}
  	}
  	//UNBREAKING
    ItemStack unb = new ItemStack(UMaterial.IRON_AXE.getMaterial());
    ItemMeta unbMeta = unb.getItemMeta();
    unbMeta.setDisplayName(ChatColor.YELLOW + "Unbreaking §7("+num+")");
    unb.setItemMeta(unbMeta);
    
  	if(num>=Enchantment.DURABILITY.getStartLevel()){
  	if(num<=Enchantment.DURABILITY.getMaxLevel()){
  	if(Enchantment.DURABILITY.canEnchantItem(i)){
    inv.addItem(unb);
  	}
  	}
  	}
  	//MENDING
    if(serverIs19()) {
    ItemStack mend = new ItemStack(UMaterial.GOLDEN_AXE.getMaterial());
    ItemMeta mendMeta = mend.getItemMeta();
    mendMeta.setDisplayName(ChatColor.YELLOW + "Mending §7("+num+")");
    mend.setItemMeta(mendMeta);
    
  	if(num>=Enchantment.MENDING.getStartLevel()){
  	if(num<=Enchantment.MENDING.getMaxLevel()) {
        if (Enchantment.MENDING.canEnchantItem(i)) {
            inv.addItem(mend);
        }
    }
  	}
  	}
  	//THORNS
    ItemStack thorns = new ItemStack(UMaterial.STONE_AXE.getMaterial());
    ItemMeta thornsMeta = thorns.getItemMeta();
    thornsMeta.setDisplayName(ChatColor.YELLOW + "Thorns §7("+num+")");
    thorns.setItemMeta(thornsMeta);
    
  	if(num>=Enchantment.THORNS.getStartLevel()){
  	if(num<=Enchantment.THORNS.getMaxLevel()){
  	if(Enchantment.THORNS.canEnchantItem(i)){
    inv.addItem(thorns);
  	}
  	}
  	}
  	//SILK TOUCH
    ItemStack silk = new ItemStack(UMaterial.WOODEN_AXE.getMaterial());
    ItemMeta silkMeta = silk.getItemMeta();
    silkMeta.setDisplayName(ChatColor.YELLOW + "Silk Touch §7("+num+")");
    silk.setItemMeta(silkMeta);
    
  	if(num>=Enchantment.SILK_TOUCH.getStartLevel()){
  	if(num<=Enchantment.SILK_TOUCH.getMaxLevel()){
  	if(Enchantment.SILK_TOUCH.canEnchantItem(i)){
    inv.addItem(silk);
  	}
  	}
  	}
  	//EFFICIENCY
    ItemStack eff = new ItemStack(UMaterial.DIAMOND_PICKAXE.getMaterial());
    ItemMeta effMeta = eff.getItemMeta();
    effMeta.setDisplayName(ChatColor.YELLOW + "Efficiency §7("+num+")");
    eff.setItemMeta(effMeta);
    
  	if(num>=Enchantment.DIG_SPEED.getStartLevel()){
  	if(num<=Enchantment.DIG_SPEED.getMaxLevel()){
  	if(Enchantment.DIG_SPEED.canEnchantItem(i)){
    inv.addItem(eff);
  	}
  	}
  	}
  	//FORTUNE
    ItemStack fort = new ItemStack(UMaterial.IRON_PICKAXE.getMaterial());
    ItemMeta fortMeta = fort.getItemMeta();
    fortMeta.setDisplayName(ChatColor.YELLOW + "Fortune §7("+num+")");
    fort.setItemMeta(fortMeta);
    
  	if(num>=Enchantment.LOOT_BONUS_BLOCKS.getStartLevel()){
  	if(num<=Enchantment.LOOT_BONUS_BLOCKS.getMaxLevel()){
  	if(Enchantment.LOOT_BONUS_BLOCKS.canEnchantItem(i)){
    inv.addItem(fort);
  	}
  	}
  	}
  	//POWER
    ItemStack pow = new ItemStack(UMaterial.BOW.getMaterial());
    ItemMeta powMeta = pow.getItemMeta();
    powMeta.setDisplayName(ChatColor.YELLOW + "Power §7("+num+")");
    pow.setItemMeta(powMeta);
    
  	if(num>=Enchantment.ARROW_DAMAGE.getStartLevel()){
  	if(num<=Enchantment.ARROW_DAMAGE.getMaxLevel()){
  	if(Enchantment.ARROW_DAMAGE.canEnchantItem(i)){
    inv.addItem(pow);
  	}
  	}
  	}
  	//FLAME
    ItemStack flame = new ItemStack(UMaterial.DIAMOND_SHOVEL.getMaterial());
    ItemMeta flameMeta = flame.getItemMeta();
    flameMeta.setDisplayName(ChatColor.YELLOW + "Flame §7("+num+")");
    flame.setItemMeta(flameMeta);
    
  	if(num>=Enchantment.ARROW_FIRE.getStartLevel()){
  	if(num<=Enchantment.ARROW_FIRE.getMaxLevel()){
  	if(Enchantment.ARROW_FIRE.canEnchantItem(i)){
    inv.addItem(flame);
  	}
  	}
    }
  	//PUNCH
    ItemStack pun = new ItemStack(UMaterial.IRON_SHOVEL.getMaterial());
    ItemMeta punMeta = pun.getItemMeta();
    punMeta.setDisplayName(ChatColor.YELLOW + "Punch §7("+num+")");
    pun.setItemMeta(punMeta);
    
  	if(num>=Enchantment.ARROW_KNOCKBACK.getStartLevel()){
  	if(num<=Enchantment.ARROW_KNOCKBACK.getMaxLevel()){
  	if(Enchantment.ARROW_KNOCKBACK.canEnchantItem(i)){
    inv.addItem(pun);
  	}
  	}
  	}
  	//INFINITY
    ItemStack inf = new ItemStack(UMaterial.GOLDEN_SHOVEL.getMaterial());
    ItemMeta infMeta = inf.getItemMeta();
    infMeta.setDisplayName(ChatColor.YELLOW + "Infinity §7("+num+")");
    inf.setItemMeta(infMeta);
    
  	if(num>=Enchantment.ARROW_INFINITE.getStartLevel()){
  	if(num<=Enchantment.ARROW_INFINITE.getMaxLevel()){
  	if(Enchantment.ARROW_INFINITE.canEnchantItem(i)){
    inv.addItem(inf);
  	}
  	}
  	}
  	//AQUA AFFINITY
    ItemStack aff = new ItemStack(UMaterial.DIAMOND_HELMET.getMaterial());
    ItemMeta affMeta = aff.getItemMeta();
    affMeta.setDisplayName(ChatColor.YELLOW + "Aqua Affinity §7("+num+")");
    aff.setItemMeta(affMeta);
    
  	if(num>=Enchantment.WATER_WORKER.getStartLevel()){
  	if(num<=Enchantment.WATER_WORKER.getMaxLevel()){
  	if(Enchantment.WATER_WORKER.canEnchantItem(i)){
    inv.addItem(aff);
  	}
  	}
  	}
  	//RESPIRATION
    ItemStack resp = new ItemStack(UMaterial.IRON_HELMET.getMaterial());
    ItemMeta respMeta = resp.getItemMeta();
    respMeta.setDisplayName(ChatColor.YELLOW + "Respiration §7("+num+")");
    resp.setItemMeta(respMeta);
    
  	if(num>=Enchantment.OXYGEN.getStartLevel()){
  	if(num<=Enchantment.OXYGEN.getMaxLevel()){
  	if(Enchantment.OXYGEN.canEnchantItem(i)){
    inv.addItem(resp);
  	}
  	}
  	}
  	//FEATHER FALLING
    ItemStack fall = new ItemStack(UMaterial.DIAMOND_BOOTS.getMaterial());
    ItemMeta fallMeta = fall.getItemMeta();
    fallMeta.setDisplayName(ChatColor.YELLOW + "Feather Falling §7("+num+")");
    fall.setItemMeta(fallMeta);
    
  	if(num>=Enchantment.PROTECTION_FALL.getStartLevel()){
  	if(num<=Enchantment.PROTECTION_FALL.getMaxLevel()){
  	if(Enchantment.PROTECTION_FALL.canEnchantItem(i)){
    inv.addItem(fall);
  	}
  	}
  	}
  	//FROST WALKER
    if(serverIs19()) {
    ItemStack fro = new ItemStack(UMaterial.IRON_BOOTS.getMaterial());
    ItemMeta froMeta = fro.getItemMeta();
    froMeta.setDisplayName(ChatColor.YELLOW + "Frost Walker §7("+num+")");
    fro.setItemMeta(froMeta);
    
  	if(num>=Enchantment.FROST_WALKER.getStartLevel()){
  	if(num<=Enchantment.FROST_WALKER.getMaxLevel()) {
        if (Enchantment.FROST_WALKER.canEnchantItem(i)) {
            inv.addItem(fro);
        }
    }
  	}
  	}
  	//DEPTH STRIDER
    ItemStack str = new ItemStack(UMaterial.GOLDEN_BOOTS.getMaterial());
    ItemMeta strMeta = str.getItemMeta();
    strMeta.setDisplayName(ChatColor.YELLOW + "Depth Strider §7("+num+")");
    str.setItemMeta(strMeta);
    
  	if(num>=Enchantment.DEPTH_STRIDER.getStartLevel()){
  	if(num<=Enchantment.DEPTH_STRIDER.getMaxLevel()){
  	if(Enchantment.DEPTH_STRIDER.canEnchantItem(i)){
    inv.addItem(str);
  	}
  	}
  	}
  	//PROTECTION
    ItemStack prot = new ItemStack(UMaterial.DIAMOND_CHESTPLATE.getMaterial());
    ItemMeta protMeta = prot.getItemMeta();
    protMeta.setDisplayName(ChatColor.YELLOW + "Protection §7("+num+")");
    prot.setItemMeta(protMeta);
    
  	if(num>=Enchantment.PROTECTION_ENVIRONMENTAL.getStartLevel()){
  	if(num<=Enchantment.PROTECTION_ENVIRONMENTAL.getMaxLevel()){
  	if(Enchantment.PROTECTION_ENVIRONMENTAL.canEnchantItem(i)){
    inv.addItem(prot);
  	}
  	}
  	}
  	//BLAST PROTECTION
    ItemStack bprot = new ItemStack(UMaterial.IRON_CHESTPLATE.getMaterial());
    ItemMeta bprotMeta = bprot.getItemMeta();
    bprotMeta.setDisplayName(ChatColor.YELLOW + "Blast Protection §7("+num+")");
    bprot.setItemMeta(bprotMeta);
    
  	if(num>=Enchantment.PROTECTION_EXPLOSIONS.getStartLevel()){
  	if(num<=Enchantment.PROTECTION_EXPLOSIONS.getMaxLevel()){
  	if(Enchantment.PROTECTION_EXPLOSIONS.canEnchantItem(i)){
    inv.addItem(bprot);
  	}
  	}
  	}
  	//FIRE PROTECTION
    ItemStack fprot = new ItemStack(UMaterial.GOLDEN_CHESTPLATE.getMaterial());
    ItemMeta fprotMeta = fprot.getItemMeta();
    fprotMeta.setDisplayName(ChatColor.YELLOW + "Fire Protection §7("+num+")");
    fprot.setItemMeta(fprotMeta);
    
  	if(num>=Enchantment.PROTECTION_FIRE.getStartLevel()){
  	if(num<=Enchantment.PROTECTION_FIRE.getMaxLevel()){
  	if(Enchantment.PROTECTION_FIRE.canEnchantItem(i)){
    inv.addItem(fprot);
  	}
  	}
  	}
  	//PROJECTILE PROTECTION
    ItemStack proj = new ItemStack(UMaterial.CHAINMAIL_CHESTPLATE.getMaterial());
    ItemMeta projMeta = proj.getItemMeta();
    projMeta.setDisplayName(ChatColor.YELLOW + "Projectile Protection §7("+num+")");
    proj.setItemMeta(projMeta);
    
  	if(num>=Enchantment.PROTECTION_PROJECTILE.getStartLevel()){
  	if(num<=Enchantment.PROTECTION_PROJECTILE.getMaxLevel()){
  	if(Enchantment.PROTECTION_PROJECTILE.canEnchantItem(i)){
    inv.addItem(proj);
  	}
  	}
  	}
  	//LURE
    ItemStack lure = new ItemStack(UMaterial.FISHING_ROD.getMaterial());
    ItemMeta lureMeta = lure.getItemMeta();
    lureMeta.setDisplayName(ChatColor.YELLOW + "Lure §7("+num+")");
    lure.setItemMeta(lureMeta);
    
  	if(num>=Enchantment.LURE.getStartLevel()){
  	if(num<=Enchantment.LURE.getMaxLevel()){
  	if(Enchantment.LURE.canEnchantItem(i)){
    inv.addItem(lure);
  	}
  	}
  	}
  	//LUCK OF THE SEA
    ItemStack luck = new ItemStack(UMaterial.LEATHER_LEGGINGS.getMaterial());
    ItemMeta luckMeta = luck.getItemMeta();
    luckMeta.setDisplayName(ChatColor.YELLOW + "Luck of the Sea §7("+num+")");
    luck.setItemMeta(luckMeta);
    
  	if(num>=Enchantment.LUCK.getStartLevel()){
  	if(num<=Enchantment.LUCK.getMaxLevel()){
  	if(Enchantment.LUCK.canEnchantItem(i)){
    inv.addItem(luck);
  	}
  	}
  	}
  	//CHANNELING
    if(serverIs113()){
    ItemStack chan = new ItemStack(UMaterial.TRIDENT.getMaterial());
    ItemMeta chanMeta = chan.getItemMeta();
    chanMeta.setDisplayName(ChatColor.YELLOW + "Channeling §7("+num+")");
    chan.setItemMeta(chanMeta);
    
  	if(num>=Enchantment.CHANNELING.getStartLevel()) {
        if (num <= Enchantment.CHANNELING.getMaxLevel()) {
            if (Enchantment.CHANNELING.canEnchantItem(i)) {
                inv.addItem(chan);
            }
        }
    }
  	}
  	//RIPTIDE
    if(serverIs113()) {
        ItemStack rip = new ItemStack(UMaterial.HEART_OF_THE_SEA.getMaterial());
        ItemMeta ripMeta = rip.getItemMeta();
        ripMeta.setDisplayName(ChatColor.YELLOW + "Riptide §7(" + num + ")");
        rip.setItemMeta(ripMeta);

        if (num >= Enchantment.RIPTIDE.getStartLevel()) {
            if (num <= Enchantment.RIPTIDE.getMaxLevel()) {
                if (Enchantment.RIPTIDE.canEnchantItem(i)) {
                    inv.addItem(rip);
                }
            }
        }
    }
  	//LOYALTY
        if(serverIs113()){
    ItemStack loy = new ItemStack(UMaterial.NAME_TAG.getMaterial());
    ItemMeta loyMeta = loy.getItemMeta();
    loyMeta.setDisplayName(ChatColor.YELLOW + "Loyalty §7("+num+")");
    loy.setItemMeta(loyMeta);
    
  	if(num>=Enchantment.LOYALTY.getStartLevel()){
  	if(num<=Enchantment.LOYALTY.getMaxLevel()) {
        if (Enchantment.LOYALTY.canEnchantItem(i)) {
            inv.addItem(loy);
        }
    }
    }
  	}
    //PIERCING
    if(serverIs114()) {
        ItemStack pie = new ItemStack(UMaterial.ARROW.getMaterial());
        ItemMeta pieMeta = pie.getItemMeta();
        pieMeta.setDisplayName(ChatColor.YELLOW + "Piercing §7(" + num + ")");
        pie.setItemMeta(pieMeta);

        if (num >= Enchantment.PIERCING.getStartLevel()) {
            if (num <= Enchantment.PIERCING.getMaxLevel()) {
                if (Enchantment.PIERCING.canEnchantItem(i)) {
                    inv.addItem(pie);
                }
            }
        }
    }
    //QUICKFIRE
    if(serverIs114()) {
        ItemStack qui = new ItemStack(UMaterial.SPECTRAL_ARROW.getMaterial());
        ItemMeta quiMeta = qui.getItemMeta();
        quiMeta.setDisplayName(ChatColor.YELLOW + "Quick Charge §7(" + num + ")");
        qui.setItemMeta(quiMeta);

        if (num >= Enchantment.QUICK_CHARGE.getStartLevel()) {
            if (num <= Enchantment.QUICK_CHARGE.getMaxLevel()) {
                if (Enchantment.QUICK_CHARGE.canEnchantItem(i)) {
                    inv.addItem(qui);
                }
            }
        }
    }
    //MULTISHOT
    if(serverIs114()) {
        ItemStack mul = new ItemStack(UMaterial.CROSSBOW.getMaterial());
        ItemMeta mulMeta = mul.getItemMeta();
        mulMeta.setDisplayName(ChatColor.YELLOW + "Multishot §7(" + num + ")");
        mul.setItemMeta(mulMeta);

        if (num >= Enchantment.MULTISHOT.getStartLevel()) {
            if (num <= Enchantment.MULTISHOT.getMaxLevel()) {
                if (Enchantment.MULTISHOT.canEnchantItem(i)) {
                    inv.addItem(mul);
                }
            }
        }
    }
  	//CURSE OF BINDING
    if(serverIs111()) {
        ItemStack bind = new ItemStack(UMaterial.SLIME_BALL.getMaterial());
        ItemMeta bindMeta = bind.getItemMeta();
        bindMeta.setDisplayName(ChatColor.RED + "Curse of Binding §7(" + num + ")");
        bind.setItemMeta(bindMeta);

        if (num >= Enchantment.BINDING_CURSE.getStartLevel()) {
            if (num <= Enchantment.BINDING_CURSE.getMaxLevel()) {
                if (Enchantment.BINDING_CURSE.canEnchantItem(i)) {
                    inv.addItem(bind);
                }
            }
        }
    }
  	//CURSE OF VANISHING
    if(serverIs111()){
    ItemStack van = new ItemStack(UMaterial.ENDER_EYE.getMaterial());
    ItemMeta vanMeta = van.getItemMeta();
    vanMeta.setDisplayName(ChatColor.RED + "Curse of Vanishing §7("+num+")");
    van.setItemMeta(vanMeta);
    
  	if(num>=Enchantment.VANISHING_CURSE.getStartLevel()){
  	if(num<=Enchantment.VANISHING_CURSE.getMaxLevel()) {
        if (Enchantment.VANISHING_CURSE.canEnchantItem(i)) {
            inv.addItem(van);
        }
    }
  	}
  	}

    ItemStack close = new ItemStack(UMaterial.BARRIER.getMaterial());
    ItemMeta closeMeta = close.getItemMeta();
    ItemStack lvl = new ItemStack(UMaterial.EXPERIENCE_BOTTLE.getMaterial());
    ItemMeta lvlMeta = lvl.getItemMeta();    
    
    closeMeta.setDisplayName(ChatColor.RED + "Close");
    close.setItemMeta(closeMeta);
    
    List<String> Lore = new ArrayList<String>();
    lvlMeta.setDisplayName(ChatColor.GREEN + "Enchantment Level");
    Lore.add("§7Level: "+num);
    lvlMeta.setLore(Lore);
    lvl.setItemMeta(lvlMeta);
     
    inv.setItem(40, close);
    inv.setItem(44, lvl);

    player.openInventory(inv);

}
//UNSAFE
private void openUnsafeGUI(Player player) {
    Inventory inv = Bukkit.createInventory(null, 45, "Enchantment Menu");

    //SHARPNESS
    ItemStack sharp = new ItemStack(UMaterial.DIAMOND_SWORD.getMaterial());
    ItemMeta sharpMeta = sharp.getItemMeta();
    sharpMeta.setDisplayName(ChatColor.YELLOW + "Sharpness §7(" + num + ")");
    sharp.setItemMeta(sharpMeta);
    inv.addItem(sharp);
    //SMITE
    ItemStack smite = new ItemStack(UMaterial.IRON_SWORD.getMaterial());
    ItemMeta smiteMeta = smite.getItemMeta();
    smiteMeta.setDisplayName(ChatColor.YELLOW + "Smite §7(" + num + ")");
    smite.setItemMeta(smiteMeta);
    inv.addItem(smite);
    //BANE OF ARTHROPODS
    ItemStack bane = new ItemStack(UMaterial.GOLDEN_SWORD.getMaterial());
    ItemMeta baneMeta = bane.getItemMeta();
    baneMeta.setDisplayName(ChatColor.YELLOW + "Bane of Arthropods §7(" + num + ")");
    bane.setItemMeta(baneMeta);
    inv.addItem(bane);
    //KNOCKBACK
    ItemStack knock = new ItemStack(UMaterial.STONE_SWORD.getMaterial());
    ItemMeta knockMeta = knock.getItemMeta();
    knockMeta.setDisplayName(ChatColor.YELLOW + "Knockback §7(" + num + ")");
    knock.setItemMeta(knockMeta);
    inv.addItem(knock);
    //FIRE ASPECT
    ItemStack fire = new ItemStack(UMaterial.WOODEN_SWORD.getMaterial());
    ItemMeta fireMeta = fire.getItemMeta();
    fireMeta.setDisplayName(ChatColor.YELLOW + "Fire Aspect §7(" + num + ")");
    fire.setItemMeta(fireMeta);
    inv.addItem(fire);
    //SWEEPING EDGE
    if (serverIs1111()) {
        ItemStack sweep = new ItemStack(UMaterial.BLAZE_ROD.getMaterial());
        ItemMeta sweepMeta = sweep.getItemMeta();
        sweepMeta.setDisplayName(ChatColor.YELLOW + "Sweeping Edge §7(" + num + ")");
        sweep.setItemMeta(sweepMeta);
        inv.addItem(sweep);
    }
    //LOOTING
    ItemStack loot = new ItemStack(UMaterial.DIAMOND_AXE.getMaterial());
    ItemMeta lootMeta = loot.getItemMeta();
    lootMeta.setDisplayName(ChatColor.YELLOW + "Looting §7(" + num + ")");
    loot.setItemMeta(lootMeta);
    inv.addItem(loot);
    //UNBREAKING
    ItemStack unb = new ItemStack(UMaterial.IRON_AXE.getMaterial());
    ItemMeta unbMeta = unb.getItemMeta();
    unbMeta.setDisplayName(ChatColor.YELLOW + "Unbreaking §7(" + num + ")");
    unb.setItemMeta(unbMeta);
    inv.addItem(unb);
    //MENDING
    if(serverIs19()) {
        ItemStack mend = new ItemStack(UMaterial.GOLDEN_AXE.getMaterial());
        ItemMeta mendMeta = mend.getItemMeta();
        mendMeta.setDisplayName(ChatColor.YELLOW + "Mending §7(" + num + ")");
        mend.setItemMeta(mendMeta);
        inv.addItem(mend);
    }
    //THORNS
    ItemStack thorns = new ItemStack(UMaterial.STONE_AXE.getMaterial());
    ItemMeta thornsMeta = thorns.getItemMeta();
    thornsMeta.setDisplayName(ChatColor.YELLOW + "Thorns §7(" + num + ")");
    thorns.setItemMeta(thornsMeta);
    inv.addItem(thorns);
    //SILK TOUCH
    ItemStack silk = new ItemStack(UMaterial.WOODEN_AXE.getMaterial());
    ItemMeta silkMeta = silk.getItemMeta();
    silkMeta.setDisplayName(ChatColor.YELLOW + "Silk Touch §7(" + num + ")");
    silk.setItemMeta(silkMeta);
    inv.addItem(silk);
    //EFFICIENCY
    ItemStack eff = new ItemStack(UMaterial.DIAMOND_PICKAXE.getMaterial());
    ItemMeta effMeta = eff.getItemMeta();
    effMeta.setDisplayName(ChatColor.YELLOW + "Efficiency §7(" + num + ")");
    eff.setItemMeta(effMeta);
    inv.addItem(eff);
    //FORTUNE
    ItemStack fort = new ItemStack(UMaterial.IRON_PICKAXE.getMaterial());
    ItemMeta fortMeta = fort.getItemMeta();
    fortMeta.setDisplayName(ChatColor.YELLOW + "Fortune §7(" + num + ")");
    fort.setItemMeta(fortMeta);
    inv.addItem(fort);
    //POWER
    ItemStack pow = new ItemStack(UMaterial.BOW.getMaterial());
    ItemMeta powMeta = pow.getItemMeta();
    powMeta.setDisplayName(ChatColor.YELLOW + "Power §7(" + num + ")");
    pow.setItemMeta(powMeta);
    inv.addItem(pow);
    //FLAME
    ItemStack flame = new ItemStack(UMaterial.DIAMOND_SHOVEL.getMaterial());
    ItemMeta flameMeta = flame.getItemMeta();
    flameMeta.setDisplayName(ChatColor.YELLOW + "Flame §7(" + num + ")");
    flame.setItemMeta(flameMeta);
    inv.addItem(flame);
    //PUNCH
    ItemStack pun = new ItemStack(UMaterial.IRON_SHOVEL.getMaterial());
    ItemMeta punMeta = pun.getItemMeta();
    punMeta.setDisplayName(ChatColor.YELLOW + "Punch §7(" + num + ")");
    pun.setItemMeta(punMeta);
    inv.addItem(pun);
    //INFINITY
    ItemStack inf = new ItemStack(UMaterial.GOLDEN_SHOVEL.getMaterial());
    ItemMeta infMeta = inf.getItemMeta();
    infMeta.setDisplayName(ChatColor.YELLOW + "Infinity §7(" + num + ")");
    inf.setItemMeta(infMeta);
    inv.addItem(inf);
    //AQUA AFFINITY
    ItemStack aff = new ItemStack(UMaterial.DIAMOND_HELMET.getMaterial());
    ItemMeta affMeta = aff.getItemMeta();
    affMeta.setDisplayName(ChatColor.YELLOW + "Aqua Affinity §7(" + num + ")");
    aff.setItemMeta(affMeta);
    inv.addItem(aff);
    //RESPIRATION
    ItemStack resp = new ItemStack(UMaterial.IRON_HELMET.getMaterial());
    ItemMeta respMeta = resp.getItemMeta();
    respMeta.setDisplayName(ChatColor.YELLOW + "Respiration §7(" + num + ")");
    resp.setItemMeta(respMeta);
    inv.addItem(resp);
    //FEATHER FALLING
    ItemStack fall = new ItemStack(UMaterial.DIAMOND_BOOTS.getMaterial());
    ItemMeta fallMeta = fall.getItemMeta();
    fallMeta.setDisplayName(ChatColor.YELLOW + "Feather Falling §7(" + num + ")");
    fall.setItemMeta(fallMeta);
    inv.addItem(fall);
    //FROST WALKER
    if(serverIs19()) {
        ItemStack fro = new ItemStack(UMaterial.IRON_BOOTS.getMaterial());
        ItemMeta froMeta = fro.getItemMeta();
        froMeta.setDisplayName(ChatColor.YELLOW + "Frost Walker §7(" + num + ")");
        fro.setItemMeta(froMeta);
        inv.addItem(fro);
    }
    //DEPTH STRIDER
    ItemStack str = new ItemStack(UMaterial.GOLDEN_BOOTS.getMaterial());
    ItemMeta strMeta = str.getItemMeta();
    strMeta.setDisplayName(ChatColor.YELLOW + "Depth Strider §7(" + num + ")");
    str.setItemMeta(strMeta);
    inv.addItem(str);
    //PROTECTION
    ItemStack prot = new ItemStack(UMaterial.DIAMOND_CHESTPLATE.getMaterial());
    ItemMeta protMeta = prot.getItemMeta();
    protMeta.setDisplayName(ChatColor.YELLOW + "Protection §7(" + num + ")");
    prot.setItemMeta(protMeta);
    inv.addItem(prot);
    //BLAST PROTECTION
    ItemStack bprot = new ItemStack(UMaterial.IRON_CHESTPLATE.getMaterial());
    ItemMeta bprotMeta = bprot.getItemMeta();
    bprotMeta.setDisplayName(ChatColor.YELLOW + "Blast Protection §7(" + num + ")");
    bprot.setItemMeta(bprotMeta);
    inv.addItem(bprot);
    //FIRE PROTECTION
    ItemStack fprot = new ItemStack(UMaterial.GOLDEN_CHESTPLATE.getMaterial());
    ItemMeta fprotMeta = fprot.getItemMeta();
    fprotMeta.setDisplayName(ChatColor.YELLOW + "Fire Protection §7(" + num + ")");
    fprot.setItemMeta(fprotMeta);
    inv.addItem(fprot);
    //PROJECTILE PROTECTION
    ItemStack proj = new ItemStack(UMaterial.CHAINMAIL_CHESTPLATE.getMaterial());
    ItemMeta projMeta = proj.getItemMeta();
    projMeta.setDisplayName(ChatColor.YELLOW + "Projectile Protection §7(" + num + ")");
    proj.setItemMeta(projMeta);
    inv.addItem(proj);
    //LURE
    ItemStack lure = new ItemStack(UMaterial.FISHING_ROD.getMaterial());
    ItemMeta lureMeta = lure.getItemMeta();
    lureMeta.setDisplayName(ChatColor.YELLOW + "Lure §7(" + num + ")");
    lure.setItemMeta(lureMeta);
    inv.addItem(lure);
    //LUCK OF THE SEA
    ItemStack luck = new ItemStack(UMaterial.LEATHER_LEGGINGS.getMaterial());
    ItemMeta luckMeta = luck.getItemMeta();
    luckMeta.setDisplayName(ChatColor.YELLOW + "Luck of the Sea §7(" + num + ")");
    luck.setItemMeta(luckMeta);
    inv.addItem(luck);
    //CHANNELING
    if (serverIs113()) {
        ItemStack chan = new ItemStack(UMaterial.TRIDENT.getMaterial());
        ItemMeta chanMeta = chan.getItemMeta();
        chanMeta.setDisplayName(ChatColor.YELLOW + "Channeling §7(" + num + ")");
        chan.setItemMeta(chanMeta);
        inv.addItem(chan);
    }
    //RIPTIDE
    if (serverIs113()) {
        ItemStack rip = new ItemStack(UMaterial.HEART_OF_THE_SEA.getMaterial());
        ItemMeta ripMeta = rip.getItemMeta();
        ripMeta.setDisplayName(ChatColor.YELLOW + "Riptide §7(" + num + ")");
        rip.setItemMeta(ripMeta);
        inv.addItem(rip);
    }
    //LOYALTY
    if (serverIs113()) {
        ItemStack loy = new ItemStack(UMaterial.NAME_TAG.getMaterial());
        ItemMeta loyMeta = loy.getItemMeta();
        loyMeta.setDisplayName(ChatColor.YELLOW + "Loyalty §7(" + num + ")");
        loy.setItemMeta(loyMeta);
        inv.addItem(loy);
    }
    //PIERCING
    if (serverIs114()) {
        ItemStack pie = new ItemStack(UMaterial.ARROW.getMaterial());
        ItemMeta pieMeta = pie.getItemMeta();
        pieMeta.setDisplayName(ChatColor.YELLOW + "Piercing §7(" + num + ")");
        pie.setItemMeta(pieMeta);
        inv.addItem(pie);
    }
    //QUICKFIRE
    if (serverIs114()) {
        ItemStack qui = new ItemStack(UMaterial.SPECTRAL_ARROW.getMaterial());
        ItemMeta quiMeta = qui.getItemMeta();
        quiMeta.setDisplayName(ChatColor.YELLOW + "Quick Charge §7(" + num + ")");
        qui.setItemMeta(quiMeta);
        inv.addItem(qui);
    }
    //MULTISHOT
    if (serverIs114()) {
        ItemStack mul = new ItemStack(UMaterial.CROSSBOW.getMaterial());
        ItemMeta mulMeta = mul.getItemMeta();
        mulMeta.setDisplayName(ChatColor.YELLOW + "Multishot §7(" + num + ")");
        mul.setItemMeta(mulMeta);
        inv.addItem(mul);
    }
    //CURSE OF BINDING
    if (serverIs111()) {
        ItemStack bind = new ItemStack(UMaterial.SLIME_BALL.getMaterial());
        ItemMeta bindMeta = bind.getItemMeta();
        bindMeta.setDisplayName(ChatColor.RED + "Curse of Binding §7(" + num + ")");
        bind.setItemMeta(bindMeta);
        inv.addItem(bind);
    }
    //CURSE OF VANISHING
    if(serverIs111()) {
        ItemStack van = new ItemStack(UMaterial.ENDER_EYE.getMaterial());
        ItemMeta vanMeta = van.getItemMeta();
        vanMeta.setDisplayName(ChatColor.RED + "Curse of Vanishing §7(" + num + ")");
        van.setItemMeta(vanMeta);
        inv.addItem(van);
    }

    ItemStack close = new ItemStack(UMaterial.BARRIER.getMaterial());
    ItemMeta closeMeta = close.getItemMeta();
    ItemStack lvl = new ItemStack(UMaterial.EXPERIENCE_BOTTLE.getMaterial());
    ItemMeta lvlMeta = lvl.getItemMeta();    
    
    closeMeta.setDisplayName(ChatColor.RED + "Close");
    close.setItemMeta(closeMeta);

    List<String> Lore = new ArrayList<String>();
    lvlMeta.setDisplayName(ChatColor.GREEN + "Enchantment Level");
    Lore.add("§7Level: "+num);
    lvlMeta.setLore(Lore);
    lvl.setItemMeta(lvlMeta);
     
    inv.setItem(40, close);
    inv.setItem(44, lvl);

    player.openInventory(inv);

}
@SuppressWarnings("incomplete-switch")
@EventHandler
public void onInventoryClick(InventoryClickEvent event) {
    if (!ChatColor.stripColor(event.getView().getTitle())
            .equalsIgnoreCase("Enchantment Menu"))
        return;
    Player player = (Player) event.getWhoClicked();
    event.setCancelled(true);
    if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
        return;
    }
    Map<Enchantment, Integer> itemEnchants = player.getInventory().getItemInHand().getItemMeta().getEnchants();
    String n = event.getCurrentItem().getItemMeta().getDisplayName();
    String name = ChatColor.stripColor(n).toLowerCase().replace(" ", "_").replace("_("+num+")","");

    if (event.getClickedInventory().getType().equals(InventoryType.CHEST)){
    for (Enchantment ench : itemEnchants.keySet()) {
        player.getInventory().getItemInHand().getItemMeta().addEnchant(Enchantment.getByKey(NamespacedKey.minecraft(name)), num, true);
        player.sendMessage(name);
    }
    }
switch (UMaterial.match(event.getCurrentItem())) {
    case DIAMOND_SWORD:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case IRON_SWORD:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case GOLDEN_SWORD:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case STONE_SWORD:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addEnchantment(Enchantment.KNOCKBACK, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case WOODEN_SWORD:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.FIRE_ASPECT, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case BLAZE_ROD:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case DIAMOND_AXE:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case IRON_AXE:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.DURABILITY, num);
        }
}
        switch (UMaterial.match(event.getCurrentItem())) {
            case GOLDEN_AXE:
                if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
                    player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.MENDING, num);
                }
        }
        switch (UMaterial.match(event.getCurrentItem())) {
            case STONE_AXE:
                if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
                    player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.THORNS, num);
                }
        }
        switch (UMaterial.match(event.getCurrentItem())) {
            case WOODEN_AXE:
                if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
                    player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.SILK_TOUCH, num);
                }
        }
        switch (UMaterial.match(event.getCurrentItem())) {
            case DIAMOND_PICKAXE:
                if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
                    player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.DIG_SPEED, num);
                }
        }
        switch (UMaterial.match(event.getCurrentItem())) {
            case IRON_PICKAXE:
                if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
                    player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, num);
                }
        }
        switch (UMaterial.match(event.getCurrentItem())) {
            case BOW:
                if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
                    player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, num);
                }
        }
switch (UMaterial.match(event.getCurrentItem())) {
case DIAMOND_SHOVEL:
    if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
        player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_FIRE, num);
    }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case IRON_SHOVEL:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case GOLDEN_SHOVEL:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case DIAMOND_HELMET:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.WATER_WORKER, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case IRON_HELMET:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.OXYGEN, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case DIAMOND_BOOTS:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case IRON_BOOTS:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.FROST_WALKER, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case GOLDEN_BOOTS:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case DIAMOND_CHESTPLATE:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case IRON_CHESTPLATE:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case GOLDEN_CHESTPLATE:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case CHAINMAIL_CHESTPLATE:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case LEATHER_LEGGINGS:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.LUCK, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case FISHING_ROD:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.LURE, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case SLIME_BALL:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.BINDING_CURSE, num);
        }
}
    switch (UMaterial.match(event.getCurrentItem())) {
        case ENDER_EYE:
            if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
                player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.VANISHING_CURSE, num);
            }
    }
switch (UMaterial.match(event.getCurrentItem())) {
    case TRIDENT:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.CHANNELING, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case HEART_OF_THE_SEA:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.RIPTIDE, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case NAME_TAG:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.LOYALTY, num);
        }
}
switch (UMaterial.match(event.getCurrentItem())) {
    case ARROW:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.PIERCING, num);
        }
}
    switch (UMaterial.match(event.getCurrentItem())) {
        case SPECTRAL_ARROW:
            if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
                player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.QUICK_CHARGE, num);
            }
    }
switch (UMaterial.match(event.getCurrentItem())) {
    case CROSSBOW:
        if (player.getInventory().getItemInHand().getType()!=UMaterial.AIR.getMaterial()) {
            player.getInventory().getItemInHand().addUnsafeEnchantment(Enchantment.MULTISHOT, num);
        }
    }
switch (UMaterial.match(event.getCurrentItem())) {
case BARRIER:
	player.closeInventory();
    }
    }
public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("enchgui")) {			                             
        	  List<String> a = Arrays.asList("1","2","3","4","5");
        	  List<String> b = Arrays.asList("safe","unsafe");          	
        	  
        	  List<String> f = Lists.newArrayList();						                        			                                      
                 if (args.length == 1) {
                 for (String s : a) {
                 if (s.startsWith(args[0])) f.add(s);
                 }
                 return f;
                 }
                 
            List<String> g = Lists.newArrayList();						                        			                                      
                if (args.length == 2) {
                for (String s : b) {
                if (s.startsWith(args[1])) g.add(s);
                }
                return g;
                }
                	 						                         
          if (args.length > 2) {
              ArrayList<String> noInput = new ArrayList<String>();
                                                        
             noInput.add("");		                                     
             
             return noInput;		
          }
  }
	return null;
}
public static boolean isInt(String s) {
    try {
        Integer.parseInt(s);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
}

