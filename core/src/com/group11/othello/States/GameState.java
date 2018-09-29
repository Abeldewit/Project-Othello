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

import java.util.concurrent.TimeUnit;
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

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (Exception e) {
                System.out.println("Error");
            }

                int x = (int) Math.floor(Gdx.input.getX() / 100);
                int y = (int) Math.floor((Othello.HEIGHT - Gdx.input.getY()) / 100);

                int convX=x * 100 + 35;
                int convY=y * 100 + 35;

            if(chipPosition.size()>0) {
                if (!isTooClose(convX, convY)) {
                    chipPosition.add(new Vector3(convX, convY, 0));
                    renderChip();
                    gL.changeTurn();
                } else {
                    System.out.println("Fail");
                }
            }
            else
                {
                    chipPosition.add(new Vector3((x * 100 + 35), y * 100 + 35, 0));
                    renderChip();
                    gL.changeTurn();
                }
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
        if(chipTexture.size()>0) {
            for (int i = 0; i < chipTexture.size() - 1; i++) {

                sb.draw(chipTexture.get(i), chipPosition.get(i+1).x, chipPosition.get(i+1).y, 30, 30);
               /* if(chipPosition.size()>=10)
                {
                    for (int h = 0; i < chipPosition.size() - 1; h++)
                    {
                        System.out.println("X :"+chipPosition.get(h).x + "     Y :" +chipPosition.get(h).y);
                    }
                }*/
            }
        }
        sb.end();
    }

    public void renderChip()
    {
        if(gL.getTurnStatus() == 0)
        {
            chipTexture.add(new Texture("BChip.png"));
        }
        else if (gL.getTurnStatus() == 1)
            {
                chipTexture.add(new Texture("WChip.png"));
            }

    }

    public boolean isTooClose(float coordX,float coordY)
    {
        for(int i=0;i<chipPosition.size();i++)
        {
            System.out.println(Math.abs(coordX-chipPosition.get(i).x));
            System.out.println(Math.abs(coordY-chipPosition.get(i).y));
            if(Math.abs(coordX-chipPosition.get(i).x)== 0 && Math.abs(coordY-chipPosition.get(i).y)==0)
            {
                return true;
            }
        }

        return false;
    }
}
