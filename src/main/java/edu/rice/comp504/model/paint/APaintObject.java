package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IInteractStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The abstract paint object for all stationary object
 */
public abstract class APaintObject implements PropertyChangeListener{
    // unit-wise
    private Point coordinate;
    private IInteractStrategy interactStrategy;
    private String type;

    /**
     * Constructor.
     *
     * @param loc              The location of the paintable in the grid(row,col)
     * @param type             The object type (e.g. image, circle)
     * @param interactStrategy The object interact strategy
     */
    public APaintObject(Point loc, String type, IInteractStrategy interactStrategy) {
        this.coordinate = loc;
        this.type = type;
        this.interactStrategy = interactStrategy;
    }


    /**
     * Detects collision between a active object
     *
     * @param obj The canvas dimensions.
     */
    public abstract boolean detectCollision(AMoveObject obj);

    /**
     * Get the paintable object type.
     *
     * @return The paintable object type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the paint location in the paint world.
     *
     * @return The paint location.
     */
    public Point getLocation() {
        return coordinate;
    }


    /**
     * Set the paint location in the canvas.  The origin (0,0) is the top left corner of the canvas.
     *
     * @param loc The paint coordinate.
     */
    public void setLocation(Point loc) {
        this.coordinate = loc;
    }

    /**
     * Get the paint object strategy.
     *
     * @return The paint object strategy
     */
    public IInteractStrategy getInteractStrategy() {
        return interactStrategy;
    }

    /**
     * Set the strategy if the paint object can switch strategies.
     *
     * @param strategy The new strategy
     */
    public void setInteractStrategy(IInteractStrategy strategy) {
        this.interactStrategy = strategy;
    }

    public void propertyChange(PropertyChangeEvent evt) {
    }
}
