<?php
require 'conectare.php';
session_start();
if(!isset($_SESSION['username']))
    return header("location:index.php");

?>

<!DOCTYPE html>
<html>
<head>
    <?php include 'layouts/includes.php'; ?>
</head>
<body>
<div class="container text-center">
    <h1>Hi, <?php echo $_SESSION['username'];?>!</h1>
    <div class="row">
        <a href="/check-assets.php" type="button" class="btn btn-primary mb-2">Check Assets</a>
        <a href="/backend/logout.php" type="button" class="btn btn-danger mb-2">Log out</a>
    </div>
</div>
</body>
</html>
