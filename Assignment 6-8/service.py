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


class GradeService:
        def __init__(self, gradeRepo):
            self._gradeRepo = gradeRepo

        def getAll(self):
            return self._gradeRepo.getAll()

class Service:
    def addStudent(self, student):
        '''
        Appends to the list a new student, if the student is valid, otherwise there will be an error
        '''
        ok = 1
        for i in self._students:
            if student.studentId == i.studentId:
                ok = 0
                break
        if ok == 1:
            self._students.append(student)
        else:
            e = Exception()
            e.IDUsed()
            
    def addDiscipline(self, discipline):
        '''
        Appends to the list a new discipline, if the discipline is valid, otherwise there will be an error
        '''
        ok = 1
        for i in self._disciplines:
            if discipline.disciplineId == i.disciplineId:
                ok = 0
                break
        if ok == 1:
            self._disciplines.append(discipline)
        else:
            e = Exception()
            e.IDUsed()

    def addGrade(self, grade):
        ok1 = 0
        ok2 = 0
        e = Exception()
        for i in self._disciplines:
            if grade.disciplineId == i.disciplineId:
                ok1 = 1
                break

        for i in self._students:
            if grade.studentId == i.studentId:
                ok2 = 1
                break
        if ok1 == ok2 and ok1 == 1:
            self._grades.append(grade)
        else:
            e.GradeNotValid()

    def update_student(self, id, name):
        '''
        Appends to a students with a given ID a new name
        '''
        ok = 0
        for i in self._students:
            if int(i.studentId) == int(id):
                i.Name = name
                ok = 1
                break
        if ok == 0:
            e = Exception()
            e.IDNotFound()
            
    def update_discipline(self, id, name):
        '''
        Appends to a discipline with a given ID a new name
        '''
        ok = 0
        for i in self._disciplines:
            if int(i.disciplineId) == int(id):
                i.Name = name
                ok = 1
                break
        if ok == 0:
            e = Exception()
            e.IDNotFound()    
            
    def remove_student(self, id):
        '''
        Remove a student and all the grades associated
        '''
        ok = 0
        for i in self._students:
            if i.studentId == int(id):
                self.remove_grades_student(int(id))
                self._students.remove(i)
                ok = 1
                break
        if ok == 0:
            e = Exception()
            e.IDNotFound()

    def remove_discipline(self, id):
        '''
        Remove a discipline and all the grades associated
        '''
        ok = 0
        for i in self._disciplines:
            if i.disciplineId == int(id):
                self.remove_grades_discipline(int(id))
                self._disciplines.remove(i)
                ok = 1
                break
        if ok == 0:
            e = Exception()
            e.IDNotFound()

    def remove_grades_student(self, ID):
        '''
        Remove all grades associated to a student with a given ID
        '''
        for i in self._grades:
            if i.studentId == ID:
                self._grades.remove(i)
                break
            
    def remove_grades(self, ID):
        '''
        Remove all grades associated to a discipline with a given ID
        '''
        for i in self._data:
            if i.ID == ID:
                self._grades.remove(i)
                break

    def search_using_ID_discipline(self, id):
        e = Exception()
        for i in self._disciplines:
            if int(i.disciplineId) == id:
                return i
        e.IDNotFound()

    def search_using_name_disciplines(self, name):
        e = Exception()
        list = []
        for i in self._disciplines:
            if i.Name.find.upper()(name.upper()) != -1:
                list.append(i)
        if len(list) != 0:
            return list
        else:
            e.NameNotFound()