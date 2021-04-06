<?php
    
    require 'conectare.php';

?>


<!DOCTYPE html>
<html>
<head>
    <?php include 'layouts/includes.php'; ?>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/script.js"></script>
    <script>
    function pageClicked(elem) {
            var inputVal = $('#form-type').val();
            var pageVal = elem.id;
            var resultDropdown = $("#result");
            if(inputVal.length > 1){
                $.get("/backend/get-destinations-by-name.php", {page: pageVal, term: inputVal}).done(function(data){
                    // Display the returned data in browser
                    resultDropdown.html(data);
                });
            } else{
                resultDropdown.empty();
            }
        }
    </script>
</head>
<body>
    <div class="container mt-10">
    <div class="row">
        <div class="input-group mt-10 col-12" style='margin-top: 50px;'>
          <div class="form-outline mt-10">
            <input type="search" id="form-type" class="form-control" placeholder="Type location"/>
            <input type="search" id="page-input" style='margin-top: 50px;' class="form-control" placeholder="Type page"/>
          </div>
        </div>
      <div id='result' style="margin-top:100px;">
          
      </div>
     
      <a href="/" type="button" class="btn btn-danger">Back</a>
    </div>
    </div>
</body>
</html>


