#pragma once
#include "FileRepository.h"

class UserService
{
private:
    FileRepository& repository;
    std::vector<Movie> currentMoviesByGenre;
    int currentMoviePosition;



public:
    UserService(FileRepository& repository, int currentMoviePosition = 0);
    std::vector<Movie> userGetMovieList();
    std::vector<Movie> userGetWatchList();
    int listMoviesByGenre(const std::string& genreGiven);
    int addMovieToWatchList();
    int addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd);
    void goToNextMovieByGenre();
    Movie getCurrentMovie();
    int getWatchListLength();

};
