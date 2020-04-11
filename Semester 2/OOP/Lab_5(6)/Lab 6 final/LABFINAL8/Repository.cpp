#include "Repository.h"
#include <iostream>
#include <fstream>



//Load movies from given file
void Repository::loadMoviesFromFile()
{
    if (movieFileName == "")
        return;
    Movie movieLoadedFromFile;
    std::ifstream fin(movieFileName);
    while (fin >> movieLoadedFromFile) {
        if(std::find(movieList.begin(), movieList.end(), movieLoadedFromFile) == movieList.end())
            movieList.push_back(movieLoadedFromFile);
    }
    fin.close();
}


//Write movies to given file
void Repository::writeMoviesToFile()
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
Repository::Repository(const std::string& nameOfTheFileUsed)
{
    std::vector<Movie> userWatchList{};
    std::vector<Movie>{};
    movieFileName = nameOfTheFileUsed;
    loadMoviesFromFile();

}

//Repository destructor
Repository::~Repository()
{
    writeMoviesToFile();
}


//Add given movie to list
int Repository::addMovie(const Movie& movieToAdd)
{
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToAdd);
    if (iteratorWhereMovieIsFound !=  movieList.end() && movieList.size() != 0)
        return -1;
    movieList.push_back(movieToAdd);
    writeMoviesToFile();
    return 1;
}



//Delete a movie from the movieList
int Repository::deleteMovie(const Movie& movieToDelete)
{
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToDelete);
    if (iteratorWhereMovieIsFound == movieList.end())
        return -1;
    movieList.erase(iteratorWhereMovieIsFound);
    writeMoviesToFile();
    return 1;
}


//Update a movie from the movieList
int Repository::updateMovie(const Movie& movieToUpdateWith)
{
    auto iteratorWhereMovieIsFound = std::find(movieList.begin(), movieList.end(),  movieToUpdateWith);
	if (iteratorWhereMovieIsFound == movieList.end())
		return -1;
	*iteratorWhereMovieIsFound = movieToUpdateWith;
    writeMoviesToFile();
	return 1;
}

//Change the file name of the repository
void Repository::changeFileName(const std::string& nameOfTheFileUsed)
{

    movieFileName = nameOfTheFileUsed;
    writeMoviesToFile();
}


//Get the movie at a certain position
Movie Repository::getMovieAtPosition(int positionOfMovie)
{
	if (positionOfMovie < 0 || positionOfMovie >= movieList.size())
		throw std::runtime_error("Invalid position");
	return movieList[positionOfMovie];}

//Get the number of movies in the list
int Repository::getNumberOfMovies()
{
	return movieList.size();
}

//Get all the movies with a given genre
std::vector<Movie> Repository::getMoviesByGenre(const std::string& genreGiven)
{
    std::vector<Movie> moviesWithGenre;
    if (genreGiven == "")
        return movieList;
    std::copy_if(movieList.begin(), movieList.end(), std::back_inserter(moviesWithGenre), [&genreGiven](const Movie& movie) {return movie.getGenre() == genreGiven; });
    return moviesWithGenre;
}

//Get all the movies
std::vector<Movie> Repository::getAllMovies()
{
	return movieList;
}


//Get all the movies in the watch list
std::vector<Movie> Repository::getAllWatchListMovies()
{
	return userWatchList;
}


//Add a certain movie to the watch list
int Repository::addMovieToWatchlist(const Movie& movieToAdd)
{
    auto iteratorWhereMovieIsFound = std::find(userWatchList.begin(), userWatchList.end(), movieToAdd);
    if (iteratorWhereMovieIsFound != userWatchList.end())
        return -1;
    userWatchList.push_back(movieToAdd);
    return 1;
}


//Add a certain movie to the watch list by the title
int Repository::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
    auto iteratorWhereMovieFound = std::find_if(movieList.begin(), movieList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
    if (iteratorWhereMovieFound == movieList.end())
        return -1;
    userWatchList.push_back(*iteratorWhereMovieFound);
    return 1;
}

//Get the number of movies in the watch list
int Repository::getNumberOfMoviesWatchList()
{
	return userWatchList.size();
}
