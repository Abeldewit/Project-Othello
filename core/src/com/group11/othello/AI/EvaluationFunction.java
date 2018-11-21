package com.group11.othello.AI;

import com.group11.othello.Logic.GameLogic;

public class EvaluationFunction {

    private int blackMoves;
    private int whiteMoves;
    GameLogic gameLogic;


    public EvaluationFunction() {

    }
    public int EvaluateMobility(GameLogic gL) {
        gameLogic = gL.copy();
        int MHV;

        blackMoves = gameLogic.getValidMovesBlack().size();
        whiteMoves = gameLogic.getValidMovesWhite().size();

        if (whiteMoves + blackMoves != 0) {

            MHV = 100 * (whiteMoves - blackMoves) / (whiteMoves + blackMoves);
        }else{

            MHV = 0;

        }
        return MHV;
    }

    public double evaluateCurrent(GameLogic gL) {
        gameLogic = gL.copy();

        blackMoves = gameLogic.getValidMoves().size();
        return 0;
    }
}
