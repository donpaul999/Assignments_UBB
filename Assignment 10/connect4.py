from texttable import Texttable
import random

class RandomMoveComputer:
    def calculateMove(self, board):
        candidates = []
        for i in range(6):
            for j in range(7):
                if board.get(i, j) == 0:
                    candidates.append((i, j))
        return random.choice(candidates)


class Game:
    def __init__(self, board, computerPlayer):
        self._board = board
        self._computerPlayer = computerPlayer

    def playerMove(self, x):
        self._board.move(x, 'B')

    def getBoard(self):
        self._board

    def computerMove(self):
        move = self._computerPlayer.calculateMove(self._board)
        self._board.move(move, 'Y')

class Board:
    def __init__(self):
        self._data = [0] * 42
        self._moves = 0


    def get(self, x, y):
        return self._data[7 * x + y]


    def move(self, x, color):
        d = {'B': 1, 'Y': -1}
        if x > 6 or x < 0 or self._data[x * 7 + 5] != 0:
            raise ValueError("Move not inside the board!")
        i = 0
        while i < 6:
            if self._data[x * 7 + i] == 0:
                break
            i += 1

        self._data[x * 6 + i] = d[color]
        self._moves += 1

    def isWon(self):
        print("XXX")
        for i in range(7):
            row = self._data[7 * i:7 * i + 7]
            col = self._data[i:i + 5]
            if abs(sum(row)) == 4 or abs(sum(col)) == 4:
                return True
        '''
        diagonal
        '''
        return False

    def isTie(self):
        return self.isWon() == False and self._moves  == 42



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
                return(int(cmd))
            except Exception:
                print("Invalid move!")

    def start(self):
        b = self._game.getBoard()
        playerMove = True
        while b.isWon() == False and b.isTie() == False:
            print(b)
            if playerMove == True:
                try:
                    move = self._readPlayerMove()
                    self._game.playerMove(move)
                except Exception as e:
                    print(e)
                    continue
            else:
                self._game.computerMove()
            playerMove = not playerMove
        if b.isTie():
            print("It's a draw!")
        elif playerMove == False:
            print("Congrats! You won!")
        else:
            print("You were defeated!")
        print(b)


b = Board()
b.move(1,'B')
b.move(2, 'B')
b.move(2, 'B')
print(b.isWon())
print(b)

'''
b = Board()
ai = RandomMoveComputer()
g = Game(b, ai)
ui = UI(g)
ui.start()
'''