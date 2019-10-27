package edu.rice.comp504.model.strategy;

public class NullInteractStrategy implements  IInteractStrategy {

    /**
     * Singleton object of current class.
     */
    private static NullInteractStrategy INSTANCE;

    /**
     * Constructor.
     */
    private NullInteractStrategy() {
    }

    /**
     * @return get the singleton of the class.
     */
    public static NullInteractStrategy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NullInteractStrategy();
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "NullInteractStrategy";
    }

    @Override
    public void interact(AMoveObject one, AMoveObject other) {

    }
}
