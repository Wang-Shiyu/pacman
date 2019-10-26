package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.AMoveObject;

/**
 * An interface for paint object strategies that determine the paint object behavior.
 */
public interface IUpdateStrategy {
    /**
     * The name of the strategy.
     * @return strategy name
     */
    String getName();

    /**
     * Update the state of the paint object.
     * @param context The paint object.
     */
    void updateState(AMoveObject context);
}
