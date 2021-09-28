import numpy as np
import math
import transformations as t
import time
from PIL import Image



#    rgb       white          red        green      blue       yellow     purple        cyan        light gray         gray         black
ColorMap = [[255,255,255], [255,0,0], [0, 255,0], [0,0,255], [255,255,0], [255,0,255], [0, 255,255], [192, 192, 192], [128,128,128], [0,0,0]]

xdim = 200
ydim = 100
imarray = np.zeros([xdim, ydim, 3], dtype = int)   # creates an empty array fpr pixels

for i in range(xdim):  # for every pixel:
    for j in range(ydim):  # for every pixel:
        if j < ydim//3:
            imarray[i,j] = [255, 0, 0]  # red
        else:
            imarray[i,j] = [255, 255, 255]  # white
im = Image.fromarray(imarray.astype('uint8')).convert('RGBA')   # converting the array to an image
im.save("test1.bmp")  # saving image to file



im = Image.open("test1.bmp")   # loading an image from memory
print(im.size)

im2arr = np.array(im)  # converting image to array: width x height x  channel
for i in range(im2arr.shape[0]):  # for every pixel:
    for j in range(im2arr.shape[1]):  # for every pixel:
        if j < ydim//3:
            im2arr[i,j] = [0, 255, 0]  # green
        else:
            im2arr[i,j] = [0, 0, 255]  # blue

im = Image.fromarray(im2arr.astype('uint8')).convert('RGBA')   # converting the array to an image
im.save("test2.bmp") # saving image to file
