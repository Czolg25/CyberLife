package com.cyberLife.Czolg.Data;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerMeneger {
    private HashMap<String,PlayerData> playerDataHashMap;

    public void setUp(){
        this.playerDataHashMap = SQL.getPlayers();
    }

    public void join(Player player){
        playerDataHashMap.putIfAbsent(player.getName(),new PlayerData(player.getName()));

        PlayerData playerData = playerDataHashMap.get(player.getName());
        playerData.join();
    }

    public PlayerData getPlayerData(Player player){
        return playerDataHashMap.get(player.getName());
    }
    public PlayerData getPlayerData(String player){
        return playerDataHashMap.get(player);
    }
}
