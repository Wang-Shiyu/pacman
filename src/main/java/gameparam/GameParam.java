package gameparam;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.PacMan;
import edu.rice.comp504.model.strategy.ChaseStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.RandomStrategy;
import edu.rice.comp504.model.strategy.SawAndChaseStrategy;

import java.awt.*;
import java.util.Deque;
import java.util.Map;

public class GameParam {
    public static final int fps = 60;
    public static final int pixelPerUnit = 31;
    public static final  int unitPerRow = 20;
    public static final int unitPerCol = 25;
    public static final double stepSize = 0.05 * pixelPerUnit; // Pacman or ghosts' moving step size, eg: 0.02 cell per step
    public static double ghostSpeed = 1 * stepSize;
    public static double pacmanSpeed = 2 * stepSize;
    public static double foodScore = 10;
    public static double bigFoodScore = 20;
    public static String boardColor;
    public static String fruitType;
    public static int pacmanMaxLives = 3;
    public static String mazeMap;
    public static int cellSize;
    public static final int PACMAN_INIT_Y = 31 * 4;
    public static final int PACMAN_INIT_X = 31 * 12;
    public static final int GHOST_INIT_Y = 31 * 12;
    public static final int DOOR_Y = 31 * 11;
    public static final int[] GHOST_INIT_X = {31 * 10, 31 * 11, 31 * 12, 31 * 13, 31 * 14};
    public static final int[] GHOST_RELEASE_TIME = {2, 7, 15, 35, 55, 75, 95};
    public static final int GHOST_ESCAPE_TIME = 5;
    public static Map<String, Deque<Point>> cache;
//    public static int strategyIndex = 0;
//    public static String[] strategyName = new String[]{"Random", "Random", "Random", "Avoid", "Chase"};

    /**
     * randomly decide the strategy according to percent.
     * @param pacMan pacman.
     * @param board game board.
     * @return an update strategy.
     */
    public static IUpdateStrategy getGhostStrategy(PacMan pacMan, ACellObject[][] board) {
//        String name = strategyName[strategyIndex];
//        strategyIndex = (strategyIndex + 1) % strategyName.length;
        double ran = Math.random();
        if (ran < 0.4) {
            return RandomStrategy.getInstance(board);
        } else if (ran < 0.7) {
            return SawAndChaseStrategy.getInstance(pacMan, board);
        } else {
            return ChaseStrategy.getInstance(pacMan, board);
        }
    }

//    public static void resetGhostStrategy() {
//        strategyIndex = 0;
//    }
}
