<?php
require 'conectare.php';
session_start();
if(isset($_SESSION['username']))
    return header("location:dashboard.php");

?>

<!DOCTYPE html>
<html>
<head>
    <?php include 'layouts/includes.php'; ?>
</head>
<body>
<div class="container text-center">
    <h1>Assets manager</h1>
    <?php
    if(isset($_SESSION['message']))
        echo '<div class="alert alert-warning" role="alert">'.$_SESSION['message'].'
    </div>';
    session_destroy();
    ?>
    <div class="row">
        <form method="POST" action="backend/login.php">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
            </div>
            <button type="submit" class="btn btn-primary mt-5">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
