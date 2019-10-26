package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.NullInteractStrategy;

import java.awt.*;

/**
 * Empty unit in the game board
 */
public class NullUnit extends APaintObject {

    /**
     * Constructor.
     *
     * @param loc              The location of the paintable in the grid(row,col)
     * @param type             The object type (e.g. image, circle)
     * @param interactStrategy The object interact strategy
     */
    public NullUnit(Point loc, String type, IInteractStrategy interactStrategy) {
        super(loc, "Null", NullInteractStrategy.getInstance());
    }

    @Override
    public boolean detectCollision(AMoveObject obj) {
        return false;
    }
}
