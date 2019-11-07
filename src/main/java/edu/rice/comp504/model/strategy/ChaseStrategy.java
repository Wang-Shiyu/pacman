package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.*;
import gameparam.TimeCounter;
import lombok.Getter;

import java.util.*;
import java.awt.Point;

/**
 * Use bfs to chase pacman(big latency in heroku).
 */
public class ChaseStrategy implements IUpdateStrategy {

    /**
     * Singleton object of ChaseStrategy class.
     */
    private static ChaseStrategy INSTANCE;

    @Getter
    private PacMan pacman;

    @Getter
    private ACellObject[][] board;

    private Deque<Point> cachePath;

    /**
     * Constructor.
     */
    private ChaseStrategy(PacMan pacMan, ACellObject[][] board) {
        this.pacman = pacMan;
        this.board = board;
        cachePath = new LinkedList<>();
    }

    public static void cleanStrategy() {
        INSTANCE = null;
    }

    /**
     * @return get the singleton of ChaseStrategy class.
     */
    public static ChaseStrategy getInstance(PacMan pacman, ACellObject[][] board) {
        if (INSTANCE == null) {
            INSTANCE = new ChaseStrategy(pacman, board);
        }
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "ChaseStrategy";
    }

    @Override
    public void updateState(ACellObject context) {
        // TODO: ghost chase the Pac man
        if (context instanceof Ghost) {
            Ghost ghost = (Ghost) context;
            Deque<Point> path = bfs(ghost, pacman);
            if (path != null) {
                path.pollFirst();
                if (!path.isEmpty()) {
                    ACellObject.Direction direction = convertDirection(ghost, path.getFirst());
                    ghost.setLastMove(context.getCurrentMove());
                    ghost.setNextMove(direction);
                    ghost.setCurrentMove(ghost.getNextMove());
                    ghost.computeNextLocation();
                }
            }
        }
    }

    private ACellObject.Direction convertDirection(Ghost ghost, Point target) {
        int ghostCol = (int) Math.round(ghost.getLocationX() / 31);
        int ghostRow = (int) Math.round(ghost.getLocationY() / 31);

        if (target.x - ghostCol < 0) {
            return ACellObject.Direction.LEFT;
        } else if (target.x - ghostCol > 0) {
            return ACellObject.Direction.RIGHT;
        } else if (target.y - ghostRow < 0) {
            return ACellObject.Direction.UP;
        } else if (target.y - ghostRow > 0) {
            return ACellObject.Direction.DOWN;
        }

        return ACellObject.Direction.STOP;
    }

    private Deque<Point> bfs(Ghost ghost, PacMan pacMan) {
        int ghostCol = (int) Math.round(ghost.getLocationX() / 31);
        int ghostRow = (int) Math.round(ghost.getLocationY() / 31);
        int pacmanCol = (int) Math.round(pacMan.getLocationX() / 31);
        int pacmanRow = (int) Math.round(pacMan.getLocationY() / 31);
        Point pacmanLoc = new Point(pacmanCol, pacmanRow);
//        System.out.println(pacmanLoc);
//        System.out.println(pacMan.getLocationX() + "," + pacMan.getLocationY());
//        System.out.println(ghostCol + "," + ghostRow);
//        System.out.println(ghostRow);

        Queue<Deque<Point>> queue = new LinkedList<>();

        Deque<Point> firstPath = new ArrayDeque<>();
        firstPath.add(new Point(ghostCol, ghostRow));

        queue.add(firstPath);

        Set<Point> visited = new HashSet<>();
        visited.add(firstPath.getLast());

        int[] offsetX = new int[] {0, 1, 0, -1};
        int[] offsetY = new int[] {1, 0, -1, 0};

        while (!queue.isEmpty()) {
            Deque<Point> path = queue.poll();
            Point lastPoint = path.getLast();
            if (lastPoint.equals(pacmanLoc)) {
                return path;
            }
            for (int i = 0; i < 4; i++) {
                Point newLoc = new Point(lastPoint.x + offsetX[i], lastPoint.y + offsetY[i]);
                if (visited.contains(newLoc) || outsideBoard(newLoc)) {
                    continue;
                }
                if (board[newLoc.y][newLoc.x] instanceof WallUnit
                        || board[newLoc.y][newLoc.x] instanceof DoorUnit) {
                    continue;
                }
                visited.add(newLoc);
                Deque<Point> newPath = new ArrayDeque<>(path);
                newPath.addLast(newLoc);
                queue.add(newPath);
            }
        }
        return null;
    }

    private boolean outsideBoard(Point loc) {
        int height = board.length, width = board[0].length;
        int row = loc.y, col = loc.x;
        return row < 0 || row >= height || col < 0 || col >= width;
    }
}
