#include "FileRepository.h"
#include <iostream>
#include <fstream>



//Load movies from given file
std::vector<Movie> FileRepository::loadMoviesFromFile()
{
    if(memoryOrFile == 1)
        return std::vector<Movie> ();
    std::vector<Movie> movieList = {};
    Movie movieLoadedFromFile;
    if (movieFileName == "")
        throw RepositoryException(std::string("FileName is invalid"));
    try {
        std::ifstream fin(movieFileName);
        while (fin >> movieLoadedFromFile) {
            if (std::find(movieList.begin(), movieList.end(), movieLoadedFromFile) == movieList.end())
                movieList.push_back(movieLoadedFromFile);
        }
        fin.close();
        return movieList;
    }
    catch (std::exception & e) {

    }
}

void FileRepository::changeRepositoryType(std::string repositoryTypeGiven) {
    repositoryType = repositoryTypeGiven;
}

//Write movies to given file
void FileRepository::writeUserMoviesToFile(std::vector<Movie> movieList, std::string movieFileName)
{
    if(memoryOrFile == 1)
        return;
    //std::cout << repositoryType<<'\n';
    std::ofstream fout(movieFileName);
    //std::cout << movieFileName << '\n';
    for (const Movie &movieToWrite : movieList)
         fout << movieToWrite << '\n';
    fout.close();
}

void FileRepository::writeMoviesToFile(std::vector<Movie> movieList, std::string movieFileName)
{
        if(memoryOrFile == 1)
            return;
        std::ofstream fout(movieFileName);
       // std::cout << movieFileName << '\n';
        for (const Movie &movieToWrite : movieList)
            fout << movieToWrite << '\n';
        fout.close();

}

//Repository constructor
FileRepository::FileRepository(const std::string& nameOfTheFileUsed, std::string repositoryType, bool memoryOrFile)
{
    movieFileName = nameOfTheFileUsed;
    repositoryType = repositoryType;
    this->memoryOrFile = memoryOrFile;
}

//Repository destructor
FileRepository::~FileRepository()
{
}

void FileRepository::setMemoryOrFile(bool memoryOrFile){
    this->memoryOrFile = memoryOrFile;
}

//Add given movie to list
int FileRepository::addMovie(const Movie& movieToAdd)
{
    std::vector<Movie> movieList;
    if(memoryOrFile == 0)
        movieList = loadMoviesFromFile();
    else
        for(int i = 0; i < this->movieList.size();++i)
            movieList.push_back(this->movieList[i]);
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToAdd);
    if (iteratorWhereMovieIsFound !=  movieList.end() && movieList.size() != 0)
        throw RepositoryException(std::string("Movie already in the list"));
    if(memoryOrFile == 0) {
        movieList.push_back(movieToAdd);
        writeMoviesToFile(movieList, movieFileName);
    }
    else{
        this->movieList.push_back(movieToAdd);
    }
    return 1;
}



//Delete a movie from the movieList
int FileRepository::deleteMovie(const Movie& movieToDelete)
{
    std::vector<Movie> movieList;
    if(memoryOrFile == 0)
        movieList = loadMoviesFromFile();
    else
        for(int i = 0; i < this->movieList.size();++i)
            movieList.push_back(this->movieList[i]);
    if(memoryOrFile == 0) {
        auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToDelete);
        if (iteratorWhereMovieIsFound == movieList.end())
            throw RepositoryException(std::string("Movie not in the list"));
            movieList.erase(iteratorWhereMovieIsFound);
            writeMoviesToFile(movieList, movieFileName);
        }
    else{
        auto iteratorWhereMovieIsFound = std::find(this->movieList.begin(), this->movieList.end(),  movieToDelete);
        if (iteratorWhereMovieIsFound == this->movieList.end())
            throw RepositoryException(std::string("Movie not in the list"));
        this->movieList.erase(iteratorWhereMovieIsFound);
    }
    return 1;
}


//Update a movie from the movieList
int FileRepository::updateMovie(const Movie& movieToUpdateWith)
{
    std::vector<Movie> movieList;
    if(memoryOrFile == 0){
        movieList = loadMoviesFromFile();
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToUpdateWith);
    if (iteratorWhereMovieIsFound == movieList.end())
        throw RepositoryException(std::string("Movie not in the list"));
        *iteratorWhereMovieIsFound = movieToUpdateWith;
        writeMoviesToFile(movieList, movieFileName);
    }
    else{
        auto iteratorWhereMovieIsFound = std::find(this->movieList.begin(), this->movieList.end(),  movieToUpdateWith);
        if (iteratorWhereMovieIsFound == this->movieList.end())
            throw RepositoryException(std::string("Movie not in the list"));
        *iteratorWhereMovieIsFound = movieToUpdateWith;

    }
    return 1;
}

//Change the file name of the repository
void FileRepository::changeFileName(const std::string& nameOfTheFileUsed)
{
    //std::vector<Movie> movieList = loadMoviesFromFile();
    if(memoryOrFile == 1)
        throw RepositoryException(std::string("Movies are in memory"));
    movieFileName = nameOfTheFileUsed;
    //writeMoviesToFile(movieList, movieFileName);
}
`

//Get the movie at a certain position
Movie FileRepository::getMovieAtPosition(int positionOfMovie)
{
    std::vector<Movie> movieList;
    if(memoryOrFile == 0)
        movieList = loadMoviesFromFile();
    else
        for(int i = 0; i < this->movieList.size();++i)
            movieList.push_back(this->movieList[i]);
    if (positionOfMovie < 0 || positionOfMovie >= movieList.size())
        throw RepositoryException(std::string("Invalid position"));
    return movieList[positionOfMovie];}

//Get the number of movies in the list

int FileRepository::getNumberOfMovies()
{
    std::vector<Movie> movieList;
    if(memoryOrFile == 0)
        movieList = loadMoviesFromFile();
    else
        for(int i = 0; i < this->movieList.size();++i)
            movieList.push_back(this->movieList[i]);
    return movieList.size();
}

//Get all the movies with a given genre
std::vector<Movie> FileRepository::getMoviesByGenre(const std::string& genreGiven)
{
    std::vector<Movie> movieList;
    if(memoryOrFile == 0)
        movieList = loadMoviesFromFile();
    else
        for(int i = 0; i < this->movieList.size();++i)
            movieList.push_back(this->movieList[i]);
    std::vector<Movie> moviesWithGenre = {};
    if (genreGiven == "")
        return movieList;
    std::copy_if(movieList.begin(), movieList.end(), std::back_inserter(moviesWithGenre), [&genreGiven](const Movie& movie) {return movie.getGenre() == genreGiven; });
    return moviesWithGenre;
}

//Get all the movies
std::vector<Movie> FileRepository::getAllMovies()
{
    memoryOrFile = 0;
    std::vector<Movie> movieList;
    if(memoryOrFile == 0)
        movieList = loadMoviesFromFile();
    else
        for(int i = 0; i < this->movieList.size();++i)
            movieList.push_back(this->movieList[i]);
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
    std::vector<Movie> movieList;
    if(memoryOrFile == 0)
        movieList = loadMoviesFromFile();
    else
        for(int i = 0; i < this->movieList.size();++i)
            movieList.push_back(this->movieList[i]);
    if(memoryOrFile == 0) {
        std::vector<Movie> watchList = getAllWatchListMovies();
        auto iteratorWhereMovieIsFound = std::find(watchList.begin(), watchList.end(), movieToAdd);
        if (iteratorWhereMovieIsFound != watchList.end())
            throw RepositoryException(std::string("Movie already in the watch list"));
        watchList.push_back(movieToAdd);
        writeUserMoviesToFile(watchList, userFileName);
    }
    else{
        auto iteratorWhereMovieIsFound = std::find(userWatchList.begin(), userWatchList.end(), movieToAdd);
        if (iteratorWhereMovieIsFound != userWatchList.end())
            throw RepositoryException(std::string("Movie already in the watch list"));
        userWatchList.push_back(movieToAdd);
    }
    return 1;
}

//
void FileRepository::deleteMovieFromWatchlist(const Movie& movieToDelete)
{
    auto iteratorWhereMovieIsFound = std::find(userWatchList.begin(), userWatchList.end(), movieToDelete);
    if (iteratorWhereMovieIsFound != userWatchList.end())
        throw RepositoryException(std::string("Movie already in the watch list"));
    userWatchList.erase(iteratorWhereMovieIsFound);
    if(memoryOrFile == 0)
        writeUserMoviesToFile(userWatchList, userFileName);
}

//Add a certain movie to the watch list by the title
int FileRepository::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
    std::vector<Movie> movieList;
    if(memoryOrFile == 0)
        movieList = loadMoviesFromFile();
    else
        for(int i = 0; i < this->movieList.size();++i)
            movieList.push_back(this->movieList[i]);
    auto iteratorWhereMovieFound = std::find_if(movieList.begin(), movieList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
    if (iteratorWhereMovieFound == movieList.end())
        throw RepositoryException(std::
        string("Movie with this title not exist."));
    if(memoryOrFile == 0) {
        std::vector<Movie> watchList = getAllWatchListMovies();
        auto iteratorWhereMovieFoundInMyList = std::find_if(watchList.begin(), watchList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
        if (iteratorWhereMovieFoundInMyList != watchList.end())
            throw RepositoryException(std::string("Movie already in the watch list"));
        watchList.push_back(*iteratorWhereMovieFound);
        writeUserMoviesToFile(watchList, userFileName);
    }
    else{
        auto iteratorWhereMovieFoundInMyList = std::find_if(userWatchList.begin(), userWatchList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
        if (iteratorWhereMovieFoundInMyList != userWatchList.end())
            throw RepositoryException(std::string("Movie already in the watch list"));
        userWatchList.push_back(*iteratorWhereMovieFound);
    }
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
    if(movieFileName != "" && memoryOrFile == 0)
        return movieFileName;
    return " ";
}

std::string FileRepository::getUserFileName(){
    if(userFileName != "" && memoryOrFile == 0)
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
    std::vector<Movie> movieList;
    if(memoryOrFile == 0)
        movieList = loadMoviesFromFile();
    else
        for(int i = 0; i < this->movieList.size();++i)
            movieList.push_back(this->movieList[i]);
    auto iteratorWhereMovieFound = std::find_if(movieList.begin(), movieList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });

    if (iteratorWhereMovieFound == movieList.end())
        throw std::runtime_error("Invalid title");
    return *iteratorWhereMovieFound;
}

void FileRepository::filterMoviesByGenre(const std::string &genreGiven) {
    if(memoryOrFile == 0){
        std::vector<Movie> movieList = getMoviesByGenre(genreGiven);
        writeMoviesToFile(movieList, movieFileName);
    }
    else{
        movieList = getMoviesByGenre(genreGiven);
    }
}

