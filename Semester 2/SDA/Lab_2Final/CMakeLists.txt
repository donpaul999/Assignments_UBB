cmake_minimum_required(VERSION 3.15)
project(Lab_2Final)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(Lab_2Final
        App.cpp
        DLLNode.cpp
        DLLNode.h
        ExtendedTest.cpp
        ExtendedTest.h
        IteratedList.cpp
        IteratedList.h
        ListIterator.cpp
        ListIterator.h
        ShortTest.cpp
        ShortTest.h)
