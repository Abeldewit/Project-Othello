package com.group11.othello.Logic;

import java.util.ArrayList;

public class Board {

    private int[][] board = new int[7][7];



    public Board()
    {
        for(int i = 0; i < 8; )
        {
            for(int j = 0; j < 8; )
            {
                board[i][j] = 0;
            }
        }


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
    /*public void setCount(Chip chip)
    {
        chipCount.add(chip);
    }

    public int getCount()
    {
        int blackChipCount = 0;
     for(int i = 0; i < chipCount.size()-1; i++ )
     {
         if(chipCount.get(i).getColor().substring(0) == "B")
         {
             blackChipCount++;
         }
     }
        return blackChipCount;
    }




*/

}