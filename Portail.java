package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class Portail implements Listener{

    @EventHandler
    public void NetherPortal(PlayerPortalEvent e){
        if(e.getPlayer().getWorld().equals(Bukkit.getWorld("world"))){
            e.getPlayer().teleport(new Location(Bukkit.getWorld("lobby"), 115, 14, -1079));
            e.getPlayer().setGameMode(GameMode.ADVENTURE);
        }
        else{
            e.getPlayer().teleport(new Location(Bukkit.getWorld("world"), -2, 154, 3));
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
        }

    }

}
