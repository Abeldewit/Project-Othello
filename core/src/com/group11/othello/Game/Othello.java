package com.group11.othello.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.AI.Testing;
import com.group11.othello.States.GameState;
import com.group11.othello.States.GameStateManager;
import com.group11.othello.States.MenuState;
import com.group11.othello.States.ScoreState;

import java.io.IOException;

public class Othello extends ApplicationAdapter {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 900;
    public GameStateManager gsm;
    public SpriteBatch sb;

    @Override
    public void create () {
        sb = new SpriteBatch();
        gsm = new GameStateManager();
        Gdx.gl.glClearColor(1, 1, 1, 1);


        gsm.push(new MenuState(gsm));

        clickProcessor inputProcessor = new clickProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render ()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(sb);
    }
}