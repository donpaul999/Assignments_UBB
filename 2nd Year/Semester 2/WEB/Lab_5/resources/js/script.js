$(document).ready(function() {
    $('#container').sortable({connectWith:"#right"});
    $('#right').sortable({connectWith:"#container"});
})