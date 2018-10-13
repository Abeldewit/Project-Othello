package com.group11.othello.AI;

public class Point {
   private int column;
   private int row;
    private int value;
    public Point(int column, int row, int value) {
        this.column = column;
        this.row = row;
        this.value = value;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getValue() {
        return value;
    }
}
