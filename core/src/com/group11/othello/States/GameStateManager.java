package com.group11.othello.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.AI.Testing;

import java.io.IOException;
import java.util.Stack;

public class GameStateManager {

    public static Testing testing;


    public static Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
        try {
            testing = new Testing();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
