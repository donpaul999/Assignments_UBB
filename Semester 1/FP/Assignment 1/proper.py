def product_factors(x):
    p = 1
    for i in range(2, (x//2 + 1), 1):
        if x % i == 0:
            p = p * i;
    if p == 1:
        return 0 #If the number is prime or is {0,1}, the product is 0
    else:
        return p

n = int(input("Give n: "))
print("The product of all the proper factors of n is: ")
print(product_factors(n))
