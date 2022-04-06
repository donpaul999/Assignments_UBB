time = [1, 2, 3, 4, 5, 6, 7];
temperature = [13, 15, 20, 14, 15, 13, 10];
[a, b] = normal_ecuations(time, temperature);
plot(time, temperature, 'o', a*time + b)