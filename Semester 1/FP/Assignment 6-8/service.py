from domain import *
from Repo import *

class StudentService:
    def __init__(self, studentRepo):
        self._studentRepo = studentRepo

    def add(self, object):
        self._studentRepo.add(object)

    def getAll(self):
        return self._studentRepo.getAll()

    def update(self, id, name):
        try:
            id = int(id)
        except:
            e = Exception()
            e.PositiveID()
        self._studentRepo.update(id, name)

    def filter(self, sign, value):
        self._studentRepo.filter(sign, value)

    def sort(self, sign, type):
        self._studentRepo.sort(sign, type)


    def remove(self, id):
        try:
            id = int(id)
        except:
            e = Exception()
            e.PositiveID()
        self._studentRepo.remove(id)

    def search_using_id(self, id):
       return self._studentRepo.search_using_id(id)

    def search_using_name(self, text):
            return self._studentRepo.search_using_name(text)


class DisciplineService:
        def __init__(self, disciplineRepo):
            self._disciplineRepo = disciplineRepo

        def add(self, object):
            self._disciplineRepo.add(object)

        def getAll(self):
            return self._disciplineRepo.getAll()

        def filter(self, sign, value):
            self._disciplineRepo.filter(sign, value)

        def sort(self, sign, type):
            self._disciplineRepo.sort(sign, type)

        def remove(self, id):
            try:
                id = int(id)
            except:
                e = Exception()
                e.PositiveID()
            self._disciplineRepo.remove(id)

        def update(self, id, name):
            try:
                id = int(id)
            except:
                e = Exception()
                e.PositiveID()
            self._disciplineRepo.update(id, name)

        def search_using_id(self, id):
            return self._disciplineRepo.search_using_id(id)

        def search_using_name(self, text):
            return self._disciplineRepo.search_using_name(text)

class GradeService:
        def __init__(self, gradeRepo, studentRepo, disciplineRepo, undoController):
            self._gradeRepo = gradeRepo
            self._studentRepo = studentRepo
            self._disciplineRepo = disciplineRepo
            self._undoController = undoController

        def add(self, grade ,studentList, disciplineList):
                self._gradeRepo.add(grade)

        def getAll(self):
            return self._gradeRepo.getAll()

        def remove(self, id, type):
            return self._gradeRepo.remove(id, type)

        def removeStudent(self, type, id):
            s = self._studentRepo.find(id)
            if s is None:
                e = Exception()
                e.IDNotFound()
            list = self._gradeRepo.remove(id, type)
            undo1 = FunctionCall(self.add_grades, list)
            redo1 = FunctionCall(self.remove, *(id, type))
            op1 = Operation(undo1, redo1)
            undo2 = FunctionCall(self._studentRepo.add, s)
            redo2 = FunctionCall(self._studentRepo.remove, id)
            op2 = Operation(undo2, redo2)
            cascade = CascadeOperation(op1, op2)
            self._undoController.recordOperation(cascade)
            self._studentRepo.remove(id)

        def removeDiscipline(self, type, id):
            d = self._disciplineRepo.find(id)
            if d is None:
                e = Exception()
                e.IDNotFound()
            list = self._gradeRepo.remove(id, type)
            undo1 = FunctionCall(self._gradeRepo.add_grades, list)
            redo1 = FunctionCall(self._gradeRepo.remove, *(id, type))
            op1 = Operation(undo1, redo1)
            undo2 = FunctionCall(self._disciplineRepo.add, d)
            redo2 = FunctionCall(self._disciplineRepo.remove, id)
            op2 = Operation(undo2, redo2)
            cascade = CascadeOperation(op1, op2)
            self._undoController.recordOperation(cascade)
            self._disciplineRepo.remove(id)

        def add_grades(self, grades):
            self._gradeRepo.add_grades(grades)

class Service:

    def failing_students(self, grades, students, disciplines):
        '''

        :param grades: list of grades
        :param students: list of students
        :param disciplines: list of disciplines
        :return: list of failed students
        '''
        list = []
        for s in students:
            for d in disciplines:
                count = 0
                sum = 0
                for g in grades:
                    if s.ID == g.studentId and d.ID == g.disciplineId:
                        sum += g.Value
                        count += 1
                if count != 0:
                    sum /= count
                    if sum < 5:
                        list.append({"Name":s.Name,"Disc":d.Name ,"Avg":sum})
        if len(list) == 0:
            e = Exception()
            e.EmptyList()
        else:
         return list

    def best_students(self, grades, students, disciplines):
            list = []
            for s in students:
                avg = 0
                count2 = 0
                for d in disciplines:
                    count = 0
                    sum = 0
                    for g in grades:
                        if s.ID == g.studentId and d.ID == g.disciplineId:
                            sum += g.Value
                            count += 1
                    if count != 0:
                        sum /= count
                        avg += sum
                        count2 += 1
                if count2 != 0:
                    avg /= count2
                if avg >= 5:
                    list.append({"Name": s.Name, "Avg": avg})
            list = sorted(list, key=lambda i: i["Avg"], reverse=True)
            if len(list) == 0:
                e = Exception()
                e.EmptyList()
            else:
                return list

    def best_classes(self, grades, students, disciplines):
        '''

        :param grades: list of grades
        :param students: list of students
        :param disciplines: list of disciplines
        :return: list of the disciplines descending ordered by the average of grades
        '''
        list = []
        for d in disciplines:
            count = 0
            sum = 0
            for s in students:
                for g in grades:
                    if s.ID == g.studentId and d.ID == g.disciplineId:
                        sum += g.Value
                        count += 1
            if count != 0:
                sum /= count
                list.append({"Name": d.Name, "Avg": sum})
        list = sorted(list, key=lambda i: i["Avg"], reverse=True)
        if len(list) == 0:
            e = Exception()
            e.EmptyList()
        else:
            return list