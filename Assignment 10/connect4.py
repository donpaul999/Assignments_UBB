from texttable import Texttable
import random
from copy import deepcopy
from ai import *



class Game:
    def __init__(self, board, computerPlayer):
        self._board = board
        self._computerPlayer = computerPlayer

    def playerMove(self, x):
        self._board.move(x, 'B')

    def getBoard(self):
        self._board

    def computerMove(self, list):
        move = self._computerPlayer.calculateMove(list)
        self._board.move(move, 'Y')
        return move

class Board:
    def __init__(self):
        self._data = [0] * 42
        self._moves = 0


    def get(self, x):
        return self._data[7 * 5 + x] == 0


    def move(self, x, color):
        d = {'B': 1, 'Y': -1}
        if x > 6 or x < 0 or self._data[5 * 7 + x] != 0:
            raise ValueError("Move not inside the board!")
        i = 0
        while i < 6:
            if self._data[x + 7 * i] == 0:
                break
            i += 1
        self._data[x + 7 * i] = d[color]
        self._moves += 1

    def isWon(self, last_move):
        if last_move == -1:
            return False
        poz = 5
        while self._data[poz * 7 + last_move] == 0:
            poz -= 1
        symbol = self._data[poz * 7 + last_move]
        dlin = [1, 0, -1, 0, 1, -1 , -1, 1]
        dcol = [0, 1, 0, -1, 1, 1, -1, -1]
        for i in range(8):
            count = 0
            xnou = poz + dlin[i]
            ynou = last_move + dcol[i]
            xstart = xnou
            ystart = ynou
            while xnou > -1 and ynou > -1 and xnou < 6 and ynou < 7 and symbol == self._data[xnou * 7 + ynou]:
                count += 1
                xnou += dlin[i]
                ynou += dcol[i]
            xnou = xstart
            ynou = ystart
            count -= 1
            while xnou > -1 and ynou > -1 and xnou < 6 and ynou < 7 and symbol == self._data[xnou * 7 + ynou]:
                count += 1
                xnou -= dlin[i]
                ynou -= dcol[i]
            if count > 3:
                return True
        return False


    def isTie(self, last_move):
        return self.isWon(last_move) == False and self._moves  == 42



    def __str__(self):
       t = Texttable()
       l = []
       d = {-1: 'Y', 0: ' ', 1: 'B'}
       for i in range(0, 42, 7):
         row = self._data[i:i + 7]
         for j in range(7):
            row[j] = d[row[j]]
         l.append(row)
       s = len(l) - 1
       while s > -1:
         t.add_row(l[s])
         s -= 1
       return t.draw()


class UI:
    def __init__(self, game):
        self._game = game

    def _readPlayerMove(self):
        while True:
            try:
                cmd = input("Input move:").capitalize()
                cmd = int(cmd)
                if cmd > -1 and cmd < 7:
                    return cmd
                else:
                    raise ValueError("")
            except Exception:
                print("Invalid move!")

    def start(self):
        b = self._game._board
        l = self._game._board._data
        playerMove = True
        move = -1
        while b.isWon(move) == False and b.isTie(move) == False:
            if playerMove == True:
                print("******************")
                print(b)
            if playerMove == True:
                try:
                    move = self._readPlayerMove()
                    self._game.playerMove(move)
                except Exception as e:
                    print(e)
                    continue
            else:
                move = self._game.computerMove(l)
            playerMove = not playerMove
        if b.isTie(move):
            print("It's a draw!")
        elif playerMove == False:
            print("Congrats! You won!")
        else:
            print("******************")
            print("You were defeated!")
            print(b)


b = Board()
'''
b.move(0,'B')
b.move(1,'B')
b.move(1,'B')
b.move(1,'B')
b.move(1,'B')
b.move(1,'B')
b.move(1,'B')
b.move(2, 'B')
b.move(3, 'B')
b.move(4, 'B')
b.move(5, 'B')
b.move(6, 'B')
b.move(6, 'B')

print(b.isWon())
print(b)
'''

b = Board()
ai = BestAI()
g = Game(b, ai)
ui = UI(g)
ui.start()