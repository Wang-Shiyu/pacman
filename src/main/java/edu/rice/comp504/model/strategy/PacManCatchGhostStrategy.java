package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.AMoveObject;

public class PacManCatchGhostStrategy implements IInteractStrategy {

    @Override
    public String getName() {
        return "GhostCatchPacManStrategy";
    }

    @Override
    public void interact(AMoveObject one, AMoveObject other) {
        // TODO: reset ghost into jail
    }
}
