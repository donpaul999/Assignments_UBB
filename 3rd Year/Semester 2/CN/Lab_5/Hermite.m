function  [H, dH] = Hermite(x, f, df, X)
    z = repelem(x, 2);
    n = length(z);

    table = div_diff(x, f, df);
    # 1st line
    c = table(1,:);
    H = c(1);
    dH = 0;
    P = X - z(1);
    dP = 1;

    for k=2:n
      H = H + c(k) * P;
      dH = dH + c(k) * dP;
      dP = P + dP * (X - z(k));
      P = P * (X - z(k));
    endfor

endfunction