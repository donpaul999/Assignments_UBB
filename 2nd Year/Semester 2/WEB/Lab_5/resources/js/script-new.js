$(document).ready(function() {
    removed = '';
    removed_id = '';
    $(".draggable-handler")
        .on('dragstart', function (){
            $("#text").text("Drag start!" + this.id);
            removed = $(this).get();
            removed_id = $(this).attr('id');
            //$(this).css('opacity', '0.01');
            $(this).fadeTo(200, 0);
           // console.log(1);
        })
        .on('dragend', function (){
            $("#text").text("Drag end!");
            //console.log(removed);
            $('#' + removed_id).css('opacity', '1');
            removed = '';
            removed_id = '';
        })
        .on('dragover', function (){
            //console.log(removed_id);
            $("#text").text("Drag over!" + this.id);
            if(removed_id < this.id)
                $('#' + removed_id).before($(this));
            else
                $(this).before($('#' + removed_id));
            //$(this).stop(true,true).animate({top:'-20px'},200);

            // console.log(1);
        })
        .on('drop', function (){
            $("#text").text("Drop!");
            // console.log(1);
        })
});

//drag start, drag over