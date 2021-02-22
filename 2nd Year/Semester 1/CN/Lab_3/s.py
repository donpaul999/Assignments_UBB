import socket
import os
import time

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("127.0.0.1",7777))
s.listen(5)

while True:
    cs,addr=s.accept()
    print(addr)
    if os.fork() == 0:
        b=cs.recv(10)
        print(b)
        time.sleep(5)
        cs.send("Hello")
        cs.close()
        os._exit(0)
