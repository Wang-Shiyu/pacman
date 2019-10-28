package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.beans.PropertyChangeEvent;

/**
 * The big food that enables Pacman to eat ghosts when Pacman eat it.
 */
public class BigFood extends ACellObject{

    /**
     * Constructor.
     * @param imageIcon gameIcon
     * @param score score
     * @param type type
     * @param locationX locationX
     * @param locationY locationY
     * @param vel velocity
     * @param updateStrategy strategy
     */
    public BigFood(String imageIcon, int score, String type, double locationX, double locationY, double vel, IUpdateStrategy updateStrategy) {
        super(imageIcon, 20, "BigFood", locationX, locationY, vel, updateStrategy);
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
