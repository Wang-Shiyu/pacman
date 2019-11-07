package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.beans.PropertyChangeEvent;

/**
 * The white dot food that Pacman can eat to earn credits.
 */
public class Food extends ACellObject {

    @Setter
    @Getter
    private boolean bigFood;
    private boolean fruit;

    /**
     * Constructor.
     * @param imageIcon img
     * @param score score
     * @param type food type
     * @param locationX point x
     * @param locationY point y
     * @param vel cell speed
     * @param updateStrategy strategy
     * @param bigFood special food sign
     */
    public Food(String imageIcon, int score, String type, double locationX, double locationY, double vel, IUpdateStrategy updateStrategy, boolean bigFood) {
        super(imageIcon, score, "Food", locationX, locationY, vel, updateStrategy);
        this.bigFood = bigFood;
        this.fruit = false;
    }

    @Override
    public boolean isOverlap(ACellObject object) {
        return false;
    }

    @Override
    public void reset() {

    }

    public void setFruit() {
        this.fruit = true;
    }

    public boolean isBigFood() {
        return bigFood;
    }

    public boolean isFruit() {
        return fruit;
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
