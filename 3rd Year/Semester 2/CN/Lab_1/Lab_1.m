##A = magic(4)
##sum(A,2)
##trace(A)
##fliplr(A)
##A =magic(10)
##A(3:7, 2:8)
##B=randi(5,3,1)
##C=randi(5,1,4)

##v = randi(5,1,6)
##u = randi(5,1,6)

##v+u
##v*u
##A = randi(5,3,6)
##v.^2

##1:2:5
##10:-1.5:1

##A=magic(7)
##tril(A, 1)
##triu(A)
##
##diag(diag(A))

##x = a:0.001:b;
##x = linspace(0,1,1000);
##y=exp(10*x.*(x-1)).*sin(12*pi*x);
##plot(x,y)

##f=@(x) exp(10*x.*(x-1)).*sin(12*pi*x)
##fplot(f, [0,1],'hr', 'linewidth', 0.5)

## t = linspace(0,10*pi,1000);
## x=@(a,b)(a+b)*cos(t) - b*cos((a/b + 1)*t);
##  y=@(a,b)(a+b)*sin(t) - b*sin((a/b + 1)*t);
##  plot(x(1,2), y(1,2))
##
##f=@(x)(-1<=x & x<=0).*(x.^2+sqrt(1-x))+(0<x & x <= 1).*(x.^2-sqrt(1-x))
##fplot(f,[-1,1])

function  g = myFirstFunction  (n)
  
  