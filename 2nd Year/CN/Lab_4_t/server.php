<?php
    $s=socket_create(AF_INET,SOCK_DGRAM,0);                  //create a UDP socket
    socket_bind($s,'0.0.0.0',5555);                          //bind on all interfaces on port 5555
    socket_recvfrom($s,$message,1024,0,$client_ip,$client_port);     //read max 10 bytes from socket $s into buffer $b
    //echo "Received: ".$b." from IP=".$client_ip.":".$client_port;
    $arr = json_decode($message, true);
    $nr = intval($arr['2']);
    $sum = 0;
    while($nr) {
        $sum += $nr % 10;
        $nr /= 10;
    }
    $message =  intval($arr['1']) + $sum;
    $message = strval($message);
    $len = strlen($message);
    socket_sendto($s,$message, $len,0,$client_ip,$client_port);   //send a message back to the client
?>