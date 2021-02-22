p = 0.5;
for i = 10:10:100
  n = i;
  x = 0:n;
  y1 = binopdf(x, n, p);
  subplot(2,1,1)
  plot(x, y1);
  y2 = normpdf(x, n*p, sqrt(n*p*(1-p)));
  subplot(2,1,2);
  plot(x,y2);
  
  pause(0.7)
end
