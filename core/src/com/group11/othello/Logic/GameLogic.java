package com.group11.othello.Logic;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
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
                return nSteps > 1;
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
                return nSteps > 1;


            if (board.getChip(x,y) == EMPTY) return false;

            x = x + dx;
            y = y + dy;
            nSteps =  nSteps + 1;
        }


        return false;

    }

    public void turnChipBlack(int x, int y, int dx, int dy){

        x += dx;
        y += dy;

        while ( board.getChip(x,y) == WHITE ){

        board.setChip(x, y, BLACK);
        x += dx;
        y += dy;

        }

    }

    public void turnChipWhite(int x, int y, int dx, int dy){

        x += dx;
        y += dy;

        while ( board.getChip(x,y) == BLACK ){

        board.setChip(x,y, WHITE);
        x += dx;
        y += dy;

        }


    }

    public void rightDirection(int column, int row, int player)
    {
        if(column < 6) {

            for (int j = column + 1; j < 8; j++) {
                if(board.getBoard()[row][j] != 0 && board.getBoard()[row][column+1] != player)
                {
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
                else
                {
                    break;
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

    }

    public void leftDirection(int column, int row, int player)
    {

        if (column > 1) {


                for (int j = column - 1; j >= 0; j--) {

                    if(board.getBoard()[row][j] != 0 && board.getBoard()[row][column-1] != player)
                    {
                        if (board.getBoard()[row][j] == player && column - j > 1)
                        {
                            for (int i = column - 1; i > j; i--)
                            {
                                System.out.println("GOtcha");
                                if (player == 2) {
                                    board.setChip(i, row, 2);
                                } else if (player == 1) {
                                    board.setChip(i, row, 1);
                                }
                            }
                        }
                    }else
                    {
                        break;
                    }
                }
            }

    }

    public void upDirection(int column, int row, int player)
    {

        if(row < 6) {
            {
                for (int j = row +1; j < 8; j++) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row+1][column] != player) {

                        if (board.getBoard()[j][column] == player && j - row > 1) {
                            System.out.println("gotcha");
                            for (int i = row + 1; i < j; i++) {
                                if (player == 2) {
                                    board.setChip(column, i, 2);
                                } else if (player == 1) {
                                    board.setChip(column, i, 1);
                                }
                            }
                        }
                    }else
                    {
                        break;
                    }
                }
            }

        }

    }

    public void downDirection(int column, int row, int player) {
        int counter = 0;
        if(row >1) {
            {
                for (int j = row-1; j >=0; j--) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row -1][column] != player) {
                        if (board.getBoard()[j][column] == player && row - j > 1) {
                            for (int i = row - 1; i > j; i--) {
                                if (player == 2) {
                                    board.setChip(column, i, 2);
                                } else if (player == 1) {
                                    board.setChip(column, i, 1);
                                }
                            }
                        }
                    }else
                    {
                        break;
                    }
                }
            }

        }
    }

    public void northWestDirection(int column, int row, int player) {

        if(row >1 && column >1)
            {
                for (int i = row-1; i >=0; i--) {
                    for(int j = column-1; column > 0; j--)
                    {
                        if (board.getBoard()[i][j] != 0 && board.getBoard()[i][j] != player && row - i > 1 && column - j > 1) {
                                if(getTurnStatus() == 1)
                                {
                                    board.setChip(i,j,1);
                                }
                            if(getTurnStatus() == 2)
                            {
                                board.setChip(i,j,2);
                            }
                        }else
                        {
                            break;
                        }
                    }

            }

        }

    }


    public Board getBoard()
    {
        return board;
    }

    public Vector3 getScore()
    {
        Vector3 v = new Vector3();
        for(int i = 0; i < board.getBoard().length -1; i++)
        {
            for(int j = 0; j < board.getBoard().length-1; j++ )
            {
                if(board.getBoard()[i][j] == 1)
                {
                    v.add(1,0,0);
                }

                if(board.getBoard()[i][j] == 2)
                {
                    v.add(0,1,0);
                }


            }
        }
        return  v;
    }

    public boolean checkLegalMove(int x, int y, int player) {
        Vector2 currentSquare = new Vector2(x,y);
        if(board.getChip(x,y) == 0){
            //check every square around the current square
            for(int n = 0; n < 8; n++) {
                Vector2 direction;
                switch (n) {
                    case 1:  direction = new Vector2(-1,0);
                    break;
                    case 2:  direction = new Vector2(-1,1);
                    break;
                    case 3:  direction = new Vector2(0,1);
                    break;
                    case 4:  direction = new Vector2(1,1);
                    break;
                    case 5:  direction = new Vector2(1,0);
                    break;
                    case 6:  direction = new Vector2(1,-1);
                    break;
                    case 7:  direction = new Vector2(0,-1);
                    break;
                    case 8:  direction = new Vector2(-1,-1);
                    break;
                    default: direction = new Vector2(0,0);
                    break;
                }

                Vector2 newSquare = currentSquare.add(direction);
                //see iff the square we're checking is the other color and not empty
                while(board.getChip((int)newSquare.x,(int)newSquare.y) != player && board.getChip((int)newSquare.x,(int)newSquare.y) != 0) {
                    newSquare.add(direction);
                    if(newSquare.x > 8 || newSquare.y > 8) {
                        return false;
                    }
                    if(board.getChip((int)newSquare.x,(int)newSquare.y) == player) {
                        return true;

                }

            }
        }


    }








}
