# This is a sample Python script for exercise 5. You need to work with cell file DOF2_cartesian.ttt

import keyboard
import numpy as np
import math
import transformations as t
import time

from PIL import Image

#    rgb       white          red        green      blue       yellow     purple        cyan        light gray      gray         black
ColorMap = [[255,255,255], [255,0,0], [0, 255,0], [0,0,255], [255,255,0], [255,0,255], [0, 255,255], [192, 192, 192], [128,128,128], [0,0,0]]


################################################################################
# needs to be implemented
def check_collision(c1, c2):

    return False

################################################################################
if __name__ == '__main__':
    # start simulation and run this program.
    #
    # IMPORTANT: for each successful call to simxStart, there
    # should be a corresponding call to simxFinish at the end!

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

    dof = 2  # degrees of freedom of robot
    nbr_obstacles = 8  # number of obstacles

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


        oh = np.empty(nbr_obstacles, dtype=object)  # create an empty array for obstacle handles

        for i in range(nbr_obstacles):
            res,oh[i]=sim.simxGetObjectHandle(clientID,'Cuboid'+str(i+1),sim.simx_opmode_blocking)
            if res == sim.simx_return_ok:
                print("obstacle ", i+1, " handle read")
            time.sleep(0.5)
        # getting the handle for the robot
        res, rh = sim.simxGetObjectHandle(clientID, 'robot', sim.simx_opmode_blocking)
        if res == sim.simx_return_ok:
            print("robot handle read")
        time.sleep(1)

        list_obst = []
        for i in range(nbr_obstacles):
            position = sim.simxGetObjectPosition(clientID, oh[i], -1, sim.simx_opmode_blocking)
            orientation = sim.simxGetObjectOrientation(clientID, oh[i], -1, sim.simx_opmode_blocking)
            p = position[1]   # extracting position of obstacle
            ori = orientation[1]   # extracting of orientation of obstacle in form of euler angles (X-Y-Z)
            print(i+1, p, ori)
            if (math.fabs(ori[2]) > math.pi/4):   # if rotated
                x = [p[0]-0.25, p[1]-0.05, 0.5, 0.1]              # format: x_min, x_max, width, height
                x = [p[0]-0.25, p[0]+0.25, p[1]-0.05, p[1]+0.05]  # format: x_min, x_max, y_min, y_max
            else:
                x = [p[0]-0.05, p[1]-0.25, 0.1, 0.5]                      # format: x_min, x_max, width, height
                x = [p[0] - 0.05, p[0] + 0.05, p[1] - 0.25, p[1] + 0.25]  # format: x_min, x_max, y_min, y_max
            #print(x)
            list_obst.append(x)   # build a list of obstacles wrt to the above format

        # Now retrieve streaming data (i.e. in a non-blocking fashion):
        startTime = time.time()

        # the workspace is [-1, 1]^2  so a square of 4m2
        xdim = 1000   # pixel dimension in x
        ydim = 1000   # pixel dimension in y
        imarray = np.zeros([xdim, ydim, 3], dtype=int)  # creates an empty array fpr pixels

        # needs to be implemented
        for i in range(xdim):  # for every pixel:
            for j in range(ydim):  # for every pixel:
                imarray[i, j] = ColorMap[1]  # red

        im = Image.fromarray(imarray.astype('uint8')).convert('RGBA')  # converting the array to an image
        im.save("cspace_2DOF_cartesian.bmp")  # saving image to file

        stopTime = time.time()
        print(stopTime - startTime)

        # Now send some data to CoppeliaSim in a non-blocking fashion:
        sim.simxAddStatusbarMessage(clientID, 'Hello CoppeliaSim!', sim.simx_opmode_blocking)

        # Before closing the connection to CoppeliaSim, make sure that the last command sent out had time to arrive. You can guarantee this with (for example):
        sim.simxGetPingTime(clientID)

        # Now close the connection to CoppeliaSim:
        sim.simxFinish(clientID)
    else:
        print('Failed connecting to remote API server')
    print('Program ended')
