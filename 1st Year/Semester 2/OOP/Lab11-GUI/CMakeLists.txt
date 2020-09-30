cmake_minimum_required(VERSION 3.15)
project(Lab11_GUI)

set(CMAKE_CXX_STANDARD 14)
find_package(Qt5Widgets REQUIRED)

include_directories(.)

add_executable(Lab11_GUI
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
        GUI.cpp
        GUI.h)
target_link_libraries(Lab11_GUI Qt5::Widgets)

