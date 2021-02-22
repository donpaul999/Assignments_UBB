pkg load statistics;
n = input("number of trials:")
p = input("probability:")
N = input("simulations:")
x(1:N) = 0;
for i=1:N
  U = rand(n, 1);
  for j=1:n
    if (U(j) < p)
      x(i) = x(i) + 1;
    endif
  endfor
endfor
X = unique(x);
b = 1:n;
plot(a, x, "o")