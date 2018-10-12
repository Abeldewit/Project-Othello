package com.group11.othello.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private BitmapFont font;
    private GameLogic gL;
    private Player1 player1;
    private Player2 player2;
    private GameStateManager gsm;


    public GameState(GameStateManager gsm)
    {

        super(gsm);
        player1 = new Player1();
        player2 = new Player2();
        gL = new GameLogic();
        WChip = new Texture("WChip.png");
        BChip = new Texture("BChip.png");
        menuButton = new Texture("MenuButtonUp.png");
        othelloBoard = new Texture("Table.png");
        scoreTable = new Texture("ScoreTable.png");
        font = new BitmapFont();
        this.gsm=gsm;
    }

    public GameState(GameStateManager gsm, Player1 player1, Player2 player2)
    {
        super(gsm);
        gL = new GameLogic();
        othelloBoard = new Texture("Table.png");
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

            //why ?
            try {
                TimeUnit.MILLISECONDS.sleep(800);
            } catch (Exception e) {
                System.out.println("Error");
            }
            if(Gdx.input.getY() >=100) {
                int x = (int) Math.floor(Gdx.input.getX() / 100);
                int y = (int) Math.floor((Othello.HEIGHT - Gdx.input.getY()) / 100);
                System.out.println("x = " + x);
                System.out.println("y = " + y);


                    if (isTooClose(x, y) == false) {
                      //  if (gL.checkLegalMove(x, y, gL.getTurnStatus())) {
                            gL.getBoard().setChip(y, x, gL.getTurnStatus());
                            runAvailable(x, y);
                            gL.getBoard().printBoard();
                            if (gL.getTurnStatus() == 1) {

                                player1.setScore((int) gL.getScore().x);
                                player2.setScore((int) gL.getScore().y);
                            } else {
                                player1.setScore((int) gL.getScore().x);
                                player2.setScore((int) gL.getScore().y);

                            }
                        }
                  //  }

                    else {
                        System.out.println("Tile Occupied");
                    }



                gL.changeTurn();

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
            WChip.dispose();
            BChip.dispose();
            gsm.pop();
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
        font.draw(sb,String.valueOf(player1.getScore()), 80, 843);
        font.draw(sb,String.valueOf(player2.getScore()), 175, 843);

            for(int i = 0; i < gL.getBoard().getBoard().length-1; i++)
            {
                for(int j = 0; j < gL.getBoard().getBoard().length-1; j++)
                {
                    if(gL.getBoard().getBoard()[j][i] == 1)
                    {
                        sb.draw(WChip, i*100 + 19, (j)*100 + 19, 60, 60);
                    }
                    else if(gL.getBoard().getBoard()[j][i] == 2)
                    {
                        sb.draw(BChip, i*100 + 19, (j)*100 + 19, 60, 60);
                    }
                    else{}
                }
            }

        sb.end();
    }


    public boolean isTooClose(int x,int y)
    {
        if(gL.getBoard().getBoard()[y][x] != 0)
        {
            return true;
        }

        return false;
    }

    public void runAvailable(int x,int y)
    {
        gL.rightDirection(x,y, gL.getTurnStatus());
        gL.leftDirection(x,y, gL.getTurnStatus());
        gL.upDirection(x,y, gL.getTurnStatus());
        gL.downDirection(x,y, gL.getTurnStatus());
        gL.northEastDirection(x,y, gL.getTurnStatus());
        gL.northWestDirection(x,y, gL.getTurnStatus());
        gL.southWestDirection(x,y, gL.getTurnStatus());
        gL.southEastDirection(x,y, gL.getTurnStatus());
    }

    public GameState getGame() {
        return this;
    }

    public GameLogic getgL() {
        return gL;
    }


}
