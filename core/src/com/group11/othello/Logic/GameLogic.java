package com.group11.othello.Logic;

import com.badlogic.gdx.math.Interpolation;
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
    private Board board;
    public static boolean[][] legalMoves;
    private int turnCnt; // public static int turnCnt;



    public GameLogic() {

        board = new Board();
        turnCnt =1;
        board.setChip(3, 3, WHITE);
        board.setChip(3, 4, BLACK);
        board.setChip(4, 3, BLACK);
        board.setChip(4, 4, WHITE);
    }

    public GameLogic(Board board, int turnCount){
        this.board = board;
        this.turnCnt = turnCount;
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
      //  System.out.println("rightcheck = " + check);
        return check;
    }

    public int correctCheck(int row, int column, int player){
        int count  = 0;
        if(board.getBoard()[row][column] != 0)
            return 0;
        for (int i = -1; i< 2 ; i++){
            for(int j = -1; j<2; j++){
                if (i != 0 || j != 0){
                    count += checkDirection(row,column,i,j,player);
                }
            }
        }
        if(count>0) System.out.println("total count" + count);
                return count;
    }
    private int checkDirection(int row, int column, int i, int j,int player){
        int count = 0;
        int length = 8;

        while(row + i >= 0 && column + j >= 0 && row + i < board.getBoard().length && column + j < board.getBoard()[0].length &&
                board.getBoard()[row+i][column+j] != 0 && board.getBoard()[row+i][column+j] != player ){
            count++;
            row+=i;
            column+=j;

        }
        if (row + i >= 0 && column + j >= 0 && row + i < board.getBoard().length && column + j < board.getBoard()[0].length &&
                count>0 && board.getBoard()[row+i][column+j]==player){

            System.out.println("count of direction "+i+" "+j+" "+ count);
            return count;
        }

        return 0;
    }
    private void flipDirection(int row, int column, int i, int j, int player){
        boolean flips = false;
        int count =0;
        while(row + i >= 0 && column + j >= 0 && row + i < board.getBoard().length && column + j < board.getBoard()[0].length &&
                board.getBoard()[row+i][column+j] != 0 && board.getBoard()[row+i][column+j] != player ){
            flips = true;
            count++;
            row+=i;
            column+=j;


        }
        if (row + i >= 0 && column + j >= 0 && row + i < board.getBoard().length && column + j < board.getBoard()[0].length &&
               count>0 && board.getBoard()[row+i][column+j]==player && flips){
            System.out.println("yes");
                for(int k =0; k<count; k++){
                    if (player == 2) {
                        board.setChip(column, row, 2);
                        System.out.println("FLIP!");
                    } else if (player == 1) {
                        board.setChip(column, row, 1);
                        System.out.println("FLIP!");
                    }
                    row-=i;
                    column-=j;
                }
        }

    }
    public void flipAvailable(int row, int column, int player){
        for (int i = -1; i< 2 ; i++){
            for(int j = -1; j<2; j++){
                if (i != 0 || j != 0){
                   flipDirection(row,column,i,j,player);
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
      //  System.out.println("leftcheck = " + check);
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
     //   System.out.println("upcheck = " + check);
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
     //   System.out.println("downcheck = " + check);
        return check;
    }

    public void northEastDirection(int row, int column, int player) {
        boolean check = false;
        if (row <7  && column < 7 ) {
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
        if (row <7  && column < 7 ) {
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
     //   System.out.println("NEcheck = " + checks);
        return checks;

    }

    public void northWestDirection(int row, int column, int player) {
        boolean check = false;
        if (row > 1 && column < 7) {
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
        if (row > 1 && column < 7) {
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
      // System.out.println("NWcheck = " + checks);
        return checks;
    }

    public void southWestDirection(int row, int column, int player) {
        boolean check = false;
        if (row > 1  && column > 1 ) {

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
        if (row > 1  && column > 1 ) {

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
      //  System.out.println("SWcheck = " + checks);
        return checks;
    }


    public void southEastDirection(int row, int column, int player) {
        boolean check = false;
        if (row < 7  && column > 1 ) {

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
        if (row < 7  && column > 1 ) {

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
//        System.out.println("checkMoves = " + check);
//        int check =correctCheck(row,column,player);
        return check;
    }


    public Board getBoard()
    {
        return board;
    }

    //use vector2 instead of 3
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
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {

              if(board.getBoard()[i][j] == 0 && board.getBoard()[i][j] != player )
              {
                   checkK = checkMoves(i,j,player);


                  if(checkK > 0)
                  {

//                      System.out.println("blanks = " + getBlankSpaces());
//                      System.out.println("playerInIndex = " + board.getBoard()[i][j]);
//                      System.out.println("i = " + i);
//                      System.out.println("j = " + j);
//                      System.out.println("checkK + " + checkK);
//                      System.out.println("player = " + player);
                      return true;
                  }
              }

            }
        }

        System.out.println("checkK + " + checkK);
        System.out.println("player = " + player);

        return false;
    }

    //lilly
    public List<Vector2> getValidMoves(){
        List<Vector2> moves = new ArrayList<Vector2>();
       
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if (board.getBoard()[i][j] == 0 && checkMoves(j,i, getTurnStatus()) > 0 ) {
                    moves.add(new Vector2(j,i));
                }
            }
        }

        return moves;
    }
    public List<Vector2> getValidMoves(GameLogic gl){
        List<Vector2> moves = new ArrayList<Vector2>();

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if (board.getBoard()[i][j] == 0 && checkMoves(j,i, gl.getTurnStatus()) > 0 ) {
                    moves.add(new Vector2(j,i));
                }
            }
        }

        return moves;
    }

    public List<Vector2> getValidMovesWhite(){
        List<Vector2> moves = new ArrayList<Vector2>();

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if (board.getBoard()[i][j] == 0 && checkMoves(j,i, 1) > 0 ) {
                    moves.add(new Vector2(j,i));
                }
            }
        }

        return moves;
    }

    public List<Vector2> getValidMovesBlack(){
        List<Vector2> moves = new ArrayList<Vector2>();

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if (board.getBoard()[i][j] == 0 && checkMoves(j,i, 2) > 0 ) {
                    moves.add(new Vector2(j,i));
                }
            }
        }

        return moves;
    }

    public int getWhiteCorners(){

        int whiteCorners = 0;

        if (board.getBoard()[0][0] == 1){
            whiteCorners ++;
        }else if (board.getBoard()[0][7]== 1){
            whiteCorners++;
        }else if (board.getBoard()[7][0]== 1){
            whiteCorners++;
        }else if (board.getBoard()[7][7]== 1){
            whiteCorners++;
        }

        return whiteCorners;
    }

    public int getBlackCorners(){

        int blackCorners = 0;

        if (board.getBoard()[0][0] == 2){
            blackCorners++;
        }else if (board.getBoard()[0][7]== 2){
            blackCorners++;
        }else if (board.getBoard()[7][0]== 2){
            blackCorners++;
        }else if (board.getBoard()[7][7]== 2){
            blackCorners++;
        }

        return blackCorners;
    }
    public boolean gameOver(){

        if(getScore().x+getScore().y == 64) return true;
        return false;
    }

    public GameLogic copy(){
        return new GameLogic(board.copy(), turnCnt);
    }

}

