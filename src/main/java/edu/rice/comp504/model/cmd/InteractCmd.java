package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Food;
import edu.rice.comp504.model.paint.PacMan;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class InteractCmd implements IPaintObjCmd {

    /**
     * Singleton object of current class.
     */
    private static InteractCmd INSTANCE;

    /**
     * Property change support contains all the ACellObjects and they are used to detect collision or
     * update the ACellObjects.
     */
    private PropertyChangeSupport pcs;

    /**
     * Constructor.
     */
    private InteractCmd() {
    }

    /**
     * @return get the singleton of the class.
     */
    public static InteractCmd getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InteractCmd();
        }
        return INSTANCE;
    }

    /**
     * Property change support setter. Initialize the property change support field.
     */
    public void setPcs(PropertyChangeSupport pcs) {
        this.pcs = pcs;
    }

    /**
     * Execute the command.
     *
     * @param context The receiver paint object on which the command is executed.
     */
    @Override
    public void execute(ACellObject context) {
        // TODO: interact with food(food, big food)
        checkFood(context, pcs.getPropertyChangeListeners("Food"));
        boolean eatBigFood = checkBigFood(pcs.getPropertyChangeListeners("BigFood"));

        if (context instanceof PacMan) {
            PacMan pacMan = (PacMan) context;
            // Interact with food
            for (PropertyChangeListener pcl : pcs.getPropertyChangeListeners("Food")) {
                Food food = (Food) pcl;
                if (pacMan.isOverlap(food)) {
                    pcs.removePropertyChangeListener("Food", pcl);
                    pacMan.setScore(pacMan.getScore() + food.getScore());
                }
            }
        }

        // TODO: if pac man eats big food, set canEatGhost & change ghost strategy

        // TODO: interact between Pac-man and ghosts
        // TODO: update score/life in game board
    }

    private void checkFood(ACellObject context, PropertyChangeListener[] propertyChangeListeners) {
        for (PropertyChangeListener propertyChangeListener : propertyChangeListeners) {
            ACellObject food = (ACellObject) propertyChangeListener;
            if (context.isOverlap(food)) {
                pcs.removePropertyChangeListener("Food", propertyChangeListener);
            }
        }
    }

    private boolean checkBigFood(PropertyChangeListener[] propertyChangeListeners) {
        return false;
    }
}
