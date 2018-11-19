package com.group11.othello.AI.Greedy;

import com.badlogic.gdx.math.Vector2;
import com.group11.othello.AI.AI;
import com.group11.othello.Logic.GameLogic;

import java.util.ArrayList;

public class Greedy extends AI {
    GameLogic gL;
    public Greedy(GameLogic gL)
    {
        this.gL = gL;
    }
    public void runAvailable(int x,int y)
    {
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
    public Vector2 tileSelected() {

        int check = 0;
        int newCheck = 0;
        Vector2 v = new Vector2();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (gL.getBoard().getBoard()[i][j] == 0 && gL.getBoard().getBoard()[i][j] != 2) {
                    newCheck = gL.checkMoves(j, i, 2);


                    if (newCheck > check) {
                        v = new Vector2(j, i);
                        check = newCheck;
                    }
                }

            }
        }

        return v;

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
