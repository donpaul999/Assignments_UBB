# This is a sample Python script for task1 of exercise 2.
# you need to open the scene euler_angle.ttt in CoppeliaSim first
import keyboard
import numpy as np
import math

############################################################################
# this needs to be implemented
def convert_quaternion_to_matrix(quat):
    # First row of the rotation matrix
    r00 = 1.0
    r01 = 0.0
    r02 = 0.0

    # Second row of the rotation matrix
    r10 = 0.0
    r11 = 1.0
    r12 = 0.0
    # Third row of the rotation matrix
    r20 = 0.0
    r21 = 0.0
    r22 = 1.0
    # 3x3 rotation matrix
    rot_matrix = np.array([[r00, r01, r02],
                           [r10, r11, r12],
                           [r20, r21, r22]])
    return rot_matrix

############################################################################
# this needs to be implemented
def euler_from_matrix(R):
    eps = 1e-7
    ax, ay, az = 0.0, 0.0, 0.0
    return [ax, ay, az]

############################################################################
# this needs to be implemented
def matrix_from_euler(angles):
    # First row of the rotation matrix
    r00 = 1.0
    r01 = 0.0
    r02 = 0.0

    # Second row of the rotation matrix
    r10 = 0.0
    r11 = 1.0
    r12 = 0.0
    # Third row of the rotation matrix
    r20 = 0.0
    r21 = 0.0
    r22 = 1.0
    # 3x3 rotation matrix
    rot_matrix = np.array([[r00, r01, r02],
                           [r10, r11, r12],
                           [r20, r21, r22]])
    return rot_matrix

################################################################################
if __name__ == '__main__':
    # start simulation in CoppeliaSim, and then run this program.
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

        # Getting Handle of the Reference Frame
        return_code, handle_ref_frame = sim.simxGetObjectHandle(clientID, 'ReferenceFrame', sim.simx_opmode_blocking)
        time.sleep(1)

        startTime = time.time()

        while True:
            if keyboard.is_pressed("q"):
                print("You pressed q")
                break
            returnCode, quat = sim.simxGetObjectQuaternion(clientID, handle_ref_frame, -1, sim.simx_opmode_blocking)  # Try to retrieve

            if returnCode == sim.simx_return_ok:  #  check the return code
                print('quat: ', quat)

                # convert quaternion to matrix - needs to be implemented
                R = convert_quaternion_to_matrix(quat)
                print(R)
                # calculate euler angles from matrix - needs to be implemented
                euler_angles = euler_from_matrix(R)
                # print euler angles
                print("alpha:", euler_angles[0]*180.0/math.pi)  # print alpha in degree
                print("beta :", euler_angles[1]*180.0/math.pi)  # print beta  in degree
                print("gamma:", euler_angles[2]*180.0/math.pi)  # print gamma in degree

                # calculate euler angles from matrix - needs to be implemented
                R1 = matrix_from_euler(euler_angles)
                print(R1)
                time.sleep(0.005)

        # Now send some data to CoppeliaSim in a non-blocking fashion:
        sim.simxAddStatusbarMessage(clientID, 'Hello CoppeliaSim!', sim.simx_opmode_oneshot)

        # Before closing the connection to CoppeliaSim, make sure that the last command sent out had time to arrive. You can guarantee this with (for example):
        sim.simxGetPingTime(clientID)

        # Now close the connection to CoppeliaSim:
        sim.simxFinish(clientID)
    else:
        print('Failed connecting to remote API server')
    print('Program ended')
