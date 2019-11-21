import unittest
from domain import *
from service import *

class TestStudents(unittest.TestCase):
    def test_student(self):
        st = Student(1,"test")
        assert st.Name == "test"
        assert st.ID == 1
        st.Name = "Ana"
        assert st.Name == "Ana"
        try:
            st.ID = -1
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
        assert d.ID == 1
        d.Name = "Chemistry"
        assert d.Name == "Chemistry"
        try:
            d.ID = -1
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
            
            
        
class TestAdd(unittest.TestCase):
    def test_add(self):
        studentRepo = Repository()
        disciplineRepo = Repository()
        gradesRepo = GradesRepository()
        s = StudentService(studentRepo)
        s.add(Student(1,"name"))
        assert len(s._studentRepo._data) == 1
        d = DisciplineService(disciplineRepo)
        d.add(Discipline(1,"name"))
        assert len(d._disciplineRepo._data) == 1
        g = GradeService(gradesRepo)
        g.add(Grade(1,1, 2), s._studentRepo._data, d._disciplineRepo._data)
        assert len(g._gradeRepo._data) == 1
        try:
            g.add(Grade(2,1,2), s._studentRepo._data, d._disciplineRepo._data)
            assert False
        except:
            assert True



class TestRemove(unittest.TestCase):
    def test_remove(self):
        studentRepo = Repository()
        disciplineRepo = Repository()
        gradesRepo = GradesRepository()
        s = StudentService(studentRepo)
        d = DisciplineService(disciplineRepo)
        g = GradeService(gradesRepo)
        s.add(Student(1,"name"))
        d.add(Discipline(1,"name"))
        g.add(Grade(1,1, 2), s._studentRepo._data, d._disciplineRepo._data)
        s.remove(1)
        d.remove(1)
        g.remove(1,"s")
        assert len(s._studentRepo._data) == 0
        assert len(d._disciplineRepo._data) == 0
        assert len(g._gradeRepo._data) == 0

class TestUpdate(unittest.TestCase):
    def test_update(self):
        studentRepo = Repository()
        disciplineRepo = Repository()
        s = StudentService(studentRepo)
        d = DisciplineService(disciplineRepo)
        s.add(Student(1,"name"))
        d.add(Discipline(1,"name"))
        s.update(1, "Andrei")
        d.update(1, "Mate")
        assert s._studentRepo._data[0].Name == "Andrei"
        assert d._disciplineRepo._data[0].Name == "Mate"