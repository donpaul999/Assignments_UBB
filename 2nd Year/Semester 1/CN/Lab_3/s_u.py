import socket
import json

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
s.bind(("0.0.0.0",5555))
mess,addr=s.recvfrom(100)
mess=json.dumps(mess)
print(mess[1])
s.sendto("hello", addr)