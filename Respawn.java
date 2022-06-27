package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;

public class Respawn implements Listener{

    @EventHandler
    public void Respawn(PlayerRespawnEvent e){
        String equipe = e.getPlayer().getDisplayName().split("§")[2];
        equipe.substring(1);
        ItemStack Shield = new ItemStack(Material.SHIELD);
        Shield.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
        ItemMeta ShieldM = Shield.getItemMeta();
        ShieldM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ShieldM.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        ShieldM.setUnbreakable(true);
        Banner banner = (Banner) ((BlockStateMeta) ShieldM).getBlockState();
        switch (equipe){
            case "3BLEU":
                banner.setBaseColor(DyeColor.BLUE);
                banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.GLOBE));
                break;
            case "aVERT":
                banner.setBaseColor(DyeColor.GREEN);
                banner.addPattern(new Pattern(DyeColor.BLACK, PatternType.CREEPER));
                break;
            case "cROUGE":
                banner.setBaseColor(DyeColor.RED);
                banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.FLOWER));
                break;
            case "eJAUNE":
                banner.setBaseColor(DyeColor.YELLOW);
                banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.MOJANG));
                break;
        }
        banner.update();
        ((BlockStateMeta) ShieldM).setBlockState(banner);
        Shield.setItemMeta(ShieldM);
        e.getPlayer().getInventory().setItemInOffHand(Shield);
        e.setRespawnLocation(new Location(Bukkit.getWorld("lobby"), 123, 6, -1127));
    }

}
