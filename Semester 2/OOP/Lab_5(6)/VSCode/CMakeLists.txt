cmake_minimum_required(VERSION 3.15)
project(lab8)

set(CMAKE_CXX_STANDARD 14)

include_directories(.)

add_executable(lab8
    AdminService.cpp
    AdminService.h
    CSVRepository.cpp
    CSVRepository.h
    DynamicVector.cpp
    DynamicVector.h
    FileRepository.cpp
    FileRepository.h
    HTMLRepository.cpp
    HTMLRepository.h
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
    Tests.cpp
    Tests.h
    UI.cpp
    UI.h
    UserService.cpp
    UserService.h
    ValidationException.cpp
    ValidationException.h)
