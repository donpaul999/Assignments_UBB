f = open("d.csv", 'r')
sum = 0
while True:
    s = f.readline().strip()
    if not s:
        break
    s = s.split(",")
    print(s[5])
    sum += float(s[5])
f.close()
print(s)