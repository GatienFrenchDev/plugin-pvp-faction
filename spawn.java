package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class spawn implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            p.sendTitle("§3§kS§r§3 Téléportation en cours... §r§3§kS", "§f§3Merci de ne pas bouger", 20, 30, 20);
            double pv = p.getHealth();
            int x = p.getLocation().getBlockX();
            int y = p.getLocation().getBlockY();
            Bukkit.getServer().getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("pvpFac"), new Runnable(){
                @Override
                public void run(){
                    if(pv - p.getHealth() > 0 || x != p.getLocation().getBlockX() || y!= p.getLocation().getBlockY()){
                        p.sendMessage("téléportation annulée !");
                    }
                    else{
                        p.teleport(new Location(Bukkit.getWorld("lobby"), 123, 6, -1127));
                        p.playEffect(p.getLocation(), Effect.BREWING_STAND_BREW, null);
                        p.setGameMode(GameMode.ADVENTURE);
                    }
                }
            }, 60L);

        }
        return true;
    }

}
