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





last_name_list = [ 'Ciupe', 'Comsa', 'Curila', 'Colta', 'Curbat', 'Craciun', 'Comanac', 'Cheran', 'Chis', 'Chis', 'Copindean', 'Cimpean', 'Demian', 'Custura', 'Creta', 'Cioroga', 'Cosma', 'Condrea', 'Craiu', 'Ciorba', 'Capra', 'David', 'Caravia', 'Cirtorosan', 'Deiac', 'Clapou', 'Carare' ]
first_name_list = [ 'Sergiu', 'Filip', 'Sebastian', 'Paul', 'Alexandra', 'Flaviu', 'Dragos', 'Bianca', 'Sergiu', 'Matei', 'Alex', 'Andreea', 'Ana', 'Octavian', 'Florin', 'Rares', 'Eduard', 'Adrian', 'Tiberiu', 'Rares', 'Paul', 'Catalin', 'Andrei', 'Dragos', 'David', 'Alexandru', 'Claudiu' ]
classes = ['Maths', 'History', 'Chemistry', 'English', 'Fundamentals', 'History', 'French']

def GenerateStudents():
    for i in range(0, 10):
        id_nr = random.randint(0, 100)
        pos = random.randint(0,len(last_name_list) - 1)
        try:
            studentRepo.add(Student(id_nr, last_name_list[pos] + " " +first_name_list[pos]))
        except:
            i -= 1

def GenerateDisciplines():
    for i in range(0, 10):
     id_nr = random.randint(0, 100)
     pos = random.randint(0, len(classes) - 1)
     try:
         disciplineRepo.add(Discipline(id_nr, classes[pos]))
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
