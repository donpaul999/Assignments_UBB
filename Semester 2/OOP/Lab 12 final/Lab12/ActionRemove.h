//
// Created by Paul Colta on 05/05/2020.
//

#ifndef LAB8_ACTIONREMOVE_H
#define LAB8_ACTIONREMOVE_H

#include "Action.h"
#include "FileRepository.h"
#include "Movie.h"

class ActionRemove:  public Action{
    Movie deletedMovie;
    FileRepository* repositoryUsed;
public:
    void executeUndo() override;
    void executeRedo() override;
    ActionRemove(FileRepository* repository, Movie movie):deletedMovie {movie}, repositoryUsed{repository}{}

};


#endif //LAB8_ACTIONREMOVE_H
