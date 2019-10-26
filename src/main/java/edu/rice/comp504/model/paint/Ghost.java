package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IInteractStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;

public class Ghost extends AMoveObject {
    protected boolean weak = false;
    protected boolean eaten = false;
    protected int tryMove, lastMove, currentMove;
    protected boolean returning = false;

    // move control
    public static final int STOP = 0;
    public static final int MOVE_LEFT = 1;
    public static final int MOVE_RIGHT = 2;
    public static final int MOVE_UP = 3;
    public static final int MOVE_DOWN = 4;

    private int movDirection = STOP;
    /**
     * Constructor
     *
     * @param loc       The location of the paintable object on the canvas
     * @param vel       The object velocity
     * @param type      The type of this object
     * @param uStrategy The object update strategy
     * @param iStrategy The object interact strategy
     */
    public Ghost(Point loc, Point vel, String type, IUpdateStrategy uStrategy, IInteractStrategy iStrategy) {
        super(loc, vel, "Ghost", uStrategy, iStrategy);
        this.isTransposable = true;
        this.isMortal = false;
        this.eaten = true;
    }

    @Override
    public boolean detectCollision(AMoveObject obj) {
        return false;
    }

    public void reset() {
        // reset ghost into jail
    }

    public boolean isWeak() {
        return weak;
    }

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
