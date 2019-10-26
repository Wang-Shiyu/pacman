package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.NullInteractStrategy;

import java.awt.*;

public class Food extends APaintObject {

    /**
     * Constructor.
     *
     * @param loc              The location of the paintable in the grid(row,col)
     * @param type             The object type (e.g. image, circle)
     * @param interactStrategy The object interact strategy
     */
    public Food(Point loc, String type, IInteractStrategy interactStrategy) {
        super(loc, "Food", NullInteractStrategy.getInstance());
    }

    @Override
    public boolean detectCollision(AMoveObject obj) {
        // if true, eat
        return false;
    }
}
