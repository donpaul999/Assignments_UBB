//
// Created by Paul Colta on 05/05/2020.
//

#include "ActionAdd.h"

void ActionAdd::executeUndo(){
    repositoryUsed->deleteMovie(addedMovie);
}

void ActionAdd::executeRedo(){
    repositoryUsed->addMovie(addedMovie);
}