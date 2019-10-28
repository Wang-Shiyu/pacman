package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.beans.PropertyChangeEvent;

/**
 * Pacman.
 */
public class PacMan extends ACellObject {
    private int remainingLife;

    /**
     * Constructor.
     * @param imageIcon image
     * @param score score
     * @param type type
     * @param locationX locationX
     * @param locationY locationY
     * @param vel velocity
     * @param updateStrategy strategy
     */
    public PacMan(String imageIcon, int score, String type, double locationX, double locationY, double vel, IUpdateStrategy updateStrategy) {
        super(imageIcon, 0, "PacMan", locationX, locationY, vel, updateStrategy);
    }


    @Override
    public boolean isOverlap(ACellObject object) {
        return false;
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

    }

    public int getRemainingLife() {
        return remainingLife;
    }

    public void setRemainingLife(int remainingLife) {
        this.remainingLife = remainingLife;
    }
}
