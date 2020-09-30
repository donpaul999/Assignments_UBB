from Repo import *
import json
import os

class StudentEncoder(json.JSONEncoder):
    def default(self, s):
        if isinstance(s, Student):
            return (s.ID, s.Name)
        else:
            return super().default(s)

class DisciplineEncoder(json.JSONEncoder):
    def default(self, d):
        if isinstance(d, Discipline):
            return (d.ID, d.Name)
        else:
            return super().default(d)

class GradeEncoder(json.JSONEncoder):
    def default(self, g):
        if isinstance(g, Grade):
            return (g.studentId, g.disciplineId, g.Value)
        else:
            return super().default(g)



class StudentsJsonRepository(Repository):
    def __init__(self, filename, undoController):
        Repository.__init__(self, undoController)
        self._filename = filename
        self._encoder = StudentEncoder()

    def __readAllFromJsonFile(self):
        self._data = []
        with open(self._filename, "r") as file:
            if os.stat(self._filename).st_size == 0:
                return
            list = json.load(file)
        for s in list:
            s = s[1:len(s)-1]
            idx = s.find(",")
            id = int(s[:idx])
            name = s[idx+3:len(s)-1].strip()
            self._data.append(Student(id, name))

    def __writeAllToJsonFile(self):
        list = []
        for s in self._data:
            list.append(self._encoder.encode(s))
        with open(self._filename, "w") as file:
            json.dump(list, file)

    def add(self, object):
        self.__readAllFromJsonFile()
        Repository.add(self, object)
        self.__writeAllToJsonFile()

    def remove(self,id):
        self.__readAllFromJsonFile()
        Repository.remove(self, id)
        self.__writeAllToJsonFile()

    def update(self, id, name):
        self.__readAllFromJsonFile()
        Repository.update(self, id, name)
        self.__writeAllToJsonFile()

class DisciplinesJsonRepository(Repository):
    def __init__(self, filename, undoController):
        Repository.__init__(self, undoController)
        self._filename = filename
        self._encoder = DisciplineEncoder()

    def __readAllFromJsonFile(self):
        self._data = []
        with open(self._filename, "r") as file:
            if os.stat(self._filename).st_size == 0:
                return
            list = json.load(file)
        for s in list:
            s = s[1:len(s)-1]
            idx = s.find(",")
            id = int(s[:idx])
            name = s[idx+3:len(s)-1].strip()
            self._data.append(Discipline(id, name))

    def __writeAllToJsonFile(self):
        list = []
        for s in self._data:
            list.append(self._encoder.encode(s))
        with open(self._filename, "w") as file:
            json.dump(list, file)

    def add(self, object):
        self.__readAllFromJsonFile()
        Repository.add(self, object)
        self.__writeAllToJsonFile()

    def remove(self,id):
        self.__readAllFromJsonFile()
        Repository.remove(self, id)
        self.__writeAllToJsonFile()

    def update(self, id, name):
        self.__readAllFromJsonFile()
        Repository.update(self, id, name)
        self.__writeAllToJsonFile()

class GradesJsonRepository(GradesRepository):
    def __init__(self, filename, undoController):
        GradesRepository.__init__(self, undoController)
        self._filename = filename
        self._encoder = GradeEncoder()

    def __readAllFromJsonFile(self):
        self._grades = []
        with open(self._filename, "r") as file:
            if os.stat(self._filename).st_size == 0:
                return
            list = json.load(file)
        for s in list:
            s = s[1:len(s)-1]
            list = s.split(",")
            sid = int(list[0])
            did = int(list[1])
            value = int(list[2])
            self._grades.append(Grade(sid, did, value))

    def __writeAllToJsonFile(self):
        list = []
        for s in self._grades:
            list.append(self._encoder.encode(s))
        with open(self._filename, "w") as file:
            json.dump(list, file)

    def add(self, grade, students, disciplines):
        self.__readAllFromJsonFile()
        GradeRepository.add(self, grade, students, disciplines)
        self.__writeAllToJsonFile()

    def remove(self, id, type):
        self.__readAllFromJsonFile()
        list = GradeRepository.remove(self, id, type)
        self.__writeAllToJsonFile()
        return list

    def specific_remove(self, grade):
        self.__readAllFromJsonFile()
        GradeRepository.specific_remove(self, grade)
        self.__writeAllToJsonFile()

    def add_grades(self,grades):
        self.__readAllFromJsonFile()
        GradeRepository.add_grades(self, grades)
        self.__writeAllToJsonFile()

