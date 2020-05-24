cmake_minimum_required(VERSION 3.15)
project(Lab5)

set(CMAKE_CXX_STANDARD 14)

add_executable(Lab5 main.cpp
        UndirectedGraphUserInterface.cpp
        UndirectedGraphUserInterface.h
        UndirectedGraph.cpp
        UndirectedGraph.h
        Service.h
        Service.cpp)