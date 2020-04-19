#pragma once

#include "Movie.h"
#include <vector>
#include <algorithm>
#include "RepositoryException.h"

class FileRepository
{
protected:
    std::vector<Movie> userWatchList;
    std::string movieFileName;
    std::string userFileName;
    std::string repositoryType;



public:
    std::vector<Movie> loadMoviesFromFile();
    void writeMoviesToFile( std::vector<Movie> movieList, std::string movieFileName);
    void writeUserMoviesToFile( std::vector<Movie> movieList, std::string movieFileName);
    FileRepository(const std::string& nameOfTheFileUsed = "", std::string repositoryType = "txt");
    ~FileRepository();
    int addMovie(const Movie& movieToAdd);
    int deleteMovie(const Movie& movieToDelete);
    int updateMovie(const Movie& ToUpdateWith);
    void changeFileName(const std::string& nameOfTheFileUsed = "");
    void changeUserFileName(const std::string& nameOfTheFileUsed = "");
    Movie getMovieAtPosition(int positionOfMovie);
    int getNumberOfMovies();
    std::vector<Movie> getMoviesByGenre(const std::string& genreGiven);
    std::vector<Movie> getAllMovies();
    std::vector<Movie> getAllWatchListMovies();
    int addMovieToWatchlist(const Movie& movieToAdd);
    void deleteMovieFromWatchlist(const Movie& movieToDelete);
    int addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd);
    int getNumberOfMoviesWatchList();
    std::string getUserFileName();
    void updateHTMLFile();
    void updateCSVFile();
    void changeRepositoryType(std::string repositoryType);
};
