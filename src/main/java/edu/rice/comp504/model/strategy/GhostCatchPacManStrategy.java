package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.AMoveObject;

/**
 * The strategy when ghost catches the pac man
 */
public class GhostCatchPacManStrategy implements IInteractStrategy {
    @Override
    public String getName() {
        return "GhostCatchPacManStrategy";
    }

    @Override
    public void interact(AMoveObject one, AMoveObject other) {
        // TODO: reset pac man
    }
}
