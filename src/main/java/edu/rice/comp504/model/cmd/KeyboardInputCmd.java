package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.ACellObject;

import javax.swing.*;

public class KeyboardInputCmd implements IPaintObjCmd {

    /**
     * Execute the command.
     * @param context The receiver paint object on which the command is executed.
     */
    @Override
    public void execute(ACellObject context) {
        // TODO: cache direction as intended velocity for pac man
    }
}
