#include "Repository.h"
#include <iostream>

Repository::Repository()
{
}


int Repository::addMovie(const Movie& movieToAdd)
{
    int positionWhereMovieIsFound = findMovie(movieList, movieToAdd);
    if (positionWhereMovieIsFound != -1)
        return -1;
    movieList.append(movieToAdd);
    return 1;
}

int Repository::findMovie(DynamicVector<Movie> listOfMovies, const Movie& movieToSearch)
{
    for (int i = 0; i < listOfMovies.size(); ++i)
        if (movieToSearch == listOfMovies[i]) {
            return i;
        }
    return -1;
}

int Repository::deleteMovie(const Movie& movieToDelete)
{
    int positionWhereMovieIsFound = findMovie(movieList, movieToDelete);
    if (positionWhereMovieIsFound == -1)
        return -1;
    movieList.remove(movieToDelete);
    return 1;
}

int Repository::updateMovie(const Movie& movieToUpdateWith)
{
	int positionWhereMovieIsFound = findMovie(movieList, movieToUpdateWith);
	if (positionWhereMovieIsFound == -1)
		return -1;
	movieList[positionWhereMovieIsFound] = movieToUpdateWith;
	return 1;
}

Movie Repository::getMovieAtPosition(int positionOfMovie)
{
	if (positionOfMovie < 0 || positionOfMovie >= movieList.size())
		throw std::runtime_error("Invalid position");
	return movieList[positionOfMovie];
}

int Repository::getNumberOfMovies()
{
	return movieList.size();
}

DynamicVector<Movie> Repository::getMoviesByGenre(const std::string& genreGiven)
{
    DynamicVector<Movie> moviesOfGenre;
	 for (int i = 0; i < movieList.size(); ++i)
		 if (genreGiven == movieList[i].getGenre() || genreGiven == "") {
			 moviesOfGenre.append(movieList[i]);
		 }
	 return moviesOfGenre;
}

DynamicVector<Movie> Repository::getAllMovies()
{
	return movieList;
}

DynamicVector<Movie> Repository::getAllWatchListMovies()
{
	return userWatchList;
}

int Repository::addMovieToWatchlist(const Movie& movieToAdd)
{
    int positionWhereMovieIsFound = findMovie(userWatchList, movieToAdd);
    if (positionWhereMovieIsFound == -1)
        return -1;
    userWatchList.append(movieToAdd);
    return 1;
}

int Repository::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
	int i;
	for (i = 0; i < movieList.size(); ++i)
		if (titleOfTheMovieToAdd == movieList[i].getTitle()) {
			break;
		}
	if (i == movieList.size())
		return -1;

    int positionWhereMovieIsFound = findMovie(userWatchList, movieList[i]);
    if (positionWhereMovieIsFound == -1)
        return -1;
    userWatchList.append(movieList[i]);
    return 1;
}


int Repository::getNumberOfMoviesWatchList()
{
	return userWatchList.size();
}
