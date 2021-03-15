# import the pygame module, so you can use it
import pickle,pygame,time
from pygame.locals import *
from random import random, randint
import numpy as np
from model import Drone, Map
from service import Service


#Creating some colors
BLUE  = (0, 0, 255)
GRAYBLUE = (50,120,120)
RED   = (255, 0, 0)
GREEN = (0, 255, 0)
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)

class UI:
    def __init__(self):
        self.m = Map()
        self.service = Service()
        self.d = Drone(0, 0)

    def prinMenu(self):
        print("1. AStar")
        print("2. Greedy")
        choice = int(input("Option: "))
        return choice

    def main(self):
        astar_or_greedy = self.prinMenu()
        # we create the map
        self.m.randomMap()
        #m.saveMap("test2.map")
        #self.m.loadMap("test1.map")


        # initialize the pygame module
        pygame.init()
        # load and set the logo
        logo = pygame.image.load("logo32x32.png")
        pygame.display.set_icon(logo)
        pygame.display.set_caption("Path in simple environment")

        # we position the drone somewhere in the area
        x = randint(0, 19)
        y = randint(0, 19)

        #random_ending
        destination_x = randint(0, 19)
        destination_y = randint(0, 19)

        while self.m.surface[destination_x][destination_y] != 0:
            destination_x = randint(0, 19)
            destination_y = randint(0, 19)

        print("Final " + str(destination_x) + " " + str(destination_y))

        #create drona
        self.d = Drone(x, y)

        # create a surface on screen that has the size of 400 x 480
        screen = pygame.display.set_mode((400,400))
        screen.fill(WHITE)


        # define a variable to control the main loop
        running = True

        # main loop
        while running:
            # event handling, gets all event from the event queue
            for event in pygame.event.get():
                # only do something if the event is of type QUIT
                if event.type == pygame.QUIT:
                    # change the value to False, to exit the main loop
                    running = False

                if event.type == KEYDOWN:
                    self.d.move(self.m) #this call will be erased


            screen.blit(self.d.mapWithDrone(self.m.image()),(0,0))
            pygame.display.flip()

        if astar_or_greedy == 1:
            path = self.service.searchAStar(self.m, x, y, destination_x, destination_y)
        else:
            path = self.service.searchGreedy(self.m, x, y, destination_x, destination_y)
        if path:
            screen.blit(self.service.displayWithPath(self.m.image(), path),(0,0))
        pygame.display.flip()
        time.sleep(5)
        pygame.quit()


