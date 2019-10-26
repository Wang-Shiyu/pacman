package edu.rice.comp504.controller;

import com.google.gson.Gson;
import edu.rice.comp504.model.DispatcherAdapter;
import edu.rice.comp504.model.paint.GameBoard;

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
        staticFiles.location("/public");

        Gson gson = new Gson();
        DispatcherAdapter dis = new DispatcherAdapter();

        post("/move/:direction", (request, response) -> {
            dis.move(request.params(":direction"));
            return "OK";
        });

        get("/update", (request, response) -> {
            return gson.toJson(dis.updatePacMamWorld());
        } );

        get("/load", (request, response) -> {
            return gson.toJson(dis.initGame());
        });

        get("/start", (request, response) -> {
            dis.startGame();
            return "OK";
        });

    }
}
