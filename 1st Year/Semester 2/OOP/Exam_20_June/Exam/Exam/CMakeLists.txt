cmake_minimum_required(VERSION 3.15)
project(Exam)
find_package(Qt5Widgets REQUIRED)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)
include_directories(x64/Debug/uic)

add_executable(Exam
        x64/Debug/moc/moc_Examen.cpp
        x64/Debug/uic/ui_Examen.h
        x64/Debug/uic/ui_TableWidget.h
        Examen.cpp
        Examen.h
        main.cpp
        Repository.cpp
        Repository.h
        Service.cpp
        Service.h
        ExceptionClass.h
        Observer.h
        TableModel.cpp
        TableModel.h
        TableWidget.cpp
        TableWidget.h
        Astronomer.cpp
        Astronomer.h
        Star.h
        Star.cpp
        )
target_link_libraries(Exam Qt5::Widgets)
