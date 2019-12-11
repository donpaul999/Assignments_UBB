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
        if col is None:
            a = NotPerfectAI()
            col = a.calculateMove(board)
        return col

    def valid_moves(self, board):
        candidates = []
        for i in range(7):
            if board[5 * 7 + i] == 0:
                candidates.append(i)
        return candidates

    def calculateMove(self, board):
        col = self.best_move(board, 5)
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
                return (None, self.score_position(board, 'Y'))
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
                new_score = self.minmax(b_copy, depth - 1, False)[1]
                if new_score < value:
                    value = new_score
                    col = column
                return col, value

    def score_position(self, board, piece):
        score = 0
        for i in range(6):
            for j in range(7):
                if board[i * 7 + j] == 0:
                    score += self.scoreOfCoordinate(board, i, j, 1, -1)
        return score

    def scoreOfCoordinate(self, board, i ,j, player, opponent):
        score = 0
        score += self.scoreOfLine(board, i, j,-1, 0, -1, 6,  None, None, player, opponent)
        score += self.scoreOfLine(board, i, j, 0, -1, None, None, -1, 6, player, opponent)
        score += self.scoreOfLine(board, i, j, -1, 1, -1, 6, 7, -1, player, opponent)
        score += self.scoreOfLine(board, i, j, -1, -1, -1, 6, -1, 7, player, opponent)
        return score

    def scoreOfLine(self, board, i ,j, rowInc, colInc,firstRowCond,secRowCond, firstColCond, secondColCond, player, opponent):
        score = 0
        currentInLine = 0
        valInARow = 0
        valInARowPrev = 0
        row = i + rowInc
        col = j + colInc
        firstLoop = True
        while row != firstRowCond and col != firstColCond and board[row * 7 + col] != 0:
            if firstLoop == True:
                currentInLine = board[row * 7 + col]
                firstLoop = False
            if currentInLine == board[row * 7 + col]:
                valInARow += 1
            else:
                break
            row += rowInc
            col += colInc

        row = i - rowInc
        col = j - colInc
        firstLoop = True
        try:
            while row != secRowCond and col != secondColCond and board[row * 7 + col] != 0:
                if firstLoop == True:
                    firstLoop = False
                    if currentInLine != board[row * 7 + col]:
                        if valInARow == 3 and currentInLine == player:
                            score += 1
                        elif valInARow == 3 and currentInLine == opponent:
                            score -= 1
                    else:
                        valInARowPrev = valInARow

                    valInARow = 0
                    currentInLine = board[row * 7 + col]

                if currentInLine == board[row * 7 + col]:
                    valInARow += 1
                else:
                    break
                row -= rowInc
                col -= colInc

            if valInARowPrev + valInARow >= 3 and currentInLine == player:
                score += 1
            elif valInARowPrev + valInARow >= 3 and currentInLine == opponent:
                score -= 1
        except:
            pass

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


