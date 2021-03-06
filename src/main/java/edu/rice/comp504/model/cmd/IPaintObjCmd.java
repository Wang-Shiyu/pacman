package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.ACellObject;

/**
 * The IPaintObjCmd is an interface used to pass commands to objects in the PaintObjWorld.  The
 * objects must execute the command.
 */
public interface IPaintObjCmd {

    /**
     * Execute the command.
     * @param context The receiver paint object on which the command is executed.
     */
    public void execute(ACellObject context);
}
