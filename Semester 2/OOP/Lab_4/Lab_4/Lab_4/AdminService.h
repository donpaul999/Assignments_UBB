#pragma once
#include "Repository.h"

class AdminService {
private:
    Repository& repository;

public:
    AdminService(Repository& repository);
    ~AdminService();
    const std::vector<std::string> explode(const std::string& s, const char& c);
    int adminAddMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer);
    //int adminAddMovie(Movie movieUsed); - Maybe
    int adminDeleteMovie(const std::string& title);
    int adminUpdateMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer);
    std::vector<Movie> adminGetMovieList();
};