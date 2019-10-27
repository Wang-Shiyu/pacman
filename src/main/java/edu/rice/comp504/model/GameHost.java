package edu.rice.comp504.model;


import edu.rice.comp504.model.cmd.InteractCmd;
import edu.rice.comp504.model.cmd.UpdateCmd;
import gameParam.GameParam;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameHost {
    private PropertyChangeSupport pcs;

    enum Status {
        UNKNOWN, PASS, OVER, INIT, START, WIN;
    };

    int level;
    Status gameStatus;
    int timeCounter;
    int score;
    //And so on

    /**
     * Constructor.
     */
    public GameHost() {
        pcs = new PropertyChangeSupport(this);
        gameStatus = Status.INIT;
    }

    /**
     * Reset the game when the game starts from fresh or reset from Game Over status
     */
    public void resetGame() {
        this.level = 1;
        this.timeCounter = 0;
        loadGameObject();
    }

    public ReturnType updatePanManWorld() {
        // TODO: send update cmd
        UpdateCmd.getInstance().setPcs(pcs);

        // TODO: send interact cmd
        InteractCmd.getInstance().setPcs(pcs);

        // TODO: check life. set OVER
        // TODO: check dots, set pass
        return null;
    }

    /**
     * handle user input about changing direction.
     */
    public void move(String direction) {
        // TODO: Send a KeyboardInputCmd to Pacman when keyboard evt is triggered
    }

    public void startGame() {
        // TODO: check previous status
        if(gameStatus == Status.INIT) {
            // first time
        } else if(gameStatus == Status.PASS) {
            // next level
//            levelInit();
        } else if(gameStatus == Status.OVER) {
            // reset
            // new game
        }
        loadGameObject();
        gameStatus = Status.START;
    }

    private void loadGameObject () {
        // clear all listener and reload
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

    public Status getGameStatus() {
        return gameStatus;
    }

    class ReturnType {
        private PropertyChangeSupport pcs;
        private int score;
        private Status status;
        // pacman.getRemainingLife()
        private int remainingLife;

        public ReturnType(PropertyChangeSupport pcs, int score, Status status, int remainingLife) {
            this.pcs = pcs;
            this.score = score;
            this.status = status;
            this.remainingLife = remainingLife;
        }
    }
}
