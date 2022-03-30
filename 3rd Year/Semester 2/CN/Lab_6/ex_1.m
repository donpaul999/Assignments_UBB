x = [0, pi/6, pi/4, pi/3, pi/2];
y = [0, 1/2, sqrt(2)/2, sqrt(3)/2, 1];
f = spline(x, [1, y, 0], 1);

t = linspace(0, pi/2, 10000);
clf
hold on
plot(t, spline(x, y, t));
plot(x, y, '*');
plot(t, sin(t), '--g');