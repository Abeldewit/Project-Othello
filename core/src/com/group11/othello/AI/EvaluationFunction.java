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

        return new Vector2(whiteCorner, blackCorner);
    }
}
    /*public int Evaluate

    public double evaluateCurrent(GameLogic gL) {
        gameLogic = gL.copy();



        return new Vector2(0,0);
    }

    public Vector2 checkCorners(GameLogic gL)
    {
        gameLogic = gL.copy();

        return new Vector2();

    }
}
*/