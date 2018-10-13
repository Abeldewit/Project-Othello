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
    private static Board board;
    public static boolean[][] legalMoves;
    public static int turnCnt = 1;


    public GameLogic() {

        board = new Board();
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


    //checks if the black player does have a legal move
    public boolean canMoveBlack() {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                if (checkLegalBlack(x, y, 1, 0)) {
                    return true;
                }

                if (checkLegalBlack(x, y, 1, -1)) {
                    return true;
                }

                if (checkLegalBlack(x, y, 0, -1)) {
                    return true;
                }

                if (checkLegalBlack(x, y, -1, -1)) {
                    return true;
                }

                if (checkLegalBlack(x, y, -1, 0)) {
                    return true;
                }

                if (checkLegalBlack(x, y, -1, 1)) {
                    return true;
                }

                if (checkLegalBlack(x, y, 0, 1)) {
                    return true;
                }

                if (checkLegalBlack(x, y, 1, 1)) {
                    return true;
                }

            }

        }
        return false;
        // Game Over ; end Game in GameState

    }

    //checks if the white player does have a legal move
    public boolean canMoveWhite() {

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {

                if (checkLegalWhite(x, y, 1, 0)) {
                    return true;
                }

                if (checkLegalWhite(x, y, 1, -1)) {
                    return true;
                }

                if (checkLegalWhite(x, y, 0, -1)) {
                    return true;
                }

                if (checkLegalWhite(x, y, -1, -1)) {
                    return true;
                }

                if (checkLegalWhite(x, y, -1, 0)) {
                    return true;
                }

                if (checkLegalWhite(x, y, -1, 1)) {
                    return true;
                }

                if (checkLegalWhite(x, y, 0, 1)) {
                    return true;
                }

                if (checkLegalWhite(x, y, 1, 1)) {
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

        if (wasLegal) {
            board.setChip(x, y, BLACK);
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

        if (wasLegal) {
            board.setChip(x, y, WHITE);
            return true;
        }

        return false;

    }


    public boolean checkLegalBlack(int x, int y, int dx, int dy) {

        int nSteps = 1;
        x += dx;
        y += dy;

        while (0 <= x && x < 8 && 0 <= y && y < 8) {

            if (board.getChip(x, y) == BLACK) {
                return nSteps > 1;
            }

            if (board.getChip(x, y) == EMPTY) return false;

            x = x + dx;
            y = y + dy;
            nSteps = nSteps + 1;
        }

        return false;

    }

    public boolean checkLegalWhite(int x, int y, int dx, int dy) {

        int nSteps = 1;
        x += dx;
        y += dy;

        while (0 <= x && x < 8 && 0 <= y && y < 8) {

            if (board.getChip(x, y) == WHITE)
                return nSteps > 1;


            if (board.getChip(x, y) == EMPTY) return false;

            x = x + dx;
            y = y + dy;
            nSteps = nSteps + 1;
        }


        return false;

    }

    public void turnChipBlack(int x, int y, int dx, int dy) {

        x += dx;
        y += dy;

        while (board.getChip(x, y) == WHITE) {

            board.setChip(x, y, BLACK);
            x += dx;
            y += dy;

        }

    }

    public void turnChipWhite(int x, int y, int dx, int dy) {

        x += dx;
        y += dy;

        while (board.getChip(x, y) == BLACK) {

            board.setChip(x, y, WHITE);
            x += dx;
            y += dy;

        }


    }

    public void rightDirection(int column, int row, int player) {
        if (column < 6) {

            for (int j = column + 1; j < 8; j++) {
                if (board.getBoard()[row][j] != 0 && board.getBoard()[row][column + 1] != player) {
                    if (board.getBoard()[row][j] == player && j - column > 1) {
                        for (int i = column + 1; i < j; i++) {

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

    public void leftDirection(int column, int row, int player) {

        if (column > 1) {


            for (int j = column - 1; j >= 0; j--) {

                if (board.getBoard()[row][j] != 0 && board.getBoard()[row][column - 1] != player) {
                    if (board.getBoard()[row][j] == player && column - j > 1) {
                        for (int i = column - 1; i > j; i--) {

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

    public void upDirection(int column, int row, int player) {

        if (row < 6) {
            {
                for (int j = row + 1; j < 8; j++) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row + 1][column] != player) {

                        if (board.getBoard()[j][column] == player && j - row > 1) {

                            for (int i = row + 1; i < j; i++) {
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

    public void downDirection(int column, int row, int player) {

        if (row > 1) {
            {
                for (int j = row - 1; j >= 0; j--) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row - 1][column] != player) {
                        if (board.getBoard()[j][column] == player && row - j > 1) {
                            for (int i = row - 1; i > j; i--) {
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

    public void northEastDirection(int row, int column, int player) {
        boolean check = false;
        if (row <8  && column < 8 ) {
            if(board.getBoard()[column +1][row+1] != 0 && board.getBoard()[column+1][row+1] != player)
            {

                int newRow = row+1;
                for(int i  = column+1; i < 8; i++)
                {
                    if(newRow < 9)
                    {
                        if(board.getBoard()[i][newRow] != 0 && board.getBoard()[i][newRow] == player)
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

    public void northWestDirection(int row, int column, int player) {
        boolean check = false;
        if (row > 0 && column < 8) {
            if (board.getBoard()[column + 1][row - 1] != 0 && board.getBoard()[column + 1][row - 1] != player) {
                int newRow = row - 1;
                for (int i = column + 1; i < 8; i++) {
                    if (newRow >= 0) {
                        if (board.getBoard()[i][newRow] != 0 && board.getBoard()[i][newRow] == player) {
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
                        if(board.getBoard()[i][newRow] != 0 && board.getBoard()[i][newRow] == player)
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
                        if(board.getBoard()[i][newRow] != 0 && board.getBoard()[i][newRow] == player)
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

    public int checkMoves(int row, int column, int player) {
        //SouthEast
        int check = 0;
        if (row < 8 && column > 0) {
            System.out.println("got past first if statement");
            if (board.getBoard()[column - 1][row + 1] != 0 && board.getBoard()[column - 1][row + 1] != player) {
                System.out.println("checking first tile");

                int newRow = row + 1;
                for (int i = column - 1; i >= 0; i--) {
                    System.out.println("xA = " + i);
                    System.out.println("yA = " + newRow);
                    if (newRow <= 8) {
                        if (board.getBoard()[i][newRow] != 0 && board.getBoard()[i][newRow] == player) {
                            System.out.println("check is true");
                            check++;
                        }
                        newRow++;
                    }
                }

            }
        }

        //southWest
        if (row > 0 && column > 0) {

            if (board.getBoard()[column - 1][row - 1] != 0 && board.getBoard()[column - 1][row - 1] != player) {
                int newRow = row - 1;
                for (int i = column - 1; i >= 0; i--) {
                    if (newRow >= 0) {
                        if (board.getBoard()[i][newRow] != 0 && board.getBoard()[i][newRow] == player) {
                            check++;
                        }
                        newRow--;
                    }
                }
            }
        }

        //northWest

        if (row > 0 && column < 8) {
            if (board.getBoard()[column + 1][row - 1] != 0 && board.getBoard()[column + 1][row - 1] != player) {
                int newRow = row - 1;
                for (int i = column + 1; i < 8; i++) {
                    if (newRow >= 0) {
                        if (board.getBoard()[i][newRow] != 0 && board.getBoard()[i][newRow] == player) {
                            check++;
                        }
                        newRow--;
                    }
                }

            }
        }

        //northEast
        if (row < 8 && column < 8) {
            if (board.getBoard()[column + 1][row + 1] != 0 && board.getBoard()[column + 1][row + 1] != player) {

                int newRow = row + 1;
                for (int i = column + 1; i < 8; i++) {
                    if (newRow < 9) {
                        if (board.getBoard()[i][newRow] != 0 && board.getBoard()[i][newRow] == player) {
                            check++;
                        }
                        newRow++;
                    }
                }

            }
        }

        //down
        if (row > 1) {
            {
                for (int j = row - 1; j >= 0; j--) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row - 1][column] != player) {
                        if (board.getBoard()[j][column] == player && row - j > 1) {
                            for (int i = row - 1; i > j; i--) {
                                check++;
                            }
                        }
                    }
                }
            }
        }

        //up
        if (row < 6) {
            {
                for (int j = row + 1; j < 8; j++) {
                    if (board.getBoard()[j][column] != 0 && board.getBoard()[row + 1][column] != player) {

                        if (board.getBoard()[j][column] == player && j - row > 1) {

                            for (int i = row + 1; i < j; i++) {
                                check++;
                            }
                        }
                    }
                }
            }
        }

        //left
        if (column > 1) {


            for (int j = column - 1; j >= 0; j--) {

                if (board.getBoard()[row][j] != 0 && board.getBoard()[row][column - 1] != player) {
                    if (board.getBoard()[row][j] == player && column - j > 1) {
                        for (int i = column - 1; i > j; i--) {
                            check++;
                        }
                    }
                }
            }
        }

        //right
        if (column < 6) {

            for (int j = column + 1; j < 8; j++) {
                if (board.getBoard()[row][j] != 0 && board.getBoard()[row][column + 1] != player) {
                    if (board.getBoard()[row][j] == player && j - column > 1) {
                        for (int i = column + 1; i < j; i++) {
                            check++;
                        }
                    }
                }
            }
        }

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

    public static boolean checkLegalMove(int x, int y, int player) {
        Vector2 currentSquare = new Vector2(x,y);
        //see if the current square is empty so a chip can be placed
        if(board.getChip(y,x) == 0){
            //check every square around the current square
            for(int n = 0; n < 8; n++) {
                Vector2 direction;
                switch (n) {
                    case 0:  direction = new Vector2(-1,0);
                    break;
                    case 1:  direction = new Vector2(-1,1);
                    break;
                    case 2:  direction = new Vector2(0,1);
                    break;
                    case 3:  direction = new Vector2(1,1);
                    break;
                    case 4:  direction = new Vector2(1,0);
                    break;
                    case 5:  direction = new Vector2(1,-1);
                    break;
                    case 6:  direction = new Vector2(0,-1);
                    break;
                    case 7:  direction = new Vector2(-1,-1);
                    break;
                    default: direction = new Vector2(0,0);
                    break;
                }

                //Vector2 newSquare = currentSquare.add(direction);
                Vector2 newSquare = new Vector2(currentSquare.x + direction.x, currentSquare.y + direction.y);
                //see if the square we're checking is the other color and not empty
                if(newSquare.x > 0 || newSquare.x < 8 || newSquare.y > 0 || newSquare.y < 8) {
                    while (board.getChipVector(newSquare) != player && board.getChipVector(newSquare) != 0) {
                        //move in the direction we're checking
                        newSquare = new Vector2(newSquare.x + direction.x,newSquare.y + direction.y);
                        //out of bounds protection
                        if (newSquare.x > 8 || newSquare.y > 8) {
                            return false;
                        }
                        //if we encounter a chip with our own color there's a sandwich and it's legal!
                        if (board.getChipVector(newSquare) == player) {
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    public static void createLegalField() {
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                legalMoves[x][y] = checkLegalMove(x, y, turnCnt);
            }
        }
    }

    public static boolean[][] getLegalMoves() {
        return legalMoves;
    }

    public void aiPlace(int player){
        List<Point> checkList = new ArrayList<Point>();
        int check = 0;
        createLegalField();
        for (int i = 0; i < legalMoves.length; i++){
            for (int j = 0; j < legalMoves[0].length; j++){
                if (legalMoves[i][j] == true){
                    check = checkMoves(i,j,player);
                    Point point = new Point(i,j,check);
                    checkList.add(point);
                }
            }
        }
        Point max= new Point(0,0, 0);
        for (Point point: checkList){
            if( max.getValue() < point.getValue()){
                max = point;
            }
        }
        board.setChip(max.getColumn(), max.getRow(),1);
    }
}

