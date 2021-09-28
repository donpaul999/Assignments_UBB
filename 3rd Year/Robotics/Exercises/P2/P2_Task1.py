# This is a sample Python script for task1 of exercise 2.
# you need to open the scene euler_angle.ttt in CoppeliaSim first
import keyboard
import numpy as np
import math

############################################################################
# this needs to be implemented
def convert_quaternion_to_matrix(quat):
    q0 = quat[0]
    q1 = quat[1]
    q2 = quat[2]
    q3 = quat[3]

    # First row of the rotation matrix
    Rot_00 = 2 * (q0 * q0 + q1 * q1) - 1
    Rot_01 = 2 * (q1 * q2 - q0 * q3)
    Rot_02 = 2 * (q1 * q3 + q0 * q2)

    # Second row of the rotation matrix
    Rot_10 = 2 * (q1 * q2 + q0 * q3)
    Rot_11 = 2 * (q0 * q0 + q2 * q2) - 1
    Rot_12 = 2 * (q2 * q3 - q0 * q1)

    # Third row of the rotation matrix
    Rot_20 = 2 * (q1 * q3 - q0 * q2)
    Rot_21 = 2 * (q2 * q3 + q0 * q1)
    Rot_22 = 2 * (q0 * q0 + q3 * q3) - 1

    # 3x3 rotation matrix
    Rot = np.array([[Rot_00, Rot_01, Rot_02],
                           [Rot_10, Rot_11, Rot_12],
                           [Rot_20, Rot_21, Rot_22]])

    return Rot

############################################################################
# this needs to be implemented
def euler_from_matrix(R):
    sy = math.sqrt(R[0,0] * R[0,0] +  R[1,0] * R[1,0])

    singular = sy < 1e-6

    if  not singular :
        x = math.atan2(R[2,1] , R[2,2])
        y = math.atan2(-R[2,0], sy)
        z = math.atan2(R[1,0], R[0,0])
    else :
        x = math.atan2(-R[1,2], R[1,1])
        y = math.atan2(-R[2,0], sy)
        z = 0

    return [x, y, z]

############################################################################
# this needs to be implemented
def matrix_from_euler(angles):
    Rot_x = np.array([[1, 0, 0],
                    [0, math.cos(angles[0]), -math.sin(angles[0])],
                    [0, math.sin(angles[0]), math.cos(angles[0])]
                    ])

    Rot_y = np.array([[math.cos(angles[1]), 0, math.sin(angles[1])],
                    [0, 1, 0],
                    [-math.sin(angles[1]), 0, math.cos(angles[1])]
                    ])

    Rot_z = np.array([[math.cos(angles[2]), -math.sin(angles[2]), 0],
                    [math.sin(angles[2]), math.cos(angles[2]), 0],
                    [0, 0, 1]
                    ])

    Rot = np.dot(Rot_z, np.dot(Rot_y, Rot_x))

    return Rot

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
