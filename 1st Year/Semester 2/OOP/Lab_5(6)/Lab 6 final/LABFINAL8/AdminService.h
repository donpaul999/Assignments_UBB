#pragma once
#include "Repository.h"
#include <vector>
#include <fstream>
class AdminService {
private:
    Repository& repository;

public:
    AdminService(Repository& repository);
    const std::vector<std::string> explode(const std::string& stringToExplode, const char& separatorUsed);
    int adminAddMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer);
    int adminDeleteMovie(const std::string& title);
    int adminUpdateMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer);
    std::vector<Movie> adminGetMovieList();
    int changeRepositoryFileName(const std::string& nameOfTheFileUsed);
};