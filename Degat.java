package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Degat implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){
        if(e.getDamager().getType().equals(EntityType.PLAYER) && e.getEntity().getType().equals(EntityType.PLAYER)){
            if (e.getEntity().getWorld().equals(Bukkit.getWorld("lobby")) || ((Player) e.getDamager()).getDisplayName().split("§")[2].equals(((Player) e.getEntity()).getDisplayName().split("§")[2]) || e.getDamager().getWorld().equals(Bukkit.getWorld("world")) && e.getDamager().getLocation().getBlockX()>-30 && e.getDamager().getLocation().getBlockX()<18 && e.getDamager().getLocation().getBlockZ()<21 && e.getDamager().getLocation().getBlockZ()>-15){
                e.setCancelled(true);
                return;
            }
        }
    }
}