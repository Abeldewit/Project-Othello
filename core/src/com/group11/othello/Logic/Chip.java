package com.group11.othello.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Chip {

    private Vector3 position;
    private Player player;
    private Texture chipTexture;

    public Chip(Player player, String chipTexture) {
        this.player = player;
        this.chipTexture = new Texture(chipTexture);
    }

    public void setPosition(Vector3 pos) {
        position = pos;
    }

}
