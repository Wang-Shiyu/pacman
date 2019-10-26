package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.AMoveObject;

public class ChaseStrategy implements IUpdateStrategy {

    /**
     * Singleton object of current class.
     */
    private static ChaseStrategy INSTANCE;

    /**
     * Constructor.
     */
    private ChaseStrategy() {
    }

    /**
     * @return get the singleton of the class.
     */
    public static ChaseStrategy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChaseStrategy();
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "ChaseStrategy";
    }

    @Override
    public void updateState(AMoveObject context) {
        // TODO: ghost chase the Pac man
    }
}
