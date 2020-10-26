import socket
import sys
import json

s=socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
port = 5555
arr = {'1':  sys.argv[1], '2': port}
arr = json.dumps(arr)
s.sendto(arr,("127.0.0.1",5555))

t = s.recvfrom(5)

print(t[0])