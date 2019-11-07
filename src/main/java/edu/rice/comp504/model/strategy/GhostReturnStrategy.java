package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.DoorUnit;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.WallUnit;

import java.awt.*;
import java.util.*;

public class GhostReturnStrategy implements IUpdateStrategy {
    private static DoorUnit doorUnit;
    private static ACellObject[][] board;
    /**
     * Constructor.
     */
    public GhostReturnStrategy(DoorUnit doorUnit, ACellObject[][] board) {
        GhostReturnStrategy.doorUnit = doorUnit;
        GhostReturnStrategy.board = board;
    }

    private Deque<Point> eyeBack(DoorUnit doorUnit, Ghost ghost) {
        int ghostCol = (int) Math.round(ghost.getLocationX() / 31);
        int ghostRow = (int) Math.round(ghost.getLocationY() / 31);
        int doorCol = (int) Math.round(doorUnit.getLocationX() / 31);
        int doorRow = (int) Math.round(doorUnit.getLocationY() / 31);
        Point door = new Point(doorCol, doorRow);
        Queue<Deque<Point>> queue = new LinkedList<>();

        int[] offsetX = new int[] {0, 1, 0, -1};
        int[] offsetY = new int[] {1, 0, -1, 0};

        Deque<Point> firstPath = new ArrayDeque<>();
        firstPath.add(new Point(ghostCol, ghostRow));

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
                if (board[newLoc.y][newLoc.x] instanceof WallUnit
                        || board[newLoc.y][newLoc.x] instanceof DoorUnit) {
                    continue;
                }
                visited.add(newLoc);
                Deque<Point> newPath = new ArrayDeque<>();
                newPath.addAll(path);
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

    @Override
    public String getName() {
        return "GhostReturn";
    }

    @Override
    public void updateState(ACellObject context) {
        if (context instanceof Ghost) {
            Ghost ghost = (Ghost) context;
            Deque<Point> path = eyeBack(doorUnit, ghost);
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
}
