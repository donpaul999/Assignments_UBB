pkg load statistics
n = input("Give n:");
m = input("Give m:");
data = -10 : 0.1 : 10;

x = fpdf(data, n, m);
plot(data, x)

prob_less_equal_0 = fcdf(0, n, m)
prob_more_equal_0 = 1 - fcdf(0, n, m)
prob_between_minus1_and_1 = fcdf(1, n, m) - fcdf(-1, n, m)
prob_less_equal_minus1_or_more_equal_1 = fcdf(-1, n, m) + 1 - fcdf(1, n, m)

alpha = input("Give alpha:");
xalpha = finv(alpha, n, m)
beta = input("Give beta:")
xbeta = 1 - finv(beta, n, m)