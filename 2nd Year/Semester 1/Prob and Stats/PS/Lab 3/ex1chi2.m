pkg load statistics
n = input("Give n:");
data = -10 : 0.1 : 10;

x = chi2pdf(data, n);
plot(data, x)

prob_less_equal_0 = chi2cdf(0, n)
prob_more_equal_0 = 1 - chi2cdf(0, n)
prob_between_minus1_and_1 = chi2cdf(1, n) - chi2cdf(-1, n)
prob_less_equal_minus1_or_more_equal_1 = chi2cdf(-1, n) + 1 - chi2cdf(1, n)

alpha = input("Give alpha:");
xalpha = chi2inv(alpha, n)
beta = input("Give beta:")
xbeta = 1 - chi2inv(beta, n)