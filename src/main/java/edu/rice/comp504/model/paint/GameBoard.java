package edu.rice.comp504.model.paint;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;

/**
 * Use composite design pattern to store all stationary paint object
 * also store all the data we need to render frontend UI(board, score, life)
 */
public class GameBoard implements PropertyChangeListener {

    /**
     * Singleton object of current class.
     */
    private static GameBoard INSTANCE;

    /**
     * @return get the singleton of the class.
     */
    public static GameBoard getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameBoard();
        }
        return INSTANCE;
    }

    /**
     * Constructor.
     */
    private GameBoard() {
        board = new APaintObject[25][25];
        init();
    }

    private APaintObject[][] board;

    private int score;

    private int life;

    private int level;

    private boolean isWin;

    private boolean isLose;

    private boolean isStart;

    private void init() {
        // TODO: init all items in the board: wall, food, big food and null
        File file = getFileFromResources("public/maze.txt");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line;
            int row = 0, col = 0;
            while ((line = br.readLine()) != null) {
                col = 0;
                for (char c : line.toCharArray()) {
                    if (c == '1') {
                        board[row][col] = new WallUnit(new Point(row, col), null, null);
                    } else {
                        board[row][col] = new Food(new Point(row, col), null, null);
                    }
                    col++;
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO: init score, life

        isStart = false;
    }

    public void reset() {
        init();
    }

    public APaintObject eatFood(boolean isBigFood, int row, int col) {
        // TODO: remove food
        // TODO: update score
        // TODO: return the eaten food
        return null;
    }

    public void decreaseLife() {
        life--;
    }

    public APaintObject[][] getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public int getLife() {
        return life;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isWin() {
        return isWin;
    }

    public boolean isLose() {
        return isLose;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean detectWall(AMoveObject obj) {
        return false;
    }

    public APaintObject detectFood(AMoveObject obj) {
        // TODO: if true, eatFood();
        // TODO: return food obj or null
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // do nothing
    }

    // get file from classpath, resources folder
    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }
}
