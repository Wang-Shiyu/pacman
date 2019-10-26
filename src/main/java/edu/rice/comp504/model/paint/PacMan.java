package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.NullInteractStrategy;
import edu.rice.comp504.model.strategy.PacManMoveStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;

public class PacMan extends AMoveObject {

    /**
     * intended velocity, when there is a user input, we set it as intended velocity
     * when updating location, first try if we can use intendedVelocity
     */
    private Point intendedVelocity;

    /**
     * after eating big food, pac man can eat ghost
     */
    private boolean canEatGhost;

    /**
     * Constructor
     *
     * @param loc       The location of the paintable object on the canvas
     * @param vel       The object velocity
     * @param type      The type of this object
     * @param uStrategy The object update strategy
     * @param iStrategy The object interact strategy
     */
    public PacMan(Point loc, Point vel, String type, IUpdateStrategy uStrategy, IInteractStrategy iStrategy) {
        super(loc, vel, "PacMan", PacManMoveStrategy.getInstance(), NullInteractStrategy.getInstance());
    }

    @Override
    public boolean detectCollision(AMoveObject obj) {
        return false;
    }

    public Point getCurrentVelocity() {
        return getVel();
    }

    public void setCurrentVelocity(Point currentVelocity) {
        setVel(currentVelocity);
    }

    public Point getIntendedVelocity() {
        return intendedVelocity;
    }

    public void setIntendedVelocity(Point intendedVelocity) {
        this.intendedVelocity = intendedVelocity;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
