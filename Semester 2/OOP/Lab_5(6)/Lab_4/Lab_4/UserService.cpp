#include "UserService.h"

UserService::UserService(Repository& repository, int currentMoviePosition): repository{ repository }, currentMoviePosition{ currentMoviePosition } {}

DynamicVector<Movie> UserService::userGetMovieList()
{
	return repository.getAllMovies();
}

DynamicVector<Movie> UserService::userGetWatchList()
{
    return repository.getAllWatchListMovies();
}

int UserService::listMoviesByGenre(const std::string& genreGiven)
{
    currentMoviesByGenre = repository.getMoviesByGenre(genreGiven);
    if (currentMoviesByGenre.size() == 0)
        return -1;
    currentMoviePosition = 0;
}

int UserService::addMovieToWatchList()
{
    return repository.addMovieToWatchlist(currentMoviesByGenre[currentMoviePosition]);
}

int UserService::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
    return repository.addMovieToWatchListByTitle(titleOfTheMovieToAdd);
}


void UserService::goToNextMovieByGenre()
{
    currentMoviePosition++;
    if(currentMoviePosition == currentMoviesByGenre.size())
        currentMoviePosition = 0; 
}

int UserService::getWatchListLength()
{
    return repository.getNumberOfMoviesWatchList();
}

Movie UserService::getCurrentMovie()
{
    return currentMoviesByGenre[currentMoviePosition];
}

