from tkinter import *
from connect4 import *
import pygame

pygame.init()

squaresize = 100
width = 7 * squaresize
height = 7  * squaresize

size = (width, height)
screen = pygame.display.set_mode(size)


def draw_board(board):
    pass



b = Board()
ai = NotPerfectAI()
g = Game(b, ai)
ui = UI(g)
ui.start()