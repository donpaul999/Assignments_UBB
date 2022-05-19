function [c, nr_it, f_c] = secanta(f, a, b, err=1e-10, n=100)
  for nr_it = 1 : n
    c = (a * f(b) - b * f(a)) / (f(b) - f(a));
    a = b;
    b = c;
    if abs(b - a) < err || abs(f(c)) < err
      break;
    endif
  endfor
  f_c = f(c);
endfunction