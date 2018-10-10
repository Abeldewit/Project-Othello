package com.group11.othello.AI.Minimax;

public class Node {

    int score;
    boolean max;
    Node children;

    public Node(int score, boolean max){
        this.score = score;
        this.max = max;
        children = null;
    }

    public Node getChildren() {
        return children;
    }

    public void setChildren(Node children) {
        this.children = children;
    }
}
