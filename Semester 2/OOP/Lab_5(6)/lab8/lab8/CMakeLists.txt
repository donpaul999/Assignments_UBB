cmake_minimum_required(VERSION 3.15)
project(lab8)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)
set(CMAKE_CXX_FLAGS "-g -O0 -fprofile-arcs -ftest-coverage")
set(CMAKE_CXX_OUTPUT_EXTENSION_REPLACE 1)
add_executable(lab8
        AdminService.cpp
        AdminService.h
        DynamicVector.cpp
        DynamicVector.h
        FileRepository.cpp
        FileRepository.h
        main.cpp
        Movie.cpp
        Movie.h
        Repository.cpp
        Repository.h
        Tests.cpp
        Tests.h
        UI.cpp
        UI.h
        UserService.cpp
        UserService.h)
