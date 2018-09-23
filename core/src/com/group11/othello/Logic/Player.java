package com.group11.othello.Logic;

public class Player {
    private String playerColor;
    private int playerScore;

    public Player(String playerColor) {
        this.playerColor = playerColor;
        this.playerScore =  0;
    }

    public void setPlayerScore(int newScore) {
        playerScore = newScore;
    }
    public int getPlayerScore() {
        return playerScore;
    }
}
