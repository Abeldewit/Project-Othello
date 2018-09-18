package com.group11.othello.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    private OrthographicCamera camera;
    private Vector3 mouse;
    private GameStateManager gsm;


    public State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    public abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Vector3 getMouse()
    {
        return mouse;
    }

    public GameStateManager getGsm()
    {
        return gsm;
    }
}
