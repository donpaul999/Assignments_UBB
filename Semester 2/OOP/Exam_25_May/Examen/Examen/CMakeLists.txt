cmake_minimum_required(VERSION 3.15)
project(Examen)

set(CMAKE_CXX_STANDARD 14)
find_package(Qt5Widgets REQUIRED)
include_directories(.)
include_directories(x64/Debug/uic)

add_executable(Examen
        x64/Debug/moc/moc_Examen.cpp
        x64/Debug/rcc/qrc_Examen.cpp
        x64/Debug/uic/ui_Examen.h
        Car.cpp
        Car.h
        Examen.cpp
        Examen.h
        main.cpp
        Repository.cpp
        Repository.h
        Service.cpp
        Service.h)
target_link_libraries(Examen Qt5::Widgets)
