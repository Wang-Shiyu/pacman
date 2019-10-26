package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.AMoveObject;

public class PacManMoveStrategy implements IUpdateStrategy {

    /**
     * Singleton object of current class.
     */
    private static PacManMoveStrategy INSTANCE;

    /**
     * Constructor.
     */
    private PacManMoveStrategy() {
    }

    /**
     * @return get the singleton of the class.
     */
    public static PacManMoveStrategy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PacManMoveStrategy();
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "PacManMoveStrategy";
    }

    @Override
    public void updateState(AMoveObject context) {
        // TODO: pac man update location
        // we also store a cache direction, when pac man can make a turn, update the velocity first then update location
    }
}
