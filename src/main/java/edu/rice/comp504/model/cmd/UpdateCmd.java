package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.AMoveObject;
import edu.rice.comp504.model.paint.GameBoard;
import edu.rice.comp504.model.paint.Ghost;

import java.util.ArrayList;
import java.util.List;

public class UpdateCmd implements IPaintObjCmd {

    /**
     * Singleton object of current class.
     */
    private static UpdateCmd INSTANCE;

    /**
     * All the existing ghosts
     */
    private List<Ghost> ghosts;

    /**
     * instance of game board
     */
    private GameBoard gameBoard;

    /**
     * Constructor.
     */
    private UpdateCmd(GameBoard board) {
        ghosts = new ArrayList<>();
        this.gameBoard = board;
    }

    /**
     * @return get the singleton of the class.
     */
    public static UpdateCmd getInstance(GameBoard board) {
        if (INSTANCE == null) {
            INSTANCE = new UpdateCmd(board);
        }
        return INSTANCE;
    }

    @Override
    public void execute(AMoveObject context) {
        // TODO: update pacman/ghosts location
        // gameBoard.detectWall(context)

        // TODO: interact with food(food, big food)
        // APaintObject obj = gameBoard.detectFood(context);

        // TODO: if pac man eats big food, set canEatGhost & change ghost strategy
        // check obj

        // TODO: interact between Pac-man and ghosts
        // TODO: update score/life in game board
        // if(ghost.detectCollision(pac man)) {
        //  if(canEatGhost) use PacManCatchGhostStrategy in pac man
        //  else use GhostCatchPacManStrategy in ghost
        // }

        // TODO: clear cache
        ghosts.clear();
    }
}
