package com.group11.othello.AI.MonteCarlo;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.AI.EvaluationFunction;
import com.group11.othello.Logic.GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonteCarlo extends AI {

    private CarloNode root;
    private int playerTurn;
    private EvaluationFunction eF;
    private final int SIMULATIONS = 10000;
    int constant = 2;

   public MonteCarlo (int player){
       root = null;
       this.playerTurn = player;
       eF = new EvaluationFunction();
   }

    public Vector2 nextMove(GameLogic gameLogic) {
        GameLogic glCopy = gameLogic.copy();
        root = new CarloNode(gameLogic.copy(),-1,-1);
        CarloNode cn = root;

        while(root.getVisits() < SIMULATIONS){

            if(cn.getChildren().size() == 0){
                if(cn.getVisits() == 0){

                    backPropagation(rollOut(cn));

                    updateVisits(cn);

                    cn = root;

                }
                else {
                    for (int i = 0; i < cn.getMoves().size(); i++) {
                        GameLogic gl = cn.getGameLogic().copy();
                        gl.getBoard().setChip((int) cn.getMoves().get(i).y, (int) cn.getMoves().get(i).x, gl.getTurnStatus());
                        CarloNode childNode = new CarloNode(gl, (int) cn.getMoves().get(i).y, (int) cn.getMoves().get(i).x);
                        cn.setChild(childNode);
                        childNode.setParent(cn);

                    }
                      cn = selection(cn);
                      backPropagation(rollOut(cn));
                      updateVisits(cn);
                      cn = root;
                }
            }
            else{

                cn = selection(cn);

                }

            }


         cn = selection(root);

       return new Vector2(cn.getColumn(),cn.getRow());

    }


    public CarloNode selection(CarloNode cn){

            if(cn.getChildren().size() > 0){
                double score = -100000000;
                int childNodeIndex = 0;
                for(int i = 0; i < cn.getChildren().size(); i++){
                    if(evaluation(cn.getChildren().get(i)) > score){
                        score = evaluation(cn.getChildren().get(i));
                        childNodeIndex = i;
                    }
                }
                cn = cn.getChildren().get(childNodeIndex);
            }
            return cn;
        }



    public CarloNode rollOut(CarloNode node){

        CarloNode cn = node;
        GameLogic glCopy = cn.getGameLogic().copy();
        Random rand = new Random();

        int k =0;

       while(glCopy.gameOver() == false){

           k++;
           List<Vector2> moveList = cn.getMoves();
           if(moveList.size()>0)
           {
               int n =0;

               n = rand.nextInt(moveList.size());
               System.out.println("....................................rand = "+n);
               glCopy.getBoard().setChip((int)cn.getMoves().get(n).y,(int)cn.getMoves().get(n).x,glCopy.getTurnStatus());
               CarloNode childNode = new CarloNode(glCopy,(int)cn.getMoves().get(n).y,(int)cn.getMoves().get(n).x);

               glCopy.changeTurn();

               setScore(childNode);


               childNode.setParent(cn);
               cn.setChild(childNode);
               cn = childNode;

           }
           else
           {
               return cn;
           }
       }
       return cn;
       
    }

   public double evaluation(CarloNode node){

           double scoreAverage = node.getScore()/node.getVisits();
           double control = (Math.sqrt((Math.log(root.getVisits())/ node.getVisits())))*constant;
           double evaluation = scoreAverage + control;

       return evaluation;
   }

   public void backPropagation(CarloNode node){

          double score = node.getScore();

          while(!(node.equals(root))){

              node = node.getParent();
              score += node.getScore();
              node.setScore(score);

          }

          root.setScore(score);
          root.addVisit();
   }

   public void updateVisits(CarloNode node){
       CarloNode cn = node;
       while(!(cn.equals(root))){
           cn.addVisit();
           cn = cn.getParent();
       }
   }

   public void setScore(CarloNode node){
       double finalScore=0;
       GameLogic glCopy = node.getGameLogic().copy();
       finalScore= eF.bigEvaluation(glCopy, glCopy.getTurnStatus());
       node.setScore(finalScore);

   }

    @Override
    public int getScore() {
        return super.getScore();
    }

}
