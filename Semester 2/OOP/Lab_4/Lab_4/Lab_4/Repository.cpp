#include "Repository.h"


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
