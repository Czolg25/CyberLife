package com.cyberLife.Czolg.Commands;

import com.cyberLife.Czolg.Data.PlayerData;
import com.cyberLife.Czolg.Data.PlayerMeneger;
import com.cyberLife.Czolg.Main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimeCommand implements CommandExecutor {
    private static final PlayerMeneger playerMeneger = Main.getPlayerMeneger();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("playertime")){
            if(args.length != 1){
                commandSender.sendMessage("§cUzycie /playertime <nick>");
                return true;
            }

            PlayerData playerData = playerMeneger.getPlayerData(args[0]);
            if(playerData == null){
                commandSender.sendMessage("§cGracz "+args[0]+" nie istnieje!");
                return true;
            }

            commandSender.sendMessage(playerData.toString());
            return true;
        }
        if(command.getName().equalsIgnoreCase("playertimeset")){
            if(args.length != 2){
                commandSender.sendMessage("§cUzycie /playertime <nick> <ile sekund>");
                return true;
            }

            PlayerData playerData = playerMeneger.getPlayerData(args[0]);
            if(playerData == null){
                commandSender.sendMessage("§cGracz "+args[0]+" nie istnieje!");
                return true;
            }

            Long time = getLong(commandSender,args[1]);
            if(time == null) return true;

            commandSender.sendMessage("§aZmieniles czas graczowi "+time+".");
            playerData.reset(time);
            return true;
        }
        return false;
    }

    private Long getLong(CommandSender commandSender,String longString){
        try {
            return Long.parseLong(longString);
        }catch (NumberFormatException exception){
            commandSender.sendMessage("§c"+longString+" nie jest liczba!");
            return null;
        }
    }
}
