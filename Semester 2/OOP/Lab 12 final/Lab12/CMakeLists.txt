cmake_minimum_required(VERSION 3.15)
project(Lab12)

set(CMAKE_CXX_STANDARD 14)
find_package(Qt5Widgets REQUIRED)

include_directories(.)
include_directories(x64/Debug/uic)

add_executable(Lab12
        x64/Debug/moc/moc_Lab12.cpp
        x64/Debug/rcc/qrc_Lab12.cpp
        x64/Debug/uic/ui_Lab12.h
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
        ActionFilter.h)

target_link_libraries(Lab12 Qt5::Widgets)
