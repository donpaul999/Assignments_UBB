class Student:
    def __init__(self, sId, name):
        self.studentId = sId
        self.Name = name

    def __repr__(self):
        return "Student ID: " + str(self.studentId) + " Name: " + self.Name
    
    def __eq__(self, other):
        return self.studentId == other.studentId

    @property
    def studentId(self):
        return self._sId
    
    @studentId.setter
    def studentId(self, value):
        try:
            value = int(value)
            if value <= 0:
                raise ValueError("The id must be a positive integer!")
            self._sId = value
        except:
            raise ValueError("The id must be a positive integer!")


    @property
    def Name(self):
        return self._name
    
    @Name.setter
    def Name(self, value):
        ok = 1
        try:
            value = int(value)
            ok = 0
        except:
            for i in range(0, len(value)):
                if value[i] >= '0' and value[i] <= '9':
                    ok = 0
                    break
            if len(value) < 2:
                ok = 0
        if ok == 0:
            raise ValueError("The name must be a valid string!")
        else:
            self._name = value
    

class Discipline:
    def __init__(self, dId, name):
        self.disciplineId = dId
        self.Name = name

    def __repr__(self):
        return "Discipline ID: " + str(self.disciplineId) + " Name: " + self.Name

    def __eq__(self, other):
        return self.disciplineId == other.disciplineId

    @property
    def disciplineId(self):
        return self._dId
    
    @disciplineId.setter
    def disciplineId(self, value):
        try:
            value = int(value)
            if value <= 0:
                raise ValueError("The id must be a positive integer!")
            self._dId = value
        except:
            raise ValueError("The id must be a positive integer!")

    
    @property
    def Name(self):
        return self._name
    
    @Name.setter
    def Name(self, value):
        ok = 1
        try:
            value = int(value)
            ok = 0
        except:
            self._name = value
        if ok == 0:
            raise ValueError("The name must be a valid string!")
    

class Grade:
    def __init__(self, dId, sId, value):
        self.disciplineId = dId
        self.studentId = sId
        self.Value = value

    def __eq__(self, other):
        return self.studentId == other.studentId
     
    def __repr__(self):
        return "Discipline ID: " + str(self.disciplineId) + "Student ID: " + str(self.studentId) + " Grade: " + self.Value
    
    @property
    def disciplineId(self):
        return self._dId
    
    @disciplineId.setter
    def disciplineId(self, value):
        try:
            value = int(value)
            if value <= 0:
                raise ValueError("The id must be a positive integer!")
            self._dId = value
        except:
            raise ValueError("The id must be a positive integer!")


    @property
    def studentId(self):
        return self._sId
    
    @studentId.setter
    def studentId(self, value):
        try:
            value = int(value)
            if value <= 0:
                raise ValueError("The id must be a positive integer!")
            self._sId = value
        except:
            raise ValueError("The id must be a positive integer!")

    
    @property
    def Value(self):
        return self._value
    
    @Value.setter
    def Value(self, value):
        try:
            value = int(value)
            if value < 0 or value > 10:
                raise("The grade must be a positive integer between 1 and 10!")
            else:
                self._value = value
        except:
            raise("The grade must be a positive integer between 1 and 10!")





def test_discipline():
        d = Discipline(1,"test")
        assert d.Name == "test"
        assert d.disciplineId == 1
        d.Name = "Chemistry"
        assert d.Name == "Chemistry"


def test_student():
        st = Student(1,"test")
        assert st.Name == "test"
        assert st.studentId == 1
        st.Name = "Ana"
        assert st.Name == "Ana"

def test_grade():
        gr = Grade(2,1,7)
        assert gr.Value == 7
        assert gr.studentId == 1
        gr.Value = 10
        assert gr.Value == 10


test_grade()
test_student()
test_discipline()
