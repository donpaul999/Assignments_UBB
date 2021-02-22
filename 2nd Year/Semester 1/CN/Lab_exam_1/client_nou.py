import sys
import socket
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect(("127.0.0.1", 7000))
msg = s.recv(100)

print(msg)


while True:
    x = raw_input('input: ')
    s.send(x)
    if x == 'QUIT':
        break

s.close()
