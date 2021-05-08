import random

import numpy as np
import torch


def generatePoints():
    max = 10
    min = -10
    size = 1000

    x = (max - min) * torch.rand((size, 2)) + min

    t = []
    w = []

    for i in x:
        t.append(i[0])
        w.append(i[1])

    t = torch.tensor(t)
    w = torch.tensor(w)

    f = torch.sin(t + (w / np.pi))
    p = torch.column_stack((x, f))
    print(x)
    print(f)
    print(p)
    torch.save(p, "mydataset.dat")
generatePoints()