clf
hold on
axis equal
grid
axis([0 1 0 1])
[x,y] = ginput(1)
x1 = [x];
y1 = [y];
n = 1;
while ~isempty([x, y])
  plot(x, y, 'ok');
  text(x + 0.02, y + 0.02, num2str(n), 'fontsize', 15);
  n = n + 1;
  [x, y] = ginput(1);
  x1 = [x1, x];
  y1 = [y1, y]
endwhile

nodes_time = linspace(0, 1, n - 1);
t = linspace(0, 1, 1000);
p = polyfit(x1, y1, length(x1));
x2 = polyval(p, x1);
plot(x2, y1)
