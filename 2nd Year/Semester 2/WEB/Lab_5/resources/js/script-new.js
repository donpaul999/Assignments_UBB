$(document).ready(function() {
    isDragging = false;
    $("div")
        .mousedown(function () {
            isDragging = false;
        })
        .mousemove(function () {
            isDragging = true;
        })
        .mouseup(function () {
            var wasDragging = isDragging;
            isDragging = false;
            if (!wasDragging) {
                console.log("false");
            } else {
                console.log("true");
            }
        });
});