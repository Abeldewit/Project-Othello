package com.group11.othello.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.AI.Testing;
import com.group11.othello.Logic.Player;
import com.badlogic.gdx.graphics.Texture;
import org.ietf.jgss.GSSManager;

import java.io.IOException;
import java.util.Arrays;

public class EndState extends State {

   private int player;
   private String playerScore,secondScore,thirdScore,fourthScore;
    private String yourScoreName;
    private Texture winState;
    BitmapFont yourBitmapFontName;
    boolean isTie=false;
    Testing testing;
    boolean isMultiplayer;
    public String[] scores;

    public EndState(GameStateManager gsm , int player, int playerScore) {
        super(gsm);
        testing = super.getGsm().testing;

        isMultiplayer=false;
        this.player = player;
        this.playerScore = "" + playerScore;
        EndGame(playerScore);
    }

    public EndState(GameStateManager gsm, int player, int playerScore,int player1Score, int player2Score, int player3Score, int player4Score)
    {
        super(gsm);
        testing =super.getGsm().testing;

        isMultiplayer=true;
        this.player=player;
        this.playerScore=""+playerScore;
        String[] scores = new String[4];

        scores[0]=""+player1Score;
        scores[1]=""+player2Score;
        scores[2]=""+player3Score;
        scores[3]=""+player4Score;

        this.scores=scores;
        EndGame(playerScore);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            getGsm().push(new ScoreState(getGsm(),scores));
            //getGsm().set(new MenuState(getGsm()));
        }

    }

    public void EndGame(int playerScore)
    {


        if(player == 1)
        {
            try {
                testing.addRecords(testing.getfName(),testing.getsName(),testing.getEv1(),testing.getEv2(),testing.getEv3(),playerScore,testing.getfName());
            } catch (IOException e) {
                e.printStackTrace();
            }

            yourScoreName = "White Wins!";
            winState = new Texture("WinMenuWhite.png");
        }
        else if(player==2)
        {
            try {
                testing.addRecords(testing.getfName(),testing.getsName(),testing.getEv1(),testing.getEv2(),testing.getEv3(),playerScore,testing.getfName());
            } catch (IOException e) {
                e.printStackTrace();
            }

            yourScoreName = "Black Wins!";
            winState = new Texture("WinMenuBlack.png");
        }
        else if(player ==3)
        {
            //Not Working For Multiplayer testing yet
            try {
                testing.addRecords(testing.getfName(),testing.getsName(),testing.getEv1(),testing.getEv2(),testing.getEv3(),playerScore,testing.getfName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            yourScoreName = "Red Wins!";
            winState = new Texture("WinMenuOrange.png");
        }
        else if(player ==4)
        {
            //Not Working For Multiplayer testing yet
            try {
                testing.addRecords(testing.getfName(),testing.getsName(),testing.getEv1(),testing.getEv2(),testing.getEv3(),playerScore,testing.getfName());
            } catch (IOException e) {
                e.printStackTrace();
            }
            yourScoreName = "Purple Wins!";
            winState = new Texture("WinMenuPurple.png");
        }
        else{

            try {
                testing.addRecords(testing.getfName(),testing.getsName(),testing.getEv1(),testing.getEv2(),testing.getEv3(),playerScore,"Tie");
            } catch (IOException e) {
                e.printStackTrace();
            }

            winState=new Texture("TieMenu.png");
            isTie=true;
        }

        yourBitmapFontName = new BitmapFont();
        yourBitmapFontName.getData().scale(2);
    }


    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        if(isTie)
        {
            sb.draw(winState,0,0,800,910);
        }
        else
        {
            sb.draw(winState, 0, 0, 800, 910);
            yourBitmapFontName.setColor(0.0f, 0.0f, 0.0f, 1.0f);
            yourBitmapFontName.draw(sb, playerScore, 500, 660);
        }
        sb.end();
    }
}
