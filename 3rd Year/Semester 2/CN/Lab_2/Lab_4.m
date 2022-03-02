h=0.25;
x = [];
f=@(x)(sqrt(5*x.^2+1));

finite_diff = zeros(6,6);
for i =0:6
   x = 1+i*h;
   for j=0:6
      finite_diff(i,j)=(h^j)*f(x)
   endfor
 endfor  
