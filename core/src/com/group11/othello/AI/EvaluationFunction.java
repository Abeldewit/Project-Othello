package com.group11.othello.AI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.group11.othello.Logic.Board;
import com.group11.othello.Logic.GameLogic;

public class EvaluationFunction {

    GameLogic gameLogic;

    public EvaluationFunction() {

    }

    public int bigEvaluation(GameLogic gL, int player) {
        int score = 0;

        //simple score based on amount of chips

        if(player == 1) {
            score += gL.getScore().x;
        } else if(player == 2) {
            score += gL.getScore().y;
        }

        //more advanced evaluation functions
        int mob = evaluateMobility(gL);
        Vector2 corners = evaluateCorners(gL);

        //Use all checks to assign right score
        if(player ==1 ) {
            score += mob;
            score += corners.x;
        } else if(player == 2) {
            score -= mob;
            score += corners.y;
        }
        //Penalty for placing around a corner
        score += cornerPenalty(gL, player);

        System.out.println("Score for player " + player + " evaluated at " + score);
        return score;
    }

    public int evaluateMobility(GameLogic gL) {
        int blackMoves = 0;
        int whiteMoves = 0;
        gameLogic = gL.copy();
        int MHV;

        blackMoves = gameLogic.getValidMovesBlack().size();
        whiteMoves = gameLogic.getValidMovesWhite().size();

        if (whiteMoves + blackMoves != 0) {

            MHV = 100 * (whiteMoves - blackMoves) / (whiteMoves + blackMoves);
        } else {

            MHV = 0;

        }
        //System.out.println("Evaluation of mobility: " + MHV);
        return MHV;
    }

    public Vector2 evaluateCorners(GameLogic gL){
        int whiteCorner = 0;
        int blackCorner = 0;

        Board currentBoard = gL.getBoard();

        //These two for loops will check (0,0) (0,7) (7,0) (7,7)
        for(int x = 0; x < 8; x += 7) {
            for(int y = 0; y < 8; y += 7) {
                if(currentBoard.getChip(x,y) == 1){
                    whiteCorner++;
                } else if(currentBoard.getChip(x,y) == 2) {
                    blackCorner++;
                }
            }
        }

        //System.out.println("Corner eval, white: " + whiteCorner * 100 + " black: " + blackCorner * 100);
        return new Vector2(whiteCorner * 100, blackCorner * 100);
    }

    public int cornerPenalty(GameLogic gL, int player) {
        Board currentBoard = gL.getBoard();
        final int penalty = 200;
        int playerPenalty = 0;

        for(int i = 1; i < 13; i++) {

            switch (i) {
                //Upper left corner
                case 1:
                    if(currentBoard.getChip(0,1) == player && currentBoard.getChip(0,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 2:
                    if(currentBoard.getChip(1,0) == player && currentBoard.getChip(0,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 3:
                    if(currentBoard.getChip(1,1) == player && currentBoard.getChip(0,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                //Lower left corner
                case 4:
                    if(currentBoard.getChip(0,6) == player && currentBoard.getChip(0,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 5:
                    if(currentBoard.getChip(1,7) == player && currentBoard.getChip(0,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 6:
                    if(currentBoard.getChip(1,6) == player && currentBoard.getChip(0,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                //Upper right corner
                case 7:
                    if(currentBoard.getChip(6,0) == player && currentBoard.getChip(7,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 8:
                    if(currentBoard.getChip(7,1) == player && currentBoard.getChip(7,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 9:
                    if(currentBoard.getChip(6,1) == player && currentBoard.getChip(7,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                //Lower right corner
                case 10:
                    if(currentBoard.getChip(7,6) == player && currentBoard.getChip(7,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 11:
                    if(currentBoard.getChip(6,7) == player && currentBoard.getChip(7,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 12:
                    if(currentBoard.getChip(6,6) == player && currentBoard.getChip(7,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
            }
        }
        return playerPenalty;
    }
}