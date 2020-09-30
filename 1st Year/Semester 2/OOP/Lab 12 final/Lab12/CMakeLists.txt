cmake_minimum_required(VERSION 3.15)
project(Lab12)

set(CMAKE_CXX_STANDARD 14)
find_package(Qt5Widgets REQUIRED)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTORCC ON)

add_executable(Lab12
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
        Lab12.cpp
        Lab12.h
        Lab12.ui
        Lab12.qrc
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
        ActionFilter.cpp
        ActionFilter.h
        MyListTableModel.cpp
        MyListTableModel.h
        MyListWidget.h
        MyListWidget.cpp
        MyListWidget.ui)

target_link_libraries(Lab12 Qt5::Widgets)
