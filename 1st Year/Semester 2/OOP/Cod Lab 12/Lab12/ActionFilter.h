//
// Created by Paul Colta on 14/05/2020.
//

#ifndef LAB12_ACTIONFILTER_H
#define LAB12_ACTIONFILTER_H
#include "Action.h"
#include "FileRepository.h"
#include "Movie.h"
#include <vector>

class ActionFilter: public Action{
    std::vector<Movie> deletedMovies;
    FileRepository* repositoryUsed;
public:
    void executeUndo() override;
    void executeRedo() override;
    ActionFilter(FileRepository* repository,  std::vector<Movie> movieList):deletedMovies {movieList}, repositoryUsed{repository}{}

};


#endif //LAB12_ACTIONFILTER_H
