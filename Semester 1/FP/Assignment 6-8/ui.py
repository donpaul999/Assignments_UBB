from domain import *
from undo import *

class UI:
    def __init__(self, studentService, disciplineService, gradeService, Service, undoController):
        self._studentService = studentService
        self._disciplineService = disciplineService
        self._gradeService = gradeService
        self._Service = Service
        self._undoController = undoController

    def addStudent(self):
        id = input("Input id: ")
        name = input("Input name: ")
        try:
            self._studentService.add(Student(id, name))
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

        

    def addDiscipline(self):
        id = input("Input id: ")
        name = input("Input name: ")
        try:
            self._disciplineService.add(Discipline(id, name))
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

        

    def addGrade(self):
        sId = input("Input id of student: ")
        dId = input("Input id of discipline: ")
        value = input("Input grade: ")
        try:
            self._gradeService.add(Grade(dId,sId, value),self._studentService._studentRepo._data,self._disciplineService._disciplineRepo._data)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()



    def printStudents(self):
        self.print_stars()
        ok = 0
        id = 0
        list = self._studentService.getAll()
        for i in list:
            id += 1
            print(str(id) + ".", end= " ")
            print(i)
            print("")
            ok = 1
        if ok == 0:
            print("There are no students in the list!")
        self.print_stars()

    def printDisciplines(self):
        self.print_stars()
        ok = 0
        id = 0
        list = self._disciplineService.getAll()
        for i in list:
            id += 1
            print(str(id) + ".", end=" ")
            print(i)
            print("")
            ok = 1
        if ok == 0:
            print("There are no disciplines in the list!")
        self.print_stars()

    def printGrades(self):
        self.print_stars()
        grades = self._gradeService.getAll()
        disciplines = self._disciplineService.getAll()
        students = self._studentService.getAll()
        okf = 0
        id = 1
        for i in grades:
            ok = 0
            for j in students:
                if j.ID == i.studentId:
                    student = j.Name
                    ok = 1
                    break
            if ok == 1:
              ok2 = 0
              for j in disciplines:
                if j.ID == i.disciplineId:
                    discipline = j.Name
                    ok2 = 0
                    break
              print("")
            if ok == 1 and ok2 == 0:
                print(str(id) + ". Student: " + student, end=", ")
                print("Discipline: " + discipline, end=", ")
                print("Grade: " + str(i.Value))
                okf = 1
                id += 1
        if okf == 0:
            print("There are no grades in the list!")
        self.print_stars()


    def update_student(self):
        id = input("Input id: ")
        name = input("Input the new name: ")
        try:
            self._studentService.update(id, name)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

    def update_discipline(self):
        id = input("Input id: ")
        name = input("Input the new name: ")
        try:
            self._disciplineService.update(id, name)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

    def remove_student(self):
        id = input("Input id: ")
        try:
            id = int(id)
        except:
            self.print_stars()
            print("ID is not valid!")
            self.print_stars()
        try:
            self._gradeService.removeStudent("s",id)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

     
    def remove_discipline(self):
        id = input("Input id: ")
        try:
            id = int(id)
        except:
            self.print_stars()
            print("ID is not valid!")
            self.print_stars()
        try:
            self._gradeService.removeDiscipline("d", id)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()


    def search_for_discipline(self):
        text = input("Input ID or name: ")
        self.print_stars()
        try:
            text = int(text)
            try:
                discipline = self._disciplineService.search_using_id(text)
                print(discipline)
            except ValueError as e:
                print(e)
        except:
            try:
                disciplines = self._disciplineService.search_using_name(text)
                for i in disciplines:
                    print(i)
            except ValueError as e:
                print(e)
        self.print_stars()

    def search_for_student(self):
        text = input("Input ID or name: ")
        self.print_stars()
        try:
            text = int(text)
            try:
                student = self._studentService.search_using_id(text)
                print(student)
            except ValueError as e:
                print(e)
        except:
            try:
                students = self._studentService.search_using_name(text)
                for i in students:
                    print(i)
            except ValueError as e:
                print(e)
        self.print_stars()

    def failing_students(self):
        self.print_stars()
        try:
            id = 0
            list = self._Service.failing_students(self._gradeService._gradeRepo._data, self._studentService._studentRepo._data,self._disciplineService._disciplineRepo._data)
            for i in list:
                id += 1
                print(str(id) + ". Name: " + i["Name"] +", Discipline: " + i["Disc"] + ", Average: " + str(i["Avg"]))
        except ValueError as e:
            print(e)
        self.print_stars()


    def best_students(self):
        self.print_stars()
        try:
            id = 0
            list = self._Service.best_students(self._gradeService._gradeRepo._data, self._studentService._studentRepo._data,self._disciplineService._disciplineRepo._data)
            for i in list:
                id += 1
                print(str(id) + ". Name: " + i["Name"] + " Average: " + str(i["Avg"]))
        except ValueError as e:
            print(e)
        self.print_stars()

    def descending_classes(self):
        self.print_stars()
        try:
            id = 0
            list = self._Service.best_classes(self._gradeService._gradeRepo._data, self._studentService._studentRepo._data,self._disciplineService._disciplineRepo._data)
            for i in list:
                id += 1
                print(str(id) + ". Name: " + i["Name"] + " Average: " + str(i["Avg"]))
        except ValueError as e:
            print(e)
        self.print_stars()

    def sort_students_id(self):
        while True:
            text = input("Input ascending or descending: ")
            if text == "ascending" or text == "descending":
                break
            print("Invalid choice!")
        self._studentService.sort(text, 'id')

    def sort_students_name(self):
        while True:
            text = input("Input ascending or descending: ")
            if text == "ascending" or text == "descending":
                break
            print("Invalid choice!")
        self._studentService.sort(text, 'name')

    def sort_disciplines_id(self):
        while True:
            text = input("Input ascending or descending: ")
            if text == "ascending" or text == "descending":
                break
            print("Invalid choice!")
        self._disciplineService.sort(text, 'id')

    def sort_disciplines_name(self):
        while True:
            text = input("Input ascending or descending: ")
            if text == "ascending" or text == "descending":
                break
            print("Invalid choice!")
        self._disciplineService.sort(text, 'name')


    def filter_students_id(self):
        while True:
            sign = input("Enter '<' or '>': ")
            value = input("Enter a value: ")
            try:
                value = int(value)
                if sign != '>' and sign != '<':
                    print("Invalid choice!")
                else:
                    self._studentService.filter(sign, value)
                    break
            except:
                print("Invalid choice!")

    def filter_disciplines_id(self):
        while True:
            sign = input("Enter '<' or '>': ")
            value = input("Enter a value: ")
            try:
                value = int(value)
                if sign != '>' and sign != '<':
                    print("Invalid choice!")
                else:
                    self._disciplineService.filter(sign, value)
                    break
            except:
                print("Invalid choice!")

    def undo(self):
        try:
            self._undoController.undo()
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

    def redo(self):
        try:
            self._undoController.redo()
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()


    def print_menu(self):
        print("1. Add a new student")
        print("2. Show the list of students")
        print("3. Update a student")
        print("4. Remove a student")
        print("5. Add a new discipline")
        print("6. Show the list of disciplines")
        print("7. Update a discipline")
        print("8. Remove a discipline")
        print("9. Add a new grade")
        print("10. Show the list of grades")
        print("11. Undo the last operation")
        print("12. Redo the last operation")
        print("13. Search for a discipline")
        print("14. Search for a student")
        print("15. Failing students")
        print("16. Best students")
        print("17. Disciplines printend in a descending order by the grades average")
        print("18. Sort students by ID")
        print("19. Sort students by Name")
        print("20. Sort disciplines by ID")
        print("21. Sort disciplines by Name")
        print("22. Filter students by ID")
        print("23. Filter disciplines by ID")
        print("24. Exit")

    def print_stars(self):
        print("***************************")

    def print_invalid(self):
        print("Invalid command!")

    def start(self):
        while True:
            self.print_menu()
            choice = input(">")
            if choice == "1":
                self.addStudent()
            elif choice == "2":
                self.printStudents()
            elif choice == "5":
                self.addDiscipline()
            elif choice == "6":
                self.printDisciplines()
            elif choice == "9":
                self.addGrade()
            elif choice == "10":
                self.printGrades()
            elif choice == "11":
                self.undo()
            elif choice == "12":
                self.redo()
            elif choice == "24":
                return
            elif choice == "3":
                self.update_student()
            elif choice == "7":
                self.update_discipline()
            elif choice == "4":
                self.remove_student()
            elif choice == "8":
                self.remove_discipline()
            elif choice == "13":
                self.search_for_discipline()
            elif choice == "14":
                self.search_for_student()
            elif choice == "15":
                self.failing_students()
            elif choice == "16":
                self.best_students()
            elif choice == "17":
                self.descending_classes()
            elif choice == "18":
                self.sort_students_id()
            elif choice == "19":
                self.sort_students_name()
            elif choice == "20":
                self.sort_disciplines_id()
            elif choice == "21":
                self.sort_disciplines_name()
            elif choice == "22":
                self.filter_students_id()
            elif choice == "23":
                self.filter_disciplines_id()
            else:
                self.print_invalid()

