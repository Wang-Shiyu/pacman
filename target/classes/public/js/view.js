'use strict';

//app to draw polymorphic shapes on canvas
var app;

const pixelPerUnit = 31;

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
        data.forEach(item => {
        });
    };

    const drawPacManWorld = function (data) {
        // TODO: check is start
        drawGameBoard(data[0]);
        drawImage(ghostImg,155, 31, 0);
        drawImage(ghostImg,217, 31, 0);
        drawImage(pacmanImg,93, 31, 0);
        drawImage(cherryImg,62, 93, 0);
        drawImage(cherryImg,248, 155, 0);
        drawImage(cherryImg,527, 341, 0);
        drawImage(cherryImg,217, 465, 0);
    };

    const drawGameBoard = function (data) {
        data.board.forEach((line) => {
            line.forEach((item) => {
                const coordinate = item.coordinate;
                const x = coordinate.x * pixelPerUnit;
                const y = coordinate.y * pixelPerUnit;
                if (item.type === "Food") {
                    drawFood(y + (pixelPerUnit + 1) / 2, x + (pixelPerUnit + 1) / 2);
                } else if (item.type === "WallUnit") {
                    drawWall(y, x);
                }
            });
        });
    };

    const drawWall = function (x, y) {
        c.fillStyle = "blue";
        c.fillRect(x, y, pixelPerUnit, pixelPerUnit);
    };

    const drawFood = function (x, y) {
        drawCircle(x, y, 3, "white");
    };

    const drawImage = function (img, x, y, dir) {
        let angle = Math.PI * dir / 2;
        c.save();
        c.translate(x + 15.5,y + 15.5);
        c.rotate(angle);
        if (angle > Math.PI / 2 && angle < Math.PI * 3 / 2) c.scale(1, -1);
        c.drawImage(img, -15.5, -15.5, 31, 31);
        c.restore();
    }

    let clear = function () {
        c.fillStyle = "black";
        c.fillRect(0, 0, canvas.width, canvas.height);
        // c.clearRect(0, 0, canvas.width, canvas.height);
    };


    return {
        drawCircle: drawCircle,
        drawPacManWorld: drawPacManWorld,
        drawImage: drawImage,
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

    clear();
    setInterval(updatePacManWorld, 100);
    loadGame();
};

/**
 * load game
 */
function loadGame() {
    $.get("/load", function (data) {
        app.drawPacManWorld(data);
    }, "json");
}

/**
 *  Update the balls in the ball world.
 */
function updatePacManWorld() {
    $.get("/update", function (data) {
        // app.updatePacManWorld(data);
    }, "json");
}

/**
 * Clear the canvas.
 */
function clear() {
    $.get("/clear");
    app.clear();
}