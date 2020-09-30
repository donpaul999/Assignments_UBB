//
// Created by Paul Colta on 05/05/2020.
//

#ifndef LAB8_ACTIONADD_H
#define LAB8_ACTIONADD_H

#include "Action.h"
#include "FileRepository.h"
#include "Movie.h"

class ActionAdd: public Action{
private:
    Movie addedMovie;
    FileRepository* repositoryUsed;
public:
    ActionAdd(FileRepository* repository, Movie movie):addedMovie {movie}, repositoryUsed{repository}{}

    void executeUndo() override;
    void executeRedo() override;

};



#endif //LAB8_ACTIONADD_H
