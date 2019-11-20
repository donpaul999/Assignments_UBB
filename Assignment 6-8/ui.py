from service import Service
from domain import *

class UI:
    def __init__(self, studentService, disciplineService, gradeService):
        self._studentService = studentService
        self._disciplineService = disciplineService
        self._gradeService = gradeService

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
        idD = input("Input id of student: ")
        idS = input("Input id of discipline: ")
        value = input("Input grade: ")
        try:
            self._gradeService.add(idD,idS, value)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()



    def printStudents(self):
        self.print_stars()
        ok = 0
        list = self._studentService.getAll()
        for i in list:
            print(i)
            print("")
            ok = 1
        if ok == 0:
            print("There are no students in the list!")
        self.print_stars()

    def printDisciplines(self):
        self.print_stars()
        ok = 0
        list = self._disciplineService.getAll()
        for i in list:
            print(i)
            print("")
            ok = 1
        if ok == 0:
            print("There are no disciplines in the list!")
        self.print_stars()

    def printGrades(self):
        self.print_stars()
        ok = 0
        grades = self._gradeService.getAll()
        disciplines = self._disciplineService.getAll()
        students = self._studentService.getAll()
        for i in grades:
            for j in students:
                if j.ID == i.studentId:
                    print("Student: " + j.Name, end=", ")
                    ok = 1
                    break
            if ok == 1:
              for j in disciplines:
                if j.ID == i.disciplineId:
                    print("Discipline: " + j.Name, end=", ")
                    break
              print("Grade: " + str(i.Value))
              print("")
            ok = 1
        if ok == 0:
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
            self._gradeService.remove(id, "s")
            self._studentService.remove(id)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

     
    def remove_discipline(self):
        id = input("Input id: ")
        try:
            self._gradeService.remove(id, "d")
            self._disciplineService.remove(id)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

    def search_for_discipline(self):
        self.print_stars()
        text = input("Input ID or name: ")
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
        self.print_stars()
        text = input("Input ID or name: ")
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
        print("15. Exit")

    def print_stars(self):
        print("***************************")

    def print_invalid(self):
        print("Invalid command!")
    
    def undo(self):
        pass

    def redo(self):
        pass

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
            elif choice == "15":
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
            else:
                self.print_invalid()

