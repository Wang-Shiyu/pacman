package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.beans.PropertyChangeEvent;

public class Blinky extends ACellObject {
    protected boolean weak;
    protected boolean eaten;
    protected boolean returning;

    private boolean canCollideDoor;
    private int releaseTime;

    /**
     * Constructor.
     *
     * @param imageIcon      imageIcon
     * @param score          score
     * @param type           type
     * @param locationX      locationX
     * @param locationY      locationY
     * @param vel            velocity
     * @param updateStrategy strategy
     */
    public Blinky(String imageIcon, int score, String type, double locationX, double locationY, double vel, IUpdateStrategy updateStrategy) {
        super(imageIcon, score, type, locationX, locationY, vel, updateStrategy);
        this.releaseTime = releaseTime;
        this.canCollideDoor = false;
        this.returning = false;
        this.weak = false;
        this.eaten = false;
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

    public void setCanCollideDoor(boolean canCollideDoor) {
        this.canCollideDoor = canCollideDoor;
    }

    public void setReturning(boolean returning) {
        this.returning = returning;
    }

    public boolean isCanCollideDoor() {
        return canCollideDoor;
    }

    public boolean isReturning() {
        return returning;
    }

    public int getReleaseTime() {
        return releaseTime;
    }
    @Override
    public boolean isOverlap(ACellObject object) {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

    }
}
