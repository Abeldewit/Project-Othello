package com.group11.othello.AI.MonteCarlo;


import com.badlogic.gdx.math.Vector2;
import com.group11.othello.Logic.GameLogic;

import java.util.List;
import java.util.Random;

public class MonteCarlo extends AI {

    private CarloNode root;
    private GameLogic gl;
    private int playerTurn;
    private final int SIMULATIONS = 10;
    int constant = 2;

   public MonteCarlo (int player){
       root = null;
       this.playerTurn = player;
   }

    public Vector2 nextMove(GameLogic gameLogic) {
        GameLogic gl = gameLogic.copy();
        root = new CarloNode(gameLogic.copy(),-1,-1);
        CarloNode cn = root;
        double score = 0;

        for(int i = 0; i < root.getMoves().size(); i++){
            GameLogic glCopy = gl.copy();
            glCopy.getBoard().setChip((int) root.getMoves().get(i).y, (int) root.getMoves().get(i).x, glCopy.getTurnStatus());
            CarloNode childNode = new CarloNode(glCopy,(int) root.getMoves().get(i).y,(int) root.getMoves().get(i).x);
            setScore(childNode);
            root.setChild(childNode);
            root.setPlayerTurn(playerTurn);
            childNode.setParent(root);
        }


        while(root.getVisits() < SIMULATIONS){

            if(cn.getChildren().size() == 0){
                if(cn.getVisits() == 0){

                    backPropagation(rollOut(cn));
                    updateVisits(cn);

                }
                else{
                    for(int i = 0; i < cn.getMoves().size(); i++){
                        GameLogic glCopy = gl.copy();
                        glCopy.changeTurn();
                        glCopy.getBoard().setChip((int) cn.getMoves().get(i).y, (int) cn.getMoves().get(i).x, glCopy.getTurnStatus());
                        CarloNode childNode = new CarloNode(glCopy,(int) cn.getMoves().get(i).y,(int) cn.getMoves().get(i).x);
                        childNode.setPlayerTurn(glCopy.getTurnStatus());
                        setScore(childNode);
                        cn.setChild(childNode);
                        childNode.setParent(cn);
                    }

                    backPropagation(rollOut(cn.getChildren().get(0)));
                    updateVisits(cn);

                }
            }
            else{
                cn = selection(cn);


            }

        }

        int childNodeIndex = 0;
        for(int i = 0; i < cn.getChildren().size(); i++){
            if(evaluation(root.getChildren().get(i)) > score){
                score = evaluation(root.getChildren().get(i));
                childNodeIndex = i;
            }
        }

        CarloNode nextNode = cn.getChildren().get(childNodeIndex);
        Vector2 move = new Vector2(nextNode.getRow(),nextNode.getColumn());




       return new Vector2();

    }


    public CarloNode selection(CarloNode cn){

            while(cn.getChildren() != null){
                double score = -100000000;
                int childNodeIndex = 0;
                for(int i = 0; i < cn.getChildren().size(); i++){
                    if(evaluation(cn.getChildren().get(i)) > score){
                        score = evaluation(cn);
                        childNodeIndex = i;
                    }
                }

                cn = cn.getChildren().get(childNodeIndex);

            }

            return cn;
        }




    public CarloNode rollOut(CarloNode node){

       CarloNode cn = node;

       while(cn.getGameLogic().gameOver() != true){
             List<Vector2> moveList = cn.moves;
              for(int i = 0; i < moveList.size(); i++){
                  GameLogic glCopy = cn.getGameLogic().copy();
                  if(!(cn.equals(root))){
                      glCopy.changeTurn();
                  }

                  glCopy.getBoard().setChip((int) moveList.get(i).y, (int) moveList.get(i).x, glCopy.getTurnStatus());

                  CarloNode childNode = new CarloNode(glCopy,(int) moveList.get(i).y,(int) moveList.get(i).x);
                  setScore(childNode);
                  cn.setChild(childNode);
                  childNode.setParent(cn);
              }

               Random rand = new Random();
               int n = rand.nextInt(moveList.size());
               cn = cn.getChildren().get(n);
       }

       return cn;
       
    }


   public double evaluation(CarloNode node){

       double scoreAverage = node.getScore()/node.getVisits();
       double control = (Math.sqrt((Math.log(root.getVisits())/ node.getVisits())))*constant;
       double eval = scoreAverage + control;

       return eval;

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
       double score = 0;

       for(int i = 0; i < node.getMoves().size(); i++){
           GameLogic glCopy = node.getGameLogic().copy();
           glCopy.getBoard().setChip((int) node.getMoves().get(i).y, (int) node.getMoves().get(i).x, node.getGameLogic().getTurnStatus());

           if(glCopy.getTurnStatus() == playerTurn){
               score += glCopy.getScore().x;
           }
           else{
               score -= glCopy.getScore().y;
           }
       }

       node.setScore(score);

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
