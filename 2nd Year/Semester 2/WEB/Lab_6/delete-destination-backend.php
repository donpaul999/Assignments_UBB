<?php

require 'conectare.php';

session_start();
try{

    $id = $_GET['id'];
    $sql ="DELETE FROM destinations where id=".$id;

    $result = mysqli_query($conectare, $sql);
    $_SESSION['message'] = "Succesful operation!";
}
catch (Exception $e) {
     $_SESSION['message'] = $e->getMessage();
}
    return header("location:/");

?>
