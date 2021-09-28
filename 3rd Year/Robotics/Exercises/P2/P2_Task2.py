# This is a sample Python script for exercise 2 and 3.
import keyboard
import numpy as np
import math
import transformations as t
from random import uniform


############################################################################
# this needs to be implemented
def convert_quaternion_to_matrix(quat):
    # Extract the values from quat
    q0 = quat[3]
    q1 = quat[0]
    q2 = quat[1]
    q3 = quat[2]

    # First row of the rotation matrix
    r00, r01, r02 = 1.0, 0.0, 0.0
    # Second row of the rotation matrix
    r10, r11, r12 = 0.0, 1.0, 0.0
    # Third row of the rotation matrix
    r20, r21, r22 = 0.0, 0.0, 1.0

    # 3x3 rotation matrix
    rot_matrix = np.array([[r00, r01, r02],
                           [r10, r11, r12],
                           [r20, r21, r22]])
    return rot_matrix
############################################################################
# this needs to be implemented
def euler_from_matrix(M):
    eps = 1e-7
    return [0, 0, 0]
############################################################################
# this needs to be implemented
def matrix_from_euler(angles):

    # First row of the rotation matrix
    r00, r01, r02 = 1.0, 0.0, 0.0
    # Second row of the rotation matrix
    r10, r11, r12 = 0.0, 1.0, 0.0
    # Third row of the rotation matrix
    r20, r21, r22 = 0.0, 0.0, 1.0

    # 3x3 rotation matrix
    rot_matrix = np.array([[r00, r01, r02],
                           [r10, r11, r12],
                           [r20, r21, r22]])
    return rot_matrix


################################################################################
def forward_kinematics(rob, joints):
    TCP = t.identity_matrix()

    return TCP

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
    # degrees of freedom of snake robot
    dof = 6
    # kinematic structure of snake robot  - to be filled out
    snake_robot = np.array([
        [0, 0, 0, 0, 0, 0],    # from world coordinate system to robot base
        [0, 0, 0, 0, 0, 0],    # from joint1 to joint2
        [0, 0, 0, 0, 0, 0],     # from joint2 to joint3
        [0, 0, 0, 0, 0, 0],     # from joint3 to joint4
        [0, 0, 0, 0, 0, 0],     # from joint4 to joint5
        [0, 0, 0, 0, 0, 0],     # from joint5 to joint6
        [0, 0, 0, 0, 0, 0],     # from joint6 to TCP
    ])

    sim.simxFinish(-1)  # just in case, close all opened connections
    clientID = sim.simxStart('127.0.0.1', 19997, True, True, 5000, 5)  # Connect to CoppeliaSim
    if clientID != -1:
        print('Connected to remote API server')

        # Now try to retrieve data in a blocking fashion (i.e. a service call):
        res, objs = sim.simxGetObjects(clientID, sim.sim_handle_all, sim.simx_opmode_blocking)
        if res == sim.simx_return_ok:
            print('Number of objects in the scene: ', len(objs))
        else:
            print('Remote API function call returned with error code: ', res)

        # Getting Handles of the two Motors
        return_code, handle_tcp = sim.simxGetObjectHandle(clientID, 'TCP', sim.simx_opmode_blocking)

        jh = np.empty(dof, dtype=object)  # create an empty array for joint handles
        # getting the handles for the joints
        res,jh[0]=sim.simxGetObjectHandle(clientID,'Revolute_joint1',sim.simx_opmode_blocking)
        res,jh[1]=sim.simxGetObjectHandle(clientID,'Revolute_joint2',sim.simx_opmode_blocking)
        res,jh[2]=sim.simxGetObjectHandle(clientID,'Revolute_joint3',sim.simx_opmode_blocking)
        res,jh[3]=sim.simxGetObjectHandle(clientID,'Revolute_joint4',sim.simx_opmode_blocking)
        res,jh[4]=sim.simxGetObjectHandle(clientID,'Revolute_joint5',sim.simx_opmode_blocking)
        res,jh[5]=sim.simxGetObjectHandle(clientID,'Revolute_joint6',sim.simx_opmode_blocking)
        time.sleep(1)
        joints = np.empty(dof, dtype=object)  # create an empty array for the joints
        # set all joints to 0
        for i in range(dof):
            sim.simxSetJointPosition(clientID, jh[i], 0.0, sim.simx_opmode_blocking)

        # Now retrieve streaming data (i.e. in a non-blocking fashion):
        startTime = time.time()
        sim.simxGetIntegerParameter(clientID, sim.sim_intparam_mouse_x,
                                    sim.simx_opmode_streaming)  # Initialize streaming
        while True:
            if keyboard.is_pressed("q"):
                print("You pressed q, so you quit the program")
                break
            returnCode, angles = sim.simxGetObjectOrientation(clientID, handle_tcp, -1, sim.simx_opmode_blocking)  # Try to retrieve orientation
            returnCode, pos = sim.simxGetObjectPosition(clientID, handle_tcp, -1, sim.simx_opmode_blocking)  # Try to retrieve position
            # retrieve the joint positions
            for i in range(dof):
                returnCode, joints[i] = sim.simxGetJointPosition(clientID, jh[i], sim.simx_opmode_blocking)
            if returnCode == sim.simx_return_ok:  # After initialization of streaming, it will take a few ms before the first value arrives, so check the return code
                print('angles: ', angles)  # from CoppeliaSim
                R1 = matrix_from_euler(angles)
                print('TCP Rotation: ')
                print(R1)                   # from CoppeliaSim
                print('TCP Position: ', pos) # from CoppeliaSim
                TCP_frame = forward_kinematics(snake_robot, joints)
                print(TCP_frame)  #from your calculation, must be same to CoppeliaSim
                #time.sleep(0.005)
            # wait for keyboard press g
            while not keyboard.is_pressed("g"):  # press g to go on
                pass
            # set joints to random position between [-pi, pi]
            for i in range(dof):
                sim.simxSetJointPosition(clientID, jh[i], uniform(-math.pi, math.pi), sim.simx_opmode_blocking)


        # Now send some data to CoppeliaSim in a non-blocking fashion:
        sim.simxAddStatusbarMessage(clientID, 'Hello CoppeliaSim!', sim.simx_opmode_non_blocking)

        # Before closing the connection to CoppeliaSim, make sure that the last command sent out had time to arrive. You can guarantee this with (for example):
        sim.simxGetPingTime(clientID)

        # Now close the connection to CoppeliaSim:
        sim.simxFinish(clientID)
    else:
        print('Failed connecting to remote API server')
    print('Program ended')
