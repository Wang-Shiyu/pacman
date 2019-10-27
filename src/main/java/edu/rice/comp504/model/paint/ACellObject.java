package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ACellObject implements PropertyChangeListener {

    enum Direction {
        UP, DOWN, LEFT, RIGHT, STOP;
    };

    private String imageIcon;
    // score for the food
    private int score;
    private String type;


    // Position related parameters
    private double locationX;
    private double locationY;
    private double preLocationX;
    private double preLocationY;
    private double initX;
    private double initY;
    private double vel;

    private Direction nextMove, lastMove, currentMove;

    /**
     * update strategy
     */
    private IUpdateStrategy updateStrategy;

    private int timeCounter;

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

    public void revertLocation() {
        this.setLocation(preLocationX, preLocationY);
    }

    public void computeNextLocation(double velX, double velY) {
        this.locationX += velX;
        this.locationY += velY;
    }

    public void moveUp() {

    }

    public void moveDown() {

    }

    public void moveLeft() {
    }

    public void moveRight() {
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
}
