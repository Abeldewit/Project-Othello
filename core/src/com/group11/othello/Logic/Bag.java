package com.group11.othello.Logic;

public class Bag {
    private int chipCount;

    public Bag()
    {
        chipCount = 64;
    }

    public int getChipCount()
    {
        return chipCount;
    }

    public void removeChip()
    {
        chipCount--;
    }

}
