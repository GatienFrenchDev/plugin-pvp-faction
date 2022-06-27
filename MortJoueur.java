package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.concurrent.ThreadLocalRandom.*;
import static me.gatiendev.pvpfac.request.postRequest;

public class MortJoueur implements Listener{
    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        if(e.getEntity().getKiller() instanceof Player){
            try {
                postRequest("http://127.0.0.1:25566/api/v1/mort", e.getEntity().getName()+"&tueur="+e.getEntity().getKiller().getName());
            } catch (IOException err) {
                err.printStackTrace();
            }
            int random = (Math.random() <= 0.5) ? 1 : 2;
            if(random == 1) {
                e.getDrops().add(new ItemStack(Material.NETHER_STAR, 1));
            }
            e.setDeathMessage(e.getEntity().getDisplayName()+"§7 s'est fait tuer par "+e.getEntity().getKiller().getDisplayName());
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(e.getEntity().getLocation(), Sound.ENTITY_WITHER_SPAWN, 1f, 1f);
            }
        }
        else{
            e.setDeathMessage("§r§e"+e.getEntity().getName()+"§7 a appris sa note au DS de Boko");
        }
    }
}
