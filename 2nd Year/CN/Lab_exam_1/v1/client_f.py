import os
import socket
import sys
import json
import time

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
port = 7000
s.connect(("127.0.0.1", port))
arr = {'1': port}

arr = json.dumps(arr)

s.send(arr)

ans = s.recv(100)

print(ans)

time.sleep(2)