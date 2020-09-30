#pragma once

#include "Movie.h"
#include <vector>
#include <algorithm>


class FileRepository
{
private:
    std::vector<Movie> userWatchList;
    std::string movieFileName;


public:
    std::vector<Movie> loadMoviesFromFile();
    void writeMoviesToFile( std::vector<Movie> movieList);
    FileRepository(const std::string& nameOfTheFileUsed = "");
    ~FileRepository();
    int addMovie(const Movie& movieToAdd);
    int deleteMovie(const Movie& movieToDelete);
    int updateMovie(const Movie& ToUpdateWith);
    void changeFileName(const std::string& nameOfTheFileUsed = "");
    Movie getMovieAtPosition(int positionOfMovie);
    int getNumberOfMovies();
    std::vector<Movie> getMoviesByGenre(const std::string& genreGiven = "");
    std::vector<Movie> getAllMovies();
    std::vector<Movie> getAllWatchListMovies();
    int addMovieToWatchlist(const Movie& movieToAdd);
    int addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd);
    int getNumberOfMoviesWatchList();
};
