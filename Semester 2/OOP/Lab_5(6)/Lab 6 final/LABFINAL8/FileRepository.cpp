#include "FileRepository.h"
#include <iostream>
#include <fstream>



//Load movies from given file
std::vector<Movie> FileRepository::loadMoviesFromFile()
{
    std::vector<Movie> movieList = {};
    if (movieFileName == "")
        return {};
    Movie movieLoadedFromFile;
    std::ifstream fin(movieFileName);
    while (fin >> movieLoadedFromFile) {
        if(std::find(movieList.begin(), movieList.end(), movieLoadedFromFile) == movieList.end())
            movieList.push_back(movieLoadedFromFile);
    }
    fin.close();
    return movieList;
}


//Write movies to given file
void FileRepository::writeMoviesToFile(std::vector<Movie> movieList)
{
    if (movieFileName == "")
        return;
    std::ofstream fout(movieFileName);
    for (const Movie& movieToWrite : movieList) {
        fout << movieToWrite << '\n';
    }
    fout.close();
}

//Repository constructor
FileRepository::FileRepository(const std::string& nameOfTheFileUsed)
{
    movieFileName = nameOfTheFileUsed;

}

//Repository destructor
FileRepository::~FileRepository()
{
}


//Add given movie to list
int FileRepository::addMovie(const Movie& movieToAdd)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToAdd);
    if (iteratorWhereMovieIsFound !=  movieList.end() && movieList.size() != 0)
        return -1;
    movieList.push_back(movieToAdd);
    writeMoviesToFile(movieList);
    return 1;
}



//Delete a movie from the movieList
int FileRepository::deleteMovie(const Movie& movieToDelete)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToDelete);
    if (iteratorWhereMovieIsFound == movieList.end())
        return -1;
    movieList.erase(iteratorWhereMovieIsFound);
    writeMoviesToFile(movieList);
    return 1;
}


//Update a movie from the movieList
int FileRepository::updateMovie(const Movie& movieToUpdateWith)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToUpdateWith);
    if (iteratorWhereMovieIsFound == movieList.end())
        return -1;
    *iteratorWhereMovieIsFound = movieToUpdateWith;
    writeMoviesToFile(movieList);
    return 1;
}

//Change the file name of the repository
void FileRepository::changeFileName(const std::string& nameOfTheFileUsed)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    movieFileName = nameOfTheFileUsed;
    writeMoviesToFile(movieList);
}


//Get the movie at a certain position
Movie FileRepository::getMovieAtPosition(int positionOfMovie)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    if (positionOfMovie < 0 || positionOfMovie >= movieList.size())
        throw std::runtime_error("Invalid position");
    return movieList[positionOfMovie];}

//Get the number of movies in the list

int FileRepository::getNumberOfMovies()
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    return movieList.size();
}

//Get all the movies with a given genre
std::vector<Movie> FileRepository::getMoviesByGenre(const std::string& genreGiven)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    std::vector<Movie> moviesWithGenre;
    if (genreGiven == "")
        return movieList;
    std::copy_if(movieList.begin(), movieList.end(), std::back_inserter(moviesWithGenre), [&genreGiven](const Movie& movie) {return movie.getGenre() == genreGiven; });
    return moviesWithGenre;
}

//Get all the movies
std::vector<Movie> FileRepository::getAllMovies()
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    return movieList;
}


//Get all the movies in the watch list
std::vector<Movie> FileRepository::getAllWatchListMovies()
{
    return userWatchList;
}


//Add a certain movie to the watch list
int FileRepository::addMovieToWatchlist(const Movie& movieToAdd)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieIsFound = std::find(userWatchList.begin(), userWatchList.end(), movieToAdd);
    if (iteratorWhereMovieIsFound != userWatchList.end())
        return -1;
    userWatchList.push_back(movieToAdd);
    return 1;
}


//Add a certain movie to the watch list by the title
int FileRepository::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieFound = std::find_if(movieList.begin(), movieList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
    if (iteratorWhereMovieFound == movieList.end())
        return -1;
    userWatchList.push_back(*iteratorWhereMovieFound);
    return 1;
}

//Get the number of movies in the watch list
int FileRepository::getNumberOfMoviesWatchList()
{
    return userWatchList.size();
}
