package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;

public class PacManMoveStrategy implements IUpdateStrategy {

    /**
     * Singleton object of PacManMoveStrategy class.
     */
    private static PacManMoveStrategy INSTANCE;

    /**
     * Constructor.
     */
    private PacManMoveStrategy() {
    }

    /**
     * @return get the singleton of PacManMoveStrategy class.
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
    public void updateState(ACellObject context) {
        // TODO: pac man update location
    }
}
