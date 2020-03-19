#pragma once
#include "Repository.h"

class UserService
{
private:
    Repository& repository;

public:
    UserService(Repository& repository);
    std::vector<Movie> userGetMovieList();

};

