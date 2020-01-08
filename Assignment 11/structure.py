class Structure:
    def __init__(self):
       self._data = []

    def __iter__(self):
        return StructureIterator(self)

    def add(self, object):
        self._data.append(object)


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


 --> 'FIND MORE IN ASSIGNMENT 6-8'
s = Structure()
s.add("mar")
s.add("par")
for i in s:
    print(i)




