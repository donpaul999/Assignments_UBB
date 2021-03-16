$(document).ready(function() {
    removed = '';
    removed_id = '';
    $(".draggable-handler")
        .on('dragstart', function (){
            $("#text").text("Drag start!" + this.id);
            removed = $(this).get();
            removed_id = $(this).attr('id');
            $(this).css('display', 'none');
           // console.log(1);
        })
        .on('dragend', function (){
            console.log(removed);
            $("#text").text("Drag end!");
            $("#" + removed_id).detach();
            $(this).stop(true,true).animate({top:'20px'},200);
            //TODO - FIX THIS
            $(removed).insertAfter(this);
            // console.log(1);
        })
        .on('dragover', function (){
            $("#text").text("Drag over!" + this.id);
            $(this).stop(true,true).animate({top:'-20px'},200);

            // console.log(1);
        })
        .on('drop', function (){
            $("#text").text("Drop!");
            // console.log(1);
        })
});

//drag start, drag over