package com.group11.othello.Logic;

public class Board {

    private int[][] board = new int[9][9];



    public Board()
    {

    }

    public void setChip(int i, int j, int player)
    {
        board [j][i] = player;
    }

    public int getChip(int i,int j)
    {
        int chip;
        chip = board [i][j];
        return chip;
    }

    public int[][] getBoard()
    {
        return board;
    }






}