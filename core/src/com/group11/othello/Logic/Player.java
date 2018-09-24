package com.group11.othello.Logic;

public abstract class Player {

    private int playerScore;

    public Player()
    {
        this.playerScore =  0;
    }

    public void setPlayerScore(int newScore)
    {
        playerScore = newScore;
    }


    public int getPlayerScore()
    {
        return playerScore;
    }
}
