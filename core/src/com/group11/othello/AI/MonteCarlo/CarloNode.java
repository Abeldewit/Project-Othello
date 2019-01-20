package com.group11.othello.AI.MonteCarlo;

import com.badlogic.gdx.math.Vector2;
import com.group11.othello.Logic.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class CarloNode {

    ArrayList<CarloNode> children;
    GameLogic glCopy;
    CarloNode parent;
    List<Vector2> moves;
    int playerTurn;
    double visits;
    double score;
    int row;
    int column;

    public CarloNode(GameLogic gl, int row, int column){

        this.glCopy = gl.copy();
        moves = glCopy.getValidMoves(gl);
        playerTurn = glCopy.getTurnStatus();
        children = new ArrayList<CarloNode>();
        visits = 1.0;
        score = 0.0;
        this.row = row;
        this.column = column;


    }



    public void setChild(CarloNode child) {
        children.add(child);
    }
    public void setScore(double score){this.score += score;}
    public void setParent(CarloNode parent) { this.parent = parent; }
    public void addVisit(){ visits++;}
    public void setPlayerTurn(int playerTurn){this.playerTurn = playerTurn;}



    public CarloNode getParent() { return parent; }
    public ArrayList<CarloNode> getChildren() {
        return children;
    }
    public double getScore(){return score;}
    public int getPlayerTurn() { return playerTurn; }
    public double getVisits(){return visits;}
    public GameLogic getGameLogic(){return glCopy;}
    public  List<Vector2> getMoves(){return moves;}
    public int getRow(){return row;}
    public int getColumn(){return column;}


}
