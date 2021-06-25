<?php
$conectare = mysqli_connect("localhost", "root","123456789", "ubb_exam");

if(!$conectare)
{
    echo "EROARE!".'</br>';
    die(mysqli_connect_error());
}



?>
