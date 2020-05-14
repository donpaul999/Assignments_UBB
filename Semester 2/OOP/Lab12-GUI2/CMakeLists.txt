cmake_minimum_required(VERSION 3.15)
project(Lab12_GUI2)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTORCC ON)

find_package(Qt5Widgets REQUIRED)

include_directories(.)

add_executable(Lab12_GUI2
        Action.cpp
        Action.h
        ActionAdd.cpp
        ActionAdd.h
        ActionRemove.cpp
        ActionRemove.h
        ActionUpdate.cpp
        ActionUpdate.h
        AdminService.cpp
        AdminService.h
        CSVRepository.cpp
        CSVRepository.h
        FileRepository.cpp
        FileRepository.h
        HTMLRepository.cpp
        HTMLRepository.h
        main.cpp
        Movie.cpp
        Movie.h
        MovieValidator.cpp
        MovieValidator.h
        RepositoryException.cpp
        RepositoryException.h
        UserService.cpp
        UserService.h
        ValidationException.cpp
        ValidationException.h
        Lab12_GUI2.ui)

target_link_libraries(Lab12_GUI2 Qt5::Widgets)
