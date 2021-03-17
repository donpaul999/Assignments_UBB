<?php

require 'conectare.php';

session_start();
try{

    $location =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['location'])));
    $country =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['country'])));
    $targets =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['targets'])));
    $costs =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['costs'])));
    $description =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['$description'])));
    $sql ="INSERT INTO destinations(name, country, targets, cost, description) VALUES ('$location','$country', '$targets','$costs', '$description')";

    $result = mysqli_query($conectare, $sql);
    $_SESSION['message'] = "Succesful operation!";
}
catch (Exception $e) {
     $_SESSION['message'] = $e->getMessage();
}
    return header("location:/");

?>
