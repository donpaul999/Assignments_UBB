import socket
import os
import json
import time

s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)

s.bind(("127.0.0.1",7000))
s.listen(5)



while True:
    list = []
    cs,addr=s.accept()
    print(addr)
    f = open("memory.txt", 'r')
    while True:
        st = f.readline().strip()
        if not st:
            break
        list.append(st)
    f.close()

    if os.fork() == 0:
        b=cs.recv(100)
        #print(addr[0])
        b = json.loads(b)
        ans = addr[0] + ':' + str(b['1'])
        if b['2'] == 1:
            list.append(ans)
        else:
            list.remove(ans)
        print(list)
        if len(list) == 0:
            open("memory.txt", 'w').close()
        else:
            f = open("memory.txt", 'w')
            for i in list:
                f.write(str(i) + '\n')
            f.close()

        list = json.dumps(list)

        time.sleep(2)
        cs.send(str(list))
        cs.close()
        os._exit(0)
