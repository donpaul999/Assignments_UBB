from domain import *
from Repo import *

class StudentService:
    def __init__(self, studentRepo):
        self._studentRepo = studentRepo

    def add(self, object):
        self._studentRepo.add(object)

    def getAll(self):
        return self._studentRepo.getAll()

    def update(self, id, name):
        try:
            id = int(id)
        except:
            e = Exception()
            e.PositiveID()
        self._studentRepo.update(id, name)

    def remove(self, id):
        try:
            id = int(id)
        except:
            e = Exception()
            e.PositiveID
        self._studentRepo.remove(id)

    def search_using_id(self, id):
       return self._studentRepo.search_using_id(id)

    def search_using_name(self, text):
            return self._studentRepo.search_using_name(text)


class DisciplineService:
        def __init__(self, disciplineRepo):
            self._disciplineRepo = disciplineRepo

        def add(self, object):
            self._disciplineRepo.add(object)

        def getAll(self):
            return self._disciplineRepo.getAll()

        def update(self, id, name):
            try:
                id = int(id)
            except:
                e = Exception()
                e.PositiveID()
            self._disciplineRepo.update(id, name)

        def remove(self, id):
            try:
                id = int(id)
            except:
                e = Exception()
                e.PositiveID
            self._disciplineRepo.remove(id)

        def search_using_id(self, id):
            return self._disciplineRepo.search_using_id(id)

        def search_using_name(self, text):
            return self._disciplineRepo.search_using_name(text)

class GradeService:
        def __init__(self, gradeRepo):
            self._gradeRepo = gradeRepo

        def add(self, grade ,studentList, disciplineList):
                self._gradeRepo.add(grade, studentList, disciplineList)

        def getAll(self):
            return self._gradeRepo.getAll()

        def remove(self, id, type):
            self._gradeRepo.remove(id, type)
