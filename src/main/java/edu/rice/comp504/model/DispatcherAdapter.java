package edu.rice.comp504.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatcherAdapter {
    private PropertyChangeSupport pcs;

    /**
     * Constructor.
     */
    public DispatcherAdapter() {
        pcs = new PropertyChangeSupport(this);
    }

    /**
     * Call the update method on all the paintobj objects to update their position in the paintobj world.
     */
    public PropertyChangeListener[] updatePacMamWorld() {
//        if (GameBoard.getInstance().isStart()) {
//            // TODO: send update cmd
//            // UpdateCmd.getInstance(GameBoard.getInstance())
//            // TODO: check if game ends() & update level
//            // GameBoard.getInstance().isWin() & GameBoard.getInstance().isLose()
//        }
        return null;
    }

    /**
     * handle user input about changing direction.
     */
    public void move(String direction) {
        // TODO: Send a KeyboardInputCmd to Pacman when keyboard evt is triggered
    }

    /**
     * initialize game board for front end rendering
     */
    public PropertyChangeListener[] initGame() {
        // TODO: init game board for rendering, add listener
//        addListener(GameBoard.getInstance());
        // TODO: init ghosts based on level, add listener

        // TODO: init pacman, add listener (please keep this order since firePropertyChange will use this order)
        return pcs.getPropertyChangeListeners();
    }

    /**
     * all objects all allowed to move according to their strategy when game starts
     */
    public void startGame() {
//        GameBoard.getInstance().setStart(true);
    }

    /**
     * Add a ball that will listen for a property change (i.e. time elapsed)
     *
     * @param pcl The ball
     */
    private void addListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }



}
