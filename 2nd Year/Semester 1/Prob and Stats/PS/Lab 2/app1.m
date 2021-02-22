pkg load statistics;
x = 0:3;
y = binopdf(x, 3, 0.5);
subplot(2, 1, 1)
plot(x, y, "*")
% y is a binomial distribution
title("PDF")
w = 0: 0.01: 3;
fx = binocdf(w, 3, 0.5);
subplot(2, 1, 2);
plot(w, fx);
title("Fx");

p0 = nth_element(y, 1)
p_not_1 = 1 - nth_element(y, 2)
p_less_equal_2 = binocdf(2, 3, 0.5)
p_less_stricly_2 = binocdf(2, 3, 0.5) - binopdf(2, 3, 0.5)
p_bigger_stricly_1 = 1 - binocdf(1, 3, 0.5)
p_bigger_equal_1 = p_bigger_stricly_1 + binopdf(1, 3, 0.5)