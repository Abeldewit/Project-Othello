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

    private Texture othelloBoard,scoreTable,menuButton;
    private Texture WChip,BChip;
    private ArrayList<Texture> chipTexture;
    private GameLogic gL;
    private ArrayList<Vector3> chipPosition;
    private Player1 player1;
    private Player2 player2;
    private GameStateManager gsm;

    public GameState(GameStateManager gsm)
    {

        super(gsm);
        player1 = new Player1();
        player2 = new Player2();
        gL = new GameLogic(player1, player2);
        WChip = new Texture("WChip.png");
        BChip = new Texture("BChip.png");
        menuButton = new Texture("MenuButtonUp.png");
        othelloBoard = new Texture("Table.png");
        scoreTable = new Texture("ScoreTable.png");
        chipTexture = new ArrayList<Texture>();
        chipTexture.add(WChip);
        chipPosition = new ArrayList<Vector3>();
        this.gsm=gsm;
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
            if(Gdx.input.getY() >=100) {
                int x = (int) Math.floor(Gdx.input.getX() / 100);
                int y = (int) Math.floor((Othello.HEIGHT - Gdx.input.getY()) / 100);

                int convX = x * 100 + 35;
                int convY = y * 100 + 35;

                if (chipPosition.size() > 0) {
                    if (!isTooClose(convX, convY)) {
                        chipPosition.add(new Vector3(convX, convY, 0));
                        System.out.println(chipPosition.get(chipPosition.size() - 1));
                        renderChip();
                        gL.changeTurn();
                    } else {
                        System.out.println("Tile Occupied");
                    }
                } else {
                    chipPosition.add(new Vector3(convX, convY, 0));
                    System.out.println(chipPosition.get(chipPosition.size() - 1));
                    renderChip();
                    System.out.println(chipTexture.size());
                    gL.changeTurn();
                }
            }
        }

        if(Gdx.input.getX() >=580 && Gdx.input.getX()<=780 && Gdx.input.getY() >= 30 && Gdx.input.getY() <= 70)
        {
            menuButton = new Texture("MenuButtonHover.png");
        }
        else
        {
            menuButton = new Texture("MenuButtonUp.png");
        }

        if(Gdx.input.getX() >=580 && Gdx.input.getX()<=780 && Gdx.input.getY() >= 30 && Gdx.input.getY() <= 70 && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            gsm.set(new MenuState(gsm));
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
        sb.draw(menuButton,580,830,200,40);
        if(chipTexture.size()>1) {
            for (int i = 0; i < chipTexture.size() - 1; i++) {

                sb.draw(chipTexture.get(i), chipPosition.get(i).x, chipPosition.get(i).y, 30, 30);
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
        else
            {
                chipTexture.add(new Texture("WChip.png"));
            }

    }

    public boolean isTooClose(float coordX,float coordY)
    {
        for(int i=0;i<chipPosition.size();i++)
        {
            if(Math.abs(coordX-chipPosition.get(i).x)== 0 && Math.abs(coordY-chipPosition.get(i).y)==0)
            {
                return true;
            }
        }

        return false;
    }
}
