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

    public Vector2 nextMove(GameLogic gameLogic) {
        GameLogic gl = gameLogic.copy();
        aiPlayer = gl.getTurnStatus();

        List<Vector2> moves = gl.getValidMoves();
        int maxScore = -10000000;
        int indexMaxScore = -1;
        if(moves.size() == 1)
            return moves.get(0);
        for (int i = 0; i < moves.size(); i++) {
            Vector2 move = moves.get(i);
            GameLogic glCopy = gl.copy();
            glCopy.getBoard().setChip((int) move.y, (int) move.x, glCopy.getTurnStatus());
            //runAvailable(glCopy,(int) move.x, (int) move.y);
            glCopy.changeTurn();
            int score = MinMaxBot(glCopy, maxDepth, 0);

            if (maxScore < score) {
                maxScore = score;
                indexMaxScore = i;
                
            }
        }
        return moves.get(indexMaxScore);

    }

    private int MinMaxBot(GameLogic gl, int maxDepth, int currentDepth) {
        if (currentDepth == maxDepth) {
            int val = calcHeuristic(gl, eF, gl.getTurnStatus());

//            System.out.println(val + " heur");
            return val;
        }
        List<Vector2> moves = gl.getValidMoves();
        //If just one move left then return 0 as heuristic since there is no other choice
        if (moves.size() <2) return calcHeuristic(gl, eF, gl.getTurnStatus());

        int maxScore = Integer.MAX_VALUE;
        if (gl.getTurnStatus() != aiPlayer)
            maxScore *= -1;
        int indexMaxScore = -1;
        for (int i = 0; i < moves.size(); i++) {
            Vector2 move = moves.get(i);
            GameLogic glCopy = gl.copy();

            glCopy.getBoard().setChip((int) move.y, (int) move.x, glCopy.getTurnStatus());
            //runAvailable(glCopy,(int) move.x, (int) move.y);
            glCopy.changeTurn();
            int score = MinMaxBot(glCopy, maxDepth, currentDepth + 1);
            if (gl.getTurnStatus() == aiPlayer) {//set max score

                if (score > maxScore) {
                    maxScore = score;
                    indexMaxScore = i;
                }
            } else {//set min score if opponent move
                if (maxScore < score) {

                    maxScore = score;

                    indexMaxScore = i;
                }
            }
        }
        System.out.println(indexMaxScore);

        return maxScore;

    }

    private int calcHeuristic(GameLogic gl, EvaluationFunction eF, int player) {

        Vector3 scores = gl.getScore();
        //return eF.bigEvaluation(gl, player);
        if (player == 1)
            return (int) scores.x;
        else
            return (int) scores.y;

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
