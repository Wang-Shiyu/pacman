package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.PacMan;
import gameparam.TimeCounter;

public class GhostInitStrategy implements IUpdateStrategy {

    private int switchLocationY;
    private PacMan pacMan;

    /**
     * The name of GhostInitStrategy strategy.
     *
     * @return strategy name
     */
    @Override
    public String getName() {
        return "GhostInit";
    }

    public GhostInitStrategy(int locationY, PacMan pacMan) {
        this.switchLocationY = locationY;
        this.pacMan = pacMan;
    }

    /**
     * Update the state of the paint object.
     *
     * @param context The paint object.
     */
    @Override
    public void updateState(ACellObject context) {
        // TODO: check time, if(TRUE) move up.
        Ghost ghost = (Ghost) context;
        if (TimeCounter.time >= ghost.getReleaseTime()) {
            ghost.setCanCollideDoor(true);
        }
        if (ghost.isCanCollideDoor()) {
            ghost.setCurrentMove(ACellObject.Direction.UP);
            ghost.computeNextLocation();
        }
        // TODO: check location Y, switch to normal strategy
        if (ghost.getLocationY() < switchLocationY) {
            ghost.setUpdateStrategy(ChaseStrategy.getInstance(pacMan));
        }
    }
}
