import unittest

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
        e = Exception()
        try:
            value = int(value)
            if value <= 0:
                e.PositiveID()
            self._sId = value
        except:
                e.PositiveID()


    @property
    def Name(self):
        return self._name
    
    @Name.setter
    def Name(self, value):
        e = Exception()
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
            e.ValidName()
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
        e = Exception()
        try:
            value = int(value)
            if value <= 0:
                e.PositiveID()
            self._dId = value
        except:
                e.PositiveID()

    
    @property
    def Name(self):
        return self._name
    
    @Name.setter
    def Name(self, value):
        ok = 1
        e = Exception()
        try:
            value = int(value)
            ok = 0
        except:
            self._name = value
        if ok == 0:
            e.ValidName()
    

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
        e = Exception()
        try:
            value = int(value)
            if value <= 0:
                e.PositiveID()
            self._dId = value
        except:
                e.PositiveID()


    @property
    def studentId(self):
        return self._sId
    
    @studentId.setter
    def studentId(self, value):
        e = Exception()
        try:
            value = int(value)
            if value <= 0:
                e.PositiveID()
            self._sId = value
        except:
                e.PositiveID()

    
    @property
    def Value(self):
        return self._value
    
    @Value.setter
    def Value(self, value):
        e = Exception()
        try:
            value = int(value)
            if value < 0 or value > 10:
                e.GradeValue()
            else:
                self._value = value
        except:
                e.GradeValue()()
        

class Exception():
    def IDNotFound(self):
        raise ValueError("ID is not in the list")
    def IDUsed(self):
        raise ValueError("ID already used!")
    def PositiveID(self):
        raise ValueError("The id must be a positive integer!")
    
    def GradeValue(self):
        raise ValueError("The grade must be a positive integer between 1 and 10!")
    
    def ValidName(self):
        raise ValueError("The name must be a valid string!")

    
