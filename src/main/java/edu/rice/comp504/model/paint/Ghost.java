package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.beans.PropertyChangeEvent;

/**
 * The ghost that will chase and eat Pacman.
 */
public class Ghost extends ACellObject {
    protected boolean weak = false;
    protected boolean eaten = false;
    protected boolean returning = false;

    private boolean canCollideDoor;
    private int releaseTime;
    private int currentTime;

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
    public Ghost(String imageIcon, int score, String type, double locationX, double locationY, double vel,
                 IUpdateStrategy updateStrategy, int releaseTime) {
        super(imageIcon, score, "Ghost", locationX, locationY, vel, updateStrategy);
        this.releaseTime = releaseTime;
        this.canCollideDoor = false;
    }

    @Override
    public boolean isOverlap(ACellObject object) {
        return false;
    }

    @Override
    public void reset() {

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
