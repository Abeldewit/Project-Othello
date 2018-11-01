package com.group11.othello.AI;

import com.badlogic.gdx.math.Vector2;

public class AI {

    private int points = 0;

    public Vector2 tileSelected(){return new Vector2();}

    public int getScore(){ return points;}

    public void setScore(int points){ this.points = points;}

}
