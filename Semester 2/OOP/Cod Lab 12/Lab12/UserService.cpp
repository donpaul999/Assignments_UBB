#include "UserService.h"
#include <iostream>
/*
    UserService constructor - repository - repository used
                              currentMoviePosition - the index of the movie listed
                              repositoryType - repository Type Given (CSV, HTML, TXT)
*/
UserService::UserService(FileRepository *repository, int currentMoviePosition, std::string fileName, std::string givenRepositoryType) : currentMoviePosition{ currentMoviePosition }, currentMoviesByGenre{repository->getAllMovies()}, repositoryType{givenRepositoryType} {
    this->repository = repository ;
    repository->changeRepositoryType(repositoryType);
}

//Get the list of all movies
std::vector<Movie> UserService::userGetMovieList()
{
	return repository->getAllMovies();
}

//Get the list of movies in the watch list
std::vector<Movie> UserService::userGetWatchList()
{
    return repository->getAllWatchListMovies();
}

//Get the list of movies with a given genre
int UserService::listMoviesByGenre(const std::string& genreGiven)
{
    std::string error = "No movies with this genre";
    currentMoviesByGenre = repository->getMoviesByGenre(genreGiven);
    if (currentMoviesByGenre.size() == 0)
        throw ValidationException(error);
    currentMoviePosition = 0;
    return 1;
}
void UserService::filterMoviesByGenre(const std::string& genreGiven){
    repository->filterMoviesByGenre(genreGiven);
}

//Add a movie to the watch list by the current movie position
int UserService::addMovieToWatchList()
{
    return repository->addMovieToWatchlist(currentMoviesByGenre[currentMoviePosition]);
}

//Add a movie to the watch list by the given title
int UserService::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
    return repository->addMovieToWatchListByTitle(titleOfTheMovieToAdd);
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
    return repository->getNumberOfMoviesWatchList();
}

//Get the current movie in the list
Movie UserService::getCurrentMovie()
{
    return currentMoviesByGenre[currentMoviePosition];
}

int UserService::changeRepositoryFileName(const std::string& nameOfTheFileUsed, const std::string& extensionToChangeWith)
{
    if(extensionToChangeWith != "txt" && extensionToChangeWith != "html" && extensionToChangeWith != "csv")
        throw ValidationException("Invalid extension provided");
    repositoryType = extensionToChangeWith;
    repository->changeUserFileName(nameOfTheFileUsed);
    repository->changeRepositoryType(extensionToChangeWith);
    updateRepositoryType(repositoryType == "csv", nameOfTheFileUsed, repository->getMovieFileName());
    return 1;
}


bool UserService::isRepositoryCSV() const {
    return repositoryType == "csv";
}

bool UserService::isRepositoryHTML() const {
    return repositoryType == "html";
}

std::string UserService:: getFileName() const{
    return repository->getUserFileName();
}

void UserService::updateRepositoryType(bool csvOrHTML, std::string fileName,std::string movieFileName ){
    std::vector<Movie> watchListMovies = repository->getAllWatchListMovies();
    FileRepository* correctRepository;
    delete this->repository;
    if(csvOrHTML == 1)
        correctRepository = new CSVRepository{fileName, movieFileName};
    else
        correctRepository = new HTMLRepository{fileName, movieFileName};
    this->repository = correctRepository;
    repository->setMemoryOrFile(0);
    for (const Movie& movieUsed: watchListMovies) {
        repository->addMovieToWatchlist(movieUsed);
    }

};
