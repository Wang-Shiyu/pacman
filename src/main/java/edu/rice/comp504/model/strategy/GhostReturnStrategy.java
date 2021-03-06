package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.*;
import gameparam.GameParam;
import lombok.Getter;

import java.awt.*;
import java.util.*;

/**
 * handle eye back to jail.
 */
public class GhostReturnStrategy implements IUpdateStrategy {
    private static Point door;

    @Getter
    private ACellObject[][] board;

    @Getter
    private PacMan pacMan;

    private Deque<Point> cachePath;
    private boolean hasPath;

    /**
     * Constructor.
     */
    public GhostReturnStrategy(Point door, PacMan pacMan, ACellObject[][] board) {
        GhostReturnStrategy.door = new Point(door.x / 31, door.y / 31);
        this.board = board;
        this.pacMan = pacMan;
        this.hasPath = false;
    }

    private Deque<Point> eyeBack(Point door, Ghost ghost) {

        int ghostCol = (int) Math.round(ghost.getLocationX() / 31);
        int ghostRow = (int) Math.round(ghost.getLocationY() / 31);
        Queue<Deque<Point>> queue = new LinkedList<>();

        int[] offsetX = new int[]{0, 1, 0, -1};
        int[] offsetY = new int[]{1, 0, -1, 0};

        Deque<Point> firstPath = new ArrayDeque<>();
        firstPath.add(new Point(ghostCol, ghostRow));
//        System.out.println(new Point(ghostCol, ghostRow));
//        System.out.println(door);
        queue.add(firstPath);
        Set<Point> visited = new HashSet<>();
        visited.add(firstPath.getLast());

        while (!queue.isEmpty()) {
            Deque<Point> path = queue.poll();
            Point lastPoint = path.getLast();
            if (lastPoint.equals(door)) {
                return path;
            }
            for (int i = 0; i < 4; i++) {
                Point newLoc = new Point(lastPoint.x + offsetX[i], lastPoint.y + offsetY[i]);
                if (visited.contains(newLoc) || outsideBoard(newLoc)) {
                    continue;
                }
                if ("WallUnit".equals(board[newLoc.y][newLoc.x].getType())) {
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
        int height = board.length;
        int width = board[0].length;
        int row = loc.y;
        int col = loc.x;
        return row < 0 || row >= height || col < 0 || col >= width;
    }

//    private ACellObject.Direction convertDirection(Ghost ghost, Point target) {
//        int ghostCol = (int) Math.round(ghost.getLocationX() / 31);
//        int ghostRow = (int) Math.round(ghost.getLocationY() / 31);
//
//        if (target.x - ghostCol < 0) {
//            return ACellObject.Direction.LEFT;
//        } else if (target.x - ghostCol > 0) {
//            return ACellObject.Direction.RIGHT;
//        } else if (target.y - ghostRow < 0) {
//            return ACellObject.Direction.UP;
//        } else if (target.y - ghostRow > 0) {
//            return ACellObject.Direction.DOWN;
//        }
//
//        return ACellObject.Direction.STOP;
//    }

    @Override
    public String getName() {
        return "GhostReturn";
    }

    @Override
    public void updateState(ACellObject context) {
        if (context instanceof Ghost) {
            Ghost ghost = (Ghost) context;
            if (ghost.getLocationX() == GameParam.GHOST_INIT_X[2] && ghost.getLocationY() == GameParam.GHOST_INIT_Y) {
                ghost.setReturning(false);
                ghost.setWeak(false);
                ghost.setUpdateStrategy(new GhostInitStrategy(GameParam.DOOR_Y - GameParam.pixelPerUnit, pacMan, board));
            } else {
                if (!hasPath) {
                    this.cachePath = eyeBack(door, ghost);
                    hasPath = true;
                }
                if (cachePath != null) {
                    cachePath.pollFirst();
                    if (!cachePath.isEmpty()) {
                        Point p = cachePath.getFirst();
                        ghost.setLocation(p.x * 31, p.y * 31);
//                        ACellObject.Direction direction = convertDirection(ghost, cachePath.getFirst());
//                        ghost.setLastMove(context.getCurrentMove());
//                        ghost.setNextMove(direction);
//                        ghost.setCurrentMove(ghost.getNextMove());
//                        ghost.computeNextLocation();
                    }
                }
            }

        }
    }
}
