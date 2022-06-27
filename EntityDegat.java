package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDegat implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL && e.getEntity().getWorld().equals(Bukkit.getWorld("lobby"))){
            e.setCancelled(true);
            return;
        }
    }
}
