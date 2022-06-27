package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {
    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e){
        e.setCancelled(true);
        String message = e.getMessage();
        Bukkit.broadcastMessage(e.getPlayer().getDisplayName() +" » §f"+message);
    }
}
