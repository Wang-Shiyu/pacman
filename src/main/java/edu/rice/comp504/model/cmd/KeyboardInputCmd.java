package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.PacMan;

import javax.swing.*;
/**
 * KeyboardInput cmd that handles object interactions.
 */
public class KeyboardInputCmd implements IPaintObjCmd {

    /**
     * Singleton object of current class.
     */
    private static KeyboardInputCmd INSTANCE;

    /**
     * Store the next move in the CMD and pass it to the Pacman through Keyboard CMD
     */
    private ACellObject.Direction move;

    /**
     * Constructor.
     */
    private KeyboardInputCmd() {
    }

    /**
     * Initialize the move field in the Keyboard CMD.
     */
    public void setMove(ACellObject.Direction move) {
        this.move = move;
    }

    /**
     * @return get the singleton of the class.
     */
    public static KeyboardInputCmd getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new KeyboardInputCmd();
        }
        return INSTANCE;
    }

    /**
     * Execute the command.
     * @param context The receiver paint object on which the command is executed.
     */
    @Override
    public void execute(ACellObject context) {
        // TODO: cache direction as intended velocity for pac man
        PacMan pacman  = (PacMan) context;
        pacman.setNextMove(move);
    }
}
