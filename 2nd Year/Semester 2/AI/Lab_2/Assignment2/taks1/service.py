import pickle,pygame,time
from pygame.locals import *
from random import random, randint
import numpy as np

#Creating some colors
BLUE  = (0, 0, 255)
GRAYBLUE = (50,120,120)
RED   = (255, 0, 0)
GREEN = (0, 255, 0)
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)

#define directions
UP = 0
DOWN = 2
LEFT = 1
RIGHT = 3


class Service():
    def customSort(self, open, totalDistance):
        list = open
        ordered = False
        while ordered == False:
            ordered = True
            for i in range(0, len(list) - 1):
                if totalDistance[list[i][0]][list[i][1]] > totalDistance[list[i + 1][0]][list[i + 1][1]]:
                    aux = list[i]
                    list[i] = list[i + 1]
                    list[i + 1] = aux
                    ordered = False
        return list

    def add_open(self, open, new_node, totalDistance):
        for node in open:
            if node == new_node and totalDistance[node[0]][node[1]] <= totalDistance[new_node[0]][new_node[1]]:
                return False
        return True

    def searchAStar(self, mapM, initialX, initialY, finalX, finalY):
        print("Final " + str(finalX) + " " + str(finalY))
        dlin = [-1, 0, 1, 0]
        dcol = [0, 1, 0, -1]

        open = []
        closed = []
        parent = {}

        start = [initialX, initialY]
        end = [finalX, finalY]
        parent[str(start)] = None
        parent[str(end)] = None
        last = start

        totalDistance = np.zeros((mapM.n, mapM.m))
        startDistance = np.zeros((mapM.n, mapM.m))
        goalDistance = np.zeros((mapM.n, mapM.m))
        open.append(start)
        while len(open) > 0:

            open = self.customSort(open, totalDistance)

            current = open.pop(0)

            closed.append(current)
            #print(parent)

            if current == end:
                path = []
                while current != start:
                    #print(current)
                    path.append([current[0], current[1]])
                    current = parent[str(current)]
                path.append([current[0], current[1]])
                #print(path)
                return path[::-1]

            for i in range(0, 4):
                new_pos = [current[0] + dlin[i], current[1] + dcol[i]]
                if new_pos not in closed and new_pos[0] > 0 and new_pos[0] < mapM.n and new_pos[1] > 0 and new_pos[1] < mapM.m:
                    parent[str(new_pos)] = current
                    if mapM.surface[new_pos[0]][new_pos[1]] == 0:
                        startDistance[new_pos[0]][new_pos[1]] = startDistance[current[0]][current[1]]
                        goalDistance[new_pos[0]][new_pos[1]] = 1
                        totalDistance[new_pos[0]][new_pos[1]] = startDistance[new_pos[0]][new_pos[1]] + goalDistance[new_pos[0]][new_pos[1]]

                        if self.add_open(open, new_pos, totalDistance):
                            open.append(new_pos)
        return None



    def searchGreedy(self, mapM, droneD, initialX, initialY, finalX, finalY):
        # TO DO
        # implement the search function and put it in controller
        # returns a list of moves as a list of pairs [x,y]
        pass

    def dummysearch(self):
        # example of some path in test1.map from [5,7] to [7,11]
        return [[5, 7], [5, 8], [5, 9], [5, 10], [5, 11], [6, 11], [7, 11]]

    def displayWithPath(self, image, path):
        mark = pygame.Surface((20, 20))
        mark.fill(GREEN)
        for move in path:
            image.blit(mark, (move[1] * 20, move[0] * 20))

        return image

