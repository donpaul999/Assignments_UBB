//
// Created by Paul Colta on 05/05/2020.
//

#ifndef LAB8_ACTIONUPDATE_H
#define LAB8_ACTIONUPDATE_H


#include "Action.h"
#include "FileRepository.h"
#include "Movie.h"

class ActionUpdate:  public Action{
    Movie newMovie;
    Movie oldMovie;
    FileRepository* repositoryUsed;
public:
    void executeUndo() override;
    void executeRedo() override;
    ActionUpdate(FileRepository* repository, Movie oldMovie, Movie newMovie):oldMovie {oldMovie}, newMovie {newMovie}, repositoryUsed{repository}{}

};


#endif //LAB8_ACTIONUPDATE_H
