from domain import *
from service import *
from undo import *

undoController = UndoController()
s = Repository(undoController)
studentService = StudentService(s)
undoController = UndoController()

studentService.add(Student(100,"mar"))
studentService.add(Student(1,"par"))

studentService._studentRepo.filterBy(Filter)
studentService.add(Student(1,"par"))
studentService._studentRepo.sort(cmp)

for i in studentService._studentRepo:
    print(i)