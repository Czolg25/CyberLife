package com.cyberLife.Czolg.Data;

import org.bukkit.configuration.file.FileConfiguration;

import java.sql.*;
import java.util.HashMap;

public class SQL {
    private static String host;
    private static String port;
    private static String database;
    private static String login;
    private static String password;
    private static Connection connection;

    public static void setUp(FileConfiguration fileConfiguration){
        host = fileConfiguration.getString("mysql.host");
        port = fileConfiguration.getString("mysql.port");
        database = fileConfiguration.getString("mysql.database");
        login = fileConfiguration.getString("mysql.login");
        password = fileConfiguration.getString("mysql.password");
    }

    public static void updateTimeInGame(String player,long time,long weekTime,long monthTime){
        String question = "UPDATE `time` INNER JOIN authme ON time.id_nick = authme.id  SET time.time = "+time+
                ",time.week-time = "+weekTime+",time.month-time = "+monthTime+" WHERE authme.realname = '"+player+"';";

        try {
            connect();

            PreparedStatement preparedStatement = connection.prepareStatement(question);
            preparedStatement.executeUpdate();

            disconnect();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public static HashMap<String,PlayerData> getPlayers(){
        HashMap<String,PlayerData> playerDataHashMap = new HashMap<>();
        String question = "SELECT authme.realname,`time`, `week-time`, `month-time` FROM `time` INNER JOIN authme ON time.id_nick = authme.id;";
        String name;

        try {
            connect();

            ResultSet resultSet = connection.createStatement().executeQuery(question);
            while (resultSet.next()) {
                name = resultSet.getString("realname");
                playerDataHashMap.putIfAbsent(name,new PlayerData(name,resultSet.getLong("time"),resultSet.getLong("week-time"),
                        resultSet.getLong("month-time")));
            }

            disconnect();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return playerDataHashMap;
    }
    //SELECT authme.realname,`time`, `week-time`, `month-time` FROM `time` INNER JOIN authme ON time.id_nick = authme.id;

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database, login, password);
    }
    private static void disconnect() throws SQLException {
        connection.close();
    }
}
