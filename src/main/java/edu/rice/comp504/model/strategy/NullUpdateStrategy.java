package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;

/**
 * for all the stationary paint objects that don't move.
 */
public class NullUpdateStrategy implements IUpdateStrategy {
    @Override
    public String getName() {
        return "NullUpdateStrategy";
    }

    @Override
    public void updateState(ACellObject context) {

    }
}
