<?php
    $s=socket_create(AF_INET,SOCK_DGRAM,0);                  //create a UDP socket
    socket_bind($s,'0.0.0.0',5555);                          //bind on all interfaces on port 5555
    socket_recvfrom($s,$message,1024,0,$client_ip,$client_port);     //read max 10 bytes from socket $s into buffer $b
    //echo "Received: ".$b." from IP=".$client_ip.":".$client_port;
    $arr = json_decode($message, true);
    $max = $arr['1'] > $arr['2'] ? $arr['1'] : $arr['2'];

    socket_sendto($s,$max, 3,0,$client_ip,$client_port);   //send a message back to the client
?>