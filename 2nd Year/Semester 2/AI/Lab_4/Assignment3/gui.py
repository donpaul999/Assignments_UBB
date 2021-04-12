# -*- coding: utf-8 -*-

from pygame.locals import *
import pygame, time
from utils import *
from domain import *
import threading

def initPyGame(dimension):
    # init the pygame
    pygame.init()
    logo = pygame.image.load("logo32x32.png")
    pygame.display.set_icon(logo)
    pygame.display.set_caption("drone exploration with AE")
    
    # create a surface on screen that has the size of 800 x 480
    screen = pygame.display.set_mode(dimension)
    screen.fill(WHITE)
    return screen


def closePyGame():
    # closes the pygame
    running = True
    # loop for events
    while running:
        # event handling, gets all event from the event queue
        for event in pygame.event.get():
            # only do something if the event is of type QUIT
            if event.type == pygame.QUIT:
                # change the value to False, to exit the main loop
                running = False
    pygame.quit()
    

def movingDrone(currentMap, path, speed = 1,  sensors=None, markSeen = True):
    # animation of a drone on a path
    time.sleep(2)
    screen = initPyGame((currentMap.n * 20, currentMap.m * 20))

    drona = pygame.image.load("drona.png")
    running = True
    while running:
        for event in pygame.event.get():
            # only do something if the event is of type QUIT
            if event.type == pygame.QUIT:
                # change the value to False, to exit the main loop
                running = False
        for i in range(len(path)):
            screen.blit(image(currentMap), (0, 0))
            if markSeen:
                sensor = pygame.Surface((20, 20))
                brick = pygame.Surface((20, 20))
                discovered = pygame.Surface((20, 20))
                crossed = pygame.Surface((20, 20))
                brick.fill(GREEN)
                sensor.fill(RED)
                discovered.fill(GRAYBLUE)
                crossed.fill(GRAY)
                for j in range(i + 1):
                    x = path[j][0]
                    y = path[j][1]
                    if currentMap.surface[x][y] == 0:
                        screen.blit(brick, (y * 20, x * 20))
                    if currentMap.surface[x][y] == 3:
                        screen.blit(crossed, (y * 20, x * 20))

                    if currentMap.surface[x][y] == 2:
                            screen.blit(sensor, (y * 20, x * 20))
                            energy = 0
                            for i in sensors:
                                if i.x == x and i.y == y:
                                    energy = i.optimal_energy
                            for var in v:
                                aux = 0
                                x = path[j][0]
                                y = path[j][1]
                                while ((0 <= x + var[0] < currentMap.n and
                                        0 <= y + var[1] < currentMap.m) and
                                       currentMap.surface[x + var[0]][y + var[1]] != 1 and currentMap.surface[x + var[0]][y + var[1]] != 2 and aux < energy):
                                    x = x + var[0]
                                    y = y + var[1]
                                    screen.blit(discovered, (y * 20, x * 20))
                                    currentMap.surface[x][y] = 3
                                    aux += 1


            screen.blit(drona, (0 * 20, 0 * 20))
            pygame.display.flip()
            time.sleep(0.5 * speed)
        time.sleep(5)
    closePyGame()
        
def image(currentMap, colour = BLUE, background = WHITE):
    # creates the image of a map

    imagine = pygame.Surface((currentMap.n * 20, currentMap.m * 20))
    brick = pygame.Surface((20, 20))
    sensor = pygame.Surface((20, 20))

    sensor.fill(RED)
    brick.fill(colour)
    imagine.fill(background)
    for i in range(currentMap.n):
        for j in range(currentMap.m):
            if (currentMap.surface[i][j] == 1):
                imagine.blit(brick, (j * 20, i * 20))
            if (currentMap.surface[i][j] == 2):
                imagine.blit(sensor, (j * 20, i * 20))

    return imagine

#
#x = threading.Thread(target=movingDrone, args=(m, path,1,True))
#x.start()