package com.group11.othello.AI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.group11.othello.Logic.Board;
import com.group11.othello.Logic.GameLogic;

public class EvaluationFunction {

    //All penalties and rewards for the heuristics
    final int discMin = 40;
    final int discPenalty = 10;

    final int mobilityReward = 100;

    final int cornerReward = 100000;
    final int cornerPenalty = 800;

    final double randomizer = 0.2;
    //-------------------------------------------

    GameLogic gameLogic;
    int amountOfEval=0;
    public boolean isEv1=true,isEvCorn=true,isEvMob=true;
    public boolean isRandom=true;
    public double score;

    public EvaluationFunction() {

    }

    public double bigEvaluation(GameLogic gL, int player) {
        //reset score for new calculation
        score = 0;

        //Scoring based on chip amount
        if(isEv1) {
            discMin(gL, player);
        }

        //Scoring based on corners
        if(isEvCorn) {
            evaluateCorners(gL, player);
            cornerPenalty(gL, player);
        }

        //Scoring based on amount of moves
        if(isEvMob) {
            evaluateMobility(gL, player);
        }

        if(isRandom) {
            randomFactor();
        }

        //System.out.println("Score for player " + player + " evaluated at " + score);
        return score;
    }

    public void discMin(GameLogic gL, int player) {
        if (gL.getScore()[0].x + gL.getScore()[0].y < discMin) {
            if (player == 1) {
                score -= discPenalty * gL.getScore()[0].x;
            } else if (player == 2) {
                score -= discPenalty * gL.getScore()[0].y;
            }
        } else {
            if (player == 1) {
                score += gL.getScore()[0].x;
            } else if (player == 2) {
                score += gL.getScore()[0].y;
            }
        }
    }

    public void evaluateMobility(GameLogic gL, int player) {
        int MHV;

        int allMoves = gL.getValidMoves().size();
        int playerMoves = gL.getValidMoves(player).size();

        if(allMoves + playerMoves != 0) {
            MHV = mobilityReward * (allMoves - playerMoves) / (allMoves + playerMoves);
        } else {
            MHV = 0;
        }

        //System.out.println("Evaluation of mobility: " + MHV);
        score += MHV;
    }

    public void evaluateCorners(GameLogic gL, int player){
        int cornerCount = 0;

        Board currentBoard = gL.getBoard();

        //These two for loops will check (0,0) (0,7) (7,0) (7,7)
        for(int x = 0; x < 8; x += 7) {
            for(int y = 0; y < 8; y += 7) {
                if(currentBoard.getChip(x,y) == player){
                    cornerCount++;
                }
            }
        }

        //System.out.println("Corner eval, white: " + whiteCorner * 100 + " black: " + blackCorner * 100);
        score += (cornerReward * cornerCount);
    }

    public void cornerPenalty(GameLogic gL, int player) {
        Board currentBoard = gL.getBoard();
        final int penalty = cornerPenalty;
        int playerPenalty = 0;

        for(int i = 1; i < 13; i++) {

            switch (i) {
                //Upper left corner
                case 1:
                    if(currentBoard.getChip(0,1) == player && currentBoard.getChip(0,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 2:
                    if(currentBoard.getChip(1,0) == player && currentBoard.getChip(0,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 3:
                    if(currentBoard.getChip(1,1) == player && currentBoard.getChip(0,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                //Lower left corner
                case 4:
                    if(currentBoard.getChip(0,6) == player && currentBoard.getChip(0,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 5:
                    if(currentBoard.getChip(1,7) == player && currentBoard.getChip(0,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 6:
                    if(currentBoard.getChip(1,6) == player && currentBoard.getChip(0,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                //Upper right corner
                case 7:
                    if(currentBoard.getChip(6,0) == player && currentBoard.getChip(7,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 8:
                    if(currentBoard.getChip(7,1) == player && currentBoard.getChip(7,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 9:
                    if(currentBoard.getChip(6,1) == player && currentBoard.getChip(7,0) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                //Lower right corner
                case 10:
                    if(currentBoard.getChip(7,6) == player && currentBoard.getChip(7,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 11:
                    if(currentBoard.getChip(6,7) == player && currentBoard.getChip(7,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
                case 12:
                    if(currentBoard.getChip(6,6) == player && currentBoard.getChip(7,7) != player) {
                        playerPenalty -= penalty;
                    }
                    break;
            }
        }
        score += playerPenalty;
    }

    public void randomFactor() {
        double scoreFraction = Math.floor((score * randomizer) * Math.random());
        score += scoreFraction;
    }
}