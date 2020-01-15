from copy import deepcopy


def valid(list, length):
    if list[length] <= list[length - 1] and length > 0:
        return False
    return True


def solution(s, index, n):
    sum = 0
    for i in range(index):
        sum += s[i]
    if sum % n == 0:
        return True
    return False


def backtracking(list, s, k, n):
    for i in range(len(list)):
        s[k] = list[i]
        if valid(s, k) == True:
            if solution(s, k + 1, n) == True:
                printing(s, k + 1)
            if k + 1 < len(list):
                backtracking(list, s, k + 1, n)


def printing(list, n):
    for i in range(n):
        print(str(list[i]), end=" ")
    print("")


def main():
    n = int(input("Enter n: "))
    values = input("Enter values separated by space: ")
    list = values.split()
    for i in range(len(list)):
        list[i] = int(list[i])
    s = deepcopy(list)
    list.sort()
    backtracking(list, s, 0, n)


main()

