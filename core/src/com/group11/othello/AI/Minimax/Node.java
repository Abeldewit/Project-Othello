package com.group11.othello.AI.Minimax;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Node {

    int score;
    int[][] boardState;
    boolean max;
    ArrayList<Node> children = new ArrayList<Node>();
    Node parent;

    public Node(int score, boolean max, int[][] boardState){
        this.score = score;
        this.max = max;
        children = null;
        this.boardState = boardState;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChild(Node child) {
        children.add(child);
    }

    public Node getParents() { return parent; }

    public void setParent(Node parent) { this.parent = parent; }

    public int[][] getboardState() {
        return boardState;
    }

    public void setBoardState(int[][] boardState){
        this.boardState = boardState;
    }


}
