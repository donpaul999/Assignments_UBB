#pragma once
#include "Repository.h"

class UserService
{
private:
    Repository& repository;
    DynamicVector<Movie> currentMoviesByGenre;
    int currentMoviePosition;



public:
    UserService(Repository& repository, int currentMoviePosition = 0);
    DynamicVector<Movie> userGetMovieList();
    DynamicVector<Movie> userGetWatchList();
    int listMoviesByGenre(const std::string& genreGiven);
    int addMovieToWatchList();
    int addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd);
    void goToNextMovieByGenre();
    Movie getCurrentMovie();
    int getWatchListLength();

};
