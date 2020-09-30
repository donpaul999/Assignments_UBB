//
// Created by Paul Colta on 05/05/2020.
//

#include "ActionRemove.h"

void ActionRemove::executeUndo(){
    repositoryUsed->addMovie(deletedMovie);
}

void ActionRemove::executeRedo(){
    repositoryUsed->deleteMovie(deletedMovie);
}