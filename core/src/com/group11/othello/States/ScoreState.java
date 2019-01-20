package com.group11.othello.States;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.TimeUnit;


public class ScoreState extends State {

    public Texture scoreState;
    public GameStateManager gsm;
    BitmapFont yourBitmapFontName;
    String[] scores;

    public ScoreState(GameStateManager gsm,String[] scores)
    {
        this.gsm=gsm;
        try {
            TimeUnit.MILLISECONDS.sleep(400);
        } catch (Exception e) {
            System.out.println("Error");
        }

        this.scores = scores;
        scoreState= new Texture("ScoreMenu.png");

        yourBitmapFontName = new BitmapFont();
        yourBitmapFontName.getData().scale(2);
    }
    public void handleInput() {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            for(int i =0 ; i< getGsm().states.size();i++)
            {
                gsm.pop();
            }
            //getGsm().set(new MenuState(getGsm()));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        sb.draw(scoreState,0,0,800,910);
        yourBitmapFontName.setColor(0.0f, 0.0f, 0.0f, 1.0f);
        yourBitmapFontName.draw(sb, scores[0], 500, 735);
        yourBitmapFontName.draw(sb, scores[1], 500, 635);
        yourBitmapFontName.draw(sb, scores[2], 500, 535);
        yourBitmapFontName.draw(sb, scores[3], 500, 430);

        sb.end();
    }
}
