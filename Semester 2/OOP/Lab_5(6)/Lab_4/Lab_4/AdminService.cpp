#include "AdminService.h"

AdminService::AdminService(Repository& repository):repository{repository}{}


const std::vector<std::string> AdminService::explode(const std::string& stringToExplode, const char& separatorUsed)
{
	std::string partialStringObtained{ "" };
	std::vector<std::string> explodedString;

	for (auto iterator : stringToExplode)
	{
		if (iterator != separatorUsed) 
			partialStringObtained += iterator;
		else
			if (iterator == separatorUsed && partialStringObtained != ""){ 
				explodedString.push_back(partialStringObtained);
			    partialStringObtained = "";
			}
	}
	if (partialStringObtained != "") 
		explodedString.push_back(partialStringObtained);
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

DynamicVector<Movie> AdminService::adminGetMovieList()
{
	return repository.getAllMovies();
}
