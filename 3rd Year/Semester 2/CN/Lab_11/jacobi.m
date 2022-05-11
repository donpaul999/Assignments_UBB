function x = jacobi(A, b, x0, N)
  n = length(b);
  x = x0;
  for k=1:N
    for i = 1:n
      x(i)  = (b(i) - A(i, 1:i-1) * x0(1:i-1) - A(i, i+1:n) * x0(i+1:n))/ A(i, i);
    endfor
    x0 = x;
  endfor
endfunction