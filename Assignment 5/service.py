from domain import Expense
from copy import deepcopy

class Service:
    def __init__(self):
        '''
        Initialize the expenses and history lists with an empty space.
        '''
        self._expenses = []
        self._history = []
        
    def addExpense(self, expense):
       '''
       Appends the new expense to the list and to the history list
       '''
       self._history.append(deepcopy(self._expenses[:]))
       self._expenses.append(expense)

    

    def filterExpenses(self, x):
        '''
        parameters - x
        Filters the expenses with the amount < x
        '''
        ok = 0
        self._history.append(deepcopy(self._expenses[:]))
        try:
            for i in self._expenses:
                if int(i.Amount) <= int(x):
                    i.Amount = 0
                    ok = 1
        except:
            raise ValueError("The value should be an integer!")
        if ok == 0:
            self._history.pop()

    @property
    def Expenses(self):
        return self._expenses

    def undo(self):
        if len(self._history) == 0:
            raise ValueError("No more undos!")
        self._expenses.clear()
        self._expenses.extend(self._history.pop())

def test_filter():
    v = Service()
    v.addExpense(Expense(3,200,"water"))
    v.addExpense(Expense(3,20,"gas"))
    v.filterExpenses(100)
    ok = 0
    for i in v._expenses:
        if i.Amount == 0:
           ok = 1
    assert ok == 1

test_filter()