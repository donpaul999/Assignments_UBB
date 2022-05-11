function x = sor(A, b, x0, N, w)
  n = length(b);
  x = x0;
  for k=1:N
    for i = 1:n
      x(i) = w * ((b(i) - A(i, 1:i-1) * x(1:i-1) - A(i, i+1:n) * x0(i+1:n))/ A(i, i)) + (1-w) * x0(i);
    endfor
    x0 = x;
  endfor
endfunction