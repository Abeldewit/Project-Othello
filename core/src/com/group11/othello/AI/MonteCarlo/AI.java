package com.group11.othello.AI.MonteCarlo;

import com.badlogic.gdx.math.Vector2;
import com.group11.othello.Logic.GameLogic;

public class AI {

    private int points = 2;

    public Vector2 nextMove(GameLogic gameLogic){return new Vector2();}

    public int getScore(){ return points;}

    public void setScore(int points){ this.points = points;}

    public void setLogic(GameLogic gL){}
}