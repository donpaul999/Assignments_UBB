function [c, nr_it, f_c] = bisect(f, a, b, err=1e-10, n=100)
  for nr_it = 1 : n
    c = (a + b) / 2;
    if f(a) * f(c) < 0
      b = c;
    else
      a = c;
    endif
    if abs(b - a) < err || abs(f(c)) < err
      break;
    endif
  endfor
  f_c = f(c);
endfunction