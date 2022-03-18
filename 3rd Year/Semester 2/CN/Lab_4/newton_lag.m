function   L = newton_lag(x, f, X)
  table = div_diff(x, f)
  c = table(1, :)

  prod = cumprod([1, X - x(1: end - 1)])';
  
  L = c * prod
 endfunction