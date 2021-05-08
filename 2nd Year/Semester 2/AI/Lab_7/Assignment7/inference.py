# we load the model
import torch
import torch.nn.functional as F
import numpy as np

import myModel

device = 'cpu'

filepath = "myNet.pt"
ann = myModel.Net(2, 10, 1).to(device)

ann.load_state_dict(torch.load(filepath))
ann.eval()

# visualise the parameters for the ann (aka weights and biases)
'''
for name, param in ann.named_parameters():
 if param.requires_grad:
     print (name, param.data)
'''

x1 = float(input("x1 = "))
x2 = float(input("x2 = "))
x = torch.tensor([x1, x2])
print(ann(x).tolist())
print(f'correct: {np.sin(x1 + x2 / np.pi)}')
