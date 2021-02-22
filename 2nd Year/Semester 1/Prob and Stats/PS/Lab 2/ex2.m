pkg load statistics
n = input("Give number of trials: ");
x = 0: n;
p = input("Give probability:");
y = binopdf(x, n, p);
w = 0: 0.001: n;
z = binocdf(w, n, p);
subplot(2, 1, 1);
plot(x, y, "*");
title("PDF")
subplot(2, 1, 2);
plot(w, z);
title("CDF")