package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.PacMan;

/**
 * SawAndChaseStrategy.
 */
public class SawAndChaseStrategy implements IUpdateStrategy {

    /**
     * Singleton object of AvoidStrategy class.
     */
    private static SawAndChaseStrategy INSTANCE;

    private PacMan pacman;

    private ACellObject[][] board;

    /**
     * Constructor.
     */
    private SawAndChaseStrategy(PacMan pacMan, ACellObject[][] board) {
        this.pacman = pacMan;
        this.board = board;
    }

    public static void cleanStrategy() {
        INSTANCE = null;
    }

    /**
     * @return get the singleton of ChaseStrategy class.
     */
    public static SawAndChaseStrategy getInstance(PacMan pacman, ACellObject[][] board) {
        if (INSTANCE == null) {
            INSTANCE = new SawAndChaseStrategy(pacman, board);
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

    /**
     * Calculate a direction when pacman's col or row = ghosts'.
     * @return direction.
     */
    private ACellObject.Direction convertDirection(Ghost ghost, PacMan pacMan) {
        int ghostCol = (int) Math.round(ghost.getLocationX() / 31);
        int ghostRow = (int) Math.round(ghost.getLocationY() / 31);
        int pacmanCol = (int) Math.round(pacMan.getLocationX() / 31);
        int pacmanRow = (int) Math.round(pacMan.getLocationY() / 31);

        if (pacmanCol == ghostCol) {
            if (pacmanRow > ghostRow) {
                return ACellObject.Direction.DOWN;
            } else {
                return ACellObject.Direction.UP;
            }
        } else if (pacmanRow == ghostRow) {
            if (pacmanCol > ghostCol) {
                return ACellObject.Direction.RIGHT;
            } else {
                return ACellObject.Direction.LEFT;
            }
        }
        if (ghost.getCurrentMove() == ACellObject.Direction.STOP) {
            switch (getRnd(1, 4)) {
                case 1:
                    return ACellObject.Direction.LEFT;
                case 3:
                    return ACellObject.Direction.UP;
                case 4:
                    return ACellObject.Direction.DOWN;
                default:
                    return ACellObject.Direction.RIGHT;
            }
        } else {
            return ghost.getCurrentMove();
        }
    }
    /**
     * Generate a random number.
     * @param base  The mininum value
     * @param limit The maximum number from the base
     * @return A randomly number
     */
    private int getRnd(int base, int limit) {
        return (int)Math.floor(Math.random() * limit + base);
    }

}
