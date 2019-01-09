package com.group11.othello.AI.MinMaxBot;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.AI.MonteCarlo.AI;
import com.group11.othello.Logic.GameLogic;
import com.group11.othello.AI.EvaluationFunction;
//import sun.awt.image.IntegerComponentRaster;

import java.util.List;

public class MinMax extends AI {
    //    private int[][] board;
    private final static int maxDepth = 4;
    private static int aiPlayer;

    EvaluationFunction eF = new EvaluationFunction();

    public MinMax(int player) {
        this.aiPlayer = player;
    }

    public Vector3 nextMove(GameLogic gl) {
        aiPlayer = gl.getTurnStatus();


        List<Vector3> moves = gl.getValidMoves();
        int maxScore = Integer.MIN_VALUE;
        int indexMaxScore = -1;
        if(moves.size() == 1) {
            return moves.get(0);
        }else {
            for (int i = 0; i < moves.size(); i++) {
                GameLogic glCopy = gl.copy();
                Vector3 move = moves.get(i);
                glCopy.getBoard().setChip((int) move.y, (int) move.x, glCopy.getTurnStatus());
                //glCopy.changeTurn();
                aiPlayer = gl.getTurnStatus();
                // runAvailable(gl,(int) move.x, (int) move.y);
                int score = MinMaxBot(glCopy, maxDepth, 0,true);

//            System.out.println(score);
                if (maxScore < score) {
                    maxScore = score;
                    indexMaxScore = i;

                }
            }
            //System.out.println("MOVE I : " + indexMaxScore);
            //System.out.println("FROM　ＭＯＶＥＳ　" + moves.toString());
            //gl.getBoard().printBoard();
            return moves.get(indexMaxScore);
        }
    }

    private int MinMaxBot(GameLogic gl, int maxDepth, int currentDepth, boolean maxPlayer) {
        List<Vector3> moves = gl.getValidMoves();

        if (currentDepth == maxDepth || moves.size() < 2) {
            int val = calcHeuristic(gl, eF , gl.getTurnStatus());
            //System.out.println(val + " heur");
            return val;
        }

        if (maxPlayer == true){
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                //System.out.println("checking the max step" + i);
                GameLogic glCopy = gl.copy();
                Vector3 move = moves.get(i);
                glCopy.getBoard().setChip((int) move.y, (int) move.x, glCopy.getTurnStatus()); // cc
                int eval = MinMaxBot(gl, maxDepth, currentDepth+1,false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        }
        else{
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                //System.out.println("checking the max step" + i);
                GameLogic glCopy = gl.copy();
                Vector3 move = moves.get(i);
                glCopy.getBoard().setChip((int) move.y, (int) move.x, glCopy.getTurnStatus()); // cc
                int eval = MinMaxBot(gl, maxDepth, currentDepth + 1, true);
                minEval = Math.min(minEval, eval);
            }
            return minEval;
        }
    }

    /* private int calcHeuristic(GameLogic gl, EvaluationFunction eF, int player) {

         Vector3 scores = gl.getScore();
         //return eF.bigEvaluation(gl, player);
         if (player == 1)
             return (int) scores.x;
         else
             return (int) scores.y;

     }*/
    private int calcHeuristic(GameLogic gl, EvaluationFunction eF, int player) {
        return (int) eF.bigEvaluation(gl, player);
    }


}
/*
function move findMove(x,x,x,x) is
    maxMove = move
    max Val = val
    for all moves:
    move[v] = minimax(move,x,x)
    return move


function minimax(node, depth, maximizingPlayer) is
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
        value := −∞
        for each child of node do
            value := max(value, minimax(child, depth − 1, FALSE))
        return value
    else (* minimizing player *)
        value := +∞
        for each child of node do
            value := min(value, minimax(child, depth − 1, TRUE))
        return value
 */
