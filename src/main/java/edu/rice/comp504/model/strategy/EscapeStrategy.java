package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.paint.ACellObject;
import edu.rice.comp504.model.paint.Ghost;
import edu.rice.comp504.model.paint.PacMan;
import edu.rice.comp504.model.paint.WallUnit;
import gameparam.GameParam;
import gameparam.TimeCounter;

import java.util.*;

public class EscapeStrategy implements IUpdateStrategy {

    /**
     * Singleton object of EscapeStrategy class.
     */
    private static EscapeStrategy INSTANCE;

    private PacMan pacman;

    private ACellObject[][] board;

    private double endTime;

    // 0 zuoshang
    // 1 youshang
    // 2 zuoxia
    // 3 youxia
    private int escapeDir = 0;

    private Map<Ghost, Integer> map;

    private Map<Ghost, ACellObject.Direction> prevDir;

    /**
     * Constructor.
     */
    private EscapeStrategy(PacMan pacman, ACellObject[][] board, double currentTime) {
        this.pacman = pacman;
        this.board = board;
        this.endTime = currentTime + GameParam.GHOST_ESCAPE_TIME;
        map = new HashMap<>();
        prevDir = new HashMap<>();
    }

    /**
     * @return get the singleton of EscapeStrategy class.
     */
    public static EscapeStrategy getInstance(PacMan pacman, ACellObject[][] board, double currentTime) {
        if (INSTANCE == null) {
            INSTANCE = new EscapeStrategy(pacman, board, currentTime);
        }
        return INSTANCE;
    }

    public static void cleanStrategy() {
        INSTANCE = null;
    }

    @Override
    public String getName() {
        return "EscapeStrategy";
    }

    @Override
    public void updateState(ACellObject context) {
        // TODO: ghost escape away from pac man when it eats big bean
        Ghost ghost = (Ghost) context;
        if (TimeCounter.getTime() >= endTime) {
            // change to chase
            ghost.setWeak(false);
            ghost.setUpdateStrategy(ChaseStrategy.getInstance(pacman, board));
        } else {
            // escape
            scatter(ghost);
//            escape(ghost);
        }
    }

    private void scatter(Ghost ghost) {
        if (!map.containsKey(ghost)) {
            map.put(ghost, escapeDir);
            if (escapeDir == 0 || escapeDir == 1) {
                prevDir.put(ghost, ACellObject.Direction.UP);
            } else {
                prevDir.put(ghost, ACellObject.Direction.DOWN);
            }
            escapeDir = (escapeDir + 1) % 4;
        }
//        System.out.println("start scatter");

        int dir = map.get(ghost);
        ACellObject.Direction prev = prevDir.get(ghost);
        if (ghost.getCurrentMove() == ACellObject.Direction.STOP) {
            if (dir == 0) {
                if (prev == ACellObject.Direction.UP) {
                    prev = ACellObject.Direction.LEFT;
                } else if (prev == ACellObject.Direction.LEFT) {
                    if(Math.random() > 0.5) {
                        prev = ACellObject.Direction.DOWN;
                    } else {
                        prev = ACellObject.Direction.RIGHT;
                    }
                } else {
                    prev = ACellObject.Direction.UP;
                }
            } else if (dir == 1) {
                if (prev == ACellObject.Direction.UP) {
                    prev = ACellObject.Direction.RIGHT;
                } else if (prev == ACellObject.Direction.RIGHT) {
                    if(Math.random() > 0.5) {
                        prev = ACellObject.Direction.DOWN;
                    } else {
                        prev = ACellObject.Direction.LEFT;
                    }
                } else {
                    prev = ACellObject.Direction.UP;
                }
            } else if (dir == 2) {
                if (prev == ACellObject.Direction.DOWN) {
                    prev = ACellObject.Direction.LEFT;
                } else if (prev == ACellObject.Direction.LEFT) {
                    if(Math.random() > 0.5) {
                        prev = ACellObject.Direction.UP;
                    } else {
                        prev = ACellObject.Direction.RIGHT;
                    }
                } else {
                    prev = ACellObject.Direction.DOWN;
                }
            } else {
                if (prev == ACellObject.Direction.DOWN) {
                    prev = ACellObject.Direction.RIGHT;
                } else if (prev == ACellObject.Direction.RIGHT) {
                    if(Math.random() > 0.5) {
                        prev = ACellObject.Direction.UP;
                    } else {
                        prev = ACellObject.Direction.LEFT;
                    }
                } else {
                    prev = ACellObject.Direction.DOWN;
                }
            }
        } else {
            if (dir == 0) {
                prev = ACellObject.Direction.UP;
            } else if (dir == 1) {
                prev = ACellObject.Direction.UP;
            } else if (dir == 2) {
                prev = ACellObject.Direction.DOWN;
            } else {
                prev = ACellObject.Direction.DOWN;
            }
        }
        prevDir.put(ghost, prev);
//        System.out.println("set next move");
        ghost.setLastMove(ghost.getCurrentMove());
        ghost.setNextMove(prev);
        ghost.setCurrentMove(ghost.getNextMove());
        ghost.computeNextLocation();
    }

    private void escape(Ghost ghost) {
        double ghostX = ghost.getLocationX();
        double ghostY = ghost.getLocationY();
        double ghostVel = GameParam.ghostSpeed;
        List<ACellObject> walls = generateNeighborWall((int) ghostX / GameParam.pixelPerUnit, (int) ghostY / GameParam.pixelPerUnit);
        ACellObject.Direction direction = ACellObject.Direction.RIGHT;
        TreeMap<Double, ACellObject.Direction> map = new TreeMap<>();
        // up
        ghost.setLastMove(ghost.getCurrentMove());
        ghost.setNextMove(ACellObject.Direction.UP);
        ghost.setCurrentMove(ghost.getNextMove());
        ghost.computeNextLocation();
        if (!overlapWithWall(ghost, walls)) {
            map.put(distance(ghost, pacman), ACellObject.Direction.UP);
        }
        // revert
        ghost.revertLocation();
        ghost.setNextMove(ghost.getCurrentMove());
        ghost.setCurrentMove(ghost.getLastMove());

        // down
        ghost.setLastMove(ghost.getCurrentMove());
        ghost.setNextMove(ACellObject.Direction.DOWN);
        ghost.setCurrentMove(ghost.getNextMove());
        ghost.computeNextLocation();
        if (!overlapWithWall(ghost, walls)) {
            map.put(distance(ghost, pacman), ACellObject.Direction.DOWN);
        }
        // revert
        ghost.revertLocation();
        ghost.setNextMove(ghost.getCurrentMove());
        ghost.setCurrentMove(ghost.getLastMove());

        // right
        ghost.setLastMove(ghost.getCurrentMove());
        ghost.setNextMove(ACellObject.Direction.RIGHT);
        ghost.setCurrentMove(ghost.getNextMove());
        ghost.computeNextLocation();
        if (!overlapWithWall(ghost, walls)) {
            map.put(distance(ghost, pacman), ACellObject.Direction.RIGHT);
        }
        // revert
        ghost.revertLocation();
        ghost.setNextMove(ghost.getCurrentMove());
        ghost.setCurrentMove(ghost.getLastMove());

        // left
        ghost.setLastMove(ghost.getCurrentMove());
        ghost.setNextMove(ACellObject.Direction.LEFT);
        ghost.setCurrentMove(ghost.getNextMove());
        ghost.computeNextLocation();
        if (!overlapWithWall(ghost, walls)) {
            map.put(distance(ghost, pacman), ACellObject.Direction.LEFT);
        }
        // revert
        ghost.revertLocation();
        ghost.setNextMove(ghost.getCurrentMove());
        ghost.setCurrentMove(ghost.getLastMove());

        if (!map.isEmpty()) {
            direction = map.lastEntry().getValue();
        }
        ghost.setLastMove(ghost.getCurrentMove());
        ghost.setNextMove(direction);
        ghost.setCurrentMove(ghost.getNextMove());
        ghost.computeNextLocation();
    }

    private double distance(Ghost ghost, PacMan pacMan) {
        return Math.pow(ghost.getLocationX() - pacMan.getLocationX(), 2)
                + Math.pow(ghost.getLocationY() - pacMan.getLocationY(), 2);
    }

    private boolean overlapWithWall(Ghost ghost, List<ACellObject> walls) {
        for (ACellObject wall : walls) {
            if (ghost.isOverlap(wall)) return true;
        }
        return false;
    }

    private List<ACellObject> generateNeighborWall(int x, int y) {
        List<ACellObject> res = new ArrayList<>();
        if (x - 1 >= 0 && isWall(board[x - 1][y].getType())) {
            res.add(board[x - 1][y]);
        }
        if (x + 1 < GameParam.unitPerCol && isWall(board[x + 1][y].getType())) {
            res.add(board[x + 1][y]);
        }
        if (y - 1 >= 0 && isWall(board[x][y - 1].getType())) {
            res.add(board[x][y - 1]);
        }
        if (y + 1 < GameParam.unitPerRow && isWall(board[x][y + 1].getType())) {
            res.add(board[x][y + 1]);
        }
        return res;
    }

    private boolean isWall(String type) {
        return "DoorUnit".equals(type) || "WallUnit".equals(type);
    }
}
