<?php
    $s=socket_create(AF_INET,SOCK_DGRAM,0);                  //create a UDP socket
    socket_bind($s,'0.0.0.0',5555);                          //bind on all interfaces on port 5555
    socket_recvfrom($s,$l,1,0,$client_ip,$client_port);     //read max 10 bytes from socket $s into buffer $b
    //echo "Received: ".$b." from IP=".$client_ip.":".$client_port;
    $l = $l.$l;
    socket_sendto($s,$l,5,0,$client_ip,$client_port);   //send a message back to the client

?>