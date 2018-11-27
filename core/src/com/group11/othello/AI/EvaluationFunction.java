package com.group11.othello.AI;

import com.badlogic.gdx.math.Vector2;
import com.group11.othello.Logic.Board;
import com.group11.othello.Logic.GameLogic;

public class EvaluationFunction {



    GameLogic gameLogic;


    public EvaluationFunction() {

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
        for(int x = 0; x < 8; x += 8) {
            for(int y = 0; y < 8; y += 8) {
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