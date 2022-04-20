f = @(x) (100./x.^2).*sin(10./x);
hold on
fplot(f, [0, 1])
axis([0 1 0 4])
T = romberg(f, 1, 3, 5);
T
I = adquad(f, 1, 3, 10.^(-4));
I

#f = @(x) 4. / (1 + x.^2);
#fplot(f,[0,1])
#axis([0 1 0 4])