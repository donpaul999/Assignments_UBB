function  A = div_diff(x, f)
    n = length(x);
    A = [f', nan(n, n-1)];
    for  j = 2:n
      A (1:n-j+1, j) = (A(2: n  - j + 2, j-1) - A(1: n - j + 1, j-1)) ./(x(j: n) - x(1 : n - j +1))';
    endfor
  endfunction