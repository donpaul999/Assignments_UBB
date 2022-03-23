function  A = div_diff(x, f, df)
    z = repelem(x, 2);
    n = length(z);
    A =  nan(n);
    A(:,1) = repelem(f, 2);
    A(1:2:end-1,2)=df;
    A(2:2:end-2,2) = diff(f)./diff(x);
    for  j = 3:n
      A (1:n-j+1, j) = (A(2: n  - j + 2, j-1) - A(1: n - j + 1, j-1)) ./(z(j: n) - z(1 : n - j +1))';
    endfor
endfunction