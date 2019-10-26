package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.AMoveObject;

public class EscapeStrategy implements IUpdateStrategy {

    /**
     * Singleton object of current class.
     */
    private static EscapeStrategy INSTANCE;

    /**
     * Constructor.
     */
    private EscapeStrategy() {
    }

    /**
     * @return get the singleton of the class.
     */
    public static EscapeStrategy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EscapeStrategy();
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "EscapeStrategy";
    }

    @Override
    public void updateState(AMoveObject context) {
        // TODO: ghost escape away from pac man when it eats big bean
    }
}
