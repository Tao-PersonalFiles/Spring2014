package com.redcarddev.kickshot_real.utils;

import com.redcarddev.kickshot_real.LevelOne;

import java.io.Serializable;

/**
 * Created by otternq on 10/28/13.
 */
public class LevelOneState implements Serializable {

    public int getCurrentState() {

        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getCurrentBallPosition() {
        return currentBallPosition;
    }

    public void setCurrentBallPosition(int currentBallPosition) {
        this.currentBallPosition = currentBallPosition;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public void increaseComputerScore() {
        this.computerScore++;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void increasePlayerScore() {
        this.playerScore++;
    }

    private int computerScore = 0;
    private int playerScore = 0;
    private int currentState = LevelOne.OFFENSE_STATE;
    private int currentBallPosition = 0;



}
