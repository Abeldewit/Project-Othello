package com.group11.othello.AI.Minimax;

public class Minimax {

    Tree tree = new Tree();

    int[][] board = {{100,-20,10,5,5,10,-20,100},
                    {-20,-50,-2,-2,-2,-2,-50,-20},
                    {10,-2,-1,-1,-1,-1,-2,10},
                    {5,-2,-1,-1,-1,-1,-2,5},
                    {5,-2,-1,-1,-1,-1,-2,5},
                    {10,-2,-1,-1,-1,-1,-2,10},
                    {-20,-50,-2,-2,-2,-2,-50,-20},
                    {100,-20,10,5,5,10,-20,100}};

    public void makeTree(){
        tree.root = new Node(1,true);
        tree.root.children = new Node(5, true);
    }
}
