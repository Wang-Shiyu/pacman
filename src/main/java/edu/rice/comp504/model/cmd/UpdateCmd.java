package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.PacMan;

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

        /*
        Check whether the moving object is Pacman or Ghost.
         */

        // TODO: check hole(pacman, ghost)
        // TODO: update pacman/ghosts location
        /*
        Try to move the moving Pacman or Ghost. Revert the move later if it is not valid.
        Backup the current move and location to enable revert.
         */
        context.setLastMove(context.getCurrentMove());
        context.setCurrentMove(context.getNextMove());

        context.computeNextLocation(); // This method also store the last valid location before the move.

        // TODO: update time in ghost
        // TODO: make sure new location is valid
    }
}
