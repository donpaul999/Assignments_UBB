A = randi(10, 4, 4)
A = triu(A);
b = randi(10, 4, 1)
x = backward_subt(A, b);
x;
A * x;
A = [10, 7, 8, 7; 7, 5, 6, 5; 8, 6, 10, 9; 7, 5, 9, 10]
b = [32; 23; 33; 31];
x = elim_gauss(A, b)
A * x

b = [32.1; 22.9; 33.1; 30.9];
A = [10, 7, 8, 7; 7, 5, 6, 5; 8, 6, 10, 9; 7, 5, 9, 10]
x = elim_gauss(A, b)
A * x
cond(A)
