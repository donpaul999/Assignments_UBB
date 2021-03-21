# -*- coding: utf-8 -*-


# imports
from gui import *
from controller import *
from repository import *
from domain import *
import pygame

class UI:
    def __init__(self, controller, repository):
        self._controller = controller
        self._repository = repository

    def print_first_menu(self):
        print("1. create random map")
        print("2. load a map")
        print("3. save a map")
        print("4. visualise map")
        print("5. Next menu")

    def print_second_menu(self):
        print("1. parameters setup")
        print("2. run the solver")
        print("3. visualise the statistics")
        print("4. view the drone moving on a path")

    def random_map(self):
        self._repository.cmap.randomMap()

    def load_map(self):
        print("Map title:")
        numfile = input()
        self._repository.cmap.loadMap(numfile)

    def save_map(self):
        print("Map title:")
        numfile = input()
        self._repository.cmap.saveMap(numfile)

    def visualize_map(self):
        pygame.init()

        screen = pygame.display.set_mode((400, 400))
        screen.fill(WHITE)

        screen.blit(self._controller.mapWithDrone(self._repository.cmap.image()), (0, 0))
        pygame.display.flip()
        pygame.display.update()
        time.sleep(10)
        pygame.quit()

    def menu(self):
        option = -1
        while option != 5:
            self.print_first_menu()
            option = int(input())
            if option == 1:
                self.random_map()
            elif option == 2:
                self.load_map()
            elif option == 3:
                self.save_map()
            elif option == 4:
                self.visualize_map()
            elif option != 5:
                print("Invalid option")
        self.print_second_menu()
        option_2 = int(input())


# create a menu
#   1. map options:
#         a. create random map
#         b. load a map
#         c. save a map
#         d visualise map
#   2. EA options:
#         a. parameters setup
#         b. run the solver
#         c. visualise the statistics
#         d. view the drone moving on a path
#              function gui.movingDrone(currentMap, path, speed, markseen)
#              ATENTION! the function doesn't check if the path passes trough walls