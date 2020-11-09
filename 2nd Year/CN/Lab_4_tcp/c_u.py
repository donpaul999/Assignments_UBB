import os
import socket
import sys
import json

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(("127.0.0.1", 7778))

arr = {'1':  sys.argv[1], '2': 5555}


arr = json.dumps(arr)

s.send(arr)
print(s.recv(20))