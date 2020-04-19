//
// Created by Paul Colta on 16/04/2020.
//

#ifndef LAB8_HTMLREPOSITORY_H
#define LAB8_HTMLREPOSITORY_H

#include "FileRepository.h"
#include <string>

class HTMLRepository: public FileRepository {
private:
    std::string HTMLfileName;
    void updateHTMLFile();

public:
    HTMLRepository(const std::string& movieFileName, const std::string& HTMLfileName = "");
    ~HTMLRepository();
    void addMovieToWatchlist(const Movie& movieToAdd);
    void deleteMovieFromWatchlist(const Movie& movieToDelete);
    std::string getFilename();
};

#endif //LAB8_HTMLREPOSITORY_H
