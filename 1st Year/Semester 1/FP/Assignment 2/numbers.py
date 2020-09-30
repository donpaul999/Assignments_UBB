import math


#Create a complex number using the parameters
def create_complex(n_id, realp = -1, imaginaryp = 0):
    return {"id": n_id, "real": realp, "imaginary":imaginaryp}

#Input

def read_number(numberList): #Read numbers from input
    realp = int(input("Number's real part= "))
    imaginaryp = int(input("Number's imaginary part= "))
    for num in numberList:
        n_id = get_number_id(num)
    n_id += 1
    return create_complex(n_id,realp, imaginaryp)

def add_numbers(numberList): #First option, appeding new complex numbers to the dictionary
    number = read_number(numberList)
    numberList.append(number)

#Input End

#Get functions
def get_number_realp(n):
    return n["real"]
def get_number_imaginaryp(n):
    return n["imaginary"]
def get_number_id(n):
    return n["id"]
#Get functions end



#Output
def property_output(numberList, start_pos, end_pos): #Output for the 3rd and 4th options
    print("**************")
    if start_pos == -1:
        print("There are no numbers to have this property!")
    else:
        for num in numberList:
             id_n = get_number_id(num)
             if id_n >= start_pos and id_n <= end_pos:
                 id_mom = id_n - start_pos + 1
                 print(str(id_mom) + ". " + str(get_number_realp(num)) +" + " +str(get_number_imaginaryp(num)) + "i")
    print("**************")


def show_numbers(numberList): #Output for the second option
    print("**************")
    print("List of numbers")
    for num in numberList:
        print(str(get_number_id(num))+". "  + str(get_number_realp(num)) +" + " +str(get_number_imaginaryp(num)) + "i")
    print("**************")
#Output end

#First property
def is_real(n): #Verify if the number is real
    return get_number_imaginaryp(n) == 0


def get_real_numbers(numberList):
    '''
    Computes the longest sequence of the real numbers from the list
    Input : list of numbers
    Output: tuple - start position of the first element with the property
                    final position of the last element with the property
    '''
    length = 0
    maxim = -1
    pos = -1
    start_pos = -1
    for num in numberList: #Compute the start postion of the longest sequence that matches the property
        if is_real(num) == 1:
            if length == 0:
                pos = get_number_id(num)
            length += 1
        elif length > maxim:
            maxim = length
            start_pos = pos
            length = 0
    if length > maxim:
            maxim = length
            start_pos = pos
    end_pos = start_pos + maxim - 1 #Compute the end position of the sequence
    return start_pos, end_pos

#First property end

   
#Second property


def modulus(num): #Compute the modulus of a complex number
    '''
    Computes the modulus of a complex numbers using the following formula
    z = a + b*i => |z| = sqrt(a * a + b * b)
    '''
    real_n = get_number_realp(num)
    img_n = get_number_imaginaryp(num)
    return math.sqrt(real_n * real_n + img_n*img_n)

def in_range(x): #Verify if the parameter is inside the [0,10] range
    return x >= 0 and x <= 10

def get_prime_difference(numberList):
     '''
    Computes the longest sequence of the prime numbers from the list
    Input : list of numbers
    Output: tuple - start position of the first element with the property
                    final position of the last element with the property
    '''
    length = 0
    maxim = -1
    pos = -1
    start_pos = -1
    for num in numberList:  #Compute the start postion of the longest sequence that matches the property
        modul = modulus(num)
        if in_range(modul) == 1:
            if length == 0:
                pos = get_number_id(num)
            length += 1
        elif length > maxim:
            maxim = length
            start_pos = pos
            length = 0
    if length > maxim:
         maxim = length
         start_pos = pos
    end_pos = start_pos + maxim - 1
    return start_pos, end_pos #Compute the end position
#Second property end


def print_menu(): #Prints the menu that pops-up when the code is executed
    print("1. Add a number")
    print("2. Show numbers")
    print("3. Show the longest sequence that consists of only real numbers")
    print("4. Show the longest sequence that has the property that the modulus of all elements is in the [0, 10] range")
    print("5. Exit app")


def init_numbers(): #Initialize the numbers list
    res = []
    res.append(create_complex(1,10,2))
    res.append(create_complex(2,4,2))
    res.append(create_complex(3,5,1))
    res.append(create_complex(4,210,1))
    res.append(create_complex(5,120,1))
    res.append(create_complex(6,104,21))
    res.append(create_complex(7,105,1))
    res.append(create_complex(8,150,1))
    res.append(create_complex(9,92,9))
    res.append(create_complex(10,1,2))
    return res
    
#Error for invalid input
def exception():
        print("**************")
        print("Invalid command")
        print("**************")
    


def start():
    numbers = init_numbers()
    while True:
        print_menu()
        choice = input(">")
        if choice == "1":
            add_numbers(numbers)
        elif choice == "2":
            show_numbers(numbers)
        elif choice == "3":
            start_pos, end_pos = get_real_numbers(numbers)
            property_output(numbers,start_pos,end_pos)
        elif choice == "4":
            start_pos, end_pos = get_prime_difference(numbers)
            property_output(numbers,start_pos,end_pos)
        elif choice == "5":
            return
        else:
            exception()

start()
