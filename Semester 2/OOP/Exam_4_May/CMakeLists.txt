cmake_minimum_required(VERSION 3.15)
project(Exam_4_May)

set(CMAKE_CXX_STANDARD 14)

add_executable(Exam_4_May main.cpp
        Repository.cpp
        Repository.h
        Service.cpp
        Service.h
        UI.cpp
        UI.h
        NeonatalUnit.h
        NeonatalUnit.cpp
        HospitalDepartament.h
        HospitalDepartament.cpp
        Surgery.h
        Surgery.cpp
        )