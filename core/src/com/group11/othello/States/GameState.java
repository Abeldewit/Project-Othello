package com.group11.othello.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.group11.othello.Game.Othello;
import com.group11.othello.Logic.GameLogic;
import com.group11.othello.Logic.Player;
import com.group11.othello.Logic.Player1;
import com.group11.othello.Logic.Player2;

import java.util.ArrayList;
import java.util.Stack;

public class GameState extends State {

    private Texture othelloBoard,scoreTable;
    private Texture WChip,BChip;
    private ArrayList<Texture> chipTexture;
    private GameLogic gL;
    private ArrayList<Vector3> chipPosition;
    private Player1 player1;
    private Player2 player2;


    public GameState(GameStateManager gsm)
    {
        super(gsm);
        player1 = new Player1();
        player2 = new Player2();
        gL = new GameLogic(player1, player2);
        WChip = new Texture("WChip.png");
        BChip = new Texture("BChip.png");
        othelloBoard = new Texture("Table.png");
        scoreTable = new Texture("ScoreTable.png");
        chipTexture = new ArrayList<Texture>();
        chipPosition = new ArrayList<Vector3>();
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
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            renderChip();
            chipPosition.add(new Vector3((Gdx.input.getX()) , Othello.HEIGHT - Gdx.input.getY(),0));
            gL.changeTurn();
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(scoreTable,0,800, 800,100);
        sb.draw(othelloBoard,0,0, 800,800);
        sb.draw(BChip,105,823,30,30);
        sb.draw(WChip,10,823, 30,30);
        if(chipTexture.size() > 0) {
            for (int i = 0; i < chipTexture.size() - 1; i++) {
                sb.draw(chipTexture.get(i), chipPosition.get(i).x, chipPosition.get(i).y, 30, 30);
            }
        }
        sb.end();
    }

    public void renderChip()
    {
        if(gL.getTurnStatus() == 1)
        {
            chipTexture.add(new Texture("BChip.png"));
        }
        else
            {
                chipTexture.add(new Texture("WChip.png"));
            }

    }
}
