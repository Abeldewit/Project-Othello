package com.group11.othello.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.group11.othello.AI.MinMaxBot.MinMax;
import com.group11.othello.AI.MinMaxBot.AlphaBeta;
import com.group11.othello.AI.MonteCarlo.AI;
import com.group11.othello.AI.Greedy.Greedy;
import com.group11.othello.AI.MonteCarlo.MonteCarlo;
import com.group11.othello.Logic.GameLogic;

import java.util.concurrent.TimeUnit;

public class MenuState extends State {

    public int number = 0;
    public int verses = 0;
    public AI firstAi;
    public AI secondAi;

    public GameStateManager gsm;
    private Texture background;
    private Texture exitBtn,settBtn,playBtn,startBtn;
    private Texture greedyBtn,monteCarloBtn,abBtn;
    private Texture greedyBtn2,monteCarloBtn2,abBtn2;
    private Texture aiVSai,playerVSplayer,playerVSai;

    public boolean playerVsAi=false;
    public boolean aiVsAi=false;

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
       startBtn = new Texture("StartUp.png");

       greedyBtn2 = new Texture("GreedyUp.png");
       monteCarloBtn2=new Texture("MonteCarloHover.png");
       abBtn2 = new Texture("AlphaBetaUp.png");

       playerVSplayer = new Texture("PlayerVSPlayerUP.png");
       playerVSai =  new Texture("PlayerVSAIUp.png");
       aiVSai = new Texture("AiVSAiUp.png");
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
                    playerVsAi=false;
                    aiVsAi=false;
                }

        }
        if(number==1) {
            //Hover
            if (Gdx.input.getX() >= 50 && Gdx.input.getX() <= 200 && Gdx.input.getY() >= 130 && Gdx.input.getY() <= 180)
            {
                playerVSplayer = new Texture("PlayerVSPlayerHover.png");
            }

            else if(Gdx.input.getX() >= 50 && Gdx.input.getX() <= 200 && Gdx.input.getY() >= 190 && Gdx.input.getY() <= 240)
            {
                playerVSai =  new Texture("PlayerVSAIHover.png");
            }
            else if(Gdx.input.getX() >= 50 && Gdx.input.getX() <= 200 && Gdx.input.getY() >= 250 && Gdx.input.getY() <= 300)
            {
                aiVSai = new Texture("AiVSAiHover.png");
            }
            else
            {
                playerVSplayer = new Texture("PlayerVSPlayerUP.png");
                playerVSai =  new Texture("PlayerVSAIUp.png");
                aiVSai = new Texture("AiVSAiUp.png");
            }
            //Click
            if (Gdx.input.getX() >= 50 && Gdx.input.getX() <= 200 && Gdx.input.getY() >= 130 && Gdx.input.getY() <= 180 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(600);
                } catch (Exception e) {
                    System.out.println("Error");
                }
                gsm.push(new GameState(gsm));
            }

            if (Gdx.input.getX() >= 50 && Gdx.input.getX() <= 200 && Gdx.input.getY() >= 190 && Gdx.input.getY() <= 240 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                System.out.println("HEre");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (Exception e) {
                    System.out.println("Error");
                }
                playerVsAi = true;
                number = 2;
            }

            if (Gdx.input.getX() >= 50 && Gdx.input.getX() <= 200 && Gdx.input.getY() >= 250 && Gdx.input.getY() <= 300 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (Exception e) {
                    System.out.println("Error");
                }
                aiVsAi = true;
                number = 2;
            }
        }
            if(playerVsAi&&number==2) {


                //Hover
                if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 130 && Gdx.input.getY() <= 180) {
                    greedyBtn = new Texture("GreedyHover.png");
                } else if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 190 && Gdx.input.getY() <= 240) {
                    abBtn = new Texture("AlphaBetaHover.png");
                } else if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 250 && Gdx.input.getY() <= 300) {
                    monteCarloBtn = new Texture("MonteCarloHover.png");
                } else {
                    greedyBtn = new Texture("GreedyUp.png");
                    monteCarloBtn = new Texture("MonteCarloUp.png");
                    abBtn = new Texture("AlphaBetaUp.png");
                }

                //Click
                if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 130 && Gdx.input.getY() <= 180 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    gsm.push(new AIvsHuman(gsm, new Greedy(2)));
                }

                if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 190 && Gdx.input.getY() <= 240 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    gsm.push(new AIvsHuman(gsm, new AlphaBeta(2)));
                }

                if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 250 && Gdx.input.getY() <= 300 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    gsm.push(new AIvsHuman(gsm, new MonteCarlo(2)));
                }

                if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 310 && Gdx.input.getY() <= 360 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    playerVsAi = false;
                    number = 1;
                }

            }
            else if(aiVsAi && number==2)
            {
                //Hover
                if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 130 && Gdx.input.getY() <= 180) {
                    greedyBtn = new Texture("GreedyHover.png");
                } else if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 190 && Gdx.input.getY() <= 240) {
                    abBtn = new Texture("AlphaBetaHover.png");
                } else if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 250 && Gdx.input.getY() <= 300) {
                    monteCarloBtn = new Texture("MonteCarloHover.png");
                }
                else if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 580 && Gdx.input.getY() >= 130 && Gdx.input.getY() <= 180) {
                    greedyBtn2 = new Texture("GreedyHover.png");
                } else if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 580 && Gdx.input.getY() >= 190 && Gdx.input.getY() <= 240) {
                    abBtn2 = new Texture("AlphaBetaHover.png");
                } else if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 580 && Gdx.input.getY() >= 250 && Gdx.input.getY() <= 300) {
                    monteCarloBtn2 = new Texture("MonteCarloHover.png");
                }else if (Gdx.input.getX() >= 350 && Gdx.input.getX() <= 500 && Gdx.input.getY() >= 310 && Gdx.input.getY() <= 360) {
                    startBtn = new Texture("StartHover.png");
                }

                else {
                    greedyBtn = new Texture("GreedyUp.png");
                    monteCarloBtn = new Texture("MonteCarloUp.png");
                    abBtn = new Texture("AlphaBetaUp.png");
                    greedyBtn2 = new Texture("GreedyUp.png");
                    monteCarloBtn2 = new Texture("MonteCarloUp.png");
                    abBtn2 = new Texture("AlphaBetaUp.png");
                    startBtn = new Texture("StartUp.png");
                }

                //Click
                if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 190 && Gdx.input.getY() <= 240 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    System.out.println("First Ai Chosen: Alpha-Beta");
                    firstAi=new AlphaBeta(1);
                }

                if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 250 && Gdx.input.getY() <= 300 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    System.out.println("First Ai Chosen: MonteCarlo");
                    firstAi=new MonteCarlo(1);
                }

                if (Gdx.input.getX() >= 250 && Gdx.input.getX() <= 400 && Gdx.input.getY() >= 130 && Gdx.input.getY() <= 180 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    System.out.println("First Ai Chosen: Greedy");
                    firstAi=new Greedy(1);
                }

                if (Gdx.input.getX() >= 430 && Gdx.input.getX() <=580 && Gdx.input.getY() >= 190 && Gdx.input.getY() <= 240 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    System.out.println("Second Ai Chosen: Alpha-Beta");
                    secondAi=new AlphaBeta(2);
                }

                if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 580 && Gdx.input.getY() >= 250 && Gdx.input.getY() <= 300 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    System.out.println("Second Ai Chosen: Monte Carlo");
                    secondAi=new MonteCarlo(2);
                }

                if (Gdx.input.getX() >= 430 && Gdx.input.getX() <= 580 && Gdx.input.getY() >= 130 && Gdx.input.getY() <= 180 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    System.out.println("Second Ai Chosen: Greedy");
                    secondAi=new Greedy(2);
                }

                if (Gdx.input.getX() >= 350 && Gdx.input.getX() <= 500 && Gdx.input.getY() >= 310 && Gdx.input.getY() <= 360 && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                    if(firstAi==null)
                    {
                        System.out.println("Please choose the first AI");
                    }
                    else if(secondAi==null)
                    {
                        System.out.println("Please choose the second AI");
                    }
                    else if(firstAi!=null && secondAi!=null)
                    {
                        gsm.push(new AIvsAI_State(gsm,firstAi,secondAi));
                    }
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
           sb.draw(playerVSplayer,50,720,150,50);
           sb.draw(playerVSai,50,660,150,50);
           sb.draw(aiVSai,50,600,150,50);

       }
       if(number==2 && aiVsAi) {
           sb.draw(greedyBtn, 250, 720, 150, 50);
           sb.draw(abBtn, 250, 660, 150, 50);
           sb.draw(monteCarloBtn, 250, 600, 150, 50);

            sb.draw(greedyBtn2, 430, 720, 150, 50);
            sb.draw(abBtn2, 430, 660, 150, 50);
            sb.draw(monteCarloBtn2, 430, 600, 150, 50);
            sb.draw(startBtn, 350, 540, 150, 50);
        }
        else if(number==2 && playerVsAi)
       {

           sb.draw(greedyBtn, 250, 720, 150, 50);
           sb.draw(abBtn, 250, 660, 150, 50);
           sb.draw(monteCarloBtn, 250, 600, 150, 50);
       }
       sb.end();
    }
}
