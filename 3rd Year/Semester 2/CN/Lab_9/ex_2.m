f = @(x) 4./(1+x.^2);
#hold on
#fplot(f, [0, 1])
#axis([0 1 0 4])
T = romberg(f, 0, 1, 5);
T
I = adquad(f, 0, 1, 10.^(-4));
I

#f = @(x) 4. / (1 + x.^2);
#fplot(f,[0,1])
#axis([0 1 0 4])