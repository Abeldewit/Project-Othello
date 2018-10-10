package com.group11.othello.Logic;

public class Player2 extends Player{

    private int score;

    public Player2()
    {
        score = 2;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void subtractScore(int score)
    {
        this.score -= score;
    }
}
