#include "AdminService.h"

//AdminService constructor
AdminService::AdminService(Repository& repository):repository{repository}{}


/*
	Explode a string after certain separators - stringToExplode - string to be separated
											separatorUsed - character used to divide the string
*/
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

/*
	Add a movie in the list - title - title of the movie
							genre - genre of the movie
							yearOfRelease - the year when the movie was released
							numberOfLikes - trailer's number of likes
							trailer - link to the movie's trailer
*/
int AdminService::adminAddMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer)
{
	Movie movieUsed{ title, genre, yearOfRelease, numberOfLikes, trailer };
	return repository.addMovie(movieUsed);
}


/*
	Delete a movie from the list, by its title
*/
int AdminService::adminDeleteMovie(const std::string& title)
{
	Movie movieUsed{ title, "fillGenre", 2020, 0, "No trailer" };
	return repository.deleteMovie(movieUsed);
}

/*
	Add a movie in the list - title - title of the movie to be updated
							genre - genre of the movie to update with
							yearOfRelease - the year when the movie was released to update with
							numberOfLikes - trailer's number of likes to update with
							trailer - link to the movie's trailer to update with
*/
int AdminService::adminUpdateMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer)
{
	Movie movieUsed{ title, genre, yearOfRelease, numberOfLikes, trailer };
	return repository.updateMovie(movieUsed);
}

//Return the list of movies
std::vector<Movie> AdminService::adminGetMovieList()
{
	return repository.getAllMovies();
}

//Change the file name used by repository
int AdminService::changeRepositoryFileName(const std::string& nameOfTheFileUsed)
{
	repository.changeFileName(nameOfTheFileUsed);
	return 1;
}
