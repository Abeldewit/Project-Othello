package com.group11.othello.AI.MinMaxBot;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.AI.EvaluationFunction;
import com.group11.othello.AI.MonteCarlo.AI;
import com.group11.othello.Logic.GameLogic;


import java.util.List;

public class AlphaBeta extends AI {
    //    private int[][] board;
    private final static int maxDepth = 4;
    private  int aiPlayer;

    EvaluationFunction eF = new EvaluationFunction();

    public AlphaBeta(int aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    public Vector3 nextMove(GameLogic gl) {

        //aiPlayer = gl.getTurnStatus();


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
                long startTime = System.currentTimeMillis();
                while(System.currentTimeMillis()<=startTime+500) {
                    int score = MinMaxAB(glCopy, Integer.MIN_VALUE, Integer.MAX_VALUE, maxDepth, 0, true);
                    move.z = score;
                    moves.set(i, (move));
//            System.out.println(score);
                    if (maxScore < score) {
                        maxScore = score;
                        indexMaxScore = i;

                    }
                }
            }
            //System.out.println("MOVE I : " + indexMaxScore);
            //System.out.println("FROM　ＭＯＶＥＳ　" + moves.toString());
            //gl.getBoard().printBoard();
            return moves.get(indexMaxScore);
        }
    }

    private int MinMaxAB(GameLogic gl, int alpha, int beta, int maxDepth, int currentDepth, boolean maxPlayer) {
        //GameLogic glCopy = gl.copy();
        List<Vector3> moves = gl.getValidMoves();

        if (currentDepth == maxDepth || moves.size() < 2) {
            int val = calcHeuristic(gl, eF , gl.getTurnStatus());
            //System.out.println(val + " heur");
            return val;
        }

        //If just one move left then return 0 as heuristic since there is no other choice
        // if (moves.size() <2) return calcHeuristic(gl, eF, gl.getTurnStatus());


        if (maxPlayer == true){
            //System.out.println("1111111111111111111111");
            moves = sortDscending(moves);  // moves ordering
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < moves.size(); i++) {
                //System.out.println("checking the max step" + i);
                GameLogic glCopy = gl.copy();
                Vector3 move = moves.get(i);
                glCopy.getBoard().setChip((int) move.y, (int) move.x, glCopy.getTurnStatus()); // cc
                //glCopy.changeTurn();
                int val = MinMaxAB(glCopy, alpha, beta, maxDepth, currentDepth + 1,false);
                maxEval = Math.max(maxEval, val);
                //System.out.println("MaxEval is " + maxEval);
                alpha = Math.max(alpha, val);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        }
        else{
            // System.out.println("222222222222222222222");
            moves= sortAscending(moves); // moves ordering
            int minEval = Integer.MAX_VALUE;
            for(int i = 0; i < moves.size(); i++){
                //System.out.println("checking the min step" + i);
                GameLogic glCopy = gl.copy();
                Vector3 move = moves.get(i);
                glCopy.getBoard().setChip((int) move.y, (int) move.x, glCopy.getTurnStatus()); //cc
                //glCopy.changeTurn();
                int val = MinMaxAB(glCopy,alpha,beta, maxDepth, currentDepth+1,true);
                minEval = Math.min(minEval, val);
                //System.out.println("MinEval is " + minEval);
                beta = Math.min(beta, val);
                if (beta <= alpha){
                    break;
                }
            }
            //System.out.println(indexMaxScore);
            return minEval;
        }


        /*
        moveList = moveGenerator(state)
            for each move M in moveList do
            value[M]  minimaxValue(applyMove(M, state),
             currentSearchDepth+1)
            if whoseTurn==myTurn then
            return max of value[]
            else
            return min of value[]

         */


    }

    /*private int calcHeuristic(GameLogic gl, int player) {
        Vector3 scores = gl.getScore();
//        System.out.println("Player " + player);
//        System.out.println(scores);

        if (player == 1)
            return (int) scores.x;
        else
            return (int) scores.y;
    }*/

    private int calcHeuristic(GameLogic gl, EvaluationFunction eF, int player) {
        return (int) eF.bigEvaluation(gl, player);
    }

    /*private int calcHeuristic(GameLogic gl, EvaluationFunction eF, int player) {
        Vector3 scores = gl.getScore();
//        System.out.println("Player " + player);
//        System.out.println(scores);

        if (player == 1)
            return (int) scores.x + eF.evaluateMobility(gl);
        else
            return (int) scores.y + eF.evaluateMobility(gl);

        // Trying different evaluation function:
        if (player == 1)
            return (int) scores.x + eF.evaluateMobility(gl);
        else
            return (int) scores.y + eF.evaluateMobility(gl);
    }*/

    private List<Vector3> sortDscending(List<Vector3> moves){

            Vector3 tmp = null;
            for(int i = 0; i < moves.size(); i++)
                for(int j = 1; j < moves.size()-i; j++)
                    if(moves.get(j-1).z < moves.get(j).z){
                        tmp = moves.get(j-1);
                       moves.set(j-1,moves.get(j));
                        moves.set(j, tmp);
                    }
            return moves;

    }
    private List<Vector3> sortAscending(List<Vector3> moves){

        Vector3 tmp = null;
        for(int i = 0; i < moves.size(); i++)
            for(int j = 1; j < moves.size()-i; j++)
                if(moves.get(j-1).z > moves.get(j).z){
                    tmp = moves.get(j-1);
                    moves.set(j-1,moves.get(j));
                    moves.set(j, tmp);
                }
        return moves;

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
