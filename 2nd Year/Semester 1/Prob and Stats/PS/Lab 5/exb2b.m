premium = [22.4 21.7 24.5 23.4 21.6 23.3 22.4 21.6 24.8 20.0];
regular = [17.7 14.8 19.6 19.6 12.1 14.8 15.4 12.6 14.0 12.2];

n1 = columns(premium);
n2 = columns(regular);
x1_bar = mean(premium);
x2_bar = mean(regular);
s1 = var(premium);
s2 = var(regular);
alpha = 0.05;
c = (s1 / n1) / (s1/n1 + s2/n2)
n = 1/((c*c) / (n1 -1) + (1 - c)* (1-c) / (n2 -1));
left = x1_bar - x2_bar - tinv(1 - alpha/2, n) * sqrt(s1/n1 + s2/n2);
right = x1_bar - x2_bar + tinv(1 - alpha/2, n) * sqrt(s1/n1 + s2/n2);
fprintf("The difference of the true means interval is (%.3f, %.3f)\n", left, right);