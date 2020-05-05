cmake_minimum_required(VERSION 3.15)
project(lab8)

set(CMAKE_CXX_STANDARD 14)
find_package(Qt5Widgets REQUIRED)


include_directories(.)

add_executable(lab8
    AdminService.cpp
    AdminService.h
    DynamicVector.cpp
    DynamicVector.h
    FileRepository.cpp
    FileRepository.h
    inputFile.in
    main.cpp
    Movie.cpp
    Movie.h
    Repository.cpp
    Repository.h
    RepositoryException.cpp
    RepositoryException.h
    testFile.txt
    TestFileJops.txt
    UI.cpp
    UI.h
    UserService.cpp
    UserService.h
    ValidationException.cpp
    ValidationException.h
    MovieValidator.cpp
    MovieValidator.h
        CSVRepository.h
        CSVRepository.cpp
        HTMLRepository.h
        HTMLRepository.cpp
        ActionUpdate.cpp
        ActionUpdate.h
        ActionRemove.h
        ActionRemove.cpp
        ActionAdd.h
        Action.cpp
        ActionAdd.cpp
        Action.h)
target_link_libraries(lab8 Qt5::Widgets)

