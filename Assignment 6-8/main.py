#STUDENTS REGISTER MANAGEMENT

from ui import UI
from service import *
import random


s = Service()
ui = UI(s)


def GenerateStudents():
    for i in range(0, 10):
        id_nr = random.randint(0, 99999)
        name_nr = random.randint(100, 999999)
        name = chr((name_nr % 10) % (ord('z') - ord('a')) + ord('A'))
        name_nr //= 10
        while name_nr != 0:
            name = name + chr((name_nr % 10) % (ord('z') - ord('a')) + ord('a'))
            name_nr //= 10
        s.addStudent(Student(id_nr, name))   

def GenerateDisciplines():
    for i in range(0, 10):
     id_nr = random.randint(0, 99999)
     name_nr = random.randint(100, 999999)
     name = chr((name_nr % 10) % (ord('z') - ord('a')) + ord('A'))
     name_nr //= 10
     while name_nr != 0:
        name = name + chr((name_nr % 10) % (ord('z') - ord('a')) + ord('a'))
        name_nr //= 10
     s.addDiscipline(Discipline(id_nr, name))   

def GenerateGrades():
    for i in range(0, 10):
        discipline = random.choice(s._disciplines)
        student = random.choice(s._students)
        grade = random.randint(1,10)
        s.addGrade(Grade(discipline.disciplineId, student.studentId, grade))


GenerateStudents()
GenerateDisciplines()
GenerateGrades()

ui.start()
