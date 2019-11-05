package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.PacMan;

public class EscapeStrategy implements IUpdateStrategy {

    /**
     * Singleton object of EscapeStrategy class.
     */
    private static EscapeStrategy INSTANCE;

    private PacMan pacman;

    private ACellObject[][] board;

    /**
     * Constructor.
     */
    private EscapeStrategy(PacMan pacman, ACellObject[][] board) {
        this.pacman = pacman;
        this.board = board;
    }

    /**
     * @return get the singleton of EscapeStrategy class.
     */
    public static EscapeStrategy getInstance(PacMan pacman, ACellObject[][] board) {
        if (INSTANCE == null) {
            INSTANCE = new EscapeStrategy(pacman, board);
        }
        return INSTANCE;
    }

    public static void cleanStrategy() {
        INSTANCE = null;
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
