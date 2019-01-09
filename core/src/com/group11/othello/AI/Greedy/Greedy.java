package com.group11.othello.AI.Greedy;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.AI.EvaluationFunction;
import com.group11.othello.AI.MonteCarlo.AI;
import com.group11.othello.Logic.GameLogic;


import java.util.List;

public class Greedy extends AI {
    GameLogic gL;
    int player;
    private EvaluationFunction eF;

    public Greedy(int player) {this.player = player; eF = new EvaluationFunction(); }

    @Override
    public Vector3 nextMove(GameLogic gL) {
        this.gL = gL;
        int check = 0;
        int newCheck = 0;
        int index = 0;
        List<Vector3> moves = gL.getValidMoves();
        for (int i = 0; i < moves.size(); i++) {
                newCheck = (int) eF.bigEvaluation(gL, player);
                if(newCheck > check ){
                    index = i;

                }

            }


        return moves.get(index);

    }
    public void runAvailable(int x,int y) {
        gL.rightDirection(x,y, gL.getTurnStatus());
        gL.leftDirection(x,y, gL.getTurnStatus());
        gL.upDirection(x,y, gL.getTurnStatus());
        gL.downDirection(x,y, gL.getTurnStatus());
        gL.northEastDirection(x,y, gL.getTurnStatus());
        gL.northWestDirection(x,y, gL.getTurnStatus());
        gL.southWestDirection(x,y, gL.getTurnStatus());
        gL.southEastDirection(x,y, gL.getTurnStatus());
    }
    @Override
    public int getScore() {
        return super.getScore();
    }

    @Override
    public void setScore(int points) {
        super.setScore(points);
    }

    public void setLogic(GameLogic gL){ this.gL = gL;}
}
