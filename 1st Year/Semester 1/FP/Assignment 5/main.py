
from ui import *
from service import *


service = Service()
ui = UI(service)

service.addExpense(Expense(3,200,"water"))
service.addExpense(Expense(2,220,"gas"))
service.addExpense(Expense(20,300,"water"))
service.addExpense(Expense(15,900,"electricity"))
service.addExpense(Expense(23,100,"water"))
service.addExpense(Expense(5,20,"gas"))
service.addExpense(Expense(3,2,"heat"))
service.addExpense(Expense(7,10,"heat"))
service.addExpense(Expense(1,230,"heat"))
service.addExpense(Expense(17,100,"water"))

ui.start()
