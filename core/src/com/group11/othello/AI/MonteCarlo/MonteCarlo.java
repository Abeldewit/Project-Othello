package com.group11.othello.AI.MonteCarlo;


import com.badlogic.gdx.math.Vector2;
import com.group11.othello.Logic.GameLogic;

import java.util.List;

public class MonteCarlo extends AI {

    private CarloNode root;
    private GameLogic gl;
    private int player;

   public MonteCarlo (int player){
       root = null;
       this.player = player;
   }

   public void selection(){}

   public void expansion(){}

   public double evaluation(CarloNode node){

       double eval = 0;
       return eval;


   }

   public void backPropagation(){}

   public void setScore(CarloNode node){
       double score = 0;

       for(int i = 0; i < node.getMoves().size(); i++){
           GameLogic glCopy = node.getGameLogic().copy();
           glCopy.getBoard().setChip((int) node.getMoves().get(i).y, (int) node.getMoves().get(i).x, node.getGameLogic().getTurnStatus());

           if(glCopy.getTurnStatus() == player){
               score += glCopy.getScore().x;
           }
           else{
               score -= glCopy.getScore().y;
           }
       }

   }

    @Override
    public Vector2 nextMove(GameLogic gameLogic) {
        return super.nextMove(gameLogic);
    }

    @Override
    public int getScore() {
        return super.getScore();
    }

    @Override
    public void setScore(int points) {
        super.setScore(points);
    }

    @Override
    public void setLogic(GameLogic gl) {
        this.gl = gl;
    }
}
