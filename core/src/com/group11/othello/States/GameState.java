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

    private Texture othelloBoard,scoreTable;
    private Texture WChip,BChip;
    private Stack<Texture> chipTexture;
    GameLogic gL;
    public int number = 1;

    public GameState(GameStateManager gsm/*, Player1 player1*/)
    {
        super(gsm);
        //gL = new GameLogic(player1);
        WChip = new Texture("WChip.png");
        BChip = new Texture("BChip.png");
        othelloBoard = new Texture("Table.png");
        scoreTable = new Texture("ScoreTable.png");
    }

    public GameState(GameStateManager gsm, Player1 player1, Player2 player2)
    {
        super(gsm);
        gL = new GameLogic(player1, player2);
        othelloBoard = new Texture("Table.png");
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
        sb.draw(scoreTable,0,800, 800,100);
        sb.draw(othelloBoard,0,0, 800,800);
        sb.draw(BChip,105,823,30,30);
        sb.draw(WChip,10,823, 30,30);
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
