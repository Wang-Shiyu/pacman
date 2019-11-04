package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;

public class GhostReturnStrategy implements IUpdateStrategy {
    @Override
    public String getName() {
        return "GhostReturn";
    }

    @Override
    public void updateState(ACellObject context) {

    }
}
