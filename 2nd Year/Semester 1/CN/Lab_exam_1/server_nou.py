import sys
import socket
import threading

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind(("127.0.0.1",7000))
s.listen(5)

clients = {}

def run(cs, addr):
    global clients
    while True:
        msg = cs.recv(100)
        if msg == 'QUIT':
            print("Client " + str(addr) + " has disconnected")
            del clients[addr]
            #print(clients)
            for c, sock in clients.items():
                sock.send("Disconnected" + str(addr[0]) + str(addr[1]))
            cs.close()
            break

while True:
    cs, addr = s.accept()
    print("Client " + str(addr) + " has connected.")
    if not clients.get(addr):
        clients[addr] = cs
    msg = "Clients:\n"
    for i in clients.keys():
        msg += str(i[0]) + ',' + str(i[1]) + '\n'
    cs.send(msg)
    threading.Thread(target=run, args=(cs, addr)).start()

