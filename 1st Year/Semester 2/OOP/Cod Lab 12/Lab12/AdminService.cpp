#include "AdminService.h"
#include "ActionFilter.h"
#include <iostream>

//AdminService constructor
//AdminService::AdminService(Repository& repository) :repository{ repository } {}
AdminService::AdminService(FileRepository* newRepository){
	this->repository = newRepository;
	this->inUndoRedo = 0;
}

void AdminService::filterMoviesByGenre(const std::string& genreGiven){
    std::vector<Movie> listOfMovies = this->adminGetMovieList();
    std::vector<Movie> deletedMovies;
    for(int i = 0; i < listOfMovies.size(); ++i)
        if(listOfMovies[i].getGenre() != genreGiven)
            deletedMovies.push_back(listOfMovies[i]);
    std::unique_ptr<Action> filterAction = std::make_unique<ActionFilter>(repository, deletedMovies);
    undoSteps.push_back(move(filterAction));
    if(!inUndoRedo) {
        emptyRedo();
    }
    repository->filterMoviesByGenre(genreGiven);
}

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
    MovieValidator::validateMovie(movieUsed);
        std::unique_ptr<Action> addAction = std::make_unique<ActionAdd>(repository, movieUsed);
        undoSteps.push_back(move(addAction));
    if(!inUndoRedo) {
        emptyRedo();
    }
	return repository->addMovie(movieUsed);
}


/*
	Delete a movie from the list, by its title
*/
int AdminService::adminDeleteMovie(const std::string& title)
{
	Movie movieUsed{ title, "fillGenre", 2020, 0, "No trailer" };
	Movie movieFound = repository->findMovie(title);
	std::unique_ptr<Action> deleteAction = std::make_unique<ActionRemove>(repository, movieFound);
	undoSteps.push_back(move(deleteAction));
    if(!inUndoRedo) {
        emptyRedo();
    }
	return repository->deleteMovie(movieUsed);
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
    MovieValidator::validateMovie(movieUsed);
    Movie movieFound = repository->findMovie(title);
    std::unique_ptr<Action> updateAction = std::make_unique<ActionUpdate>(repository, movieFound, movieUsed);
    undoSteps.push_back(move(updateAction));
    if(!inUndoRedo) {
        emptyRedo();
    }
    return repository->updateMovie(movieUsed);
}

//Return the list of movies
std::vector<Movie> AdminService::adminGetMovieList()
{
	return repository->getAllMovies();
}

//Change the file name used by repository
int AdminService::changeRepositoryFileName(const std::string& nameOfTheFileUsed)
{
	repository->changeFileName(nameOfTheFileUsed);
	return 1;
}

void AdminService::undo(){
    if(undoSteps.size() == 0)
        throw ValidationException("No more undos!\n");
    inUndoRedo = 1;
    undoSteps[undoSteps.size() - 1]->executeUndo();
    redoSteps.push_back(move(undoSteps[undoSteps.size() - 1]));
    undoSteps.pop_back();
    inUndoRedo = 0;
}

void AdminService::redo(){
    if(redoSteps.size() == 0)
        throw ValidationException("No more redos!\n");
    inUndoRedo = 1;
    redoSteps[redoSteps.size() - 1]->executeRedo();
    undoSteps.push_back(move(redoSteps[redoSteps.size() - 1]));
    redoSteps.pop_back();
    inUndoRedo = 0;
}

void AdminService::emptyRedo(){
    redoSteps.clear();
}