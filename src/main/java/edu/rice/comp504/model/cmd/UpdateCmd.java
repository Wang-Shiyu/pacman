package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Ghost;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class UpdateCmd implements IPaintObjCmd {

    /**
     * Singleton object of current class.
     */
    private static UpdateCmd INSTANCE;

    /**
     * Property change support contains all the ACellObjects and they are used to detect collision or
     * update the ACellObjects.
     */
    private PropertyChangeSupport pcs;


    /**
     * Constructor.
     */
    private UpdateCmd() {
    }

    /**
     * @return get the singleton of the class.
     */
    public static UpdateCmd getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UpdateCmd();
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
     * @param context The receiver paint object on which the command is executed.
     */
    @Override
    public void execute(ACellObject context) {
        // TODO: check hole(pacman, ghost)
        // TODO: update pacman/ghosts location
        // TODO: update time in ghost
        // TODO: make sure new location is valid
    }
}
