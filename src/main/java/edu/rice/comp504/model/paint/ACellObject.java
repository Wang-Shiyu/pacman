package edu.rice.comp504.model.paint;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ACellObject implements PropertyChangeListener {

    private String imageIcon;
    private int score;
    private String type;


    // Position related parameters
    private double locationX;
    private double locationY;
    private double preLocationX;
    private double preLocationY;
    private double velX;
    private double velY;


    private int timeCounter;


    public void setLocation(double x, double y) {
        locationX = x;
        locationY = y;
    }

    // Rest location , time counter
    public void reset(){

    }


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
    public int getScore(){
        return this.score;
    }

    public void setScore(int score){
        this.score = score;
    }

    // Time counter getter/setter
    public int getTimeCounter(){
        return this.timeCounter;
    }

    public void setTimeCounter(int timeCounter){
        this.timeCounter = timeCounter;
    }



}
