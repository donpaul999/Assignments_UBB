#include "UserService.h"

/*
    UserService constructor - repository - repository used
                              currentMoviePosition - the index of the movie listed
*/
UserService::UserService(Repository& repository, int currentMoviePosition): repository{ repository }, currentMoviePosition{ currentMoviePosition } {}

//Get the list of all movies
std::vector<Movie> UserService::userGetMovieList()
{
	return repository.getAllMovies();
}

//Get the list of movies in the watch list
std::vector<Movie> UserService::userGetWatchList()
{
    return repository.getAllWatchListMovies();
}

//Get the list of movies with a given genre
int UserService::listMoviesByGenre(const std::string& genreGiven)
{
    currentMoviesByGenre = repository.getMoviesByGenre(genreGiven);
    if (currentMoviesByGenre.size() == 0)
        return -1;
    currentMoviePosition = 0;
    return 1;
}

//Add a movie to the watch list by the current movie position
int UserService::addMovieToWatchList()
{
    return repository.addMovieToWatchlist(currentMoviesByGenre[currentMoviePosition]);
}

//Add a movie to the watch list by the given title
int UserService::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
    return repository.addMovieToWatchListByTitle(titleOfTheMovieToAdd);
}


//Iterate to the next movie in the list
void UserService::goToNextMovieByGenre()
{
    currentMoviePosition++;
    if(currentMoviePosition == currentMoviesByGenre.size())
        currentMoviePosition = 0; 
}

//Get the length of the watch list
int UserService::getWatchListLength()
{
    return repository.getNumberOfMoviesWatchList();
}

//Get the current movie in the list
Movie UserService::getCurrentMovie()
{
    return currentMoviesByGenre[currentMoviePosition];
}

