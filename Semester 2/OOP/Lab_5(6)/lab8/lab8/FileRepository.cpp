#include "FileRepository.h"
#include <iostream>
#include <fstream>



//Load movies from given file
std::vector<Movie> FileRepository::loadMoviesFromFile()
{

    std::vector<Movie> movieList = {};
    Movie movieLoadedFromFile;
    if (movieFileName == "")
        throw RepositoryException(std::string("FileName is invalid"));
    std::ifstream fin(movieFileName);
    while (fin >> movieLoadedFromFile) {
        if (std::find(movieList.begin(), movieList.end(), movieLoadedFromFile) == movieList.end())
            movieList.push_back(movieLoadedFromFile);
        }
    fin.close();
    return movieList;

}

void FileRepository::changeRepositoryType(std::string repositoryTypeGiven) {
    repositoryType = repositoryTypeGiven;
}

//Write movies to given file
void FileRepository::writeUserMoviesToFile(std::vector<Movie> movieList, std::string movieFileName)
{
    //std::cout << repositoryType<<'\n';
    std::ofstream fout(movieFileName);
    //std::cout << movieFileName << '\n';
    for (const Movie &movieToWrite : movieList)
         fout << movieToWrite << '\n';
    fout.close();
}

void FileRepository::writeMoviesToFile(std::vector<Movie> movieList, std::string movieFileName)
{
        std::ofstream fout(movieFileName);
       // std::cout << movieFileName << '\n';
        for (const Movie &movieToWrite : movieList)
            fout << movieToWrite << '\n';
        fout.close();

}

//Repository constructor
FileRepository::FileRepository(const std::string& nameOfTheFileUsed, std::string repositoryType)
{
    movieFileName = nameOfTheFileUsed;
    repositoryType = repositoryType;
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
        throw RepositoryException(std::string("Movie already in the list"));
    movieList.push_back(movieToAdd);
    writeMoviesToFile(movieList, movieFileName);
    return 1;
}



//Delete a movie from the movieList
int FileRepository::deleteMovie(const Movie& movieToDelete)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToDelete);
    if (iteratorWhereMovieIsFound == movieList.end())
        throw RepositoryException(std::string("Movie not in the list"));
    movieList.erase(iteratorWhereMovieIsFound);
    writeMoviesToFile(movieList, movieFileName);
    return 1;
}


//Update a movie from the movieList
int FileRepository::updateMovie(const Movie& movieToUpdateWith)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToUpdateWith);
    if (iteratorWhereMovieIsFound == movieList.end())
        throw RepositoryException(std::string("Movie not in the list"));
    *iteratorWhereMovieIsFound = movieToUpdateWith;
    writeMoviesToFile(movieList, movieFileName);
    return 1;
}

//Change the file name of the repository
void FileRepository::changeFileName(const std::string& nameOfTheFileUsed)
{
    movieFileName = nameOfTheFileUsed;
}


//Get the movie at a certain position
Movie FileRepository::getMovieAtPosition(int positionOfMovie)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    if (positionOfMovie < 0 || positionOfMovie >= movieList.size())
        throw RepositoryException(std::string("Invalid position"));
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
    std::vector<Movie> moviesWithGenre = {};
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
        throw RepositoryException(std::string("Movie already in the watch list"));
    userWatchList.push_back(movieToAdd);
    writeUserMoviesToFile(userWatchList, userFileName);
    return 1;
}

//
void FileRepository::deleteMovieFromWatchlist(const Movie& movieToDelete)
{
    auto iteratorWhereMovieIsFound = std::find(userWatchList.begin(), userWatchList.end(), movieToDelete);
    if (iteratorWhereMovieIsFound != userWatchList.end())
        throw RepositoryException(std::string("Movie already in the watch list"));
    userWatchList.erase(iteratorWhereMovieIsFound);
    writeUserMoviesToFile(userWatchList, userFileName);
}

//Add a certain movie to the watch list by the title
int FileRepository::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieFound = std::find_if(movieList.begin(), movieList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
    if (iteratorWhereMovieFound == movieList.end())
        throw RepositoryException(std::
        string("Movie with this title not exist."));
    userWatchList.push_back(*iteratorWhereMovieFound);
    writeUserMoviesToFile(userWatchList, userFileName);
    return 1;
}

//Get the number of movies in the watch list
int FileRepository::getNumberOfMoviesWatchList()
{
    return userWatchList.size();
}

void FileRepository::changeUserFileName(const std::string& nameOfTheFileUsed){
    userFileName = nameOfTheFileUsed;
}

std::string FileRepository::getMovieFileName(){
    if(movieFileName != "")
        return movieFileName;
    return " ";}

std::string FileRepository::getUserFileName(){
    if(userFileName != "")
        return userFileName;
    return " ";
}

const std::vector<std::string>FileRepository::explode(const std::string& stringToExplode, const std::string& separatorsUsed)
{
    std::vector<std::string>trashValues = { "Title", " Genre", " Year of Release", " Likes", " Trailer" };
    std::string partialStringObtained{ "" };
    std::vector<std::string> explodedString;

    for (auto iterator : stringToExplode)
    {
        if (iterator != separatorsUsed[0] && iterator != separatorsUsed[1])
            partialStringObtained += iterator;
        else
        if ((iterator == separatorsUsed[0] || iterator == separatorsUsed[1]) && partialStringObtained != "") {
            if (find(trashValues.begin(), trashValues.end(), partialStringObtained) == trashValues.end()) {
                explodedString.push_back(partialStringObtained);
            }
            partialStringObtained = "";
        }
    }
    if (partialStringObtained != "")
        explodedString.push_back(partialStringObtained);
    return explodedString;
}

Movie FileRepository::findMovie(const std::string& titleOfTheMovieToAdd)
{
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieFound = std::find_if(movieList.begin(), movieList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });

    if (iteratorWhereMovieFound == movieList.end())
        throw std::runtime_error("Invalid title");
    return *iteratorWhereMovieFound;
}

