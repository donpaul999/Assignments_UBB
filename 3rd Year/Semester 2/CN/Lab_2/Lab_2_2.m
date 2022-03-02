#---2 a.
t1=@(t)(cos(acos(t)));
t2=@(t)(cos(2*acos(t)));
t3=@(t)(cos(3*acos(t)));
hold  on;
fplot(t1,[-1,1]);
fplot(t2,[-1,1]);
fplot(t3,[-1,1]);