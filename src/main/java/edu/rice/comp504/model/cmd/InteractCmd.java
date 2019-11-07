package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Food;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.PacMan;
import edu.rice.comp504.model.strategy.EscapeStrategy;
import edu.rice.comp504.model.strategy.GhostReturnStrategy;
import gameparam.GameParam;
import gameparam.TimeCounter;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

/**
 * Interact cmd that handles object interactions.
 */
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
     * the actual game board.
     */
    private ACellObject[][] board;

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
    public InteractCmd setPcs(PropertyChangeSupport pcs) {
        this.pcs = pcs;
        return INSTANCE;
    }

    public InteractCmd setBoard(ACellObject[][] board) {
        this.board = board;
        return INSTANCE;
    }

    /**
     * Execute the command.
     *
     * @param context The receiver paint object on which the command is executed.
     */
    @Override
    public void execute(ACellObject context) {
        // TODO: interact with food(food, big food)
        if (context instanceof PacMan) {
            PacMan pacMan = (PacMan) context;
            // Interact with food
            for (PropertyChangeListener pcl : pcs.getPropertyChangeListeners("Food")) {
                Food food = (Food) pcl;
                if (pacMan.isOverlap(food)) {
                    ((Food) pcl).setType("Null");
                    pcs.removePropertyChangeListener("Food", pcl);
                    pcs.addPropertyChangeListener("Null", pcl);
                    pacMan.setScore(pacMan.getScore() + food.getScore());
                    if (food.isBigFood()) {
                        // Make ghosts weak and change strategy
                        for (Ghost g : findGhosts()) {
//                            EscapeStrategy.cleanStrategy();
                            if (TimeCounter.getTime() > g.getReleaseTime()) {
                                g.setWeak(true);
                                EscapeStrategy strategy = EscapeStrategy.getInstance(pacMan, board, TimeCounter.getTime());
                                strategy.setEndTime(TimeCounter.getTime());
                                g.setUpdateStrategy(strategy);
                            }
                        }
                    }
                }
            }

            // Interact with Ghost
            for (PropertyChangeListener pcl : pcs.getPropertyChangeListeners("ghost")) {
                Ghost ghost = (Ghost) pcl;
                if (pacMan.isOverlap(ghost)) {
                    if (ghost.isWeak()) {
                        // Ghost will be eaten and go back to jail
                        ghost.setEaten(true);
                        ghost.setReturning(true);
                        if (!(ghost.getUpdateStrategy() instanceof GhostReturnStrategy)) {
                            ghost.setUpdateStrategy(
                                    new GhostReturnStrategy(new Point(GameParam.GHOST_INIT_X[2], GameParam.GHOST_INIT_Y), pacMan, board));
                        }
                    } else {
                        // Pacman will die
                        pacMan.setRemainingLife(pacMan.getRemainingLife() - 1);
                        pacMan.reset();
                        for (Ghost g : findGhosts()) {
                            g.reset();
                        }
                        break;
                    }
                }
            }
        }

        // TODO: if pac man eats big food, set canEatGhost & change ghost strategy

        // TODO: interact between Pac-man and ghosts
        // TODO: update score/life in game board
    }

    private List<Ghost> findGhosts() {
        List<Ghost> ghosts = new LinkedList<>();
        for (PropertyChangeListener pcl : pcs.getPropertyChangeListeners("ghost")) {
            Ghost ghost = (Ghost) pcl;
            ghosts.add(ghost);
        }
        return ghosts;
    }
}
