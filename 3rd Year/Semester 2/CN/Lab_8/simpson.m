function S = simpson(f, a, b, n)
  h = (b - a) / n;
  x = a + h:h:b - h;
  mid = a + h / 2 : h : b - h / 2;
  S = (b - a) / (6 * n) * (f(a) + f(b) + 4 * sum(f(mid)) + 2 * sum(f(x)));
endfunction