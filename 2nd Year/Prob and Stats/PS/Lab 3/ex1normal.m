pkg load statistics
m = input("Give m:");
s = input("Give s:");
data = -10 : 0.1 : 10;

prob_less_equal_0 = normcdf(0, m, s)
prob_more_equal_0 = 1 - normcdf(0, m, s)
prob_between_minus1_and_1 = normcdf(1, m, s) - normcdf(-1, m, s)
prob_less_equal_minus1_or_more_equal_1 = normcdf(-1, m, s) + 1 - normcdf(1, m, s)

alpha = input("Give alpha:");
xalpha = norminv(alpha, m, s)
beta = input("Give beta:")
xbeta = 1 - norminv(beta, m, s)