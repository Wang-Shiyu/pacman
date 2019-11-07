package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.DoorUnit;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.WallUnit;

import java.awt.*;
import java.util.Deque;

/**
 * ghost random.
 */
public class RandomStrategy implements IUpdateStrategy {
    /**
     * Singleton object of RandomStrategy class.
     */
    private static RandomStrategy INSTANCE;

    private static int[] offsetX = new int[] {0, 1, 0, -1};
    private static int[] offsetY = new int[] {1, 0, -1, 0};
    private ACellObject[][] board;

    /**
     * Constructor.
     */
    private RandomStrategy(ACellObject[][] board) {
        this.board = board;
    }

    public static void cleanStrategy() {
        INSTANCE = null;
    }

    /**
     * @return get the singleton of ChaseStrategy class.
     */
    public static RandomStrategy getInstance(ACellObject[][] board) {
        if (INSTANCE == null) {
            INSTANCE = new RandomStrategy(board);
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "RandomStrategy";
    }

    @Override
    public void updateState(ACellObject context) {
        if (context instanceof Ghost) {
            Ghost ghost = (Ghost) context;
            int ghostCol = (int) Math.round(ghost.getLocationX() / 31);
            int ghostRow = (int) Math.round(ghost.getLocationY() / 31);
            Point lastPoint = new Point(ghostCol, ghostRow);
            int cross = 4;
            for (int i = 0; i < 4; i++) {
                Point newLoc = new Point(lastPoint.x + offsetX[i], lastPoint.y + offsetY[i]);
                if (board[newLoc.y][newLoc.x] instanceof WallUnit || board[newLoc.y][newLoc.x] instanceof DoorUnit) {
                    cross--;
                }
            }
            if (ghost.getCurrentMove() == ACellObject.Direction.STOP || cross > 2 ) {
                ACellObject.Direction direction = randomDirection();
                ghost.setLastMove(ghost.getCurrentMove());
                ghost.setNextMove(direction);
                ghost.setCurrentMove(ghost.getNextMove());
                ghost.computeNextLocation();

            } else {
                ghost.computeNextLocation();
            }
        }
    }

    /**
     * Calculate a direction.
     * @return direction.
     */
    private ACellObject.Direction randomDirection() {
        int choice = getRnd(1,4);
        switch (choice) {
            case 1:
                return ACellObject.Direction.LEFT;
            case 2:
                return ACellObject.Direction.RIGHT;
            case 3:
                return ACellObject.Direction.UP;
            case 4:
                return ACellObject.Direction.DOWN;
        }
        return ACellObject.Direction.STOP;
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
