import socket
import os
import json
import time

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)

s.bind(("127.0.0.1",7778))
s.listen(1)


while True:
    cs,addr=s.accept()
    print(addr)
    if os.fork() == 0:
        b=cs.recv(100)
        print(b)

        b = json.loads(b)

        sum = int(b['1'])
        while b['2'] != 0:
            sum += b['2'] % 10
            b['2'] /= 10

        time.sleep(2)
        cs.send(str(sum))
        cs.close()
        os._exit(0)
