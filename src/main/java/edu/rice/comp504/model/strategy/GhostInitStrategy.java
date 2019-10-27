package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;

public class GhostInitStrategy implements IUpdateStrategy {

    private int switchLocationY;
    /**
     * The name of the strategy.
     *
     * @return strategy name
     */
    @Override
    public String getName() {
        return "GhostInit";
    }

    /**
     * Update the state of the paint object.
     *
     * @param context The paint object.
     */
    @Override
    public void updateState(ACellObject context) {
        // TODO: check time, if(TRUE) move up.
        // TODO: check location Y, switch to normal strategy
    }
}
