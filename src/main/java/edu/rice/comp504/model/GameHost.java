package edu.rice.comp504.model;


import gameParam.GameParam;

public class GameHost {
    int level;
    int gameStatus;
    int timeCounter;
    int score;
    private static final int START = 0;
    //And so on

    /**
     * Reset the game when the game starts from fresh or reset from Game Over status
     */
    public void resetGame() {
        this.level = 1;
        this.timeCounter = 0;
        //And so on
    }

    /**
     * Initialize level related game variables
     */
    public void levelInit() {
        switch (level) {
            case 1:
                GameParam.ghostSpeed = 0.03;
                break;
            case 2:
                break;
                //And so on
        }
    }

    /**
     *
     */




}
