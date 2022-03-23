time = [0, 3, 5, 8, 13];
distance = [0, 225, 383, 623, 993];
speed = [75, 77, 80, 74, 72];
X = 12.5;

x = time;
f = distance;
df = speed;

[H, dH] = Hermite(x, f, df, X)