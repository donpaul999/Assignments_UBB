import math
import random
from copy import deepcopy

class RandomMoveComputer:
    def calculateMove(self, board):
        candidates = []
        for i in range(7):
            if board[5 * 7 + i] == 0:
                candidates.append(i)
        return random.choice(candidates)


class NotPerfectAI():
    def calculateMove(self, board):
        board2 = board
        move = self.calculateBestMove(board2)
        return move

    def calculateBestMove(self, board):
        for i in range(7):
            list = deepcopy(board)
            try:
                self.move(list, i, 'Y')
                if self.isAlmostWon(list, i) == True:
                    return i
            except:
                pass
        for i in range(7):
            list = deepcopy(board)
            try:
                self.move(list, i, 'B')
                if self.isAlmostWon(list, i) == True:
                    return i
            except:
                pass

        r = RandomMoveComputer()
        return r.calculateMove(board)

    def move(self, board, x, color):
        d = {'B': 1, ' ': 0, 'Y': -1}
        if board[5 * 7 + x] != 0:
            raise ValueError("Move not inside the board!")
        i = 0
        while i < 6:
            if board[x + 7 * i] == 0:
                break
            i += 1
        board[x + 7 * i] = d[color]

    def isAlmostWon(self, board, last_move):
        if last_move == -1:
            return False
        poz = 5
        while board[poz * 7 + last_move] == 0:
            poz -= 1
        symbol = board[poz * 7 + last_move]
        dlin = [1, 0, -1, 0, 1, -1, -1, 1]
        dcol = [0, 1, 0, -1, 1, 1, -1, -1]
        for i in range(8):
            count = 0
            xnou = poz + dlin[i]
            ynou = last_move + dcol[i]
            xstart = xnou
            ystart = ynou
            while xnou > -1 and ynou > -1 and xnou < 6 and ynou < 7 and symbol == board[xnou * 7 + ynou]:
                count += 1
                xnou += dlin[i]
                ynou += dcol[i]
            xnou = xstart
            ynou = ystart
            count -= 1
            while xnou > -1 and ynou > -1 and xnou < 6 and ynou < 7 and symbol == board[xnou * 7 + ynou]:
                count += 1
                xnou -= dlin[i]
                ynou -= dcol[i]
            if count > 3:
                return True
        return False


class BestAI():

    def get_next_open_row(self, board, column):
        for row in range(6):
            if board[7 * row + column] == 0:
                return row

    def best_move(self, board, depth):
        col, score = self.minmax(board, depth, True)
        if col is None: #THIS IS NOT FOR A RANDOM MOVE - when user is almost close to win, minmax
            a = NotPerfectAI() #return none instead of column
            col = a.calculateMove(board)
        return col

    def valid_moves(self, board):
        candidates = []
        for i in range(7):
            if board[5 * 7 + i] == 0:
                candidates.append(i)
        return candidates

    def calculateMove(self, board):
        col = self.best_move(board, 3)
        return col

    def minmax(self, board, depth, maximizingPlayer):
        valid = self.valid_moves(board)
        terminal = self.is_terminal(board, valid)
        if depth == 0 or terminal == True:
            if terminal == True:
                if self.calculateBestMove(board, 'Y') == True:
                     return (None, 1000000000000)
                elif self.calculateBestMove(board, 'B') == True:
                    return (None, -1000000000000)
                else:
                    return (None, 0)
            else:
                return (None, self.score_position(board, -1 ))
        if maximizingPlayer:
            value = -math.inf
            col = random.choice(valid)
            for column in valid:
                row = self.get_next_open_row(board, column)
                b_copy = board.copy()
                b_copy[row * 7 + column] = -1
                new_score = self.minmax(b_copy, depth - 1, False)[1]
                if new_score > value:
                    value = new_score
                    col = column
            return col, value
        else:
            value = math.inf
            for column in valid:
                row = self.get_next_open_row(board, column)
                b_copy = board.copy()
                b_copy[row * 7 + column] = 1
                new_score = self.minmax(b_copy, depth - 1, True)[1]
                if new_score < value:
                    value = new_score
                    col = column
            return col, value

    def ev_window(self, window, piece):
        score = 0
        if window.count(piece) == 4:
            score += 100
        elif window.count(piece) == 3 and window.count(0) == 1:
            score += 5
        elif window.count(piece) == 2 and window.count(0) == 2:
            score += 2

        op = (-1) * piece
        if window.count(op) == 3 and window.count(0) == 1:
            score -= 6
        return score

        

    def score_position(self, board, piece):
        score = 0
        center = []
        window = []
        row = []
        col = []
        #center
        for r in range(6):
            center.append(board[r * 7 + 3])
        center_piece = center.count(piece)
        score += 3 * center_piece
        #horizontal
        for r in range(0, 42, 7):
            row = board[r: r + 7]
            for c in range(4): #col -3
                window = row[c: c + 4]
                score += self.ev_window(window, piece)

        #Vertical
        for c in range(7):
            for i in range(6):
                col.append(board[i * 7 + c])
            for r in range(3):
                window = col[r: r + 4]
                score += self.ev_window(window, piece)

        #Diag
        for r in range(3):
            for c in range(4):
                 window = [board[(r + i)* 7 + c + i] for i in range(4)]
                 score += self.ev_window(window, piece)

                
        for r in range(3):
            for c in range(4):
                window = [board[(r + 3 - i) * 7 + c + i] for i in range(4)]
                score += self.ev_window(window, piece)

        return score


    def is_terminal(self, board, valid):
        return self.calculateBestMove(board, 'Y') == True or self.calculateBestMove(board, 'B') == True or len(valid) == 0

    def calculateBestMove(self, board, symbol):
        for i in range(7):
            list = deepcopy(board)
            try:
                self.move(list, i, symbol)
                if self.isAlmostWon(list, i) == True:
                    return True
            except:
                pass
        return False

    def move(self, board, x, color):
        d = {'B': 1, ' ': 0, 'Y': -1}
        if board[5 * 7 + x] != 0:
            raise ValueError("Move not inside the board!")
        i = 0
        while i < 6:
            if board[x + 7 * i] == 0:
                break
            i += 1
        board[x + 7 * i] = d[color]

    def isAlmostWon(self, board, last_move):
        if last_move == -1:
            return False
        poz = 5
        while board[poz * 7 + last_move] == 0:
            poz -= 1
        symbol = board[poz * 7 + last_move]
        dlin = [1, 0, -1, 0, 1, -1, -1, 1]
        dcol = [0, 1, 0, -1, 1, 1, -1, -1]
        for i in range(8):
            count = 0
            xnou = poz + dlin[i]
            ynou = last_move + dcol[i]
            xstart = xnou
            ystart = ynou
            while xnou > -1 and ynou > -1 and xnou < 6 and ynou < 7 and symbol == board[xnou * 7 + ynou]:
                count += 1
                xnou += dlin[i]
                ynou += dcol[i]
            xnou = xstart
            ynou = ystart
            count -= 1
            while xnou > -1 and ynou > -1 and xnou < 6 and ynou < 7 and symbol == board[xnou * 7 + ynou]:
                count += 1
                xnou -= dlin[i]
                ynou -= dcol[i]
            if count > 3:
                return True
        return False


