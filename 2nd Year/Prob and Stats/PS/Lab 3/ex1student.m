pkg load statistics
n = input("Give n:");
data = -10 : 0.1 : 10;

x = tpdf(data, n);
plot(data, x)

prob_less_equal_0 = tcdf(0, n)
prob_more_equal_0 = 1 - tcdf(0, n)
prob_between_minus1_and_1 = tcdf(1, n) - tcdf(-1, n)
prob_less_equal_minus1_or_more_equal_1 = tcdf(-1, n) + 1 - tcdf(1, n)

alpha = input("Give alpha:");
xalpha = tinv(alpha, n)
beta = input("Give beta:")
xbeta = 1 - tinv(beta, n)