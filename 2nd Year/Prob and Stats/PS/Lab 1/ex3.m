x = 0:0.1:3;
subplot(2, 2, 1)
plot((x.^5)/10,"-*r;Graph1;", "markersize", 1)
subplot(2, 2, 2)
plot(x.*sin(x), "--+b;Graph2;", "markersize", 1.5)
subplot(2, 2, 3)
plot(cos(x), ":dm;Grahp3;", "markersize", 3.0)
