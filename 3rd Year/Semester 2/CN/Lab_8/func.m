function S = func(f, a, b, n)
  h = (b - a) / n;
  x = a + h:h:b - h;
  S = (b - a) / (2 * n) * (f(a) + f(b) + 2 * sum(f(x)));
endfunction