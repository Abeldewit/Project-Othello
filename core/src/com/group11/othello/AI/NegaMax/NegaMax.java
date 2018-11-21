package com.group11.othello.AI.NegaMax;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.Logic.GameLogic;
//import com.sun.org.apache.xpath.internal.operations.Neg;

import java.util.Iterator;
import java.util.List;

public class NegaMax {

    private final static int maxDepth = 4;
    private static int aiPlayer = 1;


    public NegaMax() {
    }

    public Vector2 nextMove(GameLogic gameLogic) {
        GameLogic NegaGl = gameLogic.copy();
        aiPlayer = NegaGl.getTurnStatus();

        List<Vector2> validMoves = NegaGl.getValidMoves();
        if(validMoves.size() == 1) {
            return validMoves.get(0);
        }

        int maxScore = Integer.MIN_VALUE;
        int maxIndex = -1;
        int moveCount = 0;

        for(Vector2 move : validMoves) {
            GameLogic GLclone = NegaGl.copy();
            //Make the move in the cloned board
            GLclone.getBoard().setChip((int) move.x, (int) move.y, GLclone.getTurnStatus());
            GLclone.changeTurn();

            int score = NMax(GLclone, maxDepth, 0);
            if(score > maxScore) {
                maxScore = score;
                maxIndex = moveCount;
            }
            moveCount++;
        }
        gameLogic.getBoard().printBoard();
        return validMoves.get(maxIndex);

    }

    public int NMax(GameLogic gLogic, int maxDepth, int currentDepth) {
        if(currentDepth == maxDepth) {
            int val = calcHeuristic(gLogic, gLogic.getTurnStatus());
            return val;
        }

        List<Vector2> validMoves = gLogic.getValidMoves();
        if(validMoves.size() == 1) {
            return calcHeuristic(gLogic, gLogic.getTurnStatus());
        }

        int maxScore = Integer.MIN_VALUE;
        int maxIndex = -1;
        int indexCount = 0;
        for(Vector2 move : validMoves) {
            GameLogic GLclone = gLogic.copy();
            GLclone.getBoard().setChip((int) move.x, (int) move.y, GLclone.getTurnStatus());
            runAvailable(GLclone, (int) move.x, (int) move.y);
            GLclone.changeTurn();

            int score = NMax(GLclone, maxDepth, currentDepth + 1);

            //Here we should implement the NegaMax Variation
            if(GLclone.getTurnStatus() == aiPlayer) { score *= -1; }
            if(score > maxScore) {
                maxScore = score;
                maxIndex = indexCount;
            }
            maxIndex++;
        }
        return maxScore;
    }

    public int calcHeuristic(GameLogic gLogic, int player) {
        if(player == 1) {
            return (int) gLogic.getScore().x;
        } else {
            return (int) gLogic.getScore().y;
        }

    }

    public void runAvailable(GameLogic gL, int x, int y) {
        gL.rightDirection(x, y, gL.getTurnStatus());
        gL.leftDirection(x, y, gL.getTurnStatus());
        gL.upDirection(x, y, gL.getTurnStatus());
        gL.downDirection(x, y, gL.getTurnStatus());
        gL.northEastDirection(x, y, gL.getTurnStatus());
        gL.northWestDirection(x, y, gL.getTurnStatus());
        gL.southWestDirection(x, y, gL.getTurnStatus());
        gL.southEastDirection(x, y, gL.getTurnStatus());
    }
}