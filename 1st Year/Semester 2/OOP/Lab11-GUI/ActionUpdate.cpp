//
// Created by Paul Colta on 05/05/2020.
//

#include "ActionUpdate.h"

void ActionUpdate::executeUndo(){
    repositoryUsed->deleteMovie(newMovie);
    repositoryUsed->addMovie(oldMovie);
}

void ActionUpdate::executeRedo(){
    repositoryUsed->deleteMovie(oldMovie);
    repositoryUsed->addMovie(newMovie);
}