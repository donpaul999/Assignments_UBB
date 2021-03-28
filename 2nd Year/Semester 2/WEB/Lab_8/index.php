<?php

require 'conectare.php';
session_start();
?>

<!DOCTYPE html>
<html>
<head>
    <?php include 'layouts/includes.php'; ?>
</head>
<body>
    <div class="container text-center">
      <h1>Vacation Manager</h1>
    <?php
    if( $_SESSION['message'] != '')
        echo '<div class="alert alert-warning" role="alert">'.$_SESSION['message'].'
    </div>';
    session_destroy();
    ?>
     <div class="row">   
        <a href="add-destination.php" type="button" class="btn btn-primary mb-2">Add Destination</a>
        <a href="delete-destinations.php" type="button" class="btn btn-secondary mb-2">Delete Destinations</a>
        <a href="modify-destinations.php" type="button" class="btn btn-success mb-2">Modify Destinations</a>
        <a href="browse-destinations.php" type="button" class="btn btn-danger mb-2">Browse Destinations</a>
     </div>
    </div>
</body>
</html>
