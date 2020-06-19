cmake_minimum_required(VERSION 3.15)
project(ExamPractic)
find_package(Qt5Widgets REQUIRED)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(ExamPractic
        Controller.cpp
        Controller.h
        ExamPractic.cpp
        ExamPractic.h
        ExamPractic.ui
        Issue.cpp
        Issue.h
        main.cpp
        Observer.h
        Repository.cpp
        Repository.h
        User.cpp
        User.h)
target_link_libraries(ExamPractic Qt5::Widgets)
