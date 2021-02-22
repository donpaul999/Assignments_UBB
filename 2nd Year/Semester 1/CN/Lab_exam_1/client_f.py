import os
import socket
import sys
import json
import time
import random


c = 1
while c >= 0:
    print(c)
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    port = 7000
    s.connect(("127.0.0.1", port))
    arr = {'1': port, '2': c}

    arr = json.dumps(arr)

    s.send(arr)

    ans = s.recv(100)

    print(ans)
    c -= 1
    time.sleep(2)

