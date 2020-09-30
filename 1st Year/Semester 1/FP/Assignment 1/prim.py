import math

#Check if the paramater is a prime number
def is_prime(x):
    for j in range(2, (int(math.sqrt(x)) + 1) ,1): #At least a proper factor should be smaller than sqrt of the number
            if x % j == 0:
                return False
    return True



def larger_prime(x):
    ok = 0;
    #Check the exceptions - 2, and numbers smaller than 2.
    if x == 2:
        return x + 1
    if x < 2:
        return 2
    while ok == 0:
        x += 1
        ok = 1 #Assume that x is a prime number
        if is_prime(x) == False:
            ok = 0 #Finding that x is NOT a prime number
    return x

n = int(input("Give n: "))

print("The first prime number larger than n is: ")
print(larger_prime(n))
