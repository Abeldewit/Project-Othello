package com.group11.othello.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.AI.Testing;
import com.group11.othello.Logic.Player;
import com.badlogic.gdx.graphics.Texture;

import java.io.IOException;

public class EndState extends State {

   private int player;
   private String playerScore;
    private String yourScoreName;
    private Texture winState;
    BitmapFont yourBitmapFontName;
    boolean isTie=false;
    Testing testing;
    public EndState(GameStateManager gsm , int player, int playerScore) {
        super(gsm);
        testing = super.getGsm().testing;


        this.player = player;
        this.playerScore = "" + playerScore;
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
            yourScoreName = "Orange Wins!";
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
    public void handleInput() {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            for(int i =0 ; i< getGsm().states.size();i++)
            {
                getGsm().pop();
            }
            //getGsm().set(new MenuState(getGsm()));
        }
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
