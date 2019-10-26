package edu.rice.comp504.model.paint;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ACellObject implements PropertyChangeListener {
    protected Point loc;
    protected int score;
    private String imageIcon;

    protected ACellObject(String src){
        this.imageIcon = src;
        this.score = 0;


    }

    public Point getLoc() {
        return loc;
    }

    public void setLoc(Point loc) {
        this.loc = loc;
    }
}
