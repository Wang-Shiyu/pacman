package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.GameHost;
import edu.rice.comp504.model.paint.*;
import gameparam.GameParam;
import junit.framework.TestCase;

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class GhostStrategyTest extends TestCase {
    private PropertyChangeSupport pcs;
    private GameHost game;
    private int level;
    private int score;
    private ACellObject[][] board;
    private PacMan pacMan;
    private List<Ghost> ghosts;
    private GameHost.Status gameStatus;

    protected void setUp(){
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
    }

    public void testAvoidStrategy(){
        AvoidStrategy.cleanStrategy();
        IUpdateStrategy avoid = AvoidStrategy.getInstance(pacMan, board);
        avoid.getName();
        //pacMan.setUpdateStrategy(avoid);
        pacMan.setLocation(10,10);
        for(Ghost g:ghosts){
            g.setLocation(10,20);
        }
        ghosts.get(0).setCurrentMove(ACellObject.Direction.UP);
        ghosts.get(1).setCurrentMove(ACellObject.Direction.DOWN);
        ghosts.get(2).setCurrentMove(ACellObject.Direction.LEFT);
        ghosts.get(3).setCurrentMove(ACellObject.Direction.RIGHT);
        for(Ghost g:ghosts){
            for(int i=0;i<10;i++){
                avoid.updateState(g);
            }
        }
        assertEquals("Test avoid strategy",  ACellObject.Direction.UP,ghosts.get(0).getCurrentMove());
        assertEquals("Test avoid strategy", ACellObject.Direction.DOWN, ghosts.get(1).getCurrentMove());
        assertEquals("Test avoid strategy", ACellObject.Direction.LEFT, ghosts.get(2).getCurrentMove());
        assertEquals("Test avoid strategy", ACellObject.Direction.RIGHT, ghosts.get(3).getCurrentMove());
    }

    public void testChaseStrategy() {
        ChaseStrategy.cleanStrategy();
        IUpdateStrategy chase = ChaseStrategy.getInstance(pacMan, board);
        chase.getName();
        //pacMan.setUpdateStrategy(chase);
        pacMan.setLocation(10*31,10*31);

        ghosts.get(0).setLocation(9*31,10*31);
        ghosts.get(1).setLocation(11*31,10*31);
        ghosts.get(2).setLocation(10*31,9*31);
        ghosts.get(3).setLocation(10*31,11*31);
        ghosts.get(4).setLocation(10*31,10*31);

        for(Ghost g:ghosts){
            for(int i=0;i<10;i++){
                chase.updateState(g);
            }
        }
        assertEquals("Test Chase strategy", ACellObject.Direction.RIGHT, ghosts.get(0).getCurrentMove());
        assertEquals("Test Chase strategy", ACellObject.Direction.LEFT, ghosts.get(1).getCurrentMove());
        assertEquals("Test Chase strategy", ACellObject.Direction.DOWN, ghosts.get(2).getCurrentMove());
        assertEquals("Test Chase strategy", ACellObject.Direction.UP, ghosts.get(3).getCurrentMove());
    }

    public void testRandomStrategy(){
        RandomStrategy.cleanStrategy();
        IUpdateStrategy ran = RandomStrategy.getInstance();
        ran.getName();
        //pacMan.setUpdateStrategy(chase);
        pacMan.setLocation(10*31,10*31);

        ghosts.get(0).setLocation(9*31,10*31);
        ghosts.get(1).setLocation(11*31,10*31);
        ghosts.get(2).setLocation(10*31,9*31);
        ghosts.get(3).setLocation(10*31,11*31);
        ghosts.get(4).setLocation(10*31,10*31);

        for(Ghost g:ghosts){
            for(int i=0;i<10;i++){
                ran.updateState(g);
            }
        }
        assertNotSame("Test Ghost Random strategy", ACellObject.Direction.STOP, ghosts.get(0).getType());
    }

    private InputStream getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }

    public void testEscapeStrategy() {
        EscapeStrategy.cleanStrategy();
        IUpdateStrategy escape = EscapeStrategy.getInstance(pacMan, board,0);
        //pacMan.setUpdateStrategy(escape);
        escape.getName();
        InputStream file = getFileFromResources("public/maze.txt");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            String line;
            int row = 0;
            int col = 0;
            while ((line = br.readLine()) != null) {
                col = 0;
                for (char c : line.toCharArray()) {
                    if (c == '1') {
                        board[row][col] = new WallUnit("", 0, null, col, row, 0, null);
                        pcs.addPropertyChangeListener("Wall", board[row][col]);
                    } else if (c == '0') {
                        board[row][col] = new Food("", 10, null, col, row, 0, null, false);
                        pcs.addPropertyChangeListener("Food", board[row][col]);
                    } else if (c == '2') {
                        board[row][col] = new DoorUnit("", 0, null, col, row, 0, null);
                        pcs.addPropertyChangeListener("Door", board[row][col]);
                    } else if (c == '3') {
                        // BigFood
                        board[row][col] = new Food("", 20, null, col, row, 0, null, true);
                        pcs.addPropertyChangeListener("Food", board[row][col]);
                    } else if (c == '9') {
                        board[row][col] = new NullUnit("", 0, null, col, row, 0, null);
                        pcs.addPropertyChangeListener("Null", board[row][col]);
                    }
                    col++;
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pacMan.setLocation(10*31,10*31);

        ghosts.get(0).setLocation(9*31,10*31);
        ghosts.get(1).setLocation(11*31,10*31);
        ghosts.get(2).setLocation(10*31,9*31);
        ghosts.get(3).setLocation(10*31,11*31);
        ghosts.get(4).setLocation(10*31,10*31);

        for(Ghost g:ghosts){
            for(int i=0;i<10;i++){
                escape.updateState(g);
            }
        }
        assertEquals("Test Escape strategy", ACellObject.Direction.LEFT, ghosts.get(0).getCurrentMove());
    }

    public void testGhostInitStrategy(){

        IUpdateStrategy ini = new GhostInitStrategy(10*31,pacMan,board);
        ini.getName();
        //pacMan.setUpdateStrategy(chase);
        pacMan.setLocation(10*31,10*31);

        ghosts.get(0).setLocation(9*31,10*31);
        ghosts.get(1).setLocation(11*31,10*31);
        ghosts.get(2).setLocation(10*31,9*31);
        ghosts.get(3).setLocation(10*31,11*31);
        ghosts.get(4).setLocation(10*31,10*31);

        for(Ghost g:ghosts){
            for(int i=0;i<10;i++){
                ini.updateState(g);
            }
        }
        assertEquals("Test Ghost Init strategy", ACellObject.Direction.STOP, ghosts.get(3).getCurrentMove());
    }

    public void testGhostReturnStrategy(){
        IUpdateStrategy re = new GhostReturnStrategy(new Point(0,0),pacMan, board);
        re.getName();
        //pacMan.setUpdateStrategy(chase);
        pacMan.setLocation(10*31,10*31);

        ghosts.get(0).setLocation(9*31,10*31);
        ghosts.get(1).setLocation(11*31,10*31);
        ghosts.get(2).setLocation(10*31,9*31);
        ghosts.get(3).setLocation(10*31,11*31);
        ghosts.get(4).setLocation(10*31,10*31);

        for(Ghost g:ghosts){
            for(int i=0;i<10;i++){
                re.updateState(g);
            }
        }
        assertEquals("Test Ghost Return strategy", ACellObject.Direction.UP, ghosts.get(0).getCurrentMove());
    }

    public void testNullUpdateStrategy(){
        IUpdateStrategy nu = new NullUpdateStrategy();
        nu.getName();
        //pacMan.setUpdateStrategy(chase);
        pacMan.setLocation(10*31,10*31);

        ghosts.get(0).setLocation(9*31,10*31);
        ghosts.get(1).setLocation(11*31,10*31);
        ghosts.get(2).setLocation(10*31,9*31);
        ghosts.get(3).setLocation(10*31,11*31);
        ghosts.get(4).setLocation(10*31,10*31);

        for(Ghost g:ghosts){
            for(int i=0;i<10;i++){
                nu.updateState(g);
            }
        }
        assertEquals("Test Null strategy", ACellObject.Direction.STOP, ghosts.get(3).getCurrentMove());
    }

}
