from Repo import *
from domain import *

class TextRepository(Repository):
    def __init__(self, fileName, type, undoController):
        super().__init__(undoController)
        self._fileName = fileName
        self._type = type
        self._loadFile()

    def _loadFile(self):
        f = open(self._fileName,'r')
        while True:
            s = f.readline().strip()
            if not s:
                break
            list = s.split(',')
            if self._type == 's':
                object = Student(*list)
            else:
                object = Discipline(*list)
            self.store(object)
        f.close()

    def store(self, object):
        Repository.add(self, object)

    def add(self, object):
        Repository.add(self, object)
        self._saveFile(object)

    def _saveFile(self, object):
        f = open(self._fileName, 'a')
        s = str(object.ID) + "," + str(object.Name) + '\n'
        f.write(s)
        f.close()

    def remove(self, ID):
        Repository.remove(self, ID)
        self._rewriteFile()

    def update(self, id, name):
        Repository.update(self, id, name)
        self._rewriteFile()

    def _rewriteFile(self):
        f = open(self._fileName, 'w')
        list = Repository.getAll(self)
        for object in list:
          s = str(object.ID) + "," + str(object.Name) + '\n'
          f.write(s)
        f.close()

class GradesTextRepository(GradesRepository):
    def __init__(self, fileName, undoController, studentList, disciplineList):
        super().__init__(undoController)
        self._fileName = fileName
        self._stud = studentList
        self._disc = disciplineList
        self._loadFile()

    def _loadFile(self):
        f = open(self._fileName, 'r')
        while True:
            s = f.readline().strip()
            if not s:
                break
            list = s.split(',')
            object = Grade(list[1], list[0], list[2])
            self.store(object)
        f.close()

    def store(self, object):
        GradesRepository.add(self,object,self._stud, self._disc)

    def add(self, object):
        GradesRepository.add(self, object, self._stud, self._disc)
        self._saveFile(object)

    def _saveFile(self, object):
        f = open(self._fileName, 'a')
        s = str(object.studentId) + "," + str(object.disciplineId) + "," + str(object.Value) + '\n'
        f.write(s)
        f.close()

    def remove(self, ID, type):
        list = GradesRepository.remove(self, ID, type)
        self._rewriteFile()
        return list

    def specific_remove(self, grade):
        GradesRepository.specific_remove(self, grade)
        self._rewriteFile()

    def _rewriteFile(self):
        f = open(self._fileName, 'w')
        list = GradesRepository.getAll(self)
        for object in list:
            s = str(object.studentId) + "," + str(object.disciplineId) + "," + str(object.Value) + '\n'
            f.write(s)
        f.close()

    def add_grades(self, grades):
        GradesRepository.add_grades(self, grades)
        self._rewriteFile()
