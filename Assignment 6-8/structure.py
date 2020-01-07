from domain import *
from service import *
from undo import *

class Structure:
    def __init__(self):
        self._data = []

    def __iter__(self):
        return StructureIterator(self)

    def add(self, object):
        self._data.append(object)

    def __getitem__(self, key):
        return self._data[key]

    def __setitem__(self, key, item):
        self._data[key] = item

    def __delitem__(self, key):
        self._data.remove(self._data[key])

    def cmp(self, x1, x2, sign):
        if sign == 'ascending':
            if x1.ID < x2.ID:
                return 0
            return 1
        else:
            if x1.ID > x2.ID:
                return 0
            return 1

    def sort(self,sign):
        pos = 0
        while pos < len(self._data):
            if pos == 0 or self.cmp(self._data[pos], self._data[pos - 1], sign) ==  1:
                pos += 1
            else:
                aux = self._data[pos]
                self._data[pos] = self._data[pos - 1]
                self._data[pos - 1] = aux
                pos -= 1

    def filterBy(self, FilterBy):
        for i in range(len(self._data) - 1, -1, -1):
            if FilterBy(self._data[i]) == False:
                self.__delitem__(i)

class StructureIterator:
    def __init__(self, structure):
        self._structure = structure
        self._index = 0

    def __next__(self):
        if self._index < (len(self._structure._data)):
            result = self._structure._data[self._index]
            self._index += 1
            return result
        raise  StopIteration


def Filter(x):
    if x.ID < 2:
        return False
    return True
