package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InvetaireFerme implements Listener{
    @EventHandler
    public void onInventaireClose(InventoryCloseEvent e){
        if(e.getView().getTitle().equals("§8Choisis ta faction")){
            Player p = (Player) e.getPlayer();
            if(p.getDisplayName().startsWith("§")){
                return;
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("pvpFac"), new Runnable(){
                @Override
                public void run(){
                    p.openInventory(e.getInventory());
                }
            }, 0L);
        }
    }
}
