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
    long timeConstraint = 1000;

    public MonteCarlo(int player) {
        root = null;
        this.playerTurn = player;
        eF = new EvaluationFunction();
    }

    public Vector3 nextMove(GameLogic gameLogic) {
        GameLogic glCopy = gameLogic.copy();
        root = new CarloNode(gameLogic.copy(), -1, -1);

        for (int i = 0; i < root.getMoves().size(); i++) {
            GameLogic glCopy1 = gameLogic.copy();

            glCopy1.getBoard().setChip((int) root.getMoves().get(i).y, (int) root.getMoves().get(i).x, glCopy.getTurnStatus());
            CarloNode childNode = new CarloNode(glCopy1, (int) root.getMoves().get(i).y, (int) root.getMoves().get(i).x);
            root.setChild(childNode);
            childNode.setParent(root);

        }

        CarloNode cn = root;
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() <= startTime+timeConstraint) {

            if (cn.getChildren().size() == 0) {
                if (cn.getVisits() == 0) {
                    backPropagation(rollOut(cn));
                    updateVisits(cn);

                    while (cn.getParent() != null) {
                        cn = cn.getParent();
                    }

                } else {
                    for (int i = 0; i < cn.getMoves().size(); i++) {
                        GameLogic gl = cn.getGameLogic().copy();
                        gl.changeTurn();
                        gl.getBoard().setChip((int) cn.getMoves().get(i).y, (int) cn.getMoves().get(i).x, gl.getTurnStatus());
                        CarloNode childNode = new CarloNode(gl, (int) cn.getMoves().get(i).y, (int) cn.getMoves().get(i).x);
                        cn.setChild(childNode);
                        childNode.setParent(cn);

                    }
                    cn = selection(cn);
                    backPropagation(rollOut(cn));
                    updateVisits(cn);

                    while (cn.getParent() != null) {
                        cn = cn.getParent();
                    }
                }
            } else {

                cn = selection(cn);

            }

        }

        for (int i = 0; i < root.getChildren().size(); i++) {
            root.getChildren().get(i).setScore(eF.bigEvaluation(root.getGameLogic(),playerTurn));
        }

        cn = selection(root);

        return new Vector3(cn.getColumn(), cn.getRow(), 0);
    }



    public CarloNode selection(CarloNode cn) {

        if (cn.getChildren().size() > 0) {
            double score = -100000000;
            int childNodeIndex = 0;
            for (int i = 0; i < cn.getChildren().size(); i++) {
                if (evaluation(cn.getChildren().get(i)) > score) {
                    score = evaluation(cn.getChildren().get(i));
                    childNodeIndex = i;
                }
            }
            cn = cn.getChildren().get(childNodeIndex);
        }
        return cn;
    }


    public CarloNode rollOut(CarloNode node) {

        CarloNode cn = node;
        GameLogic glCopy = cn.getGameLogic().copy();
        Random rand = new Random();

        while (glCopy.gameOver() == false) {

            List<Vector2> moveList = cn.getMoves();
            glCopy = cn.getGameLogic().copy();
            if (moveList.size() > 0) {
                int n = 0;

                n = rand.nextInt(moveList.size());
                glCopy.getBoard().setChip((int) cn.getMoves().get(n).y, (int) cn.getMoves().get(n).x, glCopy.getTurnStatus());
                glCopy.changeTurn();
                CarloNode childNode = new CarloNode(glCopy, (int) cn.getMoves().get(n).y, (int) cn.getMoves().get(n).x);

                childNode.setParent(cn);
                cn.setChild(childNode);
                cn = childNode;

            } else {

                setScore(cn);
                return cn;
            }
        }
        setScore(cn);
        return cn;

    }

    public double evaluation(CarloNode node) {

        double scoreAverage = node.getScore() / node.getVisits();
        double control = (Math.sqrt((Math.log(root.getVisits()) / node.getVisits()))) * constant;
        double evaluation = scoreAverage + control;

        return evaluation;
    }

    public void backPropagation(CarloNode node) {

        double score = node.getScore();

        while (!(node.equals(root))) {

            node = node.getParent();
            score += node.getScore();
            node.setScore(score);

        }

        root.setScore(score);
    }

    public void updateVisits(CarloNode node) {
        CarloNode cn = node;
        while (!(cn.equals(root))) {

            cn = cn.getParent();
            cn.addVisit();
        }
    }

    public void setScore(CarloNode node) {
        double finalScore = 0;
        GameLogic glCopy = node.getGameLogic().copy();

        if (playerTurn == -1) {
            if (glCopy.getScore()[0].x > glCopy.getScore()[0].y && glCopy.getScore()[0].x > glCopy.getScore()[1].x && glCopy.getScore()[0].x > glCopy.getScore()[1].y) {
                if(playerTurn == glCopy.getTurnStatus()){
                    finalScore = 1;
                }
                else{
                    finalScore = -1;
                }

            }

        }
        if (playerTurn == -2) {
            if (glCopy.getScore()[0].y > glCopy.getScore()[0].x && glCopy.getScore()[0].y > glCopy.getScore()[1].x && glCopy.getScore()[0].y > glCopy.getScore()[1].y) {
                if(playerTurn == glCopy.getTurnStatus()){
                    finalScore = 1;
                }
                else{
                    finalScore = -1;
                }
            }
        }
        if (playerTurn == -3) {
            if (glCopy.getScore()[1].x > glCopy.getScore()[0].y && glCopy.getScore()[1].x > glCopy.getScore()[0].x && glCopy.getScore()[1].x > glCopy.getScore()[1].y) {
                if(playerTurn == glCopy.getTurnStatus()){
                    finalScore = 1;
                }
                else{
                    finalScore = -1;
                }
            }
        }
        if (playerTurn == -4) {
            if (glCopy.getScore()[1].y > glCopy.getScore()[0].x && glCopy.getScore()[1].y > glCopy.getScore()[1].x && glCopy.getScore()[1].y > glCopy.getScore()[0].y) {
                if(playerTurn == glCopy.getTurnStatus()){
                    finalScore = 1;
                }
                else{
                    finalScore = -1;
                }
            }
        }

        node.setScore(finalScore);
    }

    @Override
    public int getScore() {
        return super.getScore();
    }

}
