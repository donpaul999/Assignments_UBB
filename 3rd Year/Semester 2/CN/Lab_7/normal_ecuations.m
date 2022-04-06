function [a, b] = normal_ecuations(x, f)
   m = length(x) - 1;
   a = ((m + 1) * sum(x.*f) - sum(x) * sum(f))/((m+1) * sum(x.^2) - (sum(x)).^2);
   b = (sum(x.^2) * sum(f) - sum(x.*f) * sum(x))/((m+1) * sum(x.^2) - (sum(x)).^2);
endfunction