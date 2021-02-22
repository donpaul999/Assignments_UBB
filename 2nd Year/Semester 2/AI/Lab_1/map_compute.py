import random

ROW_LENGTH = 10
COL_LENGTH = 10



def readUDMSensors(row, col):
    return random.randint(0, 3), random.randint(0, 3), random.randint(0, 3), random.randint(0, 3)

def isInside(row, col):
    return row < ROW_LENGTH and col < COL_LENGTH and row >= 0 and col >= 0

def dfs(row, col, map, visited):
    map[row][col] = 0
    visited[row][col] = 1
    up, right, down, left = readUDMSensors(row, col)
    for i in range(0, up):
        if not isInside(row - i - 1, col):
            break
        if visited[row - i - 1][col] == 0:
            dfs(row - i - 1, col, map, visited)

    for i in range(0, right):
        if not isInside(row, col + i + 1):
            break
        if visited[row][col + i + 1] == 0:
            dfs(row, col + i + 1, map, visited)

    for i in range(0, down):
        if not isInside(row + i + 1, col):
            break
        if visited[row + i + 1][col] == 0:
            dfs(row + i + 1, col, map, visited)

    for i in range(0, left):
        if not isInside(row, col - i - 1):
            break
        if visited[row][col - i - 1] == 0:
            dfs(row, col - i - 1, map, visited)

def main():
    map = [[1]*COL_LENGTH]*ROW_LENGTH
    visited = [[0]*COL_LENGTH]*ROW_LENGTH
    dfs(random.randint(0, ROW_LENGTH), random.randint(0, COL_LENGTH), map, visited)
    print(map)
    print("************")
main()