package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeListener;

public abstract class AMoveObject implements PropertyChangeListener {

    /**
     * location of object, pixel-wise
     */
    private Point loc;
    /**
     * velocity of object
     */
    private Point vel;
    /**
     * maybe use for indexing ghost
     */
    private int index;
    /**
     * type of object
     */
    private String type;
    /**
     * interact strategy
     */
    private IInteractStrategy interactStrategy;
    /**
     * update strategy
     */
    private IUpdateStrategy updateStrategy;


    protected boolean isTransposable;
    protected boolean isMortal;

    /**
     * Constructor
     *
     * @param loc       The location of the paintable object on the canvas
     * @param vel       The object velocity
     * @param type      The type of this object
     * @param uStrategy The object update strategy
     * @param iStrategy The object interact strategy
     */
    public AMoveObject(Point loc, Point vel, String type, IUpdateStrategy uStrategy, IInteractStrategy iStrategy){
        this.loc = loc;
        this.vel = vel;
        this.type = type;
        this.interactStrategy = iStrategy;
        this.updateStrategy = uStrategy;
        this.isTransposable = false;
        this.isMortal = false;
    }

    /**
     * Detects collision between a active object
     *
     * @param obj The canvas dimensions.
     */
    public abstract boolean detectCollision(AMoveObject obj);

    public Point getLoc() {
        return loc;
    }

    public Point getVel() {
        return vel;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public IInteractStrategy getInteractStrategy() {
        return interactStrategy;
    }

    public IUpdateStrategy getUpdateStrategy() {
        return updateStrategy;
    }

    public boolean isMortal() {
        return isMortal;
    }

    public boolean isTransposable() {
        return isTransposable;
    }

    public void setLoc(Point loc) {
        this.loc = loc;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setVel(Point vel) {
        this.vel = vel;
    }

    public void setInteractStrategy(IInteractStrategy interactStrategy) {
        this.interactStrategy = interactStrategy;
    }

    public void setUpdateStrategy(IUpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
    }

    public void setMortal(boolean mortal) {
        isMortal = mortal;
    }

    public void setTransposable(boolean transposable) {
        isTransposable = transposable;
    }
}
