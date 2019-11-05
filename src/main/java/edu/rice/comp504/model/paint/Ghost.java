package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import gameparam.GameParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeEvent;

/**
 * The ghost that will chase and eat Pacman.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Ghost extends ACellObject {
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
    public Ghost(String imageIcon, int score, String type, double locationX, double locationY, double vel,
                 IUpdateStrategy updateStrategy, int releaseTime) {
        super(imageIcon, score, "Ghost", locationX, locationY, vel, updateStrategy);
        this.releaseTime = releaseTime;
        this.canCollideDoor = false;
        this.returning = false;
        this.weak = false;
        this.eaten = false;
    }

    @Override
    public boolean isOverlap(ACellObject object) {
        if (object instanceof DoorUnit && canCollideDoor) {
            return false;
        }
        if (object instanceof WallUnit) {
            double x = object.getLocationX() * GameParam.pixelPerUnit;
            double y = object.getLocationY() * GameParam.pixelPerUnit;
            return Math.abs(x - this.getLocationX()) < GameParam.pixelPerUnit &&
                    Math.abs(y - this.getLocationY()) < GameParam.pixelPerUnit;
        }
        return Math.abs(object.getLocationX() - this.getLocationX()) < GameParam.pixelPerUnit &&
                Math.abs(object.getLocationY() - this.getLocationY()) < GameParam.pixelPerUnit;
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
    public void propertyChange(PropertyChangeEvent evt) {
        /* Send the CMD from the new value stored in the event */
        IPaintObjCmd cmd = (IPaintObjCmd) evt.getNewValue();
        cmd.execute(this);
    }
}
