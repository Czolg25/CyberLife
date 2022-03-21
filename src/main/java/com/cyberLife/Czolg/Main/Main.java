package com.cyberLife.Czolg.Main;

import com.cyberLife.Czolg.Commands.TimeCommand;
import com.cyberLife.Czolg.Data.PlayerMeneger;
import com.cyberLife.Czolg.Listeners.JoinEvent;
import com.cyberLife.Czolg.Listeners.QuitEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static final PlayerMeneger playerMeneger = new PlayerMeneger();

    public static PlayerMeneger getPlayerMeneger() {
        return playerMeneger;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        playerMeneger.setUp();
        setUpListeners();
    }

    private void setUpListeners(){
        Bukkit.getPluginManager().registerEvents(new JoinEvent(),this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(),this);
    }

    private void setUpCommand(){
        TimeCommand timeCommand = new TimeCommand();
        Bukkit.getPluginCommand("playertime").setExecutor(timeCommand);
        Bukkit.getPluginCommand("playertimeset").setExecutor(timeCommand);
    }
}
