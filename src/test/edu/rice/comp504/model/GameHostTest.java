package edu.rice.comp504.model;

import gameparam.TimeCounter;
import junit.framework.TestCase;

import java.beans.PropertyChangeListener;

public class GameHostTest extends TestCase {

    public void testInitGame() {
        GameHost gh = new GameHost();
        gh.initGame();
    }

    public void testResetGame() {
        GameHost gh = new GameHost();
        gh.initGame();
        gh.resetGame();
    }

    public void testUpdatePacmanWorld() {
        GameHost gh = new GameHost();
        gh.startGame("123456");
        gh.updatePanManWorld();
        gh.getPacMan().setRemainingLife(0);
        gh.updatePanManWorld();
    }

    public void testWinUpdatePacmanWorld() {
        GameHost gh = new GameHost();
        gh.startGame("123456");
        gh.getPacMan().setRemainingLife(3);
        gh.setLevel(3);
        for (PropertyChangeListener listener : gh.getPcs().getPropertyChangeListeners("Food")) {
            gh.getPcs().removePropertyChangeListener("Food", listener);
        }
        gh.updatePanManWorld();
    }

    public void testGameLevelUp() {
        GameHost gh = new GameHost();
        gh.startGame("123456");
        gh.setGameStatus(GameHost.Status.PASS);
        gh.startGame("123456");
        gh.setGameStatus(GameHost.Status.OVER);
        gh.startGame("123456");
        gh.setGameStatus(GameHost.Status.WIN);
        gh.startGame("123456");
        gh.setGameStatus(GameHost.Status.UNKNOWN);
    }

    public void testMove() {
        String direction = "1=up";
        GameHost gh = new GameHost();
        gh.startGame("123456");
        gh.move(direction);

        direction = "1=down";
        gh.move(direction);

        direction = "1=left";
        gh.move(direction);

        direction = "1=right";
        gh.move(direction);

        direction = "1=stop";
        gh.move(direction);

        gh.setGameStatus(GameHost.Status.OVER);
        gh.move(direction);
    }
}