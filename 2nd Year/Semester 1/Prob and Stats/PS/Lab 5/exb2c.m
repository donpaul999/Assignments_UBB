premium = [22.4 21.7 24.5 23.4 21.6 23.3 22.4 21.6 24.8 20.0];
regular = [17.7 14.8 19.6 19.6 12.1 14.8 15.4 12.6 14.0 12.2];

n1 = columns(premium);
n2 = columns(regular);
x1_bar = mean(premium);
x2_bar = mean(regular);
s1 = var(premium);
s2 = var(regular);
alpha = 0.05;
left = 1 / finv(1 - alpha/2, n1 - 1, n2 - 1) * (s1/s2);
right = 1 / finv(alpha/2, n1 - 1, n2 - 1) * (s1/s2);
fprintf("The ratio of the variances interval is (%.3f, %.3f)\n", left, right);