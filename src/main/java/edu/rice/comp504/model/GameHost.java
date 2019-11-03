package edu.rice.comp504.model;


import edu.rice.comp504.model.cmd.InteractCmd;
import edu.rice.comp504.model.cmd.UpdateCmd;
import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Food;
import edu.rice.comp504.model.paint.WallUnit;
import gameparam.GameParam;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.URL;

public class GameHost {
    private PropertyChangeSupport pcs;

    enum Status {
        UNKNOWN, PASS, OVER, INIT, START, WIN;
    }

    int level;
    Status gameStatus;
    int timeCounter;
    int score;
    ACellObject[][] board;
    //And so on

    /**
     * Constructor.
     */
    public GameHost() {
        pcs = new PropertyChangeSupport(this);
        gameStatus = Status.INIT;
        board = new ACellObject[25][25];
    }

    /**
     * Reset the game when the game starts from fresh or reset from Game Over status.
     */
    public void resetGame() {
        this.level = 1;
        this.timeCounter = 0;
        loadGameObject();
    }

    /**
     * Update Pacman World.
     * @return returnType
     */
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

    /**
     * Start Game.
     */
    public void startGame() {
        // TODO: check previous status
        if (gameStatus == Status.INIT) {
            // first time
        } else if (gameStatus == Status.PASS) {
            // next level
//            levelInit();
        } else if (gameStatus == Status.OVER) {
            // reset
            // new game
        }
        loadGameObject();
        gameStatus = Status.START;
    }

    /**
     * Init the game board.
     */
    public PropertyChangeListener[] initGmae() {
        // TODO: init all items in the board: wall, food, big food and null
        InputStream file = getFileFromResources("public/maze.txt");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            String line;
            int row = 0;
            int col = 0;
            while ((line = br.readLine()) != null) {
                col = 0;
                for (char c : line.toCharArray()) {
                    if (c == '1') {
                        board[row][col] = new WallUnit("",0, null, row, col, 0, null);
                    } else {
                        board[row][col] = new Food("",0, null, row, col, 0, null);
                    }
                    pcs.addPropertyChangeListener(board[row][col]);
                    col++;
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pcs.getPropertyChangeListeners();
    }

    private InputStream getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    /**
     * Load game Object.
     */
    private void loadGameObject() {
        // clear all listener and reload
    }

    /**
     * Initialize level related game variables.
     */
    public void levelInit() {
        switch (level) {
            case 1:
                GameParam.ghostSpeed = 0.03;
                break;
            case 2:
                break;
                //And so on
            default:
                break;
        }
    }

    /**
     * Getter for game status.
     * @return gameStatus.
     */
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
