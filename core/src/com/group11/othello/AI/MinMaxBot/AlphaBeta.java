package com.group11.othello.AI.MinMaxBot;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.Logic.GameLogic;

import java.util.List;

public class AlphaBeta {
    //    private int[][] board;
    private final static int maxDepth = 8;
    private static int aiPlayer = 1;

    public AlphaBeta() {
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
            runAvailable(glCopy,(int) move.x, (int) move.y);
            glCopy.changeTurn();
            int score = MinMaxAB(glCopy,-1000000,1000000, maxDepth, 0);
//            System.out.println(score);
            if (maxScore < score) {
                maxScore = score;
                indexMaxScore = i;
                
            }
        }
        System.out.println("MOVE I : " + indexMaxScore);
        System.out.println("FROM　ＭＯＶＥＳ　" + moves.toString());
        gl.getBoard().printBoard();
        return moves.get(indexMaxScore);

    }

    private int MinMaxAB(GameLogic gl, int a, int b, int maxDepth, int currentDepth) {
        if (currentDepth == maxDepth) {
            int val = calcHeuristic(gl, gl.getTurnStatus());

//            System.out.println(val + " heur");
            return val;
        }
        List<Vector2> moves = gl.getValidMoves();
        //If just one move left then return 0 as heuristic since there is no other choice
        if (moves.size() <2) return calcHeuristic(gl, gl.getTurnStatus());


        int indexMaxScore = -1;
        int score = 0;
        for (int i = 0; i < moves.size(); i++) {
            Vector2 move = moves.get(i);
            GameLogic glCopy = gl.copy();

            glCopy.getBoard().setChip((int) move.y, (int) move.x, glCopy.getTurnStatus());
            runAvailable(glCopy,(int) move.x, (int) move.y);
            glCopy.changeTurn();
            score = MinMaxAB(glCopy, a, b, maxDepth, currentDepth + 1);

            if (gl.getTurnStatus() == aiPlayer) {//set max score
                if(score>a){
                    indexMaxScore = i;
                    a = score;
                }
                if (a >= b) {
                    break;
                }
            } else {//set min score if opponent move
                if (score<b) {
                    b = score;
                    indexMaxScore = i;
                }
                if (a >= b) {
                    break;
                }
            }
        }
        System.out.println(indexMaxScore);

        return score;
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

    private int calcHeuristic(GameLogic gl, int player) {
        Vector3 scores = gl.getScore();
//        System.out.println("Player " + player);
//        System.out.println(scores);

        if (player == 1)
            return (int) scores.x;
        else
            return (int) scores.y;
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
