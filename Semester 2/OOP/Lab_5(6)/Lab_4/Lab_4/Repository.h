#pragma once

#include "DynamicVector.h"
#include "Movie.h"
class Repository
{
private:
    std::vector<Movie> movieList;
    std::vector<Movie> userWatchList;

public:
    Repository();
    int addMovie(const Movie& movieToAdd);
    int deleteMovie(const Movie& movieToDelete);
    int updateMovie(const Movie& ToUpdateWith);
    Movie getMovieAtPosition(int positionOfMovie);
    int getNumberOfMovies();
    std::vector <Movie> getMoviesByGenre(const std::string& genreGiven);
    std::vector <Movie> getAllMovies();
    std::vector <Movie> getAllWatchListMovies();
    int addMovieToWatchlist(const Movie& movieToAdd);
    int addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd);
    int getNumberOfMoviesWatchList();
};