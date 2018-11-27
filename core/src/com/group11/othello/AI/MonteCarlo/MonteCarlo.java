package com.group11.othello.AI.MonteCarlo;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.Logic.GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonteCarlo extends AI {

    private CarloNode root;
    private int playerTurn;
    private final int SIMULATIONS = 2;
    int constant = 2;

   public MonteCarlo (int player){
       root = null;
       this.playerTurn = player;
   }
   /*
   -assign root
   -assign children

    */

    public Vector2 nextMove(GameLogic gameLogic) {
        GameLogic glCopy = gameLogic.copy();
        root = new CarloNode(gameLogic.copy(),-1,-1);
        CarloNode cn = root;
        double score = 0;
        for(int i = 0; i < cn.getMoves().size(); i++){
            GameLogic currentGl = glCopy.copy();
            currentGl.getBoard().setChip((int) root.getMoves().get(i).y, (int) root.getMoves().get(i).x, glCopy.getTurnStatus());
            CarloNode childNode = new CarloNode(currentGl,(int) root.getMoves().get(i).y,(int) root.getMoves().get(i).x);
           // setScore(childNode,currentGl);
            cn.setChild(childNode);
            childNode.setParent(root);
        }

        while(root.getVisits() < SIMULATIONS){
          // System.out.println(root.getVisits());
           cn=selection(cn);
            if(cn.getChildren().size() == 0){
                System.out.println("Passed 1st if");
                if(cn.getVisits() == 0){
                    System.out.println("Passed 2nd if");
                    backPropagation(rollOut(cn));
                    System.out.println("backpropagated");
                    updateVisits(cn);
                    System.out.println("roledout");

                }
                else{
                    for(int i = 0; i < cn.getChildren().size(); i++){
                        if(cn.getChildren().get(i).getScore() == 0){
                            System.out.println("Passed 3rd if");
                            backPropagation(rollOut(cn));
                            System.out.println("backpropagated2");
                            updateVisits(cn);
                            System.out.println("roledout2");
                            break;
                        }
                    }

                    for(int i = 0; i < cn.getMoves().size(); i++){
                        GameLogic gl = glCopy.copy();
                        glCopy.getBoard().setChip((int) cn.getMoves().get(i).y, (int) cn.getMoves().get(i).x, gl.getTurnStatus());
                        CarloNode childNode = new CarloNode(gl,(int) cn.getMoves().get(i).y,(int) cn.getMoves().get(i).x);
                        cn.setChild(childNode);
                        childNode.setParent(cn);

                    }

                    double tempScore = -1000000;
                    int tempIndex = 0;
                    for(int i = 0; i < cn.getChildren().size(); i++){
                        if(evaluation(cn.getChildren().get(i)) > tempScore){
                            tempScore = evaluation(cn.getChildren().get(i));
                            tempIndex = i;

                        }
                    }
                    backPropagation(rollOut(cn.getChildren().get(tempIndex)));
                    updateVisits(cn);

                }
            }
            else{
                double tempScore2 = -1000000;
                int tempIndex2 = 0;
                for(int i = 0; i < cn.getChildren().size(); i++){
                    if(evaluation(cn.getChildren().get(i)) > tempScore2){
                        tempScore2 = evaluation(cn.getChildren().get(i));
                        tempIndex2 = i;

                    }
                }
                cn = cn.getChildren().get(tempIndex2);
                }


            }

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
        System.out.println("Im in Rollout");
        int k =10;

       for(int i =0;i<k;i++){ //Need to change to a while loop
          // System.out.println("Im in the while loop");
           List<Vector2> moveList = cn.getMoves();
           if(moveList.size()>0)
           {
               int n =0;

               n = rand.nextInt(moveList.size());
               CarloNode childNode = new CarloNode(glCopy,(int)cn.getMoves().get(n).y,(int)cn.getMoves().get(n).x);

               glCopy.getBoard().setChip(childNode.getRow(),childNode.getColumn(),glCopy.getTurnStatus());  //in case it works, change the x with y\\
               glCopy.changeTurn();
               if(glCopy.getTurnStatus() == 1){
                   childNode.setScore(glCopy.getScore().x);
               }
               else{
                   childNode.setScore(glCopy.getScore().y);
               }

               childNode.setParent(cn);
               cn.setChild(childNode);
               cn = childNode;

           }
           else
           {
               return cn;
           }
       }
        System.out.println("Im done");
       return cn;
       
    }

   public double evaluation(CarloNode node){
       int index=0;


           double scoreAverage = node.getScore()/node.getVisits();
           double control = (Math.sqrt((Math.log(root.getVisits())/ node.getVisits())))*constant;
           double evaluation = scoreAverage + control;

       return evaluation;
   }

   public void backPropagation(CarloNode node){
          CarloNode cn = node;
          double score = cn.getScore();

          while(!(cn.equals(root))){

              cn = cn.getParent();
              score += cn.getScore();
              cn.setScore(score);

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
       float finalScore=0;

       GameLogic glCopy = node.getGameLogic().copy();
           if(glCopy.getTurnStatus()== 1)
           {
               finalScore=glCopy.getScore().x;
           }
           else
           {
               finalScore=glCopy.getScore().y;
           }

       System.out.println("Final Score : "+finalScore);
       node.setScore(finalScore);

   }


    @Override
    public int getScore() {
        return super.getScore();
    }

}
