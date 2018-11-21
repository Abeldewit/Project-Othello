package com.group11.othello.AI;

import com.badlogic.gdx.math.Vector2;
import com.group11.othello.Logic.GameLogic;

public class EvaluationFunction {

    private int blackMoves;
    private int whiteMoves;
    GameLogic gameLogic;


    public EvaluationFunction() {

    }

    public int evaluateMobility(GameLogic gL) {
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

    public int evaluateCorners(GameLogic gL){
        int x = 0;

        return x;
    }
}
    /*public int Evaluate

    public double evaluateCurrent(GameLogic gL) {
        gameLogic = gL.copy();

        blackMoves = gameLogic.getValidMoves().size();
        return 0;
    }

    public Vector2 checkCorners(GameLogic gL)
    {
        gameLogic = gL.copy();

        return new Vector2();

    }
}
*/