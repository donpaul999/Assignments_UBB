<?php
$conectare = mysqli_connect("localhost", "contario_programare","WxrB*}i*N?@7", "contario_pw");
//$conectare = mysqli_connect("localhost", "contario_programare","", "contario_pw");

if(!$conectare)
{
    echo "EROARE!".'</br>';
    die(mysqli_connect_error());
}



?>