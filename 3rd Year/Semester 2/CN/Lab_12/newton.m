function [c, nr_it, f_c] = newton(f, a, df, err=1e-10, n=100)
  for nr_it = 1 : n
    c = a - f(a)/(df(a));
    if abs(c - a) < err || abs(f(c)) < err
      break;
    endif
    a = c;
  endfor
  f_c = f(c);
endfunction