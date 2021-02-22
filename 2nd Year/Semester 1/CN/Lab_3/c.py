import os
import socket
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(("127.0.0.1", 7777))
s.send("de la client")
print(s.recv(20))