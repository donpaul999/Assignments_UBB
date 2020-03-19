#include "UserService.h"
UserService::UserService(Repository& repository) :repository{ repository } {}
std::vector<Movie> UserService::userGetMovieList()
{
	int sizeOfVector = repository.getNumberOfMovies();
	std::vector<Movie> listOfMovies;
	Movie movieUsed;
	for (int i = 0; i < sizeOfVector; ++i) {
		movieUsed = repository.getMovieAtPosition(i);
		listOfMovies.push_back(movieUsed);
	}
	return listOfMovies;
}
