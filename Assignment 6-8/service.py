from domain import *

class Service:
    def __init__(self):
        '''
        Initialize the list of grades, of students and of disciplines
        '''
        self._students = []
        self._disciplines = []
        self._grades = []

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
            
    def remove_grades_discipline(self, ID):
        '''
        Remove all grades associated to a discipline with a given ID
        '''
        for i in self._grades:
            if i.disciplineId == ID:
                self._grades.remove(i)
                break