package com.group11.othello.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.Game.Othello;

public class MenuState extends State {
    private Texture othelloBoard;
    public int number = 0;

   public MenuState(GameStateManager gsm){
       super(gsm);
       othelloBoard = new Texture("OthelloBoard2.png");
   }

    @Override
    public void handleInput() {
        System.out.println(number++);
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
       sb.begin();
       sb.draw(othelloBoard,0,0, Othello.WIDTH,Othello.HEIGHT);
       sb.end();
    }
}
