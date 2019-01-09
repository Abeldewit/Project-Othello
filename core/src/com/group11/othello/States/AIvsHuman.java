package com.group11.othello.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.group11.othello.AI.MinMaxBot.AlphaBeta;
import com.group11.othello.AI.MinMaxBot.MinMax;
import com.group11.othello.AI.MonteCarlo.AI;
import com.group11.othello.Game.Othello;
import com.group11.othello.Logic.GameLogic;
import com.group11.othello.Logic.Player1;
import com.group11.othello.Logic.Player2;

import java.util.concurrent.TimeUnit;

public class AIvsHuman extends GameState {
    private Texture othelloBoard,scoreTable,menuButton;
    private Texture WChip,BChip;
    private BitmapFont font;
    private GameLogic gL;
    private static Player1 player1;
    private static Player2 player2;
    private GameStateManager gsm;
    private AI ai1;
    long aiTime = 0;
    int aiMoves = 0;


    public AIvsHuman(GameStateManager gsm, AI ai)
    {

        super(gsm);
        player1 = new Player1();
        player2 = new Player2();
        ai1 = ai;
        gL = new GameLogic();
        WChip = new Texture("WChip.png");
        BChip = new Texture("BChip.png");
        menuButton = new Texture("MenuButtonUp.png");
        othelloBoard = new Texture("Table.png");
        scoreTable = new Texture("Score.png");
        font = new BitmapFont();
        this.gsm=gsm;
    }

    @Override
    public void handleInput()
    {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (Exception e) {
            System.out.println("Error");
        }

        if(gL.getValidMoves().size()==0){
            gL.changeTurn();

        }
        if(gL.gameOver() == true){
            System.out.println("AI Runtime : " + (aiTime/aiMoves));
            if(player1.getScore() > player2.getScore())
            {
                gsm.set(new EndState(gsm,1,player1.getScore()));
            }
            else
            {
                gsm.set(new EndState(gsm,2,player2.getScore()));
            }

        }
        if(gL.getTurnStatus() == 2 && gL.getValidMoves().size() != 0) {
            Vector2 aiMove = new Vector2();

                long prevMillis = System.currentTimeMillis();
                aiMove = ai1.nextMove(gL);
                long currentMillis = System.currentTimeMillis();
                aiTime += currentMillis - prevMillis;
                aiMoves++;

                int x = (int) aiMove.x;
                int y = (int) aiMove.y;
                if(y >=0 && x >=0) {
                    gL.getBoard().setChip(y, x, gL.getTurnStatus());
                    runAvailable(x, y);

                    player1.setScore((int) gL.getScore().x);
                    player2.setScore((int) gL.getScore().y);
                }
                gL.changeTurn();

        }
        else
            {
                if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

                    if(Gdx.input.getY() >=100) {
                        int x = (int) Math.floor(Gdx.input.getX() / 100);
                        int y = (int) Math.floor((Othello.HEIGHT - Gdx.input.getY()) / 100);

                        if (isTooClose(x, y) == false) {
                            if (gL.checkMoves(x, y, gL.getTurnStatus()) > 0) {

                                gL.getBoard().setChip(y, x, gL.getTurnStatus());
                                //  gL.getBoard().printBoard();
                                runAvailable(x, y);


                                if (gL.getTurnStatus() == 1) {

                                    player1.setScore((int) gL.getScore().x);
                                    player2.setScore((int) gL.getScore().y);

                                    gL.changeTurn();
                                } else {
                                    player1.setScore((int) gL.getScore().x);
                                    player2.setScore((int) gL.getScore().y);

                                    gL.changeTurn();
                                }
                            } else {

                                System.out.println("loser");
                            }
                        } else {
                            System.out.println("Tile Occupied");
                        }
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
            WChip.dispose();
            BChip.dispose();
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
        font.draw(sb,String.valueOf(player1.getScore()), 80, 843);
        font.draw(sb,String.valueOf(player2.getScore()), 175, 843);
        if(gL.getTurnStatus() == 1)
        {
            sb.draw(WChip,412,815,30,30);
        }
        else
        {
            sb.draw(BChip,412,815,30,30);
        }

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

    /*public GameLogic getgL() {
        return gL;
    }*/




}
