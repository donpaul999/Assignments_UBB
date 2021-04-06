$(document).ready(function(){
        $('#form-type').on("keyup", function(){
            var inputVal = $(this).val();
            var resultDropdown = $("#result");
            if(inputVal.length > 1){
                $.get("../../backend/get-destinations-by-name.php", {term: inputVal}).done(function(data){
                    // Display the returned data in browser
                    resultDropdown.html(data);
                });
            } else{
                resultDropdown.empty();
            }
        });
        
        $('#page-input').on("keyup", function(){
            var inputVal = $('#form-type').val();
            var pageVal = $(this).val();
            var resultDropdown = $("#result");
            if(inputVal.length > 1){
                $.get("../../backend/get-destinations-by-name.php", {page: pageVal, term: inputVal}).done(function(data){
                    // Display the returned data in browser
                    resultDropdown.html(data);
                });
            } else{
                resultDropdown.empty();
            }
        });

});

