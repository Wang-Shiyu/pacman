package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.ACellObject;

import java.beans.PropertyChangeSupport;

public class InteractCmd implements IPaintObjCmd {

    /**
     * Singleton object of current class.
     */
    private static InteractCmd INSTANCE;

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

        // TODO: if pac man eats big food, set canEatGhost & change ghost strategy

        // TODO: interact between Pac-man and ghosts
        // TODO: update score/life in game board
    }
}
