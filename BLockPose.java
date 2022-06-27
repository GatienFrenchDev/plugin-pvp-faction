package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BLockPose implements Listener {
    @EventHandler
    public void onBlocPose(BlockPlaceEvent e){
        if(e.getPlayer().isOp()){
            return;
        }
        if(e.getBlock().getWorld().equals(Bukkit.getWorld("lobby")) || e.getBlock().getWorld().equals(Bukkit.getWorld("world")) && e.getBlock().getLocation().getBlockX()>-30 && e.getBlock().getLocation().getBlockX()<18 && e.getBlock().getLocation().getBlockZ()<21 && e.getBlock().getLocation().getBlockZ()>-15){
            e.setCancelled(true);
            return;
        }
    }
}
