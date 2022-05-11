n = 10;
A = diag(-ones(n-1, 1), -1) + diag(-ones(n-1, 1), 1) + diag(ones(n,1) * 3,0);
b = [2; ones(n - 2, 1); 2];
jacobi(A, b, zeros(n, 1), 20)
gs(A, b, zeros(n, 1), 20)
sor(A, b, zeros(n, 1), 20, 1.15)
mat_it('Jacobi', A, b, zeros(n, 1), 10^(-5), 1.15)
mat_it('Gauss', A, b, zeros(n, 1), 10^(-5), 1.15)
mat_it('SOR', A, b, zeros(n, 1), 10^(-5), 1.15)