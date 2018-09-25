package com.group11.othello.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Chip {

    private Vector3 position;
    private Texture chipTexture;
    private String color;


    public Chip(String chipTexture) {
        this.chipTexture = new Texture(chipTexture);
        color = chipTexture;

    }

    public void setPosition(Vector3 pos) {
        position = pos;
    }

    public String getColor(){ return color;}

}
