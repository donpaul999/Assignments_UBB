#pragma once
#include "FileRepository.h"
#include "CSVRepository.h"
#include "HTMLRepository.h"
#include "ValidationException.h"
#include <memory>

class UserService {
private:
    FileRepository *repository;
    std::vector<Movie> currentMoviesByGenre;
    int currentMoviePosition;
    std::string repositoryType;
    std::string fileName;


public:
    UserService(FileRepository *repository, int currentMoviePosition = 0, std::string fileName = "",
                std::string givenRepositoryType = "txt");

    std::vector<Movie> userGetMovieList();

    std::vector<Movie> userGetWatchList();

    int listMoviesByGenre(const std::string &genreGiven);

    int addMovieToWatchList();

    int addMovieToWatchListByTitle(const std::string &titleOfTheMovieToAdd);

    void goToNextMovieByGenre();

    Movie getCurrentMovie();

    int getWatchListLength();

    int changeRepositoryFileName(const std::string &nameOfTheFileUsed, const std::string &extensionToChange);

    bool isRepositoryCSV() const;

    bool isRepositoryHTML() const;

    std::string getFileName() const;

    void updateRepositoryType(bool csvOrHTML, std::string fileName, std::string movieFileName);
    void filterMoviesByGenre(const std::string& genreGiven);
};
