package com.group11.othello.States;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.Game.Othello;
import com.group11.othello.Logic.GameLogic;
import com.group11.othello.Logic.Player;
import com.group11.othello.Logic.Player1;
import com.group11.othello.Logic.Player2;

import java.util.Stack;

public class GameState extends State {
    private Texture othelloBoard;
    private Stack<Texture> chipTexture;
    GameLogic gL;
    public int number = 1;

    public GameState(GameStateManager gsm, Player1 player1)
    {
        super(gsm);
        gL = new GameLogic(player1);
        othelloBoard = new Texture("OthelloBoard2.png");
    }

    public GameState(GameStateManager gsm, Player1 player1, Player2 player2)
    {
        super(gsm);
        gL = new GameLogic(player1, player2);
        othelloBoard = new Texture("OthelloBoard2.png");
    }

    @Override
    public void handleInput()
    {
        /*
        need to put in a click listener and click tracker to know where to call the renderChip() method
         */
        //if()
    }

    @Override
    public void update(float dt)
    {

    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(othelloBoard,0,0, Othello.WIDTH,Othello.HEIGHT);
        sb.end();
    }

    public void renderChip(SpriteBatch sb, float x, float y)
    {
        if(gL.getTurnStatus() > 1)
        {
            chipTexture.push(new Texture("black"));
        }
        else
            {
                chipTexture.push(new Texture("white"));
            }
        sb.begin();
        sb.draw(chipTexture.peek(),x,y, Othello.WIDTH,Othello.HEIGHT);
        sb.end();
    }
}
