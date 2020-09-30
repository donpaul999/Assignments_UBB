//
// Created by Paul Colta on 30/04/2020.
//

#ifndef LAB8_CSVREPOSITORY_H
#define LAB8_CSVREPOSITORY_H


#include "FileRepository.h"
#include <fstream>

class CSVRepository: public FileRepository {
public:
    CSVRepository(std::string fileName="", std::string movieFileName=""){this->userFileName = fileName; this->movieFileName = movieFileName; this->memoryOrFile = 0;};
    void writeUserMoviesToFile( std::vector<Movie> movieList, std::string movieFileName) override;
    int addMovieToWatchlist(const Movie& movieToAdd) override;
    void deleteMovieFromWatchlist(const Movie& movieToDelete) override;
    int addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd) override;
    int getNumberOfMoviesWatchList() override;
    std::vector<Movie> getAllWatchListMovies() override;
};

#endif //LAB8_CSVREPOSITORY_H
