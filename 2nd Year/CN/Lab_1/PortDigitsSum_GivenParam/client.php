<?php
    $port = 5555;
    $arr = array('1' => $argv[1], '2' => $port);
    $message = json_encode($arr);
    $len = strlen($message);

    $s=socket_create(AF_INET,SOCK_DGRAM,0);            //create a UDP socket
    socket_sendto($s, $message, $len,0,"127.0.0.1",$port);      //send 3 bytes to the server
    socket_recvfrom($s,$b,10,0,$serv_ip,$serv_port);   //read max 10 bytes from socket $s into buffer $b
    //echo "Received: ".$b." from IP=".$serv_ip.":".$serv_port;
    echo $b.PHP_EOL;
?>