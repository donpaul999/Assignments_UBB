#pragma once

#include "DynamicVector.h"
#include "Movie.h"
class Repository
{
private:
    DynamicVector<Movie> movieList;
    DynamicVector<Movie> userWatchList;

public:
    Repository();
    int addMovie(const Movie& movieToAdd);
    int deleteMovie(const Movie& movieToDelete);
    int updateMovie(const Movie& ToUpdateWith);
    Movie getMovieAtPosition(int positionOfMovie);
    int getNumberOfMovies();
    DynamicVector<Movie> getMoviesByGenre(const std::string& genreGiven);
    DynamicVector <Movie> getAllMovies();
    DynamicVector <Movie> getAllWatchListMovies();
    int addMovieToWatchlist(const Movie& movieToAdd);
    int addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd);
    int getNumberOfMoviesWatchList();
};