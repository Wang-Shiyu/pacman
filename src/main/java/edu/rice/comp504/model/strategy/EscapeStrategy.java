package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.PacMan;

public class EscapeStrategy implements IUpdateStrategy {

    /**
     * Singleton object of current class.
     */
    private static EscapeStrategy INSTANCE;

    private PacMan pacman;

    /**
     * Constructor.
     */
    private EscapeStrategy(PacMan pacman) {
        this.pacman = pacman;
    }

    /**
     * @return get the singleton of the class.
     */
    public static EscapeStrategy getInstance(PacMan pacman) {
        if (INSTANCE == null) {
            INSTANCE = new EscapeStrategy(pacman);
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "EscapeStrategy";
    }

    @Override
    public void updateState(ACellObject context) {
        // TODO: ghost escape away from pac man when it eats big bean
    }
}
