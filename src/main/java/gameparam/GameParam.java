package gameparam;

public class GameParam {
    public final static int pixelPerUnit = 31;
    public final static int unitPerRow = 20;
    public final static int unitPerCol = 25;
    public final static double stepSize = 0.05 * pixelPerUnit; // Pacman or ghosts' moving step size, eg: 0.02 cell per step
    public static double ghostSpeed = 1 * stepSize;
    public static double pacmanSpeed = 1 * stepSize;
    public static double foodScore = 10;
    public static double bigFoodScore = 20;
    public static String boardColor;
    public static String fruitType;
    public final static int pacmanMaxLives = 3;
    public static String mazeMap;
    public static int cellSize;
    public final static int PACMAN_INIT_Y = 31 * 4;
    public final static int PACMAN_INIT_X = 31 * 12;
    public final static int GHOST_INIT_Y = 31 * 12;
    public final static int GHOST_INIT_X[] = {31 * 10, 31 * 11, 31 * 12, 31 * 13, 31 * 14};
    public final static int GHOST_RELEASE_TIME[] = {50, 150, 250, 350, 450};
}
