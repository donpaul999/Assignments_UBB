#STUDENTS REGISTER MANAGEMENT
import random
from domain import *
from Repo import *
from ui import *
from service import *
from undo import *
from TextRepository import *
from PickleRepo import *
from jsonRepo import *

undoController = UndoController()


def load_properties(filepath, sep= "="):
    '''
    Read the file passed as parameter as a properties file
    '''
    props = {}
    with open(filepath, "rt") as file:
        for line in  file:
            l = line.strip()
            if len(l)!=0 :
                key_value = l.split(sep)
                key = key_value[0].strip()
                value = key_value[1].strip()
                props[key] = value
    return props

def start_program():
    props = load_properties("settings.properties")
    if props['repository'] == 'inmemory':
        studentRepo = Repository(undoController)
        disciplineRepo = Repository(undoController)
        gradeRepo = GradesRepository(undoController)
    elif props['repository'] == 'CSV':
        studentRepo = TextRepository(props['students'], 's',undoController)
        disciplineRepo = TextRepository(props['disciplines'], 'd', undoController)
        gradeRepo = GradesTextRepository(props['grades'], undoController, studentRepo._data, disciplineRepo._data)
    elif props['repository'] == 'binary':
        studentRepo = PickleRepo(props['students'], undoController)
        disciplineRepo = PickleRepo(props['disciplines'], undoController)
        gradeRepo = GradePickleRepo(props['grades'], undoController)
    elif props['repository'] == 'json':
        studentRepo = StudentsJsonRepository(props['students'], undoController)
        disciplineRepo = DisciplinesJsonRepository(props['disciplines'], undoController)
        gradeRepo= GradesJsonRepository(props['grades'], undoController)
    else:
        print("Repo type not avaliable")


    studentService = StudentService(studentRepo)
    disciplineService = DisciplineService(disciplineRepo)
    gradeService = GradeService(gradeRepo, studentRepo, disciplineRepo, undoController)
    service = Service()

    ui = UI(studentService, disciplineService, gradeService, service, undoController)
    ui.start()

start_program()

'''
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
    list = []
    while i < 10 :
     id_nr = random.randint(0, 100)
     pos = random.randint(0, len(classes) - 1)
     try:
         list.append = (Discipline(id_nr, classes[pos]))
         classes.pop(pos)
     except:
         i -= 1
    disciplineRepo.pstore(list, 'd')
     i += 1

def GenerateGrades():
    for i in range(0, 10):
        discipline = random.choice(disciplineRepo._data)
        student = random.choice(studentRepo._data)
        grade = random.randint(1,10)
        gradesRepo.add(Grade(discipline.ID, student.ID, grade))
    #print(gradesRepo._data)


#GenerateStudents()
#GenerateDisciplines()
#GenerateGrades()
'''