import random

import numpy as np
import torch


def generatePoints():
    max = 10
    min = -10
    size = 1000

    x = (max - min) * torch.rand((2, size)) + min

    f = torch.sin(x[0] + (x[1] / np.pi))

    torch.save(f, "mydataset.dat")
generatePoints()