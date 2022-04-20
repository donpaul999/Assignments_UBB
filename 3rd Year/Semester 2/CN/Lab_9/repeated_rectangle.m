function S = repeated_rectangle(f, a, b, n)
  h = (b - a) / n;
  x = a + h:h:b - h;
  mid = a + h / 2 : h : b - h / 2;
  S = h * sum(f(mid));
endfunction