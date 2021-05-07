import random

import numpy as np
import torch


def generatePoints():
    points = []
    for i in range(1000):
        points.append([random.uniform(-10, 10), random.uniform(-10, 10)])
    print(points)
    f = []
    for i in range (1000):
        f.append(torch.sin(torch.tensor(points[i][0] + (points[i][1] / np.pi))))
    print(f)

    d = []
    for i in range(1000):
        d.append([points[i], f[i]])
    print(d)
    torch.save(d, "mydataset.dat")
generatePoints()