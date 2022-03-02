
f = @(x)(exp(x));
x0 = 0;
hold on;

for n = 1:6
  t0 = @(x)(1);
  for  j= 1:n
    t1= @(x)(t0 (x)+x.^j/factorial(j));
    t0 = t1;
  endfor
  fplot(t1, [-1,3]);
endfor 
legend("T1", "T2", "T3", "T4", "T5", "T6")