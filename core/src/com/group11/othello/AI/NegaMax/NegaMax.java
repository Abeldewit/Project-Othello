package com.group11.othello.AI.NegaMax;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.Logic.GameLogic;

import java.util.Iterator;
import java.util.List;

public class NegaMax {

    GameLogic NegaGl;


    public NegaMax(GameLogic gameLogic) {
        NegaGl = gameLogic.copy();
    }

    public Iterable<Vector2> getBestMoves(int depth) {
        NegaGl.getValidMoves();
        if (depth <= 0) {
            throw new IllegalArgumentException("Search depth MUST be > 0");
        }
        Iterable<Vector2> iterator = NegaGl.getValidMoves();
        return iterator;
    }

    public void negaMax(int depth) {
        if(depth == 0) {
            //return evaluate();
        }


        
    }


}