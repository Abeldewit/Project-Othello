package com.group11.othello.Logic;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;
import java.util.Vector;

public class Board {

    private int[][] board;
    Random random;


    public Board()
    {   //Representation of board 8x8 of othello
        board = new int[9][9];

    }
    public Board(int[][] board){
        this.board = board;
    }

    public void setChip(int i, int j, int player)
    {
        board [i][j] = player;
    }

    public int getChip(int i,int j)
    {
        int chip;
        chip = board[i][j];
        return chip;
    }

    public int getChipVector(Vector2 vector) {
        int x = (int) vector.x;
        int y = (int) vector.y;
        int chip = board[x][y];
        return chip;
    }

    public int[][] getBoard()
    {
        return board;
    }
//This was the previous print board method, change to the one below that maches the ui board
//    public void printBoardOLD()
//    {
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//
//                System.out.print(getChip(i, j));
//            }
//            System.out.println();
//        }
//    }
    //print board machine the orientation of the ui
    public void printBoard()
    {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {

                System.out.print(getChip(i, j));
            }
            System.out.println();
        }
    }

    public Board copy(){
        int[][] newBoard = new int[board.length][board[0].length];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                newBoard[i][j]=board[i][j];
            }
        }
        return new Board(newBoard);
    }

}