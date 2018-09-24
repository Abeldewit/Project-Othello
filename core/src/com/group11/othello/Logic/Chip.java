package com.group11.othello.Logic;

import com.badlogic.gdx.math.Vector3;

public class Chip {

    private Vector3 position;
    private Player player;

    public Chip(Player player) {
        this.player = player;
    }

    public void setPosition(Vector3 pos) {
        position = pos;
    }

}
