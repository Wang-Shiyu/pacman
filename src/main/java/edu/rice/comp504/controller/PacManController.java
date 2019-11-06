package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.GameHost;

import static spark.Spark.*;


/**
 * The Pac man controller creates the adapter(game board) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(game board).
 */
public class PacManController {

    /**
     * The main entry point into the program.
     *
     * @param args The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFiles.location("/public");

        Gson gson = new Gson();
        GameHost game = new GameHost();

        get("/update", (request, response) -> {
            return gson.toJson(game.updatePanManWorld());
        });

        post("/start", (request, response) -> {
            return gson.toJson(game.startGame(request.body()));
        });

        get("/reset", (request, response) -> {
            game.resetGame();
            return "OK";
        });

        post("/inputevent", (request, response) -> {
            game.move(request.body());
            return "OK";
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
