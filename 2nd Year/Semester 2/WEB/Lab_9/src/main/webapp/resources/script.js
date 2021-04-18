$(document).ready(function() {
    given_id = 0;
    selected = [];
    old_id = -1;
    $(".selected-cities").on('click', '.remove-selected-city', function (event) {
        old_id = event.target.parentElement.id;
        $('#myModal').modal('show');
    });

    $(".close-delete").on('click', function (event){
        $('#myModal').modal('hide');
    });

    $(".accept-delete").on('click', function (event){
        for (var i = 0; i < selected.length; ++i) {
            if (selected[i].given_id >= old_id) {
                selected.splice(i, 1);
                i--;
            }
        }
        $('#myModal').modal('hide');
        $(".cities-list").empty();
        $(".selected-cities").empty();
        for (var i = 1; i <= selected.length; ++i) {
            $(".selected-cities").append("<li id='" + selected[i - 1].given_id + "' data-id='" + selected[i - 1].id + "' data-name= '" + selected[i - 1].name + "' class='list-group-item selected-city-box'>" + i + ". "+ selected[i - 1].name + "<button class='float-end badge bg-danger remove-selected-city'>X</button></li>");
        }
        //console.log(selected.length);
        if(selected.length > 0) {
            $.get("get-cities", {id: selected[selected.length - 1].id}).done(function (data) {
                //console.log(data);
                for (var i = 0; i < data.length; ++i) {
                    $(".cities-list").append("<li data-id='" + data[i].id + "' data-name= '" + data[i].name + "' class='list-group-item city-box'>" + data[i].name + "</li>");
                }
            });
        }
        else {
            $.get("get-cities", {id: "-1"}).done(function (data) {
                //console.log(data);
                for (var i = 0; i < data.length; ++i) {
                    $(".cities-list").append("<li data-id='" + data[i].id + "' data-name= '" + data[i].name + "' class='list-group-item city-box'>" + data[i].name + "</li>");
                }
            });
        }
    });

    $(".cities-list").on('click', '.city-box', function(event) {
        //console.log($(this).data('id'));
        $(".cities-list").empty();
        $(".selected-cities").empty();
        selected.push({'given_id': given_id, 'name': $(this).data('name'), 'id': $(this).data('id')});
        given_id += 1;
        //console.log(selected);
        for (var i = 1; i <= selected.length; ++i) {
            $(".selected-cities").append("<li id='" + selected[i - 1].given_id + "' data-id='" + selected[i - 1].id + "' data-name= '" + selected[i - 1].name + "' class='list-group-item selected-city-box'>" + i + ". "+ selected[i - 1].name + "<button class='float-end badge bg-danger remove-selected-city'>X</button></li>");
        }

        //$(".route").text(cities);
        $.get("get-cities", {id:  $(this).data('id')}).done(function(data){
                //console.log(data);
                for (var i = 0; i < data.length; ++i) {
                    $(".cities-list").append("<li data-id='" + data[i].id + "' data-name= '" + data[i].name + "' class='list-group-item city-box'>" + data[i].name + "</li>");
                }
        });

    });
});
