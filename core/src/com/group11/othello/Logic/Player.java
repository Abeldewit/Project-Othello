package com.group11.othello.Logic;

//todo: FIX BAD USE OF ABSTRACT CLASSES IN PLAYER
public abstract class Player {

    private int score;

    public Player()
    {
        this.score = 2;
    }

    public int getScore()
    {
        return score;
    }

    public void addScore(int score)
    {
        this.score += score;
    }

    public void subtractScore(int score)
    {
        this.score -= score;
    }

}
