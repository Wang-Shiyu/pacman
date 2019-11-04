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
        // TODO: update pacman/ghosts location
        /*
        Try to move the moving Pacman or Ghost. Revert the move later if it is not valid.
        Backup the current move and location to enable revert.
         */
        context.setLastMove(context.getCurrentMove());
        context.setCurrentMove(context.getNextMove());
        context.computeNextLocation(); // This method also store the last valid location before the move.

        // TODO: update time in ghost

        // TODO: make sure new location is valid
    }
}
