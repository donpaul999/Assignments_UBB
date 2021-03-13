<?php

require 'conectare.php';

?>

<!DOCTYPE html>
<html>
<head>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

</head>
<body>
    <div class="container">
    <div class="row">
    <form method="POST" action="add-destination-backend.php">
      <div class="form-group">
        <label for="location">Location</label>
        <input type="text" class="form-control" id="location" placeholder="Enter Location">
      </div>
      <div class="form-group">
        <label for="country">Country</label>
        <input type="text" class="form-control" id="country" placeholder="Enter Country">
      </div>
     <div class="form-group">
        <label for="targets">Tourist Targets</label>
        <input type="text" class="form-control" id="targets" placeholder="Enter Targets">
      </div>
      <div class="form-group">
        <label for="costs">Costs</label>
        <input type="number" class="form-control" id="costs">
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    </div>
    </div>
</body>
</html>


