package com.group11.othello.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.States.GameState;
import com.group11.othello.Game.Othello;
import java.util.concurrent.TimeUnit;

public class MenuState extends State {

    public int number = 0;

    public GameStateManager gsm;
    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture settBtn;

   public MenuState(GameStateManager gsm){
       super(gsm);
       this.gsm = gsm;
       background = new Texture("TableNew.png");
       playBtn = new Texture("StartButton.png");
       exitBtn = new Texture("ExitButton.png");
       settBtn = new Texture("SettingsUP.png");
   }

    @Override
    public void handleInput()
    {
        //Hover
        if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 420 && Gdx.input.getY() <= 520)
        {
            playBtn = new Texture("StartButtonHover.png");
        }
        else if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 570 && Gdx.input.getY() <= 670)
        {
            settBtn = new Texture("SettingsHover.png");
        }

        else if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 720 && Gdx.input.getY() <= 820)
        {
            exitBtn = new Texture("ExitButtonHover.png");
        }

        else
            {
                playBtn = new Texture("StartButton.png");
                exitBtn = new Texture("ExitButton.png");
                settBtn = new Texture("SettingsUP.png");
            }
        //Clicker
        if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 420 && Gdx.input.getY() <= 520 &&  Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            try{
            TimeUnit.MILLISECONDS.sleep(200);}
            catch (Exception e){
                System.out.println("Error");
            }
            gsm.push(new GameState(gsm));
        }

        else if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 720 && Gdx.input.getY() <= 820 && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            Gdx.app.exit();
        }

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
