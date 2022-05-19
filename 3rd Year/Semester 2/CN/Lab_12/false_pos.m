function [c, nr_it, f_c] = false_pos(f, a, b, err=1e-10, n=100)
  for nr_it = 1 : n
    c = (a * f(b) - b * f(a)) / (f(b) - f(a));
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