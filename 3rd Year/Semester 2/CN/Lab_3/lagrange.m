function  L= lagrange (x, f, X)
    n = length(x);
    A = zeros(1, n);
    linearIndices = ind(a==X)
    for i = 1:n
      A(i)= 1 / prod(x(i) - x([1:i-1, i+1:n])); # 1:n ~= i
    endfor
     L = sum(A.*f./(X-x)) / sum (A./(X - x));
  endfunction