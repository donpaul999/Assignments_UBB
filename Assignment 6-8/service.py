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
        Appends to the list a new student
        '''
        ok = 1
        for i in self._students:
            if student.studentId == i.studentId:
                ok = 0
                break
        if ok == 1:
            self._students.append(student)
        else:
            raise ValueError("ID already used!")

    def addDiscipline(self, discipline):
        '''
        Appends to the list a new discipline
        '''
        ok = 1
        for i in self._disciplines:
            if discipline.disciplineId == i.disciplineId:
                ok = 0
                break
        if ok == 1:
            self._disciplines.append(discipline)
        else:
            raise ValueError("ID already used!")


    def addGrade(self, grade):
        self._grades.append(grade)
       

    def update_student(self, id, name):
        ok = 0
        for i in self._students:
            if int(i.studentId) == int(id):
                i.Name = name
                ok = 1
                break
        if ok == 0:
            raise ValueError("ID is not in the list!")

    def update_discipline(self, id, name):
        ok = 0
        for i in self._disciplines:
            if int(i.disciplineId) == int(id):
                i.Name = name
                ok = 1
                break
        if ok == 0:
            raise ValueError("ID is not in the list!")
    
    def remove_student(self, id):
        ok = 0
        for i in self._students:
            if i.studentId == int(id):
                self.remove_grades_student(int(id))
                self._students.remove(i)
                ok = 1
                break
        if ok == 0:
            raise ValueError("ID is not in the list!")

    def remove_discipline(self, id):
        ok = 0
        for i in self._disciplines:
            if i.disciplineId == int(id):
                self.remove_grades_discipline(int(id))
                self._disciplines.remove(i)
                ok = 1
                break
        if ok == 0:
            raise ValueError("ID is not in the list!")

    def remove_grades_student(self, ID):
        for i in self._grades:
            if i.studentId == ID:
                self._grades.remove(i)
                break
            
    def remove_grades_discipline(self, ID):
        for i in self._grades:
            if i.disciplineId == ID:
                self._grades.remove(i)
                break