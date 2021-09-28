# This is a sample Python script for exercise 4.
#
# the scene 'scan_robot.ttt' must be opened and simulation running for inverse kinematics working
import keyboard
import numpy as np
import math
import transformations as t
from random import  uniform
from numpy import asarray
from numpy import savetxt
from numpy import loadtxt


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

    import time

    print('Program started')

    # degrees of freedom of redundantRobot
    dof = 7

    sim.simxFinish(-1)  # just in case, close all opened connections
    clientID = sim.simxStart('127.0.0.1', 19997, True, True, 5000, 5)  # Connect to CoppeliaSim
    if clientID != -1:
        print('Connected to remote API server')

        res, target_handle = sim.simxGetObjectHandle(clientID,'redundantRob_target',sim.simx_opmode_blocking)
        res, pen_handle = sim.simxGetObjectHandle(clientID,'feltPen_body',sim.simx_opmode_blocking)
        time.sleep(1)

        # Now retrieve streaming data (i.e. in a non-blocking fashion):
        startTime = time.time()
        task = 1   # set for task 1
        #task = 2   # set for task 3
        if task == 1:
            position_vector = []  # vector for positions only needed for task 1, nbr_saved_postions = 3
            nbr_saved_positions = 3 # 3 positions are stored in one file
        if task == 2:
            pose_vector = []   # vector for positions and orientations only needed for task 2, nbr_saved_postions = 6
            nbr_saved_positions = 6
        counter = 0
        while True:
            if keyboard.is_pressed("q"):
                print("You pressed q, so you quit the program")
                break
            if keyboard.is_pressed("s"):   # save position of target
                counter = counter + 1      # increase counter of saved positions
                if task == 1:
                    res, pos = sim.simxGetObjectPosition(clientID,target_handle,-1, sim.simx_opmode_blocking)
                    print(pos)
                    if res >= 0:
                        position_vector.append(pos)
                if task == 2:
                    handle = pen_handle
                    res1, pos = sim.simxGetObjectPosition(clientID,handle,-1, sim.simx_opmode_blocking)
                    res2, ori = sim.simxGetObjectOrientation(clientID,handle,-1, sim.simx_opmode_blocking)
                    pose = pos + ori
                    if res1 >= 0 and res2 >= 0:
                        pose_vector.append(pose)
                        print(pose_vector)
                if counter % nbr_saved_positions  == 0:  # save each 3 or 6 positions for one frame
                    if task == 1:
                        filename = 'frame' + str(counter//3) + '.txt'
                        savetxt(filename, position_vector, delimiter=',')
                        print(filename, " saved with ", nbr_saved_positions, " points")
                        position_vector = []     # empty position_vector
                    if task == 2:
                        savetxt('systems.txt', pose_vector, delimiter=',')
                        print("systems.txt saved with ", nbr_saved_positions, " poses")
                        print(pose_vector)
                        pose_vector = [] # empty pose_vector
            time.sleep(0.05)

        # Now send some data to CoppeliaSim in a non-blocking fashion:
        sim.simxAddStatusbarMessage(clientID, 'CoppeliaSim finished', sim.simx_opmode_oneshot)

        # Before closing the connection to CoppeliaSim, make sure that the last command sent out had time to arrive. You can guarantee this with (for example):
        sim.simxGetPingTime(clientID)

        # Now close the connection to CoppeliaSim:
        sim.simxFinish(clientID)
        if task == 1:
            points = loadtxt('frame1.txt', delimiter=',')
            print(points)
        if task == 2:
            points = loadtxt('systems.txt', delimiter=',')
            print(points)
    else:
        print('Failed connecting to remote API server')
    print('Program ended')
##############################################################################
