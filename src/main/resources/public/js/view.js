'use strict';

//app to draw polymorphic shapes on canvas
var app;

const pixelPerUnit = 31;
const fps = 60;
var previousDir = 0;

var intervalId;

var ghostImg, pacmanImg, cherryImg, strawberryImg;

/**
 * Create the paint object world app for a canvas
 * @param canvas The canvas to draw paintable canvas objects on
 * @returns {{dims: {width: *, height: *}, drawCircle: *, clear: *, drawPacManWorld: *}}
 */
function createApp(canvas) {
    const c = canvas.getContext("2d");

    /**
     * Draw a circle
     * @param x  The x location coordinate
     * @param y  The y location coordinate
     * @param radius  The circle radius
     * @param color The circl color
     */
    const drawCircle = function (x, y, radius, color) {
        c.fillStyle = color;
        c.beginPath();
        c.arc(x, y, radius, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    };

    const updatePacManWorld = function (data) {
        clear();
        drawPacManWorld(data);
    };

    const drawPacManWorld = function (data) {
        if (data.status === "START") {
            drawGameBoard(data.list);
            updateScore(data.score);
        }
        updateLevel(data.level);
        updateLife(data.remainingLife);
    };

    const drawGameBoard = function (data) {
        data.forEach((item) => {
            const coordinate = item.coordinate;
            const x = item.locationX * pixelPerUnit;
            const y = item.locationY * pixelPerUnit;
            if (item.type === "Food") {
                if (item.bigFood) {
                    drawBigFood(x + (pixelPerUnit + 1) / 2, y + (pixelPerUnit + 1) / 2);
                } else if (item.fruit) {
                    console.log(item.fruit);
                    drawImage(cherryImg, data.locationX, data.locationY, 0);
                } else {
                    drawFood(x + (pixelPerUnit + 1) / 2, y + (pixelPerUnit + 1) / 2);
                }
            } else if (item.type === "WallUnit") {
                drawWall(x, y, 'blue');
            } else if (item.type === "DoorUnit") {
                drawWall(x, y, 'white');
            } else if (item.type === "PacMan") {
                drawPacMan(item);
            } else if (item.type === "Ghost") {
                drawGhost(item);
            }
        });
    };

    const updateScore = function (data) {
        $("#score").text("Score: " + data);
    };

    const updateLife = function (data) {
        $("#lifeno").text("Number of Lives Left: " + data);
    };

    const updateLevel = function (data) {
        $("#level").text("Level :" + data);
    };

    const drawGhost = function (data) {
        drawImage(ghostImg, data.locationX, data.locationY, 0);
    };

    const drawPacMan = function (data) {
        if(data.currentMove === 'RIGHT') {
            drawImage(pacmanImg, data.locationX, data.locationY, 0);
            previousDir = 0;
        } else if(data.currentMove === 'LEFT') {
            drawImage(pacmanImg, data.locationX, data.locationY, 90);
            previousDir = 90;
        } else if(data.currentMove === 'UP') {
            drawImage(pacmanImg, data.locationX, data.locationY, 135);
            previousDir = 135;
        } else if(data.currentMove === 'DOWN') {
            drawImage(pacmanImg, data.locationX, data.locationY, 45);
            previousDir = 45;
        } else {
            drawImage(pacmanImg, data.locationX, data.locationY, previousDir);
        }
    };

    const drawWall = function (x, y, color) {
        c.fillStyle = color;
        c.fillRect(x, y, pixelPerUnit, pixelPerUnit);
    };

    const drawFood = function (x, y) {
        drawCircle(x, y, 3, "white");
    };

    const drawBigFood = function (x, y) {
        drawCircle(x, y, 8, "white");
    };

    const drawImage = function (img, x, y, dir) {
        let angle = Math.PI * dir / 2;
        c.save();
        c.translate(x + 15.5, y + 15.5);
        c.rotate(angle);
        if (angle > Math.PI / 2 && angle < Math.PI * 3 / 2) c.scale(1, -1);
        c.drawImage(img, -15.5, -15.5, 31, 31);
        c.restore();
    };

    let clear = function () {
        c.fillStyle = $('#bgColor').val().toString();;
        c.fillRect(0, 0, canvas.width, canvas.height);
        // c.clearRect(0, 0, canvas.width, canvas.height);
    };


    return {
        drawCircle: drawCircle,
        drawPacManWorld: drawPacManWorld,
        drawImage: drawImage,
        updatePacManWorld: updatePacManWorld,
        clear: clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}


window.onload = function () {
    ghostImg = new Image();
    pacmanImg = new Image();
    cherryImg = new Image();
    strawberryImg = new Image();

    ghostImg.src = "pinkGhost.gif";
    pacmanImg.src = "pacman.png";
    cherryImg.src = "cherry.png";
    strawberryImg.src = "strawberry.png";

    app = createApp(document.querySelector("canvas"));

    app.clear();

    $("#btn-start").click(startGame);
    $("#btn-reset").click(resetGame);
};

/**
 * load game
 */
function loadGame() {
    $.get("/init", function (data) {
        app.drawPacManWorld(data);
    }, "json");
}


/**
 * start game
 */
function startGame() {
    let life = $('#Life').val().toString();
    $.post("/start", {life: life}, function (data) {
        app.drawPacManWorld(data);
        if (intervalId == null) {
            intervalId = setInterval(updatePacManWorld, 1000 / fps);
        }
        monitorInput();
    }, "json");
}

/**
 * reset game
 */
function resetGame() {
    $.get("/reset", function (data) {
    }, "json");
}


/**
 *  Update the balls in the ball world.
 */
function updatePacManWorld() {
    $.get("/update", function (data) {
        if(data.status === "START" || data.status === "INIT") {
            if(data.status === "INIT") {
                // to make pacman head right after reset
                previousDir = 0;
            }
            app.updatePacManWorld(data);
        } else {
            previousDir = 0;
        }
    }, "json");
}

/**
 * Clear the canvas.
 */
function clear() {
    $.get("/clear");
    app.clear();
}

/**
 * send user input.
 */
function send(direction) {
    $.post("/inputevent", {direction: direction}, function (data) {
    }, "json");
}

/**
 * Monitor input event
 */
function monitorInput() {
    $("body").keydown(function (event) {
        switch (event.keyCode) {
            case 37:
                send("left");
                break;
            case 38:
                send("up");
                break;
            case 39:
                send("right");
                break;
            case 40:
                send("down");
                break;
        }
    });
}