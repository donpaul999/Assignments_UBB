function duplicateInputs() {
    $team = " <input type=\"text\" class=\"col-6\" name=\"team\" placeholder=\"Enter Team\">";
    $player = "<input type=\"text\" class=\"col-6\" name=\"player\" placeholder=\"Enter Player\">";
    $div = "<div class=\"form-inputs mb-5\">";
    $div2 = "</div>";
    $obj = $.parseHTML($div + $team + $player + $div2);
    $($obj).insertAfter('#initial');
}

function addUsers() {
    $teams = $("input[name='team']")
        .map(function(){return $(this).val();}).get();

    $players = $("input[name='player']")
        .map(function(){return $(this).val();}).get();
    var req_arr = [];
    for($i = 0; $i < $teams.length; $i += 1){
        req_arr.push({'team': $teams[$i], 'player': $players[$i]});
    }
    $.post("/backend/add-players.php", {'req_arr': req_arr}).done(function(data){
        $("div.form-inputs").remove();
    });
    //console.log(req_arr);
}
