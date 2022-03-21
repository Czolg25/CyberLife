package com.cyberLife.Czolg.Data;

public class PlayerData {
    private final String name;

    private long time;
    private long timeInGame;
    private long weekTimeInGame;
    private long monthTimeInGame;

    public PlayerData(String name){
        this.name = name;

        this.timeInGame = 0;
        this.weekTimeInGame = 0;
        this.monthTimeInGame = 0;
    }


    public PlayerData(String name,long timeInGame,long weekTimeInGame,long monthTimeInGame){
        this.name = name;

        this.timeInGame = timeInGame;
        this.weekTimeInGame = weekTimeInGame;
        this.monthTimeInGame = monthTimeInGame;
    }

    public void join(){
        this.time = System.currentTimeMillis() / 1000;
    }

    public void quit(){
        long diffrence = System.currentTimeMillis() / 1000 - this.time;

        this.timeInGame += diffrence;
        this.weekTimeInGame += diffrence;
        this.monthTimeInGame += diffrence;

        SQL.updateTimeInGame(name,timeInGame,weekTimeInGame,monthTimeInGame);
    }

    public void reset(long time){
        this.timeInGame = time;
        this.weekTimeInGame = time;
        this.monthTimeInGame = time;

        SQL.updateTimeInGame(name,timeInGame,weekTimeInGame,monthTimeInGame);
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "name='" + name + '\'' +
                ", timeInGame=" + timeInGame +
                ", weekTimeInGame=" + weekTimeInGame +
                ", monthTimeInGame=" + monthTimeInGame +
                '}';
    }
}
