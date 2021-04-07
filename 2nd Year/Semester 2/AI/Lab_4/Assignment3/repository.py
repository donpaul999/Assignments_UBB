# -*- coding: utf-8 -*-

import pickle
from domain import *


class Repository():
    def __init__(self):
        self.__populations = []
        self.__colonies = []
        self.cmap = Map()
        self.drone = [0,0]


    def addColony(self, nb_of_ants, energy):
        self.__colonies.append(Colony(nb_of_ants, energy))

    def currentColony(self):
        return self.__colonies[-1]

    def getSensors(self):
        sensors = []
        for i in range(0, self.cmap.n):
            for j in range(0, self.cmap.m):
                if  self.cmap.surface[i][j] == 2:
                    s = Sensor(i, j)
                    s.discover_squares(self.cmap)
                    sensors.append(s)
        return sensors

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

    def computeBestFitnessLastestPopulation(self):
        return self.__populations[-1].getBestFitness(self.cmap, self.drone)

    # TO DO : add the other components for the repository: 
    #    load and save from file, etc
            