import unittest
from domain import *

class TestStudents(unittest.TestCase):
    def test_student(self):
        st = Student(1,"test")
        assert st.Name == "test"
        assert st.studentId == 1
        st.Name = "Ana"
        assert st.Name == "Ana"

class TestDiscipline(unittest.TestCase):
    def test_discipline(self):
        d = Discipline(1,"test")
        assert d.Name == "test"
        assert d.disciplineId == 1
        d.Name = "Chemistry"
        assert d.Name == "Chemistry"


    
class TestGrades(unittest.TestCase):
    def test_grade(self):
        gr = Grade(2,1,7)
        assert gr.Value == 7
        assert gr.studentId == 1
        gr.Value = 10
        assert gr.Value == 10

    