package com.group11.othello.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.Game.Othello;

public class MenuState extends State {

    public int number = 0;
    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture settBtn;

   public MenuState(GameStateManager gsm){
       super(gsm);
       background = new Texture("TableNew.png");
       playBtn = new Texture("StartButton.png");
       exitBtn = new Texture("ExitButton.png");
       settBtn = new Texture("Settings.png");
   }

    @Override
    public void handleInput()
    {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
       sb.begin();
       sb.draw(background,0,0, 800,900);
       sb.draw(playBtn,470,380,300,100);
       sb.draw(settBtn,470,230,300,100);
       sb.draw(exitBtn,470,80,300,100);
       sb.end();
    }
}
