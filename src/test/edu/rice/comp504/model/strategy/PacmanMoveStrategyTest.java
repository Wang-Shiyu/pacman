package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.GameHost;
import edu.rice.comp504.model.paint.*;
import gameparam.GameParam;
import junit.framework.TestCase;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class PacmanMoveStrategyTest extends TestCase {

    private PropertyChangeSupport pcs;
    private GameHost game;
    private int level;
    private int score;
    private ACellObject[][] board;
    private PacMan pacMan;
    private List<Ghost> ghosts;
    private GameHost.Status gameStatus;

    public void testPacManMove(){
        game = new GameHost();
        pcs = new PropertyChangeSupport(this);
        gameStatus = GameHost.Status.START;
        board = new ACellObject[GameParam.unitPerRow][GameParam.unitPerCol];
        ghosts = new LinkedList<>();
        score = 0;
        level = 1;
        game.initGame();
        PacMan pacMan = new PacMan("", 0, null,
                GameParam.PACMAN_INIT_X, GameParam.PACMAN_INIT_Y,
                GameParam.pacmanSpeed, PacManMoveStrategy.getInstance());
        this.pacMan = pacMan;
        for (int i = 0; i < 5; i++) {
            Ghost ghost = new Ghost("", 200, null,
                    GameParam.GHOST_INIT_X[i], GameParam.GHOST_INIT_Y, GameParam.ghostSpeed,
                    new GhostInitStrategy(GameParam.DOOR_Y - GameParam.pixelPerUnit, pacMan, board), GameParam.GHOST_RELEASE_TIME[i]);
            this.ghosts.add(ghost);
            pcs.addPropertyChangeListener("ghost", ghost);
        }
        pacMan.setCurrentMove(ACellObject.Direction.UP);
        IUpdateStrategy pacMove = PacManMoveStrategy.getInstance();
        pacMove.getName();
        pacMove.updateState(pacMan);
        assertEquals("Test Pacman Move Strategy", ACellObject.Direction.STOP,pacMan.getCurrentMove());
    }
}
