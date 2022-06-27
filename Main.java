package me.gatiendev.pvpfac;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import static me.gatiendev.pvpfac.request.postRequest;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // ajout de tous les listeners
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new NouveauJoueur(), this);
        getServer().getPluginManager().registerEvents(new ClickInventaire(), this);
        getServer().getPluginManager().registerEvents(new InvetaireFerme(), this);
        getServer().getPluginManager().registerEvents(new MortJoueur(), this);
        getServer().getPluginManager().registerEvents(new Degat(), this);
        getServer().getPluginManager().registerEvents(new Respawn(), this);
        getServer().getPluginManager().registerEvents(new Portail(), this);
        getServer().getPluginManager().registerEvents(new BlocCasse(), this);
        getServer().getPluginManager().registerEvents(new EntityDeath(), this);
        getServer().getPluginManager().registerEvents(new EntityDegat(), this);
        getServer().getPluginManager().registerEvents(new BLockPose(), this);

        //ajout de toutes les commandes
        getCommand("wiki").setExecutor(new wiki());
        getCommand("spawn").setExecutor(new spawn());
        getCommand("leaderboard").setExecutor(new Leaderboard());


        //chargement de la map
        new WorldCreator("world").createWorld();
        Bukkit.getWorld("world").loadChunk(-14, -3);

        // mise en place des gamerules
        getServer().getWorld("lobby").setDifficulty(Difficulty.PEACEFUL);
        getServer().getWorld("lobby").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, Boolean.FALSE);
        getServer().getWorld("lobby").setTime(1000);

        // connection au serveur Node
        try {
            postRequest("http://127.0.0.1:25566/api/v1/demarrage", "up");
        } catch (IOException e) {
            System.out.println("LE SERVEUR WEB N'EST PAS DEMARRE !");
            Bukkit.shutdown();
        }
    }

    @Override
    public void onDisable() {
        try {
            postRequest("http://127.0.0.1:25566/api/v1/eteindre", "down");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
