package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static me.gatiendev.pvpfac.request.postRequest;

public class ClickInventaire implements Listener {
    @EventHandler
    public void onCLick(InventoryClickEvent e) throws IOException {

        Map<String, String> dico_couleur = new HashMap<String, String>();
        dico_couleur.put("Bleu", "§3");
        dico_couleur.put("Vert", "§a");
        dico_couleur.put("Rouge", "§c");
        dico_couleur.put("Jaune", "§e");
        if(e.getRawSlot() == 45 && e.getInventory().getType().equals(InventoryType.CRAFTING)) {
            e.setCancelled(true);
        }

        if(e.getView().getTitle().equals("§8Choisis ta faction")){
            if(e.getCurrentItem() == null){
                return;
            }
           e.setCancelled(true);
           String block = ((Banner)((BlockStateMeta) e.getCurrentItem().getItemMeta()).getBlockState()).getBaseColor().toString();
           String player = e.getWhoClicked().getName();
           if(block.equals("AIR")){
               return;
           }
           String equipe = postRequest("http://127.0.0.1:25566/api/v1/join_faction", block+"|"+player);
           Player p = (Player) e.getWhoClicked();
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
           p.setDisplayName("§7["+dico_couleur.get(equipe)+equipe.toUpperCase(Locale.ROOT)+"§7] §7"+p.getName());
           p.setPlayerListName("§7["+dico_couleur.get(equipe)+equipe.toUpperCase(Locale.ROOT)+"§7] §f"+p.getName());
           Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("pvpFac"), new Runnable() {
                @Override
                public void run() {
                    p.closeInventory();
                    p.getInventory().setItemInOffHand(Shield);
                }
            }, 2L);
        }
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e){
        e.setCancelled(true);
    }
}
