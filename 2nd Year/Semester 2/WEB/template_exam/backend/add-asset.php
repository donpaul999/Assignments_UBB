<?php
require '../conectare.php';

session_start();
try{
    $userid = $_SESSION['userid'];
    $name =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['name'])));
    $description =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['description'])));
    $value =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['value'])));
    $sql ="INSERT INTO assets(name, value, description, userid) VALUES ('$name','$value', '$description', '$userid')";

    $result = mysqli_query($conectare, $sql);
    $_SESSION['message'] = "Succesful operation!";
}
catch (Exception $e) {
    $_SESSION['message'] = $e->getMessage();
    echo($e->getMessage());
}
?>
