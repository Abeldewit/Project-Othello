package com.group11.othello.Logic;

public class GameLogic {
    private int turnStatus;
    private Player1 player1;
    private Player2 player2;
    private Bag bag;
    private Board board;




    public GameLogic(Player1 player1)
    {
        this.player1 = new Player1();
        bag = new Bag();
        board = new Board();
    }

    public GameLogic(Player1 player1,Player2 player2)
    {
        this.player1 = new Player1();
        this.player2 = new Player2();
        bag = new Bag();
    }

    public int getTurnStatus()
    {
        return turnStatus;
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
            if(turnStatus > 1)
            {
                changeTurn();
                return new Chip(player2, "black");
            }
            else
                {
                    changeTurn();
                    return new Chip(player1, "white");
                }
    }

    public void changeTurn()
    {
        if(turnStatus > 1)
        {
            turnStatus--;
        }
        else
            {
                turnStatus++;
            }


    }

    public void setChip(int i, int j)
    {
        board.setChip(i,j,turnStatus);
    }






}
