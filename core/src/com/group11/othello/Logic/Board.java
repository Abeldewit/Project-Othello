package com.group11.othello.Logic;

public class Board {

    private int[][] board = new int[8][8];

    public Board()
    {
        for(int i = 0; i < 9; )
        {
            for(int j = 0; j < 9; )
            {
                board[i][j] = 0;
            }
        }
    }

    public void setChip(int i, int j, int player)
    {
        board [i][j] = player;
    }





}
