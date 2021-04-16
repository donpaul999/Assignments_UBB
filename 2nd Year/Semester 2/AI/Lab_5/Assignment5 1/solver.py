# -*- coding: utf-8 -*-
"""
In this file your task is to write the solver function!

"""


def solver(t,w):
    t = 7
    w = -0.5
    """
    Parameters
    ----------
    t : TYPE: float
        DESCRIPTION: the angle theta
    w : TYPE: float
        DESCRIPTION: the angular speed omega

    Returns
    -------
    F : TYPE: float
        DESCRIPTION: the force that must be applied to the cart
    or

    None :if we have a division by zero

    """
    teta = {"NVB" : [-100, -25, -40], "NB": [-40, -10, -25], "N":[-20, 0, -10], "ZO":[-5, 5, 0], "P":[0, 20, 10], "PB":[10, 40, 25], "PVB":[25, 100, 40]}
    omega = {"NB" :[-20, -3, -8], "N" :[-6, 0, -3] , "ZO" : [-1, 1, 0], "P" :[0, 6, 3], "PB" : [3, 20, 8]}
    force = {'NVB+NB': 'NVVB', 'NVB+N': 'NVVB', 'NVB+ZO': 'NVB', 'NVB+P': 'NB', 'NVB+PB': 'N', 'NB+NB': 'NVVB', 'NB+N': 'NVB', 'NB+ZO': 'NB', 'NB+P': 'N', 'NB+PB': 'Z', 'N+NB': 'NVB', 'N+N': 'NB', 'N+ZO': 'N', 'N+P': 'Z', 'N+PB': 'P', 'ZO+NB': 'NB', 'ZO+N': 'N', 'ZO+ZO': 'Z', 'ZO+P': 'P', 'ZO+PB': 'PB', 'P+NB': 'N', 'P+N': 'Z', 'P+ZO': 'P', 'P+P': 'PB', 'P+PB': 'PVB', 'PB+NB': 'Z', 'PB+N': 'P', 'PB+ZO': 'PB', 'PB+P': 'PVB', 'PB+PB': 'PVVB', 'PVB+NB': 'P', 'PVB+N': 'PB', 'PVB+ZO': 'PVB', 'PVB+P': 'PVVB', 'PVB+PB': 'PVVB'}
    products = {'NVVB': -32, 'NVB': -24, 'NB': -16, 'N': -8, 'Z': 0, 'P': 8, 'PB': 16, 'PVB': 24, 'PVVB': 32}


    niuteta = {}

    for key in teta:
        niuteta[key] = 0
        if teta[key][0] <= t <= teta[key][1]:
            try:
                niuteta[key] = max(0, min((t - teta[key][0]) / (teta[key][2] - teta[key][0]), 1,
                                          (teta[key][1] - t) / (teta[key][1] - teta[key][2])))
            except:
                pass
            #print(niuteta[key])

    niuomega = {}
    for key in omega:
        niuomega[key] = 0
        if omega[key][0] <= w <= omega[key][1]:
            try:
                niuomega[key] = max(0, min((w - omega[key][0]) / (omega[key][2] - omega[key][0]), 1,
                                          (omega[key][1] - w) / (omega[key][1] - omega[key][2])))
            except:
                pass
            #print(niuomega[key])

    #print(niuteta)

    #print(niuomega)

    forces = {}
    for key in teta:
        for key_2 in omega:
            val = min(niuteta[key], niuomega[key_2])
            new_key = key + '+' + key_2
            if force[new_key] not in forces:
                forces[force[new_key]] = val
            elif forces[force[new_key]] < val:
                forces[force[new_key]] = val

    #print(forces)

    sum = 0
    product = 0
    for key in forces:
        sum += forces[key]
        product += forces[key] * products[key]
    try:
        f = sum / product
    except:
        f = 0
    print("XX:" + str(t) + ' ' + str(w) + ' ' + str(f))
    return f

