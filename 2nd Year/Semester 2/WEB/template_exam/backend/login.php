<?php

require '../conectare.php';

session_start();
try{

    $username =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['username'])));
    $password =  htmlspecialchars(str_replace("&lt","",str_replace("&gt","", $_POST['password'])));
    $sql ="SELECT * from users where username= '".$username."' AND password= '".$password."'";
    $result = mysqli_query($conectare, $sql);
    $count = mysqli_num_rows($result);
    if($count > 0) {
        $user = mysqli_fetch_array($result);
        $_SESSION['username'] = $user['username'];
        $_SESSION['userid'] = $user['id'];
        return header("location:../dashboard.php");
    }
    throw new Exception('Incorrect username/password.');
}
catch (Exception $e) {
    $_SESSION['message'] = $e->getMessage();
}
return header("location:../index.php");
