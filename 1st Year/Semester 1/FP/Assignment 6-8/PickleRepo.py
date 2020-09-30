from Repo import *
import pickle

class PickleRepo(Repository):
    def __init__(self, filename, undoController):
        Repository.__init__(self, undoController)
        self._filename = filename

    def __readAllFromBinaryFile(self):
        self._data = []
        try:
            with open(self._filename, "rb") as file:
                self._data = pickle.load(file)
        except EOFError:
            return

    def __writeAllToBinaryFile(self):
        with open(self._filename, "wb") as file:
            pickle.dump(self._data, file)
        file.close()

    def add(self, entity):
        self.__readAllFromBinaryFile()
        Repository.add(self, entity)
        self.__writeAllToBinaryFile()

    def update(self, id, name):
        self.__readAllFromBinaryFile()
        Repository.update(self, id, name)
        self.__writeAllToBinaryFile()

    def remove(self, id):
        self.__readAllFromBinaryFile()
        Repository.remove(self, id)
        self.__writeAllToBinaryFile()

class GradePickleRepo(GradesRepository):
    def __init__(self, filename, undoController):
        GradesRepository.__init__(self, undoController)
        self._filename = filename

    def __readAllFromBinaryFile(self):
        self._grades = []
        try:
            with open(self._filename, "rb") as file:
                self._grades = pickle.load(file)
        except EOFError:
            return

    def __writeAllToBinaryFile(self):
        with open(self._filename, "wb") as file:
            pickle.dump(self._grades, file)
        file.close()

    def add(self, grade, students, disciplines):
        self.__readAllFromBinaryFile()
        GradeRepository.add(self, grade, students, disciplines)
        self.__writeAllToBinaryFile()

    def remove(self, id, type):
        self.__readAllFromBinaryFile()
        list = GradeRepository.remove(self, id, type)
        self.__writeAllToBinaryFile()
        return list

    def specific_remove(self, grade):
        self.__readAllFromBinaryFile()
        GradeRepository.specific_remove(self, grade)
        self.__writeAllToBinaryFile()

    def add_grades(self,grades):
        self.__readAllFromBinaryFile()
        GradeRepository.add_gradesJ(self, grades)
        self.__writeAllToBinaryFile()