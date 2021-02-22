x = [ 7 7 4 5 9 9 4 12 8 1 8 7 3 13 2 1 17 7 12 5 6 2 1 13 14 10 2 4 9 11 3 5 12 6 10 7 ];

s_squared = var(x);
n = columns(x) * rows(x);
alpha = 0.05;
left = (n-1) * s_squared / chi2inv(1 - alpha/2, n-1);
right = (n-1) * s_squared / chi2inv(alpha/2, n-1);
fprintf("The confidence interval for the variance is (%.3f, %.3f)\n", left, right);
fprintf("The confidence interval for the standard deviation is (%.3f, %.3f)\n", sqrt(left), sqrt(right));