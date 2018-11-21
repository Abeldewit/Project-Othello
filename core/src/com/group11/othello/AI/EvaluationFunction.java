package com.group11.othello.AI;

import com.group11.othello.Logic.GameLogic;

public class EvaluationFunction {

    private int blackMoves;
    private int whiteMoves;
    GameLogic gameLogic;


    public EvaluationFunction() {

    }

    public double evaluateCurrent(GameLogic gL) {
        gameLogic = gL.copy();

        blackMoves = gameLogic.getValidMoves().size();
        return 0;
    }
}
