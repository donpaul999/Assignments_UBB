cmake_minimum_required(VERSION 3.15)
project(Lab_1)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(Lab_1
    Direct_Graph.cpp
    Direct_Graph.h
    Lab_1.cpp
    Service.cpp
    Service.h
    UIDirectGraph.cpp
    UIDirectGraph.h
        DAGUI.cpp
        DAGUI.h
        Activity.h
        Activity.cpp)
