#STUDENTS REGISTER MANAGEMENT
import random
from domain import *
from Repo import *
from ui import *
from service import *

studentRepo = Repository()
disciplineRepo = Repository()
gradesRepo = GradesRepository()

studentService = StudentService(studentRepo)
disciplineService = DisciplineService(disciplineRepo)
gradeService = GradeService(gradesRepo)

ui = UI(studentService, disciplineService, gradeService)

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
            studentRepo.add(Student(id_nr, name))
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
         disciplineRepo.add(Discipline(id_nr, name))
     except:
         i -= 1

def GenerateGrades():
    for i in range(0, 10):
        discipline = random.choice(disciplineRepo._data)
        student = random.choice(studentRepo._data)
        grade = random.randint(1,10)
        gradesRepo.add(Grade(discipline.ID, student.ID, grade),studentRepo._data ,disciplineRepo._data)
    print(gradesRepo._data)


GenerateStudents()
GenerateDisciplines()
GenerateGrades()

ui.start()
