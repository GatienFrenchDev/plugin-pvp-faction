package me.gatiendev.pvpfac;

import org.bukkit.*;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static me.gatiendev.pvpfac.request.postRequest;

public class NouveauJoueur implements Listener {

    @EventHandler
    public void onNewPlayer(PlayerJoinEvent e) throws IOException {
        Map<String, String> dico_couleur = new HashMap<String, String>();
        dico_couleur.put("Bleu", "§3");
        dico_couleur.put("Vert", "§a");
        dico_couleur.put("Rouge", "§c");
        dico_couleur.put("Jaune", "§e");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "weather clear 1000");
        e.getPlayer().setGameMode(GameMode.ADVENTURE);
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 2));
        e.setJoinMessage("§7[§a+§7] "+e.getPlayer().getName());
        e.getPlayer().teleport(new Location(Bukkit.getWorld("lobby"), 123, 6, -1127));
        String equipe = postRequest("http://127.0.0.1:25566/api/v1/connection", e.getPlayer().getName());
        if(equipe.equals("pas_equipe")){
            Inventory choix_faction = Bukkit.createInventory(null, 9, "§8Choisis ta faction");
            ItemStack Shield = new ItemStack(Material.SHIELD);
            Shield.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
            ItemMeta ShieldM = Shield.getItemMeta();
            ShieldM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            ShieldM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            ShieldM.setUnbreakable(true);
            Banner banner = (Banner) ((BlockStateMeta) ShieldM).getBlockState();

            ShieldM.setDisplayName("§3Bleu");
            banner.setBaseColor(DyeColor.BLUE);
            banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.GLOBE));
            banner.update();
            ((BlockStateMeta) ShieldM).setBlockState(banner);
            Shield.setItemMeta(ShieldM);
            choix_faction.addItem(Shield);

            ShieldM.setDisplayName("§aVert");
            banner.setBaseColor(DyeColor.GREEN);
            banner.addPattern(new Pattern(DyeColor.BLACK, PatternType.CREEPER));
            banner.update();
            ((BlockStateMeta) ShieldM).setBlockState(banner);
            Shield.setItemMeta(ShieldM);
            choix_faction.addItem(Shield);

            ShieldM.setDisplayName("§cRouge");
            banner.setBaseColor(DyeColor.RED);
            banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.FLOWER));
            banner.update();
            ((BlockStateMeta) ShieldM).setBlockState(banner);
            Shield.setItemMeta(ShieldM);
            choix_faction.addItem(Shield);

            ShieldM.setDisplayName("§eJaune");
            banner.setBaseColor(DyeColor.YELLOW);
            banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.MOJANG));
            banner.update();
            ((BlockStateMeta) ShieldM).setBlockState(banner);
            Shield.setItemMeta(ShieldM);
            choix_faction.addItem(Shield);

            e.getPlayer().openInventory(choix_faction);
        }
        else{
            ItemStack Shield = new ItemStack(Material.SHIELD);
            Shield.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
            ItemMeta ShieldM = Shield.getItemMeta();
            ShieldM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            ShieldM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            ShieldM.setUnbreakable(true);
            Banner banner = (Banner) ((BlockStateMeta) ShieldM).getBlockState();
            switch (equipe){
                case "Bleu":
                    banner.setBaseColor(DyeColor.BLUE);
                    banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.GLOBE));
                    break;
                case "Vert":
                    banner.setBaseColor(DyeColor.GREEN);
                    banner.addPattern(new Pattern(DyeColor.BLACK, PatternType.CREEPER));
                    break;
                case "Rouge":
                    banner.setBaseColor(DyeColor.RED);
                    banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.FLOWER));
                    break;
                case "Jaune":
                    banner.setBaseColor(DyeColor.YELLOW);
                    banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.MOJANG));
                    break;
            }
            banner.update();
            ((BlockStateMeta) ShieldM).setBlockState(banner);
            Shield.setItemMeta(ShieldM);
            e.getPlayer().getInventory().setItemInOffHand(Shield);
            e.getPlayer().setDisplayName("§7["+dico_couleur.get(equipe)+equipe.toUpperCase(Locale.ROOT)+"§7] §7"+e.getPlayer().getName());
            e.getPlayer().setPlayerListName("§7["+dico_couleur.get(equipe)+equipe.toUpperCase(Locale.ROOT)+"§7] §f"+e.getPlayer().getName());
            e.getPlayer().sendMessage("§l§c============");
            e.getPlayer().sendMessage("§fBienvenue sur le serveur !");
            e.getPlayer().sendMessage("§fN'hésite pas à consulter le §3§l/wiki");
            e.getPlayer().sendMessage("§l§c============");
        }
    }

}
