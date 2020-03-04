#Check if the parameter is a perfect number or not
def is_perfect(x):
    factors_sum = 1
    for i in range(2, ((x//2) + 1), 1):
        if x % i == 0:
            factors_sum = factors_sum + i
    return x == factors_sum


#Find the largest perfect number < n
def smaller_number(x):
    while x > 1:
        x -=1
        if is_perfect(x):
            return x

n = int(input("Give n: "))
if smaller_number(n) != 1:
    print("The largest perfect number smaller than n is: ")
    print(smaller_number(n))
else:
    print("There is no perfect number smaller than n!")
    
