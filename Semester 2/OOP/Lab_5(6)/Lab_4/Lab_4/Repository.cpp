#include "Repository.h"
#include <iostream>
Repository::Repository()
{
}


int Repository::addMovie(const Movie& movieToAdd)
{
	if (std::find(movieList.begin(), movieList.end(), movieToAdd) != movieList.end())
		return -1;
	movieList.push_back(movieToAdd);
	return 1;
}

int Repository::deleteMovie(const Movie& movieToDelete)
{
	
	auto movieFound = std::find(movieList.begin(), movieList.end(), movieToDelete);
	if (movieFound == movieList.end())
		return -1;
	movieList.erase(movieFound);
	return 1;
}

int Repository::updateMovie(const Movie& movieToUpdateWith)
{
	auto movieFound = std::find(movieList.begin(), movieList.end(), movieToUpdateWith);
	if (movieFound == movieList.end())
		return -1;
	*movieFound = movieToUpdateWith;
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

std::vector<Movie> Repository::getMoviesByGenre(const std::string& genreGiven)
{
	 std::vector<Movie> moviesOfGenre;
	 for (int i = 0; i < movieList.size(); ++i)
		 if (genreGiven == movieList[i].getGenre() || genreGiven == "") {
			 moviesOfGenre.push_back(movieList[i]);
		 }
	 return moviesOfGenre;
}

std::vector<Movie> Repository::getAllMovies()
{
	return movieList;
}

std::vector<Movie> Repository::getAllWatchListMovies()
{
	return userWatchList;
}

int Repository::addMovieToWatchlist(const Movie& movieToAdd)
{
	auto it = std::find(userWatchList.begin(), userWatchList.end(), movieToAdd);
	if (it != userWatchList.end())
		return -1;
	userWatchList.push_back(movieToAdd);
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
	auto it = std::find(userWatchList.begin(), userWatchList.end(), movieList[i]);
	if (it != userWatchList.end())
		return -1;
	userWatchList.push_back(movieList[i]);
	return 1;
}


int Repository::getNumberOfMoviesWatchList()
{
	return userWatchList.size();
}
