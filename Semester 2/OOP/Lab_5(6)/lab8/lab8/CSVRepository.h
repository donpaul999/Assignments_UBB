//
// Created by Paul Colta on 16/04/2020.
//

#ifndef LAB8_CSVREPOSITORY_H
#define LAB8_CSVREPOSITORY_H

#include <string>
#include "FileRepository.h"

class CSVRepository: public FileRepository {
private:
    std::string CSVfileName;
    void updateCSVFile();

public:
    CSVRepository(const std::string& movieFileName, const std::string& CSVfileName = "");
    ~CSVRepository();
    void addMovieToWatchlist(const Movie& movie);
    void deleteMovieFromWatchlist(const Movie& movie);
    std::string getFilename();
};



#endif //LAB8_CSVREPOSITORY_H
