# -*- coding: utf-8 -*-
import copy
import pickle
from random import *

import pygame

from utils import *
import numpy as np

# the class gene can be replaced with int or float, or other types
# depending on your problem's representation

class Gene:
    def __init__(self):
        self._coord = random.randint(1,4) # N - 1, E - 2, S - 3, V - 4

class Individual:
    def __init__(self, size = 0):
        self.__size = size
        self.__x = [randint(1,4) for i in range(self.__size)]
        self.f = None
        
    def fitness(self, currentMap, drone):
        self.f = 0
        path = self.computePath(currentMap, drone)
        #print(path)
        visited = []
        v = [[-1, 0], [0, 1], [1, 0], [0, -1]]
        for i in range(len(path)):
            x = path[i][0]
            y = path[i][1]
            if [x, y] not in visited:
                visited.append([x, y])
                if 0 > x or 0 > y or x >= currentMap.n or y >= currentMap.m:
                    break
                if currentMap.surface[x][y] == 1:
                    break

                self.f += 1
                for var in v:
                    while ((0 <= x + var[0] < currentMap.n and
                            0 <= y + var[1] < currentMap.m) and
                           currentMap.surface[x + var[0]][y + var[1]] != 1):
                            if [x + var[0], y + var[1]] not in visited:
                                visited.append([x + var[0], y + var[1]])
                                self.f += 1
                            x = x + var[0]
                            y = y + var[1]




    def computePath(self, currentMap, drone):
        path = [[drone[0], drone[1]]]
        for i in self.__x:
            if i == 1:
                path.append([path[-1][0] - 1, path[-1][1]])
            elif i == 2:
                path.append([path[-1][0], path[-1][1] + 1])
            elif i == 3:
                path.append([path[-1][0] + 1, path[-1][1]])
            elif i == 4:
                path.append([path[-1][0], path[-1][1] - 1])

        valid_path = []
        for p in path:
            if 0 > p[0] or 0 > p[1] or p[0] >= currentMap.n or p[1] >= currentMap.m:
                break
            if currentMap.surface[p[0]][p[1]] == 1:
                break
            valid_path.append(p)
        return valid_path

    def mutate(self, mutateProbability = 0.04):
        if random() < mutateProbability:
            self.__x[randint(0, self.__size - 1)] = randint(1,4)
        
    
    def crossover(self, otherParent, crossoverProbability = 0.8):
        offspring1, offspring2 = Individual(self.__size), Individual(self.__size) 
        if random() < crossoverProbability:
            position = randint(0, self.__size - 1)
            offspring1.__x = otherParent.__x[:position] + self.__x[position:]
            offspring2.__x = self.__x[:position] + otherParent.__x[position:]
        
        return offspring1, offspring2
    
class Population():
    def __init__(self, populationSize = 0, individualSize = 0):
        self.__populationSize = populationSize
        self.v = [Individual(individualSize) for x in range(self.__populationSize)]

    def evaluate(self, map, drone):
        # evaluates the population
        for x in self.v:
            x.fitness(map, drone)

    def computeAverageFitnessAndDeviation(self, map, drone):
        fitness = []
        for x in self.v:
            x.fitness(map, drone)
            fitness.append(x.f)
        return [np.average(fitness), np.std(fitness)]

    def size(self):
        return len(self.v)

    def setIndividuals(self, individuals):
        self.v = individuals

    def addIndividual(self, individual, map, drone):
        individual.fitness(map, drone)
        self.v.append(individual)

    def selection(self, k = 0):
        # perform a selection of k individuals from the population
        # and returns that selection
        selected = []
        individuals_copy = copy.deepcopy(self.v)
        individuals_copy = self.sortIndividuals(individuals_copy)

        for i in range(0, k):
            selected.append(individuals_copy[i])
        return selected

    def sortIndividuals(self, individuals):
        sorted = False
        while not sorted:
            sorted = True
            for i in range(0, len(individuals) - 1):
                if individuals[i].f < individuals[i + 1].f:
                    aux = individuals[i]
                    individuals[i] = individuals[i + 1]
                    individuals[i + 1] = aux
                    sorted = False
        return individuals

    def getFirstPath(self, map, drone):
        self.evaluate(map, drone)
        individuals_copy = copy.deepcopy(self.v)
        individuals_copy = self.sortIndividuals(individuals_copy)
        return individuals_copy[0].computePath(map, drone)

    def getBestFitness(self, map, drone):
        self.evaluate(map, drone)
        individuals_copy = copy.deepcopy(self.v)
        individuals_copy = self.sortIndividuals(individuals_copy)
        return individuals_copy[0].f

class Map():
    def __init__(self, n = 20, m = 20):
        self.n = n
        self.m = m
        self.surface = np.zeros((self.n, self.m))

    def randomMap(self, fill = 0.2):
        for i in range(self.n):
            for j in range(self.m):
                if random() <= fill :
                    self.surface[i][j] = 1
                
    def __str__(self):
        string=""
        for i in range(self.n):
            for j in range(self.m):
                string = string + str(int(self.surface[i][j]))
            string = string + "\n"
        return string
                
    def saveMap(self, numFile="test.map"):
        with open(numFile, 'wb') as f:
            pickle.dump(self, f)
            f.close()

    def loadMap(self, numfile):
        with open(numfile, "rb") as f:
            dummy = pickle.load(f)
            self.n = dummy.n
            self.m = dummy.m
            self.surface = dummy.surface
            f.close()

    def image(self, colour=BLUE, background=WHITE):
        imagine = pygame.Surface((400, 400))
        brick = pygame.Surface((20, 20))
        brick.fill(BLUE)
        imagine.fill(WHITE)
        for i in range(self.n):
            for j in range(self.m):
                if (self.surface[i][j] == 1):
                    imagine.blit(brick, (j * 20, i * 20))

        return imagine

