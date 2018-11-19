package com.group11.othello.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.AI.AI;
import com.group11.othello.AI.Greedy.Greedy;
import com.group11.othello.Logic.GameLogic;
import com.group11.othello.States.GameState;
import com.group11.othello.Game.Othello;
import java.util.concurrent.TimeUnit;

public class MenuState extends State {

    public int number = 0;

    public GameStateManager gsm;
    private Texture background;
    private Texture playBtn;
    private Texture exitBtn;
    private Texture settBtn;
    private Texture greedyBtn,monteCarloBtn,abBtn;

   public MenuState(GameStateManager gsm){
       super(gsm);
       this.gsm = gsm;
       background = new Texture("TableNew.png");
       playBtn = new Texture("StartButton.png");
       exitBtn = new Texture("ExitButton.png");
       settBtn = new Texture("SettingsUP.png");
       greedyBtn = new Texture("GreedyUp.png");
       monteCarloBtn=new Texture("MonteCarloHover.png");
       abBtn = new Texture("AlphaBetaUp.png");
   }

    @Override
    public void handleInput()
    {

        //Hover
        if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 420 && Gdx.input.getY() <= 520)
        {
            playBtn = new Texture("StartButtonHover.png");
        }
        else if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 570 && Gdx.input.getY() <= 670)
        {
            settBtn = new Texture("SettingsHover.png");
        }

        else if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 720 && Gdx.input.getY() <= 820)
        {
            exitBtn = new Texture("ExitButtonHover.png");
        }

        else
            {
                playBtn = new Texture("StartButton.png");
                exitBtn = new Texture("ExitButton.png");
                settBtn = new Texture("SettingsUP.png");
            }
        //Clicker
        if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 420 && Gdx.input.getY() <= 520 &&  Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            try{
            TimeUnit.MILLISECONDS.sleep(200);}
            catch (Exception e){
                System.out.println("Error");
            }
            gsm.push(new GameState(gsm));

        }

        if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 720 && Gdx.input.getY() <= 820 && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            Gdx.app.exit();
        }

        if(Gdx.input.getX() >=470 && Gdx.input.getX()<=770 && Gdx.input.getY() >= 540 && Gdx.input.getY() <= 710 && Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            try{
                TimeUnit.MILLISECONDS.sleep(500);}
            catch (Exception e){
                System.out.println("Error");
            }
            if(number == 0)
            {
                number = 1;
            }
            else
                {
                    number = 0;
                }

        }
        if(number==1) {
            //Hover
            if(Gdx.input.getX() >= 50 && Gdx.input.getX() <= 350 && Gdx.input.getY() >= 80 && Gdx.input.getY() <= 180)
            {
                greedyBtn=new Texture("GreedyHover.png");
            }
            else if(Gdx.input.getX() >= 50 && Gdx.input.getX() <= 350 && Gdx.input.getY() >= 200 && Gdx.input.getY() <= 300)
            {
                abBtn= new Texture("AlphaBetaHover.png");
            }
            else if(Gdx.input.getX() >= 50 && Gdx.input.getX() <= 350 && Gdx.input.getY() >= 320 && Gdx.input.getY() <= 420)
            {
                monteCarloBtn = new Texture("MonteCarloHover.png");
            }
            else
            {
                greedyBtn = new Texture("GreedyUp.png");
                monteCarloBtn=new Texture("MonteCarloUp.png");
                abBtn = new Texture("AlphaBetaUp.png");
            }
            //Click
            if (Gdx.input.getX() >= 50 && Gdx.input.getX() <= 350 && Gdx.input.getY() >= 80 && Gdx.input.getY() <= 180 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (Exception e) {
                    System.out.println("Error");
                }
                gsm.push(new AIvsHuman(gsm, new Greedy(new GameLogic())));
            }

            if (Gdx.input.getX() >= 50 && Gdx.input.getX() <= 350 && Gdx.input.getY() >= 200 && Gdx.input.getY() <= 300 &&Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (Exception e) {
                    System.out.println("Error");
                }// MinMax by lilly AIvsAI simulation
                gsm.push(new AIvsAI_State(gsm));
            }

            if (Gdx.input.getX() >= 50 && Gdx.input.getX() <= 350 && Gdx.input.getY() >= 320 && Gdx.input.getY() <= 420 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (Exception e) {
                    System.out.println("Error");
                }
                gsm.push(new AIvsHuman(gsm, new AI()));
            }

            if (Gdx.input.getX() >= 50 && Gdx.input.getX() <= 350 && Gdx.input.getY() >= 440 && Gdx.input.getY() <= 540 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (Exception e) {
                    System.out.println("Error");
                }
                gsm.push(new AIvsHuman(gsm, new AI()));
            }


        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
       sb.begin();
       sb.draw(background,0,0, 800,900);
       sb.draw(playBtn,470,380,300,100);
       sb.draw(settBtn,470,230,300,100);
       sb.draw(exitBtn,470,80,300,100);
       if(number == 1)
       {
           sb.draw(greedyBtn,50,720,300,100);
           sb.draw(abBtn,50,600,300,100);
           sb.draw(monteCarloBtn,50,480,300,100);
           sb.draw(settBtn,50,360,300,100);

       }
       sb.end();
    }
}
