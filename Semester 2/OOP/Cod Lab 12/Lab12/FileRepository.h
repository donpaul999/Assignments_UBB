#pragma once

#include "Movie.h"
#include <vector>
#include <algorithm>
#include "RepositoryException.h"

class FileRepository
{
protected:
    std::vector<Movie> userWatchList;
    std::vector<Movie> movieList;
    std::string movieFileName;
    std::string userFileName;
    std::string repositoryType;
    bool memoryOrFile;



public:
    std::vector<Movie> loadMoviesFromFile();
    void writeMoviesToFile( std::vector<Movie> movieList, std::string movieFileName);
    virtual void writeUserMoviesToFile( std::vector<Movie> movieList, std::string movieFileName);
    FileRepository(const std::string& nameOfTheFileUsed = "", std::string repositoryType = "txt", bool memoryOrFile=0);
    void setMemoryOrFile(bool memoryOrFile);
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
    virtual std::vector<Movie> getAllWatchListMovies();
    virtual int addMovieToWatchlist(const Movie& movieToAdd);
    virtual void deleteMovieFromWatchlist(const Movie& movieToDelete);
    virtual int addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd);
    virtual int getNumberOfMoviesWatchList();
    std::string getUserFileName();
    std::string getMovieFileName();
    void changeRepositoryType(std::string repositoryType);
    const std::vector<std::string>explode(const std::string& stringToExplode, const std::string& separatorsUsed);
   // void operator=(FileRepository& repository);
   Movie findMovie(const std::string& title);

};
