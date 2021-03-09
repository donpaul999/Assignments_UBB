var values = [];
var emptyPosition = 15;
function init() {
    for(i = 0; i < 4; ++i)
        for(j = 0; j < 4; ++j) {
            piece = new Image();
            piece.src = "resources/img/" + i + '-' + j + ".jpg";
            piece.className = "piece";
            piece.id = 4 * i + j;
            piece.onclick = function() {
                pieceClick(this);
            }
            values.push(piece);
        }

    values[15].src = "resources/img/white.png";

    randomize(values);
    showGame();
}

function randomize(values) {
    for(i = values.length; i > 0; i--) {
        j = Math.floor(Math.random() * 15);
        aux = values[i - 1];
        values[i - 1] = values[j];
        values[j] = aux;
    }
    emptyPosition = findEmptyPosition(values);
}

function findEmptyPosition(values) {
    for(i = 0; i < values.length; ++i) {
        if(values[i].id == 15) {
            return i;
        }
    }
}

function showGame() {
    //console.log("*********");
    var isFinished = true;
    document.getElementById('game').innerHTML = "";
    for(i = 0; i < values.length; ++i) {
        //console.log(values[i]);
        if(values[i].id != i) {
            isFinished = false;
        }
        document.getElementById('game').appendChild(values[i]);
    }

    if(isFinished) {
        document.getElementById("status").innerText = "The game is finished!";
    }
}

function pieceClick(piece) {
    //console.log(emptyPosition);
    piecePosition = values.indexOf(piece);
    aux = values[piecePosition];
    values[piecePosition] = values[emptyPosition];
    values[emptyPosition] = aux;
    emptyPosition = piecePosition;
    showGame();
}