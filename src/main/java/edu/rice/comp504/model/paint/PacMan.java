package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.cmd.IPaintObjCmd;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.PacManMoveStrategy;
import gameparam.GameParam;

import java.beans.PropertyChangeEvent;

/**
 * Pacman.
 */
public class PacMan extends ACellObject {
    private int remainingLife;

    /**
     * Constructor.
     *
     * @param imageIcon      image
     * @param score          score
     * @param type           type
     * @param locationX      locationX
     * @param locationY      locationY
     * @param vel            velocity
     * @param updateStrategy strategy
     */
    public PacMan(String imageIcon, int score, String type, double locationX, double locationY, double vel, IUpdateStrategy updateStrategy) {
        super(imageIcon, 0, "PacMan", locationX, locationY, vel, updateStrategy);
        remainingLife = 3;
    }

    @Override
    public boolean isOverlap(ACellObject object) {
//        double currentX = this.getLocationX() + GameParam.pixelPerUnit / 2;
//        double currentY = this.getLocationY() + GameParam.pixelPerUnit / 2;
        if (object instanceof BigFood
                || object instanceof Food
                || object instanceof WallUnit) {
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

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        /* Send the CMD from the new value stored in the event */
        IPaintObjCmd cmd = (IPaintObjCmd) evt.getNewValue();
        cmd.execute(this);
    }

    public int getRemainingLife() {
        return remainingLife;
    }

    public void setRemainingLife(int remainingLife) {
        this.remainingLife = remainingLife;
    }
}
