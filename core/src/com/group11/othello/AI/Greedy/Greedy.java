package com.group11.othello.AI.Greedy;

import com.group11.othello.Logic.GameLogic;

import java.util.ArrayList;

public class Greedy {

    public static void main(String[] args){

        GameLogic logic = new GameLogic();
        ArrayList a = logic.checkChipFlip(1);
        System.out.println(a);
    }
}
