f = @(x)((x-2)^2 - log(x));
df = @(x)2*(x - 2) - 1/x;
a = 1;
b = 2;
fzero(f,[a,b]);
[c, nr_it, f_c] = bisect(f, a, b)
[c, nr_it, f_c] = false_pos(f, a, b)
[c, nr_it, f_c] = secanta(f, a, b)
[c, nr_it, f_c] = newton(f, a, df)

f = @(x)(x - 0.8 * sin(x) - 2*pi/10);
df = @(x)(1 - 0.8*cos(x));

a = 1
b = 10;
fzero(f,[a,b]);
[c, nr_it, f_c] = bisect(f, a, b)
[c, nr_it, f_c] = false_pos(f, a, b)
[c, nr_it, f_c] = secanta(f, a, b)
[c, nr_it, f_c] = newton(f, a, df)

