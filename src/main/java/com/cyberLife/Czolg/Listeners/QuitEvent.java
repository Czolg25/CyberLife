package com.cyberLife.Czolg.Listeners;

import com.cyberLife.Czolg.Data.PlayerData;
import com.cyberLife.Czolg.Data.PlayerMeneger;
import com.cyberLife.Czolg.Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class QuitEvent implements Listener {
    private static final PlayerMeneger playerMeneger = Main.getPlayerMeneger();

    @EventHandler
    public void quitEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        PlayerData playerData = playerMeneger.getPlayerData(player);
        playerData.quit();
    }
}
