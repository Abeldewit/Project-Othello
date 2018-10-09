package com.group11.othello.Logic;

import com.badlogic.gdx.math.Vector3;

public class GameLogic {

    final int WHITE = 1;
    final int BLACK = 2;
    final int EMPTY = 0;
    private Board board;
    public int turnCnt = 1;

    public GameLogic() {

        board = new Board();
        board.setChip(3,3, WHITE);
        board.setChip(3,4, BLACK);
        board.setChip(4,3, BLACK);
        board.setChip(4,4, WHITE);
    }

    public void changeTurn(){

        if (turnCnt == 1) {
            turnCnt = 2;
        }else if (turnCnt == 2){
            turnCnt = 1;
        }
    }

    public int getTurnStatus(){

        return turnCnt;
    }


    //checks if the black player does have a legal move
    public boolean canMoveBlack(){

        for (int x=0; x<8; x++){
            for (int y = 0; y<8; y++){

                if (checkLegalBlack(x, y, 1, 0)){
                    return true;
                }

                if (checkLegalBlack(x, y, 1, -1)){
                    return true;
                }

                if (checkLegalBlack(x, y, 0, -1)){
                    return true;
                }

                if (checkLegalBlack(x, y, -1, -1)){
                    return true;
                }

                if (checkLegalBlack(x, y, -1, 0)){
                    return true;
                }

                if (checkLegalBlack(x, y, -1, 1)){
                    return true;
                }

                if (checkLegalBlack(x, y, 0, 1)){
                    return true;
                }

                if (checkLegalBlack(x, y, 1, 1)){
                    return true;
                }

            }

        }
        return false;
       // Game Over ; end Game in GameState

    }

    //checks if the white player does have a legal move
    public boolean canMoveWhite(){

        for (int x=0; x<8; x++){
            for (int y = 0; y<8; y++){

                if (checkLegalWhite(x, y, 1, 0)){
                    return true;
                }

                if (checkLegalWhite(x, y, 1, -1)){
                    return true;
                }

                if (checkLegalWhite(x, y, 0, -1)){
                    return true;
                }

                if (checkLegalWhite(x, y, -1, -1)){
                    return true;
                }

                if (checkLegalWhite(x, y, -1, 0)){
                    return true;
                }

                if (checkLegalWhite(x, y, -1, 1)){
                    return true;
                }

                if (checkLegalWhite(x, y, 0, 1)){
                    return true;
                }

                if (checkLegalWhite(x, y, 1, 1)){
                    return true;
                }


            }

        }
        return false;
       // Game Over ; end Game in GameState

    }



    //places black chip in board
    public boolean placeChipBlack(int x, int y) {

        //keeps track if there is any legal move
        boolean wasLegal = false;

        if (checkLegalBlack(x, y, 1, 0)) {

            turnChipBlack(x, y, 1, 0);
            wasLegal = true;
        }

        if (checkLegalBlack(x, y, 1, 1)) {

            turnChipBlack(x, y, 1, 1);
            wasLegal = true;
        }

        if (checkLegalBlack(x, y, 1, -1)) {

            turnChipBlack(x, y, 1, -1);
            wasLegal = true;
        }

        if (checkLegalBlack(x, y, 0, -1)) {

            turnChipBlack(x, y, 0, -1);
            wasLegal = true;
        }

        if (checkLegalBlack(x, y, -1, -1)) {

            turnChipBlack(x, y, -1, -1);
            wasLegal = true;
        }

        if (checkLegalBlack(x, y, -1, 0)) {

            turnChipBlack(x, y, -1, 0);
            wasLegal = true;
        }

        if (checkLegalBlack(x, y, -1, 1)) {

            turnChipBlack(x, y, -1, 1);
            wasLegal = true;
        }

        if (checkLegalBlack(x, y, 0, 1)) {

            turnChipBlack(x, y, 0, 1);
            wasLegal = true;
        }

        if (wasLegal){
            board.setChip(x,y, BLACK);
            return true;
        }

        return false;

    }

    public boolean placeChipWhite(int x, int y) {

        boolean wasLegal = false;
        if (checkLegalWhite(x, y, 1, 0)) {

            turnChipWhite(x, y, 1, 0);
            wasLegal = true;
        }

        if (checkLegalWhite(x, y, 1, 1)) {

            turnChipWhite(x, y, 1, 1);
            wasLegal = true;
        }

        if (checkLegalWhite(x, y, 1, -1)) {

            turnChipWhite(x, y, 1, -1);
            wasLegal = true;
        }

        if (checkLegalWhite(x, y, 0, -1)) {

            turnChipWhite(x, y, 0, -1);
            wasLegal = true;
        }

        if (checkLegalWhite(x, y, -1, -1)) {

            turnChipWhite(x, y, -1, -1);
            wasLegal = true;
        }

        if (checkLegalWhite(x, y, -1, 0)) {

            turnChipWhite(x, y, -1, 0);
            wasLegal = true;
        }

        if (checkLegalWhite(x, y, -1, 1)) {

            turnChipWhite(x, y, -1, 1);
            wasLegal = true;
        }

        if (checkLegalWhite(x, y, 0, 1)) {

            turnChipWhite(x, y, 0, 1);
            wasLegal = true;
        }

        if (wasLegal){
            board.setChip(x,y, WHITE);
            return true;
        }

        return false;

    }


    public boolean checkLegalBlack (int x, int y, int dx, int dy){

        int nSteps = 1;
        x += dx;
        y += dy;

        while ( 0<=x && x<8 && 0<=y && y<8) {

            if ( board.getChip(x,y)== BLACK) {
                if (nSteps > 1) return true;
            else
                return false;
            }

            if (board.getChip(x,y) == EMPTY) return false;

            x = x + dx;
            y = y + dy;
            nSteps =  nSteps + 1;
        }

        return false;

    }

    public boolean checkLegalWhite (int x, int y, int dx, int dy){

        int nSteps = 1;
        x += dx;
        y += dy;

        while ( 0<=x && x<8 && 0<=y && y<8) {

            if ( board.getChip(x,y) == WHITE)
                if (nSteps > 1) return true;
            else
                return false;


            if (board.getChip(x,y) == EMPTY) return false;

            x = x + dx;
            y = y + dy;
            nSteps =  nSteps + 1;
        }


        return false;

    }

    public void turnChipBlack(int x, int y, int dx, int dy){

        x += dx;
        y += dx;

        while ( board.getChip(x,y) == WHITE ){

        board.setChip(x, y, BLACK);
        x += dx;
        y += dx;

        }

    }

    public void turnChipWhite(int x, int y, int dx, int dy){

        x += dx;
        y += dx;

        while ( board.getChip(x,y) == BLACK ){

        board.setChip(x,y, WHITE);
        x += dx;
        y += dx;

        }


    }


    public int rightDirection(int column, int row, int player)
    {
        int counter = 0;
        boolean check = false;
        if(column < 8) {

            for (int j = column + 1; j < 8; j++) {
                //System.out.println("BOB :" + board.getBoard()[row][j]);
                //System.out.println("bILL " + player);

                if (board.getBoard()[row][j] == player && j - column > 1) {
                    for (int i = column + 1; i < j; i++) {
                        System.out.println("GOtcha");
                        if (player == 2) {
                            board.setChip(i, row, 2);
                        } else if (player == 1) {
                            board.setChip(i, row, 1);
                        }
                    }
                }
            }
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {

                System.out.print(board.getChip(i,j));
                }
                System.out.println();
        }
        }
        System.out.println("counter: " + counter);

        return counter+1;

    }

    public int leftDirection(int row, int column, int player)
    {
        int counter = 0;
        boolean check = false;
        if (column > 0)
            for(int j = column-1; j > 0; j--)
            {
                if(board.getBoard()[row][j] == player)
                {
                    check = true;
                }
                else
                {
                    counter--;
                }
            }
        if(check == true) {
            for (int j = column - 1; j > 0; j--)

                if (board.getBoard()[row][j] != player && board.getBoard()[row][j] != 0) {
                    board.getBoard()[row][j] = player;
                    counter++;
                }
        }

        System.out.println("counter: " + counter);

        return counter+1;

    }

    public int upDirection(int row, int column, int player)
    {
        int counter = 0;
        boolean check = false;
        if(row > 0) {
            for (int j = column - 1; j > 0; j--) {
                if (board.getBoard()[row][j] == player) {
                    check = true;
                } else {
                    counter--;
                }
            }
            if (check == true) {
                for (int j = row - 1; j > 0; j--) {
                    if (board.getBoard()[j][column] != player && board.getBoard()[j][column] != 0) {
                        board.getBoard()[j][column] = player;
                        counter++;
                    }
                }
            }

        }
        return counter+1;

    }

    public int downDirection(int row, int column, int player) {
        int counter = 0;
        boolean check = false;
        if (row < 8) {
            if (row > 0) {
                for (int j = column - 1; j > 0; j--) {
                    if (board.getBoard()[j][column] == player) {
                        check = true;
                    } else {
                        counter--;
                    }
                }
                if (check == true) {
                    for (int j = row + 1; j < 8; j++) {
                        if (board.getBoard()[j][column] != player && board.getBoard()[j][column] != 0) {
                            board.getBoard()[j][column] = player;
                            counter++;
                        }
                    }
                }
            }


            return counter + 1;

        }
        return counter + 1;
    }


    public Board getBoard()
    {
        return board;
    }








}
