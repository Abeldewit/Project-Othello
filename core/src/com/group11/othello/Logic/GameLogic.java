package com.group11.othello.Logic;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.AI.Point;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    final int WHITE = 1;
    final int BLACK = 2;
    final int EMPTY = 0;
    private  Board board;
    public static boolean[][] legalMoves;
    public static int turnCnt;


    public GameLogic() {

        board = new Board();
        turnCnt =1;
        board.setChip(3, 3, WHITE);
        board.setChip(3, 4, BLACK);
        board.setChip(4, 3, BLACK);
        board.setChip(4, 4, WHITE);
    }

    public void changeTurn() {

        if (turnCnt == 1) {
            turnCnt = 2;
        } else if (turnCnt == 2) {
            turnCnt = 1;
        }
    }

    public int getTurnStatus() {

        return turnCnt;
    }

    public void rightDirection(int column, int row, int player) {
        if (column < 6) {

            for (int j = column + 1; j < 8; j++) {
                if (board.getBoard()[row][j] != 0 && board.getBoard()[row][column + 1] != player) {
                    if (board.getBoard()[row][j] == player && j - column > 1) {
                        for (int i = column + 1; i < j; i++) {
                          //  System.out.println("made it to right direction");
                            if (player == 2) {
                                board.setChip(row, i, 2);
                            } else if (player == 1) {
                                board.setChip(row, i, 1);
                            }
                        }
                    }
                } else {
                    break;
                }
            }

        }
    }

    public int rightCheck(int row, int column, int player) {
       int check = 0;
        if (column < 6) {

            for (int j = column + 1; j < 8; j++) {
                if (board.getBoard()[row][j] != 0 && board.getBoard()[row][column + 1] != player) {
                    if (board.getBoard()[row][j] == player && j - column > 1) {
                        check = j -column -1;
                        break;
                    }
                } else {
                    break;
                }
            }

        }
        //System.out.println("rightcheck = " + check);
        return check;
    }

    public void leftDirection(int column, int row, int player) {

        if (column > 1) {


            for (int j = column - 1; j >= 0; j--) {

                if (board.getBoard()[row][j] != 0 && board.getBoard()[row][column - 1] != player) {
                    if (board.getBoard()[row][j] == player && column - j > 1) {
                        for (int i = column - 1; i > j; i--) {
                        //   System.out.println("made it to left direction");
                            if (player == 2) {
                                board.setChip(row, i, 2);
                            } else if (player == 1) {
                                board.setChip(row, i, 1);
                            }
                        }
                    }
                } else {
                    break;
                }
            }
        }

    }

    public int leftCheck(int row, int column, int player) {
        int check = 0;
        if (column > 1) {


            for (int j = column - 1; j >= 0; j--) {

                if (board.getBoard()[row][j] != 0 && board.getBoard()[row][column - 1] != player) {
                    if (board.getBoard()[row][j] == player && column - j > 1) {
                       check = column - j -1;
                       break;
                    }
                } else {
                    break;
                }
            }
        }
        //System.out.println("leftcheck = " + check);
        return check;
    }

    public void upDirection(int column, int row, int player) {

        if (row < 6) {
            {
                for (int j = row + 1; j < 8; j++) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row + 1][column] != player) {

                        if (board.getBoard()[j][column] == player && j - row > 1) {

                            for (int i = row + 1; i < j; i++) {
                               // System.out.println("made it to up directions");
                                if (player == 2) {
                                    board.setChip(i, column, 2);
                                } else if (player == 1) {
                                    board.setChip(i, column, 1);
                                }
                            }
                        }
                    } else {
                        break;
                    }
                }
            }

        }

    }

    public int upCheck(int row, int column, int player) {
        int check =0;
        if (row < 6) {
            {
                for (int j = row + 1; j < 8; j++) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row + 1][column] != player) {

                        if (board.getBoard()[j][column] == player && j - row > 1) {

                           check = j - row -1;
                           break;
                        }
                    } else {
                        break;
                    }
                }
            }

        }
       // System.out.println("upcheck = " + check);
        return check;
    }

    public void downDirection(int column, int row, int player) {

        if (row > 1) {
            {
                for (int j = row - 1; j >= 0; j--) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row - 1][column] != player) {
                        if (board.getBoard()[j][column] == player && row - j > 1) {
                            for (int i = row - 1; i > j; i--) {
                             //   System.out.println("made it to down direction");
                                if (player == 2) {
                                    board.setChip(i, column, 2);
                                } else if (player == 1) {
                                    board.setChip(i, column, 1);
                                }
                            }
                        }
                    } else {
                        break;
                    }
                }
            }

        }
    }

    public int downCheck(int row, int column, int player) {
        int check = 0;
        if (row > 1) {
            {
                for (int j = row - 1; j >= 0; j--) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row - 1][column] != player) {
                        if (board.getBoard()[j][column] == player && row - j > 1) {
                           check = row-j-1;
                           break;
                        }
                    } else {
                        break;
                    }
                }
            }

        }
        //System.out.println("downcheck = " + check);
        return check;
    }

    public void northEastDirection(int row, int column, int player) {
        boolean check = false;
        if (row <8  && column < 8 ) {
            if(board.getBoard()[column +1][row+1] != 0 && board.getBoard()[column+1][row+1] != player)
            {

                int newRow = row+2;
                for(int i  = column+2; i < 8; i++)
                {
                    if(newRow < 9)
                    {
                        if(board.getBoard()[i][newRow] == 0){
                            check = false;
                            break;
                        }
                        if( board.getBoard()[i][newRow] == player)
                        {
                            check = true;
                            break;
                        }
                        newRow++;
                    }
                }

            }
            if(check == true)
            {
                for (int i = column + 1; i < 8; i++) {
                    row++;
                    if (board.getBoard()[i][row] != 0 && board.getBoard()[i][row] != player) {
                      //  System.out.println("made it to NE direction");
                        if (getTurnStatus() == 1) {
                            board.setChip(i, row, 1);

                        }
                        if (getTurnStatus() == 2) {
                            board.setChip(i, row, 2);

                        }

                    } else {
                        break;
                    }

                }

            }

        }

    }

    public int northEastCheck(int row, int column, int player) {
        boolean check = false;
        int checks = 0;
        if (row <8  && column < 8 ) {
            if(board.getBoard()[column +1][row+1] != 0 && board.getBoard()[column+1][row+1] != player)
            {

                int newRow = row+1;
                for(int i  = column+1; i < 8; i++)
                {
                    if(newRow < 9)
                    {
                        if(board.getBoard()[i][newRow] == 0)
                        {
                            check = false;
                            break;
                        }
                        if(board.getBoard()[i][newRow] == player)
                        {
                            check = true;
                            break;
                        }
                        newRow++;
                    }
                }

            }
            if(check == true)
            {
                for (int i = column + 1; i < 8; i++) {
                    row++;
                    if (board.getBoard()[i][row] != 0 && board.getBoard()[i][row] != player) {
                      //  System.out.println("made it to NE");
                       checks++;

                    } else {
                        break;
                    }

                }

            }

        }
        //System.out.println("NEcheck = " + checks);
        return checks;

    }

    public void northWestDirection(int row, int column, int player) {
        boolean check = false;
        if (row > 0 && column < 8) {
            if (board.getBoard()[column + 1][row - 1] != 0 && board.getBoard()[column + 1][row - 1] != player) {
                int newRow = row - 1;
                for (int i = column + 1; i < 8; i++) {
                    if (newRow >= 0) {
                        if(board.getBoard()[i][newRow] == 0)
                        {
                            check = false;
                            break;
                        }
                        if (board.getBoard()[i][newRow] == player) {
                            check = true;
                            break;
                        }
                        newRow--;
                    }
                }

            }
            if (check == true) {
                for (int i = column + 1; i < 8; i++) {
                    row--;
                    if (board.getBoard()[i][row] != 0 && board.getBoard()[i][row] != player) {
                     //   System.out.println("made it to NW direction");

                        if (getTurnStatus() == 1) {
                            board.setChip(i, row, 1);
                        }
                        if (getTurnStatus() == 2) {
                            board.setChip(i, row, 2);
                        }
                    } else {
                        break;
                    }

                }
            }
        }
    }
    public int northWestCheck(int row, int column, int player) {
        boolean check = false;
        int checks = 0;
        if (row > 0 && column < 8) {
            if (board.getBoard()[column + 1][row - 1] != 0 && board.getBoard()[column + 1][row - 1] != player) {
                int newRow = row - 1;
                for (int i = column + 1; i < 8; i++) {
                    if (newRow >= 0) {
                        if(board.getBoard()[i][newRow] == 0)
                        {
                            check = false;
                            break;
                        }
                        if (board.getBoard()[i][newRow] == player) {
                            check = true;
                            break;
                        }
                        newRow--;
                    }
                }

            }
            if (check == true) {
                for (int i = column + 1; i < 8; i++) {
                    row--;
                    if (board.getBoard()[i][row] != 0 && board.getBoard()[i][row] != player) {
                      //  System.out.println("made it to NW");
                        checks++;
                    } else {
                        break;
                    }

                }
            }
        }
        //System.out.println("NWcheck = " + checks);
        return checks;
    }

    public void southWestDirection(int row, int column, int player) {
        boolean check = false;
        if (row > 0  && column > 0 ) {

            if(board.getBoard()[column -1][row-1] != 0 && board.getBoard()[column-1][row-1] != player)
            {
                int newRow = row-1;
                for(int i  = column-1; i >= 0; i--)
                {
                    if(newRow >= 0)
                    {
                        if(board.getBoard()[i][newRow] == 0)
                        {
                            check = false;
                            break;
                        }
                        if(board.getBoard()[i][newRow] == player)
                        {
                            check = true;
                            break;
                        }
                        newRow--;
                    }
                }
            }
            if(check == true)
            {
                for (int i = column - 1; i > 0; i--) {
                    row--;
                    if (board.getBoard()[i][row] != 0 && board.getBoard()[i][row] != player) {
                     //   System.out.println("made it to SW direction");
                        if (getTurnStatus() == 1) {
                            board.setChip(i, row, 1);

                        }
                        if (getTurnStatus() == 2) {
                            board.setChip(i, row, 2);
                            }

                    } else {
                        break;
                    }
                }
            }
        }
    }

    public int southWestCheck(int row, int column, int player) {
        boolean check = false;
        int checks = 0;
        if (row > 0  && column > 0 ) {

            if(board.getBoard()[column -1][row-1] != 0 && board.getBoard()[column-1][row-1] != player)
            {
                int newRow = row-1;
                for(int i  = column-1; i >= 0; i--)
                {
                    if(newRow >= 0)
                    {
                        if(board.getBoard()[i][newRow] == 0)
                        {
                            check = false;
                            break;
                        }
                        if(board.getBoard()[i][newRow] == player)
                        {
                            check = true;
                            break;
                        }
                        newRow--;
                    }
                }
            }
            if(check == true)
            {
                for (int i = column - 1; i > 0; i--) {
                    row--;
                    if (board.getBoard()[i][row] != 0 && board.getBoard()[i][row] != player) {
                     //   System.out.println("made it to SW");
                        checks++;

                    } else {
                        break;
                    }
                }
            }
        }
        //System.out.println("SWcheck = " + checks);
        return checks;
    }


    public void southEastDirection(int row, int column, int player) {
        boolean check = false;
        if (row < 8  && column > 0 ) {

            if(board.getBoard()[column -1][row+1] != 0 && board.getBoard()[column-1][row+1] != player)
            {

                int newRow = row+1;

                for(int i  = column-1; i >= 0; i--)
                {
                    if(newRow <= 8)
                    {
                        if(board.getBoard()[i][newRow] == 0)
                        {
                            check = false;
                            break;
                        }
                        if(board.getBoard()[i][newRow] == player)
                        {
                            check = true;
                            break;
                        }
                        newRow++;
                    }
                }

            }
            if(check == true)
            {
                for (int i = column - 1; i > 0; i--) {
                    row++;
                    if (board.getBoard()[i][row] != 0 && board.getBoard()[i][row] != player) {
                       // System.out.println("made it to SE direction");
                        if (getTurnStatus() == 1) {
                            board.setChip(i, row, 1);

                        }
                        if (getTurnStatus() == 2) {
                            board.setChip(i, row, 2);

                        }

                    } else {
                        break;
                    }

                }

            }

        }

    }

    public int southEastCheck(int row, int column, int player) {
        boolean check = false;
        int checks = 0;
        if (row < 8  && column > 0 ) {

            if(board.getBoard()[column -1][row+1] != 0 && board.getBoard()[column-1][row+1] != player)
            {

                int newRow = row+1;

                for(int i  = column-1; i >= 0; i--)
                {
                    if(newRow <= 8)
                    {
                        if(board.getBoard()[i][newRow] == 0)
                        {
                            check = false;
                            break;
                        }
                        if(board.getBoard()[i][newRow] == player)
                        {
                            check = true;
                            break;
                        }
                        newRow++;
                    }
                }

            }
            if(check == true)
            {
                for (int i = column - 1; i > 0; i--) {
                    row++;
                    if (board.getBoard()[i][row] != 0 && board.getBoard()[i][row] != player) {
                       // System.out.println("made it to SE");
                        checks++;

                    } else {
                        break;
                    }

                }

            }

        }
        //System.out.println("SEcheck = " + checks);
        return checks;

    }

    public int checkMoves(int row, int column, int player) {
        int check = upCheck(column, row, player) + downCheck(column,row,player) + leftCheck(column,row,player) + rightCheck(column,row,player) + northEastCheck(row,column,player) + northWestCheck(row,column,player) +southEastCheck(row,column,player) + southWestCheck(row,column,player);
        System.out.println("check = " + check);
        return check;
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

    public boolean endGame(int player)
    {
        int checkK=0;
        for(int i = 0; i < board.getBoard().length-1; i++)
        {
            for(int j = 0; j < board.getBoard().length-1; j++)
            {
              checkK=checkK+checkMoves(i,j,player);
              if(board.getBoard()[i][j] == 0)
              {
                  if(checkK == 0)
                  {
                      System.out.println("Bib" + checkMoves(i,j,player));
                      return true;
                  }
              }

            }
        }
        return false;
    }


}

