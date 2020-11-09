import socket
import os
import json
import time

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)

s.bind(("127.0.0.1",7777))
s.listen(1)

dict={}


while True:
    cs,addr=s.accept()
    print(addr)

    f = open("memory.txt", 'r')
    while True:
        st = f.readline().strip()
        if not st:
            break
        nb = st.split(':')
        dict[nb[0]] = nb[1]
    f.close()

    if os.fork() == 0:
        b=cs.recv(100)
        #print(b)

        b = json.loads(b)
        b['1'] = str(b['1'])
        ans = 0
        #print(dict)

        if b['1'] in dict:
            ans = dict[b['1']]
        dict[b['1']] = time.time()

        f = open("memory.txt", 'w')
        for i in dict:
            f.write(str(i) + ':' + str(dict[i]) + '\n')
        f.close()

        time.sleep(2)
        cs.send(str(ans))
        cs.close()
        os._exit(0)
