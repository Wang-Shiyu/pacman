package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.beans.PropertyChangeEvent;

/**
 * The orange ghost that will avoid Pacman.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Clyde extends ACellObject{
    protected boolean weak;
    protected boolean eaten;
    protected boolean returning;

    private boolean canCollideDoor;
    private int releaseTime;

    /**
     * Constructor.
     * @param imageIcon imageIcon
     * @param score score
     * @param type type
     * @param locationX locationX
     * @param locationY locationY
     * @param vel velocity
     * @param updateStrategy strategy
     * @param releaseTime releaseTime
     */
    public Clyde(String imageIcon, int score, String type, double locationX, double locationY, double vel,
                 IUpdateStrategy updateStrategy, int releaseTime) {
        super(imageIcon, score, "Ghost", locationX, locationY, vel, updateStrategy);
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
