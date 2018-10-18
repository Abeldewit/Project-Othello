package com.group11.othello.States;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group11.othello.Logic.Player;

public class EndState extends State {

   private int player;
   private String playerScore;
    private String yourScoreName;
    BitmapFont yourBitmapFontName;

    public EndState(GameStateManager gsm , int player, int playerScore)
    {
        super(gsm);
        this.player = player;
        this.playerScore = "Score = " + playerScore;
        if(player == 1)
        {
            yourScoreName = "White Wins!";
        }
        else
            {
                yourScoreName = "Black Wins!";
            }

        yourBitmapFontName = new BitmapFont();
        yourBitmapFontName.getData().scale(2);

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
       // yourBitmapFontName.setColor(0.0f, 0.0f, 0.0f, 0.0f);
        yourBitmapFontName.draw(sb, yourScoreName, 250, 550);
        yourBitmapFontName.draw(sb, playerScore, 250, 350);
        sb.end();
    }
}
