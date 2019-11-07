package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.PacMan;
import edu.rice.comp504.model.strategy.ChaseStrategy;
import edu.rice.comp504.model.strategy.PacManMoveStrategy;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Update cmd that handles object location update.
 */
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
     * Check if moving object overlaps any wall.
     */
    private boolean overlapWithWall(ACellObject context){
        List<PropertyChangeListener> propertyChangeListenerList = new ArrayList<>();
        Collections.addAll(propertyChangeListenerList, pcs.getPropertyChangeListeners("Wall"));
        Collections.addAll(propertyChangeListenerList, pcs.getPropertyChangeListeners("Door"));
        for (PropertyChangeListener pcl: propertyChangeListenerList){
            // TODO: WHAT is the property name of Wall?
            /*
            Loop all the moving objects in the pcs to check if any overlaps with walls
            */
            ACellObject wall = (ACellObject) pcl;
            if(context.isOverlap(wall)){
                return true;
            }
        }
        return false;
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
        if (context instanceof PacMan) {
            context.getUpdateStrategy().updateState(context);
        } else if (context instanceof Ghost) {
            context.getUpdateStrategy().updateState(context);
        }

        if (overlapWithWall(context)) {
            //Invalid move, Revert location and current move
            context.revertLocation();
            context.setNextMove(context.getCurrentMove());
            context.setCurrentMove(context.getLastMove());

            // Then make a move based on last move direction
            context.computeNextLocation();

            // Check if last valid moving direction is possible. If not, make the moving object STOP.
            if(overlapWithWall(context)){
                context.revertLocation();
                context.setCurrentMove(ACellObject.Direction.STOP);
            }
        }
    }
}
