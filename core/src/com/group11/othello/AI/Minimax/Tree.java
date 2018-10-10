package com.group11.othello.AI.Minimax;

public class Tree {

    Node root;

    public Tree(){

        root = null;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }
}
