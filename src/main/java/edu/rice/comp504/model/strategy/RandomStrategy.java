package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Ghost;

public class RandomStrategy implements IUpdateStrategy {
    /**
     * Singleton object of RandomStrategy class.
     */
    private static RandomStrategy INSTANCE;

    /**
     * Constructor.
     */
    private RandomStrategy() {
    }

    public static void cleanStrategy() {
        INSTANCE = null;
    }

    /**
     * @return get the singleton of ChaseStrategy class.
     */
    public static RandomStrategy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RandomStrategy();
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
            ACellObject.Direction direction = randomDirection();
            ghost.setLastMove(ghost.getCurrentMove());
            ghost.setNextMove(direction);
            ghost.setCurrentMove(ghost.getNextMove());
            ghost.computeNextLocation();
        }
    }
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
