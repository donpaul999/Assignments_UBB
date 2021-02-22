pkg load statistics
n = input("Give n:");
p = input("Give p:");
x = 0:n;
subplot(2,1,1);
y1 = binopdf(x, n, p);
plot(x, y1)
subplot(2,1,2);
y2 = poisspdf(x, n*p);
plot(x, y2);