f = @(x) exp(-x.^2);
S = repeated_rectangle(f, 1, 1.5,  500);
S
hold on
fill([1, 1.5, 1.5, 1], [0, 0, f((1+1.5)/2), f((1+1.5)/2)], "w")
fplot(f,[1,1.5])
