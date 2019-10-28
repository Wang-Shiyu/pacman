package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.beans.PropertyChangeEvent;

/**
 * The white dot food that Pacman can eat to earn credits.
 */
public class Food extends ACellObject {

    public Food(String imageIcon, int score, String type, double locationX, double locationY, double vel, IUpdateStrategy updateStrategy) {
        super(imageIcon, 10, "Food", locationX, locationY, vel, updateStrategy);
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
}
