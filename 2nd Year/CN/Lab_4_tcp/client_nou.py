import os
import socket
import sys
import json
import time

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(("127.0.0.1", 7777))

arr = {'1':  sys.argv[1]}


arr = json.dumps(arr)

s.send(arr)

ans = float(s.recv(20))

if ans != 0:
    ans = time.ctime(ans)

print(ans)