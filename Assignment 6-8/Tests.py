import unittest
from domain import Student
from domain import Discipline
from domain import Grade

class TestStudents(unittest.TestCase):
    def test_student(self):
        st = Student(1,"test")
        assert st.Name == "test"
        assert st.studentId == 1
        st.Name = "Ana"
        assert st.Name == "Ana"
        try:
            st.studentID = -1
            assert False
        except:
            assert True
        
        try:
            st.Name = 1
            assert False
        except:
            assert True
        
class TestDisciplines(unittest.TestCase):
    def test_discipline(self):
        d = Discipline(1,"test")
        assert d.Name == "test"
        assert d.disciplineId == 1
        d.Name = "Chemistry"
        assert d.Name == "Chemistry"
        try:
            d.disciplineId = -1
            assert False
        except:
            assert True
        
        try:
            d.Name = 1
            assert False
        except:
            assert True
        

    
class TestGrades(unittest.TestCase):
    def test_grade(self):
        gr = Grade(2,1,7)
        assert gr.Value == 7
        assert gr.studentId == 1
        gr.Value = 10
        assert gr.Value == 10
        try:
            gr.disciplineId = -1
            assert False
        except:
            assert True
        
        try:
            gr.Name = 1
            assert False
        except:
            assert True
            
        try:
            gr.studentId = -1
            assert False
        except:
            assert True
        
        try:
            gr.Name = 1
            assert False
        except:
            assert True
        

        



