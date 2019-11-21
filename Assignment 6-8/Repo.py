from domain import *


class Repository:
    def __init__(self):
        self._data = []
        


    def add(self, object):
        ok = 1
        for i in self._data:
            if object.ID == i.ID:
                ok = 0
                break
        if ok == 1:
            self._data.append(object)
        else:
            e = Exception()
            e.IDUsed()

    def update(self, ID, name):
        ok = 0
        for i in self._data:
            if int(i.ID) == int(ID):
                i.Name = name
                ok = 1
                break
        if ok == 0:
            e = Exception()
            e.IDNotFound()

    def remove(self, id):
        '''
        Remove a student and all the grades associated
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
    def __init__(self):
        self._data = []

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
        else:
            e.GradeNotValid()

    def getAll(self):
        return self._data

    def remove(self, id, type):
        try:
            if type != "s" and type != "d":
                raise ValueError()
            id = int(id)
            if type == "s":
                for i in self._data:
                    if i.studentId == id:
                        self._data.remove(i)
            else:
                for i in self._data:
                    if i.disciplineId == id:
                        self._data.remove(i)
        except:
            e = Exception()
            e.PositiveID()