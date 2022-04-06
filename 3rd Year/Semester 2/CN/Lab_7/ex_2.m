temperature = [0, 10, 20, 30, 40, 60, 80, 100];
temp2 = linspace(0, 100)
pressure = [0.0061, 0.0123, 0.0234, 0.0424, 0.0738, 0.1992, 0.4736, 1.0133];

p = polyfit(temperature, pressure, 7);

plot(temperature, pressure,'o');

hold on
x1 = polyval(p, temperature);

p45 = polyval(p, 45)

plot(temp2, polyval(p, temp2));