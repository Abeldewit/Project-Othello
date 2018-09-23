package com.group11.othello.Logic;

import com.badlogic.gdx.math.Vector3;

public class Cell {
    private static Vector3 position;
    private static Piece pieceInCell;

    public Cell(int x, int y) {
        position.x = x;
        position.y = y;
        pieceInCell = null;
    }

    public void setPieceInCell(Piece piece) {
        pieceInCell = piece;
    }
    public Piece getPieceInCell() {
        return pieceInCell;
    }

}
