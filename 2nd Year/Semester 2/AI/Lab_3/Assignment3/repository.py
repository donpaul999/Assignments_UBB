# -*- coding: utf-8 -*-

import pickle
from domain import *


class Repository():
    def __init__(self):
        self.__populations = []
        self.cmap = Map()
        self.drone = [0,0]

    def setDronePositions(self, x, y):
        self.drone = [x, y]

    def addIndividual(self, population, individual):
        population.addIndividual(individual, self.cmap, self.drone)

    def evaluatePopulation(self, population):
        population.evaluate(self.cmap, self.drone)

    def computeAverageFitnessAndDeviation(self):
        return self.__populations[-1].computeAverageFitnessAndDeviation(self.cmap, self.drone)

    def currentPopulation(self):
        return self.__populations[-1]

    def createPopulation(self, args):
        # args = [populationSize, individualSize] -- you can add more args    
        return Population(args[0], args[1])

    def addPopulation(self, population):
        self.__populations.append(population)

    def randomDrone(self):
        x = randint(0, self.cmap.n - 1)
        y = randint(0, self.cmap.m - 1)
        while self.cmap.surface[x][y] != 0:
            x = randint(0, self.cmap.n - 1)
            y = randint(0, self.cmap.m - 1)
        self.drone = [x,y]

    def getFirstPath(self):
        return self.__populations[-1].getFirstPath(self.cmap, self.drone)

    # TO DO : add the other components for the repository: 
    #    load and save from file, etc
            