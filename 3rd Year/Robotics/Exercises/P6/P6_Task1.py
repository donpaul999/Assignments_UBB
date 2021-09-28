# This is a sample Python script for exercise 6. You need to work with cell file DOF2_cartesian.ttt

import keyboard
import numpy as np
import math
import transformations as t
import time

from PIL import Image
from collections import deque

#    rgb       white          red        green      blue       yellow     purple          cyan         light gray      gray         black      brown
ColorMap = [[255,255,255], [255,0,0], [0, 255,0], [0,0,255], [255,255,0], [255,0,255], [0, 255,255], [192, 192, 192], [128,128,128], [0,0,0], [128, 0 , 0]]

# Direction table for neighbors
DirTable = [(0, -1), # up  (north)
            (1,  0), # right  (east)
            (0,  1), # down  (south)
            (-1, 0),  # left  (west)
            (1, -1),  # up-right (north-east)
            (1,  1),  # down-right (south-east)
            (-1, 1),  # down-left (south-west)
            (-1, -1)]  # up-left (north-west)

neighborhood = 4  # N4 neighborhood
neighborhood = 8  # N8 neighborhood
################################################################################################
def FindPathBFS(cspace, nStart, nGoal):
    start, goal = nStart, nGoal
    size_x, size_y = arr.shape[0], arr.shape[1]
    V = np.empty((size_x, size_y), object)
    L = np.empty((size_x, size_y), object)
    for i in range(size_x):
        for j in range(size_y):
            V[i, j] = False  # initialize visited
            L[i, j] = -1     # initialize level (distance to start)

    V[start[0], start[1]] = True;  # set start cell as visited
    L[start[0], start[1]] = 0;   # zero distance to start
    path = []
    # implement from here
    # ......


    im = Image.fromarray(cspace.astype('uint8')).convert('RGBA')  # converting the array to an image
    im.save("cspace_2DOF_cartesian_path.bmp")  # saving image to file
    path_reverse = path[::-1]
    return path_reverse

################################################################################
if __name__ == '__main__':
    # start simulation and run this program.
    #
    # IMPORTANT: for each successful call to simxStart, there
    # should be a corresponding call to simxFinish at the end!

    im = Image.open("cspace_2DOF_cartesian.bmp")  # loading an image from memory
    print(im.size)
    arr = np.array(im)  # converting image to array: width x height x  channel
    size_x, size_y = arr.shape[0], arr.shape[1]

    try:
        import sim
    except:
        print('--------------------------------------------------------------')
        print('"sim.py" could not be imported. This means very probably that')
        print('either "sim.py" or the remoteApi library could not be found.')
        print('Make sure both are in the same folder as this file,')
        print('or appropriately adjust the file "sim.py"')
        print('--------------------------------------------------------------')
        print('')


    print('Program started')


    sim.simxFinish(-1)  # just in case, close all opened connections
    clientID = sim.simxStart('127.0.0.1', 19997, True, True, 5000, 5)  # Connect to CoppeliaSim
    if clientID != -1:
        print('Connected to remote API server')

        # Now try to retrieve data in a blocking fashion (i.e. a service call):
        res, objs = sim.simxGetObjects(clientID, sim.sim_object_shape_type, sim.simx_opmode_blocking)
        if res == sim.simx_return_ok:
            print('Number of objects in the scene: ', len(objs))
        else:
            print('Remote API function call returned with error code: ', res)

        res, rh = sim.simxGetObjectHandle(clientID, 'robot', sim.simx_opmode_blocking)
        if res == sim.simx_return_ok:
            print("robot handle read")
        else:
            print("robot handle NOT read")
        time.sleep(0.5)

        while True:
            if keyboard.is_pressed("q"):
                print("You pressed q, so have programmed start and goal configuration")
                break
            if keyboard.is_pressed("g") or keyboard.is_pressed("G"):   # setting goal configuration
                position = sim.simxGetObjectPosition(clientID, rh, -1, sim.simx_opmode_blocking)
                goal = position[1]
                print("Goal is: ", goal)
            if keyboard.is_pressed("s") or keyboard.is_pressed("S"):    # setting start configuration
                position = sim.simxGetObjectPosition(clientID, rh, -1, sim.simx_opmode_blocking)
                start = position[1]
                print("Start is: ", start)

        print("Start: ", start, "  Goal:", goal)
        #   implement from here
        #   .....

        res = sim.simxSetObjectPosition(clientID, rh, -1, [0, 0, 0], sim.simx_opmode_oneshot)
        time.sleep(0.02)


        # Now send some data to CoppeliaSim in a non-blocking fashion:
        sim.simxAddStatusbarMessage(clientID, 'Hello CoppeliaSim!', sim.simx_opmode_blocking)

        # Before closing the connection to CoppeliaSim, make sure that the last command sent out had time to arrive. You can guarantee this with (for example):
        sim.simxGetPingTime(clientID)

        # Now close the connection to CoppeliaSim:
        sim.simxFinish(clientID)
    else:
        print('Failed connecting to remote API server')
    print('Program ended')
