package com.group11.othello.AI.MinMaxBot;



public class ScoreAndMove {
    private int score;
    private int[] move = new int[2];
    public ScoreAndMove(){
        this.score = 0;
        this.move = null;
    }

    public int getScore() {
        return score;
    }

    public int[] getMove() {
        return move;
    }

    public void setMove(int[] move) {
        this.move = move;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
