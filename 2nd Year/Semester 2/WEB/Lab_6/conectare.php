<?php
$conectare = mysqli_connect("localhost", "root","", "vacations");

if(!$conectare)
{
    echo "EROARE!".'</br>';
    die(mysqli_connect_error());
}



?>