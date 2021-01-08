x = 0:0.01:3;
plot((x.^5)/10,"-*r;Graph1;", "markersize", 1, 
x.*sin(x), "--+b;Graph2;", "markersize", 1.5,
cos(x), ":dm;Grahp3;", "markersize", 3.0)
title("Test Graph")