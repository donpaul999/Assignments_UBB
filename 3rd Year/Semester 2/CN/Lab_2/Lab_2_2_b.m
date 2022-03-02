#--2b

N = 5
hold on;

t0=@(x)(1)
t1=@(x)(x)
fplot(t0,[-1,1])
fplot(t1,[-1,1])
for n = 1:N
  t2 = @(x)(2*x.*t1(x) - t0(x));
  fplot(t2, [-1,1])
  t0 = t1;
  t1 = t2;
endfor