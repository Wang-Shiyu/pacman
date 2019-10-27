package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.PacMan;

public class ChaseStrategy implements IUpdateStrategy {

    /**
     * Singleton object of current class.
     */
    private static ChaseStrategy INSTANCE;

    private PacMan pacman;

    /**
     * Constructor.
     */
    private ChaseStrategy(PacMan pacMan) {
        this.pacman = pacMan;
    }

    /**
     * @return get the singleton of the class.
     */
    public static ChaseStrategy getInstance(PacMan pacman) {
        if (INSTANCE == null) {
            INSTANCE = new ChaseStrategy(pacman);
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "ChaseStrategy";
    }

    @Override
    public void updateState(ACellObject context) {
        // TODO: ghost chase the Pac man
    }
}
