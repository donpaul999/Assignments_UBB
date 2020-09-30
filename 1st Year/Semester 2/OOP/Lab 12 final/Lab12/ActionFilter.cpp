//
// Created by Paul Colta on 14/05/2020.
//

#include <iostream>
#include "ActionFilter.h"


void ActionFilter::executeUndo(){
    for(int i = 0; i < deletedMovies.size(); ++i) {
        repositoryUsed->addMovie(deletedMovies[i]);
    }
}

void ActionFilter::executeRedo(){
    for(int i = 0; i < deletedMovies.size(); ++i)
        repositoryUsed->deleteMovie(deletedMovies[i]);
}