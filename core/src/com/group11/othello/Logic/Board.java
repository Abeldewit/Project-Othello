package com.group11.othello.Logic;

public class Board {

    private Cell[][] board = new Cell[8][8];

    public Board() {
        //setup of cells
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }
}
