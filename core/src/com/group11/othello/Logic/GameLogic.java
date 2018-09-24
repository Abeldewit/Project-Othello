package com.group11.othello.Logic;

public class GameLogic {
    private int playerCount;
    private int turnStatus;
    private Player1 player1;
    private Player2 player2;
    private Bag bag;




    public GameLogic(Player1 player1)
    {
        this.player1 = new Player1();
        bag = new Bag();
    }

    public GameLogic(Player1 player1,Player2 player2)
    {
        this.player1 = new Player1();
        this.player2 = new Player2();
        bag = new Bag();
    }

    public int getTurnStatus()
    {
        return playerCount;
    }


    public boolean endGame()
    {
        boolean end = false;

        if(bag.getChipCount() < 0 )
        {
            end = true;
        }

        return end;

    }

    public Chip createChip()
    {
            if(turnStatus > 0)
            {
                changeTurn();
                return new Chip(player2);
            }
            else
                {
                    changeTurn();
                    return new Chip(player1);
                }
    }

    public void changeTurn()
    {
        if(turnStatus > 0)
        {
            turnStatus--;
        }
        else
            {
                turnStatus++;
            }


    }






}
