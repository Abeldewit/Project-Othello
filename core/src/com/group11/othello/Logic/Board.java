package com.group11.othello.Logic;

public class Board {

    private int[][] board = new int[7][7];



    public Board()
    {

    }

    public void setChip(int i, int j, int player)
    {
        board [i][j] = player;
    }

    public int getChip(int i,int j)
    {
        int chip;
        chip = board [i][j];
        return chip;
    }






}