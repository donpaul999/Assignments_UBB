function x = elim_gauss(A,b)
  n = length(b);
  x = zeros(n, 1);
  A = [A b];
  for i = 1:n-1
    [val, pos] = max(abs(A(i:n, i)));
    assert(val ~= 0);
    pos = pos + i - 1;
    A([i, pos],:)=A([pos, i], :);
    for j = i+1:n
      A(j, :) = A(j,:) - A(i,:) * A(j, i)/A(i, i);
    endfor
  endfor
  x = backward_subt(A(:, 1:n), A(:, n + 1));
endfunction
