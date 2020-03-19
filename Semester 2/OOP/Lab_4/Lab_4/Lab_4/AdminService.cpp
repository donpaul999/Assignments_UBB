#include "AdminService.h"

AdminService::AdminService(Repository& repository):repository{repository}{}

AdminService::~AdminService()
{
	//
}

const std::vector<std::string> AdminService::explode(const std::string& stringToExplode, const char& separatorUsed)
{
	std::string buffer{ "" };
	std::vector<std::string> explodedString;

	for (auto iterator : stringToExplode)
	{
		if (iterator != separatorUsed) 
			buffer += iterator;
		else
			if (iterator == separatorUsed && buffer != ""){ 
				explodedString.push_back(buffer);
			    buffer = "";
			}
	}
	if (buffer != "") 
		explodedString.push_back(buffer);
	return explodedString;
}

int AdminService::adminAddMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer)
{
	Movie movieUsed{ title, genre, yearOfRelease, numberOfLikes, trailer };
	return repository.addMovie(movieUsed);
}

int AdminService::adminDeleteMovie(const std::string& title)
{
	Movie movieUsed{ title, "fillGenre", 2020, 0, "No trailer" };
	return repository.deleteMovie(movieUsed);
}

int AdminService::adminUpdateMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer)
{
	Movie movieUsed{ title, genre, yearOfRelease, numberOfLikes, trailer };
	return repository.updateMovie(movieUsed);
}

std::vector<Movie> AdminService::adminGetMovieList()
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
