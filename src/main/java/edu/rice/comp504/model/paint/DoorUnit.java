package edu.rice.comp504.model.paint;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

/**
 * The door that imprison ghosts.
 */
public class DoorUnit extends WallUnit {
    public DoorUnit(String imageIcon, int score, String type, double locationX, double locationY, double vel, IUpdateStrategy updateStrategy) {
        super(imageIcon, score, "", locationX, locationY, vel, updateStrategy);
        setType("DoorUnit");
    }
}
