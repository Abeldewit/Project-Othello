package com.group11.othello.AI.Minimax;

import com.group11.othello.AI.MonteCarlo.CarloNode;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Node {

    int score;
    int[][] boardState;
    boolean max;
    ArrayList<Node> children = new ArrayList<Node>();
    Node parent;
    int playerTurn;

    public Node(int score, int[][] boardState, int playerTurn){
        super();
        this.score = score;
        this.max = false;
        children = null;
        this.boardState = boardState;
        this.playerTurn = playerTurn;
    }

    

    public void setChild(Node child) {
        children.add(child);
    }

    public Node getParent() { return parent; }

    public void setParent(Node parent) { this.parent = parent; }

    public int[][] getboardState() {
        return boardState;
    }

    public void setBoardState(int[][] boardState){
        this.boardState = boardState;
    }

    public void setPlayerTurn(int pT) { playerTurn = pT; }

    public int getPlayerTurn() { return playerTurn; }


}
