#STUDENTS REGISTER MANAGEMENT

from ui import UI
from service import *


s = Service()
ui = UI(s)

s.addStudent(Student(1,"Ana"))
s.addStudent(Student(2,"Ciupe"))
s.addStudent(Student(3,"Clapou"))
s.addStudent(Student(4,"Dud"))
s.addStudent(Student(5,"Ananas"))
s.addStudent(Student(6,"Anat"))
s.addStudent(Student(7,"Ioana"))
s.addStudent(Student(8,"Andreea"))
s.addStudent(Student(9,"Andrei"))
s.addStudent(Student(10,"Alex"))


s.addDiscipline(Discipline(1,"Maths"))
s.addDiscipline(Discipline(2,"English"))
s.addDiscipline(Discipline(3,"Chemistry"))
s.addDiscipline(Discipline(4,"History"))
s.addDiscipline(Discipline(5,"ASC"))
s.addDiscipline(Discipline(6,"Fiziq"))
s.addDiscipline(Discipline(7,"FP"))
s.addDiscipline(Discipline(8,"DAT"))
s.addDiscipline(Discipline(9,"Geometry"))
s.addDiscipline(Discipline(10,"TTC"))


s.addGrade(Grade(2, 1, 5))
s.addGrade(Grade(2, 2, 10))
s.addGrade(Grade(3, 7, 4))
s.addGrade(Grade(4, 2, 1))
s.addGrade(Grade(4, 2, 1))
s.addGrade(Grade(6, 5, 9))
s.addGrade(Grade(7, 5, 2))
s.addGrade(Grade(8, 9, 2))
s.addGrade(Grade(2, 4, 5))
s.addGrade(Grade(10, 3, 10))



ui.start()
