function [x,N] = mat_it(type, A, b, x0, er, w)
  L = tril(A, -1);
  U = triu(A, 1);
  D = diag(diag(A));
  
  switch type
    case 'Jacobi'
      T = - D \ (L + U);
      C = D \ b;
    case 'Gauss'
      T = - (D + L) \ U;
      C = (D + L) \ b;
    case 'SOR'
      T = (D + w * L) \ ((1 - w) * D - w * U);
      C = (D + w * L) \ (w * b);
  endswitch
  
  N = 1;
  x = T * x0 + C;
  while norm(x-x0) > er
    x0 = x;
    x = T * x0 + C;
    N++;
  endwhile
  N
endfunction