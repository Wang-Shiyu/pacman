package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.PacMan;
import gameparam.TimeCounter;

public class GhostInitStrategy implements IUpdateStrategy {

    private int switchLocationY;
    private PacMan pacMan;
    private ACellObject[][] board;

    /**
     * The name of GhostInitStrategy strategy.
     *
     * @return strategy name
     */
    @Override
    public String getName() {
        return "GhostInit";
    }

    /**
     * Constructor.
     * @param locationY door location.
     * @param pacMan pacman.
     * @param board game board.
     */
    public GhostInitStrategy(int locationY, PacMan pacMan, ACellObject[][] board) {
        this.switchLocationY = locationY;
        this.pacMan = pacMan;
        this.board = board;
    }

    /**
     * Update the state of the paint object.
     *
     * @param context The paint object.
     */
    @Override
    public void updateState(ACellObject context) {
        // Compare the time to the preset release time
        Ghost ghost = (Ghost) context;
        if (TimeCounter.getTime() >= ghost.getReleaseTime()) {
            ghost.setCanCollideDoor(true);
        }
        if (ghost.isCanCollideDoor()) {
            ghost.setCurrentMove(ACellObject.Direction.UP);
            ghost.computeNextLocation();
        }
        // Check if the ghost has crossed the door. If so, switch to ChaseStrategy
        if (ghost.getLocationY() < switchLocationY) {
            ghost.setUpdateStrategy(RandomStrategy.getInstance(board));
            ghost.setCanCollideDoor(false);
            ghost.setCurrentMove(ACellObject.Direction.LEFT);
        }
    }
}
