#STUDENTS REGISTER MANAGEMENT
import random
from domain import *
from Repo import *
from ui import *
from service import *
from undo import *

undoController = UndoController()

studentRepo = Repository(undoController)
disciplineRepo = Repository(undoController)
gradesRepo = GradesRepository(undoController)

studentService = StudentService(studentRepo)
disciplineService = DisciplineService(disciplineRepo)
gradeService = GradeService(gradesRepo)
service = Service()


ui = UI(studentService, disciplineService, gradeService, service, undoController)





last_name_list = [ 'Ciupe', 'Comsa', 'Curila', 'Colta', 'Curbat', 'Craciun', 'Comanac', 'Cheran', 'Chis', 'Chis', 'Copindean', 'Cimpean', 'Demian', 'Custura', 'Creta', 'Cioroga', 'Cosma', 'Condrea', 'Craiu', 'Ciorba', 'Capra', 'David', 'Caravia', 'Cirtorosan', 'Deiac', 'Clapou', 'Carare' ]
first_name_list = [ 'Sergiu', 'Filip', 'Sebastian', 'Paul', 'Alexandra', 'Flaviu', 'Dragos', 'Bianca', 'Sergiu', 'Matei', 'Alex', 'Andreea', 'Ana', 'Octavian', 'Florin', 'Rares', 'Eduard', 'Adrian', 'Tiberiu', 'Rares', 'Paul', 'Catalin', 'Andrei', 'Dragos', 'David', 'Alexandru', 'Claudiu' ]
classes = ['Maths', 'History', 'Chemistry', 'English', 'Fundamentals', 'ASC', 'French', 'Geometry', 'German', 'Logic']

def GenerateStudents():
    i = 0
    while i < 10 :
        id_nr = random.randint(0, 100)
        pos = random.randint(0,len(last_name_list) - 1)
        try:
            studentRepo.add(Student(id_nr, last_name_list[pos] + " " +first_name_list[pos]))
            last_name_list.pop(pos)
            first_name_list.pop(pos)
        except:
            i -= 1
        i += 1

def GenerateDisciplines():
    i = 0
    while i < 10 :
     id_nr = random.randint(0, 100)
     pos = random.randint(0, len(classes) - 1)
     try:
         disciplineRepo.add(Discipline(id_nr, classes[pos]))
         classes.pop(pos)
     except:
         i -= 1
     i += 1

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
