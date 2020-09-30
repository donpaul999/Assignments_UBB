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


def backtracking(list, n):
    s = [0] * len(list)
    rank = [0] * len(list)
    k = 0
    i = 0
    while k != -1:
        s[k] = list[i]
        if valid(s, k) == True:
            if solution(s, k + 1, n) == True:
                printing(s, k + 1)
            rank[k] = i
            k += 1
            i = 0
        elif i < len(list) - 1 and k < len(list) - 1:
            i += 1
        else:
            k -= 1
            if k != -1:
                k -= 1
                i = rank[k] + 1
                while k > - 1 and i == len(list):
                    i = rank[k] + 1
                    k -= 1


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
    list.sort()
    backtracking(list, n)


main()
