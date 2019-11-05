package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.PacMan;

import java.awt.*;

public class AvoidStrategy implements IUpdateStrategy {

    /**
     * Singleton object of AvoidStrategy class.
     */
    private static AvoidStrategy INSTANCE;

    private PacMan pacman;

    private ACellObject[][] board;

    /**
     * Constructor.
     */
    private AvoidStrategy(PacMan pacMan, ACellObject[][] board) {
        this.pacman = pacMan;
        this.board = board;
    }

    public static void cleanStrategy() {
        INSTANCE = null;
    }

    /**
     * @return get the singleton of ChaseStrategy class.
     */
    public static AvoidStrategy getInstance(PacMan pacman, ACellObject[][] board) {
        if (INSTANCE == null) {
            INSTANCE = new AvoidStrategy(pacman, board);
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "AvoidStrategy";
    }

    @Override
    public void updateState(ACellObject context) {
        if (context instanceof Ghost) {
            Ghost ghost = (Ghost) context;
            ACellObject.Direction direction = convertDirection(ghost, pacman);
            ghost.setLastMove(ghost.getCurrentMove());
            ghost.setNextMove(direction);
            ghost.setCurrentMove(ghost.getNextMove());
            ghost.computeNextLocation();
        }
    }

    private ACellObject.Direction convertDirection(Ghost ghost, PacMan pacMan) {
        int ghostCol = (int) Math.round(ghost.getLocationX() / 31);
        int ghostRow = (int) Math.round(ghost.getLocationY() / 31);
        int pacmanCol = (int) Math.round(pacMan.getLocationX() / 31);
        int pacmanRow = (int) Math.round(pacMan.getLocationY() / 31);

        if (pacmanCol == ghostCol || pacmanRow == ghostRow) {
            switch (ghost.getCurrentMove()) {
                case UP:
                    return ACellObject.Direction.DOWN;
                case DOWN:
                    return ACellObject.Direction.UP;
                case LEFT:
                    return ACellObject.Direction.RIGHT;
                case RIGHT:
                    return ACellObject.Direction.LEFT;
            }
        }

        return ACellObject.Direction.STOP;
    }

}
