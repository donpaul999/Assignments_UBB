cmake_minimum_required(VERSION 3.15)
project(Lab9)

set(CMAKE_CXX_STANDARD 14)
find_package(Qt5Widgets REQUIRED)


add_executable(Lab9 main.cpp)
target_link_libraries(Lab9 Qt5::Widgets)
