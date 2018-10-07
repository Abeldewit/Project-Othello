package com.group11.othello.Logic;

public class Player1 extends Player {
    private int score;

    public Player1()
    {
        score = 2;
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
