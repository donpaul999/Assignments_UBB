#APARTMENT BUILDING ADMINISTRATOR - Domain

def validate_data(ap_id, type_e, amount, type_list): # Validate input data
    if ap_id <= 0 : #Apartment id should be > 0
        return "Invalid apartament id!"
    if amount <= 0: #The cost should be > 0
        return "Invalid cost!"
    ok = 0
    for t in type_list: #Type of expense should be found in the list
        if type_e == t:
            ok = 1
    if ok == 0:
        return "Type of expense is not on the list!"
    return None

def validate_expense(expense, expenses): #Verify if the expense is on the list
    for e in expenses:
        if e == expense:
            return 1
    return None 



def create_apartment(ap_id, type_e, amount): #Create an apartment
    return{"ap_id":ap_id, type_e:amount, "total": get_total_expenses(ap_id) + amount}


#Getter functions
def get_ap_id(apartment):
    return apartment["ap_id"]
def get_total_expense_amount(apartments, expense):
    sum = 0
    for i in apartments:
        try:
            sum += i[expense]
        except:
            sum += 0
    return sum
def get_ap_amount_for_type(apartment, type_e): #Get the amount of money to be payed for a known expense
    try:
        return apartment[type_e]
    except:
        return 0
def get_total_expenses(apartment): #Get the total amount of money to be payed assigned to an apartment
    try:
      return apartment["total"]
    except:
      return 0    
#Getter functions end

def init_apartments(): #Initialize apartments list
    res = []
    res.append(create_apartment(25, "gas", 100))
    res.append(create_apartment(24, "gas", 131))
    res.append(create_apartment(21, "gas", 1))
    res.append(create_apartment(10, "water", 52))
    res.append(create_apartment(12, "gas", 21))
    res.append(create_apartment(2, "gas", 21))
    res.append(create_apartment(6, "gas", 92))
    res.append(create_apartment(1, "gas", 10))
    res.append(create_apartment(100, "gas", 11))
    return res


def init_expenses(): #Initialize expenses list
    res = []
    res.append("gas")
    res.append("water")
    res.append("electricity")
    res.append("heat")
    res.append("other")
    return res

