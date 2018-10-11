package com.group11.othello.AI.Minimax;

import com.group11.othello.Logic.*;
import com.group11.othello.States.GameState;

import static com.group11.othello.Logic.GameLogic.legalMoves;

public class Tree {

    private Node root;

    public Tree(){
        root = null;

    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void createNodes(int[][] board, Node parent) {
        int player = 0;
        //if the Node above was white playing, all the Nodes below will be the turn of black (and vice versa)
        if(parent.getPlayerTurn() == 1) { player = 2; }
        if(parent.getPlayerTurn() == 2) { player = 1; }


        GameLogic.createLegalField();
        for(int i = 0; i < legalMoves.length; i++) {
            for(int j = 0; j < legalMoves[0].length; j++) {
                if(legalMoves[i][j] == true) {

                    int[][] newBoard = board;
                    newBoard[i][j] = player;
                    Node newNode = new Node(0,newBoard,player);
                    newNode.setParent(parent);
                    parent.setChild(newNode);
                }
            }
        }
    }



}
