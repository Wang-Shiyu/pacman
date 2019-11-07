package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;
import gameparam.GameParam;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.font.GlyphMetrics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The abstract class for all objects used in the Pacman game.
 * It contains properties that apply to all game objects.
 */
public abstract class ACellObject implements PropertyChangeListener {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, STOP
    }

    private String imageIcon;
    // score for the food
    private int score;
    private String type;


    // Position related parameters
    private double locationX;
    private double locationY;
    private double preLocationX;
    private double preLocationY;
    @Getter
    private double initX;
    @Getter
    private double initY;
    @Getter
    @Setter
    private double vel;

    private Direction nextMove;
    private Direction lastMove;
    private Direction currentMove;

    @Getter
    @Setter
    private IUpdateStrategy updateStrategy;

    private int timeCounter;

    /**
     * Constructor.
     *
     * @param imageIcon      imageIcon
     * @param score          score
     * @param type           type
     * @param locationX      locationX
     * @param locationY      locationY
     * @param vel            velocity
     * @param updateStrategy strategy
     */
    public ACellObject(String imageIcon, int score, String type, double locationX, double locationY,
                       double vel, IUpdateStrategy updateStrategy) {
        this.imageIcon = imageIcon;
        this.score = score;
        this.type = type;
        this.locationX = locationX;
        this.locationY = locationY;
        this.vel = vel;
        this.updateStrategy = updateStrategy;
        this.initX = locationX;
        this.initY = locationY;
        this.nextMove = Direction.STOP;
        this.currentMove = Direction.STOP;
        this.lastMove = Direction.STOP;
    }

    public abstract boolean isOverlap(ACellObject object);

    public void setLocation(double x, double y) {
        locationX = x;
        locationY = y;
    }

    // Reset location , time counter
    public abstract void reset();


    public double getLocationX() {
        return this.locationX;
    }

    public double getLocationY() {
        return this.locationY;
    }

    public void  revertLocation() {
        this.setLocation(preLocationX, preLocationY);
    }

    /**
     * Make a move according to the current moving direction.
     * This method also store the last valid location before the move.
     */
    public void computeNextLocation() {
        /*
        Store the last valid location in case of reverting it.
         */
        preLocationX = locationX;
        preLocationY = locationY;

        /*
        Make a move according to the current moving direction
         */
        switch (currentMove) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            default: break;
        }
        roundupLocation();
        // check hole
        if (this.getLocationX() > GameParam.pixelPerUnit * GameParam.unitPerCol && getCurrentMove() == Direction.RIGHT) {
            setLocation(0, 31);
        } else if (this.getLocationX() < - GameParam.pixelPerUnit / 2.0 && getCurrentMove() == Direction.LEFT) {
            setLocation(GameParam.pixelPerUnit * (GameParam.unitPerCol - 1), 15 * GameParam.pixelPerUnit);
        }
    }

    private void roundupLocation() {
        double x = Math.round(this.getLocationX() * 100.0) / 100.0;
        double y = Math.round(this.getLocationY() * 100.0) / 100.0;
        setLocation(x, y);
    }

    public void moveUp() {
        this.locationY -= vel;
    }

    public void moveDown() {
        this.locationY += vel;
    }

    public void moveLeft() {
        this.locationX -= vel;
    }

    public void moveRight() {
        this.locationX += vel;
    }
    // location control end

    // Score getter/setter
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Time counter getter/setter
    public int getTimeCounter() {
        return this.timeCounter;
    }

    public void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }

    public Direction getNextMove() {
        return nextMove;
    }

    public void setNextMove(Direction nextMove) {
        this.nextMove = nextMove;
    }

    public Direction getLastMove() {
        return lastMove;
    }

    public void setLastMove(Direction lastMove) {
        this.lastMove = lastMove;
    }

    public Direction getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(Direction currentMove) {
        this.currentMove = currentMove;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IUpdateStrategy getUpdateStrategy() {
        return updateStrategy;
    }

    public void setUpdateStrategy(IUpdateStrategy updateStrategy) {
        this.updateStrategy = updateStrategy;
    }
}
