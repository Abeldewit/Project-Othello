package com.group11.othello.Logic;

public abstract class Player {
    private String color;
    private int score;

    public Player()
    {

        score = 0;
    }

    public abstract int getScore();


    public abstract void addScore(int score);

    public abstract void subtractScore(int score);




}
