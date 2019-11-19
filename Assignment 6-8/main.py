#STUDENTS REGISTER MANAGEMENT

from ui import UI
from service import *
import random


s = Service()
ui = UI(s)


def GenerateStudents():
    for i in range(0, 10):
        id_nr = random.randint(0, 100)
        length = random.randint(4,15)
        number = random.randint(0,25)
        name = chr(number + ord('A'))
        for j in range (0, length):
             number = random.randint(0,25)
             name = name + chr(number + ord('a'))
        try:
            s.addStudent(Student(id_nr, name))   
        except:
            i -= 1

def GenerateDisciplines():
    for i in range(0, 10):
     id_nr = random.randint(0, 100)
     length = random.randint(4,15)
     number = random.randint(0,25)
     name = chr(number + ord('A'))
     for j in range (0, length):
          number = random.randint(0,25)
          name = name + chr(number + ord('a'))
     try:
         s.addDiscipline(Discipline(id_nr, name))   
     except:
         i -= 1

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
