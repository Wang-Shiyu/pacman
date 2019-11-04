package edu.rice.comp504.model;


import edu.rice.comp504.model.cmd.KeyboardInputCmd;
import edu.rice.comp504.model.cmd.UpdateCmd;
import edu.rice.comp504.model.paint.*;
import edu.rice.comp504.model.strategy.GhostInitStrategy;
import edu.rice.comp504.model.strategy.PacManMoveStrategy;
import gameparam.GameParam;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
     *
     * @return returnType
     */
    public ReturnType updatePanManWorld() {
        if (gameStatus == Status.START) {
            // TODO: send update cmd
            UpdateCmd.getInstance().setPcs(pcs);
            pcs.firePropertyChange("pacman", null, UpdateCmd.getInstance());
//        pcs.firePropertyChange("ghost", null, UpdateCmd.getInstance());

            // TODO: send interact cmd
//        InteractCmd.getInstance().setPcs(pcs);
//        pcs.firePropertyChange("pacman", null, InteractCmd.getInstance());
//        pcs.firePropertyChange("ghost", null, InteractCmd.getInstance());

            // TODO: check life. set OVER
            // TODO: check dots, set pass
        }
        return new ReturnType(0, gameStatus, 3);
    }

    /**
     * handle user input about changing direction.
     */
    public void move(String direction) {
        // TODO: Send a KeyboardInputCmd to Pacman when keyboard evt is triggered
        if(gameStatus != Status.START) return;
        String dir = direction.split("=")[1];
        ACellObject.Direction d = ACellObject.Direction.STOP;
        if ("up".equals(dir)) {
            d = ACellObject.Direction.UP;
        } else if ("left".equals(dir)) {
            d = ACellObject.Direction.LEFT;
        } else if ("right".equals(dir)) {
            d = ACellObject.Direction.RIGHT;
        } else if ("down".equals(dir)) {
            d = ACellObject.Direction.DOWN;
        }
        KeyboardInputCmd.getInstance().setMove(d);
        pcs.firePropertyChange("pacman", null, KeyboardInputCmd.getInstance());
    }

    /**
     * Start Game.
     */
    public void startGame() {
        // TODO: check previous status
        if (gameStatus == Status.INIT) {
            // first time
            gameStatus = Status.START;
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
                        board[col][row] = new WallUnit("", 0, null, col, row, 0, null);
                        pcs.addPropertyChangeListener("Wall", board[col][row]);
                    } else if (c == '0') {
                        board[col][row] = new Food("", 0, null, col, row, 0, null);
                        pcs.addPropertyChangeListener("Food", board[col][row]);
                    } else if (c == '2') {
                        board[col][row] = new DoorUnit("", 0, null, col, row, 0, null);
                        pcs.addPropertyChangeListener("Door", board[col][row]);
                    } else if (c == '9') {
                        board[col][row] = new NullUnit("", 0, null, col, row, 0, null);
                        pcs.addPropertyChangeListener("Null", board[col][row]);
                    }
                    col++;
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // init pacman and ghosts
        initPacMan();
        initGhosts();
        return getPropertyChangeListenerList();
    }

    private void initGhosts() {
        for (int i = 0; i < 5; i++) {
            Ghost ghost = new Ghost("", 200, null,
                    GameParam.GHOST_INIT_X[i], GameParam.GHOST_INIT_Y, GameParam.ghostSpeed,
                    new GhostInitStrategy(), GameParam.GHOST_RELEASE_TIME[i]);
            pcs.addPropertyChangeListener("ghost", ghost);
        }
    }

    private void initPacMan() {
        PacMan pacMan = new PacMan("", 0, null,
                GameParam.PACMAN_INIT_X, GameParam.PACMAN_INIT_Y,
                GameParam.pacmanSpeed, PacManMoveStrategy.getInstance());
        pcs.addPropertyChangeListener("pacman", pacMan);
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
     *
     * @return gameStatus.
     */
    public Status getGameStatus() {
        return gameStatus;
    }

    private PropertyChangeListener[] getPropertyChangeListenerList() {
        List<PropertyChangeListener> list = new ArrayList<>();
        Collections.addAll(list, pcs.getPropertyChangeListeners("Wall"));
        Collections.addAll(list, pcs.getPropertyChangeListeners("Food"));
        Collections.addAll(list, pcs.getPropertyChangeListeners("Door"));
        Collections.addAll(list, pcs.getPropertyChangeListeners("Null"));
        Collections.addAll(list, pcs.getPropertyChangeListeners("pacman"));
        Collections.addAll(list, pcs.getPropertyChangeListeners("ghost"));
        return list.toArray(new PropertyChangeListener[0]);
    }

    class ReturnType {
        private PropertyChangeListener[] list;
        private int score;
        private Status status;
        // pacman.getRemainingLife()
        private int remainingLife;

        public ReturnType(int score, Status status, int remainingLife) {
            this.list = getPropertyChangeListenerList();
            this.score = score;
            this.status = status;
            this.remainingLife = remainingLife;
        }
    }
}
