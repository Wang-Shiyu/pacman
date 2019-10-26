package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.AMoveObject;
import edu.rice.comp504.model.paint.APaintObject;

public interface IInteractStrategy {

    /**
     * Get the name of the strategy.
     *
     * @return The strategy name.
     */
    public String getName();

    /**
     * Update the state the paint object.
     *
     * @param one   The paint object to apply the strategy to.
     * @param other The paint object to apply the strategy to.
     */
    public void interact(AMoveObject one, AMoveObject other);
}
