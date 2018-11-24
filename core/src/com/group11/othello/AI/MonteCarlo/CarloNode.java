package com.group11.othello.AI.MonteCarlo;

import com.badlogic.gdx.math.Vector2;
import com.group11.othello.AI.Minimax.Node;
import com.group11.othello.Logic.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class CarloNode {

    ArrayList<Node> children = new ArrayList<Node>();
    GameLogic glCopy;
    Node parent;
    List<Vector2> moves;
    int playerTurn;
    int visits;
    double score;

    public CarloNode(GameLogic gl){

        this.glCopy = gl.copy();
        moves = glCopy.getValidMoves();
        playerTurn = glCopy.getTurnStatus();
        children = null;
        visits = 1;

    }



    public void setChild(Node child) {
        children.add(child);
    }
    public void setScore(double score){this.score = score;}
    public void setParent(Node parent) { this.parent = parent; }
    public void addVisit(){ visits++;}



    public Node getParent() { return parent; }
    public ArrayList<Node> getChildren() {
        return children;
    }
    public double getScore(){return score;}
    public int getPlayerTurn() { return playerTurn; }
    public int getVisits(){return visits;}
    public GameLogic getGameLogic(){return glCopy;}
    public  List<Vector2> getMoves(){return moves;}


}
