package com.group11.othello.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.Logic.Player;
import com.badlogic.gdx.graphics.Texture;
public class EndState extends State {

   private int player;
   private String playerScore;
    private String yourScoreName;
    private Texture winState;
    BitmapFont yourBitmapFontName;
    boolean isTie=false;

    public EndState(GameStateManager gsm , int player, int playerScore)
    {
        super(gsm);
        this.player = player;
        this.playerScore = "" + playerScore;
        if(player == 1)
        {
            yourScoreName = "White Wins!";
            winState = new Texture("WinMenuWhite.png");
        }
        else if(player==2)
            {
                yourScoreName = "Black Wins!";
                winState = new Texture("WinMenuBlack.png");
            }
            else{
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
            getGsm().set(new MenuState(getGsm()));
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
