from service import Service
from domain import *

class UI:
    def __init__(self, service):
        self._service = service

    def addStudent(self):
        id = input("Input id: ")
        name = input("Input name: ")
        try:
            self._service.addStudent(Student(id, name))
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

        

    def addDiscipline(self):
        id = input("Input id: ")
        name = input("Input name: ")
        try:
            self._service.addDiscipline(Discipline(id, name))
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

        

    def addGrade(self):
        idD = input("Input id of student: ")
        idS = input("Input id of discipline: ")
        value = input("Input grade: ")
        try:
            self._service.addGrade(Grade(idD,idS, value))
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()



    def printStudents(self):
        self.print_stars()
        ok = 0
        for i in self._service._students:
            print(i)
            print("")
            ok = 1
        if ok == 0:
            print("There are no students in the list!")
        self.print_stars()

    def printDisciplines(self):
        self.print_stars()
        ok = 0
        for i in self._service._disciplines:
            print(i)
            print("")
            ok = 1
        if ok == 0:
            print("There are no disciplines in the list!")
        self.print_stars()

    def printGrades(self):
        self.print_stars()
        ok = 0
        for i in self._service._grades:
            for j in self._service._students:
                if j.studentId == i.studentId:
                    print("Student: " + j.Name, end=", ")
                    break
            for j in self._service._disciplines:
                if j.disciplineId == i.disciplineId:
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
            self._service.update_student(id, name)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

    def update_discipline(self):
        id = input("Input id: ")
        name = input("Input the new name: ")
        try:
            self._service.update_discipline(id, name)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

    def remove_student(self):
        id = input("Input id: ")
        try:
            self._service.remove_student(id)
        except ValueError as e:
            self.print_stars()
            print(e)
            self.print_stars()

     
    def remove_discipline(self):
        id = input("Input id: ")
        try:
            self._service.remove_discipline(id)
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
        print("13. Exit")

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
            elif choice == "13":
                return
            elif choice == "3":
                self.update_student()
            elif choice == "7":
                self.update_discipline()
            elif choice == "4":
                self.remove_student()
            elif choice == "8":
                self.remove_discipline()            
            else:
                self.print_invalid()

