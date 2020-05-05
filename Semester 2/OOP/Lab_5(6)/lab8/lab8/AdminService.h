#pragma once
#include "Repository.h"
#include "FileRepository.h"
#include <vector>
#include <fstream>
#include "ValidationException.h"
#include "MovieValidator.h"
#include <memory>
#include "Action.h"
#include "ActionAdd.h"
#include "ActionUpdate.h"
#include "ActionRemove.h"

class AdminService {
private:
    std::vector<std::unique_ptr<Action>> undoSteps;
    std::vector<std::unique_ptr<Action>> redoSteps;
    FileRepository* repository;
    bool inUndoRedo;

public:
    //AdminService(Repository& repository);
    AdminService(FileRepository* repository);
    const std::vector<std::string> explode(const std::string& stringToExplode, const char& separatorUsed);
    int adminAddMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer);
    int adminDeleteMovie(const std::string& title);
    int adminUpdateMovie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer);
    std::vector<Movie> adminGetMovieList();
    int changeRepositoryFileName(const std::string& nameOfTheFileUsed);
    void undo();
    void redo();
    void emptyRedo();
};