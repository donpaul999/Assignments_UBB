#pragma once

#include "Movie.h"
#include <vector>
#include <algorithm>


class Repository
{
private:
    std::vector<Movie> movieList;
    std::vector<Movie> userWatchList;
    std::string movieFileName;


public:
    void loadMoviesFromFile();
    void writeMoviesToFile();
    Repository(const std::string& nameOfTheFileUsed = "");
    ~Repository();
    int addMovie(const Movie& movieToAdd);
    int findMovie(std::vector<Movie> listOfMovies, const Movie& movieToSearch);
    int deleteMovie(const Movie& movieToDelete);
    int updateMovie(const Movie& ToUpdateWith);
    void changeFileName(const std::string& nameOfTheFileUsed = "");
    Movie getMovieAtPosition(int positionOfMovie);
    int getNumberOfMovies();
    std::vector<Movie> getMoviesByGenre(const std::string& genreGiven);
    std::vector<Movie> getAllMovies();
    std::vector<Movie> getAllWatchListMovies();
    int addMovieToWatchlist(const Movie& movieToAdd);
    int addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd);
    int getNumberOfMoviesWatchList();
};