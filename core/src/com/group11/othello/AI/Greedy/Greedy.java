package com.group11.othello.AI.Greedy;

import com.badlogic.gdx.math.Vector2;
import com.group11.othello.AI.MonteCarlo.AI;
import com.group11.othello.Logic.GameLogic;

import java.util.List;

public class Greedy extends AI {
    GameLogic gL;
    int player;

    public Greedy(int player) {this.player = player; }

    @Override
    public Vector2 nextMove(GameLogic gL) {
        this.gL = gL;
        int check = 0;
        int newCheck = 0;
        int index = 0;
        List<Vector2> moves = gL.getValidMoves();
        for (int i = 0; i < moves.size(); i++) {
                newCheck = gL.checkMoves((int)moves.get(i).y,(int)moves.get(i).x, player);
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
