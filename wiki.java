package me.gatiendev.pvpfac;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class wiki implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            p.sendMessage("§fLien du wiki : §3https://wiki.boko.ml");
        }
        return true;
    }
}
