from domain import *
from undo import *
from structure import *

class Repository(Structure):
    def __init__(self, undoController):
        super().__init__()
        self._data = []
        self._undoController = undoController

    def add(self, object):
        ok = 1
        for i in self._data:
            if object.ID == i.ID:
                ok = 0
                break
        if ok == 1:
            self._data.append(object)
            undo = FunctionCall(self.remove, object.ID)
            redo = FunctionCall(self.add, object)
            op = Operation(undo, redo)
            self._undoController.recordOperation(op)
        else:
            e = Exception()
            e.IDUsed()

    def find(self,id): #search
        '''
        finds an object in the list based on its ID
        :param id: the given id
        :return: the object if found, raises error otherwise
        '''
        for s in self._data:
            if int(s.ID) == int(id):
                return s
        return None


    def update(self, ID, name):
        ok = 0
        for i in self._data:
            if int(i.ID) == int(ID):
                undo = FunctionCall(self.update, ID, i.Name)
                redo = FunctionCall(self.update, ID, name)
                op = Operation(undo, redo)
                self._undoController.recordOperation(op)
                i.Name = name
                ok = 1
                break
        if ok == 0:
            e = Exception()
            e.IDNotFound()

    def remove(self, id):
        '''
        Remove a student/discipline and all the grades associated
        '''
        ok = 0
        for i in self._data:
            if i.ID == int(id):
                self._data.remove(i)
                ok = 1
                break
        if ok == 0:
            e = Exception()
            e.IDNotFound()


    def search_using_ID(self, id):
        e = Exception()
        for i in self._data:
            if int(i.disciplineId) == id:
                return i
        e.IDNotFound()

    def search_using_name(self, name):
        e = Exception()
        list = []
        for i in self._data:
            if i.Name.find.upper()(name.upper()) != -1:
                list.append(i)
        if len(list) != 0:
            return list
        else:
            e.NameNotFound()

    def getAll(self):
      return self._data

    def search_using_id(self,id):
        e = Exception()
        for i in self._data:
            if i.ID == id:
                return i
        e.IDNotFound()

    def search_using_name(self,name):
        e = Exception()
        list = []
        for i in self._data:
            text = i.Name.upper()
            if text.find(name.upper()) != -1:
                list.append(i)
        if len(list) != 0:
            return list
        else:
            e.NameNotFound()

class GradesRepository():
    def __init__(self, undoController):
        self._data = []
        self._undoController = undoController

    def add(self, grade, students, disciplines):
        ok1 = 0
        ok2 = 0
        e = Exception()
        for i in disciplines:
            if grade.disciplineId == i.ID:
                ok1 = 1
                break
        for i in students:
            if grade.studentId == i.ID:
                ok2 = 1
                break
        if ok1 == ok2 and ok1 == 1:
            self._data.append(grade)
            undo = FunctionCall(self.specific_remove, grade)
            redo = FunctionCall(self.add, grade)
            op = Operation(undo, redo)
            self._undoController.recordOperation(op)
        else:
            e.GradeNotValid()

    def getAll(self):
        return self._data

    def specific_remove(self, grade):
        for i in self._data:
            if i == grade:
                self._data.remove(i)
                break

    def remove(self, id, type):
        try:
            if type != "s" and type != "d":
                raise ValueError()
            id = int(id)
            list = []
            if type == "s":
                i = 0
                while i < len(self._data):
                    if int(self._data[i].studentId) == id:
                        list.append(self._data[i])
                        del self._data[i]
                        i = 0
                    i += 1
            else:
                i = 0
                while i < len(self._data):
                    if int(self._data[i].disciplineId) == id:
                        list.append(self._data[i])
                        del self._data[i]
                        i = 0
                    i += 1
            return list
        except:
            e = Exception()
            e.PositiveID()

    def add_grades(self, grades):
        for i in grades:
            self._data.append(i)