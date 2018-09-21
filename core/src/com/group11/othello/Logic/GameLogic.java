package com.group11.othello.Logic;

public class GameLogic {
    private int playerCount;
    private Player1 player1;
    private Player2 player2;
    private Bag bag;




    public GameLogic(Player1 player1)
    {
        this.player1 = new Player1("white");
        bag = new Bag(64);
    }

    public GameLogic(Player1 player1,Player2 player2)
    {
        this.player1 = new Player1("white");
        this.player2 = new Player2("black");
        bag = new Bag(64);
    }


    public boolean endGame()
    {
        boolean end = false;

        if(bag.getChipCount() < 1 )
        {
            end = true;
        }

        return end;

    }

}
